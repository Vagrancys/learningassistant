package com.vargancys.learningassistant.module.game.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.db.game.GameConfigUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Vagrancy
 * @date 2020/4/16
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 关卡配置页面
 */
public class GameConfigActivity extends BaseActivity {
    @BindView(R.id.common_title_data)
    TextView commonTitleData;
    @BindView(R.id.config_number_first)
    TextView configNumberFirst;
    @BindView(R.id.config_number_second)
    TextView configNumberSecond;
    @BindView(R.id.config_number_third)
    TextView configNumberThird;
    @BindView(R.id.config_number_fourth)
    TextView configNumberFourth;
    @BindView(R.id.config_number_fifth)
    TextView configNumberFifth;
    @BindView(R.id.config_single_first)
    TextView configSingleFirst;
    @BindView(R.id.config_single_second)
    TextView configSingleSecond;
    @BindView(R.id.config_single_third)
    TextView configSingleThird;
    @BindView(R.id.config_single_fourth)
    TextView configSingleFourth;
    @BindView(R.id.config_single_fifth)
    TextView configSingleFifth;
    @BindView(R.id.config_type_first)
    TextView configTypeFirst;
    @BindView(R.id.config_type_second)
    TextView configTypeSecond;
    @BindView(R.id.config_type_third)
    TextView configTypeThird;
    @BindView(R.id.config_type_fourth)
    TextView configTypeFourth;
    @BindView(R.id.config_type_fifth)
    TextView configTypeFifth;
    @BindView(R.id.config_difficulty_first)
    TextView configDifficultyFirst;
    @BindView(R.id.config_difficulty_second)
    TextView configDifficultySecond;
    @BindView(R.id.config_difficulty_third)
    TextView configDifficultyThird;
    @BindView(R.id.config_difficulty_fourth)
    TextView configDifficultyFourth;
    @BindView(R.id.config_difficulty_fifth)
    TextView configDifficultyFifth;
    @BindView(R.id.config_contain_first)
    TextView configContainFirst;
    @BindView(R.id.config_contain_second)
    TextView configContainSecond;
    @BindView(R.id.config_repeat_first)
    TextView configRepeatFirst;
    @BindView(R.id.config_repeat_second)
    TextView configRepeatSecond;
    @BindView(R.id.config_save)
    TextView configSave;
    @BindView(R.id.config_number)
    TextView configNumber;
    @BindView(R.id.config_single)
    TextView configSingle;
    @BindView(R.id.config_type)
    TextView configType;
    @BindView(R.id.config_difficulty)
    TextView configDifficulty;
    @BindView(R.id.config_contain)
    TextView configContain;
    @BindView(R.id.config_repeat)
    TextView configRepeat;

    @Override
    public int getLayoutId() {
        return R.layout.activity_game_config;
    }

    @Override
    public void initView() {
        initConfig();
    }

    private void clearNumber() {
        configNumberFirst.setSelected(false);
        configNumberSecond.setSelected(false);
        configNumberThird.setSelected(false);
        configNumberFourth.setSelected(false);
        configNumberFifth.setSelected(false);
    }

    private void clearSingle() {
        configSingleFirst.setSelected(false);
        configSingleSecond.setSelected(false);
        configSingleThird.setSelected(false);
        configSingleFourth.setSelected(false);
        configSingleFifth.setSelected(false);
    }

    private void clearType() {
        configTypeFirst.setSelected(false);
        configTypeSecond.setSelected(false);
        configTypeThird.setSelected(false);
        configTypeFourth.setSelected(false);
        configTypeFifth.setSelected(false);
    }

    private void clearDifficulty() {
        configDifficultyFirst.setSelected(false);
        configDifficultySecond.setSelected(false);
        configDifficultyThird.setSelected(false);
        configDifficultyFourth.setSelected(false);
        configDifficultyFifth.setSelected(false);
    }

    private void initConfig() {
        switch (GameConfigUtils.CONFIG_NUMBER) {
            case 10:
                configNumberFirst.setSelected(true);
                break;
            case 20:
                configNumberSecond.setSelected(true);
                break;
            case 30:
                configNumberThird.setSelected(true);
                break;
            case 40:
                configNumberFourth.setSelected(true);
                break;
            case 50:
                configNumberFifth.setSelected(true);
                break;
        }
        switch (GameConfigUtils.CONFIG_SINGLE) {
            case 1:
                configSingleFirst.setSelected(true);
                break;
            case 2:
                configSingleSecond.setSelected(true);
                break;
            case 3:
                configSingleThird.setSelected(true);
                break;
            case 4:
                configSingleFourth.setSelected(true);
                break;
            case 5:
                configSingleFifth.setSelected(true);
                break;
        }

        switch (GameConfigUtils.CONFIG_TYPE) {
            case 1:
                configTypeFirst.setSelected(true);
                break;
            case 2:
                configTypeSecond.setSelected(true);
                break;
            case 3:
                configTypeThird.setSelected(true);
                break;
            case 4:
                configTypeFourth.setSelected(true);
                break;
            case 5:
                configTypeFifth.setSelected(true);
                break;
        }

        switch (GameConfigUtils.CONFIG_DIFFICULTY) {
            case 1:
                configDifficultyFirst.setSelected(true);
                break;
            case 2:
                configDifficultySecond.setSelected(true);
                break;
            case 3:
                configDifficultyThird.setSelected(true);
                break;
            case 4:
                configDifficultyFourth.setSelected(true);
                break;
            case 5:
                configDifficultyFifth.setSelected(true);
                break;
        }

        if (GameConfigUtils.CONFIG_CONTAIN) {
            configContainSecond.setSelected(true);
        } else {
            configContainFirst.setSelected(true);
        }

        if (GameConfigUtils.CONFIG_REPEAT) {
            configRepeatSecond.setSelected(true);
        } else {
            configRepeatFirst.setSelected(true);
        }
    }

    @Override
    public void initToolbar() {

        commonTitleData.setText(getText(R.string.config_title));
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, GameConfigActivity.class);
        activity.startActivity(intent);
    }

    // 确定闯关的题数 10 20 30 40 50
    // 确定是否包含错题 false true
    // 确定每个知识点出题数  1 2 3 4 5
    //确定是否重复已答的题目 false true
    // 确定题目类型 0 1 2 3 4
    // 确定题目难易 1 2 3 4 5


    @OnClick({R.id.config_number_first,R.id.config_number_second,R.id.config_number_third,R.id.config_number_fourth,
            R.id.config_number_fifth,R.id.config_single_first,R.id.config_single_second,R.id.config_single_third,
            R.id.config_single_fourth,R.id.config_single_fifth,R.id.config_type_first,R.id.config_type_second,R.id.config_type_third,
            R.id.config_type_fourth,R.id.config_type_fifth,R.id.config_difficulty_first,R.id.config_difficulty_second,
            R.id.config_difficulty_third,R.id.config_difficulty_fourth,R.id.config_difficulty_fifth,R.id.config_contain_first,
            R.id.config_contain_second,R.id.config_repeat_first,R.id.config_repeat_second,R.id.config_save,
            R.id.common_back})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.config_number_first:
                clearNumber();
                configNumberFirst.setSelected(true);
                GameConfigUtils.CONFIG_NUMBER = 10;
                break;
            case R.id.config_number_second:
                clearNumber();
                configNumberSecond.setSelected(true);
                GameConfigUtils.CONFIG_NUMBER = 20;
                break;
            case R.id.config_number_third:
                clearNumber();
                configNumberThird.setSelected(true);
                GameConfigUtils.CONFIG_NUMBER = 30;
                break;
            case R.id.config_number_fourth:
                clearNumber();
                configNumberFourth.setSelected(true);
                GameConfigUtils.CONFIG_NUMBER = 40;
                break;
            case R.id.config_number_fifth:
                clearNumber();
                configNumberFifth.setSelected(true);
                GameConfigUtils.CONFIG_NUMBER = 50;
                break;
            case R.id.config_single_first:
                clearSingle();
                configSingleFirst.setSelected(true);
                GameConfigUtils.CONFIG_SINGLE = 1;
                break;
            case R.id.config_single_second:
                clearSingle();
                configSingleSecond.setSelected(true);
                GameConfigUtils.CONFIG_SINGLE = 2;
                break;
            case R.id.config_single_third:
                clearSingle();
                configSingleThird.setSelected(true);
                GameConfigUtils.CONFIG_SINGLE = 3;
                break;
            case R.id.config_single_fourth:
                clearSingle();
                configSingleFirst.setSelected(true);
                GameConfigUtils.CONFIG_SINGLE = 4;
                break;
            case R.id.config_single_fifth:
                clearSingle();
                configSingleFifth.setSelected(true);
                GameConfigUtils.CONFIG_SINGLE = 5;
                break;
            case R.id.config_type_first:
                clearType();
                configTypeFirst.setSelected(true);
                GameConfigUtils.CONFIG_TYPE = 0;
                break;
            case R.id.config_type_second:
                clearType();
                configTypeSecond.setSelected(true);
                GameConfigUtils.CONFIG_TYPE = 1;
                break;
            case R.id.config_type_third:
                clearType();
                configTypeThird.setSelected(true);
                GameConfigUtils.CONFIG_TYPE = 2;
                break;
            case R.id.config_type_fourth:
                clearType();
                configTypeFourth.setSelected(true);
                GameConfigUtils.CONFIG_TYPE = 3;
                break;
            case R.id.config_type_fifth:
                clearType();
                configTypeFifth.setSelected(true);
                GameConfigUtils.CONFIG_TYPE = 4;
                break;
            case R.id.config_difficulty_first:
                clearDifficulty();
                configDifficultyFirst.setSelected(true);
                GameConfigUtils.CONFIG_DIFFICULTY = 1;
                break;
            case R.id.config_difficulty_second:
                clearDifficulty();
                configDifficultySecond.setSelected(true);
                GameConfigUtils.CONFIG_DIFFICULTY = 2;
                break;
            case R.id.config_difficulty_third:
                clearDifficulty();
                configDifficultyThird.setSelected(true);
                GameConfigUtils.CONFIG_DIFFICULTY = 3;
                break;
            case R.id.config_difficulty_fourth:
                clearDifficulty();
                configDifficultyFourth.setSelected(true);
                GameConfigUtils.CONFIG_DIFFICULTY = 4;
                break;
            case R.id.config_difficulty_fifth:
                clearDifficulty();
                configDifficultyFifth.setSelected(true);
                GameConfigUtils.CONFIG_DIFFICULTY = 5;
                break;
            case R.id.config_contain_first:
                configContainFirst.setSelected(true);
                GameConfigUtils.CONFIG_CONTAIN = false;
                break;
            case R.id.config_contain_second:
                configContainSecond.setSelected(true);
                GameConfigUtils.CONFIG_CONTAIN = true;
                break;
            case R.id.config_repeat_first:
                configRepeatFirst.setSelected(true);
                GameConfigUtils.CONFIG_REPEAT = false;
                break;
            case R.id.config_repeat_second:
                configRepeatSecond.setSelected(true);
                GameConfigUtils.CONFIG_REPEAT = true;
                break;
            case R.id.config_save:
                ToastUtils.ToastText(getContext(), R.string.game_config_success_text);
                GameStartActivity.launch(GameConfigActivity.this);
                finish();
                break;
            case R.id.common_back:
                finish();
                break;
        }
    }
}
