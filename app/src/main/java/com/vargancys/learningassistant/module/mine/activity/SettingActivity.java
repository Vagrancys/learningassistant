package com.vargancys.learningassistant.module.mine.activity;

import android.app.Activity;
import android.content.Intent;

import com.vargancys.learningassistant.base.BaseActivity;

/**
 * @author Vagrancy
 * @date 2020/6/2
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 设置成功页面
 */
public class SettingActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initView() {
        //TODO 设置页面
    }

    public static void launch(Activity activity){
        Intent intent = new Intent(activity,SettingActivity.class);
        activity.startActivity(intent);
    }
}
