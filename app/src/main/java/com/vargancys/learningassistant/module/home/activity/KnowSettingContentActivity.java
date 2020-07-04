package com.vargancys.learningassistant.module.home.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.utils.ConstantsUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/28
 * version:1.0
 * 知识配置中心页面
 */
public class KnowSettingContentActivity extends BaseActivity {
    @BindView(R.id.common_back)
    ImageView commonBack;
    @BindView(R.id.common_title_data)
    TextView commonTitleData;

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting_content;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initToolbar() {
        commonTitleData.setText(getText(R.string.common_setting_content));
    }

    public static void launch(Activity activity, Long know_id) {
        Intent intent = new Intent(activity, KnowSettingContentActivity.class);
        intent.putExtra(ConstantsUtils.KNOW_ITEM_ID, know_id);
        activity.startActivity(intent);
    }

    @OnClick({R.id.common_back})
    public void onViewClicked(View itemView){
        finish();
    }
}
