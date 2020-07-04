package com.vargancys.learningassistant.module.overview.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseFragment;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.model.overview.bean.OverViewHallRankBean;
import com.vargancys.learningassistant.module.overview.adapter.OverViewHallQualityAdapter;
import com.vargancys.learningassistant.module.overview.view.BaseHallView;
import com.vargancys.learningassistant.presenter.overview.BaseOverViewPresenter;
import com.vargancys.learningassistant.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
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
    private AlertDialog.Builder mAlert;
    private long overviewId;

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
        mAlert = new AlertDialog.Builder(getContext());
        mAlert.setNegativeButton(R.string.common_cancel_text, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                overviewId = 0;
                dialog.dismiss();
            }
        });
        mAlert.setPositiveButton(R.string.common_determine_text, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(overviewId != 0){
                    mPresenter.insertOverViewData(overviewId);
                }
                dialog.dismiss();
            }
        });
        mAdapter = new OverViewHallQualityAdapter(getContext(),mHall);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
        mPresenter.getHallQualityData();
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                OverViewHallRankBean bean = mHall.get(position);
                overviewId = bean.getId();
                mAlert.setTitle(bean.getTitle());
                mAlert.setMessage("是否关注"+bean.getTitle()+"并学习该体系!");
                mAlert.show();
            }
        });
    }

    @Override
    public void getHallDataSuccess(List<OverViewHallRankBean> mBean) {
        mHall.clear();
        mHall.addAll(mBean);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void getHallDataFail(int error, String message) {
        ToastUtils.ToastText(getContext(),R.string.overview_hall_quality_fail_text);
    }

    @Override
    public void insertHallDataFail(int error, String message) {
        ToastUtils.ToastText(getContext(),R.string.overview_hall_insert_fail_text);
    }

    @Override
    public void insertHallDataSuccess() {
        ToastUtils.ToastText(getContext(),R.string.overview_hall_insert_win_text);
    }
}
