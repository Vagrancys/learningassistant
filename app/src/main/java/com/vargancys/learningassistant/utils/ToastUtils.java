package com.vargancys.learningassistant.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/02/29
 * version:1.0
 */
public class ToastUtils {
    public static void ToastText(Context context,String text){
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
    }

    public static void ToastText(Context context,int ids){
        Toast.makeText(context,ResourceUtils.getString(context,ids),
                Toast.LENGTH_SHORT).show();
    }
}
