package com.hhgs.kks.readerandwriter;

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
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootKksApplication.class)//这里是启动类
@WebAppConfiguration
public class readerToWriterTest {

    @Autowired
    NaRiDataUtil naRiDataUtil;

    @Test
    public void fromReaderToWriterTest() throws IOException {

        Map<String, String> naRiDataMap = naRiDataUtil.getNaRiData();
        //源文件路径
        String inputPath = "C:\\Users\\hc\\Desktop\\原始测点.xlsx";
        List<String[]> fileList = ExcelReaderUtil.readExcelServlet(inputPath, 0, 0);

        for (String[] rows : fileList) {
            String id = naRiDataMap.get(rows[1]);
            if(id == null || id == "") {
                rows[0] = "unknown";
            }else {
                rows[0] = id;
            }
        }

        //目标文件路径
        String outputPath = "C:\\Users\\hc\\Desktop\\测点id匹配结果.xlsx";
        //设置列名称
        String[] fields = new String[]{"原始测点ID","原始测点名称","设备编码","设备名称","逻辑设备路径","BCS标准属性英文名称","BCS标准属性中文名称","属性分类","属性类别","是否必选(M/O)","测点对标状态","测点全局编号","REMARK"};
        ExcelWriterUtil.writerExcel(fileList,fields,outputPath);
    }

}
