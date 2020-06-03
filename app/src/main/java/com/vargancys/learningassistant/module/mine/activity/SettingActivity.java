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
    @BindView(R.id.ll_setting_mine)
    RelativeLayout llSettingMine;
    @BindView(R.id.ll_setting_exit)
    RelativeLayout llSettingExit;

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView() {
        //TODO 设置页面
    }

    @Override
    public void initToolbar() {
        commonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        commonTitleData.setText(getResources().getString(R.string.setting_toolbar));
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, SettingActivity.class);
        activity.startActivity(intent);
    }

    @OnClick({R.id.ll_setting_mine, R.id.ll_setting_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_setting_mine:
                SettingMineActivity.launch(this);
                break;
            case R.id.ll_setting_exit:
                //TODO 跳转到登录界面
                finish();
                break;
        }
    }
}
