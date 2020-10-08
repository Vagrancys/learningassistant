package com.vargancys.learningassistant.module.home.activity.insert;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.model.home.bean.ArticleBean;
import com.vargancys.learningassistant.db.knowledge.TemporaryArticleDb;
import com.vargancys.learningassistant.module.home.activity.show.ShowArticleActivity;
import com.vargancys.learningassistant.module.home.view.InsertArticleView;
import com.vargancys.learningassistant.presenter.home.ArticlePresenter;
import com.vargancys.learningassistant.utils.CacheUtils;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ToastUtils;
import com.vargancys.learningassistant.widget.KnowLedgeDataDialog;

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
    @BindView(R.id.common_title)
    TextView commonTitle;
    private ArticlePresenter mPresenter;
    //知识的id
    private int KnowLedge_Id;
    //本地文章id
    private long nativeArticle_id;
    private boolean isArticle = false;
    private KnowLedgeDataDialog mDialog;
    private ArticleBean mArticle = new ArticleBean();

    @Override
    public int getLayoutId() {
        return R.layout.activity_knowledge_insert_article;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        if(intent != null){
            KnowLedge_Id = intent.getIntExtra(ConstantsUtils.KNOWLEDGE_ID,0);
        }
        mPresenter = new ArticlePresenter(this);
        mPresenter.nativeQuery(KnowLedge_Id);
        mArticle = new ArticleBean();
        initDialog();
    }

    private void initDialog(){
        mDialog = new KnowLedgeDataDialog(this);
        mDialog.setOnClickCancelListener(() -> mDialog.cancel());
        mDialog.setOnClickDeterMineListener((common, title, summary, explain) -> {
            mArticle.setLevel(common);
            mArticle.setTitle(title);
            mArticle.setSummary(summary);
            mArticle.setExplain(explain);
            mDialog.dismiss();
        });
    }

    @Override
    public void initToolbar() {
        commonTitle.setText(getText(R.string.article_toolbar));
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

        mArticle.setFather_id(KnowLedge_Id);
        mArticle.setContent(articleEdit.getText().toString());
        mPresenter.add(mArticle);
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

    @OnClick({R.id.common_back,R.id.common_save,R.id.common_set,R.id.common_data})
    public void onViewClicked(View itemView){
        switch (itemView.getId()){
            case R.id.common_back:
                finishArticle();
                break;
            case R.id.common_save:
                mPresenter.isEmpty();
                break;
            case R.id.common_data:
                if(!mDialog.isEdit()){
                    mDialog.setTitle(mArticle.getTitle());
                    mDialog.setLevel(mArticle.getLevel());
                    mDialog.setExplain(mArticle.getExplain());
                    mDialog.setSummary(mArticle.getSummary());
                }
                mDialog.show();
                break;
            case R.id.common_set:
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
