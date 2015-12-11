package com.hj.test;

import com.hj.biz.service.YiyaoService;
import com.hj.client.object.list.LawData;
import com.hj.client.object.list.QyPowerDetailInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

import static com.hj.dal.Constants.idv;
import static com.hj.dal.Constants.idx;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2015/2/4  0:50
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JunitSpringTest extends AbstractJUnit4SpringContextTests {

    @Resource
    YiyaoService yiyaoService;

    @Test
    public void test1(){
        String name="华北制药集团新药研究开发有限责任公司";
        List<QyPowerDetailInfo> qyPowerInfos = yiyaoService.getQyPowerDetailInfo(name, idx, idv,null);
        for(QyPowerDetailInfo powerDetailInfo:qyPowerInfos){
            System.out.println(powerDetailInfo);
        }
    }

    @Test
    public void test2(){
        String key="中国";
//        String time="2013.5.5";
        LawData data=new LawData();
        data.setTitle(key);
//        data.setTime(time);
        List<LawData> datas= yiyaoService.getLawInfo(data,idx,idv,null);
        System.out.println(datas);
        System.out.println(datas.size());
    }


}
