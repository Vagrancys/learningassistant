package com.vargancys.learningassistant.module.ladder.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseFragment;
import com.vargancys.learningassistant.db.ladder.LadderCommentBean;
import com.vargancys.learningassistant.module.ladder.activity.LadderCommentReplyActivity;
import com.vargancys.learningassistant.module.ladder.adapter.CommunicationAdapter;
import com.vargancys.learningassistant.module.ladder.view.LadderCommentView;
import com.vargancys.learningassistant.presenter.ladder.BaseLadderPresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author Vagrancy
 * @date 2020/5/8
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 天梯交流区的评论内容
 */
public class CommunicationFragment extends BaseFragment implements LadderCommentView {
    private static String TAG = "CommunicationFragment";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.fragment_empty)
    LinearLayout fragmentEmpty;
    public static CommunicationFragment newInstance(int position) {
        CommunicationFragment mFragment = new CommunicationFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantsUtils.LADDER_COMMUNICATION_ITEM, position);
        mFragment.setArguments(bundle);
        return mFragment;
    }
    private CommunicationAdapter mAdapter;

    private int mCurrent = 0;
    private BaseLadderPresenter mPresenter;
    private List<LadderCommentBean> mBean = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.fragment_communication;
    }

    @Override
    protected void initView() {
        if (getArguments() != null) {
            mCurrent = getArguments().getInt(ConstantsUtils.LADDER_COMMUNICATION_ITEM);
        }
        mPresenter = new BaseLadderPresenter(this);
    }

    @Override
    public void initData() {
        swipeRefresh.setColorSchemeResources(R.color.pink);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                autoRefreshData();
            }
        });
        mAdapter = new CommunicationAdapter(getContext(),mBean);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
        autoRefreshData();
        mAdapter.setOnCommentClickListener(new CommentListener());
    }

    private class CommentListener implements CommunicationAdapter.OnCommentClickListener {

        @Override
        public void onPraisePressure(int position) {
            mPresenter.updatePraiseData(mBean.get(position).getId(),true);
        }

        @Override
        public void onPraiseUnPressure(int position) {
            mPresenter.updatePraiseData(mBean.get(position).getId(),false);
        }

        @Override
        public void onStepPressure(int position) {
            mPresenter.updateStepData(mBean.get(position).getId(),true);
        }

        @Override
        public void onStepUnPressure(int position) {
            mPresenter.updateStepData(mBean.get(position).getId(),false);
        }

        @Override
        public void onReplyPressure(int position) {
            LadderCommentReplyActivity.launch(getActivity(),mBean.get(position).getId());
        }
    }

    private void autoRefreshData() {
        mBean.clear();
        swipeRefresh.setRefreshing(true);
        mPresenter.refreshCommentData(mCurrent);
    }

    public void refreshData() {
        autoRefreshData();
    }

    @Override
    public void showCommentDataFinish(List<LadderCommentBean> bean) {
        mBean.addAll(bean);
        recyclerView.setVisibility(View.VISIBLE);
        fragmentEmpty.setVisibility(View.GONE);
        swipeRefresh.setRefreshing(false);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showCommentDataError(int error, String message) {
        swipeRefresh.setRefreshing(false);
        fragmentEmpty.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        ToastUtils.ToastText(getContext(),"Error ="+error+", Message ="+message);
    }
}
