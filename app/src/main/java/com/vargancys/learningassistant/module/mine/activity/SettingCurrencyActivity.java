package com.vargancys.learningassistant.module.mine.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Vagrancy
 * @date 2020/6/4
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 通用配置页面
 */
public class SettingCurrencyActivity extends BaseActivity {
    @BindView(R.id.common_title_data)
    TextView commonTitleData;
    @BindView(R.id.setting_checkbox)
    RadioButton settingCheckbox;

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting_currency;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initToolbar() {
        commonTitleData.setText(getResources().getString(R.string.setting_currency_toolbar));
        settingCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    ToastUtils.ToastText(getContext(),getResources().getString(R.string.setting_push_successful_text));
                }else{
                    ToastUtils.ToastText(getContext(),getResources().getString(R.string.setting_push_fail_text));
                }
            }
        });
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, SettingCurrencyActivity.class);
        activity.startActivity(intent);
    }

    @OnClick({R.id.common_back, R.id.ll_setting_recommend})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.common_back:
                finish();
                break;
            case R.id.ll_setting_recommend:
                ToastUtils.ToastText(getContext(),getResources().getString(R.string.setting_currency_recommend_text));
                break;
        }
    }
}
