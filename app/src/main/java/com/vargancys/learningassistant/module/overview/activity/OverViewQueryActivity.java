package com.vargancys.learningassistant.module.overview.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.bean.overview.OverViewListContent;
import com.vargancys.learningassistant.module.overview.adapter.OverViewSearchAdapter;
import com.vargancys.learningassistant.module.overview.view.OverViewQueryView;
import com.vargancys.learningassistant.presenter.overview.BaseOverViewPresenter;
import com.vargancys.learningassistant.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Vagrancy
 * @date 2020/6/23
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识体系查询页面
 */
public class OverViewQueryActivity extends BaseActivity implements OverViewQueryView {
    @BindView(R.id.search_edit)
    EditText searchEdit;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    private BaseOverViewPresenter mPresenter;
    private OverViewSearchAdapter mAdapter;
    private List<OverViewListContent> mBean = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_overview_query;
    }

    @Override
    public void initView() {
        mPresenter = new BaseOverViewPresenter(this);

        mAdapter = new OverViewSearchAdapter(getContext(), mBean);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
        swipeRefresh.setColorSchemeResources(R.color.pink);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                autoData();
            }
        });
    }

    private void autoData(){
        if(swipeRefresh.isRefreshing()){
            return;
        }
        String query = searchEdit.getText().toString();
        mPresenter.isQueryDataEmpty(query);
        swipeRefresh.setRefreshing(true);
    }

    @Override
    public boolean isDataEmpty(String query) {
        return query.isEmpty();
    }

    @Override
    public void queryDataEmpty() {
        swipeRefresh.setRefreshing(false);
        ToastUtils.ToastText(getContext(), R.string.overview_query_empty_text);
    }

    @Override
    public void queryDataError(int error, String message) {
        ToastUtils.ToastText(getContext(), "Error =" + error + ", Message =" + message);
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void queryDataFinish(List<OverViewListContent> bean) {
        swipeRefresh.setRefreshing(false);
        mBean.clear();
        mBean.addAll(bean);
        mAdapter.notifyDataSetChanged();
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, OverViewQueryActivity.class);
        activity.startActivity(intent);
    }

    @OnClick({R.id.back_menu, R.id.search_menu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_menu:
                finish();
                break;
            case R.id.search_menu:
                autoData();
                break;
        }
    }
}
