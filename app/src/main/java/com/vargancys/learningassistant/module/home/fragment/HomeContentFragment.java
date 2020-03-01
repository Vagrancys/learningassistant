package com.vargancys.learningassistant.module.home.fragment;

import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseFragment;
import com.vargancys.learningassistant.module.common.MainActivity;
import com.vargancys.learningassistant.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/01
 * version:1.0
 */
public class HomeContentFragment extends BaseFragment {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.content_menu)
    ImageView contentMenu;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_content;
    }

    @Override
    protected void initView() {
        contentMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.ToastText(getContext(),"切换侧滑!");
                openDrawer();
            }
        });
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
    }

    private void openDrawer() {
        MainActivity mainActivity =(MainActivity) getContext();
        HomeFragment homeFragment = (HomeFragment) mainActivity.getFragment(0);
        homeFragment.openDrawer();
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.know_add:
                ToastUtils.ToastText(getContext(),"添加数据!");
                break;
            case R.id.know_help:
                ToastUtils.ToastText(getContext(),"获取帮助!");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.home_content_menu,menu);
    }

}
