package com.vargancys.learningassistant.module.common;

import android.app.Activity;
import android.content.Intent;

import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.utils.ConstantsUtils;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/07
 * version:1.0
 */
public class HelpSummaryActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initView() {

    }

    public static void launch(Activity activity,int position){
        Intent intent = new Intent(activity,HelpSummaryActivity.class);
        intent.putExtra(ConstantsUtils.HELP_SUMMARY_ID,position);
        activity.startActivity(intent);
    }
}
