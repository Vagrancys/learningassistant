package com.vargancys.learningassistant.module.home.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseFragment;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.model.home.bean.HomeContentBean;
import com.vargancys.learningassistant.module.common.MainActivity;
import com.vargancys.learningassistant.module.home.activity.KnowShowDefaultActivity;
import com.vargancys.learningassistant.module.home.activity.KnowShowFifthActivity;
import com.vargancys.learningassistant.module.home.activity.KnowShowFirstActivity;
import com.vargancys.learningassistant.module.home.activity.KnowShowFourthActivity;
import com.vargancys.learningassistant.module.home.activity.KnowShowSecondActivity;
import com.vargancys.learningassistant.module.home.activity.KnowShowThirdActivity;
import com.vargancys.learningassistant.module.home.adapter.HomeContentAdapter;
import com.vargancys.learningassistant.module.home.view.HomeContentView;
import com.vargancys.learningassistant.persenter.home.HomeContentPresenter;
import com.vargancys.learningassistant.utils.JumpRouteUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/01
 * version:1.0
 */
public class HomeContentFragment extends BaseFragment implements HomeContentView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.content_menu)
    ImageView contentMenu;

    private HomeContentAdapter homeContentAdapter;
    private HomeContentPresenter homeContentPresenter;
    private List<?> mBean = new ArrayList<>();
    private JumpRouteUtils jumpRouteUtils;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_content;
    }

    @Override
    protected void initView() {
        homeContentPresenter = new HomeContentPresenter(this);
        //recyclerView.setAdapter();
        contentMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.ToastText(getContext(),"切换侧滑!");
                openDrawer();
            }
        });
        homeContentAdapter.setOnItemClickListener(new HomeContentItemClickListener());
        jumpRouteUtils = jumpRouteUtils.getJumpRouteUtils();
    }

    class HomeContentItemClickListener implements BaseRecyclerAdapter.OnItemClickListener{
        @Override
        public void OnItemClick(int position) {
            Sq
            HomeContentBean.ContentBean homeContentBean = (HomeContentBean.ContentBean) mBean.get(position);
            if(homeContentBean.isHave()){
                launchDemonstrateActivity(homeContentBean.getActivity());
            }else{
                launchShowActivity(homeContentBean.getItem_id(),homeContentBean.getLevel());
            }
        }
    }

    private void launchDemonstrateActivity(String activity) {
        if(!activity.isEmpty()){
            jumpRouteUtils.JumpActivity(getActivity(),activity);
        }else{
            ToastUtils.ToastText(getContext(),"不好意识！还没有完成该知识的演示!");
        }
    }

    private void launchShowActivity(int item_id,int level) {
        switch (level){
            case 0:
                KnowShowFirstActivity.launch(getActivity(),item_id);
                break;
            case 1:
                KnowShowSecondActivity.launch(getActivity(),item_id);
                break;
            case 2:
                KnowShowThirdActivity.launch(getActivity(),item_id);
                break;
            case 3:
                KnowShowFourthActivity.launch(getActivity(),item_id);
                break;
            case 4:
                KnowShowFifthActivity.launch(getActivity(),item_id);
                break;
            default:
                KnowShowDefaultActivity.launch(getActivity());
                break;
        }

    }

    private void openDrawer() {
        MainActivity mainActivity =(MainActivity) getContext();
        HomeFragment homeFragment = (HomeFragment) mainActivity.getFragment(0);
        homeFragment.openDrawer();
    }

    @Override
    public void initData() {
        super.initData();
        homeContentPresenter.getData();
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

    @Override
    public void showContentBean(List<?> bean) {
        mBean = bean;
        homeContentAdapter = new HomeContentAdapter(getContext(),bean);
        recyclerView.setAdapter(homeContentAdapter);
    }

    @Override
    public void showError(int error, String msg) {
        ToastUtils.ToastText(getContext(),"Error = "+error+" msg = "+msg);
    }
}
