package com.vargancys.learningassistant.module.home.activity.history;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.db.home.HomeKnowContent;
import com.vargancys.learningassistant.db.home.HomeKnowFunction;
import com.vargancys.learningassistant.db.home.HomeKnowHistory;
import com.vargancys.learningassistant.db.home.HomeKnowHistoryFunction;
import com.vargancys.learningassistant.module.home.adapter.HomeHistorySecondAdapter;
import com.vargancys.learningassistant.module.home.adapter.HomeKnowShowSecondAdapter;
import com.vargancys.learningassistant.module.home.view.HistoryShowView;
import com.vargancys.learningassistant.module.home.view.KnowShowView;
import com.vargancys.learningassistant.persenter.home.HistoryShowPresenter;
import com.vargancys.learningassistant.persenter.home.KnowShowPresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/06
 * version:1.0
 */
public class HistoryShowSecondActivity extends BaseActivity implements HistoryShowView {
    private static String TAG = "HistoryShowSecondActivity";
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

    private HistoryShowPresenter mPresenter;
    private long historyId;
    private HomeHistorySecondAdapter mAdapter;
    private List<HomeKnowHistoryFunction> mFunction = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_history_show_second;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        if(intent !=null){
            historyId = intent.getLongExtra(ConstantsUtils.KNOW_HISTORY_ID,0);
        }
        init();
        mPresenter = new HistoryShowPresenter(this);
        mPresenter.getDefaultShowData(historyId);

    }

    private void init() {
        mAdapter = new HomeHistorySecondAdapter(getContext(),mFunction);
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


        commonImg.setVisibility(View.GONE);
    }

    public static void launch(Activity activity,long history_id) {
        Intent intent = new Intent(activity, HistoryShowSecondActivity.class);
        intent.putExtra(ConstantsUtils.KNOW_HISTORY_ID, history_id);
        activity.startActivity(intent);
    }

    @Override
    public void showContentError(int error, String msg) {
        ToastUtils.ToastText(getContext(),"Error = "+error+"msg ="+msg);
        scrollView.setVisibility(View.GONE);
        includeKnowEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void showContentFinish(HomeKnowHistory homeKnowHistory) {
        scrollView.setVisibility(View.VISIBLE);
        includeKnowEmpty.setVisibility(View.GONE);
        initData(homeKnowHistory);
    }

    private void initData(HomeKnowHistory homeKnowHistory) {
        insertShowTitle.setText(homeKnowHistory.getTitle());
        insertShowSummary.setText(homeKnowHistory.getSummary());
        insertShowHeed.setText(homeKnowHistory.getHeed());
        insertShowExperience.setText(homeKnowHistory.getExperience());
        Log.e(TAG,"id ="+homeKnowHistory.getId()+"size ="+homeKnowHistory.getHomeKnowHistoryFunctions().size());
        int count = homeKnowHistory.getHomeKnowHistoryFunctions().size();
        if(count <= 0){
            showEmptySecond.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }else{
            showEmptySecond.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
        insertShowCount.setText(String.valueOf(homeKnowHistory.getHomeKnowHistoryFunctions().size()));
        mFunction.addAll(homeKnowHistory.getHomeKnowHistoryFunctions());
        commonTitle.setText(homeKnowHistory.getTitle());
        mAdapter.notifyDataSetChanged();
    }


}
