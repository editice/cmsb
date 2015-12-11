package com.hj.biz.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hj.client.object.ValuePair1;
import com.hj.client.object.ValuePair2;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2015/3/17  23:03
 */
public class GraphUtil {

    public static Map<String, Integer> extra1(Map<String, Integer> input) {
        Map<String, Integer> res = Maps.newHashMap();
        if (CollectionUtils.isEmpty(input)) {
            return input;
        }
        List<ValuePair1> pairs = Lists.newArrayList();
        int total = 0;
        for (String s : input.keySet()) {
            ValuePair1 pair1 = new ValuePair1();
            pair1.setName(s);
            int v = input.get(s);
            total += v;
            pair1.setValue(v);
            pairs.add(pair1);
        }
        Collections.sort(pairs);
        List<ValuePair1> tmp = Lists.newArrayList();
        int t = 0;
        for (ValuePair1 pair1 : pairs) {
            if (t * 1.0 / total > 0.9) {
                ValuePair1 pair2=new ValuePair1();
                pair2.setName("其他类型");
                pair2.setValue(total-t);
                tmp.add(pair2);
                break;
            }
            int v = pair1.getValue();
            t += v;
            tmp.add(pair1);
        }
        for (ValuePair1 pair1 : tmp) {
            res.put(pair1.getName(), pair1.getValue());
        }
        return res;


    }

    public static Map<String, Long> extra2(Map<String, Long> input) {
        Map<String, Long> res = Maps.newHashMap();
        if (CollectionUtils.isEmpty(input)) {
            return input;
        }
        List<ValuePair2> pairs = Lists.newArrayList();
        long total = 0;
        for (String s : input.keySet()) {
            ValuePair2 pair1 = new ValuePair2();
            pair1.setName(s);
            long v = input.get(s);
            total += v;
            pair1.setValue(v);
            pairs.add(pair1);
        }
        Collections.sort(pairs);
        List<ValuePair2> tmp = Lists.newArrayList();
        long t = 0;
        int cnt=0;
        for (ValuePair2 pair1 : pairs) {
            if (t * 1.0 / total > 0.9 || cnt>BizProcess.UP_COUNT) {//超过一定的饼图展示数量 or 超过90%
                if(total-t==0){
                    break;
                }
                ValuePair2 pair2=new ValuePair2();
                pair2.setName("其他类型");
                pair2.setValue(total-t);
                tmp.add(pair2);
                break;
            }
            cnt++;
            long v = pair1.getValue();
            t += v;
            tmp.add(pair1);
        }
        for (ValuePair2 pair1 : tmp) {
            res.put(pair1.getName(), pair1.getValue());
        }
        return res;
    }
}
