package com.vargancys.learningassistant.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/07
 * version:1.0
 */
public class TimeUtils {
    public static String getTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        String time = simpleDateFormat.format(new Date());
        return time;
    }
}
