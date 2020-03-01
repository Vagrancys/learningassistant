package com.vargancys.learningassistant.module.home.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/01
 * version:1.0
 */
public class HomeFragment extends BaseFragment {
    @BindView(R.id.fl_content)
    FrameLayout flContent;
    @BindView(R.id.fl_left)
    FrameLayout flLeft;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    private HomeContentFragment homeContent;
    private HomeLeftFragment homeLeft;
    private String HOME_CONTENT = "home_content";
    private String HOME_LEFT = "home_Left";

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        homeContent = new HomeContentFragment();
        homeLeft = new HomeLeftFragment();
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fl_content,homeContent,HOME_CONTENT);
        fragmentTransaction.replace(R.id.fl_left,homeLeft,HOME_LEFT);
        fragmentTransaction.commit();
    }

    public void openDrawer(){
        drawerLayout.openDrawer(Gravity.START);
    }
}
