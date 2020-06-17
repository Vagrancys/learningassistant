package com.vargancys.learningassistant.module.ladder.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.db.ladder.LadderHelpBean;
import com.vargancys.learningassistant.module.ladder.view.LadderHelpDetailsView;
import com.vargancys.learningassistant.presenter.ladder.BaseLadderPresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ResourceUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Vagrancy
 * @date 2020/5/11
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 帮助详情页
 */
public class LadderHelpDetailsActivity extends BaseActivity implements LadderHelpDetailsView {
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.help_details_number)
    TextView helpDetailsNumber;
    @BindView(R.id.help_details_title)
    TextView helpDetailsTitle;
    @BindView(R.id.help_details_summary)
    TextView helpDetailsSummary;
    @BindView(R.id.help_details_content)
    TextView helpDetailsContent;
    @BindView(R.id.help_details_time)
    TextView helpDetailsTime;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    private BaseLadderPresenter mPresenter;
    private long helpId;

    @Override
    public int getLayoutId() {
        return R.layout.activity_help_details;
    }

    @Override
    public void initView() {
        helpId = getIntent().getIntExtra(ConstantsUtils.LADDER_HELP_ID,0);
        mPresenter = new BaseLadderPresenter(this);
        initListener();
        mPresenter.getLadderHelpDetailsData(helpId);
    }

    private void initListener() {
        swipeRefresh.setRefreshing(true);
        swipeRefresh.setColorSchemeColors(ResourceUtils.getColor(getContext(),R.color.pink));
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefresh.setRefreshing(true);
                mPresenter.getLadderHelpDetailsData(helpId);
            }
        });
    }

    @Override
    public void initToolbar() {
        commonTitle.setText(getText(R.string.help_details_toolbar));
    }

    public static void launch(Activity activity, long helpId) {
        Intent intent = new Intent(activity, LadderHelpDetailsActivity.class);
        intent.putExtra(ConstantsUtils.LADDER_HELP_ID, helpId);
        activity.startActivity(intent);
    }

    @Override
    public void showHelpDetailsError(int error, String message) {
        swipeRefresh.setRefreshing(false);
        ToastUtils.ToastText(getContext(),"Error = "+error+", Message ="+message);
        helpDetailsNumber.setText("--");
        helpDetailsContent.setText("--");
        helpDetailsSummary.setText("--");
        helpDetailsTime.setText("--");
        helpDetailsTitle.setText("--");
    }

    @Override
    public void showHelpDetailsFinish(LadderHelpBean mBean) {
        swipeRefresh.setRefreshing(false);
        helpDetailsTitle.setText(mBean.getTitle());
        helpDetailsTime.setText(mBean.getTime());
        helpDetailsSummary.setText(mBean.getSummary());
        helpDetailsNumber.setText(mBean.getNumber());
        helpDetailsContent.setText(mBean.getContent());
    }

    @OnClick({R.id.common_back})
    public void onViewClicked(View itemView){
        finish();
    }
}
