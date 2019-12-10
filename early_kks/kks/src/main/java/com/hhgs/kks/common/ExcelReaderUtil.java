package com.hhgs.kks.common;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormatter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelReaderUtil {

    public static List<String[]> readExcelServlet(String url,int index,int start) {

        //excel文件路径
        String excelPath = url;
        List<String[]> result=new ArrayList<>();
        try {

            File excel = new File(excelPath);
            if (excel.isFile() && excel.exists()) {   //判断文件是否存在

                String[] split = excel.getName().split("\\.");  //.是特殊字符，需要转义！！！！！
                Workbook wb=null;
                //根据文件后缀（xls/xlsx）进行判断
                if ("xls".equals(split[1])) {
                    FileInputStream fis = new FileInputStream(excel);   //文件流对象
                    wb = new HSSFWorkbook(fis);
                } else if ("xlsx".equals(split[1])) {
                    wb = new XSSFWorkbook(excel);
                } else {
                    System.out.println("文件类型错误!");
                }
                //开始解析
                Sheet sheet = wb.getSheetAt(index);     //读取sheet 0

                int firstRowIndex = start;//sheet.getFirstRowNum() + 1;   //第一行是列名，所以不读
                int lastRowIndex = sheet.getLastRowNum();
                for (int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {   //遍历行
                    Row row = sheet.getRow(rIndex);

                    if (row != null) {
                        List<String> temp=new ArrayList<>();
                        int firstCellIndex = row.getFirstCellNum();
                        int lastCellIndex = row.getLastCellNum();
                        for (int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++) {   //遍历列
                            Cell cell = row.getCell(cIndex);
                            if (cell != null) {
                                if(cell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
                                    HSSFDataFormatter dataFormatter = new HSSFDataFormatter();
                                    String cellFormatted = dataFormatter.formatCellValue(cell);
                                    temp.add(cellFormatted);
                                }else {
                                    temp.add(cell.toString());
                                }
                            }else{
                                temp.add(null);
                            }
                        }
                        result.add(temp.toArray(new String[temp.size()]));
                    }
                }
            } else {
                System.out.println(excelPath);
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
