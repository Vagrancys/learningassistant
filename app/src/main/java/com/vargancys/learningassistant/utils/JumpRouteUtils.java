package com.vargancys.learningassistant.utils;

import android.app.Activity;
import android.content.Intent;

import java.util.ArrayList;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/06
 * version:1.0
 */
public class JumpRouteUtils {
    private ArrayList<Activity> activities;
    private static JumpRouteUtils jumpRouteUtils;

    private JumpRouteUtils(){
        activities = new ArrayList<>();
    }

    public JumpRouteUtils getJumpRouteUtils() {
        if(jumpRouteUtils == null){
            synchronized (JumpRouteUtils.this){
                if(jumpRouteUtils == null){
                    jumpRouteUtils = new JumpRouteUtils();
                }
            }
        }
        return jumpRouteUtils;
    }

    public void JumpActivity(Activity activity,String activityTitle){
        Class clazz;
        try {
            clazz = Class.forName("com.vargancys.learningassistant.module.home.activity.demonstrate."+activityTitle);
            Intent intent = new Intent(activity,clazz.getClass());
            activity.startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
