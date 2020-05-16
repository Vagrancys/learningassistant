package com.vargancys.learningassistant.module.ladder.fragment;

import android.os.Bundle;
import android.util.Log;
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
import com.vargancys.learningassistant.module.ladder.activity.LadderCommunicationActivity;
import com.vargancys.learningassistant.module.ladder.activity.LadderDifficultyActivity;
import com.vargancys.learningassistant.module.ladder.activity.LadderHelpActivity;
import com.vargancys.learningassistant.module.ladder.activity.LadderModeActivity;
import com.vargancys.learningassistant.module.ladder.activity.LadderRankActivity;
import com.vargancys.learningassistant.module.ladder.activity.LadderResultActivity;
import com.vargancys.learningassistant.module.ladder.view.LadderView;
import com.vargancys.learningassistant.presenter.ladder.BaseLadderPresenter;
import com.vargancys.learningassistant.utils.CacheUtils;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.TimeUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Vagrancy
 * @date 2020/5/3
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 天梯碎片模块
 */
public class LadderFragment extends BaseFragment implements LadderView {
    @BindView(R.id.interlude_layout)
    RelativeLayout interludeLayout;
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
    @BindView(R.id.ladder_layout)
    FrameLayout ladderLayout;

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
    @BindView(R.id.ladder_win_unavailable)
    TextView ladderWinUnavailable;
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
    @BindView(R.id.empty_layout)
    LinearLayout emptyLayout;
    @BindView(R.id.retry_button)
    TextView retryButton;

    private LadderDataBean mLadder;
    public static LadderFragment newInstance() {
        return new LadderFragment();
    }

    private BaseLadderPresenter mPresenter;
    private long ladderId;
    private int LadderLevel = 1;
    private int mProgressMax = 10;
    private int mProgress = 10;

    //单选的答案
    private int RadioAnswer = 0;

    //多选的四个答案
    private boolean MultipleFirstAnswer = false;
    private boolean MultipleSecondAnswer = false;
    private boolean MultipleThirdAnswer = false;
    private boolean MultipleFourthAnswer = false;

    //填空的答案
    private int fillAnswer = 0;

    //旧称号
    private String oldTitle = "";

    private String[] mTitle;
    private List<LadderTopicBean> mTopics = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.fragment_ladder;
    }

    @Override
    protected void initView() {
        ladderId = CacheUtils.getLong(getContext(), ConstantsUtils.LADDER_DATA_ID, 0);
        mPresenter = new BaseLadderPresenter(this);
        mTitle = getResources().getStringArray(R.array.ladder_title);
        initHideLayout();
        mPresenter.getLadderData(ladderId);
    }

    @Override
    public void getLadderData(LadderDataBean ladder) {
        mLadder = ladder;
        mPresenter.getLadderAllTopicItem(mLadder.getHighest());
    }

    private void initHideLayout(){
        interludeLayout.setVisibility(View.GONE);
        prepareLayout.setVisibility(View.GONE);
        ladderLayout.setVisibility(View.GONE);
        failLayout.setVisibility(View.GONE);
        winLayout.setVisibility(View.GONE);
        functionLayout.setVisibility(View.GONE);
        emptyLayout.setVisibility(View.GONE);
    }

    @Override
    public void loadingLayout() {
        emptyLayout.setVisibility(View.GONE);
        interludeLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void loadingFinish() {
        interludeLayout.setVisibility(View.GONE);
    }

    @Override
    public void getLadderDataError(long result) {
        emptyLayout.setVisibility(View.VISIBLE);
        if(result !=0){
            CacheUtils.putLong(getContext(),ConstantsUtils.LADDER_DATA_ID,result);
        }
    }

    @OnClick({R.id.ladder_prepare_start,R.id.ladder_judgment,R.id.ladder_win_start,
            R.id.ladder_function_list,R.id.function_layout,R.id.ladder_mode,
            R.id.retry_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ladder_prepare_start:
                mPresenter.showLadderLayout();
                break;
            case R.id.ladder_judgment:
                if(mPresenter.isAnswerEmpty()){
                    ToastUtils.ToastText(getContext(),"请回答问题!");
                    return;
                }
                mPresenter.TrailAnswer();
                break;
            case R.id.ladder_win_start:
                mPresenter.saveLadderData(ladderId);
                mPresenter.showPrepareLayout();
                break;
            case R.id.ladder_function_list:
                if(functionLayout.getVisibility() == View.GONE){
                    functionLayout.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.function_layout:
                if(functionLayout.getVisibility() == View.VISIBLE){
                    functionLayout.setVisibility(View.GONE);
                }
                break;
            case R.id.ladder_mode:
                //模式配置
                LadderModeActivity.launch(getActivity());
                break;
            case R.id.ladder_communication:
                //交流经验
                LadderCommunicationActivity.launch(getActivity());
                break;
            case R.id.ladder_help:
                //帮助中心
                LadderHelpActivity.launch(getActivity());
                break;
            case R.id.ladder_rank:
                //排行中心
                LadderRankActivity.launch(getActivity());
                break;
            case R.id.ladder_difficulty:
                //难度选择
                LadderDifficultyActivity.launch(getActivity());
                break;
            case R.id.ladder_result:
                //成绩展示
                LadderResultActivity.launch(getActivity());
                break;
            case R.id.retry_button:
                mPresenter.getLadderData(ladderId);
                break;
        }
    }

    //处理天梯的问题正确性
    @Override
    public void TrailAnswer() {
        LadderTopicBean mBean = mTopics.get(LadderLevel);
        switch (mBean.getType()){
            case 1:
                JudgmentRadio(mBean);
                break;
            case 2:
                JudgmentMultiple(mBean);
                break;
            case 3:
                JudgmentFill(mBean);
                break;
            case 4:
                JudgmentSubjective(mBean);
                break;
        }
    }

    //判断主观的正确性
    private void JudgmentSubjective(LadderTopicBean mBean) {
        if(ladderSubjectiveEdit.getText().length()>0){
            JudgmentCorrect();
        }else{
            JudgmentError();
        }
    }

    //判断填空的正确性
    private void JudgmentFill(LadderTopicBean mBean) {
        if(mBean.getFill_answer() == fillAnswer){
            JudgmentCorrect();
        }else{
            JudgmentError();
        }
    }

    //判断多选的正确性
    private void JudgmentMultiple(LadderTopicBean mBean) {
        boolean result;
        if(mBean.getMultiple_first_answer()==MultipleFirstAnswer){
            result = true;
        }else{
            result = false;
        }

        if(mBean.getMultiple_second_answer()==MultipleSecondAnswer){
            result = true;
        }else{
            result = false;
        }

        if(mBean.getMultiple_third_answer()==MultipleThirdAnswer){
            result = true;
        }else{
            result = false;
        }

        if(mBean.getMultiple_fourth_answer()==MultipleFourthAnswer){
            result = true;
        }else{
            result = false;
        }

        if(result){
            JudgmentCorrect();
        }else{
            JudgmentError();
        }
    }

    //判断是否答题
    @Override
    public boolean isAnswerEmpty() {
        switch (mTopics.get(LadderLevel).getType()){
            case 1:
                if(RadioAnswer == 0){
                    return true;
                }
                break;
            case 2:
                if(!MultipleFirstAnswer||!MultipleSecondAnswer||!MultipleThirdAnswer||!MultipleFourthAnswer){
                    return true;
                }
                break;
            case 3:
                if(fillAnswer == 0){
                    return true;
                }
                break;
            case 4:
                if(ladderSubjectiveEdit.getText().length() == 0){
                    return true;
                }
                break;
        }
        return false;
    }

    //判断单选的正确性
    private void JudgmentRadio(LadderTopicBean mBean) {
        if(mBean.getRadio_answer() == RadioAnswer){
            JudgmentCorrect();
        }else{
            JudgmentError();
        }
    }

    //回答正确
    private void JudgmentCorrect() {
        LadderLevel++;
        if(LadderLevel>mLadder.getTotal()){
            changeLadderData();
            mPresenter.showWinLayout();
            return;
        }
        hideProblemLayout();
        initProblem();
    }

    //回答错误
    private void JudgmentError(){
        changeLadderData();
        mPresenter.showFailLayout();
    }

    //显示登顶的页面
    @Override
    public void showWinLayout() {
        initWinData();
        ladderLayout.setVisibility(View.GONE);
        winLayout.setVisibility(View.VISIBLE);
    }

    //处理登顶成功的数据
    private void initWinData() {
        ladderWinTime.setText(mLadder.getTime());
        ladderWinTotal.setText(mLadder.getTotal());
        ladderWinDifficulty.setText(mLadder.getDifficulty());
        ladderWinUnavailable.setText(mLadder.getTotal_time());
        ladderWinChange.setText(oldTitle +"->"+mLadder.getTitle());
    }

    //改变天梯数据
    private void changeLadderData(){
        mLadder.setUpgrade_total(LadderLevel);
        int level = mLadder.getUpgrade_total()/500;
        mLadder.setTitle(mTitle[level]);
        mLadder.setTitle_level(level);
        mLadder.setFail(LadderLevel);
        int time = Integer.parseInt(TimeUtils.getTime())-Integer.parseInt(mLadder.getTime());
        mLadder.setTotal_time(String.valueOf(time));
        mLadder.setTime(TimeUtils.getTime());
    }

    //显示登顶失败布局
    @Override
    public void showFailLayout() {
        initFailData();
        ladderLayout.setVisibility(View.GONE);
        failLayout.setVisibility(View.VISIBLE);
    }

    //显示登顶失败的页面数据
    private void initFailData() {
        ladderFailLevel.setText(LadderLevel+"阶");
        ladderFailTime.setText(mLadder.getTime());
        ladderFailTotal.setText(mLadder.getTotal()+"阶");
        ladderFailDifficulty.setText(mLadder.getDifficulty());
        ladderFailChange.setText(oldTitle +"->"+mLadder.getTitle());
    }

    //显示天梯布局
    @Override
    public void showLadderLayout() {
        initLadderData();
        prepareLayout.setVisibility(View.GONE);
        showLadderInterludeLayout();
    }

    //显示天梯过场布局
    private void showLadderInterludeLayout() {
        ladderLayout.setVisibility(View.VISIBLE);
        ladderInterludeLayout.setVisibility(View.VISIBLE);
        hideProblemLayout();
    }

    //隐藏各个问题
    private void hideProblemLayout() {
        llRadioLayout.setVisibility(View.GONE);
        llMultipleLayout.setVisibility(View.GONE);
        llFillLayout.setVisibility(View.GONE);
        llSubjectiveLayout.setVisibility(View.GONE);
    }

    //处理天梯数据
    private void initLadderData() {
        LadderLevel = 1;
        ladderLevel.setText(LadderLevel+"阶");
        ladderTimeProgress.setMax(mProgressMax);
        ladderTimeProgress.setProgress(mProgress);
        ladderTime.setText(mProgress+"s");
        initProblem();
        ladderInterludeLayout.setVisibility(View.GONE);
    }

    //处理天梯的各问题显示
    private void initProblem() {
        LadderTopicBean mBean = mTopics.get(LadderLevel);
        switch (mBean.getType()){
            case 1:
                initRadioData(mBean);
                llRadioLayout.setVisibility(View.VISIBLE);
                break;
            case 2:
                initMultipleData(mBean);
                llMultipleLayout.setVisibility(View.VISIBLE);
                break;
            case 3:
                initFillData(mBean);
                llFillLayout.setVisibility(View.VISIBLE);
                break;
            case 4:
                initSubjectiveData(mBean);
                llSubjectiveLayout.setVisibility(View.VISIBLE);
                break;
        }
    }

    //初始化单选数据
    private void initRadioData(LadderTopicBean mBean) {
        ladderRadioTitle.setText(mBean.getRadio_title());
        ladderRadioFirstTitle.setText(mBean.getRadio_first_answer());
        ladderRadioSecondTitle.setText(mBean.getRadio_second_answer());
        ladderRadioThirdTitle.setText(mBean.getRadio_third_answer());
        ladderRadioFourthTitle.setText(mBean.getRadio_fourth_answer());
    }

    //初始化多选数据
    private void initMultipleData(LadderTopicBean mBean){
        ladderMultipleTitle.setText(mBean.getMultiple_title());
        ladderMultipleFirstTitle.setText(mBean.getMultiple_first_title());
        ladderMultipleSecondTitle.setText(mBean.getMultiple_second_title());
        ladderMultipleThirdTitle.setText(mBean.getMultiple_third_title());
        ladderMultipleFourthTitle.setText(mBean.getMultiple_fourth_title());
    }

    //初始化填空数据
    private void initFillData(LadderTopicBean mBean){
        ladderFillTitle.setText(mBean.getFill_title());
        ladderFillFirstTitle.setText(mBean.getFill_first_answer());
        ladderFillSecondTitle.setText(mBean.getFill_second_answer());
        ladderFillThirdTitle.setText(mBean.getFill_third_answer());
        ladderFillFourthTitle.setText(mBean.getFill_fourth_answer());
    }

    //初始化主观数据
    private void initSubjectiveData(LadderTopicBean mBean){
        ladderSubjectiveTitle.setText(mBean.getSubjective_title());
        ladderSubjectiveEdit.setText("");
    }

    @Override
    public void getLadderTopicFinish(List<LadderTopicBean> topic) {
        interludeLayout.setVisibility(View.GONE);
        mTopics = topic;
        mPresenter.showPrepareLayout();
    }

    //显示准备布局
    @Override
    public void showPrepareLayout() {
        initPrepareData();
        winLayout.setVisibility(View.GONE);
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
