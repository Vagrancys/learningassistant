package com.vargancys.learningassistant.module.mine.activity;

import android.app.Activity;
import android.content.Intent;

import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.utils.ConstantsUtils;

/**
 * @author Vagrancy
 * @date 2020/5/26
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 个人中心天梯挑战各项页面
 */
public class ChallengeItemActivity extends BaseActivity {
    private int type = 1;
    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initView() {
        type = getIntent().getIntExtra(ConstantsUtils.KNOW_TYPE_ID,0);
        //TODO 未制作天梯各项知识
    }

    public static void launch(Activity activity,int type){
        Intent intent = new Intent(activity,ChallengeItemActivity.class);
        intent.putExtra(ConstantsUtils.KNOW_TYPE_ID,type);
        activity.startActivity(intent);
    }
}
