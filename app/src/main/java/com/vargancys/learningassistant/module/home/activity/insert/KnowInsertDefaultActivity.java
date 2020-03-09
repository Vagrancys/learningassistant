package com.vargancys.learningassistant.module.home.activity.insert;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.module.home.view.KnowInsertDefaultView;
import com.vargancys.learningassistant.persenter.home.KnowInsertPresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/09
 * version:1.0
 */
public class KnowInsertDefaultActivity extends BaseActivity implements KnowInsertDefaultView {
    @BindView(R.id.common_back)
    ImageView commonBack;
    @BindView(R.id.common_img)
    ImageView commonImg;
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
        return R.layout.activity_know_insert_default;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        if(intent != null){
            know_item_id = intent.getIntExtra(ConstantsUtils.KNOW_ITEM_ID,1);
        }
        mPresenter = new KnowInsertPresenter(this);
        initListener();
    }

    private void initListener() {
        commonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        commonImg.setBackgroundResource(R.drawable.commend_complete_selector);

        commonImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.isDefaultEmpty();
            }
        });
    }

    @Override
    public boolean isDefaultEmpty() {
        return insertTitleEdit.getText().toString().isEmpty()
                &&insertSummaryEdit.getText().toString().isEmpty()
                &&insertShowEdit.getText().toString().isEmpty()
                &&insertExplainEdit.getText().toString().isEmpty()
                &&insertHeedEdit.getText().toString().isEmpty()
                &&insertExperienceEdit.getText().toString().isEmpty();
    }

    @Override
    public void isDefaultEmptyError(int error, String msg) {
        ToastUtils.ToastText(getContext(),"Error = "+error+",Msg = "+msg+"请填满每个知识项!");
    }

    @Override
    public void isDefaultEqualsItem() {
        mPresenter.isEqualsDefaultItem(insertTitleEdit.getText().toString());
    }

    @Override
    public void saveDefaultKnowItem() {
        mPresenter.saveKnowDefaultItem(know_item_id,insertTitleEdit.getText().toString(),
                insertSummaryEdit.getText().toString(),
                insertShowEdit.getText().toString(),
                insertExplainEdit.getText().toString(),
                insertHeedEdit.getText().toString(),
                insertExperienceEdit.getText().toString());
    }

    @Override
    public void isDefaultEqualsError(int error, String msg) {
        ToastUtils.ToastText(getContext(),"Error = "+error+",Msg = "+msg+"该知识已经添加了!请重新输入!");
    }

    public static void launch(Activity activity,int know_id){
        Intent intent = new Intent(activity,KnowInsertDefaultActivity.class);
        intent.putExtra(ConstantsUtils.KNOW_ITEM_ID,know_id);
        activity.startActivity(intent);
    }

    @Override
    public void saveDefaultItemError(int error, String msg) {
        ToastUtils.ToastText(getContext(),"Error = "+error+", Msg = "+msg);
    }

    @Override
    public void saveDefaultItemFinish() {
        ToastUtils.ToastText(getContext(),"保存成功了哦!");
        initEmpty();
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
