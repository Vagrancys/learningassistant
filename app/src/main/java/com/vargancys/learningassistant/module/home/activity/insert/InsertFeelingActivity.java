package com.vargancys.learningassistant.module.home.activity.insert;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.model.home.bean.FeelingBean;
import com.vargancys.learningassistant.module.home.adapter.FeelingItemAdapter;
import com.vargancys.learningassistant.module.home.view.InsertFeelingView;
import com.vargancys.learningassistant.presenter.home.FeelingPresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ToastUtils;
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
 * 知识添加五级页面
 */
public class InsertFeelingActivity extends BaseActivity implements InsertFeelingView {
    @BindView(R.id.common_img)
    ImageView commonImg;
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private FeelingPresenter mPresenter;
    private int article_id;
    private FeelingBean mFeeling;
    private KnowLedgeDataDialog mDialog;
    private FeelingItemAdapter mAdapter;
    private List<FeelingBean.FeelingItemBean> mItem = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_knowledge_insert_feeling;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        if(intent != null){
            article_id = intent.getIntExtra(ConstantsUtils.KNOWLEDGE_ARTICLE_ID,0);
        }
        initData();
        initDialog();
        mPresenter = new FeelingPresenter(this);
    }

    private void initData() {

    }

    private void initDialog() {
        mDialog = new KnowLedgeDataDialog(this);
        mDialog.setOnClickCancelListener(() -> mDialog.cancel());
        mDialog.setOnClickDeterMineListener((common, title, summary, explain) -> {
            mFeeling.setLevel(common);
            mFeeling.setTitle(title);
            mFeeling.setSummary(summary);
            mFeeling.setExplain(explain);
            mDialog.dismiss();
        });

    }

    @Override
    public void initToolbar() {
        commonTitle.setText(getText(R.string.feeling_insert_toolbar));
        commonImg.setImageResource(R.drawable.comment_complete_selector);
    }

    public static void launch(Activity activity, int know_id){
        Intent intent = new Intent(activity, InsertFeelingActivity.class);
        intent.putExtra(ConstantsUtils.KNOWLEDGE_ARTICLE_ID,know_id);
        activity.startActivity(intent);
    }

    @OnClick({R.id.common_back,R.id.common_img})
    public void onViewClicked(View itemView){
        switch (itemView.getId()){
            case R.id.common_back:
                finish();
                break;
            case R.id.common_img:
                mPresenter.isDataEmpty();
                break;
        }
    }


    @Override
    public void isEmptySuccess() {
        mPresenter.add(mFeeling);
    }

    @Override
    public boolean isEmpty() {
        return mItem.size() > 0;
    }

    @Override
    public void isEmptyFail() {
        ToastUtils.ToastText(getContext(),R.string.feeling_empty);
    }
}


