package com.hhgs.kks.file;


import com.hhgs.kks.common.ExcelWriterUtil;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PrintStreamTest {



    @Test
    public void printStreamTest() throws IOException {
        //列名
        String[] fields = new String[]{"姓名","性别","eanglishname"};

        List<String[]> fileList = new ArrayList<>();
        String[] strings1 = new String[]{"周杰伦","男","zhoujielun"};
        String[] strings2 = new String[]{"王力宏","男","wanglihong"};
        String[] strings3 = new String[]{"林俊杰","男","linjunjie"};
        String[] strings4 = new String[]{"陈奕迅","男","chenyixun"};
        fileList.add(strings1);
        fileList.add(strings2);
        fileList.add(strings3);
        fileList.add(strings4);

        ExcelWriterUtil.writerExcel(fileList,fields,"F:\\test.xlsx");
    }


}
