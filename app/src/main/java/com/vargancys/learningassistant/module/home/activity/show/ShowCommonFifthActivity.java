package com.vargancys.learningassistant.module.home.activity.show;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.bean.home.HomeKnowContent;
import com.vargancys.learningassistant.module.home.activity.ShowKnowDataActivity;
import com.vargancys.learningassistant.module.home.view.ShowCommonView;
import com.vargancys.learningassistant.presenter.home.KnowShowPresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/06
 * version:1.0
 * 知识展示五级页面
 */
public class ShowCommonFifthActivity extends BaseActivity implements ShowCommonView {
    private static final String TAG = "KnowShowFifthActivity";

    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.insert_show_title)
    TextView insertShowTitle;
    @BindView(R.id.insert_show_summary)
    TextView insertShowSummary;
    @BindView(R.id.insert_show_show)
    TextView insertShowShow;
    @BindView(R.id.insert_show_explain)
    TextView insertShowExplain;
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
    @Override
    public int getLayoutId() {
        return R.layout.activity_know_show_fifth;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        if (intent != null) {
            item_id = intent.getLongExtra(ConstantsUtils.KNOW_ITEM_ID, 0);
        }

        mPresenter = new KnowShowPresenter(this);
        mPresenter.getDefaultShowData(item_id);
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
        }
    }

    public static void launch(Activity activity, long item_id) {
        Intent intent = new Intent(activity, ShowCommonFifthActivity.class);
        intent.putExtra(ConstantsUtils.KNOW_ITEM_ID,item_id);
        activity.startActivity(intent);
    }

    @Override
    public void showContentError(int error, String msg) {
        ToastUtils.ToastText(getContext(),"Error = "+error +"Msg ="+msg);
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
        insertShowShow.setText(homeKnowContent.getShow());
        insertShowHeed.setText(homeKnowContent.getHeed());
        insertShowExperience.setText(homeKnowContent.getExperience());
        insertShowExplain.setText(homeKnowContent.getExplain());
        commonTitle.setText(homeKnowContent.getTitle());
    }

    @OnClick({R.id.common_back,R.id.common_img})
    public void onViewClicked(View itemView){
        switch (itemView.getId()){
            case R.id.common_back:
                finish();
                break;
            case R.id.common_img:
                ShowKnowDataActivity.launch(ShowCommonFifthActivity.this,REQUEST_CODE,item_id);
                break;
        }
    }
}