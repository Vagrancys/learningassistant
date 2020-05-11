package com.vargancys.learningassistant.module.ladder.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.db.ladder.LadderHelpBean;
import com.vargancys.learningassistant.module.ladder.adapter.LadderHelpAdapter;
import com.vargancys.learningassistant.module.ladder.adapter.LadderHelpView;
import com.vargancys.learningassistant.presenter.ladder.BaseLadderPresenter;
import com.vargancys.learningassistant.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Vagrancy
 * @date 2020/5/7
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 天梯帮助中心
 */
public class LadderHelpActivity extends BaseActivity implements LadderHelpView {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.common_back)
    ImageView commonBack;
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.common_img)
    ImageView commonImg;

    private List<LadderHelpBean> mBean = new ArrayList<>();
    private BaseLadderPresenter mPresenter;
    private LadderHelpAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_ladder_help;
    }

    @Override
    public void initView() {
        mPresenter = new BaseLadderPresenter(this);
        initData();
        mPresenter.getLadderHelpAllData();
    }

    private void initData(){
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefresh.setRefreshing(true);
                mPresenter.getLadderHelpAllData();
            }
        });
        mAdapter = new LadderHelpAdapter(getContext(),mBean);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                LadderHelpDetailsActivity.launch(LadderHelpActivity.this,mBean.get(position).getId());
            }
        });
    }

    @Override
    public void initToolbar() {
        commonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        commonImg.setVisibility(View.GONE);
        commonTitle.setText(getResources().getString(R.string.ladder_help_title_text));
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, LadderHelpActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void showAllDataFinish(List<LadderHelpBean> bean) {
        mBean = bean;
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showAllDataError(int error, String message) {
        ToastUtils.ToastText(getContext(),"Error ="+error+", Message =" +message);
    }
}
