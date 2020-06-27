package com.vargancys.learningassistant.module.overview.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseFragment;
import com.vargancys.learningassistant.db.common.KnowListBean;
import com.vargancys.learningassistant.db.overview.OverViewListItem;
import com.vargancys.learningassistant.module.overview.activity.OverViewInformationActivity;
import com.vargancys.learningassistant.module.overview.activity.OverViewSelectActivity;
import com.vargancys.learningassistant.module.overview.adapter.SimpleTreeAdapter;
import com.vargancys.learningassistant.module.overview.view.OverViewContentView;
import com.vargancys.learningassistant.presenter.overview.OverViewContentPresenter;
import com.vargancys.learningassistant.utils.CacheUtils;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ResourceUtils;
import com.vargancys.learningassistant.utils.ToastUtils;
import com.vargancys.learningassistant.widget.TreeDirectory.TreeListViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Vagrancy
 * @date 2020/4/1
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识体系碎片页面
 */
public class OverViewFragment extends BaseFragment implements OverViewContentView {
    private static String TAG = "OverViewFragment";
    @BindView(R.id.overview_select)
    ImageView overviewSelect;
    @BindView(R.id.overview_information)
    ImageView overviewInformation;
    @BindView(R.id.overview_title)
    TextView overviewTitle;
    @BindView(R.id.overview_listview)
    ListView overviewListView;
    @BindView(R.id.overview_swipe)
    SwipeRefreshLayout overViewSwipe;

    private List<OverViewListItem> mItems= new ArrayList<>();
    private List<KnowListBean> mBeans = new ArrayList<>();
    private TreeListViewAdapter mAdapter;
    private OverViewContentPresenter mPresenter;
    public static OverViewFragment newInstance() {
        return new OverViewFragment();
    }
    private long selectId = 1;
    private String mTitle;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_overview;
    }

    @Override
    protected void initView() {
        mPresenter = new OverViewContentPresenter(this);
        try {
            mAdapter = new SimpleTreeAdapter<>(overviewListView,getContext(),mBeans,10);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        init();
        overviewListView.setAdapter(mAdapter);
        mPresenter.getOverViewListData(selectId);
    }

    private void init() {
        getSelectId();
        overViewSwipe.setColorSchemeColors(ResourceUtils.getColor(getContext(),R.color.pink));
        overViewSwipe.setRefreshing(true);
        overViewSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getSelectId();
                overViewSwipe.setRefreshing(true);
                mPresenter.getOverViewListData(selectId);
            }
        });
    }

    public void getSelectId(){
        selectId = CacheUtils.getLong(getContext(), ConstantsUtils.OVERVIEW_ID,0);
    }

    @Override
    public void showOverViewDataError(int error, String message) {
        overViewSwipe.setRefreshing(false);
        ToastUtils.ToastText(getContext(),"Error = "+error+",Message ="+message);
    }

    @Override
    public void showOverViewDataFinish(List<OverViewListItem> overViewListItemList) {
        overViewSwipe.setRefreshing(false);
        initData(overViewListItemList);
    }

    private void initData(List<OverViewListItem> overViewListItemList){
        mBeans.clear();
        for (OverViewListItem item:overViewListItemList){
            KnowListBean mBean = new KnowListBean(item.getSortId(),item.getParentId(),item.getTitle());
            mBean.setMasterLevel(item.getMasterLevel());
            mBean.setScore(item.getScore());
            mBean.setStudy(item.getStudy());
            mBeans.add(mBean);
        }
        try {
            mAdapter.swipeData(mBeans);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        mAdapter.notifyDataSetChanged();
    }

    @OnClick({R.id.overview_select,R.id.overview_information})
    public void onViewClicked(View itemView){
        switch (itemView.getId()){
            case R.id.overview_select:
                OverViewSelectActivity.launch(getActivity());
                break;
            case R.id.overview_information:
                OverViewInformationActivity.launch(getActivity(),selectId);
                break;
        }
    }
}

