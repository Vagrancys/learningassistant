package com.vargancys.learningassistant.module.ladder.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.bean.ladder.LadderDataBean;
import com.vargancys.learningassistant.bean.ladder.LadderResultBean;
import com.vargancys.learningassistant.module.ladder.view.LadderResultView;
import com.vargancys.learningassistant.presenter.ladder.BaseLadderPresenter;
import com.vargancys.learningassistant.utils.CacheUtils;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Vagrancy
 * @date 2020/5/7
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 天梯成绩展示
 */
public class LadderResultActivity extends BaseActivity implements LadderResultView {
    @BindView(R.id.common_title_data)
    TextView commonTitleData;
    @BindView(R.id.result_author)
    TextView resultAuthor;
    @BindView(R.id.result_level_head)
    TextView resultLevelHead;
    @BindView(R.id.result_title)
    TextView resultTitle;
    @BindView(R.id.result_level)
    TextView resultLevel;
    @BindView(R.id.result_upgrade)
    TextView resultUpgrade;
    @BindView(R.id.result_upgrade_total)
    TextView resultUpgradeTotal;
    @BindView(R.id.result_difficulty)
    TextView resultDifficulty;
    @BindView(R.id.result_highest)
    TextView resultHighest;
    @BindView(R.id.result_fail)
    TextView resultFail;
    @BindView(R.id.result_time)
    TextView resultTime;
    @BindView(R.id.result_total_time)
    TextView resultTotalTime;
    @BindView(R.id.result_total)
    TextView resultTotal;
    @BindView(R.id.result_master)
    TextView resultMaster;
    @BindView(R.id.result_chance)
    TextView resultChance;
    @BindView(R.id.result_start_time)
    TextView resultStartTime;
    @BindView(R.id.result_day)
    TextView resultDay;
    @BindView(R.id.result_dare_total)
    TextView resultDareTotal;
    @BindView(R.id.result_fail_total)
    TextView resultFailTotal;
    @BindView(R.id.result_win_total)
    TextView resultWinTotal;
    @BindView(R.id.result_long_time)
    TextView resultLongTime;
    @BindView(R.id.result_highest_count)
    TextView resultHighestCount;
    @BindView(R.id.result_score)
    TextView resultScore;
    @BindView(R.id.result_short_time)
    TextView resultShortTime;

    @Override
    public int getLayoutId() {
        return R.layout.activity_ladder_result;
    }

    private long ladderId;
    private BaseLadderPresenter mPresenter;

    @Override
    public void initView() {
        ladderId = CacheUtils.getLong(getContext(), ConstantsUtils.LADDER_DATA_ID, 0);
        mPresenter = new BaseLadderPresenter(this);

        mPresenter.getLadderResultData(ladderId);
        mPresenter.getLadderResultUse(ladderId);
    }

    @Override
    public void initToolbar() {

        commonTitleData.setText(getText(R.string.ladder_result_toolbar));
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, LadderResultActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void showResultDataFinish(LadderDataBean mBean) {
        setData(mBean);
    }

    //设置成就数据
    private void setData(LadderDataBean bean) {
        resultAuthor.setText(bean.getName());
        resultLevelHead.setText(bean.getTitle());
        resultTitle.setText(bean.getTitle());
        resultLevel.setText(String.valueOf(bean.getTitle_level()));
        resultUpgrade.setText(String.valueOf(bean.getUpgrade()));
        resultUpgradeTotal.setText(String.valueOf(bean.getUpgrade_total()));
        resultDifficulty.setText(bean.getDifficulty());
        resultHighest.setText(String.valueOf(bean.getHighest()));
        resultFail.setText(String.valueOf(bean.getFail()));
        resultTime.setText(String.valueOf(bean.getTime()));
        resultTotalTime.setText(String.valueOf(bean.getTotal_time()));
        resultTotal.setText(String.valueOf(bean.getTotal()));
        resultMaster.setText(String.valueOf(bean.getMaster()));
        resultChance.setText(bean.getChance());
    }

    @Override
    public void showResultDataError(int error, String message) {
        ToastUtils.ToastText(getContext(), "Error = " + error + ", Message =" + message);
        clearData();
    }

    //清空成就数据
    private void clearData() {
        resultAuthor.setText("--");
        resultLevelHead.setText("--");
        resultTitle.setText("--");
        resultLevel.setText("--");
        resultUpgrade.setText("--");
        resultUpgradeTotal.setText("--");
        resultDifficulty.setText("--");
        resultHighest.setText("--");
        resultFail.setText("--");
        resultTime.setText("--");
        resultTotalTime.setText("--");
        resultTotal.setText("--");
        resultMaster.setText("--");
        resultChance.setText("--");
    }

    @Override
    public void showResultUseFinish(LadderResultBean mBean) {
        setUse(mBean);
    }

    //设置生涯数据
    private void setUse(LadderResultBean bean) {
        resultStartTime.setText(String.valueOf(bean.getStart_time()));
        resultDay.setText(String.valueOf(bean.getDay()));
        resultDareTotal.setText(String.valueOf(bean.getTotal()));
        resultFailTotal.setText(String.valueOf(bean.getFail_count()));
        resultWinTotal.setText(String.valueOf(bean.getWin_count()));
        resultLongTime.setText(String.valueOf(bean.getLong_time()));
        resultHighestCount.setText(String.valueOf(bean.getHighest_count()));
        resultScore.setText(String.valueOf(bean.getScore()));
        resultShortTime.setText(String.valueOf(bean.getShort_time()));
    }

    @Override
    public void showResultUseError(int error, String message) {
        ToastUtils.ToastText(getContext(),"Error ="+error+", Message ="+message);
        clearUse();
    }

    //清空生涯数据
    private void clearUse() {
        resultStartTime.setText("--");
        resultDay.setText("--");
        resultDareTotal.setText("--");
        resultFailTotal.setText("--");
        resultWinTotal.setText("--");
        resultLongTime.setText("--");
        resultHighestCount.setText("--");
        resultScore.setText("--");
        resultShortTime.setText("--");
    }

    @OnClick({R.id.common_back})
    public void onViewClicked(View itemView){
        switch (itemView.getId()){
            case R.id.common_back:
                finish();
                break;
        }
    }
}
