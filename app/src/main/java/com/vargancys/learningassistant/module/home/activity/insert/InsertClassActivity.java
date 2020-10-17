package com.vargancys.learningassistant.module.home.activity.insert;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.model.home.bean.ClassBean;
import com.vargancys.learningassistant.model.home.bean.ClassTreeBean;
import com.vargancys.learningassistant.model.home.bean.ClassTreeListBean;
import com.vargancys.learningassistant.module.home.adapter.InsertClassTreeAdapter;
import com.vargancys.learningassistant.module.home.view.InsertClassView;
import com.vargancys.learningassistant.presenter.home.ClassPresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ToastUtils;
import com.vargancys.learningassistant.widget.dialog.ClassHeaderDataDialog;
import com.vargancys.learningassistant.widget.dialog.ClassItemDataDialog;
import com.vargancys.learningassistant.widget.dialog.KnowLedgeDataDialog;

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
    @BindView(R.id.class_recycler)
    RecyclerView classRecycler;
    private ClassPresenter mPresenter;
    private int article_id;
    private ArrayList<ClassTreeBean> classTrees = new ArrayList<>();
    private InsertClassTreeAdapter mAdapter;
    private KnowLedgeDataDialog mDialog;
    private ClassBean mClass;
    private ClassHeaderDataDialog mHeaderDialog;
    private ClassItemDataDialog mItemDialog;

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
        initData();
    }

    private void initData() {
        ClassTreeBean mBean = new ClassTreeBean();
        mBean.setTree_id(0);
        mBean.setPosition(0);
        mBean.setCount(0);
        mBean.setType(2);
        classTrees.add(mBean);
        mAdapter.notifyDataSetChanged();
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
        mAdapter.setOnClickClassListener(new InsertClassTreeAdapter.OnClickClassListener() {
            @Override
            public void onAdd(int position) {
                mHeaderDialog.show();
            }

            @Override
            public void onItemUpdate(int position, int header_id, int item_id) {
                if(classTrees.get(position).getTree_id() == header_id){
                    List<ClassTreeBean.ClassTreeItemBean> mItem = classTrees.get(position).getItems();
                    for (ClassTreeBean.ClassTreeItemBean itemBean : mItem) {
                        if(itemBean.getTree_item_id()==item_id){
                            mItemDialog.setTitle(itemBean.getTitle());
                            mItemDialog.show();
                        }
                    }
                }
            }

            @Override
            public void onItemDelete(int position, int header_id, int item_id) {
                if(classTrees.get(position).getTree_id() == header_id){
                    List<ClassTreeBean.ClassTreeItemBean> mItem = classTrees.get(position).getItems();
                    int ItemLength = mItem.size();
                    for (int i = 0; i < ItemLength; i++) {
                        if(mItem.get(i).getTree_item_id() == item_id){
                            mItem.remove(i);
                            mAdapter.setTrees(classTrees);
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }

            @Override
            public void onHeaderAdd(int position, int header_id) {
                mItemDialog.setTree_id(position);
                mItemDialog.show();
            }

            @Override
            public void onHeaderUpdate(int position, int header_id) {
                if(classTrees.get(position).getTree_id() == header_id){
                    mHeaderDialog.setTitle(classTrees.get(position).getTitle());
                    mHeaderDialog.setLevel(classTrees.get(position).getLevel());
                    mHeaderDialog.setSummary(classTrees.get(position).getSummary());
                    mHeaderDialog.show();
                }
            }

            @Override
            public void onHeaderDelete(int position, int header_id) {
                if(classTrees.get(position).getTree_id() == header_id){
                    classTrees.remove(position);
                    mAdapter.setTrees(classTrees);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
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

        mHeaderDialog = new ClassHeaderDataDialog(this);
        mHeaderDialog.setOnClickCancelListener(() -> mHeaderDialog.cancel());
        mHeaderDialog.setOnClickDeterMineListener(((common, title, summary) -> {
            ClassTreeBean classBean = new ClassTreeBean();
            classBean.setCount(0);
            classBean.setType(1);
            classBean.setTitle(title);
            classBean.setPosition(0);
            classBean.setTree_id(0);
            classBean.setSummary(summary);
            classBean.setLevel(common);
            classTrees.add(classBean);
            mAdapter.setTrees(classTrees);
            mAdapter.notifyDataSetChanged();
        }));

        mItemDialog = new ClassItemDataDialog(this);
        mItemDialog.setOnClickCancelListener(()->mItemDialog.cancel());
        mItemDialog.setOnClickDeterMineListener(((position,common, title, summary) -> {
            ClassTreeBean.ClassTreeItemBean mItem = new ClassTreeBean.ClassTreeItemBean();
            mItem.setPosition(position);
            mItem.setTree_item_id(position);
            mItem.setTitle(title);
            classTrees.get(position).getItems().add(mItem);
            mAdapter.setTrees(classTrees);
            mAdapter.notifyDataSetChanged();
        }));
    }

    @Override
    public void isEmptySuccess() {
        mClass.setTrees(classTrees);
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

