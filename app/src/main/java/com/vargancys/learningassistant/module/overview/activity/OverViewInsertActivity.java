package com.vargancys.learningassistant.module.overview.activity;

import android.app.Activity;
import android.content.Intent;

import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.module.overview.view.OverViewInsertView;
import com.vargancys.learningassistant.module.overview.view.OverViewUpdateView;
import com.vargancys.learningassistant.utils.ConstantsUtils;

/**
 * @author Vagrancy
 * @date 2020/7/2
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识体系添加模块
 */
public class OverViewInsertActivity extends BaseActivity implements OverViewInsertView {
    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initView() {
        //TODO 知识体系添加页面
    }

    public static void launch(Activity activity,long createId,int code){
        Intent intent =new Intent(activity, OverViewInsertActivity.class);
        intent.putExtra(ConstantsUtils.OVERVIEW_ID,createId);
        activity.startActivityForResult(intent,code);
    }
}
