package com.vargancys.learningassistant.module.overview.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.model.overview.bean.OverViewHallBean;
import com.vargancys.learningassistant.module.overview.adapter.OverViewHallBannerSection;
import com.vargancys.learningassistant.module.overview.adapter.OverViewHallItemSection;
import com.vargancys.learningassistant.module.overview.adapter.OverViewHallRankSection;
import com.vargancys.learningassistant.module.overview.view.OverViewHallView;
import com.vargancys.learningassistant.presenter.overview.BaseOverViewPresenter;
import com.vargancys.learningassistant.utils.ToastUtils;
import com.vargancys.learningassistant.widget.section.SectionedRecyclerViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Vagrancy
 * @date 2020/6/27
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识体系大厅模块
 */
public class OverViewHallActivity extends BaseActivity implements OverViewHallView {
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
    private BaseOverViewPresenter mPresenter;
    private SectionedRecyclerViewAdapter mAdapter;
    private AlertDialog.Builder mAlert;
    private long HallId = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_overview_hall;
    }

    @Override
    public void initView() {
        mPresenter = new BaseOverViewPresenter<OverViewHallView>(this);
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
        mPresenter.getOverViewHallData();
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
    public void getOverViewHallDataFail(int error, String message) {
        swipeRefresh.setRefreshing(false);
        ToastUtils.ToastText(getContext(), "Error =" + error + ",Message = " + message);
        recyclerView.setVisibility(View.GONE);
        fragmentEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void getOverViewHallDataSuccess(OverViewHallBean mBean) {
        swipeRefresh.setRefreshing(false);
        recyclerView.setVisibility(View.VISIBLE);
        fragmentEmpty.setVisibility(View.GONE);
        //1.轮播 最新消息
        //2.排行 厉害的要排一排 分类
        //3.展示 最需要曝光的展示 分类
        mAdapter.addSection(new OverViewHallBannerSection(getContext(),new Handler(),mBean.getBanner()));
        mAdapter.addSection(new OverViewHallRankSection(getContext(),getSupportFragmentManager()));
        mAdapter.addSection(new OverViewHallItemSection(getContext(),mBean.getHall()));
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

    public static void launch(Activity activity){
        Intent intent = new Intent(activity,OverViewHallActivity.class);
        activity.startActivity(intent);
    }
}
