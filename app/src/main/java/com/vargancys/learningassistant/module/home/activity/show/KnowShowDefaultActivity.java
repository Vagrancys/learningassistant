package com.vargancys.learningassistant.module.home.activity.show;

import android.app.Activity;
import android.content.Intent;

import com.vargancys.learningassistant.base.BaseActivity;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/06
 * version:1.0
 */
public class KnowShowDefaultActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initView() {

    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, KnowShowDefaultActivity.class);
        activity.startActivity(intent);
    }
}
