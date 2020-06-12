package com.vargancys.learningassistant.module.common.member;

import android.app.Activity;
import android.content.Intent;
import android.widget.RelativeLayout;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.module.common.view.LoginForgetView;
import com.vargancys.learningassistant.presenter.common.BaseCommonPresenter;
import com.vargancys.learningassistant.utils.ResourceUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

/**
 * @author Vagrancy
 * @date 2020/6/11
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 忘记密码页面
 */
public class LoginForgetActivity extends BaseActivity implements LoginForgetView {
    private BaseCommonPresenter mPresenter;
    @Override
    public int getLayoutId() {
        return R.layout.activity_login_forget;
    }

    @Override
    public void initView() {
        //TODO 做到忘记密码
        mPresenter = new BaseCommonPresenter(this);
    }

    public static void launch(Activity activity){
        Intent intent = new Intent(activity,LoginForgetActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void loadCodeFinish() {

    }

    @Override
    public void loadCodeError(int error, String message) {
        ToastUtils.ToastText(getContext(),"Error ="+error+", Message ="+message);
    }

    @Override
    public void updatePasswordFinish() {
        ToastUtils.ToastText(getContext(), ResourceUtils.getString(getContext(),R.string.login_forget_update_successful_text));
        finish();
    }

    @Override
    public void updatePasswordError(int error, String message) {
        ToastUtils.ToastText(getContext(),"Error ="+error+", Message ="+message);
    }
}
