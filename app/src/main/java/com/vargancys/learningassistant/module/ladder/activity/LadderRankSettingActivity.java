package com.vargancys.learningassistant.module.ladder.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.db.ladder.LadderRankSettingBean;
import com.vargancys.learningassistant.module.ladder.view.LadderRankSettingView;
import com.vargancys.learningassistant.module.ladder.view.LadderView;
import com.vargancys.learningassistant.presenter.ladder.BaseLadderPresenter;
import com.vargancys.learningassistant.utils.ResourceUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

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
    private int[] mInt;
    private List<Integer> mInteger = new ArrayList<>();

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

        commonTitleData.setText(ResourceUtils.getString(getContext(),R.string.rank_setting_toolbar));
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, LadderRankSettingActivity.class);
        activity.startActivity(intent);
    }

    @OnClick({R.id.rank_type_second, R.id.rank_type_third,R.id.rank_type_first,R.id.rank_type_fourth,
    R.id.rank_type_fifth,R.id.rank_type_sixth,R.id.rank_type_seventh,R.id.rank_type_eighth,
    R.id.rank_type_ninth,R.id.rank_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rank_type_first:
                booleanData(1,rankTypeFirst);
                break;
            case R.id.rank_type_second:
                booleanData(2,rankTypeSecond);
                break;
            case R.id.rank_type_third:
                booleanData(3,rankTypeThird);
                break;
            case R.id.rank_type_fourth:
                booleanData(4,rankTypeFourth);
                break;
            case R.id.rank_type_fifth:
                booleanData(5,rankTypeFifth);
                break;
            case R.id.rank_type_sixth:
                booleanData(6,rankTypeSixth);
                break;
            case R.id.rank_type_seventh:
                booleanData(7,rankTypeSeventh);
                break;
            case R.id.rank_type_eighth:
                booleanData(8,rankTypeEighth);
                break;
            case R.id.rank_type_ninth:
                booleanData(9,rankTypeNinth);
                break;
            case R.id.rank_save:
                mPresenter.saveLadderRankSettingData(mInteger);
                break;
        }
    }

    private void booleanData(int number,TextView tv){
        if(mInteger.size()<4 && !mInteger.contains(number)){
            mInteger.add(number);
            tv.setSelected(true);
        }else{
            deleteInteger(number);
            tv.setSelected(false);
        }
    }

    private void deleteInteger(int number) {
        for (int i = 0; i< mInteger.size();i++){
            if(mInteger.get(i).equals(number)){
                mInteger.remove(i);
            }
        }
    }

    @Override
    public void showRankSettingFinish(List<LadderRankSettingBean> mBean) {
        mInteger.clear();
        for (LadderRankSettingBean bean:mBean){
            mInteger.add(bean.getType());
        }
        initTypeBackground();
    }

    private void initTypeBackground() {
        for (Integer bean: mInteger){
            switch (bean){
                case 1:
                    rankTypeFirst.setSelected(true);
                    break;
                case 2:
                    rankTypeSecond.setSelected(true);
                    break;
                case 3:
                    rankTypeThird.setSelected(true);
                    break;
                case 4:
                    rankTypeFourth.setSelected(true);
                    break;
                case 5:
                    rankTypeFifth.setSelected(true);
                    break;
                case 6:
                    rankTypeSixth.setSelected(true);
                    break;
                case 7:
                    rankTypeSeventh.setSelected(true);
                    break;
                case 8:
                    rankTypeEighth.setSelected(true);
                    break;
                case 9:
                    rankTypeNinth.setSelected(true);
                    break;
            }
        }
    }

    @Override
    public void showRankSettingError() {
        ToastUtils.ToastText(getContext(),ResourceUtils.getString(getContext(),R.string.rank_setting_error_text));
    }

    @Override
    public void saveRankSettingFinish() {
        ToastUtils.ToastText(getContext(),ResourceUtils.getString(getContext(),R.string.rank_setting_win_text));
        finish();
    }

    @Override
    public void saveRankSettingError(int error, String message) {
        ToastUtils.ToastText(getContext(),"Error ="+error+", Message ="+message);
    }
}
