package com.hj.dal.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Lists.newArrayList;
import static java.util.Collections.emptyList;

/**
 * 此缓存需要手工指定 ID。如果需要 memoize 功能需要在此缓存上再封一层。
 */
public final class RemoteCache {
    // class, id => cached instance
    private final Map<Class<?>, Map<Object, Object>> cache = Maps.newHashMap();

    private static final Logger logger=LoggerFactory.getLogger(RemoteCache.class);

    private boolean needHoldRemoteCache = false;

    private static final class Null {
    }

    /**
     * 我们特殊对待 null。防止因为远程返回 null 而反复调用远程。
     */
    private static final Null NULL = new Null();

    // RemoteCache 是单例。但单例不好测。我们加入一个只供测试使用的构建函数。
    RemoteCache() {
    }

    public static RemoteCache instance() {
        return inst.get();
    }

    // 一个线程对应一个 RemoteCache 实例
    private static final ThreadLocal<RemoteCache> inst = new ThreadLocal<RemoteCache>() {
        @Override
        protected RemoteCache initialValue() {
            return new RemoteCache();
        }
    };

    /**
     * 将要缓存的对象放入缓存中。
     *
     * @param klass    要缓存对象的类型
     * @param id       要缓存对象的 ID
     * @param instance 要缓存对象的实例
     * @param <T>      要缓存对象的类型
     */
    public final <T> void put(Class<? super T> klass, Object id, @Nullable T instance) {
        if (instance != null)
            checkArgument(klass.isInstance(instance), "incompatible class and instance");

        @SuppressWarnings({"MismatchedQueryAndUpdateOfCollection"})
        Map<Object, Object> idToInstanceCache = getIdToInstanceCache(klass);

        if (instance != null) {
            idToInstanceCache.put(id, instance);
        }
        else {
            idToInstanceCache.put(id, NULL);
        }
        if (idToInstanceCache.size() > 1000) {
            logger.warn("RemoteCache: idToInstanceCache too large! size = " + idToInstanceCache.size() + ", class = " + klass);
        }
    }

    /**
     * 将对象批量放入缓存。批量放入的这些实例后续可以通过 ID 单独获取出来。所以，
     * {@code ids} 与 {@code instances} 的值必须是一一对应的。
     *
     * @param klass     要缓存对象的类型
     * @param ids       要缓存对象的 ID 列表
     * @param instances 与 ID 列表对应的实例列表，注意要保序
     * @param <T>       要缓存对象的类型
     */
    public final <T> void batchPut(Class<? super T> klass, List<?> ids, List<? extends T> instances) {
        checkArgument(ids.size() == instances.size(), "incompatible ids and instances");

        Iterator<?> idsItr = ids.iterator();
        Iterator<? extends T> instancesItr = instances.iterator();

        while (idsItr.hasNext()) {
            Object id = idsItr.next();
            T instance = instancesItr.next();
            if (instance != null)
                checkArgument(klass.isInstance(instance), "incompatible class and instance");

            put(klass, id, instance);
        }
    }

    /**
     * 以指定的类型和 ID 获取缓存中的对象。如果没有，则返回 {@code null}。
     *
     * @param klass 被缓存对象的类型（必须与 put 时一致，否则拿不到）
     * @param id    被缓存对象的 ID
     * @param <T>   被缓存对象的类型
     */
    @Nullable
    public final <T> T get(Class<T> klass, Object id) {
        return get(klass, id, null);
    }

    /**
     * 以指定的类型和 ID 获取缓存中的对象。如果没有，则返回 {@code callbackOnMiss}
     * 的调用结果。此调用结果会被自动缓存起来。后续的 {@code get/batchGet} 调用
     * 都会从缓存中取结果。
     *
     * @param klass          被缓存对象的类型（必须与 put 时一致，否则拿不到）
     * @param id             被缓存对象的 ID
     * @param callbackOnMiss 如果缓存没有命中时的回调
     * @param <T>            被缓存对象的类型
     */
    @Nullable
    public final <T> T get(Class<T> klass, Object id, @Nullable Callable<? extends T> callbackOnMiss) {
        Object ret = get0(klass, id, callbackOnMiss);
        if (ret == NULL) {
            return null;
        }
        //noinspection unchecked
        return (T) ret;
    }

    @Nullable
    private <T> Object get0(Class<T> klass, Object id, @Nullable Callable<? extends T> callbackOnMiss) {
        Map<Object, Object> idToInstanceCache = cache.get(klass);
        Object ret = idToInstanceCache == null ? null : idToInstanceCache.get(id);

        if (ret == NULL) {
            return NULL;
        }
        if (ret != null) {
            return ret;
        }

        if (callbackOnMiss != null) {
            try {
                T callbackRet = callbackOnMiss.call();

                put(klass, id, callbackRet);

                return callbackRet;
            }
            catch (Exception ex) {
                throw new CallbackOnMissExecutionException(klass, newArrayList(id), ex);
            }
        }

        return null;
    }

    /**
     * 以指定的类型和 ID 列表批量获取被缓存对象。如果缓存中不存在则返回 {@code null}。
     * 返回的被缓存对象列表与 {@code ids} 保持顺序一致。
     *
     * @param klass 被缓存对象的类型（必须与 put 时一致，否则拿不到）
     * @param ids   被缓存对象的 ID 列表
     * @param <T>   被缓存对象的类型
     */
    @Nonnull
    public final <T> List<T> batchGet(Class<T> klass, List<?> ids) {
        return batchGet(klass, ids, null);
    }

    /**
     * 以指定的类型和 ID 列表批量获取被缓存对象。返回的被缓存对象列表与 {@code ids}
     * 保持顺序一致。如果缓存中不存在则调用 {@code callbackOnMiss}，并且返回此回
     * 调的返回值。
     *
     * @param klass          被缓存对象的类型
     * @param ids            被缓存对象的 ID 列表
     * @param callbackOnMiss 缓存没有命中时的回调
     * @param <T>            被缓存对象的类型
     */
    @Nonnull
    public final <T> List<T> batchGet(Class<T> klass,
                                      List<?> ids,
                                      @Nullable Callable<List<T>> callbackOnMiss) {
        List<T> cachedInstances = batchGet0(klass, ids);

        if (cachedInstances.size() > ids.size()) {
            throw new IllegalStateException("incorrect cache size. expected ids.size:" +
                                            ids.size() + "but got size:" +
                                            cachedInstances.size());
        }
        if (cachedInstances.size() == ids.size()) {
            return cachedInstances;
        }

        if (callbackOnMiss != null) {
            try {
                List<T> ret = callbackOnMiss.call();

                batchPut(klass, ids, ret);

                return ret;
            }
            catch (Exception ex) {
                throw new CallbackOnMissExecutionException(klass, ids, ex);
            }
        }

        return Collections.emptyList();
    }

    @Nonnull
    final <T> List<T> batchGet0(@Nonnull Class<T> klass,
                                @Nonnull List<?> ids) {
        Iterator<?> idsItr = ids.iterator();

        List<T> ret = Lists.newArrayListWithCapacity(ids.size());

        while (idsItr.hasNext()) {
            Object id = idsItr.next();

            Object cachedInstance = get0(klass, id, null);
            if (cachedInstance == NULL) {
                ret.add(null);
            }
            else if (cachedInstance != null) {
                //noinspection unchecked
                ret.add((T) cachedInstance);
            }
            else {
                return emptyList();
            }
        }

        return ret;
    }

    @Nonnull
    private Map<Object, Object> getIdToInstanceCache(@Nonnull Class<?> klass) {
        Map<Object, Object> ret = cache.get(klass);
        if (ret == null) {
            ret = Maps.newHashMap();
            cache.put(klass, ret);
        }
        return ret;
    }

    /**
     * 清空当前线程对应的缓存。
     */
    public final void clear() {
        if (!inst.get().needHoldRemoteCache) {
            inst.remove();
        }
    }

    public static void forceClear() {
        inst.remove();
    }


    public static void flagNeedHoldRemoteCache() {
        inst.get().needHoldRemoteCache = true;
    }

    /**
     * 清空当前线程内以指定类型缓存的对象。
     */
    public final void clear(@Nonnull Class<?> klass) {
        @SuppressWarnings({"MismatchedQueryAndUpdateOfCollection"})
        Map<Object, Object> cacheOfClass = cache.get(klass);
        if (cacheOfClass != null) {
            cache.remove(klass);
        }
    }
}
