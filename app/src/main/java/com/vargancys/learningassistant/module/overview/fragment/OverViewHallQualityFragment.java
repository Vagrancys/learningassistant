package com.vargancys.learningassistant.module.overview.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseFragment;
import com.vargancys.learningassistant.model.overview.bean.OverViewHallRankBean;
import com.vargancys.learningassistant.module.overview.view.BaseHallView;
import com.vargancys.learningassistant.presenter.overview.BaseOverViewPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Vagrancy
 * @date 2020/6/28
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识体系大厅质量碎片
 */
public class OverViewHallQualityFragment extends BaseFragment implements BaseHallView {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private OverViewHallQualityAdapter mAdapter;
    private BaseOverViewPresenter mPresenter;
    private List<OverViewHallRankBean> mHall = new ArrayList<>();

    public static OverViewHallQualityFragment newInstance() {
        return new OverViewHallQualityFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_hall_quality;
    }

    @Override
    protected void initView() {
        mPresenter = new BaseOverViewPresenter<BaseHallView>(this);
        mPresenter.getHallQualityData();

    }


}
