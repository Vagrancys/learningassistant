package com.vargancys.learningassistant.module.home.activity.show;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.db.home.HomeKnowContent;
import com.vargancys.learningassistant.db.home.HomeKnowFunction;
import com.vargancys.learningassistant.module.home.adapter.HomeKnowShowSecondAdapter;
import com.vargancys.learningassistant.module.home.view.KnowShowView;
import com.vargancys.learningassistant.persenter.home.KnowShowPresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/06
 * version:1.0
 */
public class KnowShowSecondActivity extends BaseActivity implements KnowShowView {
    @BindView(R.id.common_back)
    ImageView commonBack;
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.common_img)
    ImageView commonImg;
    @BindView(R.id.insert_show_title)
    TextView insertShowTitle;
    @BindView(R.id.insert_show_summary)
    TextView insertShowSummary;
    @BindView(R.id.insert_show_count)
    TextView insertShowCount;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.show_empty_second)
    TextView showEmptySecond;
    @BindView(R.id.insert_show_heed)
    TextView insertShowHeed;
    @BindView(R.id.insert_show_experience)
    TextView insertShowExperience;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.include_know_empty)
    LinearLayout includeKnowEmpty;

    private KnowShowPresenter mPresenter;
    private int item_id;
    private HomeKnowShowSecondAdapter mAdapter;
    private List<HomeKnowFunction> mFunction = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_know_show_second;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        if(intent !=null){
            item_id = intent.getIntExtra(ConstantsUtils.KNOW_ITEM_ID,0);
        }

        mPresenter = new KnowShowPresenter(this);
        mPresenter.getDefaultShowData(item_id);
        init();
    }

    private void init() {
        mAdapter = new HomeKnowShowSecondAdapter(getContext(),mFunction);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initToolbar() {
        commonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public static void launch(Activity activity, int item_id) {
        Intent intent = new Intent(activity, KnowShowSecondActivity.class);
        intent.putExtra(ConstantsUtils.ITEM_ID, item_id);
        activity.startActivity(intent);
    }

    @Override
    public void showContentError(int error, String msg) {
        ToastUtils.ToastText(getContext(),"Error = "+error+"msg ="+msg);
        scrollView.setVisibility(View.GONE);
        includeKnowEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void showContentFinish(HomeKnowContent homeKnowContent) {
        scrollView.setVisibility(View.VISIBLE);
        includeKnowEmpty.setVisibility(View.GONE);
        initData(homeKnowContent);
    }

    private void initData(HomeKnowContent homeKnowContent) {
        insertShowTitle.setText(homeKnowContent.getTitle());
        insertShowSummary.setText(homeKnowContent.getSummary());
        insertShowHeed.setText(homeKnowContent.getHeed());
        insertShowExperience.setText(homeKnowContent.getExperience());
        int count = homeKnowContent.getHomeKnowFunctions().size();
        if(count <= 0){
            showEmptySecond.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }else{
            showEmptySecond.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
        insertShowCount.setText(homeKnowContent.getHomeKnowFunctions().size());
        mFunction = homeKnowContent.getHomeKnowFunctions();
        commonTitle.setText(homeKnowContent.getTitle());
        mAdapter.notifyDataSetChanged();
    }


}