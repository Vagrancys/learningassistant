package com.vargancys.learningassistant.module.ladder.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.module.ladder.view.LadderRankSettingView;
import com.vargancys.learningassistant.module.ladder.view.LadderView;
import com.vargancys.learningassistant.presenter.ladder.BaseLadderPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Vagrancy
 * @date 2020/5/14
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 天梯排行配置中心
 */
public class LadderRankSettingActivity extends BaseActivity implements LadderRankSettingView {
    @BindView(R.id.common_back)
    ImageView commonBack;
    @BindView(R.id.common_title_data)
    TextView commonTitleData;
    @BindView(R.id.rank_type_first)
    TextView rankTypeFirst;
    @BindView(R.id.rank_type_second)
    TextView rankTypeSecond;
    @BindView(R.id.rank_type_third)
    TextView rankTypeThird;
    @BindView(R.id.rank_type_fourth)
    TextView rankTypeFourth;
    @BindView(R.id.rank_type_fifth)
    TextView rankTypeFifth;
    @BindView(R.id.rank_type_sixth)
    TextView rankTypeSixth;
    @BindView(R.id.rank_type_seventh)
    TextView rankTypeSeventh;
    @BindView(R.id.rank_type_eighth)
    TextView rankTypeEighth;
    @BindView(R.id.rank_type_ninth)
    TextView rankTypeNinth;
    @BindView(R.id.rank_save)
    TextView rankSave;
    private BaseLadderPresenter mPresenter;
    private ArrayList<Integer> mInteger = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_rank_setting;
    }

    @Override
    public void initView() {
        mPresenter = new BaseLadderPresenter(this);
        mPresenter.getLadderRankSettingData();
    }

    @Override
    public void initToolbar() {
        commonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        commonTitleData.setText(getResources().getString(R.string.rank_setting_toolbar));
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, LadderRankSettingActivity.class);
        activity.startActivity(intent);
    }

    @OnClick({R.id.rank_type_second, R.id.rank_type_third,R.id.rank_type_third,R.id.rank_type_fourth,
    R.id.rank_type_fifth,R.id.rank_type_sixth,R.id.rank_type_seventh,R.id.rank_type_eighth,
    R.id.rank_type_ninth,R.id.rank_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rank_type_first:
                booleanData(1,rankTypeThird);
                break;
            case R.id.rank_type_second:
                booleanData(2,rankTypeSecond);
                break;
            case R.id.rank_type_third:
                booleanData(3,rankTypeThird);
                break;
            case R.id.rank_type_fourth:
                booleanData(4,rankTypeThird);
                break;
            case R.id.rank_type_fifth:
                booleanData(5,rankTypeThird);
                break;
            case R.id.rank_type_sixth:
                booleanData(6,rankTypeThird);
                break;
            case R.id.rank_type_seventh:
                booleanData(7,rankTypeThird);
                break;
            case R.id.rank_type_eighth:
                booleanData(8,rankTypeThird);
                break;
            case R.id.rank_type_ninth:
                booleanData(9,rankTypeThird);
                break;
            case R.id.rank_save:
                mPresenter.saveLadderRankSettingData(mInteger);
                break;
        }
    }

    private void booleanData(int number,TextView tv){
        if(mInteger.size()<4&&!mInteger.contains(number)){
            mInteger.add(number);
            tv.setSelected(true);
        }else{
            mInteger.remove(number);
            tv.setSelected(false);
        }
    }

    @Override
    public void showRankSettingFinish(ArrayList<Integer> mBean) {
        mInteger.clear();
        mInteger.addAll(mBean);
        initTypeBackground();
    }

    private void initTypeBackground() {
        // TODO 处理排行配置页面
    }

    @Override
    public void showRankSettingError() {

    }
}
