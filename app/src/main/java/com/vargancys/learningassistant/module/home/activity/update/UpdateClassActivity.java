package com.vargancys.learningassistant.module.home.activity.update;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.model.home.bean.ClassBean;
import com.vargancys.learningassistant.model.home.bean.ClassTreeBean;
import com.vargancys.learningassistant.module.home.adapter.CommonClassTreeAdapter;
import com.vargancys.learningassistant.module.home.view.BaseKnowLedgeUpdateView;
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
 * 更新函数知识页面
 */
public class UpdateClassActivity extends BaseActivity implements BaseKnowLedgeUpdateView {
    private String TAG = "UpdateClassActivity";
    @BindView(R.id.common_img)
    ImageView commonImg;
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.update_title_edit)
    EditText updateTitleEdit;
    @BindView(R.id.update_summary_edit)
    EditText updateSummaryEdit;
    @BindView(R.id.update_show_add)
    ImageView updateShowAdd;
    @BindView(R.id.update_show_count)
    TextView updateShowCount;
    @BindView(R.id.update_heed_edit)
    EditText updateHeedEdit;
    @BindView(R.id.update_experience_edit)
    EditText updateExperienceEdit;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.show_hint_fourth)
    TextView showHintFourth;
    private ClassPresenter mPresenter;
    private ArrayList<ClassTreeBean> classTrees = new ArrayList<>();
    private ClassBean mClass;
    private ClassHeaderDataDialog mHeaderDialog;
    private ClassItemDataDialog mItemDialog;
    private KnowLedgeDataDialog mDialog;
    private int fatherId;
    private int articleId;
    private CommonClassTreeAdapter mAdapter;
    private int RESULT_CODE = 2002;

    @Override
    public int getLayoutId() {
        return R.layout.activity_know_update_fourth;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        if(intent != null){
            fatherId = intent.getIntExtra(ConstantsUtils.KNOWLEDGE_FATHER_ID,0);
            articleId = intent.getIntExtra(ConstantsUtils.KNOWLEDGE_ARTICLE_ID,0);
        }
        mPresenter = new ClassPresenter(this);
        initRecyclerView();
        initListener();
        initDialog();
        mPresenter.query(articleId);
    }

    private void initListener() {
        mAdapter.setOnClickClassListener(new CommonClassTreeAdapter.OnClickClassListener() {
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
        mAdapter = new CommonClassTreeAdapter(getContext(),classTrees);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initToolbar() {
        commonTitle.setText(getText(R.string.class_update_toolbar));
        commonImg.setImageResource(R.drawable.common_update_normal);
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

    public static void launch(Activity activity,int REQUEST_CODE, int article_id,int father_id){
        Intent intent = new Intent(activity, UpdateClassActivity.class);
        intent.putExtra(ConstantsUtils.KNOWLEDGE_FATHER_ID,father_id);
        intent.putExtra(ConstantsUtils.KNOWLEDGE_ARTICLE_ID,article_id);
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

    @Override
    public boolean isPass() {
        return classTrees.size() > 0;
    }

    @Override
    public void isPassSuccess() {
        mClass.setTrees(classTrees);
        mPresenter.update(mClass);
    }

    @Override
    public void isPassFail() {
        ToastUtils.ToastText(getContext(),R.string.class_empty);
    }

    @Override
    public void onUpdateSuccess() {
        ToastUtils.ToastText(getContext(),R.string.book_update_success);
    }

    @Override
    public void onUpdateFail() {
        ToastUtils.ToastText(getContext(),R.string.book_update_fail);
    }

    @Override
    public void onSuccess(Object object) {
        mClass = (ClassBean) object;
        classTrees.addAll(mClass.getTrees());
        initData();
    }

    @Override
    public void onError(String message) {
        ToastUtils.ToastText(getContext(),R.string.common_error);
    }

    @Override
    public void onFail() {
        ToastUtils.ToastText(getContext(),R.string.common_fail);
    }
}

