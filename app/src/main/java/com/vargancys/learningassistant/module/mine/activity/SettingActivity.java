package com.vargancys.learningassistant.module.mine.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.module.common.member.LoginActivity;
import com.vargancys.learningassistant.utils.CacheUtils;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ResourceUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Vagrancy
 * @date 2020/6/2
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 设置成功页面
 */
public class SettingActivity extends BaseActivity {
    @BindView(R.id.common_back)
    ImageView commonBack;
    @BindView(R.id.common_title_data)
    TextView commonTitleData;

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initToolbar() {

        commonTitleData.setText(getText(R.string.setting_toolbar));
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, SettingActivity.class);
        activity.startActivity(intent);
    }

    @OnClick({R.id.ll_setting_mine, R.id.ll_setting_exit,R.id.ll_setting_currency,R.id.common_back,
            R.id.ll_setting_score, R.id.ll_setting_about, R.id.ll_setting_protocol, R.id.ll_setting_feedback})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_setting_mine:
                SettingMineActivity.launch(this);
                break;
            case R.id.common_back:
            case R.id.ll_setting_exit:
                CacheUtils.putBoolean(getContext(), ConstantsUtils.LOGIN_STATE, false);
                LoginActivity.launch(this);
                finish();
                break;
            case R.id.ll_setting_currency:
                SettingCurrencyActivity.launch(this);
                break;
            case R.id.ll_setting_score:
                jumpScore();
                break;
            case R.id.ll_setting_about:
                SettingAboutActivity.launch(this);
                break;
            case R.id.ll_setting_protocol:
                SettingProtocolActivity.launch(this);
                break;
            case R.id.ll_setting_feedback:
                SettingFeedbackActivity.launch(this);
                break;
        }
    }

    //跳转评分页面
    public void jumpScore(){
        try {
            Uri uri = Uri.parse("market://details?id="+getPackageName());
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }catch (Exception e){
            ToastUtils.ToastText(getContext(),R.string.setting_score_fail_text);
            e.printStackTrace();
        }
    }
}
