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
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.db.ladder.LadderDataBean;
import com.vargancys.learningassistant.module.mine.adapter.ChallengeItemAdapter;
import com.vargancys.learningassistant.module.mine.view.ChallengeItemView;
import com.vargancys.learningassistant.presenter.mine.BaseMinePresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ResourceUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Vagrancy
 * @date 2020/5/26
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 个人中心天梯挑战各项页面
 */
public class ChallengeItemActivity extends BaseActivity implements ChallengeItemView {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.common_back)
    ImageView commonBack;
    @BindView(R.id.common_title_data)
    TextView commonTitleData;
    private int type = 1;
    private BaseMinePresenter mPresenter;
    private ChallengeItemAdapter mAdapter;
    private List<LadderDataBean> mBean = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_challenge_item;
    }

    @Override
    public void initView() {
        type = getIntent().getIntExtra(ConstantsUtils.KNOW_TYPE_ID, 0);
        mPresenter = new BaseMinePresenter(this);
        swipeRefresh.setColorSchemeColors(ResourceUtils.getColor(getContext(),R.color.pink));
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                autoRefresh();
            }
        });

        mAdapter = new ChallengeItemAdapter(getContext(),mBean);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                ChallengeDetailsActivity.launch(ChallengeItemActivity.this,mBean.get(position).getId());
            }
        });
    }

    @Override
    public void initToolbar() {
        commonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        commonTitleData.setText(ResourceUtils.getString(getContext(),R.string.challenge_item_toolbar));
    }

    private void autoRefresh(){
        swipeRefresh.setRefreshing(true);
        mPresenter.getChallengeItemData(type);
    }

    public static void launch(Activity activity, int type) {
        Intent intent = new Intent(activity, ChallengeItemActivity.class);
        intent.putExtra(ConstantsUtils.KNOW_TYPE_ID, type);
        activity.startActivity(intent);
    }

    @Override
    public void loadItemDataError(int error, String message) {
        ToastUtils.ToastText(getContext(),"Error ="+error+", Message ="+message);
    }

    @Override
    public void loadItemDataFinish(List<LadderDataBean> bean) {
        mBean.clear();
        mBean.addAll(bean);
        mAdapter.notifyDataSetChanged();
    }
}
