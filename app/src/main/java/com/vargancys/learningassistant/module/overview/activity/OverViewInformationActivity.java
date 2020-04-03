package com.vargancys.learningassistant.module.overview.activity;

import android.app.Activity;
import android.content.Intent;

import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.utils.ConstantsUtils;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/03
 * version:1.0
 */
public class OverViewInformationActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initView() {

    }

    public static void launch(Activity activity,long selectId){
        Intent intent = new Intent(activity,OverViewInformationActivity.class);
        intent.putExtra(ConstantsUtils.OVERVIEW_SELECTED_ID,selectId);
        activity.startActivity(intent);
    }
}
