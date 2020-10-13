package com.vargancys.learningassistant.module.home.activity.insert;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.model.home.bean.AidedBean;
import com.vargancys.learningassistant.module.home.view.InsertAidedView;
import com.vargancys.learningassistant.presenter.home.AidedPresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ResourceUtils;
import com.vargancys.learningassistant.utils.ToastUtils;
import com.vargancys.learningassistant.widget.dialog.KnowLedgeDataDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/09
 * version:1.0
 * 知识添加三级页面
 */
public class InsertAidedActivity extends BaseActivity implements InsertAidedView {
    private static String TAG = "InsertAidedActivity";
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.aided_insert_directory)
    EditText aidedInsertDirectory;
    @BindView(R.id.aided_insert_explain)
    EditText aidedInsertExplain;
    @BindView(R.id.aided_insert_deep_explain)
    EditText aidedInsertDeepExplain;
    @BindView(R.id.aided_insert_experience)
    EditText aidedInsertExperience;
    @BindView(R.id.aided_insert_advanced)
    EditText aidedInsertAdvanced;
    @BindView(R.id.aided_insert_publicize)
    EditText aidedInsertPublicize;
    private AidedPresenter mPresenter;
    private int knowledge_article_id;
    private KnowLedgeDataDialog mDialog;
    private AidedBean aidedBean;
    private boolean dialog_state = false;

    @Override
    public int getLayoutId() {
        return R.layout.activity_knowledge_insert_aided;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        if (intent != null) {
            knowledge_article_id = intent.getIntExtra(ConstantsUtils.KNOWLEDGE_ARTICLE_ID, 0);
        }
        mPresenter = new AidedPresenter(this);
        aidedBean = new AidedBean();
        aidedBean.setFather_id(knowledge_article_id);
        initDialog();
    }

    private void initDialog(){
        mDialog = new KnowLedgeDataDialog(this);
        mDialog.setOnClickDeterMineListener((common, title, summary, explain) -> {
            aidedBean.setLevel(common);
            aidedBean.setTitle(title);
            aidedBean.setSummary(summary);
            aidedBean.setExplain(explain);
            mDialog.dismiss();
        });
        mDialog.setOnClickCancelListener(()->{
            mDialog.dismiss();
        });
    }

    @Override
    public void initToolbar() {
        commonTitle.setText(ResourceUtils.getString(getContext(),R.string.aided_insert_toolbar));
    }

    public static void launch(Activity activity, int know_id) {
        Intent intent = new Intent(activity, InsertAidedActivity.class);
        intent.putExtra(ConstantsUtils.KNOWLEDGE_ARTICLE_ID, know_id);
        activity.startActivity(intent);
    }



    private void initEmpty() {
        aidedInsertDirectory.setText(null);
        aidedInsertExplain.setText(null);
        aidedInsertDeepExplain.setText(null);
        aidedInsertExperience.setText(null);
        aidedInsertAdvanced.setText(null);
        aidedInsertPublicize.setText(null);
    }

    @OnClick({R.id.common_back, R.id.common_data,R.id.common_save})
    public void onViewClicked(View itemView) {
        switch (itemView.getId()) {
            case R.id.common_back:
                finish();
                break;
            case R.id.common_data:
                if(dialog_state){
                    mDialog.setLevel(aidedBean.getLevel());
                    mDialog.setTitle(aidedBean.getTitle());
                    mDialog.setSummary(aidedBean.getSummary());
                    mDialog.setExplain(aidedBean.getExplain());
                }else{
                    mDialog.show();
                }
                break;
            case R.id.common_save:
                mPresenter.isDataEmpty();
                break;
        }
    }

    @Override
    public boolean isDataEmpty() {
        return aidedInsertDirectory.getText().length() > 0 &&
                aidedInsertExplain.getText().length() > 0 &&
                aidedInsertDeepExplain.getText().length() > 0 &&
                aidedInsertAdvanced.getText().length() > 0 &&
                aidedInsertExperience.getText().length() > 0 &&
                aidedInsertPublicize.getText().length() > 0;
    }

    @Override
    public void isDataEmptyFail() {
        ToastUtils.ToastText(getContext(),R.string.aided_insert_empty_fail);
    }

    @Override
    public void isDataEmptySuccess() {
        aidedBean.setDirectory(aidedInsertDirectory.getText().toString());
        aidedBean.setNow_explain(aidedInsertExplain.getText().toString());
        aidedBean.setDeep_explain(aidedInsertDeepExplain.getText().toString());
        aidedBean.setAdvance(aidedInsertAdvanced.getText().toString());
        aidedBean.setExperience(aidedInsertExperience.getText().toString());
        aidedBean.setPublicize(aidedInsertPublicize.getText().toString());
        mPresenter.add(aidedBean);
    }

    @Override
    public void onSuccess() {
        ToastUtils.ToastText(getContext(),R.string.aided_insert_save_success);
        initEmpty();
    }

    @Override
    public void onSuccess(Object object) {

    }

    @Override
    public void onNoData() {

    }

    @Override
    public void onFail() {
        ToastUtils.ToastText(getContext(),R.string.aided_insert_save_fail);
    }

    @Override
    public void onError(String message) {
        ToastUtils.ToastText(getContext(),R.string.aided_insert_save_error);
    }

    @Override
    public void onFinish() {

    }
}

