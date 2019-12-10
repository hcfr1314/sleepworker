package com.hhgs.kks.common;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class ExcelWriterUtil {

    /**
     * 根据路径写出文件到本地
     * @param list
     * @param fields
     * @param outPath
     */
    public static void writerExcel(List<String[]> list, String[] fields, String outPath) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("原始测点信息");
        //标题行
        Row titleRow = sheet.createRow(0);
        for (int i = 0; i < fields.length; i++) {
            Cell cell = titleRow.createCell(i);
            String field = fields[i];
            cell.setCellValue(field);
        }
        //设置数据列
        for (int i = 1; i < list.size(); i++) {
            String[] data = list.get(i);
            //数据行
            Row dataRow = sheet.createRow(i);
            if (data != null) {
                for (int j = 0; j < data.length; j++) {
                    String value = data[j];
                    Cell cell = dataRow.createCell(j);
                    cell.setCellValue(value);
                }
            }
        }
        //设置头信息
        OutputStream os = null;
       // try {
            //根据路径创建输出流
            //如果是在controller里可以使用reponse.getOutputStream()方法获取输出流
            os = new FileOutputStream(outPath);
            workbook.write(os);
      /*  } catch (Exception e) {
            System.out.println("导出出错");
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                workbook.close();
            } catch (Exception e) {
                System.out.println("workbook 流关闭失败");
            }
        }*/

        workbook.close();
    }
}
