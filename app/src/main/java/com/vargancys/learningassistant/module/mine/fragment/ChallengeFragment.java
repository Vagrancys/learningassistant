package com.vargancys.learningassistant.module.mine.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseFragment;
import com.vargancys.learningassistant.db.mine.MineDataBean;
import com.vargancys.learningassistant.model.mine.bean.ChallengeTypeDataBean;
import com.vargancys.learningassistant.module.mine.adapter.ChallengeItemSection;
import com.vargancys.learningassistant.module.mine.view.ChallengeView;
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
 * Description: 个人中心天梯碎片页面
 */
public class ChallengeFragment extends BaseFragment implements ChallengeView {
    public static ChallengeFragment newInstance(){
        return new ChallengeFragment();
    }

    @BindView(R.id.mine_challenge_day)
    TextView mineChallengeDay;
    @BindView(R.id.mine_challenge_highest)
    TextView mineChallengeHighest;
    @BindView(R.id.mine_challenge_level)
    TextView mineChallengeLevel;
    @BindView(R.id.mine_challenge_area)
    TextView mineChallengeArea;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    private BaseMinePresenter mPresenter;
    private long mineId;
    private SectionedRecyclerViewAdapter mSectionAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_knowledge;
    }

    @Override
    protected void initView() {
        mineId = CacheUtils.getLong(getContext(), ConstantsUtils.MINE_MEMBER_ID,0);
        mPresenter = new BaseMinePresenter(this);
        initRefresh();
        mPresenter.getChallengeData(mineId);
        mSectionAdapter = new SectionedRecyclerViewAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mSectionAdapter);
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
        mPresenter.getChallengeTypeData(mineId);
    }

    @Override
    public void loadChallengeDataError(int error, String message) {
        ToastUtils.ToastText(getContext(), "Error =" + error + ", Message =" + message);
        mineChallengeDay.setText("--");
        mineChallengeHighest.setText("--");
        mineChallengeLevel.setText("--");
        mineChallengeArea.setText("--");
    }

    @Override
    public void loadChallengeDataFinish(MineDataBean mine) {
        mineChallengeDay.setText(String.valueOf(mine.getDay()));
        mineChallengeHighest.setText(String.valueOf(mine.getHighest()));
        mineChallengeLevel.setText(String.valueOf(mine.getLevel()));
        mineChallengeArea.setText(String.valueOf(mine.getArea()));
    }

    @Override
    public void loadChallengeTypeDataError(int error, String message) {
        ToastUtils.ToastText(getContext(),"Error ="+error+",Message ="+message);
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void loadChallengeTypeDataFinish(ChallengeTypeDataBean mBean) {
        swipeRefresh.setRefreshing(false);
        for (int i=0; i<mBean.getItemBeans().size();i++){
            mSectionAdapter.addSection(new ChallengeItemSection(getContext(),getActivity(),mBean.getItemBeans().get(i)));
        }
        mSectionAdapter.notifyDataSetChanged();
    }
}
