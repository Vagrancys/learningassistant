package com.vargancys.learningassistant.module.home.activity.show;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.db.home.HomeKnowContent;
import com.vargancys.learningassistant.module.home.activity.ShowKnowDataActivity;
import com.vargancys.learningassistant.module.home.view.KnowShowView;
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
 * 知识展示一级页面
 */
public class KnowShowFirstActivity extends BaseActivity implements KnowShowView {

    private static final String TAG = "KnowShowFirstActivity";

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
    private long itemId;
    private static int REQUEST_CODE = 2001;

    @Override
    public int getLayoutId() {
        return R.layout.activity_know_show_first;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        if (intent != null) {
            itemId = intent.getLongExtra(ConstantsUtils.KNOW_ITEM_ID, 0);
        }
        mPresenter = new KnowShowPresenter(this);
        mPresenter.getDefaultShowData(itemId);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE&&resultCode == ShowKnowDataActivity.RESULT_CODE&&data !=null){
            int state = data.getIntExtra(ConstantsUtils.ITEM_DELETE_STATUS,0);
            if(state == 1){
                finish();
            }else if(state == 2){
                mPresenter.getRefreshDefaultShowData(itemId);
            }
        }
    }

    public static void launch(Activity activity, long item_id) {
        Intent intent = new Intent(activity, KnowShowFirstActivity.class);
        intent.putExtra(ConstantsUtils.KNOW_ITEM_ID, item_id);
        activity.startActivity(intent);
    }

    @Override
    public void showContentFinish(HomeKnowContent homeKnowContent) {
        scrollView.setVisibility(View.VISIBLE);
        includeKnowEmpty.setVisibility(View.GONE);
        initData(homeKnowContent);
    }

    @Override
    public void showContentError(int error, String msg) {
        ToastUtils.ToastText(getContext(),"Error = "+error+"Msg = "+msg);
        scrollView.setVisibility(View.GONE);
        includeKnowEmpty.setVisibility(View.VISIBLE);
    }

    private void initData(HomeKnowContent homeKnowContent){
        insertShowTitle.setText(homeKnowContent.getTitle());
        insertShowSummary.setText(homeKnowContent.getSummary());
        insertShowShow.setText(homeKnowContent.getShow());
        insertShowExplain.setText(homeKnowContent.getExplain());
        insertShowExperience.setText(homeKnowContent.getExperience());
        insertShowHeed.setText(homeKnowContent.getHeed());
        commonTitle.setText(homeKnowContent.getTitle());
    }

    @OnClick({R.id.common_back,R.id.common_img})
    public void onViewClicked(View itemView){
        switch (itemView.getId()){
            case R.id.common_back:
                finish();
                break;
            case R.id.common_img:
                ShowKnowDataActivity.launch(KnowShowFirstActivity.this,REQUEST_CODE,itemId);
                break;
        }
    }
}
