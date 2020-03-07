package com.vargancys.learningassistant.module.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.module.common.adapter.HelpContentAdapter;
import com.vargancys.learningassistant.module.common.view.HelpContentView;
import com.vargancys.learningassistant.persenter.common.HelpContentPresenter;
import com.vargancys.learningassistant.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/07
 * version:1.0
 * 帮助中心页
 */
public class HelpContentActivity extends BaseActivity implements HelpContentView {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.fragment_content)
    TextView fragmentContent;
    @BindView(R.id.fragment_empty)
    LinearLayout fragmentEmpty;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.common_add)
    ImageView commonAdd;
    @BindView(R.id.common_back)
    ImageView commonBack;

    private HelpContentPresenter helpContentPresenter;
    private HelpContentAdapter helpContentAdapter;
    @Override
    public int getLayoutId() {
        return R.layout.activity_help_content;
    }

    @Override
    public void initView() {
        helpContentPresenter = new HelpContentPresenter(this);
        swipeRefresh.setColorSchemeColors(getResources().getColor(R.color.pink));
        swipeRefresh.setOnRefreshListener(new HelpContentOnRefreshListener());

        helpContentAdapter.setOnItemClickListener(new HelpContentOnItemClickListener());
    }

    class HelpContentOnItemClickListener implements BaseRecyclerAdapter.OnItemClickListener{
        @Override
        public void OnItemClick(int position) {
            HelpSummaryActivity.launch(HelpContentActivity.this,position);
        }
    }

    class HelpContentOnRefreshListener implements SwipeRefreshLayout.OnRefreshListener{
        @Override
        public void onRefresh() {
            swipeRefresh.setRefreshing(true);
            helpContentPresenter.getAllBean();
        }
    }

    @Override
    public void initToolbar() {
        super.initToolbar();
        commonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        commonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HelpAddActivity.launch(HelpContentActivity.this);
            }
        });
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, HelpContentActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void showContentBean(List<?> bean) {
        helpContentAdapter = new HelpContentAdapter(getContext(),bean);
        recyclerView.setAdapter(helpContentAdapter);
    }

    @Override
    public void showError(int error, String msg) {
        swipeRefresh.setRefreshing(false);
        fragmentContent.setText(getText(R.string.help_error_text));
        ToastUtils.ToastText(getContext(),"Error = "+error+",Msg = "+msg);
    }

    @Override
    public void hideEmpty() {
        recyclerView.setVisibility(View.VISIBLE);
        fragmentEmpty.setVisibility(View.GONE);
    }

    @Override
    public void showEmpty() {
        recyclerView.setVisibility(View.GONE);
        fragmentContent.setText(getText(R.string.help_empty_text));
        fragmentEmpty.setVisibility(View.VISIBLE);
    }


}
