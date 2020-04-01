package com.vargancys.learningassistant.module.home.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.db.home.HomeKnowContent;
import com.vargancys.learningassistant.db.home.HomeKnowItem;
import com.vargancys.learningassistant.module.home.activity.insert.KnowInsertDefaultActivity;
import com.vargancys.learningassistant.module.home.activity.insert.KnowInsertFifthActivity;
import com.vargancys.learningassistant.module.home.activity.insert.KnowInsertFirstActivity;
import com.vargancys.learningassistant.module.home.activity.insert.KnowInsertFourthActivity;
import com.vargancys.learningassistant.module.home.activity.insert.KnowInsertSecondActivity;
import com.vargancys.learningassistant.module.home.activity.insert.KnowInsertThirdActivity;
import com.vargancys.learningassistant.module.home.activity.show.KnowShowDefaultActivity;
import com.vargancys.learningassistant.module.home.activity.show.KnowShowFifthActivity;
import com.vargancys.learningassistant.module.home.activity.show.KnowShowFirstActivity;
import com.vargancys.learningassistant.module.home.activity.show.KnowShowFourthActivity;
import com.vargancys.learningassistant.module.home.activity.show.KnowShowSecondActivity;
import com.vargancys.learningassistant.module.home.activity.show.KnowShowThirdActivity;
import com.vargancys.learningassistant.module.home.adapter.KnowSearchAdapter;
import com.vargancys.learningassistant.module.home.view.HomeCommonView;
import com.vargancys.learningassistant.persenter.home.HomeCommonPresenter;
import com.vargancys.learningassistant.utils.JumpRouteUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/01
 * version:1.0
 */
public class KnowSearchActivity extends BaseActivity implements HomeCommonView {
    @BindView(R.id.back_menu)
    ImageView backMenu;
    @BindView(R.id.search_menu)
    ImageView searchMenu;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.search_edit)
    EditText searchEdit;

    private HomeCommonPresenter mPresenter;
    private KnowSearchAdapter mAdapter;
    private JumpRouteUtils jumpRouteUtils;
    private ArrayList<HomeKnowItem> homeKnowItems = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_know_search;
    }

    @Override
    public void initView() {
        mPresenter = new HomeCommonPresenter(this);
        jumpRouteUtils = new JumpRouteUtils().getJumpRouteUtils();
        mAdapter = new KnowSearchAdapter(getContext(),homeKnowItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
        initListener();
    }

    private void initListener() {
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                HomeKnowItem homeKnowItem =homeKnowItems.get(position);
                if(homeKnowItem.getCreateClass()){
                    if(homeKnowItem.getHave()){
                        launchDemonstrateActivity(homeKnowItem.getActivity());
                    }else{
                        launchShowActivity(homeKnowItem.getId().intValue(),homeKnowItem.getLevel());
                    }
                }else{
                    if(homeKnowItem.getHave()){
                        ToastUtils.ToastText(getContext(),"这需要官方来创建!个人不能创建!");
                    }else{
                        launchInsertActivity(homeKnowItem.getId().intValue(),homeKnowItem.getLevel());
                    }
                }
            }
        });
    }

    private void launchDemonstrateActivity(String activity) {
        if(!activity.isEmpty()){
            jumpRouteUtils.JumpActivity(this,activity);
        }else{
            ToastUtils.ToastText(getContext(),"不好意识！还没有完成该知识的演示!");
        }
    }

    private void launchInsertActivity(int item_id,int level) {
        Log.e("launch","level="+level);
        switch (level){
            case 1:
                KnowInsertFirstActivity.launch(this,item_id);
                break;
            case 2:
                KnowInsertSecondActivity.launch(this,item_id);
                break;
            case 3:
                KnowInsertThirdActivity.launch(this,item_id);
                break;
            case 4:
                KnowInsertFourthActivity.launch(this,item_id);
                break;
            case 5:
                KnowInsertFifthActivity.launch(this,item_id);
                break;
            default:
                KnowInsertDefaultActivity.launch(this,item_id);
                break;
        }
    }

    private void launchShowActivity(int item_id,int level) {
        switch (level){
            case 1:
                KnowShowFirstActivity.launch(this,item_id);
                break;
            case 2:
                KnowShowSecondActivity.launch(this,item_id);
                break;
            case 3:
                KnowShowThirdActivity.launch(this,item_id);
                break;
            case 4:
                KnowShowFourthActivity.launch(this,item_id);
                break;
            case 5:
                KnowShowFifthActivity.launch(this,item_id);
                break;
            default:
                KnowShowDefaultActivity.launch(this,item_id);
                break;
        }
    }

    @Override
    public void initToolbar() {
        backMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        searchMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!searchEdit.getText().toString().isEmpty()){
                    mPresenter.getSearchAllData(searchEdit.getText().toString());
                }
                searchEdit.setText("");
            }
        });
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, KnowSearchActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void showAllDataError(int error, String message) {
        ToastUtils.ToastText(getContext(),"error ="+error+", Message ="+message);
    }

    @Override
    public void showAllDataFinish(List<HomeKnowItem> homeKnowItem) {
        homeKnowItems.clear();
        homeKnowItems.addAll(homeKnowItem);
        mAdapter.notifyDataSetChanged();
    }
}
