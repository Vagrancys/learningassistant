package com.vargancys.learningassistant.utils;

import android.content.Context;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/01
 * version:1.0
 */
public class DensityUtils {
    public static int dip2px(Context context,int deValue){
        float density= context.getResources().getDisplayMetrics().density;
        return (int) (density * deValue +0.5f);
    }
}
