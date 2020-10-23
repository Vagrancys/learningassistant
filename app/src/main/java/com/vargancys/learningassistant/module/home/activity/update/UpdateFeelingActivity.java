package com.vargancys.learningassistant.module.home.activity.update;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.bean.home.HomeKnowContent;
import com.vargancys.learningassistant.bean.home.HomeKnowHistory;
import com.vargancys.learningassistant.presenter.home.FeelingPresenter;
import com.vargancys.learningassistant.presenter.home.KnowUpdatePresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/09
 * version:1.0
 * 知识更新五级页面
 */
public class UpdateFeelingActivity extends BaseActivity{
    private static final String TAG = "KnowUpdateFifthActivity";
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
    private FeelingPresenter mPresenter;
    private int article_Id;
    private int father_Id;
    private int RESULT_CODE = 2002;

    @Override
    public int getLayoutId() {
        return R.layout.activity_know_update_fifth;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        if(intent != null){
            article_Id = intent.getIntExtra(ConstantsUtils.KNOWLEDGE_ARTICLE_ID,0);
            father_Id = intent.getIntExtra(ConstantsUtils.KNOWLEDGE_FATHER_ID,0);
        }
//        mPresenter = new KnowUpdatePresenter(this);
        mPresenter.query(article_Id);
    }

    @Override
    public void initToolbar() {
        commonTitle.setText(getText(R.string.common_update_fifth));

        commonImg.setImageResource(R.drawable.common_update_normal);

    }

    public static void launch(Activity activity,int REQUEST_CODE, int content_id,int data_id){
        Intent intent = new Intent(activity, UpdateFeelingActivity.class);
        intent.putExtra(ConstantsUtils.KNOWLEDGE_ARTICLE_ID,content_id);
        intent.putExtra(ConstantsUtils.KNOWLEDGE_FATHER_ID,data_id);
        activity.startActivityForResult(intent,REQUEST_CODE);
    }

    @OnClick({R.id.common_back,R.id.common_img})
    public void onViewClicked(View itemView){
        switch (itemView.getId()){
            case R.id.common_back:
                setResult(RESULT_CODE);
                finish();
                break;
            case R.id.common_img:
                mPresenter.isUpdateEmpty();
                break;
        }
    }
}


