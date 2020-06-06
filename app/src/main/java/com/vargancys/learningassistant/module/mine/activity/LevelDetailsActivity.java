package com.vargancys.learningassistant.module.mine.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.db.mine.MineLevelPrivilegeBean;
import com.vargancys.learningassistant.model.mine.bean.LevelItemBean;
import com.vargancys.learningassistant.module.mine.adapter.LevelPrivilegeAdapter;
import com.vargancys.learningassistant.module.mine.view.LevelDetailsView;
import com.vargancys.learningassistant.presenter.mine.BaseMinePresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ResourceUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Vagrancy
 * @date 2020/5/30
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 个人中心等级详情页面
 */
public class LevelDetailsActivity extends BaseActivity implements LevelDetailsView {
    @BindView(R.id.common_back)
    ImageView commonBack;
    @BindView(R.id.common_title_data)
    TextView commonTitleData;
    @BindView(R.id.level_details_title)
    TextView levelDetailsTitle;
    @BindView(R.id.level_details_level)
    TextView levelDetailsLevel;
    @BindView(R.id.level_details_experience)
    TextView levelDetailsExperience;
    @BindView(R.id.level_details_next_experience)
    TextView levelDetailsNextExperience;
    @BindView(R.id.level_details_privilege)
    TextView levelDetailsPrivilege;
    @BindView(R.id.level_details_rank)
    TextView levelDetailsRank;
    @BindView(R.id.level_details_time)
    TextView levelDetailsTime;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    private long levelId;
    private LevelPrivilegeAdapter mAdapter;
    private BaseMinePresenter mPresenter;
    private List<MineLevelPrivilegeBean> mBean = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_level_details;
    }

    @Override
    public void initView() {
        levelId = getIntent().getLongExtra(ConstantsUtils.MINE_LEVEL_ID, 0);
        mPresenter = new BaseMinePresenter(this);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                autoRefresh();
            }
        });
        mAdapter = new LevelPrivilegeAdapter(getContext(),mBean);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
        mPresenter.getLevelDetailsData(levelId);
    }

    public void autoRefresh(){
        swipeRefresh.setRefreshing(true);
        mPresenter.getLevelPrivilegeData();
    }

    public static void launch(Activity activity, long id) {
        Intent intent = new Intent(activity, LevelDetailsActivity.class);
        intent.putExtra(ConstantsUtils.CHALLENGE_DETAILS_ID, id);
        activity.startActivity(intent);
    }

    @Override
    public void initToolbar() {
        commonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        commonTitleData.setText(ResourceUtils.getString(getContext(),R.string.level_details_toolbar));
    }

    @Override
    public void loadLevelDetailsDataError(int error, String message) {
        ToastUtils.ToastText(getContext(),"error ="+error+", Message ="+message);
        levelDetailsTitle.setText("--");
        levelDetailsLevel.setText("--");
        levelDetailsExperience.setText("--");
        levelDetailsNextExperience.setText("--");
        levelDetailsPrivilege.setText("--");
        levelDetailsRank.setText("--");
        levelDetailsTime.setText("--");
    }

    @Override
    public void loadLevelDetailsDataFinish(LevelItemBean.LevelItem mItem) {
        levelDetailsTitle.setText(mItem.getTitle());
        levelDetailsTime.setText(mItem.getTime());
        levelDetailsLevel.setText(mItem.getLevel());
        levelDetailsRank.setText(mItem.getRank());
        levelDetailsPrivilege.setText(mItem.getPrivilege());
        levelDetailsNextExperience.setText(mItem.getNext_experience());
        levelDetailsExperience.setText(mItem.getExperience());
    }

    @Override
    public void loadLevelPrivilegeDataError(int error, String message) {
        swipeRefresh.setRefreshing(false);
        ToastUtils.ToastText(getContext(),"error ="+error+",Message ="+message);
    }

    @Override
    public void loadLevelPrivilegeDataFinish(List<MineLevelPrivilegeBean> mPrivilege) {
        swipeRefresh.setRefreshing(false);
        mBean.clear();
        mBean.addAll(mPrivilege);
        mAdapter.notifyDataSetChanged();
    }
}
