package com.vargancys.learningassistant.module.home.activity;

import android.app.Activity;
import android.content.Intent;

import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.utils.ConstantsUtils;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/06
 * version:1.0
 */
public class KnowShowFirstActivity extends BaseActivity {


    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initView() {

    }
    public static void launch(Activity activity, int item_id) {
        Intent intent = new Intent(activity, KnowShowFirstActivity.class);
        intent.putExtra(ConstantsUtils.ITEM_ID,item_id);
        activity.startActivity(intent);
    }
}
