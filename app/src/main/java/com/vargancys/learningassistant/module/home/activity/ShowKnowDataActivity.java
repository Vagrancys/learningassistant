package com.vargancys.learningassistant.module.home.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.db.home.HomeKnowCommend;
import com.vargancys.learningassistant.db.home.HomeKnowHistory;
import com.vargancys.learningassistant.module.home.adapter.HomeKnowCommendAdapter;
import com.vargancys.learningassistant.module.home.adapter.HomeKnowHistoryAdapter;
import com.vargancys.learningassistant.module.home.view.KnowDataView;
import com.vargancys.learningassistant.persenter.home.KnowDataPresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/25
 * version:1.0
 */
public class ShowKnowDataActivity extends BaseActivity implements KnowDataView {
    @BindView(R.id.common_back)
    ImageView commonBack;
    @BindView(R.id.common_title_data)
    TextView commonTitleData;
    @BindView(R.id.know_data_title)
    TextView knowDataTitle;
    @BindView(R.id.know_data_level)
    ImageView knowDataLevel;
    @BindView(R.id.know_data_count)
    TextView knowDataCount;
    @BindView(R.id.know_data_master)
    TextView knowDataMaster;
    @BindView(R.id.know_data_time)
    TextView knowDataTime;
    @BindView(R.id.know_data_update)
    ImageView knowDataUpdate;
    @BindView(R.id.know_data_delete)
    ImageView knowDataDelete;
    @BindView(R.id.know_data_setting)
    ImageView knowDataSetting;
    @BindView(R.id.know_data_history_text)
    TextView knowDataHistoryText;
    @BindView(R.id.know_data_history_count)
    TextView knowDataHistoryCount;
    @BindView(R.id.history_recycler)
    RecyclerView historyRecycler;
    @BindView(R.id.know_data_commend)
    TextView knowDataCommend;
    @BindView(R.id.commend_recycler)
    RecyclerView commendRecycler;
    @BindView(R.id.know_data_commend_edit)
    EditText knowDataCommendEdit;
    @BindView(R.id.know_data_commend_send)
    ImageView knowDataCommendSend;
    private KnowDataPresenter mPresenter;
    private int know_id;
    private ArrayList<HomeKnowCommend> mCommend = new ArrayList<>();
    private HomeKnowCommendAdapter mCommendAdapter;
    private HomeKnowHistoryAdapter mHirstoryAdapter;
    private ArrayList<HomeKnowHistory> mHistory = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_show_know_data;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        if(intent !=null){
            know_id = intent.getIntExtra(ConstantsUtils.KNOW_ITEM_ID,0);
        }
        init();
        mPresenter = new KnowDataPresenter(this);
        mPresenter.getShowData();
    }

    private void init(){
        mCommendAdapter = new HomeKnowCommendAdapter(getContext(),mCommend);
        commendRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        commendRecycler.setAdapter(mCommendAdapter);
        mHirstoryAdapter = new HomeKnowHistoryAdapter(getContext(),mHistory);
        historyRecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        historyRecycler.setAdapter(mHirstoryAdapter);
    }

    public static void launch(Activity activity,int know_id){
        Intent intent = new Intent(activity,ShowKnowDataActivity.class);
        intent.putExtra(ConstantsUtils.KNOW_ITEM_ID,know_id);
        activity.startActivity(intent);
    }
}
