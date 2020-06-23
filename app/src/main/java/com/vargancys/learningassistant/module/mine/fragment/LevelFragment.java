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
import com.vargancys.learningassistant.model.mine.bean.LevelTypeDataBean;
import com.vargancys.learningassistant.module.mine.adapter.KnowLedgeItemSection;
import com.vargancys.learningassistant.module.mine.adapter.LevelItemSection;
import com.vargancys.learningassistant.module.mine.view.LevelView;
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
 * Description: 个人中心个人等级碎片页面
 */
public class LevelFragment extends BaseFragment implements LevelView {
    private static String TAG = "LevelFragment";
    public static LevelFragment newInstance(){
        return new LevelFragment();
    }

    @BindView(R.id.mine_level_highest)
    TextView mineLevelHighest;
    @BindView(R.id.mine_level_minimum)
    TextView mineLevelMinimum;
    @BindView(R.id.mine_level_rank)
    TextView mineLevelRank;
    @BindView(R.id.mine_level_count)
    TextView mineLevelCount;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.fragment_empty)
    LinearLayout fragmentEmpty;
    @BindView(R.id.fragment_content)
    TextView fragmentContent;
    @BindView(R.id.level_scrollview)
    ScrollView levelScrollview;
    private BaseMinePresenter mPresenter;
    private long mineId;
    private SectionedRecyclerViewAdapter mSectionAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_level;
    }

    @Override
    protected void initView() {
        mineId = CacheUtils.getLong(getContext(), ConstantsUtils.MINE_MEMBER_ID,0);
        mPresenter = new BaseMinePresenter(this);
        initRefresh();
        mPresenter.getLevelData(mineId);
        mSectionAdapter = new SectionedRecyclerViewAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mSectionAdapter);
        fragmentContent.setText(getText(R.string.level_data_empty_text));
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
        mPresenter.getLevelTypeData(getContext(),mineId);
    }

    @Override
    public void loadLevelDataError(int error, String message) {
        ToastUtils.ToastText(getContext(), "Error =" + error + ", Message =" + message);
        mineLevelHighest.setText("--");
        mineLevelMinimum.setText("--");
        mineLevelRank.setText("--");
        mineLevelCount.setText("--");
    }

    @Override
    public void loadLevelDataFinish(MineDataBean mine) {
        mineLevelHighest.setText(String.valueOf(mine.getLevel_highest()));
        mineLevelMinimum.setText(String.valueOf(mine.getLevel_minimum()));
        mineLevelRank.setText(String.valueOf(mine.getLevel_rank()));
        mineLevelCount.setText(String.valueOf(mine.getLevel_count()));
    }

    @Override
    public void loadLevelTypeDataError(int error, String message) {
        ToastUtils.ToastText(getContext(),"Error ="+error+",Message ="+message);
        swipeRefresh.setRefreshing(false);
        levelScrollview.setVisibility(View.GONE);
        fragmentEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void loadLevelTypeDataFinish(LevelTypeDataBean mBean) {
        swipeRefresh.setRefreshing(false);
        levelScrollview.setVisibility(View.VISIBLE);
        fragmentEmpty.setVisibility(View.GONE);
        if(mBean != null && mBean.getItemBeans() != null){
            for (int i=0; i<mBean.getItemBeans().size();i++){
                mSectionAdapter.addSection(new LevelItemSection(getContext(),getActivity(),mBean.getItemBeans().get(i)));
            }
            mSectionAdapter.notifyDataSetChanged();
        }
    }
}

