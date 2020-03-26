package com.vargancys.learningassistant.module.home.activity;

import android.app.Activity;
import android.content.Intent;

import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.utils.ConstantsUtils;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/26
 * version:1.0
 */
public class KnowHistoryDataActivity extends BaseActivity {
    @Override
    public void initView() {

    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    public static void launch(Activity activity,long know_id){
        Intent intent = new Intent(activity,KnowHistoryDataActivity.class);
        intent.putExtra(ConstantsUtils.KNOW_ITEM_ID,know_id);
        activity.startActivity(intent);
    }
}
