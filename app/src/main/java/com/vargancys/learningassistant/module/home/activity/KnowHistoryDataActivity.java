package com.vargancys.learningassistant.module.home.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.db.home.HomeKnowHistory;
import com.vargancys.learningassistant.module.home.activity.history.HistoryShowDefaultActivity;
import com.vargancys.learningassistant.module.home.adapter.HistoryDataAdapter;
import com.vargancys.learningassistant.module.home.view.BaseHistoryView;
import com.vargancys.learningassistant.persenter.home.KnowHistoryPresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/26
 * version:1.0
 */
public class KnowHistoryDataActivity extends BaseActivity implements BaseHistoryView {
    @BindView(R.id.common_back)
    ImageView commonBack;
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.common_img)
    ImageView commonImg;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private long know_id;
    private int know_level;
    private HistoryDataAdapter mHistoryAdapter;
    private KnowHistoryPresenter mPresenter;
    private List<HomeKnowHistory> mHistorys = new ArrayList<>();
    private Handler mHandler;
    @Override
    public void initView() {
        Intent intent = getIntent();
        if(intent != null){
            know_id = intent.getLongExtra(ConstantsUtils.KNOW_ITEM_ID,0);
            know_level = intent.getIntExtra(ConstantsUtils.KNOW_LEVEL_ID,1);
        }
        mHandler = new Handler();
        initAdapter();
        mPresenter = new KnowHistoryPresenter(this);
        mPresenter.getAllHistoryData(know_id);
        initListener();
    }

    private void initListener() {
        mHistoryAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                selectShowHistoryLevel(know_level,mHistorys.get(position).getId());
            }
        });
    }

    private void selectShowHistoryLevel(int know_level,long id) {
        switch (know_level){
            case 1:

                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            default:
                HistoryShowDefaultActivity.launch(KnowHistoryDataActivity.this,id);
                break;
        }
    }

    private void initAdapter() {
        mHistoryAdapter = new HistoryDataAdapter(getContext(),mHandler,mHistorys);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mHistoryAdapter);
    }

    @Override
    public void initToolbar() {
        commonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        commonTitle.setText(getResources().getString(R.string.common_history_title));

        commonImg.setVisibility(View.GONE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_history_data;
    }

    public static void launch(Activity activity, long know_id,int know_level) {
        Intent intent = new Intent(activity, KnowHistoryDataActivity.class);
        intent.putExtra(ConstantsUtils.KNOW_ITEM_ID, know_id);
        intent.putExtra(ConstantsUtils.KNOW_LEVEL_ID,know_level);
        activity.startActivity(intent);
    }

    @Override
    public void showHistoryDataFinish(List<HomeKnowHistory> histories) {
        mHistorys.addAll(histories);
        mHistoryAdapter.notifyDataSetChanged();
    }

}
