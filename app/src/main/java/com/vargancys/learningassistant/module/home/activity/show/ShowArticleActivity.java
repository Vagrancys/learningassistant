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
import com.vargancys.learningassistant.model.home.bean.ArticleBean;
import com.vargancys.learningassistant.module.home.activity.ShowKnowDataActivity;
import com.vargancys.learningassistant.module.home.activity.data.DataArticleActivity;
import com.vargancys.learningassistant.module.home.view.ShowCommonView;
import com.vargancys.learningassistant.presenter.home.ArticlePresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/06
 * version:1.0
 * 知识展示一级页面
 */
public class ShowArticleActivity extends BaseActivity  implements ShowCommonView<ArticleBean> {
    private static final String TAG = "ShowArticleActivity";

    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.include_know_empty)
    LinearLayout includeKnowEmpty;
    @BindView(R.id.article_content)
    TextView articleContent;
    private ArticlePresenter mPresenter;
    private int article_id;
    private static int REQUEST_CODE = 2001;

    @Override
    public int getLayoutId() {
        return R.layout.activity_knowledge_show_article;
    }

    @Override
    public void initView() {
        if (getIntent() != null) {
            article_id = getIntent().getIntExtra(ConstantsUtils.KNOWLEDGE_ARTICLE_ID, 0);
        }

        mPresenter = new ArticlePresenter(this);
        mPresenter.query(article_id);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE&&resultCode == ShowKnowDataActivity.RESULT_CODE&&data !=null){
            int state = data.getIntExtra(ConstantsUtils.ITEM_DELETE_STATUS,0);
            if(state == 1){
                finish();
            }else if(state == 2){
                mPresenter.query(article_id);
            }
        }
    }

    public static void launch(Activity activity, long article_id) {
        Intent intent = new Intent(activity, ShowArticleActivity.class);
        intent.putExtra(ConstantsUtils.KNOWLEDGE_ARTICLE_ID, article_id);
        activity.startActivity(intent);
    }



    @Override
    public void showFinish(ArticleBean object) {

    }

    @Override
    public void showError(String msg) {

    }

    private void initData(ArticleBean object) {
        articleContent.setText(object.getTitle());
        commonTitle.setText(object.getTitle());
    }

    @OnClick({R.id.common_back,R.id.common_img})
    public void onViewClicked(View itemView){
        switch (itemView.getId()){
            case R.id.common_back:
                finish();
                break;
            case R.id.common_img:
                DataArticleActivity.launch(ShowArticleActivity.this,REQUEST_CODE,article_id);
                break;
        }
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onSuccess(Object object) {
        scrollView.setVisibility(View.VISIBLE);
        includeKnowEmpty.setVisibility(View.GONE);
        initData((ArticleBean) object);
    }

    @Override
    public void onNoData() {

    }

    @Override
    public void onFail() {
        ToastUtils.ToastText(getContext(),R.string.article_query_empty);
        scrollView.setVisibility(View.GONE);
        includeKnowEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void onError(String message) {
        ToastUtils.ToastText(getContext(),message);
        scrollView.setVisibility(View.GONE);
        includeKnowEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFinish() {

    }
}
