package com.vargancys.learningassistant.module.overview.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.db.overview.OverViewListContent;
import com.vargancys.learningassistant.module.overview.adapter.OverViewSearchAdapter;
import com.vargancys.learningassistant.module.overview.view.BaseOverView;
import com.vargancys.learningassistant.presenter.overview.BaseOverViewPresenter;
import com.vargancys.learningassistant.utils.CacheUtils;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Vagrancy
 * @date 2020/4/3
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识体系搜索页面
 */
public class OverViewSearchActivity extends BaseActivity implements BaseOverView {
    private static String TAG = "OverViewSearchActivity";
    @BindView(R.id.common_back)
    ImageView commonBack;
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.common_img)
    ImageView commonImg;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.fragment_empty)
    LinearLayout fragmentEmpty;
    @BindView(R.id.common_img_one)
    ImageView commonImgOne;
    private long overviewId = 0;
    private OverViewSearchAdapter mAdapter;
    private int REQUEST_CODE = 2000;
    private List<OverViewListContent> mObjects = new ArrayList<>();
    private BaseOverViewPresenter mPresenter;
    private AlertDialog.Builder mAlert;

    @Override
    public int getLayoutId() {
        return R.layout.activity_overview_search;
    }

    @Override
    public void initView() {
        mPresenter = new BaseOverViewPresenter(this);
        mAdapter = new OverViewSearchAdapter(getContext(), mObjects);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
        mPresenter.getAllContentData();
        initData();
        initListener();
    }

    private void initData() {
        mAlert = new AlertDialog.Builder(getContext());
        mAlert.setPositiveButton(R.string.common_cancel_text, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                overviewId = 0;
            }
        });
        mAlert.setNegativeButton(R.string.common_determine_text, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CacheUtils.putLong(getContext(), ConstantsUtils.OVERVIEW_ID, overviewId);
                overviewId = 0;
                finish();
            }
        });
    }

    private void initListener() {
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                overviewId = mObjects.get(position).getId();
                mAlert.setTitle(mObjects.get(position).getTitle());
                mAlert.setMessage(mObjects.get(position).getSummary());
                mAlert.show();
            }
        });
    }

    @Override
    public void initToolbar() {

        commonTitle.setVisibility(View.GONE);

        commonImg.setImageResource(R.drawable.common_add_normal);

        commonImgOne.setImageResource(R.drawable.know_search_selector);
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, OverViewSearchActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void getAllData(List<OverViewListContent> objects) {
        if (fragmentEmpty.getVisibility() == View.VISIBLE) {
            fragmentEmpty.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
        mObjects.addAll(objects);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void getAllDataError(int error, String message) {
        ToastUtils.ToastText(getContext(), "Error = " + error + ", Message =" + message);
        recyclerView.setVisibility(View.GONE);
        fragmentEmpty.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.common_back,R.id.common_img,R.id.common_img_one})
    public void onViewClicked(View itemView) {
        switch (itemView.getId()) {
            case R.id.common_back:
                finish();
                break;
            case R.id.common_img:
                OverViewAddActivity.launch(OverViewSearchActivity.this, REQUEST_CODE);
                break;
            case R.id.common_img_one:
                OverViewQueryActivity.launch(this);
                break;
        }
    }
}
