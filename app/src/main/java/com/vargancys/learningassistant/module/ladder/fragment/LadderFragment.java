package com.vargancys.learningassistant.module.ladder.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseFragment;
import com.vargancys.learningassistant.db.ladder.LadderDataBean;
import com.vargancys.learningassistant.db.ladder.LadderTopicBean;
import com.vargancys.learningassistant.module.ladder.view.LadderView;
import com.vargancys.learningassistant.presenter.ladder.BaseLadderPresenter;
import com.vargancys.learningassistant.utils.CacheUtils;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/05/03
 * version:1.0
 */
public class LadderFragment extends BaseFragment implements LadderView {
    @BindView(R.id.interlude_layout)
    RelativeLayout interludeLayout;
    @BindView(R.id.ladder_prepare_head_text)
    TextView ladderPrepareHeadText;
    @BindView(R.id.ladder_prepare_title)
    TextView ladderPrepareTitle;
    @BindView(R.id.ladder_prepare_upgrade)
    TextView ladderPrepareUpgrade;
    @BindView(R.id.ladder_prepare_difficulty)
    TextView ladderPrepareDifficulty;
    @BindView(R.id.ladder_prepare_highest)
    TextView ladderPrepareHighest;
    @BindView(R.id.ladder_prepare_fail)
    TextView ladderPrepareFail;
    @BindView(R.id.ladder_prepare_time)
    TextView ladderPrepareTime;
    @BindView(R.id.ladder_prepare_total)
    TextView ladderPrepareTotal;
    @BindView(R.id.ladder_prepare_master)
    TextView ladderPrepareMaster;
    @BindView(R.id.ladder_prepare_chance)
    TextView ladderPrepareChance;
    @BindView(R.id.ladder_prepare_start)
    TextView ladderPrepareStart;
    @BindView(R.id.prepare_layout)
    LinearLayout prepareLayout;

    @BindView(R.id.ladder_interlude_layout)
    LinearLayout ladderInterludeLayout;
    @BindView(R.id.ladder_level)
    TextView ladderLevel;
    @BindView(R.id.ladder_time_progress)
    ProgressBar ladderTimeProgress;
    @BindView(R.id.ladder_time)
    TextView ladderTime;
    @BindView(R.id.ladder_radio_title)
    TextView ladderRadioTitle;
    @BindView(R.id.ladder_radio_first_title)
    TextView ladderRadioFirstTitle;
    @BindView(R.id.ladder_radio_second_title)
    TextView ladderRadioSecondTitle;
    @BindView(R.id.ladder_radio_third_title)
    TextView ladderRadioThirdTitle;
    @BindView(R.id.ladder_radio_fourth_title)
    TextView ladderRadioFourthTitle;
    @BindView(R.id.ll_radio_layout)
    LinearLayout llRadioLayout;
    @BindView(R.id.ladder_multiple_title)
    TextView ladderMultipleTitle;
    @BindView(R.id.ladder_multiple_first_title)
    TextView ladderMultipleFirstTitle;
    @BindView(R.id.ladder_multiple_second_title)
    TextView ladderMultipleSecondTitle;
    @BindView(R.id.ladder_multiple_third_title)
    TextView ladderMultipleThirdTitle;
    @BindView(R.id.ladder_multiple_fourth_title)
    TextView ladderMultipleFourthTitle;
    @BindView(R.id.ll_multiple_layout)
    LinearLayout llMultipleLayout;
    @BindView(R.id.ladder_fill_title)
    TextView ladderFillTitle;
    @BindView(R.id.ladder_fill_first_title)
    TextView ladderFillFirstTitle;
    @BindView(R.id.ladder_fill_second_title)
    TextView ladderFillSecondTitle;
    @BindView(R.id.ladder_fill_third_title)
    TextView ladderFillThirdTitle;
    @BindView(R.id.ladder_fill_fourth_title)
    TextView ladderFillFourthTitle;
    @BindView(R.id.ll_fill_layout)
    LinearLayout llFillLayout;
    @BindView(R.id.ladder_subjective_title)
    TextView ladderSubjectiveTitle;
    @BindView(R.id.ladder_subjective_edit)
    EditText ladderSubjectiveEdit;
    @BindView(R.id.ll_subjective_layout)
    LinearLayout llSubjectiveLayout;
    @BindView(R.id.ladder_judgment)
    TextView ladderJudgment;
    @BindView(R.id.ladder_layout)
    FrameLayout ladderLayout;

    @BindView(R.id.fail_title_head)
    TextView failTitleHead;
    @BindView(R.id.ladder_fail_level)
    TextView ladderFailLevel;
    @BindView(R.id.ladder_fail_time)
    TextView ladderFailTime;
    @BindView(R.id.ladder_fail_total)
    TextView ladderFailTotal;
    @BindView(R.id.ladder_fail_difficulty)
    TextView ladderFailDifficulty;
    @BindView(R.id.ladder_fail_change)
    TextView ladderFailChange;
    @BindView(R.id.ladder_fail_start)
    TextView ladderFailStart;
    @BindView(R.id.fail_layout)
    LinearLayout failLayout;
    @BindView(R.id.win_title_head)
    TextView winTitleHead;
    @BindView(R.id.ladder_win_time)
    TextView ladderWinTime;
    @BindView(R.id.ladder_win_total)
    TextView ladderWinTotal;
    @BindView(R.id.ladder_win_difficulty)
    TextView ladderWinDifficulty;
    @BindView(R.id.ladder_win_level)
    TextView ladderWinLevel;
    @BindView(R.id.ladder_win_change)
    TextView ladderWinChange;
    @BindView(R.id.ladder_win_start)
    TextView ladderWinStart;
    @BindView(R.id.ladder_mode)
    TextView ladderMode;
    @BindView(R.id.ladder_communication)
    TextView ladderCommunication;
    @BindView(R.id.ladder_help)
    TextView ladderHelp;
    @BindView(R.id.ladder_difficulty)
    TextView ladderDifficulty;
    @BindView(R.id.ladder_rank)
    TextView ladderRank;
    @BindView(R.id.ladder_result)
    TextView ladderResult;
    @BindView(R.id.function_layout)
    RelativeLayout functionLayout;
    @BindView(R.id.win_layout)
    LinearLayout winLayout;

    private LadderDataBean mLadder;
    public static LadderFragment newInstance() {
        return new LadderFragment();
    }

    private BaseLadderPresenter mPresenter;
    private long ladderId;
    private List<LadderTopicBean> mTopics = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.fragment_ladder;
    }

    @Override
    protected void initView() {
        ladderId = CacheUtils.getLong(getContext(), ConstantsUtils.LADDER_DATA_ID, 0);
        mPresenter = new BaseLadderPresenter(this);
        initHideLayout();
        mPresenter.getLadderData(ladderId);
        mPresenter.getLadderAllTopicItem();
    }

    @Override
    public void getLadderData(LadderDataBean ladder) {
        mLadder = ladder;
    }

    private void initHideLayout(){
        interludeLayout.setVisibility(View.GONE);
        prepareLayout.setVisibility(View.GONE);
        ladderLayout.setVisibility(View.GONE);
        failLayout.setVisibility(View.GONE);
        winLayout.setVisibility(View.GONE);
        functionLayout.setVisibility(View.GONE);
    }

    @Override
    public void loadingLayout() {
        interludeLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void loadingFinish() {
        interludeLayout.setVisibility(View.GONE);
    }

    @OnClick({R.id.ladder_prepare_start})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ladder_prepare_start:
                mPresenter.showLadderLayout();
                break;
        }
    }

    @Override
    public void showLadderLayout() {
        prepareLayout.setVisibility(View.GONE);
        initLadderData();
        ladderLayout.setVisibility(View.VISIBLE);
    }

    //TODO 处理到天梯关卡
    private void initLadderData() {

    }

    @Override
    public void getLadderTopicFinish(List<LadderTopicBean> topic) {
        interludeLayout.setVisibility(View.GONE);
        mTopics = topic;
        mPresenter.showPrepareLayout();
    }

    @Override
    public void showPrepareLayout() {
        initPrepareData();
        prepareLayout.setVisibility(View.VISIBLE);
    }

    private void initPrepareData() {
        ladderPrepareTitle.setText(mLadder.getTitle());
        ladderPrepareUpgrade.setText(mLadder.getUpgrade()+"阶");
        ladderPrepareDifficulty.setText(mLadder.getDifficulty());
        ladderPrepareHighest.setText(mLadder.getHighest()+"阶");
        ladderPrepareFail.setText(mLadder.getFail()+"阶");
        ladderPrepareTime.setText(mLadder.getTime());
        ladderPrepareTotal.setText(mLadder.getTotal()+"阶");
        ladderPrepareMaster.setText(mLadder.getMaster()+"题");
        ladderPrepareChance.setText(mLadder.getChance());
    }

    @Override
    public void getLadderTopicError(int error, String message) {
        interludeLayout.setVisibility(View.GONE);
        ToastUtils.ToastText(getContext(),"Error = "+error+", Message ="+message);
    }
}
