package com.vargancys.learningassistant.module.common.member;

import android.app.Activity;
import android.content.Intent;

import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.module.common.view.LoginForgetView;

/**
 * @author Vagrancy
 * @date 2020/6/11
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 忘记密码页面
 */
public class LoginForgetActivity extends BaseActivity implements LoginForgetView {
    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initView() {
        //TODO 做到忘记密码
    }

    public static void launch(Activity activity){
        Intent intent = new Intent(activity,LoginForgetActivity.class);
        activity.startActivity(intent);
    }
}
