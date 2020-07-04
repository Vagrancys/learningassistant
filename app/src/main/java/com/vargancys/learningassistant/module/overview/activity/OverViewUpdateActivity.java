package com.vargancys.learningassistant.module.overview.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.db.common.KnowListBean;
import com.vargancys.learningassistant.module.overview.adapter.UpdateTreeAdapter;
import com.vargancys.learningassistant.module.overview.view.OverViewUpdateView;
import com.vargancys.learningassistant.presenter.overview.BaseOverViewPresenter;
import com.vargancys.learningassistant.utils.CacheUtils;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ResourceUtils;
import com.vargancys.learningassistant.utils.ToastUtils;
import com.vargancys.learningassistant.widget.dialog.OverViewUpdatePopupWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @author Vagrancy
 * @date 2020/7/2
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识体系更新模块
 */
public class OverViewUpdateActivity extends BaseActivity implements OverViewUpdateView {
    @BindView(R.id.common_title_data)
    TextView commonTitleData;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    private BaseOverViewPresenter mPresenter;
    private List<KnowListBean> mBean = new ArrayList<>();
    private long overViewId;
    private UpdateTreeAdapter mAdapter;
    private OverViewUpdatePopupWindow mPopupWindow;

    @Override
    public int getLayoutId() {
        return R.layout.activity_overview_update;
    }

    @Override
    public void initView() {
        mPresenter = new BaseOverViewPresenter<OverViewUpdateView>(this);
        swipeRefresh.setColorSchemeColors(ResourceUtils.getColor(getContext(),R.color.pink));
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                autoData();
            }
        });
        mPopupWindow = new OverViewUpdatePopupWindow(this);
        mPopupWindow.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setOnUpdateClickListener(new OverViewUpdatePopupWindow.OnUpdateClickListener() {
            @Override
            public void exit() {
                mPopupWindow.dismiss();
            }

            @Override
            public void TextEmpty() {
                ToastUtils.ToastText(getContext(),R.string.overview_update_empty_text);
            }

            @Override
            public void determine(long id, String message) {
                mPresenter.updateOverViewData(id,message);
            }

            @Override
            public void cancel() {
                mPopupWindow.dismiss();
            }
        });
        try {
            mAdapter = new UpdateTreeAdapter(listView,getContext(),mBean,10);
            mAdapter.setOnUpdateClickListener(new UpdateTreeAdapter.OnUpdateClickListener() {
                @Override
                public void update(int position, long id) {
                    KnowListBean mItem =mBean.get(position);
                    mPopupWindow.setUpdateId(id);
                    mPopupWindow.setEdit(mItem.getTitle());
                    mPopupWindow.setMessage("要修改这"+mItem.getTitle()+"吗?");
                    mPopupWindow.setTitle(mItem.getTitle());
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
        mPresenter.getOverViewUpdateData(overViewId);
    }

    public void getSelectId(){
        overViewId = CacheUtils.getLong(getContext(), ConstantsUtils.OVERVIEW_ID,0);
    }

    @Override
    public void initToolbar() {
        commonTitleData.setText(getText(R.string.overview_update_toolbar));
    }

    public static void launch(Activity activity, long createId, int code) {
        Intent intent = new Intent(activity, OverViewUpdateActivity.class);
        intent.putExtra(ConstantsUtils.OVERVIEW_ID, createId);
        activity.startActivityForResult(intent, code);
    }

    @OnClick(R.id.common_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void getOverViewDataSuccess(List<KnowListBean> mBean) {
        swipeRefresh.setRefreshing(false);
        this.mBean.addAll(mBean);
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
        ToastUtils.ToastText(getContext(),R.string.overview_update_data_fail_text);
    }

    @Override
    public void updateOverViewDataFail(int error, String message) {
        ToastUtils.ToastText(getContext(),R.string.overview_update_list_fail_text);
    }

    @Override
    public void updateOverViewDataSuccess() {
        ToastUtils.ToastText(getContext(),R.string.overview_update_list_success_text);
        autoData();
    }
}
