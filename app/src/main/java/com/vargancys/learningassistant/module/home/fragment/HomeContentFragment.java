package com.vargancys.learningassistant.module.home.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseFragment;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.db.home.KnowLedgeBean;
import com.vargancys.learningassistant.module.common.help.HelpContentActivity;
import com.vargancys.learningassistant.module.common.MainActivity;
import com.vargancys.learningassistant.module.home.activity.AddKnowLedgeActivity;
import com.vargancys.learningassistant.module.home.activity.KnowSearchActivity;
import com.vargancys.learningassistant.module.home.activity.insert.KnowInsertDefaultActivity;
import com.vargancys.learningassistant.module.home.activity.insert.KnowInsertFifthActivity;
import com.vargancys.learningassistant.module.home.activity.insert.KnowInsertFirstActivity;
import com.vargancys.learningassistant.module.home.activity.insert.KnowInsertFourthActivity;
import com.vargancys.learningassistant.module.home.activity.insert.KnowInsertSecondActivity;
import com.vargancys.learningassistant.module.home.activity.insert.KnowInsertThirdActivity;
import com.vargancys.learningassistant.module.home.activity.show.KnowShowDefaultActivity;
import com.vargancys.learningassistant.module.home.activity.show.KnowShowFifthActivity;
import com.vargancys.learningassistant.module.home.activity.show.KnowShowFirstActivity;
import com.vargancys.learningassistant.module.home.activity.show.KnowShowFourthActivity;
import com.vargancys.learningassistant.module.home.activity.show.KnowShowSecondActivity;
import com.vargancys.learningassistant.module.home.activity.show.KnowShowThirdActivity;
import com.vargancys.learningassistant.module.home.adapter.HomeContentAdapter;
import com.vargancys.learningassistant.module.home.view.HomeContentView;
import com.vargancys.learningassistant.presenter.home.HomeContentPresenter;
import com.vargancys.learningassistant.utils.JumpRouteUtils;
import com.vargancys.learningassistant.utils.ResourceUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/01
 * version:1.0
 * 首页中心碎片
 */
public class HomeContentFragment extends BaseFragment implements HomeContentView,View.OnClickListener {
    private static final String TAG = "HomeContentFragment";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.fragment_empty)
    LinearLayout fragmentEmpty;
    @BindView(R.id.fragment_content)
    TextView fragmentContent;
    @BindView(R.id.know_class_layout)
    FrameLayout knowClassLayout;

    private HomeContentAdapter homeContentAdapter;
    private HomeContentPresenter homeContentPresenter;
    private List<KnowLedgeBean> mBean = new ArrayList<>();
    private JumpRouteUtils jumpRouteUtils;
    private HomeClassFragment mClassFragment;
    private Animation mUpScaleAnim;
    private Animation mDownScaleAnim;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_content;
    }

    @Override
    protected void initView() {
        homeContentPresenter = new HomeContentPresenter(this);
        //recyclerView.setAdapter();
        homeContentAdapter = new HomeContentAdapter(getContext(),mBean);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(homeContentAdapter);
        initListener();
        initClass();
    }

    @Override
    public void initData() {
        swipeRefresh.setRefreshing(true);
        homeContentPresenter.getKnowledge(1,10);
    }

    private void initClass() {
        mUpScaleAnim = AnimationUtils.loadAnimation(getContext(),R.anim.common_scale_top_anim);
        mDownScaleAnim = AnimationUtils.loadAnimation(getContext(),R.anim.common_scale_buttom_anim);
        mClassFragment = HomeClassFragment.getInstance();
        FragmentTransaction fragmentTransaction =getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.know_class_layout,mClassFragment);
        fragmentTransaction.show(mClassFragment);
        fragmentTransaction.commit();
    }

    private void initListener() {
        homeContentAdapter.setOnItemClickListener(new HomeContentItemClickListener());

        homeContentAdapter.setOnItemLongClickListener(new HomeContentItemLongClickListener());

        jumpRouteUtils = new JumpRouteUtils().getJumpRouteUtils();
        swipeRefresh.setColorSchemeColors(ResourceUtils.getColor(getContext(),R.color.pink));
        swipeRefresh.setOnRefreshListener(new HomeContentOnRefreshListener());

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }

    class HomeContentItemLongClickListener implements BaseRecyclerAdapter.OnItemLongClickListener{
        @Override
        public void OnItemLongClick(int position) {
            final KnowLedgeBean homeKnowItem = mBean.get(position);
            if(homeKnowItem.getOfficial()){
                ToastUtils.ToastText(getContext(),"官方的知识不能删除!您可以选择其他的知识删除");
            }else{
                final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setTitle(homeKnowItem.getTitle());
                alert.setMessage("您确定要删除"+homeKnowItem.getTitle()+"吗?");
                alert.setNegativeButton(R.string.common_determine_text, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        homeContentPresenter.deleteKnowData(homeKnowItem.getId());
                        dialog.dismiss();
                    }
                });
                alert.setPositiveButton(R.string.common_cancel_text, new DialogInterface.OnClickListener() {
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
            homeContentPresenter.getKnowledge(1,10);
        }
    }

    class HomeContentItemClickListener implements BaseRecyclerAdapter.OnItemClickListener{
        @Override
        public void OnItemClick(int position) {
            KnowLedgeBean homeKnowItem =mBean.get(position);
            if(homeKnowItem.getCreateClass()){
                homeContentPresenter.updateCount(homeKnowItem.getId());
                if(homeKnowItem.getHave()){
                    launchDemonstrateActivity(homeKnowItem.getActivity());
                }else{
                    launchShowActivity(homeKnowItem.getId(),homeKnowItem.getLevel());
                }
            }else{
                if(homeKnowItem.getHave()){
                    ToastUtils.ToastText(getContext(),"这需要官方来创建!个人不能创建!");
                }else{
                    launchInsertActivity(homeKnowItem.getId(),homeKnowItem.getLevel());
                }
            }
        }
    }

    @OnClick({R.id.search_menu,R.id.fragment_empty,R.id.recyclerView,R.id.class_menu,R.id.help_menu,
            R.id.add_menu,R.id.content_menu})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_menu:
                AddKnowLedgeActivity.launch(getActivity());
                break;
            case R.id.help_menu:
                HelpContentActivity.launch(getActivity());
                break;
            case R.id.class_menu:
                if(knowClassLayout.getVisibility() == View.VISIBLE){
                    hideKnowClass();
                    homeContentPresenter.getSelectContentData(mClassFragment.getLanguage(),mClassFragment.getLevel(),
                            mClassFragment.getShow(),mClassFragment.getMaster());
                }else{
                    knowClassLayout.setVisibility(View.VISIBLE);
                    knowClassLayout.startAnimation(mDownScaleAnim);
                }
                break;
            case R.id.recyclerView:
            case R.id.fragment_empty:
                hideKnowClass();
                break;
            case R.id.search_menu:
                KnowSearchActivity.launch(getActivity());
                break;
            case R.id.content_menu:
                ToastUtils.ToastText(getContext(),"切换侧滑!");
                openDrawer();
                break;
        }
    }

    private void hideKnowClass() {
        if(knowClassLayout.getVisibility() == View.VISIBLE){
            knowClassLayout.startAnimation(mUpScaleAnim);
            knowClassLayout.setVisibility(View.GONE);
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
                KnowShowDefaultActivity.launch(getActivity(),item_id);
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
                KnowInsertThirdActivity.launch(getActivity(),item_id);
                break;
            case 4:
                KnowInsertFourthActivity.launch(getActivity(),item_id);
                break;
            case 5:
                KnowInsertFifthActivity.launch(getActivity(),item_id);
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
    public void showContentBean(List<KnowLedgeBean> bean) {
        mBean.clear();
        mBean.addAll(bean);
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
    public void deleteFinish(long item_id) {
        ToastUtils.ToastText(getContext(),"知识项删除成功了!");
        homeContentAdapter.notifyItemRemoved((int)item_id);
        homeContentAdapter.notifyItemChanged((int)item_id,mBean.size()-1);
    }

    @Override
    public void onResume() {
        super.onResume();
        swipeRefresh.setRefreshing(true);
        homeContentPresenter.getData();
    }

    @Override
    public void deleteError(int error, String msg) {
        ToastUtils.ToastText(getContext(),"Error = "+error+", msg = "+msg);
    }

    @Override
    public void showRefreshContentBean(List<KnowLedgeBean> bean) {
        mBean.clear();
        mBean.addAll(bean);
        homeContentAdapter.notifyDataSetChanged();
    }
}
