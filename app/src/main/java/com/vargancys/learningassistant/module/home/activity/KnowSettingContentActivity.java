package com.vargancys.learningassistant.module.home.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.module.home.view.BaseKnowUpdateView;
import com.vargancys.learningassistant.utils.ConstantsUtils;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/28
 * version:1.0
 */
public class KnowSettingContentActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initView() {

    }

    public static void launch(Activity activity, Long know_id) {
        Intent intent = new Intent(activity,KnowSettingContentActivity.class);
        intent.putExtra(ConstantsUtils.KNOW_ITEM_ID,know_id);
        activity.startActivity(intent);
    }
}
