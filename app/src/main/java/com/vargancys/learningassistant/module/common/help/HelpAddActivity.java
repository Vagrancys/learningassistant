package com.vargancys.learningassistant.module.common.help;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.module.common.view.HelpAddView;
import com.vargancys.learningassistant.persenter.common.help.HelpAddPresenter;
import com.vargancys.learningassistant.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/07
 * version:1.0
 * 帮助添加页
 */
public class HelpAddActivity extends BaseActivity implements HelpAddView {
    @BindView(R.id.common_back)
    ImageView commonBack;
    @BindView(R.id.common_img)
    ImageView commonImg;
    @BindView(R.id.help_title_edit)
    EditText helpTitleEdit;
    @BindView(R.id.help_summary_edit)
    EditText helpSummaryEdit;
    private HelpAddPresenter helpAddPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_help_add;
    }

    @Override
    public void initView() {
        helpAddPresenter = new HelpAddPresenter(this);
        initListener();
    }

    private void initListener() {
        commonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        commonImg.setBackgroundResource(R.drawable.commend_complete_selector);
        commonImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helpAddPresenter.saveHelpData(helpTitleEdit.getText().toString(),
                        helpSummaryEdit.getText().toString());
            }
        });
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, HelpContentActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void addFinish() {
        ToastUtils.ToastText(getContext(), "添加帮助成功了哦!");
        resetView();
    }

    @Override
    public void addError(int error, String msg) {
        ToastUtils.ToastText(getContext(), "添加帮助失败了哦!");
        resetView();
    }

    private void resetView() {
        helpTitleEdit.setText("");
        helpSummaryEdit.setText("");
    }
}
