package com.vargancys.learningassistant.module.mine.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.model.mine.bean.KnowLedgeItemBean;
import com.vargancys.learningassistant.module.home.activity.show.ShowBookActivity;
import com.vargancys.learningassistant.module.home.activity.show.ShowCommonDefaultActivity;
import com.vargancys.learningassistant.module.home.activity.show.ShowCommonFifthActivity;
import com.vargancys.learningassistant.module.home.activity.show.ShowArticleActivity;
import com.vargancys.learningassistant.module.home.activity.show.ShowCommonFourthActivity;
import com.vargancys.learningassistant.module.home.activity.show.ShowCommonThirdActivity;
import com.vargancys.learningassistant.module.mine.adapter.ProblemItemAdapter;
import com.vargancys.learningassistant.module.mine.view.ProblemItemView;
import com.vargancys.learningassistant.presenter.mine.BaseMinePresenter;
import com.vargancys.learningassistant.utils.CacheUtils;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Vagrancy
 * @date 2020/6/1
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 个人中心问题各项页面
 */
public class ProblemItemActivity extends BaseActivity implements ProblemItemView {
    @BindView(R.id.common_title_data)
    TextView commonTitleData;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    private BaseMinePresenter mPresenter;
    private long problemId;
    private int type;
    private ProblemItemAdapter mAdapter;
    private List<KnowLedgeItemBean.KnowLedgeItem> mProblem = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_problem_item;
    }

    @Override
    public void initView() {
        problemId = CacheUtils.getLong(getContext(),ConstantsUtils.MINE_MEMBER_ID,0);
        type = getIntent().getIntExtra(ConstantsUtils.PROBLEM_ID,0);
        mPresenter = new BaseMinePresenter(this);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                autoData();
            }
        });
        mAdapter = new ProblemItemAdapter(getContext(),this,mProblem);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                KnowLedgeItemBean.KnowLedgeItem mItem = mProblem.get(position);
                if(mItem.isCreateClass()){
                    if(!mItem.isHave()){
                        launchShowActivity(mItem.getId().intValue(),mItem.getLevel());
                    }
                }else{
                    if(mItem.isHave()){
                        ToastUtils.ToastText(getContext(),"这需要官方来创建!个人不能创建!");
                    }
                }
            }
        });

        mPresenter.getProblemItemData(problemId,type);
    }

    private void launchShowActivity(int item_id,int level) {
        switch (level){
            case 1:
                ShowArticleActivity.launch(this,item_id);
                break;
            case 2:
                ShowBookActivity.launch(this,item_id);
                break;
            case 3:
                ShowCommonThirdActivity.launch(this,item_id);
                break;
            case 4:
                ShowCommonFourthActivity.launch(this,item_id);
                break;
            case 5:
                ShowCommonFifthActivity.launch(this,item_id);
                break;
            default:
                ShowCommonDefaultActivity.launch(this,item_id);
                break;
        }
    }

    private void autoData(){
        swipeRefresh.setRefreshing(true);
        mPresenter.getProblemItemData(problemId,type);
    }

    public static void launch(Activity activity, int type) {
        Intent intent = new Intent(activity, ProblemItemActivity.class);
        intent.putExtra(ConstantsUtils.PROBLEM_ID, type);
        activity.startActivity(intent);
    }

    @OnClick(R.id.common_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void loadProblemData(List<KnowLedgeItemBean.KnowLedgeItem> mItem) {
        swipeRefresh.setRefreshing(false);
        mProblem.addAll(mItem);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadProblemDataError(int error, String message) {
        swipeRefresh.setRefreshing(false);
        ToastUtils.ToastText(getContext(),"Error ="+error +",Message ="+message);
    }
}
