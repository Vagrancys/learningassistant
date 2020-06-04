package com.vargancys.learningassistant.module.mine.activity;

import android.app.Activity;
import android.content.Intent;

import com.vargancys.learningassistant.base.BaseActivity;

/**
 * @author Vagrancy
 * @date 2020/6/4
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 意见反馈页面
 */
public class SettingFeedbackActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initView() {
        //TODO 做到意见反馈这里
    }

    public static void launch(Activity activity){
        Intent intent = new Intent(activity,SettingFeedbackActivity.class);
        activity.startActivity(intent);
    }
}
