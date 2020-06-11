package com.vargancys.learningassistant.module.game.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.db.game.AnswerSheetParcelable;
import com.vargancys.learningassistant.db.game.GameAnswerSheetBean;
import com.vargancys.learningassistant.db.game.GameStartContent;
import com.vargancys.learningassistant.module.game.view.StartGameView;
import com.vargancys.learningassistant.presenter.game.BaseGamePresenter;
import com.vargancys.learningassistant.utils.CacheUtils;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ResourceUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author Vagrancy
 * @date 2020/4/16
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 关卡挑战页面
 */
public class GameStartActivity extends BaseActivity implements StartGameView {
    @BindView(R.id.start_back)
    ImageView startBack;
    @BindView(R.id.start_progress_title)
    TextView startProgressTitle;
    @BindView(R.id.start_answer_sheet)
    ImageView startAnswerSheet;
    @BindView(R.id.start_progress)
    ProgressBar startProgressBar;
    @BindView(R.id.start_radio_title)
    TextView startRadioTitle;
    @BindView(R.id.start_radio_first_title)
    TextView startRadioFirstTitle;
    @BindView(R.id.start_radio_second_title)
    TextView startRadioSecondTitle;
    @BindView(R.id.start_radio_third_title)
    TextView startRadioThirdTitle;
    @BindView(R.id.start_radio_fourth_title)
    TextView startRadioFourthTitle;
    @BindView(R.id.start_radio_submit)
    TextView startRadioSubmit;
    @BindView(R.id.ll_radio_layout)
    LinearLayout llRadioLayout;
    @BindView(R.id.start_multiple_title)
    TextView startMultipleTitle;
    @BindView(R.id.start_multiple_first_title)
    TextView startMultipleFirstTitle;
    @BindView(R.id.start_multiple_second_title)
    TextView startMultipleSecondTitle;
    @BindView(R.id.start_multiple_third_title)
    TextView startMultipleThirdTitle;
    @BindView(R.id.start_multiple_fourth_title)
    TextView startMultipleFourthTitle;
    @BindView(R.id.start_multiple_submit)
    TextView startMultipleSubmit;
    @BindView(R.id.ll_multiple_layout)
    LinearLayout llMultipleLayout;
    @BindView(R.id.start_fill_title)
    TextView startFillTitle;
    @BindView(R.id.start_fill_first_title)
    TextView startFillFirstTitle;
    @BindView(R.id.start_fill_second_title)
    TextView startFillSecondTitle;
    @BindView(R.id.start_fill_third_title)
    TextView startFillThirdTitle;
    @BindView(R.id.start_fill_fourth_title)
    TextView startFillFourthTitle;
    @BindView(R.id.start_fill_submit)
    TextView startFillSubmit;
    @BindView(R.id.ll_fill_layout)
    LinearLayout llFillLayout;
    @BindView(R.id.start_subjective_title)
    TextView startSubjectiveTitle;
    @BindView(R.id.start_subjective_edit)
    EditText startSubjectiveEdit;
    @BindView(R.id.start_subjective_submit)
    TextView startSubjectiveSubmit;
    @BindView(R.id.ll_subjective_layout)
    LinearLayout llSubjectiveLayout;
    @BindView(R.id.ll_start_empty)
    LinearLayout llStartEmpty;
    @BindView(R.id.next_problem)
    TextView nextProblem;
    private BaseGamePresenter mPresenter;
    //关卡id
    private long gameId;
    private Handler mHandler;
    private int startProgress = 0;
    private int startRadioAnswer = 0;
    private boolean multipleFirstAnswer =false;
    private boolean multipleSecondAnswer =false;
    private boolean multipleThirdAnswer =false;
    private boolean multipleFourthAnswer =false;
    private int startFillAnswer = 0;
    private List<GameStartContent> mContents = new ArrayList<>();
    private AnswerSheetParcelable mAnswerSheetParcelable;
    private ArrayList<GameAnswerSheetBean> mBean;

    @Override
    public int getLayoutId() {
        return R.layout.activity_game_start;
    }

    @Override
    public void initView() {
        mHandler = new Handler();
        mPresenter = new BaseGamePresenter(this);
        gameId = CacheUtils.getLong(getContext(), ConstantsUtils.GAME_ID, 0);
        mPresenter.getGameStartAllData(mHandler, gameId);
        mAnswerSheetParcelable = new AnswerSheetParcelable();
        mBean = new ArrayList<>();
        initLayoutListener();
    }

    //注册监听器
    private void initLayoutListener() {
        startBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        startAnswerSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameAnswerSheetActivity.launch(GameStartActivity.this,mAnswerSheetParcelable);
            }
        });
        initRadioLayout();
        initMultipleLayout();
        initFillLayout();
        initSubjectiveLayout();
    }

    //注册主观的监听器
    private void initSubjectiveLayout() {
        startSubjectiveSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(startSubjectiveEdit.getText().length()>0){
                    handlerSubjectiveAnswer();
                }else{
                    ToastUtils.ToastText(getContext(),"你还没有选择答案呢!");
                }
            }
        });
    }

    //处理主观的问题正确
    private void handlerSubjectiveAnswer() {
        startFillSubmit.setVisibility(View.GONE);
        nextProblem.setVisibility(View.VISIBLE);
        JudgeAnswer(4);
    }

    //注册填空的监听器
    private void initFillLayout() {
        startFillFirstTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recoveryFill();
                if(startFillFirstTitle.getBackground() == ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_no_bg)){
                    startFillFirstTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_anwser_bg));
                    startFillAnswer = 1;
                }
            }
        });
        startFillSecondTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recoveryFill();
                if(startFillSecondTitle.getBackground() == ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_no_bg)){
                    startFillSecondTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_anwser_bg));
                    startFillAnswer = 2;
                }
            }
        });
        startFillThirdTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recoveryFill();
                if(startFillThirdTitle.getBackground() == ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_no_bg)){
                    startFillThirdTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_anwser_bg));
                    startFillAnswer = 1;
                }
            }
        });
        startFillFourthTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recoveryFill();
                if(startFillFourthTitle.getBackground() == ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_no_bg)){
                    startFillFourthTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_anwser_bg));
                    startFillAnswer = 1;
                }
            }
        });
        startFillSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(startFillAnswer == 0){
                    ToastUtils.ToastText(getContext(),"你还没有选择答案呢!");
                }else{
                    handlerFillAnswer();
                }
            }
        });
    }

    //处理填空的问题正确
    private void handlerFillAnswer() {
        GameStartContent mContent =mContents.get(startProgress);
        if(mContent.getFill_answer() == startFillAnswer){
            AnswerWin();
        }else{
            startFillSubmit.setVisibility(View.GONE);
            nextProblem.setVisibility(View.VISIBLE);
            JudgeAnswer(3);
        }
    }

    //重置填空问题
    private void recoveryFill() {
        startFillFirstTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_no_bg));
        startFillSecondTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_no_bg));
        startFillThirdTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_no_bg));
        startFillFourthTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_no_bg));
    }

    //注册多选的监听器
    private void initMultipleLayout() {
        startMultipleFirstTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(multipleFirstAnswer){
                    multipleFirstAnswer = false;
                    startMultipleFirstTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_no_bg));
                }else{
                    multipleFirstAnswer = true;
                    startMultipleFirstTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_anwser_bg));
                }
            }
        });
        startMultipleSecondTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(multipleSecondAnswer){
                    multipleSecondAnswer = false;
                    startMultipleSecondTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_no_bg));
                }else{
                    multipleSecondAnswer = true;
                    startMultipleSecondTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_anwser_bg));
                }
            }
        });
        startMultipleThirdTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(multipleThirdAnswer){
                    multipleThirdAnswer = false;
                    startMultipleThirdTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_no_bg));
                }else{
                    multipleThirdAnswer = true;
                    startMultipleThirdTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_anwser_bg));
                }
            }
        });
        startMultipleFourthTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(multipleFourthAnswer){
                    multipleFourthAnswer = false;
                    startMultipleFourthTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_no_bg));
                }else{
                    multipleFourthAnswer = true;
                    startMultipleFourthTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_anwser_bg));
                }
            }
        });
        startMultipleSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(multipleFirstAnswer|multipleSecondAnswer|multipleThirdAnswer|multipleFourthAnswer){
                    handlerMultipleAnswer();
                }else{
                    ToastUtils.ToastText(getContext(),"你还没有选择答案呢!");
                }
            }
        });
    }

    //处理多选的问题正确
    private void handlerMultipleAnswer(){
        GameStartContent mContent = mContents.get(startProgress);
        boolean isMultiple;
        if(multipleFirstAnswer == mContent.isMultiple_first_answer()){
            isMultiple = true;
        }else{
            isMultiple = false;
        }
        if(multipleSecondAnswer == mContent.isMultiple_second_answer()){
            isMultiple = true;
        }else{
            isMultiple = false;
        }

        if(multipleThirdAnswer == mContent.isMultiple_third_answer()){
            isMultiple = true;
        }else{
            isMultiple = false;
        }

        if(multipleFourthAnswer == mContent.isMultiple_fourth_answer()){
            isMultiple = true;
        }else{
            isMultiple = false;
        }

        if(isMultiple){
            AnswerWin();
        }else{
            startMultipleSubmit.setVisibility(View.GONE);
            nextProblem.setVisibility(View.VISIBLE);
            JudgeAnswer(2);
        }
    }

    //注册单选的监听器
    private void initRadioLayout() {
        startRadioFirstTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recoveryRadio();
                if(startRadioFirstTitle.getBackground() == ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_no_bg)){
                    startRadioFirstTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_anwser_bg));
                    startRadioAnswer = 1;
                }
            }
        });
        startRadioSecondTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recoveryRadio();
                if(startRadioSecondTitle.getBackground() == ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_no_bg)){
                    startRadioSecondTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_anwser_bg));
                    startRadioAnswer = 2;
                }
            }
        });
        startRadioThirdTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recoveryRadio();
                if(startRadioThirdTitle.getBackground() == ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_no_bg)){
                    startRadioThirdTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_anwser_bg));
                    startRadioAnswer = 3;
                }
            }
        });
        startRadioFourthTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recoveryRadio();
                if(startRadioFourthTitle.getBackground() == ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_no_bg)){
                    startRadioFourthTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_anwser_bg));
                    startRadioAnswer = 4;
                }
            }
        });

        startRadioSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(startRadioAnswer == 0){
                    ToastUtils.ToastText(getContext(),"你还没有选择答案呢!");
                }
                handlerRadioAnswer();
            }
        });
    }

    //处理单选的问题正确
    private void handlerRadioAnswer() {
        GameStartContent mContent = mContents.get(startProgress);
        if(mContent.getRadio_yes() == startRadioAnswer){
            AnswerWin();
        }else{
            startRadioSubmit.setVisibility(View.GONE);
            nextProblem.setVisibility(View.VISIBLE);
            JudgeAnswer(1);
        };
    }

    //错误答案处理方式
    private void JudgeAnswer(int select) {
        mBean.add(new GameAnswerSheetBean(mContents.get(startProgress).getStart_id(),false,mContents.get(startProgress).getContent_id()));
        switch (select){
            case 1:
                JudgeRadioLayout();
                break;
            case 2:
                JudgeMultipleLayout();
                break;
            case 3:
                JudgeFillLayout();
                break;
            case 4:
                JudgeSubjectiveLayout();
                break;
        }
    }

    //主观错误处理方式
    private void JudgeSubjectiveLayout() {
        GameStartContent mContent = mContents.get(startProgress);
        startSubjectiveEdit.setText(mContent.getSubjective_answer());
    }

    //填空错误处理方式
    private void JudgeFillLayout() {
        GameStartContent mContent = mContents.get(startProgress);
        switch (startFillAnswer){
            case 1:
                startFillFirstTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_fail_bg));
                break;
            case 2:
                startFillSecondTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_fail_bg));
                break;
            case 3:
                startFillThirdTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_fail_bg));
                break;
            case 4:
                startFillFourthTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_fail_bg));
                break;
        }
        switch (mContent.getFill_answer()){
            case 1:
                startFillFirstTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_win_bg));
                break;
            case 2:
                startFillSecondTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_win_bg));
                break;
            case 3:
                startFillThirdTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_win_bg));
                break;
            case 4:
                startFillFourthTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_win_bg));
                break;
        }
    }

    //多选错误处理方式
    private void JudgeMultipleLayout() {
        GameStartContent mContent = mContents.get(startProgress);
        if(multipleFirstAnswer == mContent.isMultiple_first_answer()){
            startRadioFirstTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_win_bg));
        }else{
            startRadioFirstTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_fail_bg));
        }
        if(multipleSecondAnswer == mContent.isMultiple_second_answer()){
            startRadioSecondTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_win_bg));
        }else{
            startRadioSecondTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_fail_bg));
        }

        if(multipleThirdAnswer == mContent.isMultiple_third_answer()){
            startRadioThirdTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_win_bg));
        }else{
            startRadioThirdTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_fail_bg));
        }

        if(multipleFourthAnswer == mContent.isMultiple_fourth_answer()){
            startRadioFourthTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_win_bg));
        }else{
            startRadioFourthTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_fail_bg));
        }
    }

    //单选错误处理方式
    private void JudgeRadioLayout() {
        GameStartContent mContent = mContents.get(startProgress);
        switch (startRadioAnswer){
            case 1:
                startRadioFirstTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_fail_bg));
                break;
            case 2:
                startRadioSecondTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_fail_bg));
                break;
            case 3:
                startRadioThirdTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_fail_bg));
                break;
            case 4:
                startRadioFourthTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_fail_bg));
                break;
        }
        switch (mContent.getRadio_yes()){
            case 1:
                startRadioFirstTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_win_bg));
                break;
            case 2:
                startRadioSecondTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_win_bg));
                break;
            case 3:
                startRadioThirdTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_win_bg));
                break;
            case 4:
                startRadioFourthTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_win_bg));
                break;
        }
    }

    //回答问题正确
    private void AnswerWin(){
        recoveryRadio();
        mBean.add(new GameAnswerSheetBean(mContents.get(startProgress).getStart_id(),true,mContents.get(startProgress).getContent_id()));
        nextProblem();
    }

    //下一道题
    private void nextProblem() {
        startProgress++;
        if(mContents.size() == startProgress){
            GameAnswerSheetActivity.launch(GameStartActivity.this,mAnswerSheetParcelable);
            finish();
        }
        startProgressTitle.setText(startProgress+"/"+mContents.size());
        startProgressBar.setProgress(startProgress);
        GameStartContent mContent = mContents.get(startProgress);
        initData(mContent.getType());
    }

    //重置单选问题
    private void recoveryRadio() {
        startRadioFirstTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_no_bg));
        startRadioSecondTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_no_bg));
        startRadioThirdTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_no_bg));
        startRadioFourthTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_no_bg));
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, GameStartActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void showRefreshLayout() {
        llStartEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void showTidyAllDataFinish(List<GameStartContent> contents) {
        llStartEmpty.setVisibility(View.GONE);
        mContents = contents;
        startProgressTitle.setText(startProgress+"/"+mContents.size());
        startProgressBar.setProgress(startProgress);
        startProgressBar.setMax(mContents.size());
        mAnswerSheetParcelable.setGame_id(contents.get(0).getGame_id());
        initData(mContents.get(startProgress).getType());
    }

    //初始化数据
    private void initData(int select){
        nextProblem.setVisibility(View.GONE);

        switch (select){
            case 1:
                showLayout(select);
                initRadioData();
                break;
            case 2:
                showLayout(select);
                initMultipleData();
                break;
            case 3:
                showLayout(select);
                initFillData();
                break;
            case 4:
                showLayout(select);
                initSubjectiveData();
                break;
        }
    }

    //初始化主观问题
    private void initSubjectiveData() {
        GameStartContent mContent = mContents.get(startProgress);
        startSubjectiveTitle.setText(mContent.getSubjective_title());
        startSubjectiveEdit.setText("");
        startSubjectiveSubmit.setVisibility(View.VISIBLE);
    }

    //初始化填空问题
    private void initFillData() {
        GameStartContent mContent = mContents.get(startProgress);
        startFillTitle.setText(mContent.getFill_title());
        startFillFirstTitle.setText(mContent.getFill_first_title());
        startFillSecondTitle.setText(mContent.getFill_second_title());
        startFillThirdTitle.setText(mContent.getFill_third_title());
        startFillFourthTitle.setText(mContent.getFill_fourth_title());
        startFillSubmit.setVisibility(View.VISIBLE);
        recoveryFill();
    }

    //初始化多选问题
    private void initMultipleData() {
        GameStartContent mContent = mContents.get(startProgress);
        startMultipleTitle.setText(mContent.getMultiple_title());
        startMultipleFirstTitle.setText(mContent.getMultiple_first_title());
        startMultipleSecondTitle.setText(mContent.getMultiple_second_title());
        startMultipleThirdTitle.setText(mContent.getMultiple_third_title());
        startMultipleFourthTitle.setText(mContent.getMultiple_fourth_title());
        startMultipleSubmit.setVisibility(View.VISIBLE);
        recoveryMultiple();
    }

    //重置多选
    private void recoveryMultiple(){
        startMultipleFirstTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_no_bg));
        startMultipleSecondTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_no_bg));
        startMultipleThirdTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_no_bg));
        startMultipleFourthTitle.setBackground(ResourceUtils.getDrawable(getContext(),R.drawable.topic_select_no_bg));
    }

    //显示需要显示的视图
    private void showLayout(int select) {
        hideLayout();
        switch (select){
            case 1:
                llRadioLayout.setVisibility(View.VISIBLE);
                break;
            case 2:
                llMultipleLayout.setVisibility(View.VISIBLE);
                break;
            case 3:
                llFillLayout.setVisibility(View.VISIBLE);
                break;
            case 4:
                llSubjectiveLayout.setVisibility(View.VISIBLE);
                break;
        }
    }

    //隐藏各问题
    private void hideLayout(){
        llRadioLayout.setVisibility(View.GONE);
        llMultipleLayout.setVisibility(View.GONE);
        llFillLayout.setVisibility(View.GONE);
        llSubjectiveLayout.setVisibility(View.GONE);
    }

    //初始化单选
    private void initRadioData(){
        GameStartContent mContent = mContents.get(startProgress);
        startRadioAnswer = 0;
        startRadioTitle.setText(mContent.getRadio_title());
        startRadioFirstTitle.setText(mContent.getRadio_first_title());
        startRadioSecondTitle.setText(mContent.getRadio_second_title());
        startRadioThirdTitle.setText(mContent.getRadio_third_title());
        startRadioFourthTitle.setText(mContent.getRadio_third_title());
        startRadioSubmit.setVisibility(View.VISIBLE);
        recoveryRadio();
    }

    @Override
    public void showTidyAllDataError(int error, String message) {
        llStartEmpty.setVisibility(View.GONE);
        ToastUtils.ToastText(getContext(), "Error = " + error + ", Message =" + message);
        finish();
    }
}
