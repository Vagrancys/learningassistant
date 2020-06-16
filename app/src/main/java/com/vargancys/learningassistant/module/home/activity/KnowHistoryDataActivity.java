package com.vargancys.learningassistant.module.home.activity;

import android.app.Activity;
import android.content.Intent;
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
import com.vargancys.learningassistant.module.home.activity.history.HistoryShowFifthActivity;
import com.vargancys.learningassistant.module.home.activity.history.HistoryShowFirstActivity;
import com.vargancys.learningassistant.module.home.activity.history.HistoryShowFourthActivity;
import com.vargancys.learningassistant.module.home.activity.history.HistoryShowSecondActivity;
import com.vargancys.learningassistant.module.home.activity.history.HistoryShowThirdActivity;
import com.vargancys.learningassistant.module.home.adapter.HistoryDataAdapter;
import com.vargancys.learningassistant.module.home.view.BaseHistoryView;
import com.vargancys.learningassistant.presenter.home.KnowHistoryPresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ResourceUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/26
 * version:1.0
 * 知识历史数据页面
 */
public class KnowHistoryDataActivity extends BaseActivity implements BaseHistoryView {
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private long dataId;
    private int know_level;
    private HistoryDataAdapter mHistoryAdapter;
    private KnowHistoryPresenter mPresenter;
    private List<HomeKnowHistory> mHistorys = new ArrayList<>();
    private Handler mHandler;
    @Override
    public void initView() {
        Intent intent = getIntent();
        if(intent != null){
            dataId = intent.getLongExtra(ConstantsUtils.KNOW_DATA_ID,0);
            know_level = intent.getIntExtra(ConstantsUtils.KNOW_LEVEL_ID,1);
        }
        mHandler = new Handler();
        initAdapter();
        mPresenter = new KnowHistoryPresenter(this);
        mPresenter.getAllHistoryData(dataId);
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
                HistoryShowFirstActivity.launch(KnowHistoryDataActivity.this,id);
                break;
            case 2:
                HistoryShowSecondActivity.launch(KnowHistoryDataActivity.this,id);
                break;
            case 3:
                HistoryShowThirdActivity.launch(KnowHistoryDataActivity.this,id);
                break;
            case 4:
                HistoryShowFourthActivity.launch(KnowHistoryDataActivity.this,id);
                break;
            case 5:
                HistoryShowFifthActivity.launch(KnowHistoryDataActivity.this,id);
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
        commonTitle.setText(getText(R.string.common_history_title));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_history_data;
    }

    public static void launch(Activity activity, long data_id,int know_level) {
        Intent intent = new Intent(activity, KnowHistoryDataActivity.class);
        intent.putExtra(ConstantsUtils.KNOW_DATA_ID, data_id);
        intent.putExtra(ConstantsUtils.KNOW_LEVEL_ID,know_level);
        activity.startActivity(intent);
    }

    @Override
    public void showHistoryDataFinish(List<HomeKnowHistory> histories) {
        mHistorys.addAll(histories);
        mHistoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void showHistoryDataError(int error, String message) {
        ToastUtils.ToastText(getContext(),"Error = "+error+",Message ="+message);
    }

    @OnClick({R.id.common_back})
    public void onViewClicked(View itemView){
        finish();
    }
}
