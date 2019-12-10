package com.hhgs.kks.NewAddData;

import com.hhgs.kks.SpringbootKksApplication;
import com.hhgs.kks.common.ExcelReaderUtil;
import com.hhgs.kks.common.ExcelWriterUtil;
import com.hhgs.kks.common.NaRiDataUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootKksApplication.class)//这里是启动类
@WebAppConfiguration
public class NewAddDataTest {

    @Autowired
    NaRiDataUtil naRiDataUtil;

    @Test
    public void naRiMapTest() throws IOException {
        Map<String, String> naRiData = naRiDataUtil.getNaRiData();
        List<String> row2List = new ArrayList<>();
        List<String[]> addNewList = new ArrayList<>();

        Set<String> keyset = naRiData.keySet();

        List<String[]> list = ExcelReaderUtil.readExcelServlet("C:\\Users\\hc\\Desktop\\原始测点.xlsx", 0, 0);

        for (String[] rows : list) {
            row2List.add(rows[1]);
            System.out.println(rows[1]);
        }

        for (String key : keyset) {
            System.out.println("=====================================================================================");
            if(!row2List.contains(key)) {
                System.out.println(key);
                String[] addNewRow = new String[2];
                String id = naRiData.get(key);
                addNewRow[0] = id;
                addNewRow[1] = key;
                addNewList.add(addNewRow);
            };
        }
        String[] fields = new String[]{"原始测点ID","原始测点名称"};

        ExcelWriterUtil.writerExcel(addNewList,fields,"C:\\Users\\hc\\Desktop\\新增测点.xlsx");
    }
}
