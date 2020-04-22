package com.vargancys.learningassistant.module.game.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.db.game.GameStartContent;
import com.vargancys.learningassistant.module.game.view.StartGameView;
import com.vargancys.learningassistant.presenter.game.BaseGamePresenter;
import com.vargancys.learningassistant.utils.CacheUtils;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/16
 * version:1.0
 */
public class GameStartActivity extends BaseActivity implements StartGameView {
    @BindView(R.id.start_back)
    ImageView startBack;
    @BindView(R.id.start_progress_title)
    TextView startProgressTitle;
    @BindView(R.id.start_answer_sheet)
    ImageView startAnswerSheet;
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
    private BaseGamePresenter mPresenter;
    //关卡id
    private long gameId;
    private Handler mHandler;
    private List<GameStartContent> mContent = new ArrayList<>();

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

    }

    private void initData(int select){

    };

    private void isDataState(){

    }

    @Override
    public void showTidyAllDataError(int error, String message) {
        llStartEmpty.setVisibility(View.GONE);
        ToastUtils.ToastText(getContext(), "Error = " + error + ", Message =" + message);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
