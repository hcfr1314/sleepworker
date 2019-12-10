package com.hhgs.kks.common;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ChangeNumber {
    public static void main(String[] args) throws ParseException {
        List<String[]> strings = ExcelReaderUtil.readExcelServlet("C:\\Users\\lenovo\\Desktop\\工作簿1.xlsx", 0, 0);
        for (String[] string : strings) {
//            System.out.println(string[0]);
            String time =string[0].split("=")[1];
            /*if(temp.indexOf(".")>0){
                String result=temp.substring(0,temp.lastIndexOf("."));
                System.out.println(result);
            }else{
                System.out.println(0);
            }*/

            SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date date = new Date();
            long t=Long.valueOf(String.valueOf(time));
            date.setTime(t);
            String beiJingTime = sft.format(date);
            System.out.println(beiJingTime);

        }

//        long t=1560873601464L;
//        Date date = new Date();

    }


}
