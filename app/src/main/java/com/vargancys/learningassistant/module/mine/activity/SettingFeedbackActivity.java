package com.vargancys.learningassistant.module.mine.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.module.mine.view.SettingFeedbackView;
import com.vargancys.learningassistant.presenter.mine.BaseMinePresenter;
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
 * Description: 意见反馈页面
 */
public class SettingFeedbackActivity extends BaseActivity implements SettingFeedbackView {
    @BindView(R.id.common_back)
    ImageView commonBack;
    @BindView(R.id.common_title_data)
    TextView commonTitleData;
    @BindView(R.id.feedback_edit)
    EditText feedbackEdit;
    private BaseMinePresenter mPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting_feedback;
    }

    @Override
    public void initView() {
        mPresenter = new BaseMinePresenter(this);
    }

    @Override
    public void initToolbar() {
        commonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        commonTitleData.setText(ResourceUtils.getString(getContext(), R.string.setting_feedback_toolbar));
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, SettingFeedbackActivity.class);
        activity.startActivity(intent);
    }

    @OnClick(R.id.feedback_submit)
    public void onViewClicked() {
        if(feedbackEdit.getText().toString().isEmpty()){
            ToastUtils.ToastText(getContext(),ResourceUtils.getString(getContext(),R.string.feedback_edit_empty));
            return;
        }
        mPresenter.saveFeedbackData(feedbackEdit.getText().toString());
    }

    @Override
    public void saveFeedbackError(int error, String message) {
        ToastUtils.ToastText(getContext(),"Error ="+error +",Message ="+message);
    }

    @Override
    public void saveFeedbackFinish() {
        ToastUtils.ToastText(getContext(),ResourceUtils.getString(getContext(),R.string.feedback_successful_text));
    }
}
