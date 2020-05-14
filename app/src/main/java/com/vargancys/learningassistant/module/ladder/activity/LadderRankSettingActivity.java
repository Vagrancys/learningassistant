package com.vargancys.learningassistant.module.ladder.activity;

import android.app.Activity;
import android.content.Intent;

import com.vargancys.learningassistant.base.BaseActivity;

/**
 * @author Vagrancy
 * @date 2020/5/14
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 天梯排行配置中心
 */
public class LadderRankSettingActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initView() {
        //TODO 天梯配置页面
    }

    public static void launch(Activity activity){
        Intent intent = new Intent(activity,LadderRankSettingActivity.class);
        activity.startActivity(intent);
    }
}
