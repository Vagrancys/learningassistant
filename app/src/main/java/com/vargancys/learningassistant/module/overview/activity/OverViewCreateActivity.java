package com.vargancys.learningassistant.module.overview.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.db.overview.OverViewListItem;
import com.vargancys.learningassistant.module.overview.adapter.OverViewCreateAdapter;
import com.vargancys.learningassistant.module.overview.view.OverViewCreateView;
import com.vargancys.learningassistant.presenter.overview.BaseOverViewPresenter;
import com.vargancys.learningassistant.utils.ToastUtils;
import com.vargancys.learningassistant.widget.dialog.OverViewCreateDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @author Vagrancy
 * @date 2020/6/30
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 个人知识体系管理中心
 */
public class OverViewCreateActivity extends BaseActivity implements OverViewCreateView {
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    private BaseOverViewPresenter mPresenter;
    private OverViewCreateAdapter mAdapter;
    private int OVERVIEW_CREATE = 2001;
    private AlertDialog.Builder mAlert;
    private long overviewId = 0;
    private long createId = 1;
    private OverViewCreateDialog mPopup;
    private List<OverViewListItem> mItems = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_overview_create;
    }

    @Override
    public void initView() {

        mPresenter = new BaseOverViewPresenter<OverViewCreateView>(this);
        swipeRefresh.setColorSchemeResources(R.color.pink);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                autoData();
            }
        });

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
                    mPresenter.insertOverViewCreateData(overviewId);
                }
                dialog.dismiss();
            }
        });
        /**
         * 数据库缓存 知识缓存到数据库中
         * 同步 主动同步服务器的知识或者设置被动时间同步服务器的知识
         * 上传 更新,添加,修改,刪除本地知识,然后上传到服务器里
         * 下载 下载最新的知识或者其他人的知识
         *
         */
        mPopup = new OverViewCreateDialog(this);
        mPopup.setOnDialogClickListener(new OverViewCreateDialog.OnDialogClickListener() {
            @Override
            public void onClose() {
                mPopup.dismiss();
            }

            @Override
            public void onDelete(int position) {
                OverViewListItem mItem = mItems.get(position);
                mPresenter.deleteOverViewCreateData(mItem.getId());
            }

            @Override
            public void onUpdate(int position) {
                OverViewListItem mItem = mItems.get(position);
                OverViewUpdateActivity.launch(OverViewCreateActivity.this,mItem.getId(),OVERVIEW_CREATE);
            }

            @Override
            public void onInsert(int position) {
                OverViewListItem mItem = mItems.get(position);
                OverViewInsertActivity.launch(OverViewCreateActivity.this,mItem.getId(),OVERVIEW_CREATE);
            }
        });

        mAdapter = new OverViewCreateAdapter(getContext(),mItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                OverViewListItem bean = mItems.get(position);
                overviewId = bean.getId();
                mAlert.setTitle(bean.getTitle());
                mAlert.setMessage("是否关注"+bean.getTitle()+"并学习该体系!");
                mAlert.show();
            }
        });
        mAdapter.setOnItemLongClickListener(new BaseRecyclerAdapter.OnItemLongClickListener() {
            @Override
            public void OnItemLongClick(int position) {
                OverViewListItem mItem = mItems.get(position);
                mPopup.setTitle(mItem.getTitle());
                mPopup.setPosition(position);
                mPopup.showAsDropDown(recyclerView);
            }
        });
    }

    @Override
    public void initToolbar() {
        commonTitle.setText(getText(R.string.overview_create_toolbar));
    }

    private void autoData(){
        swipeRefresh.setRefreshing(true);
        mPresenter.getOverViewData(createId);
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, OverViewCreateActivity.class);
        activity.startActivity(intent);
    }

    @OnClick({R.id.common_back, R.id.common_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.common_back:
                finish();
                break;
            case R.id.common_img:
                OverViewAddActivity.launch(this,OVERVIEW_CREATE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data.getData() != null){
            if(requestCode == OVERVIEW_CREATE){
                switch (resultCode){
                    case 30001:
                        break;
                }
            }
        }
    }

    @Override
    public void getOverViewCreateDataSuccess(List<OverViewListItem> mItem) {
        swipeRefresh.setRefreshing(false);
        mItems.clear();

        this.mItems.addAll(mItem);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void getOverViewCreateDataFail(int error, String message) {
        swipeRefresh.setRefreshing(false);
        ToastUtils.ToastText(getContext(),"Error ="+error+",Message ="+message);
    }

    @Override
    public void insertCreateDataFail(int error, String message) {
        ToastUtils.ToastText(getContext(),R.string.overview_hall_insert_fail_text);
    }

    @Override
    public void insertCreateDataSuccess() {
        ToastUtils.ToastText(getContext(),R.string.overview_hall_insert_win_text);
        finish();
    }

    @Override
    public void deleteCreateDataFail(int error, String message) {
        ToastUtils.ToastText(getContext(),"Error = "+error+",Message ="+message);
    }

    @Override
    public void deleteCreateDataSuccess() {
        ToastUtils.ToastText(getContext(),R.string.overview_delete_success_text);
        autoData();
    }
}
