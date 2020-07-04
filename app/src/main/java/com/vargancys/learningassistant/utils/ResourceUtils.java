package com.vargancys.learningassistant.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * @author Vagrancy
 * @date 2020/6/4
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 资源工具类
 */
public class ResourceUtils {
    //返回字符串
    public static String getString(Context context,int id){
        return context.getResources().getString(id);
    }

    //返回颜色
    public static int getColor(Context context,int id){
        return context.getResources().getColor(id);
    }

    //返回字符串数组
    public static String[] getStringArray(Context context,int id){
        return context.getResources().getStringArray(id);
    }

    //返回drawable
    public static Drawable getDrawable(Context context,int drawable){
        return context.getResources().getDrawable(drawable);
    }

    //返回Dimension
    public static float getDimension(Context context,int dimension){
        return context.getResources().getDimension(dimension);
    }

    public static int getInt(Context context, int drawable) {
        return context.getResources().getInteger(drawable);
    }
}
