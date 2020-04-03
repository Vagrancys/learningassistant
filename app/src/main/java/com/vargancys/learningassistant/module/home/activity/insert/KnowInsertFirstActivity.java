package com.vargancys.learningassistant.module.home.activity.insert;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.module.home.view.KnowInsertFirstView;
import com.vargancys.learningassistant.presenter.home.KnowInsertPresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import butterknife.BindView;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/09
 * version:1.0
 */
public class KnowInsertFirstActivity extends BaseActivity implements KnowInsertFirstView {
    @BindView(R.id.common_back)
    ImageView commonBack;
    @BindView(R.id.common_img)
    ImageView commonImg;
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.insert_title_edit)
    EditText insertTitleEdit;
    @BindView(R.id.insert_summary_edit)
    EditText insertSummaryEdit;
    @BindView(R.id.insert_show_edit)
    EditText insertShowEdit;
    @BindView(R.id.insert_explain_edit)
    EditText insertExplainEdit;
    @BindView(R.id.insert_heed_edit)
    EditText insertHeedEdit;
    @BindView(R.id.insert_experience_edit)
    EditText insertExperienceEdit;
    private KnowInsertPresenter mPresenter;
    private int know_item_id;

    @Override
    public int getLayoutId() {
        return R.layout.activity_know_insert_first;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        if(intent != null){
            know_item_id = intent.getIntExtra(ConstantsUtils.KNOW_ITEM_ID,0);
            Log.e("idddd","id="+know_item_id);
        }
        mPresenter = new KnowInsertPresenter(this);
        initListener();
    }

    @Override
    public void initToolbar() {
        commonTitle.setText(getResources().getString(R.string.common_first));
    }

    private void initListener() {
        commonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        commonImg.setImageResource(R.drawable.commend_complete_selector);

        commonImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.isFirstEmpty();
            }
        });
    }

    @Override
    public boolean isFirstEmpty() {
        return insertTitleEdit.getText().toString().isEmpty()
                &&insertSummaryEdit.getText().toString().isEmpty()
                &&insertShowEdit.getText().toString().isEmpty()
                &&insertExplainEdit.getText().toString().isEmpty()
                &&insertHeedEdit.getText().toString().isEmpty()
                &&insertExperienceEdit.getText().toString().isEmpty();
    }

    @Override
    public void isFirstEmptyError(int error, String msg) {
        ToastUtils.ToastText(getContext(),"Error = "+error+",Msg = "+msg+"请填满每个知识项!");
    }

    @Override
    public void isFirstEqualsItem() {
        mPresenter.isEqualsFirstItem(insertTitleEdit.getText().toString());
    }

    @Override
    public void saveFirstKnowItem() {
        mPresenter.saveKnowFirstItem(know_item_id,insertTitleEdit.getText().toString(),
                insertSummaryEdit.getText().toString(),
                insertShowEdit.getText().toString(),
                insertExplainEdit.getText().toString(),
                insertHeedEdit.getText().toString(),
                insertExperienceEdit.getText().toString());
    }

    @Override
    public void isFirstEqualsError(int error, String msg) {
        ToastUtils.ToastText(getContext(),"Error = "+error+",Msg = "+msg+"该知识已经添加了!请重新输入!");
    }

    public static void launch(Activity activity, int know_id){
        Intent intent = new Intent(activity,KnowInsertFirstActivity.class);
        intent.putExtra(ConstantsUtils.KNOW_ITEM_ID,know_id);
        activity.startActivity(intent);
    }

    @Override
    public void saveFirstItemError(int error, String msg) {
        ToastUtils.ToastText(getContext(),"Error = "+error+", Msg = "+msg);
    }

    @Override
    public void saveFirstItemFinish() {
        ToastUtils.ToastText(getContext(),"保存成功了哦!");
        initEmpty();
        finish();
    }

    private void initEmpty() {
        insertTitleEdit.setText("");
        insertSummaryEdit.setText("");
        insertShowEdit.setText("");
        insertExplainEdit.setText("");
        insertHeedEdit.setText("");
        insertExperienceEdit.setText("");
    }
}
