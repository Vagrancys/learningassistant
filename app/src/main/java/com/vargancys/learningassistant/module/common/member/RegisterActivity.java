package com.vargancys.learningassistant.module.common.member;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.module.common.view.RegisterView;
import com.vargancys.learningassistant.presenter.common.BaseCommonPresenter;
import com.vargancys.learningassistant.utils.ResourceUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Vagrancy
 * @date 2020/6/13
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 注册页面
 */
public class RegisterActivity extends BaseActivity implements RegisterView {
    @BindView(R.id.common_back)
    ImageView commonBack;
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.common_img_one)
    ImageView commonImgOne;
    @BindView(R.id.common_img)
    ImageView commonImg;
    @BindView(R.id.register_logo)
    ImageView registerLogo;
    @BindView(R.id.register_name_edit)
    EditText registerNameEdit;
    @BindView(R.id.register_name_close)
    ImageView registerNameClose;
    @BindView(R.id.register_password_edit)
    EditText registerPasswordEdit;
    @BindView(R.id.register_password_close)
    ImageView registerPasswordClose;
    @BindView(R.id.register_submit)
    TextView registerSubmit;
    private BaseCommonPresenter mPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {
        mPresenter = new BaseCommonPresenter(this);
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, RegisterActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void saveNameFinish() {
        ToastUtils.ToastText(getContext(), ResourceUtils.getString(getContext(), R.string.register_save_win_text));
        LoginActivity.launch(this);
        finish();
    }

    @Override
    public void saveNameError(int error, String message) {
        ToastUtils.ToastText(getContext(), "Error =" + error + ",Message =" + message);
    }

    @OnClick({R.id.common_back, R.id.common_img, R.id.register_name_close, R.id.register_password_close, R.id.register_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.common_back:
                finish();
                break;
            case R.id.common_img:
                LoginActivity.launch(this);
                finish();
                break;
            case R.id.register_name_close:
                registerNameEdit.setText("");
                registerNameClose.setVisibility(View.GONE);
                break;
            case R.id.register_password_close:
                registerPasswordClose.setVisibility(View.GONE);
                registerPasswordEdit.setText("");
                break;
            case R.id.register_submit:
                isName();
                break;
        }
    }

    private void isName(){
        String name = registerNameEdit.getText().toString();
        String password = registerPasswordEdit.getText().toString();
        if(name.isEmpty()){
            ToastUtils.ToastText(getContext(),ResourceUtils.getString(getContext(),R.string.register_name_empty_text));
            return;
        }
        if(password.isEmpty()){
            ToastUtils.ToastText(getContext(),ResourceUtils.getString(getContext(),R.string.register_password_empty_text));
            return;
        }

        mPresenter.saveMember(name,password);
    }
}
