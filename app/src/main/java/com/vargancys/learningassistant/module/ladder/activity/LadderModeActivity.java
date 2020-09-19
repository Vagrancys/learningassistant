package com.vargancys.learningassistant.module.ladder.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.bean.ladder.LadderModeUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Vagrancy
 * @date 2020/5/7
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 天梯模式配置中心
 */
public class LadderModeActivity extends BaseActivity {
    @BindView(R.id.common_title_data)
    TextView commonTitleData;
    @BindView(R.id.mode_number_first)
    TextView modeNumberFirst;
    @BindView(R.id.mode_number_second)
    TextView modeNumberSecond;
    @BindView(R.id.mode_number_third)
    TextView modeNumberThird;
    @BindView(R.id.mode_number_fourth)
    TextView modeNumberFourth;
    @BindView(R.id.mode_number_fifth)
    TextView modeNumberFifth;
    @BindView(R.id.mode_time_first)
    TextView modeTimeFirst;
    @BindView(R.id.mode_time_second)
    TextView modeTimeSecond;
    @BindView(R.id.mode_time_third)
    TextView modeTimeThird;
    @BindView(R.id.mode_time_fourth)
    TextView modeTimeFourth;
    @BindView(R.id.mode_time_fifth)
    TextView modeTimeFifth;
    @BindView(R.id.mode_type_first)
    TextView modeTypeFirst;
    @BindView(R.id.mode_type_second)
    TextView modeTypeSecond;
    @BindView(R.id.mode_type_third)
    TextView modeTypeThird;
    @BindView(R.id.mode_type_fourth)
    TextView modeTypeFourth;
    @BindView(R.id.mode_type_fifth)
    TextView modeTypeFifth;
    @BindView(R.id.mode_class_first)
    TextView modeClassFirst;
    @BindView(R.id.mode_class_second)
    TextView modeClassSecond;
    @BindView(R.id.mode_class_third)
    TextView modeClassThird;
    @BindView(R.id.mode_class_fourth)
    TextView modeClassFourth;
    @BindView(R.id.mode_class_fifth)
    TextView modeClassFifth;
    @BindView(R.id.mode_save)
    TextView modeSave;
    @Override
    public int getLayoutId() {
        return R.layout.activity_ladder_mode;
    }

    @Override
    public void initView() {
        initConfig();
    }

    public static void launch(Activity activity){
        Intent intent = new Intent(activity,LadderModeActivity.class);
        activity.startActivity(intent);
    }

    private void clearNumber() {
        modeNumberFirst.setSelected(false);
        modeNumberSecond.setSelected(false);
        modeNumberThird.setSelected(false);
        modeNumberFourth.setSelected(false);
        modeNumberFifth.setSelected(false);
    }

    private void clearTime() {
        modeTimeFirst.setSelected(false);
        modeTimeSecond.setSelected(false);
        modeTimeThird.setSelected(false);
        modeTimeFourth.setSelected(false);
        modeTimeFifth.setSelected(false);
    }

    private void clearType() {
        modeTypeFirst.setSelected(false);
        modeTypeSecond.setSelected(false);
        modeTypeThird.setSelected(false);
        modeTypeFourth.setSelected(false);
        modeTypeFifth.setSelected(false);
    }

    private void clearClass() {
        modeClassFirst.setSelected(false);
        modeClassSecond.setSelected(false);
        modeClassThird.setSelected(false);
        modeClassFourth.setSelected(false);
        modeClassFifth.setSelected(false);
    }

    private void initConfig() {
        switch (LadderModeUtils.CONFIG_NUMBER) {
            case 10:
                modeNumberFirst.setSelected(true);
                break;
            case 20:
                modeNumberSecond.setSelected(true);
                break;
            case 30:
                modeNumberThird.setSelected(true);
                break;
            case 40:
                modeNumberFourth.setSelected(true);
                break;
            case 50:
                modeNumberFifth.setSelected(true);
                break;
        }
        switch (LadderModeUtils.CONFIG_TIME) {
            case 10:
                modeTimeFirst.setSelected(true);
                break;
            case 20:
                modeTimeSecond.setSelected(true);
                break;
            case 30:
                modeTimeThird.setSelected(true);
                break;
            case 40:
                modeTimeFourth.setSelected(true);
                break;
            case 50:
                modeTimeFifth.setSelected(true);
                break;
        }
        switch (LadderModeUtils.CONFIG_TYPE) {
            case 1:
                modeTypeFirst.setSelected(true);
                break;
            case 2:
                modeTypeSecond.setSelected(true);
                break;
            case 3:
                modeTypeThird.setSelected(true);
                break;
            case 4:
                modeTypeFourth.setSelected(true);
                break;
            case 5:
                modeTypeFifth.setSelected(true);
                break;
        }

        switch (LadderModeUtils.CONFIG_CLASS) {
            case 1:
                modeClassFirst.setSelected(true);
                break;
            case 2:
                modeClassSecond.setSelected(true);
                break;
            case 3:
                modeClassThird.setSelected(true);
                break;
            case 4:
                modeClassFourth.setSelected(true);
                break;
            case 5:
                modeClassFifth.setSelected(true);
                break;
        }
    }

    @Override
    public void initToolbar() {

        commonTitleData.setText(getText(R.string.ladder_mode_toolbar));
    }

    // 确定闯关的题数 10 20 30 40 50
    // 确定是否包含错题 false true
    // 确定每个知识点出题数  1 2 3 4 5
    //确定是否重复已答的题目 false true
    // 确定题目类型 0 1 2 3 4
    // 确定题目难易 1 2 3 4 5


    @OnClick({R.id.mode_number_first,R.id.mode_number_second,R.id.mode_number_third,R.id.mode_number_fourth,
            R.id.mode_number_fifth,R.id.mode_time_first,R.id.mode_time_second,R.id.mode_time_third,
            R.id.mode_time_fourth,R.id.mode_time_fifth,R.id.mode_type_first,R.id.mode_type_second,R.id.mode_type_third,
            R.id.mode_type_fourth,R.id.mode_type_fifth,R.id.mode_class_first,R.id.mode_class_second,
            R.id.mode_class_third,R.id.mode_class_fourth,R.id.mode_class_fifth,R.id.mode_save,R.id.common_back})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.mode_number_first:
                clearNumber();
                modeNumberFirst.setSelected(true);
                LadderModeUtils.CONFIG_NUMBER = 10;
                break;
            case R.id.mode_number_second:
                clearNumber();
                modeNumberSecond.setSelected(true);
                LadderModeUtils.CONFIG_NUMBER = 20;
                break;
            case R.id.mode_number_third:
                clearNumber();
                modeNumberThird.setSelected(true);
                LadderModeUtils.CONFIG_NUMBER = 30;
                break;
            case R.id.mode_number_fourth:
                clearNumber();
                modeNumberFourth.setSelected(true);
                LadderModeUtils.CONFIG_NUMBER = 40;
                break;
            case R.id.mode_number_fifth:
                clearNumber();
                modeNumberFifth.setSelected(true);
                LadderModeUtils.CONFIG_NUMBER = 50;
                break;
            case R.id.mode_time_first:
                clearTime();
                modeTimeFirst.setSelected(true);
                LadderModeUtils.CONFIG_TIME = 10;
                break;
            case R.id.mode_time_second:
                clearTime();
                modeTimeSecond.setSelected(true);
                LadderModeUtils.CONFIG_TIME = 20;
                break;
            case R.id.mode_time_third:
                clearTime();
                modeTimeThird.setSelected(true);
                LadderModeUtils.CONFIG_TIME = 30;
                break;
            case R.id.mode_time_fourth:
                clearTime();
                modeTimeFirst.setSelected(true);
                LadderModeUtils.CONFIG_TIME = 40;
                break;
            case R.id.mode_time_fifth:
                clearTime();
                modeTimeFifth.setSelected(true);
                LadderModeUtils.CONFIG_TIME = 50;
                break;
            case R.id.mode_type_first:
                clearType();
                modeTypeFirst.setSelected(true);
                LadderModeUtils.CONFIG_TYPE = 0;
                break;
            case R.id.mode_type_second:
                clearType();
                modeTypeSecond.setSelected(true);
                LadderModeUtils.CONFIG_TYPE = 1;
                break;
            case R.id.mode_type_third:
                clearType();
                modeTypeThird.setSelected(true);
                LadderModeUtils.CONFIG_TYPE = 2;
                break;
            case R.id.mode_type_fourth:
                clearType();
                modeTypeFourth.setSelected(true);
                LadderModeUtils.CONFIG_TYPE = 3;
                break;
            case R.id.mode_type_fifth:
                clearType();
                modeTypeFifth.setSelected(true);
                LadderModeUtils.CONFIG_TYPE = 4;
                break;
            case R.id.mode_class_first:
                clearClass();
                modeClassFirst.setSelected(true);
                LadderModeUtils.CONFIG_CLASS = 1;
                break;
            case R.id.mode_class_second:
                clearClass();
                modeClassSecond.setSelected(true);
                LadderModeUtils.CONFIG_CLASS= 2;
                break;
            case R.id.mode_class_third:
                clearClass();
                modeClassThird.setSelected(true);
                LadderModeUtils.CONFIG_CLASS = 3;
                break;
            case R.id.mode_class_fourth:
                clearClass();
                modeClassFourth.setSelected(true);
                LadderModeUtils.CONFIG_CLASS = 4;
                break;
            case R.id.mode_class_fifth:
                clearClass();
                modeClassFifth.setSelected(true);
                LadderModeUtils.CONFIG_CLASS = 5;
                break;
            case R.id.mode_save:
                ToastUtils.ToastText(getContext(), "保存成功了哦!");
                finish();
                break;
            case R.id.common_back:
                finish();
                break;
        }
    }
}
