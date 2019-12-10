package com.hhgs.kks.file;

import com.hhgs.kks.SpringbootKksApplication;
import com.hhgs.kks.common.ExcelReaderUtil;
import com.hhgs.kks.common.NaRiDataUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;


import java.util.List;
import java.util.Map;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootKksApplication.class)//这里是启动类
@WebAppConfiguration
public class ExcelReaderTest {


    @Autowired
    private NaRiDataUtil naRiDataUtil;


    @Test
    public void testExcel() {
        List<String[]> list = ExcelReaderUtil.readExcelServlet("C:\\Users\\hc\\Desktop\\原始测点.xlsx", 0, 0);

        Map<String, String> naRiData = naRiDataUtil.getNaRiData();
        String[] strings1 = list.get(0);
        for (String s : strings1) {
            System.out.println(s);
        }
        for (String[] strings : list) {

            for (String string : strings) {
                System.out.println(string);
            }
            System.out.println("===============");

        }
    }
}
