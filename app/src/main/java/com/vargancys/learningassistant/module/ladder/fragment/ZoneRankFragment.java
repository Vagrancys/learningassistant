package com.vargancys.learningassistant.module.ladder.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseFragment;
import com.vargancys.learningassistant.bean.ladder.LadderRankDataBean;
import com.vargancys.learningassistant.module.ladder.adapter.ZoneRankDataAdapter;
import com.vargancys.learningassistant.module.ladder.view.LadderZoneRankView;
import com.vargancys.learningassistant.presenter.ladder.BaseLadderPresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ResourceUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author Vagrancy
 * @date 2020/5/14
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 排行分区页面
 */
public class ZoneRankFragment extends BaseFragment implements LadderZoneRankView {
    private static String TAG = "ZoneRankFragment";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    private BaseLadderPresenter mPresenter;
    private int zoneId;

    public static ZoneRankFragment newInstance(int position) {
        ZoneRankFragment mFragment = new ZoneRankFragment();
        Bundle mBundle = new Bundle();
        mBundle.putInt(ConstantsUtils.ZONE_RANK_POSITION, position);
        mFragment.setArguments(mBundle);
        return mFragment;
    }
    private ZoneRankDataAdapter mAdapter;
    private List<LadderRankDataBean> mBean = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.fragment_zone_rank;
    }

    @Override
    protected void initView() {
        if(getArguments() != null){
            zoneId = getArguments().getInt(ConstantsUtils.ZONE_RANK_POSITION,0);
        }
        mPresenter = new BaseLadderPresenter(this);
        swipeRefresh.setColorSchemeColors(ResourceUtils.getColor(getContext(),R.color.pink));
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getLadderZoneRankData(zoneId);
            }
        });

        mAdapter = new ZoneRankDataAdapter(getContext(),mBean);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
        swipeRefresh.setRefreshing(true);
        mPresenter.getLadderZoneRankData(zoneId);
    }

    @Override
    public void showZoneRankDataFinish(List<LadderRankDataBean> bean) {
        swipeRefresh.setRefreshing(false);
        mBean = bean;
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showZoneRankDataError(int error, String message) {
        swipeRefresh.setRefreshing(false);
        ToastUtils.ToastText(getContext(),"Error ="+error+",Message ="+message);
    }
}
