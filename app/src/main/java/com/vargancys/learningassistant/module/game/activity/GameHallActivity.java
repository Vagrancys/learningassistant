package com.vargancys.learningassistant.module.game.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.model.game.bean.GameHallBean;
import com.vargancys.learningassistant.module.game.adapter.GameHallBannerSection;
import com.vargancys.learningassistant.module.game.adapter.GameHallItemSection;
import com.vargancys.learningassistant.module.game.adapter.GameHallRankSection;
import com.vargancys.learningassistant.module.game.view.GameHallView;
import com.vargancys.learningassistant.presenter.game.BaseGamePresenter;
import com.vargancys.learningassistant.utils.ToastUtils;
import com.vargancys.learningassistant.widget.section.SectionedRecyclerViewAdapter;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @author Vagrancy
 * @date 2020/7/6
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 关卡大厅模块
 */
public class GameHallActivity extends BaseActivity implements GameHallView {
    private static String TAG = "OverViewHallActivity";
    @BindView(R.id.common_title_data)
    TextView commonTitleData;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.fragment_content)
    TextView fragmentContent;
    @BindView(R.id.fragment_empty)
    LinearLayout fragmentEmpty;
    private BaseGamePresenter mPresenter;
    private SectionedRecyclerViewAdapter mAdapter;
    private AlertDialog.Builder mAlert;
    private long HallId = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_game_hall;
    }

    @Override
    public void initView() {
        mPresenter = new BaseGamePresenter<GameHallView>(this);
        mAdapter = new SectionedRecyclerViewAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
        fragmentContent.setText(getText(R.string.overview_hall_empty));
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                autoData();
            }
        });
        swipeRefresh.setColorSchemeResources(R.color.pink);
        initDialog();
    }

    public static void launch(Activity activity){
        Intent intent = new Intent(activity,GameHallActivity.class);
        activity.startActivity(intent);
    }

    private void initDialog(){
        mAlert = new AlertDialog.Builder(getContext())
                .setNegativeButton(R.string.common_determine_text, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.selectHallData(HallId);
                    }
                })
                .setPositiveButton(R.string.common_cancel_text, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
    }

    private void autoData(){
        swipeRefresh.setRefreshing(true);
        mPresenter.getGameHallData();
    }

    @Override
    public void initToolbar() {
        commonTitleData.setText(getText(R.string.overview_hall_toolbar));
    }

    @OnClick({R.id.common_back})
    public void onViewClicked(View itemView) {
        switch (itemView.getId()) {
            case R.id.common_back:
                finish();
                break;
        }
    }

    @Override
    public void getGameHallDataFail(int error, String message) {
        swipeRefresh.setRefreshing(false);
        ToastUtils.ToastText(getContext(), "Error =" + error + ",Message = " + message);
        recyclerView.setVisibility(View.GONE);
        fragmentEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void getGameHallDataSuccess(GameHallBean mBean) {
        swipeRefresh.setRefreshing(false);
        recyclerView.setVisibility(View.VISIBLE);
        fragmentEmpty.setVisibility(View.GONE);
        //1.轮播 最新消息
        //2.排行 厉害的要排一排 分类
        //3.展示 最需要曝光的展示 分类
        mAdapter.addSection(new GameHallBannerSection(getContext(),new Handler(),mBean.getBanner()));
        mAdapter.addSection(new GameHallRankSection(getContext(),getSupportFragmentManager()));
        mAdapter.addSection(new GameHallItemSection(getContext(),mBean.getHall()));
    }

    private void selectHallData(long id,String title){
        mAlert.setTitle(title);
        mAlert.setMessage("您是否要选择该("+title+")知识体系!");
        HallId = id;
        mAlert.show();
    }

    @Override
    public void selectHallDataFail(int error, String message) {
        ToastUtils.ToastText(getContext(),"Error ="+error+", Message ="+message);
    }

    @Override
    public void selectHallDataSuccess() {
        ToastUtils.ToastText(getContext(),R.string.overview_hall_select_success_text);
        finish();
    }
}
