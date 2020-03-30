package com.vargancys.learningassistant.module.home.activity.show;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.db.home.HomeKnowContent;
import com.vargancys.learningassistant.db.home.HomeKnowFunction;
import com.vargancys.learningassistant.module.home.activity.ShowKnowDataActivity;
import com.vargancys.learningassistant.module.home.adapter.HomeKnowShowFourthAdapter;
import com.vargancys.learningassistant.module.home.view.KnowShowView;
import com.vargancys.learningassistant.persenter.home.KnowShowPresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/06
 * version:1.0
 */
public class KnowShowFourthActivity extends BaseActivity implements KnowShowView {
    private static final String TAG = "KnowShowFourthActivity";
    @BindView(R.id.common_back)
    ImageView commonBack;
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.common_img)
    ImageView commonImg;
    @BindView(R.id.insert_show_title)
    TextView insertShowTitle;
    @BindView(R.id.insert_show_summary)
    TextView insertShowSummary;
    @BindView(R.id.insert_show_count)
    TextView insertShowCount;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.show_empty_fourth)
    TextView showEmptyFourth;
    @BindView(R.id.insert_show_heed)
    TextView insertShowHeed;
    @BindView(R.id.insert_show_experience)
    TextView insertShowExperience;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.include_know_empty)
    LinearLayout includeKnowEmpty;

    private KnowShowPresenter mPresenter;
    private long item_id;
    private static int REQUEST_CODE = 2001;
    private HomeKnowShowFourthAdapter mAdapter;
    private List<HomeKnowFunction> mFunction = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_know_show_fourth;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        if(intent !=null){
            item_id = intent.getLongExtra(ConstantsUtils.KNOW_ITEM_ID,0);
        }
        mPresenter = new KnowShowPresenter(this);
        init();
        mPresenter.getDefaultShowData(item_id);
    }

    private void init() {
        mAdapter = new HomeKnowShowFourthAdapter(getContext(),mFunction);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initToolbar() {
        commonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        commonImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowKnowDataActivity.launch(KnowShowFourthActivity.this,REQUEST_CODE,item_id);
            }
        });
    }

    public static void launch(Activity activity, long item_id) {
        Intent intent = new Intent(activity, KnowShowFourthActivity.class);
        intent.putExtra(ConstantsUtils.KNOW_ITEM_ID, item_id);
        activity.startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE&&resultCode == ShowKnowDataActivity.RESULT_CODE&&data !=null){
            int state = data.getIntExtra(ConstantsUtils.ITEM_DELETE_STATUS,0);
            if(state == 1){
                finish();
            }else if(state == 2){
                mPresenter.getRefreshDefaultShowData(item_id);
            }
            Log.e(TAG,"state ="+state);
        }
    }

    @Override
    public void showContentError(int error, String msg) {
        ToastUtils.ToastText(getContext(),"Error = "+error+"msg ="+msg);
        scrollView.setVisibility(View.GONE);
        includeKnowEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void showContentFinish(HomeKnowContent homeKnowContent) {
        scrollView.setVisibility(View.VISIBLE);
        includeKnowEmpty.setVisibility(View.GONE);
        initData(homeKnowContent);
    }

    private void initData(HomeKnowContent homeKnowContent) {
        insertShowTitle.setText(homeKnowContent.getTitle());
        insertShowSummary.setText(homeKnowContent.getSummary());
        insertShowHeed.setText(homeKnowContent.getHeed());
        insertShowExperience.setText(homeKnowContent.getExperience());
        int count = homeKnowContent.getHomeKnowFunctions().size();
        if(count <= 0){
            showEmptyFourth.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }else{
            showEmptyFourth.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
        insertShowCount.setText(String.valueOf(homeKnowContent.getHomeKnowFunctions().size()));
        mFunction.addAll(homeKnowContent.getHomeKnowFunctions());
        commonTitle.setText(homeKnowContent.getTitle());
        mAdapter.notifyDataSetChanged();
    }
}

