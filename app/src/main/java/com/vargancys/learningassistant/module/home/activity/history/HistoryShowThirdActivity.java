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
import com.vargancys.learningassistant.db.home.HomeKnowContent;
import com.vargancys.learningassistant.db.home.HomeKnowHistory;
import com.vargancys.learningassistant.module.home.view.HistoryShowView;
import com.vargancys.learningassistant.module.home.view.KnowShowView;
import com.vargancys.learningassistant.persenter.home.HistoryShowPresenter;
import com.vargancys.learningassistant.persenter.home.KnowShowPresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import butterknife.BindView;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/06
 * version:1.0
 */
public class HistoryShowThirdActivity extends BaseActivity implements HistoryShowView {
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
    private long item_id;

    @Override
    public int getLayoutId() {
        return R.layout.activity_history_show_third;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        if (intent != null) {
            item_id = intent.getLongExtra(ConstantsUtils.KNOW_ITEM_ID, 0);
        }

        mPresenter = new HistoryShowPresenter(this);
        mPresenter.getDefaultShowData(item_id);
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

    public static void launch(Activity activity,long item_id) {
        Intent intent = new Intent(activity, HistoryShowThirdActivity.class);
        intent.putExtra(ConstantsUtils.KNOW_ITEM_ID, item_id);
        activity.startActivity(intent);
    }

    @Override
    public void showContentError(int error, String msg) {
        ToastUtils.ToastText(getContext(),"Error = "+error +"Msg ="+msg);
        scrollView.setVisibility(View.GONE);
        includeKnowEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void showContentFinish(HomeKnowHistory homeKnowHistory) {
        scrollView.setVisibility(View.VISIBLE);
        includeKnowEmpty.setVisibility(View.GONE);
        initData(homeKnowHistory);
    }

    private void initData(HomeKnowHistory homeKnowHistory) {
        insertShowTitle.setText(homeKnowHistory.getTitle());
        insertShowSummary.setText(homeKnowHistory.getSummary());
        insertShowShow.setText(homeKnowHistory.getShow());
        insertShowHeed.setText(homeKnowHistory.getHeed());
        insertShowExperience.setText(homeKnowHistory.getExperience());
        insertShowExplain.setText(homeKnowHistory.getExplain());
        commonTitle.setText(homeKnowHistory.getTitle());
    }
}
