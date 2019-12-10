package com.hhgs.kks.narikks;


import com.hhgs.kks.SpringbootKksApplication;
import com.hhgs.kks.mapper.NaRiPageMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootKksApplication.class)//这里是启动类
@WebAppConfiguration
public class NaRiPageDataTest {

    @Autowired
    private NaRiPageMapper naRiPageMapper;

    @Test
    public void testPageSelect() {

        List<Map<String, Object>> dataList = naRiPageMapper.getNaRiDataFromRownum(10000, 0, "黄河共和光伏电站");

        for (Map<String, Object> stringObjectMap : dataList) {
            Set<String> keySet = stringObjectMap.keySet();
            for (String s : keySet) {
                Object o = stringObjectMap.get(s);
                System.out.println(o+"===="+s);
            }
        }
    }
}
