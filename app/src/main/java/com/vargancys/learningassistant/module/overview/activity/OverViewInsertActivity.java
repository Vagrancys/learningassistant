package com.vargancys.learningassistant.module.overview.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.bean.common.KnowListBean;
import com.vargancys.learningassistant.bean.overview.OverViewListItem;
import com.vargancys.learningassistant.module.overview.adapter.InsertTreeAdapter;
import com.vargancys.learningassistant.module.overview.view.OverViewInsertView;
import com.vargancys.learningassistant.presenter.overview.BaseOverViewPresenter;
import com.vargancys.learningassistant.utils.CacheUtils;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ResourceUtils;
import com.vargancys.learningassistant.utils.ToastUtils;
import com.vargancys.learningassistant.widget.dialog.OverViewInsertPopupWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Vagrancy
 * @date 2020/7/2
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识体系添加模块
 */
public class OverViewInsertActivity extends BaseActivity implements OverViewInsertView{
    @BindView(R.id.common_title_data)
    TextView commonTitleData;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    private BaseOverViewPresenter mPresenter;
    private List<KnowListBean> mBean = new ArrayList<>();
    private long overViewId;
    private InsertTreeAdapter mAdapter;
    private long memberId;
    private long parentId;
    private long contentId;
    private OverViewInsertPopupWindow mPopupWindow;
    private List<OverViewListItem> mList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_overview_insert;
    }

    @Override
    public void initView() {
        memberId = CacheUtils.getLong(getContext(),ConstantsUtils.OVERVIEW_ID,0);
        mPresenter = new BaseOverViewPresenter<OverViewInsertView>(this);
        swipeRefresh.setColorSchemeColors(ResourceUtils.getColor(getContext(),R.color.pink));
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                autoData();
            }
        });
        mPopupWindow = new OverViewInsertPopupWindow(this);
        mPopupWindow.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setOnInsertClickListener(new OverViewInsertPopupWindow.OnInsertClickListener() {
            @Override
            public void exit() {
                mPopupWindow.dismiss();
            }

            @Override
            public void determine(long id, String title, String summary, String score, int level) {
                mPresenter.insertOverViewInsertDta(id,contentId,parentId,memberId,title,summary,score,level);
            }

            @Override
            public void cancel() {
                mPopupWindow.dismiss();
            }
        });
        try {
            mAdapter = new InsertTreeAdapter<>(listView,getContext(),mBean,10);
            mAdapter.setOnInsertClickListener(new InsertTreeAdapter.OnInsertClickListener() {
                @Override
                public void update(int position, long id) {
                    OverViewListItem mView = mList.get(position);
                    parentId = mView.getId();
                    contentId = mView.getContentId();
                    KnowListBean mItem =mBean.get(position);
                    mPopupWindow.setInsertId(mItem.getKnowId());
                    mPopupWindow.showAsDropDown(listView);
                }
            });
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        listView.setAdapter(mAdapter);
        autoData();
    }

    private void autoData(){
        swipeRefresh.setRefreshing(true);
        getSelectId();
        mPresenter.getOverViewInsertData(overViewId);
    }

    public void getSelectId(){
        overViewId = CacheUtils.getLong(getContext(), ConstantsUtils.OVERVIEW_ID,0);
    }

    @Override
    public void initToolbar() {
        commonTitleData.setText(getText(R.string.overview_insert_toolbar));
    }

    public static void launch(Activity activity,long createId,int code){
        Intent intent =new Intent(activity, OverViewInsertActivity.class);
        intent.putExtra(ConstantsUtils.OVERVIEW_ID,createId);
        activity.startActivityForResult(intent,code);
    }

    @OnClick(R.id.common_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void getOverViewDataSuccess(List<OverViewListItem> mBean) {
        mList.addAll(mBean);
        List<KnowListBean> mItems = new ArrayList<>();
        for (OverViewListItem item:mBean){
            KnowListBean mItem = new KnowListBean(item.getSortId(),item.getParentId(),item.getTitle());
            mItem.setMasterLevel(item.getMasterLevel());
            mItem.setScore(item.getScore());
            mItem.setStudy(item.getStudy());
            mItem.setKnowId(item.getId());
            mItem.setLevel(item.getLevel());
            mItem.setTitle(item.getTitle());
            mItems.add(mItem);
        }
        swipeRefresh.setRefreshing(false);
        this.mBean.addAll(mItems);
        try {
            mAdapter.swipeData(this.mBean);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void getOverViewDataFail(int error, String message) {
        swipeRefresh.setRefreshing(false);
        ToastUtils.ToastText(getContext(),R.string.overview_insert_data_fail_text);
    }

    @Override
    public void insertOverViewDataFail(int error, String message) {
        ToastUtils.ToastText(getContext(),R.string.overview_insert_list_fail_text);
    }

    @Override
    public void insertOverViewDataSuccess() {
        ToastUtils.ToastText(getContext(),R.string.overview_insert_list_success_text);
        autoData();
    }
}

