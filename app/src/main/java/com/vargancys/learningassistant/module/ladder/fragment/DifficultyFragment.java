package com.vargancys.learningassistant.module.ladder.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseFragment;
import com.vargancys.learningassistant.db.ladder.LadderDifficultyCommentBean;
import com.vargancys.learningassistant.db.ladder.LadderDifficultyDataBean;
import com.vargancys.learningassistant.module.ladder.adapter.DifficultyCommentAdapter;
import com.vargancys.learningassistant.module.ladder.view.LadderDifficultyDetailsView;
import com.vargancys.learningassistant.presenter.ladder.BaseLadderPresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Vagrancy
 * @date 2020/5/10
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 难度详情碎片
 */
public class DifficultyFragment extends BaseFragment implements LadderDifficultyDetailsView {
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.difficulty_title)
    TextView difficultyTitle;
    @BindView(R.id.difficulty_describe)
    TextView difficultyDescribe;
    @BindView(R.id.difficulty_successful)
    TextView difficultySuccessful;
    @BindView(R.id.difficulty_people)
    TextView difficultyPeople;
    @BindView(R.id.difficulty_time)
    TextView difficultyTime;
    @BindView(R.id.difficulty_comment_count)
    TextView difficultyCommentCount;
    @BindView(R.id.difficulty_recyclerView)
    RecyclerView difficultyRecyclerView;

    public static DifficultyFragment newInstance(int type) {
        DifficultyFragment mFragment = new DifficultyFragment();
        Bundle mBundle = new Bundle();
        mBundle.putInt(ConstantsUtils.LADDER_DIFFICULTY_TYPE, type);
        mFragment.setArguments(mBundle);
        return mFragment;
    }

    private List<LadderDifficultyCommentBean> mCommentBean = new ArrayList<>();
    private BaseLadderPresenter mPresenter;
    private int difficultyType = 1;
    private DifficultyCommentAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_difficulty;
    }

    @Override
    protected void initView() {
        if (getArguments() != null) {
            difficultyType = getArguments().getInt(ConstantsUtils.LADDER_DIFFICULTY_TYPE);
        }
        mPresenter = new BaseLadderPresenter(this);
        initListener();
    }

    private void initListener() {
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.pink));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                autoRefreshData();
            }
        });
    }

    @Override
    public void initData() {
        mAdapter = new DifficultyCommentAdapter(getContext(),mCommentBean);
        difficultyRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        difficultyRecyclerView.setAdapter(mAdapter);
        autoRefreshData();
    }

    public void autoRefreshData() {
        mCommentBean.clear();
        mPresenter.getLadderDetailsData(difficultyType);
        mPresenter.getLadderCommentAllData(difficultyType);
    }

    @Override
    public void showDifficultyDetailsError(int error, String message) {
        ToastUtils.ToastText(getContext(),"Error ="+error+",Message ="+message);
        difficultyTitle.setText("--");
        difficultyDescribe.setText("--");
        difficultySuccessful.setText("--");
        difficultyPeople.setText("");
        difficultyTime.setText("--");
    }

    @Override
    public void showDifficultyDetailsFinish(LadderDifficultyDataBean bean) {
        difficultyTitle.setText(bean.getTitle());
        difficultyTime.setText(bean.getTime());
        difficultySuccessful.setText(bean.getSuccessful());
        difficultyPeople.setText(bean.getPeople());
        difficultyDescribe.setText(bean.getContent());
    }

    @Override
    public void showDifficultyCommentFinish(List<LadderDifficultyCommentBean> mBean) {
        swipeRefreshLayout.setRefreshing(false);
        mCommentBean.addAll(mBean);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showDifficultyCommentError(int error, String message) {
        ToastUtils.ToastText(getContext(),"Error ="+error +", Message ="+message);
        difficultyRecyclerView.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
    }
}
