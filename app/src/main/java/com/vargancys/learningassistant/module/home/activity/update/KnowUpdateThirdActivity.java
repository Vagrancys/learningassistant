package com.vargancys.learningassistant.module.home.activity.update;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.db.home.HomeKnowContent;
import com.vargancys.learningassistant.db.home.HomeKnowHistory;
import com.vargancys.learningassistant.module.home.view.BaseKnowUpdateView;
import com.vargancys.learningassistant.presenter.home.KnowUpdatePresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/09
 * version:1.0
 * 知识展示三级页面
 */
public class KnowUpdateThirdActivity extends BaseActivity implements BaseKnowUpdateView {
    private static String TAG = "KnowUpdateThirdActivity";
    @BindView(R.id.common_img)
    ImageView commonImg;
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.update_title_edit)
    EditText updateTitleEdit;
    @BindView(R.id.update_summary_edit)
    EditText updateSummaryEdit;
    @BindView(R.id.update_show_edit)
    EditText updateShowEdit;
    @BindView(R.id.update_explain_edit)
    EditText updateExplainEdit;
    @BindView(R.id.update_heed_edit)
    EditText updateHeedEdit;
    @BindView(R.id.update_experience_edit)
    EditText updateExperienceEdit;
    private KnowUpdatePresenter mPresenter;
    private long contentId;
    private long dataId;
    private int RESULT_CODE = 2002;
    private HomeKnowHistory mOldHistory;
    private HomeKnowContent mNewContent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_know_update_third;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        if(intent != null){
            contentId = intent.getLongExtra(ConstantsUtils.KNOW_CONTENT_ID,0);
            dataId = intent.getLongExtra(ConstantsUtils.KNOW_DATA_ID,0);
        }
        mPresenter = new KnowUpdatePresenter(this);
        mPresenter.getKnowThirdContent(contentId);
    }

    @Override
    public void initToolbar() {
        commonTitle.setText(getText(R.string.common_update_third));

        commonImg.setImageResource(R.drawable.common_update_normal);
    }

    public static void launch(Activity activity,int REQUEST_CODE, long content_id,long data_id){
        Intent intent = new Intent(activity, KnowUpdateThirdActivity.class);
        intent.putExtra(ConstantsUtils.KNOW_CONTENT_ID,content_id);
        intent.putExtra(ConstantsUtils.KNOW_DATA_ID,data_id);
        activity.startActivityForResult(intent,REQUEST_CODE);
    }

    @Override
    public boolean isKnowUpdateDefaultEmpty() {
        return updateTitleEdit.getText().toString().isEmpty()&&
                updateSummaryEdit.getText().toString().isEmpty()&&
                updateShowEdit.getText().toString().isEmpty()&&
                updateExplainEdit.getText().toString().isEmpty()&&
                updateExperienceEdit.getText().toString().isEmpty()&&
                updateHeedEdit.getText().toString().isEmpty();
    }

    @Override
    public void showKnowEmptyError(int error, String message) {
        ToastUtils.ToastText(getContext(),"Error = "+error+",Message = "+message);
    }

    @Override
    public boolean isKnowUpdateDefaultEquals() {
        return updateTitleEdit.getText().toString().equals(mOldHistory.getTitle())&&
                updateSummaryEdit.getText().toString().equals(mOldHistory.getSummary())&&
                updateShowEdit.getText().toString().equals(mOldHistory.getShow())&&
                updateExplainEdit.getText().toString().equals(mOldHistory.getExplain())&&
                updateExperienceEdit.getText().toString().equals(mOldHistory.getExperience())&&
                updateHeedEdit.getText().toString().equals(mOldHistory.getHeed());
    }

    @Override
    public void saveKnowUpdateContent() {
        mNewContent.setTitle(updateTitleEdit.getText().toString());
        mNewContent.setSummary(updateSummaryEdit.getText().toString());
        mNewContent.setShow(updateShowEdit.getText().toString());
        mNewContent.setExplain(updateExplainEdit.getText().toString());
        mNewContent.setExperience(updateExperienceEdit.getText().toString());
        mNewContent.setHeed(updateHeedEdit.getText().toString());
        mPresenter.saveKnowUpdateThird(mOldHistory,mNewContent);
    }

    @Override
    public void showKnowEqualsError(int error, String message) {
        ToastUtils.ToastText(getContext(),"Error ="+error+",Message ="+message);
    }

    @Override
    public void showKnowDataFinish(HomeKnowContent content) {
        mNewContent = content;
        addHistory(mNewContent);
        updateTitleEdit.setText(content.getTitle());
        updateSummaryEdit.setText(content.getSummary());
        updateShowEdit.setText(content.getShow());
        updateExplainEdit.setText(content.getExplain());
        updateHeedEdit.setText(content.getHeed());
        updateExperienceEdit.setText(content.getExperience());
    }

    private void addHistory(HomeKnowContent content){
        mOldHistory = new HomeKnowHistory();
        mOldHistory.setDataId(dataId);
        mOldHistory.setTitle(content.getTitle());
        mOldHistory.setSummary(content.getSummary());
        mOldHistory.setExplain(content.getExplain());
        mOldHistory.setExperience(content.getExperience());
        mOldHistory.setHeed(content.getHeed());
        mOldHistory.setShow(content.getShow());
    }

    @Override
    public void showKnowDataError(int error, String message) {
        ToastUtils.ToastText(getContext(),"Error = "+error+", Message ="+message);
    }

    @Override
    public void showKnowSaveFinish() {
        ToastUtils.ToastText(getContext(),"修改成功了正在退出!");
        //0没有更新 1更新了
        Intent intent = new Intent();
        intent.putExtra(ConstantsUtils.ITEM_UPDATE_STATUS,1);
        setResult(RESULT_CODE,intent);
        finish();
    }

    @Override
    public void showKnowSaveError(int error, String message) {
        ToastUtils.ToastText(getContext(),"Error ="+error+", Message ="+message);
    }

    @OnClick({R.id.common_back,R.id.common_img})
    public void onViewClicked(View itemView){
        switch (itemView.getId()){
            case R.id.common_back:
                setResult(RESULT_CODE);
                finish();
                break;
            case R.id.common_img:
                mPresenter.isKnowUpdateThirdEmpty();
                break;
        }
    }
}

