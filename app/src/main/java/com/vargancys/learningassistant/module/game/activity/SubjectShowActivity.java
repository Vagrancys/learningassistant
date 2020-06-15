package com.vargancys.learningassistant.module.game.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.db.game.GameSubjectContent;
import com.vargancys.learningassistant.db.game.GameSubjectItem;
import com.vargancys.learningassistant.module.game.adapter.SubjectShowAdapter;
import com.vargancys.learningassistant.module.game.view.SubjectShowView;
import com.vargancys.learningassistant.presenter.game.BaseGamePresenter;
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
 * @date 2020/4/12
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 问题展示页面
 */
public class SubjectShowActivity extends BaseActivity implements SubjectShowView {
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.common_img)
    ImageView commonImg;
    @BindView(R.id.subject_title)
    TextView subjectTitle;
    @BindView(R.id.subject_last_time)
    TextView subjectLastTime;
    @BindView(R.id.subject_problem)
    TextView subjectProblem;
    @BindView(R.id.subject_error)
    TextView subjectError;
    @BindView(R.id.subject_answer)
    TextView subjectAnswer;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.fragment_content)
    TextView fragmentContent;
    @BindView(R.id.fragment_empty)
    LinearLayout fragmentEmpty;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefreshLayout;
    private SubjectShowAdapter mAdapter;
    private BaseGamePresenter mPresenter;
    private Handler mHandler;
    //总结知识单项的id;
    private long knowItemId;
    //题目中心的id
    private long mSubjectContent;

    private List<GameSubjectItem> mItems =new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_subject_show;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        if (intent != null) {
            knowItemId = intent.getLongExtra(ConstantsUtils.KNOW_ITEM_ID, 0);
        }
        init();
        mPresenter = new BaseGamePresenter(this);
        mPresenter.getSubjectData(knowItemId);
    }

    @Override
    public void initToolbar() {

        commonTitle.setText(ResourceUtils.getString(getContext(),R.string.common_subject_title));

        commonImg.setImageResource(R.drawable.know_add_normal);
    }

    private void init() {
        mHandler = new Handler();
        mAdapter = new SubjectShowAdapter(getContext(),mItems,mHandler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                mPresenter.getSubjectData(knowItemId);
            }
        });
        swipeRefreshLayout.setColorSchemeColors(ResourceUtils.getColor(getContext(),R.color.pink));
    }

    public static void launch(Activity activity, long know_id) {
        Intent intent = new Intent(activity, SubjectShowActivity.class);
        intent.putExtra(ConstantsUtils.KNOW_ITEM_ID, know_id);
        activity.startActivity(intent);
    }

    @Override
    public void showSubjectContentFinish(GameSubjectContent mContent) {
        swipeRefreshLayout.setRefreshing(false);
        mSubjectContent = mContent.getId();
        subjectTitle.setText(mContent.getTitle());
        subjectLastTime.setText(mContent.getLast_time());
        subjectError.setText(mContent.getError()+"道");
        subjectProblem.setText(mContent.getProblem()+"题");
        subjectAnswer.setText(mContent.getAnswer()+"道");
    }

    @Override
    public void showSubjectContentError(int error, String message) {
        swipeRefreshLayout.setRefreshing(false);
        ToastUtils.ToastText(getContext(),"Error = "+error +", Message ="+message);
        clearContentData();
    }

    @Override
    public void showSubjectItemError(int error, String message) {
        ToastUtils.ToastText(getContext(),"Error ="+error+", Message ="+message);
        clearItemData();
    }

    @Override
    public void showSubjectItemFinish(List<GameSubjectItem> Items) {
        recyclerView.setVisibility(View.VISIBLE);
        fragmentEmpty.setVisibility(View.GONE);
        mItems.clear();
        mItems.addAll(Items);
        mAdapter.notifyDataSetChanged();
    }

    private void clearItemData() {
        recyclerView.setVisibility(View.GONE);
        fragmentEmpty.setVisibility(View.VISIBLE);
        fragmentContent.setText(ResourceUtils.getString(getContext(),R.string.subject_item_empty));
    }

    private void clearContentData() {
        subjectTitle.setText(ResourceUtils.getString(getContext(),R.string.subject_title_empty));
        subjectLastTime.setText("--");
        subjectAnswer.setText("--");
        subjectError.setText("--");
        subjectProblem.setText("--");
    }

    @OnClick({R.id.common_back,R.id.common_img})
    public void onViewClicked(View itemView){
        switch (itemView.getId()){
            case R.id.common_back:
                finish();
                break;
            case R.id.common_img:
                //添加问题的各种答题方法
                SubjectAddActivity.launch(SubjectShowActivity.this,knowItemId,mSubjectContent);
                break;
        }
    }
}
