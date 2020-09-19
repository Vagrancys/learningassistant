package com.vargancys.learningassistant.module.mine.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.bean.ladder.LadderDataBean;
import com.vargancys.learningassistant.module.mine.view.ChallengeDetailsView;
import com.vargancys.learningassistant.presenter.mine.BaseMinePresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Vagrancy
 * @date 2020/5/26
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 个人中心天梯各项详情页面
 */
public class ChallengeDetailsActivity extends BaseActivity implements ChallengeDetailsView {
    @BindView(R.id.common_title_data)
    TextView commonTitleData;
    @BindView(R.id.challenge_details_title)
    TextView challengeDetailsTitle;
    @BindView(R.id.challenge_details_upgrade)
    TextView challengeDetailsUpgrade;
    @BindView(R.id.challenge_details_difficulty)
    TextView challengeDetailsDifficulty;
    @BindView(R.id.challenge_details_highest)
    TextView challengeDetailsHighest;
    @BindView(R.id.challenge_details_fail)
    TextView challengeDetailsFail;
    @BindView(R.id.challenge_details_time)
    TextView challengeDetailsTime;
    @BindView(R.id.challenge_details_total)
    TextView challengeDetailsTotal;
    @BindView(R.id.challenge_details_master)
    TextView challengeDetailsMaster;
    @BindView(R.id.challenge_details_chance)
    TextView challengeDetailsChance;
    @BindView(R.id.challenge_details_head)
    TextView challengeDetailsHead;
    private long challengeId;
    private BaseMinePresenter mPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_challenge_details;
    }

    @Override
    public void initView() {
        challengeId = getIntent().getLongExtra(ConstantsUtils.CHALLENGE_DETAILS_ID, 0);
        mPresenter = new BaseMinePresenter(this);
        mPresenter.getChallengeDetailsData(challengeId);
    }

    @Override
    public void initToolbar() {
        commonTitleData.setText(getText(R.string.challenge_details_toolbar));
    }

    public static void launch(Activity activity, long id) {
        Intent intent = new Intent(activity, ChallengeDetailsActivity.class);
        intent.putExtra(ConstantsUtils.CHALLENGE_DETAILS_ID, id);
        activity.startActivity(intent);
    }

    @Override
    public void loadChallengeDetailsDataError(int error, String message) {
        ToastUtils.ToastText(getContext(),"Error ="+error+", Message ="+message);
        challengeDetailsChance.setText("--");
        challengeDetailsDifficulty.setText("--");
        challengeDetailsFail.setText("--");
        challengeDetailsHead.setText("--");
        challengeDetailsHighest.setText("--");
        challengeDetailsMaster.setText("--");
        challengeDetailsTime.setText("--");
        challengeDetailsTitle.setText("--");
        challengeDetailsTotal.setText("--");
        challengeDetailsUpgrade.setText("--");
    }

    @Override
    public void loadChallengeDetailsData(LadderDataBean mBean) {
        challengeDetailsChance.setText(String.valueOf(mBean.getChance()));
        challengeDetailsDifficulty.setText(String.valueOf(mBean.getDifficulty()));
        challengeDetailsFail.setText(String.valueOf(mBean.getFail()));
        challengeDetailsHead.setText(mBean.getTitle());
        challengeDetailsHighest.setText(String.valueOf(mBean.getHighest()));
        challengeDetailsMaster.setText(String.valueOf(mBean.getMaster()));
        challengeDetailsTime.setText(String.valueOf(mBean.getTime()));
        challengeDetailsTitle.setText(String.valueOf(mBean.getTitle()));
        challengeDetailsTotal.setText(String.valueOf(mBean.getTotal()));
        challengeDetailsUpgrade.setText(String.valueOf(mBean.getUpgrade()));
    }

    @OnClick({R.id.common_back})
    public void onViewClicked(View itemView){
        finish();
    }
}
