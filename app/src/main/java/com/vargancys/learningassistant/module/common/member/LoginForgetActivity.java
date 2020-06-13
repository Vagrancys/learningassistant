package com.vargancys.learningassistant.module.common.member;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.module.common.view.LoginForgetView;
import com.vargancys.learningassistant.presenter.common.BaseCommonPresenter;
import com.vargancys.learningassistant.utils.ResourceUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Vagrancy
 * @date 2020/6/11
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 忘记密码页面
 */
public class LoginForgetActivity extends BaseActivity implements LoginForgetView {
    @BindView(R.id.common_back)
    ImageView commonBack;
    @BindView(R.id.common_title_data)
    TextView commonTitleData;
    @BindView(R.id.login_name_edit)
    EditText loginNameEdit;
    @BindView(R.id.login_name_close)
    ImageView loginNameClose;
    @BindView(R.id.login_code_edit)
    EditText loginCodeEdit;
    @BindView(R.id.login_code_time)
    TextView loginCodeTime;
    @BindView(R.id.login_forget_submit)
    TextView loginForgetSubmit;
    @BindView(R.id.ll_submit)
    LinearLayout llSubmit;
    @BindView(R.id.login_name_update_edit)
    EditText loginNameUpdateEdit;
    @BindView(R.id.login_name_update_close)
    ImageView loginNameUpdateClose;
    @BindView(R.id.login_password_update_edit)
    EditText loginPasswordUpdateEdit;
    @BindView(R.id.login_password_update_close)
    ImageView loginPasswordUpdateClose;
    @BindView(R.id.login_submit_update)
    TextView loginSubmitUpdate;
    @BindView(R.id.ll_update)
    LinearLayout llUpdate;
    private BaseCommonPresenter mPresenter;
    private CountDownTimer mTimer;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login_forget;
    }

    @Override
    public void initView() {
        //TODO 做到忘记密码
        mPresenter = new BaseCommonPresenter(this);
        mTimer = new CountDownTimer(60000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                loginCodeTime.setText(millisUntilFinished/1000+"秒");
            }

            @Override
            public void onFinish() {
                loginCodeTime.setText(ResourceUtils.getString(getContext(),R.string.login_forget_time_text));
                loginCodeTime.setClickable(true);
                loginCodeTime.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.login_forget_time_shape));
            }
        };

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

        loginNameClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginNameClose.setVisibility(View.GONE);
                loginNameEdit.setText("");
            }
        });

        loginNameUpdateEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(loginNameUpdateEdit.getText().length() > 0){
                    loginNameUpdateClose.setVisibility(View.VISIBLE);
                }else{
                    loginNameUpdateClose.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        loginNameUpdateClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginNameUpdateClose.setVisibility(View.GONE);
                loginNameUpdateEdit.setText("");
            }
        });

        loginPasswordUpdateEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(loginPasswordUpdateEdit.getText().length() > 0){
                    loginPasswordUpdateClose.setVisibility(View.VISIBLE);
                }else{
                    loginPasswordUpdateClose.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        loginPasswordUpdateClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPasswordUpdateClose.setVisibility(View.GONE);
                loginPasswordUpdateEdit.setText("");
            }
        });
    }

    @Override
    public void initToolbar() {
        commonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        commonTitleData.setText(ResourceUtils.getString(getContext(), R.string.login_forget_toolbar));
    }


    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, LoginForgetActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void loadCodeFinish() {

    }

    @Override
    public void loadCodeError(int error, String message) {
        ToastUtils.ToastText(getContext(), "Error =" + error + ", Message =" + message);
    }

    @Override
    public void updatePasswordFinish() {
        ToastUtils.ToastText(getContext(), ResourceUtils.getString(getContext(), R.string.login_forget_update_successful_text));
        finish();
    }

    @Override
    public void updatePasswordError(int error, String message) {
        ToastUtils.ToastText(getContext(), "Error =" + error + ", Message =" + message);
    }

    @OnClick({R.id.login_code_time,R.id.login_forget_submit,R.id.login_submit_update})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.login_code_time:
                loginCodeTime.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.login_forget_gray_time_shape));
                loginCodeTime.setClickable(false);
                mPresenter.loadCodeName();
                mTimer.start();
                break;
            case R.id.login_forget_submit:
                isName();
                break;
            case R.id.login_submit_update:
                isUpdateName();
                break;
        }
    }

    private void isUpdateName() {
        String name = loginNameUpdateEdit.getText().toString();
        String password = loginPasswordUpdateEdit.getText().toString();
        if(name.isEmpty()){
            ToastUtils.ToastText(getContext(),ResourceUtils.getString(getContext(),R.string.login_forget_update_empty_text));
            return;
        }
        if(password.isEmpty()){
            ToastUtils.ToastText(getContext(),ResourceUtils.getString(getContext(),R.string.login_forget_update_password_empty_text));
            return;
        }

        if(!name.equals(password)){
            ToastUtils.ToastText(getContext(),ResourceUtils.getString(getContext(),R.string.login_forget_different_text));
            return;
        }
        mPresenter.updatePasswordData(name);
    }

    private void isName() {
        String name = loginNameEdit.getText().toString();
        if(name.isEmpty()){
           ToastUtils.ToastText(getContext(),ResourceUtils.getString(getContext(),R.string.login_forget_empty_text));
            return;
        }
        if(mPresenter.isCodeSuccessful(loginCodeEdit.getText().toString())){
            launchLayout();
        }else{
            ToastUtils.ToastText(getContext(),ResourceUtils.getString(getContext(),R.string.login_forget_code_fail_text));
            return;
        }
    }

    private void launchLayout() {
        if(mTimer != null){
            mTimer.cancel();
        }
        llSubmit.setVisibility(View.GONE);
        llUpdate.setVisibility(View.VISIBLE);
    }
}
