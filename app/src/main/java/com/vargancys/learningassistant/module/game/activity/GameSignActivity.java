package com.vargancys.learningassistant.module.game.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.db.game.GameSignContent;
import com.vargancys.learningassistant.module.game.view.SignGameView;
import com.vargancys.learningassistant.presenter.game.BaseGamePresenter;
import com.vargancys.learningassistant.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/10
 * version:1.0
 */
public class GameSignActivity extends BaseActivity implements SignGameView {
    @BindView(R.id.common_back)
    ImageView commonBack;
    @BindView(R.id.common_title_data)
    TextView commonTitleData;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.fragment_empty)
    TextView fragmentEmpty;
    @BindView(R.id.fragment_content)
    LinearLayout fragmentContent;
    private BaseGamePresenter mPresenter;
    private GameSignAdapter mAdapter;
    private List<GameSignContent> mSigns = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_game_sign;
    }

    @Override
    public void initView() {
        mPresenter = new BaseGamePresenter(this);
        initData();
        mPresenter.getGameSignAllData();
    }

    private void initData() {
        swipeRefresh.setColorSchemeColors(getResources().getColor(R.color.pink));
        swipeRefresh.setRefreshing(true);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefresh.setRefreshing(true);
                mPresenter.getGameSignAllData();
            }
        });
        mAdapter = new GameSignAdapter(recyclerView, mSigns);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, GameSignActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void showGameSignAllData(List<GameSignContent> mSign) {
        fragmentContent.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        mSigns.clear();
        mSigns.addAll(mSign);
        mAdapter.notifityDataChange();
    }

    @Override
    public void showGameSignAllError(int error, String message) {
        ToastUtils.ToastText(getContext(),"Error ="+error+", Message ="+message);
        swipeRefresh.setRefreshing(false);
        fragmentEmpty.setText(getResources().getString(R.string.fragment_sign_empty));
        fragmentContent.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }
}
