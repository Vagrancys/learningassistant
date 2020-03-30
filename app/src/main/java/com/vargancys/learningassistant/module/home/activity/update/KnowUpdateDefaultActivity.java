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
import com.vargancys.learningassistant.module.home.view.KnowUpdateFourthView;
import com.vargancys.learningassistant.persenter.home.KnowUpdatePresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import butterknife.BindView;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/28
 * version:1.0
 */
public class KnowUpdateDefaultActivity extends BaseActivity implements KnowUpdateFourthView {
    private static String TAG = "KnowUpdateDefaultActivity";
    @BindView(R.id.common_back)
    ImageView commonBack;
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.common_img)
    ImageView commonImg;
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
    private long know_id;
    private KnowUpdatePresenter mPresenter;
    private HomeKnowHistory mOldHistory;
    private int RESULT_CODE = 2002;

    @Override
    public int getLayoutId() {
        return R.layout.activity_know_update_default;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        if (intent != null) {
            know_id = intent.getLongExtra(ConstantsUtils.KNOW_ITEM_ID, 0);
        }
        mPresenter = new KnowUpdatePresenter(this);
        mPresenter.getKnowDefaultContent(know_id);
    }

    @Override
    public void initToolbar() {
        commonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CODE);
                finish();
            }
        });

        commonTitle.setText(getResources().getString(R.string.common_update_default));

        commonImg.setImageResource(R.drawable.common_update_normal);

        commonImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.isKnowUpdateDefaultEmpty();
            }
        });
    }

    public static void launch(Activity activity,int request_code,long know_id) {
        Intent intent = new Intent(activity, KnowUpdateDefaultActivity.class);
        intent.putExtra(ConstantsUtils.KNOW_ITEM_ID, know_id);
        activity.startActivityForResult(intent,request_code);
    }

    @Override
    public void showKnowDataFinish(HomeKnowContent content) {
        initData(content);
    }

    @Override
    public void showKnowDataError(int error, String message) {
        ToastUtils.ToastText(getContext(), "Error = " + error + ", Message = " + message);
    }

    private void initData(HomeKnowContent content) {
        addHistory(content);
        updateTitleEdit.setText(content.getTitle());
        updateSummaryEdit.setText(content.getSummary());
        updateShowEdit.setText(content.getShow());
        updateExplainEdit.setText(content.getExplain());
        updateExperienceEdit.setText(content.getExperience());
        updateHeedEdit.setText(content.getHeed());
    }

    private void addHistory(HomeKnowContent content) {
        mOldHistory = new HomeKnowHistory();
        mOldHistory.setDataId(know_id);
        mOldHistory.setTitle(content.getTitle());
        mOldHistory.setSummary(content.getSummary());
        mOldHistory.setExplain(content.getExplain());
        mOldHistory.setExperience(content.getExperience());
        mOldHistory.setHeed(content.getHeed());
        mOldHistory.setShow(content.getShow());
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
        ToastUtils.ToastText(getContext(),"Error = "+error+", Message = "+message);
    }

    @Override
    public void showKnowEqualsError(int error, String message) {
        ToastUtils.ToastText(getContext(),"Error = "+error+", Message = "+message);
    }

    @Override
    public boolean isKnowUpdateDefaultEquals() {
        return updateTitleEdit.getText().toString().equals(mOldHistory.getTitle())||
                updateSummaryEdit.getText().toString().equals(mOldHistory.getSummary())||
                updateShowEdit.getText().toString().equals(mOldHistory.getShow())||
                updateExplainEdit.getText().toString().equals(mOldHistory.getExplain())||
                updateExperienceEdit.getText().toString().equals(mOldHistory.getExperience())||
                updateHeedEdit.getText().toString().equals(mOldHistory.getHeed());
    }

    @Override
    public void saveKnowUpdateContent() {
        HomeKnowContent mNewContent = new HomeKnowContent();
        mNewContent.setId(know_id);
        mNewContent.setTitle(updateTitleEdit.getText().toString());
        mNewContent.setSummary(updateSummaryEdit.getText().toString());
        mNewContent.setShow(updateShowEdit.getText().toString());
        mNewContent.setExplain(updateExplainEdit.getText().toString());
        mNewContent.setExperience(updateExperienceEdit.getText().toString());
        mNewContent.setHeed(updateHeedEdit.getText().toString());
        mPresenter.saveKnowUpdateDefault(mOldHistory,mNewContent);
    }

    @Override
    public void showKnowSaveError(int error, String message) {
        ToastUtils.ToastText(getContext(),"Error = "+error+", Message = "+message);
    }

    @Override
    public void showKnowSaveFinish() {
        ToastUtils.ToastText(getContext(),"修改成功了正在退出!");
        Intent intent = new Intent();
        intent.putExtra(ConstantsUtils.ITEM_UPDATE_STATUS,1);
        setResult(RESULT_CODE,intent);
        finish();
    }
}

