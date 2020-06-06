package com.vargancys.learningassistant.module.mine.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.utils.ResourceUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Vagrancy
 * @date 2020/6/4
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 隐私页面
 */
public class SettingProtocolActivity extends BaseActivity {
    @BindView(R.id.common_title_data)
    TextView commonTitleData;

    @Override
    public int getLayoutId() {
        return R.layout.acitivity_setting_protocol;
    }

    @Override
    public void initView() {
        //TODO 到隐私这里
    }

    @Override
    public void initToolbar() {
        commonTitleData.setText(ResourceUtils.getString(getContext(),R.string.setting_protocol_toolbar));
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, SettingProtocolActivity.class);
        activity.startActivity(intent);
    }

    @OnClick({R.id.common_back, R.id.ll_setting_permission, R.id.ll_setting_privacy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.common_back:
                finish();
                break;
            case R.id.ll_setting_permission:
                ToastUtils.ToastText(getContext(), ResourceUtils.getString(getContext(),R.string.setting_permission_hint_text));
                break;
            case R.id.ll_setting_privacy:
                ToastUtils.ToastText(getContext(),ResourceUtils.getString(getContext(),R.string.setting_privacy_hint_text));
                break;
        }
    }
}
