package com.vargancys.learningassistant.module.home.activity.data;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.model.home.bean.ArticleDataBean;
import com.vargancys.learningassistant.module.home.activity.KnowSettingContentActivity;
import com.vargancys.learningassistant.module.home.activity.update.UpdateArticleActivity;
import com.vargancys.learningassistant.module.home.view.DataKnowledgeView;
import com.vargancys.learningassistant.presenter.home.ArticlePresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ResourceUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/25
 * version:1.0
 * 展示知识数据页面
 */
public class DataArticleActivity extends BaseActivity implements DataKnowledgeView {
    private static String TAG = "DataArticleActivity";
    @BindView(R.id.common_title_data)
    TextView commonTitleData;
    @BindView(R.id.know_data_title)
    TextView knowDataTitle;
    @BindView(R.id.know_data_level)
    ImageView knowDataLevel;
    @BindView(R.id.know_data_count)
    TextView knowDataCount;
    @BindView(R.id.know_data_master)
    TextView knowDataMaster;
    private ArticlePresenter mPresenter;
    private int father_id;
    private int article_id;
    public static int RESULT_CODE = 2000;
    public static int REQUEST_CODE = 1900;
    public static int RESULT_UPDATE_CODE = 2002;
    private boolean update_status = false;

    @Override
    public int getLayoutId() {
        return R.layout.activity_show_know_data;
    }

    @Override
    public void initView() {
        if(getIntent() !=null){
            article_id = getIntent().getIntExtra(ConstantsUtils.KNOWLEDGE_ARTICLE_ID,0);
        }
        mPresenter = new ArticlePresenter(this);
        mPresenter.queryData(article_id);
    }

    public static void launch(Activity activity,int SHOW_REQUEST,int article_id){
        Intent intent = new Intent(activity, DataArticleActivity.class);
        intent.putExtra(ConstantsUtils.KNOWLEDGE_ARTICLE_ID,article_id);
        activity.startActivityForResult(intent,SHOW_REQUEST);
    }

    private void SelectDataLevel(int level){
        switch (level){
            case 1:
                knowDataLevel.setImageResource(R.drawable.know_level_1);
                break;
            case 2:
                knowDataLevel.setImageResource(R.drawable.know_level_2);
                break;
            case 3:
                knowDataLevel.setImageResource(R.drawable.know_level_3);
                break;
            case 4:
                knowDataLevel.setImageResource(R.drawable.know_level_4);
                break;
            case 5:
                knowDataLevel.setImageResource(R.drawable.know_level_5);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE&&resultCode == RESULT_UPDATE_CODE&&data !=null){
            if(data.getIntExtra(ConstantsUtils.ITEM_UPDATE_STATUS,0) == 1){
                update_status = true;
            }
        }
    }

    @Override
    public void deleteSuccess() {
        Intent intent = new Intent();
        //0表示没有状态，1表示删除状态,2表示更新状态
        intent.putExtra(ConstantsUtils.ITEM_DELETE_STATUS,1);
        setResult(RESULT_CODE,intent);
        finish();
    }

    @Override
    public void deleteFail() {
        ToastUtils.ToastText(getContext(),R.string.article_delete_fail);
    }

    @Override
    public void deleteError(String message) {
        ToastUtils.ToastText(getContext(),R.string.article_delete_error);
    }

    @OnClick({R.id.common_back,R.id.know_data_setting,R.id.know_data_update,R.id.know_data_delete})
    public void onViewClicked(View itemView){
        switch (itemView.getId()){
            case R.id.common_back:
                //0表示没有状态，1表示删除状态,2表示更新状态
                if(update_status){
                    Intent intent = new Intent();
                    intent.putExtra(ConstantsUtils.ITEM_DELETE_STATUS,2);
                    setResult(RESULT_CODE,intent);
                }
                finish();
                break;
            case R.id.know_data_setting:
                KnowSettingContentActivity.launch(DataArticleActivity.this,article_id);
                break;
            case R.id.know_data_update:
                UpdateArticleActivity.launch(this,REQUEST_CODE,father_id,article_id);
                break;
            case R.id.know_data_delete:
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setTitle(ResourceUtils.getString(getContext(),R.string.dialog_data_delete_title));
                dialog.setMessage(ResourceUtils.getString(getContext(),R.string.dialog_data_delete_message));
                dialog.setNegativeButton(R.string.common_cancel_text, (dialog1, which) -> dialog1.dismiss());
                dialog.setPositiveButton(R.string.common_determine_text, (dialog12, which) -> {
                    dialog12.dismiss();
                    mPresenter.delete(article_id);
                });
                dialog.show();
                break;
        }
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onSuccess(Object object) {
        ArticleDataBean mBean = (ArticleDataBean) object;
        father_id = mBean.getFather_id();
        commonTitleData.setText(mBean.getTitle());
        knowDataTitle.setText(mBean.getTitle());
        SelectDataLevel(mBean.getLevel());
        knowDataCount.setText(mBean.getCount()+"次");
        knowDataMaster.setText(mBean.getMaster());
    }

    @Override
    public void onNoData() {

    }

    @Override
    public void onFail() {
        ToastUtils.ToastText(getContext(),R.string.article_data_empty);
    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void onFinish() {

    }
}
