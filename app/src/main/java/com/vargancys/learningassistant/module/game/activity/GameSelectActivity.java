package com.vargancys.learningassistant.module.game.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.db.overview.OverViewListContent;
import com.vargancys.learningassistant.module.game.adapter.GameSelectAdapter;
import com.vargancys.learningassistant.module.game.view.SelectGameView;
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
public class GameSelectActivity extends BaseActivity implements SelectGameView {
    @BindView(R.id.game_select)
    ImageView gameSelect;
    @BindView(R.id.game_text)
    TextView gameText;
    @BindView(R.id.game_sign)
    ImageView gameSign;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private BaseGamePresenter mPresenter;
    private GameSelectAdapter mAdapter;
    private List<OverViewListContent> mContents = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_game_select;
    }

    @Override
    public void initView() {
        mPresenter = new BaseGamePresenter(this);
        init();

    }

    private void init() {
        mAdapter = new GameSelectAdapter(getContext(),mContents);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                ToastUtils.ToastText(getContext(),"点击了"+position);
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
}
