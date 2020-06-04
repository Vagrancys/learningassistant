package com.vargancys.learningassistant.module.mine.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Vagrancy
 * @date 2020/6/4
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 关于我页面
 */
public class SettingAboutActivity extends BaseActivity {
    @BindView(R.id.common_back)
    ImageView commonBack;
    @BindView(R.id.common_title_data)
    TextView commonTitleData;
    @BindView(R.id.about_icon)
    ImageView aboutIcon;
    @BindView(R.id.about_version)
    TextView aboutVersion;
    @BindView(R.id.about_me)
    TextView aboutMe;
    @BindView(R.id.about_blog)
    TextView aboutBlog;
    @BindView(R.id.about_email)
    TextView aboutEmail;

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting_about;
    }

    @Override
    public void initView() {
        String[] mInformation = getResources().getStringArray(R.array.about_author_information);
        aboutMe.setText(mInformation[0]);
        aboutBlog.setText(mInformation[1]);
        aboutEmail.setText(mInformation[2]);
        aboutIcon.setImageResource(R.drawable.setting_icon_normal);
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(),0);
            aboutVersion.setText(info.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initToolbar() {
        commonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        commonTitleData.setText(getResources().getString(R.string.setting_about_toolbar));
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, SettingAboutActivity.class);
        activity.startActivity(intent);
    }

    @OnClick(R.id.about_update)
    public void onViewClicked(View itemView) {
        switch (itemView.getId()){
            case R.id.about_update:
                ToastUtils.ToastText(getContext(),getResources().getString(R.string.setting_about_update_text));
                break;
            case R.id.about_explain:
                ToastUtils.ToastText(getContext(),getResources().getString(R.string.setting_about_explain_text));
                break;
        }
    }
}
