package com.vargancys.learningassistant.module.common;

import android.app.Activity;
import android.content.Intent;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;

public class MainActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

    }

    public static void launch(Activity activity){
        Intent intent = new Intent(activity,MainActivity.class);
        activity.startActivity(intent);
    }
}
