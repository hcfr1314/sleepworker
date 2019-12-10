package com.hhgs.kks.narikks;


import com.hhgs.kks.SpringbootKksApplication;
import com.hhgs.kks.common.NaRiDataUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Collection;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootKksApplication.class)//这里是启动类
@WebAppConfiguration
public class NaRiMapTest {

    @Autowired
    NaRiDataUtil naRiDataUtil;

    @Test
    public void naRiMapTest() {
        Map<String, String> naRiData = naRiDataUtil.getNaRiData();

        Collection<String> values = naRiData.values();
        for (String value : values) {
            System.out.println(value);
        }

    }



}
