package com.hhgs.kks.narikks;

import com.hhgs.kks.SpringbootKksApplication;
import com.hhgs.kks.mapper.NaRiDataMapper;
import com.hhgs.kks.mapper.NaRiTableMsgMapper;
import com.hhgs.kks.service.INaRiDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootKksApplication.class)//这里是启动类
@WebAppConfiguration
public class NaRiDataTest {


    @Autowired
    private NaRiDataMapper naRiDataMapper;

    @Autowired
    private NaRiTableMsgMapper naRiTableMsgMapper;

   /* @Autowired
    private INaRiDataService nRiDataService;*/

    @Test
    public void NaRiDataTest() {
        List<Map<String, Object>> mapList = naRiDataMapper.selectFromDescript();
/*
        List<Map<Integer, String>> mapList = nRiDataService.selectFromDescript();
*/
        for (Map<String, Object> map : mapList) {
            //获取到每一个map元素的键集
            Set<String> keySet = map.keySet();
            //遍历键集，寻找值
            for (String key : keySet) {

               /* if (key .equals("ID") ) {
                    String id =map.get(key).toString();
                    System.out.println(key+"==="+id);

                } else {
                    String descript = map.get(key).toString();
                    System.out.println(key+"==="+descript);
                }*/

                String value = map.get(key).toString();

                System.out.println(key+"==="+value);

            }
        }
    }

    @Test
    public void selectFromPlantCodeTest() {
        List<Map<String, Object>> mapList = naRiDataMapper.getNaRiDataFromScheduler("黄河共和光伏电站");
        for (Map<String, Object> stringObjectMap : mapList) {

            Set<String> keySet = stringObjectMap.keySet();
            for (String key : keySet) {

               /* if (key .equals("ID") ) {
                    String id =map.get(key).toString();
                    System.out.println(key+"==="+id);

                } else {
                    String descript = map.get(key).toString();
                    System.out.println(key+"==="+descript);
                }*/

                String value = stringObjectMap.get(key).toString();

                System.out.println(key+"==="+value);

            }

        }

    }

    @Test
    public void columnTest() {
        List<String> ycColumnNames = naRiTableMsgMapper.getColumnName("YC");
        for (String ycColumnName : ycColumnNames) {
           // System.out.println(ycColumnName);
        }

        List<String> yxColumnNames = naRiTableMsgMapper.getColumnName("YX");
        for (String yxColumnName : yxColumnNames) {
            System.out.println(yxColumnName);
        }

        List<Map<String, Object>> yxDataList = naRiDataMapper.getYXDataWithScheduler("黄河共和光伏电站");
        for (Map<String, Object> stringObjectMap : yxDataList) {
            for (String yxColumnName : yxColumnNames) {

                Object o = stringObjectMap.get(yxColumnName);

                System.out.println(yxColumnName+"===="+o);
            }
        }
    }
}
