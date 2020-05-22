package com.vargancys.learningassistant.module.mine.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseFragment;
import com.vargancys.learningassistant.db.mine.MineDataBean;
import com.vargancys.learningassistant.model.mine.bean.KnowLedgeTypeDataBean;
import com.vargancys.learningassistant.module.mine.view.KnowLedgeView;
import com.vargancys.learningassistant.presenter.mine.BaseMinePresenter;
import com.vargancys.learningassistant.utils.CacheUtils;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Vagrancy
 * @date 2020/5/21
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 个人中心知识碎片页面
 */
public class KnowLedgeFragment extends BaseFragment implements KnowLedgeView {
    @BindView(R.id.mine_knowledge_day)
    TextView mineKnowledgeDay;
    @BindView(R.id.mine_knowledge_count)
    TextView mineKnowledgeCount;
    @BindView(R.id.mine_knowledge_quality)
    TextView mineKnowledgeQuality;
    @BindView(R.id.mine_knowledge_result)
    TextView mineKnowledgeResult;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    private BaseMinePresenter mPresenter;
    private long mineId;

    public static KnowLedgeFragment newInstance() {
        return new KnowLedgeFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_knowledge;
    }

    @Override
    protected void initView() {
        //TODO 多类型recycler
        mineId = CacheUtils.getLong(getContext(),ConstantsUtils.MINE_MEMBER_ID,0);
        mPresenter = new BaseMinePresenter(this);
        initRefresh();
        mPresenter.getKnowLedgeData(mineId);
        autoRefresh();
    }

    private void initRefresh() {
        swipeRefresh.setColorSchemeColors(getResources().getColor(R.color.pink));
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                autoRefresh();
            }
        });
    }

    private void autoRefresh(){
        swipeRefresh.setRefreshing(true);
        mPresenter.getKnowLedgeTypeData(mineId);
    }

    @Override
    public void showKnowLedgeDataError(int error, String message) {
        ToastUtils.ToastText(getContext(), "Error =" + error + ", Message =" + message);
        mineKnowledgeDay.setText("--");
        mineKnowledgeCount.setText("--");
        mineKnowledgeQuality.setText("--");
        mineKnowledgeResult.setText("--");
    }

    @Override
    public void showKnowLedgeDataFinish(MineDataBean mine) {
        mineKnowledgeDay.setText(String.valueOf(mine.getDay()));
        mineKnowledgeCount.setText(String.valueOf(mine.getKnowledge()));
        mineKnowledgeResult.setText(String.valueOf(mine.getResult()));
        mineKnowledgeQuality.setText(String.valueOf(mine.getQuality()));
    }

    @Override
    public void showKnowLedgeTypeDataError(int error, String message) {
        ToastUtils.ToastText(getContext(),"Error ="+error+",Message ="+message);
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void showKnowLedgeTypeDataFinish(KnowLedgeTypeDataBean mBean) {
        swipeRefresh.setRefreshing(false);
    }
}
