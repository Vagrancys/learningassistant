package com.vargancys.learningassistant.module.home.activity.insert;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.bean.home.ArticleBean;
import com.vargancys.learningassistant.db.TemporaryArticleDb;
import com.vargancys.learningassistant.module.home.activity.show.ShowArticleActivity;
import com.vargancys.learningassistant.module.home.view.InsertArticleView;
import com.vargancys.learningassistant.presenter.home.ArticlePresenter;
import com.vargancys.learningassistant.utils.CacheUtils;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/09
 * version:1.0
 * 知识文章型页面
 */
public class InsertArticleActivity extends BaseActivity implements InsertArticleView {
    @BindView(R.id.article_edit)
    EditText articleEdit;
    private ArticlePresenter mPresenter;
    //知识的id
    private int KnowLedge_Id;
    //本地文章id
    private long nativeArticle_id;
    private boolean isArticle = false;

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
        mPresenter.nativeQuery(KnowLedge_Id);
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

    @Override
    public void nativeQueryFinish(Object object) {
        isArticle = true;
        TemporaryArticleDb db = (TemporaryArticleDb) object;
        nativeArticle_id = db.getTemporary_article_id();
        articleEdit.setText(db.getContent());
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

    @OnClick({R.id.common_back,R.id.article_save,R.id.article_set,R.id.article_data})
    public void onViewClicked(View itemView){
        switch (itemView.getId()){
            case R.id.common_back:
                finishArticle();
                break;
            case R.id.article_save:
                mPresenter.isEmpty();
                break;
            case R.id.article_data:
            case R.id.article_set:
                // TODO 功能等待....
                ToastUtils.DefaultToast(getContext());
                break;

        }
    }

    private void finishArticle() {
        if(articleEdit.getText().length() > 0){
            if(CacheUtils.getBoolean(getContext(),ConstantsUtils.ARTICLE_NATIVE_HINT_STATE)){
                if(CacheUtils.getBoolean(getContext(),ConstantsUtils.ARTICLE_NATIVE_SAVE_STATE)){
                    nativeAdd();
                }

            }else{
                CacheUtils.putBoolean(getContext(),ConstantsUtils.ARTICLE_NATIVE_HINT_STATE,true);
                new AlertDialog.Builder(getContext())
                        .setMessage(R.string.article_native_message)
                        .setTitle(R.string.article_native_title)
                        .setPositiveButton(R.string.common_cancel_text, (dialog, which) -> {
                            CacheUtils.putBoolean(getContext(),ConstantsUtils.ARTICLE_NATIVE_HINT_STATE,false);
                        })
                        .setNegativeButton(R.string.common_determine_text, (dialog, which) -> {
                            CacheUtils.putBoolean(getContext(),ConstantsUtils.ARTICLE_NATIVE_HINT_STATE,true);
                           nativeAdd();
                        });
            }

        }else{
            finish();
        }
    }

    private void nativeAdd(){
        TemporaryArticleDb mDB = new TemporaryArticleDb();
        mDB.setArticle_id(KnowLedge_Id);
        mDB.setContent(articleEdit.getText().toString());
        mPresenter.nativeAdd(mDB);
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onSuccess(Object object) {
        if(isArticle){
            mPresenter.nativeDelete(nativeArticle_id);
        }
        ToastUtils.ToastText(getContext(),R.string.know_insert_success_text);
        initEmpty();
        ShowArticleActivity.launch(this,(int)object);
        finish();
    }

    @Override
    public void onNoData() {

    }

    @Override
    public void onFail() {
        ToastUtils.ToastText(getContext(),R.string.common_fail);
    }

    @Override
    public void onError(String message) {
        ToastUtils.ToastText(getContext(),R.string.common_error);
    }

    @Override
    public void onFinish() {

    }
}
