package com.vargancys.learningassistant.module.mine.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.db.home.KnowLedgeBean;
import com.vargancys.learningassistant.module.home.adapter.HomeContentAdapter;
import com.vargancys.learningassistant.module.mine.view.KnowLedgeItemView;
import com.vargancys.learningassistant.presenter.mine.BaseMinePresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ResourceUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author Vagrancy
 * @date 2020/5/26
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 各语言知识展示
 */
public class KnowLedgeItemActivity extends BaseActivity implements KnowLedgeItemView {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    private BaseMinePresenter mPresenter;
    private HomeContentAdapter mAdapter;
    private List<KnowLedgeBean> mItems = new ArrayList<>();
    private int type = 1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_knowledge_item;
    }

    @Override
    public void initView() {
        type = getIntent().getIntExtra(ConstantsUtils.KNOW_TYPE_ID,0);
        mPresenter = new BaseMinePresenter(this);
        initListener();
    }

    private void initListener(){
        swipeRefresh.setColorSchemeColors(ResourceUtils.getColor(getContext(),R.color.pink));
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                autoRefresh();
            }
        });
        mAdapter = new HomeContentAdapter(getContext(),mItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
    }

    private void autoRefresh(){
        swipeRefresh.setRefreshing(true);
        mPresenter.getHomeKnowData(type);
    }

    @Override
    public void loadKnowLedge(List<KnowLedgeBean> mItem) {
        swipeRefresh.setRefreshing(false);
        mItems.addAll(mItem);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadKnowLedgeError(int error, String message) {
        swipeRefresh.setRefreshing(false);
        ToastUtils.ToastText(getContext(),"Error ="+error+", Message ="+message);
    }

    public static void launch(Activity activity,int type){
        Intent intent = new Intent(activity,KnowLedgeItemActivity.class);
        intent.putExtra(ConstantsUtils.KNOW_TYPE_ID,type);
        activity.startActivity(intent);
    }
}
