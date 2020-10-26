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
import com.vargancys.learningassistant.model.home.bean.FeelingBean;
import com.vargancys.learningassistant.module.home.activity.data.DataFeelingActivity;
import com.vargancys.learningassistant.module.home.adapter.CommonFeelingItemAdapter;
import com.vargancys.learningassistant.presenter.home.FeelingPresenter;
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
 * 知识展示感悟页面
 */
public class ShowFeelingActivity extends BaseActivity{
    private static final String TAG = "ShowFeelingActivity";

    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.include_know_empty)
    LinearLayout includeKnowEmpty;
    private FeelingPresenter mPresenter;
    private int item_id;
    private FeelingBean mFeeling;
    private ArrayList<FeelingBean.FeelingItemBean> mItem;
    private CommonFeelingItemAdapter mAdapter;
    private static int REQUEST_CODE = 2001;
    @Override
    public int getLayoutId() {
        return R.layout.activity_knowledge_show_feeling;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        if (intent != null) {
            item_id = intent.getIntExtra(ConstantsUtils.KNOWLEDGE_ARTICLE_ID, 0);
        }

        mPresenter = new FeelingPresenter(this);
        initAdapter();
        mPresenter.query(item_id);
    }

    private void initAdapter() {
        mAdapter = new CommonFeelingItemAdapter(getContext(),true,mItem);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE&&resultCode == DataFeelingActivity.RESULT_CODE&&data !=null){
            int state = data.getIntExtra(ConstantsUtils.ITEM_DELETE_STATUS,0);
            if(state == 1){
                finish();
            }else if(state == 2){
                mPresenter.query(item_id);
            }
        }
    }

    public static void launch(Activity activity, long item_id) {
        Intent intent = new Intent(activity, ShowFeelingActivity.class);
        intent.putExtra(ConstantsUtils.KNOWLEDGE_ARTICLE_ID,item_id);
        activity.startActivity(intent);
    }

    @OnClick({R.id.common_back,R.id.common_img})
    public void onViewClicked(View itemView){
        switch (itemView.getId()){
            case R.id.common_back:
                finish();
                break;
            case R.id.common_img:
                DataFeelingActivity.launch(ShowFeelingActivity.this,REQUEST_CODE,item_id);
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
        mFeeling = (FeelingBean) object;
        mItem.addAll(mFeeling.getItems());
        mAdapter.setTree(mItem);
        mAdapter.notifyDataSetChanged();
    }
}
