package com.vargancys.learningassistant.module.home.activity.insert;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.db.home.ArticleBean;
import com.vargancys.learningassistant.module.home.view.InsertArticleView;
import com.vargancys.learningassistant.presenter.home.ArticlePresenter;
import com.vargancys.learningassistant.presenter.home.KnowInsertPresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/09
 * version:1.0
 * 知识添加一级页面
 */
public class InsertArticleActivity extends BaseActivity implements InsertArticleView {
    @BindView(R.id.article_edit)
    EditText articleEdit;
    private ArticlePresenter mPresenter;
    //知识的id
    private int KnowLedge_Id;

    @Override
    public int getLayoutId() {
        return R.layout.activity_article_insert;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        if(intent != null){
            KnowLedge_Id = intent.getIntExtra(ConstantsUtils.KNOWLEDGE_ID,0);
        }
        mPresenter = new ArticlePresenter(this);
    }

    @Override
    public void initToolbar() {

    }

    @Override
    public boolean isEmpty() {
        return articleEdit.getText().length() > 0;
    }

    @Override
    public void isEmptyError(int error) {
        ToastUtils.ToastText(getContext(),R.string.article_edit_empty);
    }

    @Override
    public void isEmptyFinish() {
        mPresenter.addArticle();
    }

    @Override
    public void addArticle() {
        ArticleBean mBean = new ArticleBean();
        mBean.setKnowledge_id(KnowLedge_Id);
        mBean.setContent(articleEdit.getText().toString());
        mPresenter.add(mBean);
    }

    public static void launch(Activity activity, int knowledge_id){
        Intent intent = new Intent(activity, InsertArticleActivity.class);
        intent.putExtra(ConstantsUtils.KNOWLEDGE_ID,knowledge_id);
        activity.startActivity(intent);
    }

    private void initEmpty() {
        mPresenter.nativeDelete(KnowLedge_Id);
        articleEdit.setText(null);
    }

    @OnClick({R.id.common_back,R.id.article_save})
    public void onViewClicked(View itemView){
        switch (itemView.getId()){
            case R.id.common_back:
                finishArticle();
                break;
            case R.id.article_save:
                mPresenter.isEmpty();
                break;
        }
    }

    private void finishArticle() {
        if(articleEdit.getText().length() > 0){
            // TODO
        }else{
            finish();
        }
    }

    @Override
    public void onSuccess() {
        ToastUtils.ToastText(getContext(),R.string.know_insert_success_text);
        initEmpty();
        finish();
    }

    @Override
    public void onSuccess(Object object) {

    }

    @Override
    public void onNoData() {

    }

    @Override
    public void onFail() {
        ToastUtils.ToastText(getContext(),R.string.article_fail);
    }

    @Override
    public void onError(String message) {
        ToastUtils.ToastText(getContext(),R.string.article_error);
    }

    @Override
    public void onFinish() {

    }
}
