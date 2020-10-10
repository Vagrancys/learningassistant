package com.vargancys.learningassistant.module.home.activity.insert;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.model.home.bean.ClassBean;
import com.vargancys.learningassistant.model.home.bean.ClassTreeBean;
import com.vargancys.learningassistant.module.home.adapter.InsertClassTreeAdapter;
import com.vargancys.learningassistant.module.home.view.InsertClassView;
import com.vargancys.learningassistant.presenter.home.ClassPresenter;
import com.vargancys.learningassistant.presenter.home.KnowInsertPresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ToastUtils;
import com.vargancys.learningassistant.widget.KnowLedgeDataDialog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    @BindView(R.id.class_recycler)
    RecyclerView classRecycler;
    private ClassPresenter mPresenter;
    private int article_id;
    private ArrayList<ClassTreeBean> classTrees = new ArrayList<>();
    private InsertClassTreeAdapter mAdapter;
    private KnowLedgeDataDialog mDialog;
    private ClassBean mClass;

    @Override
    public int getLayoutId() {
        return R.layout.activity_knowledge_insert_class;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        if (intent != null) {
            article_id = intent.getIntExtra(ConstantsUtils.KNOWLEDGE_ARTICLE_ID, 0);
        }
        mPresenter = new ClassPresenter(this);
        mClass = new ClassBean();
        mClass.setFather_id(article_id);
        initRecyclerView();
        initListener();
        initDialog();
    }

    private void initRecyclerView() {
        mAdapter = new InsertClassTreeAdapter(getContext(), classTrees);
        classRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        classRecycler.setAdapter(mAdapter);
    }

    @Override
    public void initToolbar() {
        commonTitle.setText(getText(R.string.class_insert_toolbar));
    }

    private void initListener() {
    }

    private void initDialog() {
        mDialog = new KnowLedgeDataDialog(this);
        mDialog.setOnClickCancelListener(() -> mDialog.cancel());
        mDialog.setOnClickDeterMineListener((common, title, summary, explain) -> {
            mClass.setLevel(common);
            mClass.setTitle(title);
            mClass.setSummary(summary);
            mClass.setExplain(explain);
            mDialog.dismiss();
        });
    }

    @Override
    public void isEmptySuccess() {
        mPresenter.add(mClass);
    }

    @Override
    public boolean isEmpty() {
        return classTrees.size() > 0;
    }

    @Override
    public void isEmptyFail() {
        ToastUtils.ToastText(getContext(),R.string.class_empty);
    }

    public static void launch(Activity activity, int article_id) {
        Intent intent = new Intent(activity, InsertClassActivity.class);
        intent.putExtra(ConstantsUtils.KNOWLEDGE_ARTICLE_ID, article_id);
        activity.startActivity(intent);
    }

    private void initEmpty() {
        classTrees.clear();
        mAdapter.notifyDataSetChanged();
    }

    @OnClick({R.id.common_back, R.id.common_save, R.id.common_add, R.id.common_data})
    public void onViewClicked(View itemView) {
        switch (itemView.getId()) {
            case R.id.common_back:
                finish();
                break;
            case R.id.common_save:
                mPresenter.isDataEmpty();
                break;
            case R.id.common_data:
                if(!mDialog.isEdit()){
                    mDialog.setTitle(mClass.getTitle());
                    mDialog.setLevel(mClass.getLevel());
                    mDialog.setExplain(mClass.getExplain());
                    mDialog.setSummary(mClass.getSummary());
                }
                mDialog.show();
                break;
        }
    }

    @Override
    public void onSuccess() {
        ToastUtils.ToastText(getContext(),R.string.class_success);
        initEmpty();
    }

    @Override
    public void onFail() {
        ToastUtils.ToastText(getContext(),R.string.common_fail);
    }

    @Override
    public void onError(String message) {
        ToastUtils.ToastText(getContext(),R.string.common_error);
    }
}

