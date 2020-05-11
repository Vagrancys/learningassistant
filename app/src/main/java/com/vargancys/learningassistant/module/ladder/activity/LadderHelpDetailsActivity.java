package com.vargancys.learningassistant.module.ladder.activity;

import android.app.Activity;
import android.content.Intent;

import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.utils.ConstantsUtils;

/**
 * @author Vagrancy
 * @date 2020/5/11
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 帮助详情页
 */
public class LadderHelpDetailsActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initView() {
        //TODO 帮助详情页
    }

    public static void launch(Activity activity,long helpId){
        Intent intent = new Intent(activity,LadderHelpDetailsActivity.class);
        intent.putExtra(ConstantsUtils.LADDER_HELP_ID,helpId);
        activity.startActivity(intent);
    }
}
