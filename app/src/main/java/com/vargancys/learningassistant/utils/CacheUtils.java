package com.vargancys.learningassistant.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/01
 * version:1.0
 */
public class CacheUtils {
    public static void putBoolean(Context context,String key,boolean deValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(ConstantsUtils.SHARED_STATE,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key,deValue).commit();
    }

    public static boolean getBoolean(Context context,String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(ConstantsUtils.SHARED_STATE,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key,false);
    }

    public static void putLong(Context context,String key,long deValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(ConstantsUtils.SHARED_STATE,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key,deValue).commit();
    }

    public static Long getLong(Context context,String key,long deValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(ConstantsUtils.SHARED_STATE,Context.MODE_PRIVATE);
        return sharedPreferences.getLong(key,deValue);
    }
}
