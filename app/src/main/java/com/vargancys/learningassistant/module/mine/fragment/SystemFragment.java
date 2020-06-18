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
import com.vargancys.learningassistant.db.overview.OverViewListContent;
import com.vargancys.learningassistant.module.mine.adapter.SystemDataAdapter;
import com.vargancys.learningassistant.module.mine.view.SystemView;
import com.vargancys.learningassistant.presenter.mine.BaseMinePresenter;
import com.vargancys.learningassistant.utils.CacheUtils;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ResourceUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author Vagrancy
 * @date 2020/5/21
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 个人中心知识体系碎片页面
 */
public class SystemFragment extends BaseFragment  implements SystemView {
    private static String TAG = "SystemFragment";
    public static SystemFragment newInstance(){
        return new SystemFragment();
    }

    @BindView(R.id.mine_system_count)
    TextView mineSystemCount;
    @BindView(R.id.mine_knowledge_total)
    TextView mineKnowledgeTotal;
    @BindView(R.id.mine_speciality_count)
    TextView mineSpecialityCount;
    @BindView(R.id.mine_people_count)
    TextView minePeopleCount;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.fragment_content)
    TextView fragmentContent;
    @BindView(R.id.fragment_empty)
    LinearLayout fragmentEmpty;
    @BindView(R.id.system_scrollview)
    ScrollView systemScrollview;
    private BaseMinePresenter mPresenter;
    private long mineId;
    private SystemDataAdapter mAdapter;
    private List<OverViewListContent> mBean = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.fragment_system;
    }

    @Override
    protected void initView() {
        mineId = CacheUtils.getLong(getContext(), ConstantsUtils.MINE_MEMBER_ID,0);
        mPresenter = new BaseMinePresenter(this);
        initRefresh();
        mPresenter.getSystemData(mineId);
        mAdapter = new SystemDataAdapter(getContext(),mBean);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
        fragmentContent.setText(getText(R.string.system_data_empty_text));
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
        mPresenter.getSystemTypeData(mineId);
    }

    @Override
    public void loadSystemDataError(int error, String message) {
        ToastUtils.ToastText(getContext(), "Error =" + error + ", Message =" + message);
        mineSystemCount.setText("--");
        mineKnowledgeTotal.setText("--");
        minePeopleCount.setText("--");
        mineSpecialityCount.setText("--");
    }

    @Override
    public void loadSystemDataFinish(MineDataBean mine) {
        mineSystemCount.setText(String.valueOf(mine.getSystem_count()));
        mineKnowledgeTotal.setText(String.valueOf(mine.getKnowledge_total()));
        minePeopleCount.setText(String.valueOf(mine.getPeople_count()));
        mineSpecialityCount.setText(String.valueOf(mine.getSpeciality_total()));
    }

    @Override
    public void loadSystemTypeDataError(int error, String message) {
        ToastUtils.ToastText(getContext(),"Error ="+error+",Message ="+message);
        swipeRefresh.setRefreshing(false);
        fragmentEmpty.setVisibility(View.VISIBLE);
        systemScrollview.setVisibility(View.GONE);
    }

    @Override
    public void loadSystemTypeDataFinish(List<OverViewListContent> bean) {
        swipeRefresh.setRefreshing(false);
        fragmentEmpty.setVisibility(View.GONE);
        systemScrollview.setVisibility(View.VISIBLE);
        mBean.clear();
        mBean.addAll(bean);
        mAdapter.notifyDataSetChanged();
    }
}
