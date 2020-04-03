package com.vargancys.learningassistant.module.home.activity.history;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.db.home.HomeKnowHistory;
import com.vargancys.learningassistant.module.home.view.HistoryShowView;
import com.vargancys.learningassistant.presenter.home.HistoryShowPresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import butterknife.BindView;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/06
 * version:1.0
 */
public class HistoryShowDefaultActivity extends BaseActivity implements HistoryShowView {
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
    private HistoryShowPresenter mPresenter;
    private long historyId;

    @Override
    public int getLayoutId() {
        return R.layout.activity_history_show_default;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        if (intent != null) {
            historyId = intent.getLongExtra(ConstantsUtils.KNOW_HISTORY_ID, 0);
        }
        mPresenter = new HistoryShowPresenter(this);
        mPresenter.getDefaultShowData(historyId);
    }

    @Override
    public void initToolbar() {
        commonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        commonImg.setVisibility(View.GONE);
    }

    public static void launch(Activity activity, long history_id) {
        Intent intent = new Intent(activity, HistoryShowDefaultActivity.class);
        intent.putExtra(ConstantsUtils.KNOW_HISTORY_ID, history_id);
        activity.startActivity(intent);
    }

    @Override
    public void showContentError(int error, String msg) {
        ToastUtils.ToastText(getContext(),"Error = "+error+"Msg = "+msg);
        scrollView.setVisibility(View.GONE);
        includeKnowEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void showContentFinish(HomeKnowHistory homeKnowHistory) {
        scrollView.setVisibility(View.VISIBLE);
        includeKnowEmpty.setVisibility(View.GONE);
        initData(homeKnowHistory);
    }

    private void initData(HomeKnowHistory history){
        commonTitle.setText(history.getTitle());
        insertShowTitle.setText(history.getTitle());
        insertShowSummary.setText(history.getSummary());
        insertShowShow.setText(history.getShow());
        insertShowExperience.setText(history.getExperience());
        insertShowExplain.setText(history.getExplain());
        insertShowHeed.setText(history.getHeed());
    }
}
