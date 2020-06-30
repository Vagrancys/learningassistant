package com.vargancys.learningassistant.module.overview.activity;

import android.app.Activity;
import android.content.Intent;

import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.module.overview.view.OverViewCreateView;


/**
 * @author Vagrancy
 * @date 2020/6/30
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 个人知识体系管理中心
 */
public class OverViewCreateActivity extends BaseActivity implements OverViewCreateView {
    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initView() {
        //TODO 个人知识体系中心管理
    }

    public static void launch(Activity activity){
        Intent intent = new Intent(activity,OverViewCreateActivity.class);
        activity.startActivity(intent);
    }
}
