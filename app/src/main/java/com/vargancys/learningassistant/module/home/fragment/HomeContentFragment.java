package com.vargancys.learningassistant.module.home.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseFragment;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.db.home.HomeKnowItem;
import com.vargancys.learningassistant.module.common.help.HelpContentActivity;
import com.vargancys.learningassistant.module.common.MainActivity;
import com.vargancys.learningassistant.module.home.activity.AddKnowActivity;
import com.vargancys.learningassistant.module.home.activity.insert.KnowInsertDefaultActivity;
import com.vargancys.learningassistant.module.home.activity.insert.KnowInsertFirstActivity;
import com.vargancys.learningassistant.module.home.activity.insert.KnowInsertSecondActivity;
import com.vargancys.learningassistant.module.home.activity.show.KnowShowDefaultActivity;
import com.vargancys.learningassistant.module.home.activity.show.KnowShowFifthActivity;
import com.vargancys.learningassistant.module.home.activity.show.KnowShowFirstActivity;
import com.vargancys.learningassistant.module.home.activity.show.KnowShowFourthActivity;
import com.vargancys.learningassistant.module.home.activity.show.KnowShowSecondActivity;
import com.vargancys.learningassistant.module.home.activity.show.KnowShowThirdActivity;
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
public class HomeContentFragment extends BaseFragment implements HomeContentView,View.OnClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.content_menu)
    ImageView contentMenu;
    @BindView(R.id.fragment_empty)
    LinearLayout fragmentEmpty;
    @BindView(R.id.fragment_content)
    TextView fragmentContent;
    @BindView(R.id.add_menu)
    ImageView addMenu;
    @BindView(R.id.help_menu)
    ImageView helpMenu;

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
        homeContentAdapter = new HomeContentAdapter(getContext(),mBean);
        recyclerView.setAdapter(homeContentAdapter);
        initListener();
    }

    private void initListener() {
        contentMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.ToastText(getContext(),"切换侧滑!");
                openDrawer();
            }
        });
        homeContentAdapter.setOnItemClickListener(new HomeContentItemClickListener());

        homeContentAdapter.setOnItemLongClickListener(new HomeContentItemLongClickListener());

        jumpRouteUtils = new JumpRouteUtils().getJumpRouteUtils();
        swipeRefresh.setColorSchemeColors(getResources().getColor(R.color.pink));
        swipeRefresh.setOnRefreshListener(new HomeContentOnRefreshListener());
        addMenu.setOnClickListener(this);

        helpMenu.setOnClickListener(this);
    }

    class HomeContentItemLongClickListener implements BaseRecyclerAdapter.OnItemLongClickListener{
        @Override
        public void OnItemLongClick(int position) {
            final HomeKnowItem homeKnowItem = (HomeKnowItem) mBean.get(position);
            if(homeKnowItem.isOfficial()){
                ToastUtils.ToastText(getContext(),"官方的知识不能删除!您可以选择其他的知识删除");
            }else{
                final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setTitle(homeKnowItem.getTitle());
                alert.setMessage(homeKnowItem.getSummary());
                alert.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        homeContentPresenter.deleteKnowData(homeKnowItem.getItem_id());
                        dialog.dismiss();
                    }
                });
                alert.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alert.show();
            }
        }
    }

    class HomeContentOnRefreshListener implements SwipeRefreshLayout.OnRefreshListener{

        @Override
        public void onRefresh() {
            swipeRefresh.setRefreshing(true);
            homeContentPresenter.getData();
        }
    }

    class HomeContentItemClickListener implements BaseRecyclerAdapter.OnItemClickListener{
        @Override
        public void OnItemClick(int position) {
            HomeKnowItem homeKnowItem = (HomeKnowItem) mBean.get(position);
            if(homeKnowItem.isCreateClass()){
                homeContentPresenter.updateCount(position);
                if(homeKnowItem.isHave()){
                    launchDemonstrateActivity(homeKnowItem.getActivity());
                }else{
                    launchShowActivity(homeKnowItem.getItem_id(),homeKnowItem.getLevel());
                }
            }else{
                if(homeKnowItem.isHave()){
                    ToastUtils.ToastText(getContext(),"这需要官方来创建!个人不能创建!");
                }else{
                    //ToastUtils.ToastText(getContext(),"还没有编程到这里!");
                    launchInsertActivity(homeKnowItem.getItem_id(),homeKnowItem.getLevel());
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_menu:
                AddKnowActivity.launch(getActivity());
                break;
            case R.id.help_menu:
                HelpContentActivity.launch(getActivity());
                break;
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
            case 1:
                KnowShowFirstActivity.launch(getActivity(),item_id);
                break;
            case 2:
                KnowShowSecondActivity.launch(getActivity(),item_id);
                break;
            case 3:
                KnowShowThirdActivity.launch(getActivity(),item_id);
                break;
            case 4:
                KnowShowFourthActivity.launch(getActivity(),item_id);
                break;
            case 5:
                KnowShowFifthActivity.launch(getActivity(),item_id);
                break;
            default:
                KnowShowDefaultActivity.launch(getActivity());
                break;
        }
    }

    private void launchInsertActivity(int item_id,int level) {
        switch (level){
            case 1:
                KnowInsertFirstActivity.launch(getActivity(),item_id);
                break;
            case 2:
                KnowInsertSecondActivity.launch(getActivity(),item_id);
                break;
            case 3:
                KnowShowThirdActivity.launch(getActivity(),item_id);
                break;
            case 4:
                KnowShowFourthActivity.launch(getActivity(),item_id);
                break;
            case 5:
                KnowShowFifthActivity.launch(getActivity(),item_id);
                break;
            default:
                KnowInsertDefaultActivity.launch(getActivity(),item_id);
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
        swipeRefresh.setRefreshing(true);
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
        homeContentAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(int error, String msg) {
        swipeRefresh.setRefreshing(false);
        ToastUtils.ToastText(getContext(),"Error = "+error+" msg = "+msg);
        fragmentContent.setText(getText(R.string.fragment_error));
        fragmentEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmpty() {
        swipeRefresh.setRefreshing(false);
        recyclerView.setVisibility(View.VISIBLE);
        fragmentEmpty.setVisibility(View.GONE);
    }

    @Override
    public void showEmpty() {
        swipeRefresh.setRefreshing(false);
        recyclerView.setVisibility(View.GONE);
        fragmentContent.setText(getText(R.string.fragment_content));
        fragmentEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void deleteFinish(int item_id) {
        ToastUtils.ToastText(getContext(),"知识项删除成功了!");
        homeContentAdapter.notifyItemRemoved(item_id);
    }

    @Override
    public void deleteError(int error, String msg) {
        ToastUtils.ToastText(getContext(),"Error = "+error+", msg = "+msg);
    }
}
