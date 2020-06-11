package com.vargancys.learningassistant.module.common.member;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.module.common.MainActivity;
import com.vargancys.learningassistant.module.common.view.LoginView;
import com.vargancys.learningassistant.presenter.common.BaseCommonPresenter;
import com.vargancys.learningassistant.utils.CacheUtils;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ResourceUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * @author Vagrancy
 * @date 2020/6/10
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 登录界面
 */
public class LoginActivity extends BaseActivity implements LoginView {
    @BindView(R.id.common_back)
    ImageView commonBack;
    @BindView(R.id.common_title_data)
    TextView commonTitleData;
    @BindView(R.id.login_logo)
    ImageView loginLogo;
    @BindView(R.id.login_name_edit)
    EditText loginNameEdit;
    @BindView(R.id.login_name_close)
    ImageView loginNameClose;
    @BindView(R.id.login_password_edit)
    EditText loginPasswordEdit;
    @BindView(R.id.login_password_close)
    ImageView loginPasswordClose;

    private BaseCommonPresenter mPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        loginLogo.setImageResource(ResourceUtils.getInt(getContext(), R.drawable.login_logo_normal));
        mPresenter = new BaseCommonPresenter(this);
        loginNameEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(loginNameEdit.getText().length() > 0){
                    loginNameClose.setVisibility(View.VISIBLE);
                }else{
                    loginNameClose.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        loginPasswordEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(loginPasswordEdit.getText().length() > 0){
                    loginPasswordClose.setVisibility(View.VISIBLE);
                }else{
                    loginPasswordClose.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void initToolbar() {
        commonBack.setVisibility(View.GONE);
        commonTitleData.setText(ResourceUtils.getString(getContext(), R.string.login_toolbar));
    }

    @OnClick({R.id.login_forget_password, R.id.login_submit,R.id.login_name_close, R.id.login_password_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_forget_password:
                LoginForgetActivity.launch(this);
                break;
            case R.id.login_submit:
                submitLogin();
                break;
            case R.id.login_name_close:
                loginNameClose.setVisibility(View.GONE);
                loginNameEdit.setText("");
                break;
            case R.id.login_password_close:
                loginPasswordClose.setVisibility(View.GONE);
                loginPasswordEdit.setText("");
                break;
        }
    }

    private void submitLogin() {
        if (loginNameEdit.getText().toString().isEmpty()) {
            ToastUtils.ToastText(getContext(), ResourceUtils.getString(getContext(), R.string.login_name_empty_text));
            return;
        }
        if (loginPasswordEdit.getText().toString().isEmpty()) {
            ToastUtils.ToastText(getContext(), ResourceUtils.getString(getContext(), R.string.login_password_empty_text));
            return;
        }
        String name = loginNameEdit.getText().toString();
        String password = loginPasswordEdit.getText().toString();
        if (!mPresenter.isExistName(name)) {
            ToastUtils.ToastText(getContext(), ResourceUtils.getString(getContext(), R.string.login_name_exist_text));
            return;
        }
        mPresenter.loginName(name, password);
    }

    @Override
    public void requestLoginFinish(String message) {
        ToastUtils.ToastText(getContext(), message);
        CacheUtils.putBoolean(getContext(), ConstantsUtils.LOGIN_STATE, true);
        MainActivity.launch(this);
        finish();
    }

    @Override
    public void requestLoginError(int error, String message) {
        ToastUtils.ToastText(getContext(), "Error = " + error + ", Message =" + message);
        loginNameEdit.setText("");
        loginPasswordEdit.setText("");
    }

    public static void launch(Activity activity){
        Intent intent = new Intent(activity,LoginActivity.class);
        activity.startActivity(intent);
    }
}
