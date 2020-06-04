package com.vargancys.learningassistant.module.ladder.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.module.ladder.adapter.LadderRankAdapter;
import com.vargancys.learningassistant.utils.ResourceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Vagrancy
 * @date 2020/5/7
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 天梯排行中心
 */
public class LadderRankActivity extends BaseActivity {
    private static String TAG = "LadderRankActivity";
    @BindView(R.id.common_back)
    ImageView commonBack;
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.common_img)
    ImageView commonImg;
    @BindView(R.id.sliding_tab)
    SlidingTabLayout slidingTab;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private String[] mRank = {
            "单人帮", "分区榜", "全服榜"
    };
    private LadderRankAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_ladder_rank;
    }

    @Override
    public void initToolbar() {
        commonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        commonTitle.setText(ResourceUtils.getString(getContext(),R.string.ladder_rank_toolbar));
        commonImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LadderRankSettingActivity.launch(LadderRankActivity.this);
            }
        });
    }

    @Override
    public void initView() {
        Log.e(TAG,"断点!");
        mAdapter = new LadderRankAdapter(getSupportFragmentManager(),mRank.length);
        viewPager.setAdapter(mAdapter);
        viewPager.setOffscreenPageLimit(mRank.length);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                slidingTab.setCurrentTab(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        slidingTab.setViewPager(viewPager,mRank);
        slidingTab.setCurrentTab(0);
        slidingTab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, LadderRankActivity.class);
        activity.startActivity(intent);
    }
}
