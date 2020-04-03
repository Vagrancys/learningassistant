package com.vargancys.learningassistant.module.overview.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseFragment;
import com.vargancys.learningassistant.db.overview.OverViewListBean;
import com.vargancys.learningassistant.db.overview.OverViewListItem;
import com.vargancys.learningassistant.module.overview.activity.OverViewInformationActivity;
import com.vargancys.learningassistant.module.overview.activity.OverViewSearchActivity;
import com.vargancys.learningassistant.module.overview.adapter.SimpleTreeAdapter;
import com.vargancys.learningassistant.module.overview.view.OverViewContentView;
import com.vargancys.learningassistant.presenter.overview.OverViewContentPresenter;
import com.vargancys.learningassistant.utils.ToastUtils;
import com.vargancys.learningassistant.widget.TreeDirectory.TreeListViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/01
 * version:1.0
 */
public class OverViewFragment extends BaseFragment implements OverViewContentView {
    private static String TAG = "OverViewFragment";
    @BindView(R.id.overview_select)
    ImageView overviewSelect;
    @BindView(R.id.overview_information)
    ImageView overviewInformation;
    @BindView(R.id.overview_title)
    TextView overviewTitle;
    @BindView(R.id.overview_listview)
    ListView overviewListView;

    private List<OverViewListItem> mItems= new ArrayList<>();
    private List<OverViewListBean> mBeans = new ArrayList<>();
    private TreeListViewAdapter mAdapter;
    private OverViewContentPresenter mPresenter;
    public static OverViewFragment newInstance() {
        return new OverViewFragment();
    }
    private long selectId = 1;
    private String mTitle;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_overview;
    }

    @Override
    protected void initView() {
        mPresenter = new OverViewContentPresenter(this);
        try {
            mAdapter = new SimpleTreeAdapter<>(overviewListView,getContext(),mBeans,10);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        overviewListView.setAdapter(mAdapter);
        initToolbar();
        mPresenter.getOverViewListData(selectId);
    }

    public void initToolbar(){
        overviewSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OverViewSearchActivity.launch(getActivity());
            }
        });

        overviewInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OverViewInformationActivity.launch(getActivity(),selectId);
            }
        });
    }

    @Override
    public void showOverViewDataError(int error, String message) {
        ToastUtils.ToastText(getContext(),"Error = "+error+",Message ="+message);
    }

    @Override
    public void showOverViewDataFinish(List<OverViewListItem> overViewListItemList) {
        initData(overViewListItemList);
    }

    private void initData(List<OverViewListItem> overViewListItemList) {
        for (OverViewListItem item:overViewListItemList){
            OverViewListBean mBean = new OverViewListBean(item.getId(),item.getParentId(),item.getTitle());
            mBean.setMasterLevel(item.getMasterLevel());
            mBean.setScore(item.getScore());
            mBean.setStudy(item.getStudy());
            mBeans.add(mBean);
        }
        mAdapter.notifyDataSetChanged();
    }
}
