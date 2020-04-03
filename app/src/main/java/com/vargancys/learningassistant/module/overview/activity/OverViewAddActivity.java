package com.vargancys.learningassistant.module.overview.activity;

import android.app.Activity;
import android.content.Intent;

import com.vargancys.learningassistant.base.BaseActivity;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/03
 * version:1.0
 */
public class OverViewAddActivity extends BaseActivity {

    public static void launch(Activity activity, int request_code) {
        Intent intent = new Intent(activity,OverViewAddActivity.class);
        activity.startActivityForResult(intent,request_code);
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initView() {

    }
}
