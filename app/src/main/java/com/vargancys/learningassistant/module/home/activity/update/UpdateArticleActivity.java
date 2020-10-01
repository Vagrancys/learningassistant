package com.vargancys.learningassistant.module.home.activity.update;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.bean.home.ArticleBean;
import com.vargancys.learningassistant.module.home.view.BaseKnowLedgeUpdateView;
import com.vargancys.learningassistant.presenter.home.ArticlePresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/09
 * version:1.0
 * 知识更新一级页面
 */
public class UpdateArticleActivity extends BaseActivity implements BaseKnowLedgeUpdateView {
    private static String TAG = "UpdateArticleActivity";
    @BindView(R.id.common_img)
    ImageView commonImg;
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.update_edit)
    EditText updateEdit;
    private ArticlePresenter mPresenter;
    private int article_id;
    private int father_id;
    private int RESULT_CODE = 2002;
    private int UPDATE_STATE = 0;
    private ArticleBean mArticle;

    @Override
    public int getLayoutId() {
        return R.layout.activity_knowledge_update_article;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        if(intent != null){
            article_id = intent.getIntExtra(ConstantsUtils.KNOWLEDGE_ARTICLE_ID,0);
            father_id = intent.getIntExtra(ConstantsUtils.KNOWLEDGE_FATHER_ID,0);
        }
        mPresenter = new ArticlePresenter(this);
        mPresenter.query(article_id);
    }

    @Override
    public void initToolbar() {
        commonTitle.setText(getText(R.string.common_update_first));

        commonImg.setImageResource(R.drawable.common_update_normal);
    }

    public static void launch(Activity activity,int request_code, int father_id,long article_id){
        Intent intent = new Intent(activity, UpdateArticleActivity.class);
        intent.putExtra(ConstantsUtils.KNOWLEDGE_ARTICLE_ID,article_id);
        intent.putExtra(ConstantsUtils.KNOWLEDGE_FATHER_ID,father_id);
        activity.startActivityForResult(intent,request_code);
    }

    @Override
    public boolean isPass() {
        return !updateEdit.getText().toString().isEmpty()&&updateEdit.getText().toString().equals(mArticle.getTitle());
    }

    @Override
    public void isPassSuccess() {
        mArticle.setTitle(updateEdit.getText().toString());
        mPresenter.update(mArticle);
    }

    @Override
    public void isPassFail() {
        ToastUtils.ToastText(getContext(),R.string.article_update_empty);
    }

    @Override
    public void onUpdateSuccess() {
        ToastUtils.ToastText(getContext(),"修改成功了正在退出!");
        //0没有更新 1更新了
        UPDATE_STATE = 1;
    }

    @Override
    public void onUpdateFail() {
        ToastUtils.ToastText(getContext(),R.string.article_update_fail);
    }

    @OnClick({R.id.common_back,R.id.common_img})
    public void onViewClicked(View itemView){
        switch (itemView.getId()){
            case R.id.common_back:
                Intent intent = new Intent();
                intent.putExtra(ConstantsUtils.ITEM_UPDATE_STATUS,UPDATE_STATE);
                setResult(RESULT_CODE,intent);
                finish();
                break;
            case R.id.common_img:
                mPresenter.isUpdateEmpty();
                break;
        }
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onSuccess(Object object) {
        mArticle =(ArticleBean) object;
        updateEdit.setText(mArticle.getTitle());
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
