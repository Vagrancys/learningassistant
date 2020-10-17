package com.vargancys.learningassistant.module.home.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.bean.home.KnowLedgeBean;
import com.vargancys.learningassistant.module.home.activity.insert.InsertAidedActivity;
import com.vargancys.learningassistant.module.home.activity.insert.InsertArticleActivity;
import com.vargancys.learningassistant.module.home.activity.insert.InsertClassActivity;
import com.vargancys.learningassistant.module.home.activity.insert.InsertDefaultActivity;
import com.vargancys.learningassistant.module.home.activity.insert.KnowInsertFifthActivity;
import com.vargancys.learningassistant.module.home.activity.insert.InsertBookActivity;
import com.vargancys.learningassistant.module.home.activity.show.ShowAidedActivity;
import com.vargancys.learningassistant.module.home.activity.show.ShowArticleActivity;
import com.vargancys.learningassistant.module.home.activity.show.ShowBookActivity;
import com.vargancys.learningassistant.module.home.activity.show.ShowCommonDefaultActivity;
import com.vargancys.learningassistant.module.home.activity.show.ShowCommonFifthActivity;
import com.vargancys.learningassistant.module.home.activity.show.ShowClassActivity;
import com.vargancys.learningassistant.module.home.adapter.KnowSearchAdapter;
import com.vargancys.learningassistant.module.home.view.HomeCommonView;
import com.vargancys.learningassistant.presenter.home.HomeCommonPresenter;
import com.vargancys.learningassistant.utils.JumpRouteUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/01
 * version:1.0
 * 知识搜索页面
 */
public class KnowSearchActivity extends BaseActivity implements HomeCommonView {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.search_edit)
    EditText searchEdit;

    private HomeCommonPresenter mPresenter;
    private KnowSearchAdapter mAdapter;
    private JumpRouteUtils jumpRouteUtils;
    private ArrayList<KnowLedgeBean> homeKnowItems = new ArrayList<>();
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
                KnowLedgeBean homeKnowItem =homeKnowItems.get(position);
                if(homeKnowItem.getCreateClass()){
                    if(homeKnowItem.getHave()){
                        launchDemonstrateActivity(homeKnowItem.getActivity());
                    }else{
                        launchShowActivity(homeKnowItem.getId(),homeKnowItem.getLevel());
                    }
                }else{
                    if(homeKnowItem.getHave()){
                        ToastUtils.ToastText(getContext(),"这需要官方来创建!个人不能创建!");
                    }else{
                        launchInsertActivity(homeKnowItem.getId(),homeKnowItem.getLevel());
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
        switch (level){
            case 1:
                InsertArticleActivity.launch(this,item_id);
                break;
            case 2:
                InsertBookActivity.launch(this,item_id);
                break;
            case 3:
                InsertAidedActivity.launch(this,item_id);
                break;
            case 4:
                InsertClassActivity.launch(this,item_id);
                break;
            case 5:
                KnowInsertFifthActivity.launch(this,item_id);
                break;
            default:
                InsertDefaultActivity.launch(this,item_id);
                break;
        }
    }

    private void launchShowActivity(int item_id,int level) {
        switch (level){
            case 1:
                ShowArticleActivity.launch(this,item_id);
                break;
            case 2:
                ShowBookActivity.launch(this,item_id);
                break;
            case 3:
                ShowAidedActivity.launch(this,item_id);
                break;
            case 4:
                ShowClassActivity.launch(this,item_id);
                break;
            case 5:
                ShowCommonFifthActivity.launch(this,item_id);
                break;
            default:
                ShowCommonDefaultActivity.launch(this,item_id);
                break;
        }
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
    public void showAllDataFinish(List<KnowLedgeBean> homeKnowItem) {
        homeKnowItems.clear();
        homeKnowItems.addAll(homeKnowItem);
        mAdapter.notifyDataSetChanged();
    }

    @OnClick({R.id.back_menu,R.id.search_menu})
    public void onViewClicked(View itemView){
        switch (itemView.getId()){
            case R.id.back_menu:
                finish();
                break;
            case R.id.search_menu:
                if(!searchEdit.getText().toString().isEmpty()){
                    mPresenter.getSearchAllData(searchEdit.getText().toString());
                }
                searchEdit.setText("");
                break;
        }
    }
}
