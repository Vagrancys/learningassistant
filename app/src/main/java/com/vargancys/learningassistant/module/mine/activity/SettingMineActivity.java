package com.vargancys.learningassistant.module.mine.activity;

import android.app.Activity;
import android.content.Intent;

import com.vargancys.learningassistant.base.BaseActivity;

/**
 * @author Vagrancy
 * @date 2020/6/3
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 个人信息页面
 */
public class SettingMineActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initView() {

    }

    public static void launch(Activity activity){
        Intent intent = new Intent(activity,SettingMineActivity.class);
        activity.startActivity(intent);
    }
}
