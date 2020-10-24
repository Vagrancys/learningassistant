package com.vargancys.learningassistant.module.home.activity.update;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.model.home.bean.AidedBean;
import com.vargancys.learningassistant.module.home.view.BaseKnowLedgeUpdateView;
import com.vargancys.learningassistant.presenter.home.AidedPresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/09
 * version:1.0
 * 更新辅助型知识页面
 */
public class UpdateAidedActivity extends BaseActivity implements BaseKnowLedgeUpdateView {
    private static String TAG = "UpdateAidedActivity";
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.common_img)
    ImageView commonImg;
    @BindView(R.id.aided_update_directory)
    EditText aidedUpdateDirectory;
    @BindView(R.id.aided_update_explain)
    EditText aidedUpdateExplain;
    @BindView(R.id.aided_update_deep_explain)
    EditText aidedUpdateDeepExplain;
    @BindView(R.id.aided_update_case)
    EditText aidedUpdateCase;
    @BindView(R.id.aided_update_experience)
    EditText aidedUpdateExperience;
    @BindView(R.id.aided_update_advanced)
    EditText aidedUpdateAdvanced;
    @BindView(R.id.aided_update_publicize)
    EditText aidedUpdatePublicize;
    private AidedPresenter mPresenter;
    private int article_id;
    private int father_id;
    private int RESULT_CODE = 2002;
    private int UPDATE_STATE = 0;
    private AidedBean mArticle;

    @Override
    public int getLayoutId() {
        return R.layout.activity_knowledge_update_aided;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        if (intent != null) {
            article_id = intent.getIntExtra(ConstantsUtils.KNOWLEDGE_ARTICLE_ID, 0);
            father_id = intent.getIntExtra(ConstantsUtils.KNOWLEDGE_FATHER_ID, 0);
        }
        mPresenter = new AidedPresenter(this);
        mPresenter.query(article_id);
    }

    @Override
    public void initToolbar() {
        commonTitle.setText(getText(R.string.aided_update_toolbar));

        commonImg.setImageResource(R.drawable.common_update_normal);
    }

    public static void launch(Activity activity, int request_code, int father_id, long article_id) {
        Intent intent = new Intent(activity, UpdateArticleActivity.class);
        intent.putExtra(ConstantsUtils.KNOWLEDGE_ARTICLE_ID, article_id);
        intent.putExtra(ConstantsUtils.KNOWLEDGE_FATHER_ID, father_id);
        activity.startActivityForResult(intent, request_code);
    }

    @Override
    public boolean isPass() {
        return isEmptyPass() && isEqualsPass();
    }

    private boolean isEqualsPass() {
        return aidedUpdateDirectory.getText().toString().equals(mArticle.getDirectory()) &&
                aidedUpdateExplain.getText().toString().equals(mArticle.getExplain()) &&
                aidedUpdateDeepExplain.getText().toString().equals(mArticle.getDeep_explain()) &&
                aidedUpdateCase.getText().toString().equals(mArticle.getCase()) &&
                aidedUpdateAdvanced.getText().toString().equals(mArticle.getAdvance()) &&
                aidedUpdatePublicize.getText().toString().equals(mArticle.getPublicize());
    }

    public boolean isEmptyPass(){
        return aidedUpdateDirectory.getText().length() > 0 &&
                aidedUpdateExplain.getText().length() > 0 &&
                aidedUpdateDeepExplain.getText().length() > 0 &&
                aidedUpdateAdvanced.getText().length() > 0 &&
                aidedUpdateExperience.getText().length() > 0 &&
                aidedUpdatePublicize.getText().length() > 0;
    }

    @Override
    public void isPassSuccess() {
        mArticle.setDirectory(aidedUpdateDirectory.getText().toString());
        mArticle.setNow_explain(aidedUpdateExplain.getText().toString());
        mArticle.setDeep_explain(aidedUpdateDeepExplain.getText().toString());
        mArticle.setAdvance(aidedUpdateAdvanced.getText().toString());
        mArticle.setExperience(aidedUpdateExperience.getText().toString());
        mArticle.setCase(aidedUpdateCase.getText().toString());
        mArticle.setPublicize(aidedUpdatePublicize.getText().toString());
        mPresenter.update(mArticle);
    }

    @Override
    public void isPassFail() {
        ToastUtils.ToastText(getContext(), R.string.aided_update_empty);
    }

    @Override
    public void onUpdateSuccess() {
        ToastUtils.ToastText(getContext(), R.string.aided_update_success);
        //0没有更新 1更新了
        UPDATE_STATE = 1;
    }

    @Override
    public void onUpdateFail() {
        ToastUtils.ToastText(getContext(), R.string.aided_update_fail);
    }

    @OnClick({R.id.common_back, R.id.common_img})
    public void onViewClicked(View itemView) {
        switch (itemView.getId()) {
            case R.id.common_back:
                Intent intent = new Intent();
                intent.putExtra(ConstantsUtils.ITEM_UPDATE_STATUS, UPDATE_STATE);
                setResult(RESULT_CODE, intent);
                finish();
                break;
            case R.id.common_img:
                mPresenter.isUpdateEmpty();
                break;
        }
    }

    @Override
    public void onSuccess(Object object) {
        mArticle = (AidedBean) object;
        initAidedBean();
    }

    private void initAidedBean() {
        aidedUpdateDirectory.setText(mArticle.getDirectory());
        aidedUpdateExplain.setText(mArticle.getExplain());
        aidedUpdateDeepExplain.setText(mArticle.getDeep_explain());
        aidedUpdateCase.setText(mArticle.getCase());
        aidedUpdateAdvanced.setText(mArticle.getAdvance());
        aidedUpdatePublicize.setText(mArticle.getPublicize());
    }

    @Override
    public void onFail() {
        ToastUtils.ToastText(getContext(), R.string.common_fail);
    }

    @Override
    public void onError(String message) {
        ToastUtils.ToastText(getContext(), R.string.common_error);
    }
}