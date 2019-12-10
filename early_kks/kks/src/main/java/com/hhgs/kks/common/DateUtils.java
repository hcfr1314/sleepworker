package com.hhgs.kks.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static void main(String[] args) {
        SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Long L = 1560906000010L;
        Date date = new Date();
        date.setTime(L);
        String beiJingTime = sft.format(date);
        System.out.println(beiJingTime);

    }
}
