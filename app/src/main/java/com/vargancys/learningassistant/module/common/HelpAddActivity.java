package com.vargancys.learningassistant.module.common;

import android.app.Activity;
import android.content.Intent;

import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.module.common.view.HelpAddView;
import com.vargancys.learningassistant.persenter.common.HelpAddPresenter;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/07
 * version:1.0
 * 帮助添加页
 */
public class HelpAddActivity extends BaseActivity{
    private HelpAddView helpAddView;
    private HelpAddPresenter helpAddPresenter;

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initView() {
        helpAddView = new HelpAddView() {
            @Override
            public void saveFinish() {

            }
        };

        helpAddPresenter = new HelpAddPresenter(helpAddView);
    }

    public static void launch(Activity activity){
        Intent intent = new Intent(activity,HelpContentActivity.class);
        activity.startActivity(intent);
    }

}
