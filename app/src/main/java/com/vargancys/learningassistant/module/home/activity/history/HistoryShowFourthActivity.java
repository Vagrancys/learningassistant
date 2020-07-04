package com.vargancys.learningassistant.module.home.activity.history;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.db.home.HomeKnowHistory;
import com.vargancys.learningassistant.db.home.HomeKnowHistoryFunction;
import com.vargancys.learningassistant.module.home.adapter.HomeHistoryFourthAdapter;
import com.vargancys.learningassistant.module.home.view.HistoryShowView;
import com.vargancys.learningassistant.presenter.home.HistoryShowPresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Vagrancy
 * @date 2020/3/6
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识历史显示第四级页面
 */
public class HistoryShowFourthActivity extends BaseActivity implements HistoryShowView {
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.insert_show_title)
    TextView insertShowTitle;
    @BindView(R.id.insert_show_summary)
    TextView insertShowSummary;
    @BindView(R.id.insert_show_count)
    TextView insertShowCount;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.show_empty_fourth)
    TextView showEmptyFourth;
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
    private HomeHistoryFourthAdapter mAdapter;
    private List<HomeKnowHistoryFunction> mFunction = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_know_show_fourth;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        if(intent !=null){
            historyId = intent.getLongExtra(ConstantsUtils.KNOW_HISTORY_ID,0);
        }
        mPresenter = new HistoryShowPresenter(this);
        init();
        mPresenter.getDefaultShowData(historyId);
    }

    private void init() {
        mAdapter = new HomeHistoryFourthAdapter(getContext(),mFunction);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
    }

    public static void launch(Activity activity, long history_id) {
        Intent intent = new Intent(activity, HistoryShowFourthActivity.class);
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
        int count = homeKnowHistory.getHomeKnowHistoryFunctions().size();
        if(count <= 0){
            showEmptyFourth.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }else{
            showEmptyFourth.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
        insertShowCount.setText(String.valueOf(homeKnowHistory.getHomeKnowHistoryFunctions().size()));
        mFunction.addAll(homeKnowHistory.getHomeKnowHistoryFunctions());
        commonTitle.setText(homeKnowHistory.getTitle());
        mAdapter.notifyDataSetChanged();
    }

    @OnClick({R.id.common_back})
    public void onViewClicked(View itemView){
        finish();
    }
}

