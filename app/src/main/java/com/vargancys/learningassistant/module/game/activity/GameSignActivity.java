package com.vargancys.learningassistant.module.game.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
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
import com.vargancys.learningassistant.module.game.adapter.GameSignAdapter;
import com.vargancys.learningassistant.module.game.view.SignGameView;
import com.vargancys.learningassistant.presenter.game.BaseGamePresenter;
import com.vargancys.learningassistant.utils.ResourceUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Vagrancy
 * @date 2020/4/10
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 关卡签到页面
 */
public class GameSignActivity extends BaseActivity implements SignGameView {
    @BindView(R.id.common_back)
    ImageView commonBack;
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.common_img)
    ImageView commonImg;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.fragment_empty)
    LinearLayout fragmentEmpty;
    @BindView(R.id.fragment_content)
    TextView fragmentContent;
    private BaseGamePresenter mPresenter;
    private GameSignAdapter mAdapter;
    private Handler mHandler;
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

    @Override
    public void initToolbar() {

        commonTitle.setText(ResourceUtils.getString(getContext(),R.string.game_sign_title));

        commonImg.setImageResource(R.drawable.game_sign_add_normal);
    }

    private void initData() {
        mHandler = new Handler();
        swipeRefresh.setColorSchemeColors(ResourceUtils.getColor(getContext(),R.color.pink));
        swipeRefresh.setRefreshing(true);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefresh.setRefreshing(true);
                mPresenter.getGameSignAllData();
            }
        });
        mAdapter = new GameSignAdapter(getContext(), mSigns,mHandler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, GameSignActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void showGameSignAllData(List<GameSignContent> mSign) {
        swipeRefresh.setRefreshing(false);
        fragmentContent.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        mSigns.clear();
        mSigns.addAll(mSign);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showGameSignAllError(int error, String message) {
        ToastUtils.ToastText(getContext(),"Error ="+error+", Message ="+message);
        swipeRefresh.setRefreshing(false);
        fragmentContent.setText(ResourceUtils.getString(getContext(),R.string.fragment_sign_empty));
        fragmentEmpty.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @OnClick({R.id.common_back,R.id.common_img})
    public void onViewClicked(View itemView){
        switch (itemView.getId()){
            case R.id.common_back:
                finish();
                break;
            case R.id.common_img:
                GameSignAddActivity.launch(GameSignActivity.this);
                break;
        }
    }
}
