package com.vargancys.learningassistant.module.home.activity.history;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.bean.home.HomeKnowHistory;
import com.vargancys.learningassistant.module.home.view.HistoryShowView;
import com.vargancys.learningassistant.presenter.home.HistoryShowPresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Vagrancy
 * @date 2020/3/6
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识历史显示第五级页面
 */
public class HistoryShowFifthActivity extends BaseActivity implements HistoryShowView {
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
    private HistoryShowPresenter mPresenter;
    private long historyId;
    @Override
    public int getLayoutId() {
        return R.layout.activity_know_show_fifth;
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

    public static void launch(Activity activity, long history_id) {
        Intent intent = new Intent(activity, HistoryShowFifthActivity.class);
        intent.putExtra(ConstantsUtils.KNOW_HISTORY_ID,history_id);
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

    @OnClick({R.id.common_back})
    public void onViewClicked(View itemView){
        finish();
    }
}
