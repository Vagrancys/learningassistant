package com.vargancys.learningassistant.module.mine.activity;

import android.app.Activity;
import android.content.Intent;

import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.utils.ConstantsUtils;

/**
 * @author Vagrancy
 * @date 2020/6/1
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 个人中心问题各项页面
 */
public class ProblemItemActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initView() {
        //TODO 个人中心问题各项详情页面
    }

    public static void launch(Activity activity, int type) {
        Intent intent = new Intent(activity, ProblemItemActivity.class);
        intent.putExtra(ConstantsUtils.PROBLEM_ID, type);
        activity.startActivity(intent);
    }
}
