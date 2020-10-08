package com.vargancys.learningassistant.module.home.activity.insert;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.bean.home.HomeKnowFunction;
import com.vargancys.learningassistant.module.home.adapter.HomeKnowFourthAdapter;
import com.vargancys.learningassistant.module.home.view.InsertClassView;
import com.vargancys.learningassistant.presenter.home.KnowInsertPresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ToastUtils;
import com.vargancys.learningassistant.widget.KnowLedgeDataDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/09
 * version:1.0
 * 函数型知识添加页面
 */
public class InsertClassActivity extends BaseActivity implements InsertClassView {
    @BindView(R.id.common_title)
    TextView commonTitle;
    private KnowInsertPresenter mPresenter;
    private int article_id;
    private HomeKnowFourthAdapter mAdapter;
    private KnowLedgeDataDialog mDialog;

    @Override
    public int getLayoutId() {
        return R.layout.activity_knowledge_insert_class;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        if(intent != null){
            know_item_id = intent.getIntExtra(ConstantsUtils.KNOWLEDGE_ARTICLE_ID,0);
        }
        mPresenter = new KnowInsertPresenter(this);
        initRecyclerView();
        initListener();
        initDialog();
    }

    private void initRecyclerView() {
        mAdapter = new HomeKnowFourthAdapter(getContext(),homeKnowFunctions);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initToolbar() {
        commonTitle.setText(getText(R.string.class_insert_toolbar));
    }

    private void initListener() {
        mAdapter.setOnItemLongClickListener(position -> {
            AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
            alert.setTitle(homeKnowFunctions.get(position).getTitle());
            alert.setMessage(homeKnowFunctions.get(position).getSummary());
            alert.setPositiveButton("取消", (dialog, which) -> dialog.dismiss());
            alert.setNegativeButton("确定", (dialog, which) -> {
                homeKnowFunctions.remove(position);
                mAdapter.notifyItemRemoved(position);
                dialog.dismiss();
            });
        });
    }

    private void initDialog(){
        mDialog = new KnowLedgeDataDialog(this);
        mDialog.setOnClickCancelListener(() -> mDialog.cancel());
        mDialog.setOnClickDeterMineListener((common, title, summary, explain) -> {

        });
    }

    public static void launch(Activity activity, int article_id){
        Intent intent = new Intent(activity, InsertClassActivity.class);
        intent.putExtra(ConstantsUtils.KNOWLEDGE_ARTICLE_ID,article_id);
        activity.startActivity(intent);
    }

    private void initEmpty() {
        insertTitleEdit.setText("");
        insertSummaryEdit.setText("");
        insertHeedEdit.setText("");
        insertExperienceEdit.setText("");
        homeKnowFunctions.clear();
        mAdapter.notifyDataSetChanged();
        recyclerView.setVisibility(View.GONE);
        showHintFourth.setVisibility(View.VISIBLE);
        insertShowCount.setVisibility(View.GONE);
    }

    @Override
    public void showFunctionWindow() {
        mDialog.show();
    }

    @OnClick({R.id.common_back,R.id.common_save,R.id.common_add,R.id.common_data})
    public void onViewClicked(View itemView){
        switch (itemView.getId()){
            case R.id.common_back:
                finish();
                break;
            case R.id.common_save:
                mPresenter.isFourthEmpty();
                break;
            case R.id.common_data:
                mPresenter.showFourthFunctionWindow();
                break;
        }
    }
}

