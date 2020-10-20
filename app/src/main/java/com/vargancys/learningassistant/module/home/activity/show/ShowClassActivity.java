package com.vargancys.learningassistant.module.home.activity.show;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.model.home.bean.ClassBean;
import com.vargancys.learningassistant.model.home.bean.ClassTreeBean;
import com.vargancys.learningassistant.module.home.activity.ShowKnowDataActivity;
import com.vargancys.learningassistant.module.home.adapter.CommonClassTreeAdapter;
import com.vargancys.learningassistant.presenter.home.ClassPresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/06
 * version:1.0
 * 知识展示四级页面
 */
public class ShowClassActivity extends BaseActivity{
    private static final String TAG = "ShowClassActivity";
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.include_know_empty)
    LinearLayout includeKnowEmpty;

    private ClassPresenter mPresenter;
    private static int REQUEST_CODE = 2001;
    private int article_id;
    private ClassBean mClass;
    private CommonClassTreeAdapter mAdapter;
    private ArrayList<ClassTreeBean> mTree = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_knowledge_show_class;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        if(intent !=null){
            article_id = intent.getIntExtra(ConstantsUtils.KNOWLEDGE_ARTICLE_ID,0);
        }
        mPresenter = new ClassPresenter(this);
        init();
        mPresenter.query(article_id);
    }

    private void init() {
        mAdapter = new CommonClassTreeAdapter(getContext(),mTree);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
    }

    public static void launch(Activity activity, long item_id) {
        Intent intent = new Intent(activity, ShowClassActivity.class);
        intent.putExtra(ConstantsUtils.KNOWLEDGE_ARTICLE_ID, item_id);
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
                mPresenter.query(article_id);
            }
        }
    }

    @OnClick({R.id.common_back,R.id.common_img})
    public void onViewClicked(View itemView){
        switch (itemView.getId()){
            case R.id.common_back:
                finish();
                break;
            case R.id.common_img:
                ShowKnowDataActivity.launch(ShowClassActivity.this,REQUEST_CODE,article_id);
                break;
        }
    }

    @Override
    public void onError(String message) {
        ToastUtils.ToastText(getContext(),R.string.common_error);
    }

    @Override
    public void onFail() {
        ToastUtils.ToastText(getContext(),R.string.common_fail);
    }

    @Override
    public void onSuccess(Object object) {
        mClass = (ClassBean) object;
        mAdapter.setTrees(mClass.getTrees());
        mAdapter.notifyDataSetChanged();
    }
}

