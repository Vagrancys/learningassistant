package com.vargancys.learningassistant.module.mine.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseFragment;
import com.vargancys.learningassistant.db.mine.MineDataBean;
import com.vargancys.learningassistant.model.mine.bean.KnowLedgeTypeDataBean;
import com.vargancys.learningassistant.model.mine.bean.ProblemTypeDataBean;
import com.vargancys.learningassistant.module.mine.adapter.KnowLedgeItemSection;
import com.vargancys.learningassistant.module.mine.adapter.ProblemItemSection;
import com.vargancys.learningassistant.module.mine.view.ProblemView;
import com.vargancys.learningassistant.presenter.mine.BaseMinePresenter;
import com.vargancys.learningassistant.utils.CacheUtils;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ResourceUtils;
import com.vargancys.learningassistant.utils.ToastUtils;
import com.vargancys.learningassistant.widget.section.SectionedRecyclerViewAdapter;

import butterknife.BindView;

/**
 * @author Vagrancy
 * @date 2020/5/21
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 个人中心问题碎片页面
 */
public class ProblemFragment extends BaseFragment implements ProblemView {
    private static String TAG = "ProblemFragment";
    @BindView(R.id.mine_problem_count)
    TextView mineProblemCount;
    @BindView(R.id.mine_level)
    TextView mineLevel;
    @BindView(R.id.mine_people)
    TextView minePeople;
    @BindView(R.id.mine_use)
    TextView mineUse;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.problem_scrollview)
    ScrollView problemScrollView;
    @BindView(R.id.fragment_empty)
    LinearLayout fragmentEmpty;
    @BindView(R.id.fragment_content)
    TextView fragmentContent;
    private BaseMinePresenter mPresenter;
    private long mineId;
    private SectionedRecyclerViewAdapter mSectionAdapter;

    public static KnowLedgeFragment newInstance() {
        return new KnowLedgeFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_problem;
    }

    @Override
    protected void initView() {
        Log.e(TAG,"问题断点");
        mineId = CacheUtils.getLong(getContext(), ConstantsUtils.MINE_MEMBER_ID,0);
        mPresenter = new BaseMinePresenter(this);
        initRefresh();
        mPresenter.getProblemData(mineId);
        mSectionAdapter = new SectionedRecyclerViewAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mSectionAdapter);
        fragmentContent.setText(ResourceUtils.getString(getContext(),R.string.problem_data_empty_text));
        autoRefresh();
    }

    private void initRefresh() {
        swipeRefresh.setColorSchemeColors(ResourceUtils.getColor(getContext(),R.color.pink));
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                autoRefresh();
            }
        });
    }

    private void autoRefresh(){
        swipeRefresh.setRefreshing(true);
        mPresenter.getProblemTypeData(mineId);
    }

    @Override
    public void loadProblemDataError(int error, String message) {
        ToastUtils.ToastText(getContext(), "Error =" + error + ", Message =" + message);
        mineProblemCount.setText("--");
        mineLevel.setText("--");
        minePeople.setText("--");
        mineUse.setText("--");
    }

    @Override
    public void loadProblemDataFinish(MineDataBean mine) {
        mineProblemCount.setText(String.valueOf(mine.getDay()));
        mineLevel.setText(String.valueOf(mine.getKnowledge()));
        minePeople.setText(String.valueOf(mine.getResult()));
        mineUse.setText(String.valueOf(mine.getQuality()));
    }

    @Override
    public void loadProblemTypeDataError(int error, String message) {
        ToastUtils.ToastText(getContext(),"Error ="+error+",Message ="+message);
        swipeRefresh.setRefreshing(false);
        problemScrollView.setVisibility(View.GONE);
        fragmentEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void loadProblemTypeDataFinish(ProblemTypeDataBean mBean) {
        swipeRefresh.setRefreshing(false);
        problemScrollView.setVisibility(View.VISIBLE);
        fragmentEmpty.setVisibility(View.GONE);
        for (int i=0; i<mBean.getItemBeans().size();i++){
            mSectionAdapter.addSection(new ProblemItemSection(getContext(),getActivity(),mBean.getItemBeans().get(i)));
        }
        mSectionAdapter.notifyDataSetChanged();
    }
}
