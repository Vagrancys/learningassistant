package com.vargancys.learningassistant.module.home.activity.show;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.model.home.bean.AidedBean;
import com.vargancys.learningassistant.module.home.activity.data.DataAidedActivity;
import com.vargancys.learningassistant.presenter.home.AidedPresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/06
 * version:1.0
 * 知识展示三级页面
 */
public class ShowAidedActivity extends BaseActivity{
    private static final String TAG = "ShowAidedActivity";

    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.aided_show_directory)
    TextView aidedShowDirectory;
    @BindView(R.id.aided_show_explain)
    TextView aidedShowExplain;
    @BindView(R.id.aided_show_deep_explain)
    TextView aidedShowDeepExplain;
    @BindView(R.id.aided_show_case)
    TextView aidedShowCase;
    @BindView(R.id.aided_show_experience)
    TextView aidedShowExperience;
    @BindView(R.id.aided_show_advanced)
    TextView aidedShowAdvanced;
    @BindView(R.id.aided_show_publicize)
    TextView aidedShowPublicize;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.include_know_empty)
    LinearLayout includeKnowEmpty;
    private AidedPresenter mPresenter;
    private int article_id;
    private static int REQUEST_CODE = 2001;

    @Override
    public int getLayoutId() {
        return R.layout.activity_knowledge_show_aided;
    }

    @Override
    public void initView() {
        if (getIntent() != null) {
            article_id = getIntent().getIntExtra(ConstantsUtils.KNOWLEDGE_ARTICLE_ID, 0);
        }

        mPresenter = new AidedPresenter(this);
        mPresenter.query(article_id);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == DataAidedActivity.RESULT_CODE && data != null) {
            int state = data.getIntExtra(ConstantsUtils.ITEM_DELETE_STATUS, 0);
            if (state == 1) {
                finish();
            } else if (state == 2) {
            }
        }
    }

    public static void launch(Activity activity, long item_id) {
        Intent intent = new Intent(activity, ShowAidedActivity.class);
        intent.putExtra(ConstantsUtils.KNOWLEDGE_ARTICLE_ID, item_id);
        activity.startActivity(intent);
    }

    private void initData(AidedBean aided) {
        aidedShowDirectory.setText(aided.getDirectory());
        aidedShowExplain.setText(aided.getNow_explain());
        aidedShowDeepExplain.setText(aided.getDeep_explain());
        aidedShowCase.setText(aided.getCase());
        aidedShowAdvanced.setText(aided.getAdvance());
        aidedShowExperience.setText(aided.getExperience());
        aidedShowPublicize.setText(aided.getPublicize());
        commonTitle.setText(aided.getTitle());
    }

    @OnClick({R.id.common_back, R.id.common_img})
    public void onViewClicked(View itemView) {
        switch (itemView.getId()) {
            case R.id.common_back:
                finish();
                break;
            case R.id.common_img:
                DataAidedActivity.launch(ShowAidedActivity.this,REQUEST_CODE,article_id);
                break;
        }
    }

    @Override
    public void onSuccess(Object object) {
        scrollView.setVisibility(View.VISIBLE);
        includeKnowEmpty.setVisibility(View.GONE);
        initData((AidedBean) object);
    }

    @Override
    public void onFail() {
        ToastUtils.ToastText(getContext(), R.string.aided_query_empty);
        scrollView.setVisibility(View.GONE);
        includeKnowEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void onError(String message) {
        ToastUtils.ToastText(getContext(), message);
        scrollView.setVisibility(View.GONE);
        includeKnowEmpty.setVisibility(View.VISIBLE);
    }
}
