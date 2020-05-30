package com.vargancys.learningassistant.module.mine.activity;

import android.app.Activity;
import android.content.Intent;

import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.utils.ConstantsUtils;

/**
 * @author Vagrancy
 * @date 2020/5/30
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 个人中心等级详情页面
 */
public class LevelDetailsActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initView() {
        //TODO 做到个人中心等级详情页面

    }

    public static void launch(Activity activity, long id) {
        Intent intent = new Intent(activity,LevelDetailsActivity.class);
        intent.putExtra(ConstantsUtils.CHALLENGE_DETAILS_ID, id);
        activity.startActivity(intent);
    }
}
