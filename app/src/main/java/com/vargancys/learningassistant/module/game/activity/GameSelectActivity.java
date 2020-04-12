package com.vargancys.learningassistant.module.game.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.vargancys.learningassistant.db.game.GameContent;
import com.vargancys.learningassistant.db.overview.OverViewListContent;
import com.vargancys.learningassistant.module.game.adapter.GameSelectAdapter;
import com.vargancys.learningassistant.module.game.view.SelectGameView;
import com.vargancys.learningassistant.presenter.game.BaseGamePresenter;
import com.vargancys.learningassistant.utils.CacheUtils;
import com.vargancys.learningassistant.utils.ConstantsUtils;
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
public class GameSelectActivity extends BaseActivity implements SelectGameView {
    @BindView(R.id.game_select)
    ImageView gameSelect;
    @BindView(R.id.game_sign)
    ImageView gameSign;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    private BaseGamePresenter mPresenter;
    private GameSelectAdapter mAdapter;
    private AlertDialog.Builder mAlertDialog;
    private String gameTitle;
    private List<OverViewListContent> mContents = new ArrayList<>();
    private long gameId = 0;
    @Override
    public int getLayoutId() {
        return R.layout.activity_game_select;
    }

    @Override
    public void initView() {
        mPresenter = new BaseGamePresenter(this);
        init();
        mPresenter.getGameSelectListData();
    }

    private void init() {
        mAdapter = new GameSelectAdapter(getContext(),mContents);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
        mAlertDialog = new AlertDialog.Builder(getContext());
        mAlertDialog.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mPresenter.saveGameSelectData(gameId);
                dialog.dismiss();
            }
        });
        mAlertDialog.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                gameId = 0;
                dialog.dismiss();
            }
        });
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                gameId = mContents.get(position).getId();
                gameTitle = mContents.get(position).getTitle();
                mAlertDialog.setTitle(mContents.get(position).getTitle());
                mAlertDialog.setMessage(mContents.get(position).getSummary());
                mAlertDialog.show();
            }
        });
        swipeRefresh.setRefreshing(true);
        swipeRefresh.setColorSchemeColors(getResources().getColor(R.color.pink));
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefresh.setRefreshing(true);
                mPresenter.getGameSelectListData();
            }
        });

    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, GameSelectActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void initToolbar() {
        gameSelect.setImageResource(R.drawable.common_back_normal);
        gameSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        gameSign.setVisibility(View.GONE);
    }

    @Override
    public void showGameSelectAllData(List<OverViewListContent> contents) {
        swipeRefresh.setRefreshing(false);
        mContents.clear();
        mContents.addAll(contents);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showGameSelectAllError(int error, String message) {
        swipeRefresh.setRefreshing(false);
        ToastUtils.ToastText(getContext(),"Error ="+error+",Message ="+message);
    }

    @Override
    public void tidyGameContent(OverViewListContent mContent) {
        GameContent gameContent = new GameContent();
        gameContent.setOverviewId(gameId);
        gameContent.setTitle(gameTitle);
        gameContent.setGame_title(gameTitle);
        gameContent.setDifficulty(mContent.getLevel());
        gameContent.setError(0);
        gameContent.setScore(mContent.getGrade());
        gameContent.setSubject(mContent.getNumber());
        mPresenter.saveGameContentData(gameContent);
    }

    @Override
    public void saveSelectGameError(int error, String message) {
        ToastUtils.ToastText(getContext(),"Error ="+error+"，Message ="+message);
    }

    @Override
    public void saveSelectGameFinish(long gameId) {
        ToastUtils.ToastText(getContext(),"选择成功!");
        setGameId(gameId);
        finish();
    }

    @Override
    public void isGameContentEmptyFinish(long gameId) {
        ToastUtils.ToastText(getContext(),"已经选择该关卡");
        setGameId(gameId);
    }

    private void setGameId(long gameId){
        CacheUtils.putLong(getContext(), ConstantsUtils.GAME_ID,gameId);
    }
}
