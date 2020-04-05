package com.vargancys.learningassistant.module.overview.activity;

import android.app.Activity;
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
import com.vargancys.learningassistant.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/03
 * version:1.0
 */
public class OverViewSearchActivity extends BaseActivity implements BaseOverView {
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
    private OverViewSearchAdapter mAdapter;
    private int REQUEST_CODE = 2000;
    private List<OverViewListContent> mObjects = new ArrayList<>();
    private BaseOverViewPresenter mPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_overview_search;
    }

    @Override
    public void initView() {
        mPresenter = new BaseOverViewPresenter(this);
        mAdapter = new OverViewSearchAdapter(getContext(),mObjects);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
        mPresenter.getAllContentData();
        initListener();
    }

    private void initListener() {
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                ToastUtils.ToastText(getContext(),"未完工");
            }
        });
    }

    @Override
    public void initToolbar() {
        commonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        commonTitle.setVisibility(View.GONE);

        commonImg.setImageResource(R.drawable.common_add_normal);

        commonImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OverViewAddActivity.launch(OverViewSearchActivity.this,REQUEST_CODE);
            }
        });
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, OverViewSearchActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void getAllData(List<OverViewListContent> objects) {
        if(fragmentEmpty.getVisibility() == View.VISIBLE){
            fragmentEmpty.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
        mObjects.addAll(objects);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void getAllDataError(int error, String message) {
        ToastUtils.ToastText(getContext(),"Error = "+error+", Message ="+message);
        recyclerView.setVisibility(View.GONE);
        fragmentEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void TidyAllData() {

    }

    @Override
    public void saveDataFinish() {

    }

    @Override
    public void saveDataError(int error, String message) {

    }
}
