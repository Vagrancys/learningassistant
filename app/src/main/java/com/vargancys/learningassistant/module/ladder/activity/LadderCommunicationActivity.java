package com.vargancys.learningassistant.module.ladder.activity;

import android.app.Activity;
import android.content.Intent;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;

/**
 * @author Vagrancy
 * @date 2020/5/7
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 天梯交流中心
 */
public class LadderCommunicationActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_ladder_communication;
    }

    @Override
    public void initView() {

    }

    public static void launch(Activity activity){
        Intent intent = new Intent(activity, LadderCommunicationActivity.class);
        activity.startActivity(intent);
    }
}
