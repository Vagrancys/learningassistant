package com.vargancys.learningassistant.module.mine.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseFragment;
import com.vargancys.learningassistant.db.mine.MineDataBean;
import com.vargancys.learningassistant.module.mine.activity.SettingActivity;
import com.vargancys.learningassistant.module.mine.adapter.MineSlidingAdapter;
import com.vargancys.learningassistant.module.mine.view.BaseMineView;
import com.vargancys.learningassistant.presenter.mine.BaseMinePresenter;
import com.vargancys.learningassistant.utils.CacheUtils;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Vagrancy
 * @date 2020/5/20
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 我的中心页面
 */
public class MineFragment extends BaseFragment implements BaseMineView {

    @BindView(R.id.common_back)
    ImageView commonBack;
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.common_img_one)
    ImageView commonImgOne;
    @BindView(R.id.common_img)
    ImageView commonImg;
    @BindView(R.id.mine_avatar)
    ImageView mineAvatar;
    @BindView(R.id.mine_name)
    TextView mineName;
    @BindView(R.id.mine_level)
    ImageView mineLevel;
    @BindView(R.id.ll_mine_data)
    LinearLayout llMineData;
    @BindView(R.id.mine_progressbar_data)
    TextView mineProgressbarData;
    @BindView(R.id.mine_money)
    TextView mineMoney;
    @BindView(R.id.mine_real_level)
    TextView mineRealLevel;
    @BindView(R.id.mine_knowledge)
    TextView mineKnowledge;
    @BindView(R.id.mine_influence)
    TextView mineInfluence;
    @BindView(R.id.sliding_tab)
    SlidingTabLayout slidingTab;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.mine_progress)
    ProgressBar mineProgress;

    public static MineFragment newInstance() {
        return new MineFragment();
    }
    private String[] mTitle;

    private BaseMinePresenter mPresenter;
    private long mineId;
    private MineSlidingAdapter mAdapter;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }


    @Override
    protected void initView() {
        mineId = CacheUtils.getLong(getContext(), ConstantsUtils.MINE_DATA_ID, 0);
        mPresenter = new BaseMinePresenter(this);
    }

    @Override
    public void initData() {
        mTitle = getResources().getStringArray(R.array.mine_sliding);
        mPresenter.getMineData(mineId);
        commonBack.setVisibility(View.GONE);
        commonTitle.setText(getResources().getString(R.string.mine_toolbar));
        commonImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingActivity.launch(getActivity());
            }
        });
        mAdapter = new MineSlidingAdapter(getFragmentManager(),mTitle);
        slidingTab.setViewPager(viewPager);
        slidingTab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

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
    }

    @Override
    public void showMineDataFinish(MineDataBean bean) {
        mineName.setText(bean.getName());
        setLevelImg(bean.getLevel());
        mineProgress.setMax(bean.getLevel_total());
        mineProgress.setProgress(bean.getLevel_current());
        mineMoney.setText(bean.getMoney());
        mineProgressbarData.setText(bean.getLevel_current()+"/"+bean.getLevel_total());
        mineRealLevel.setText(bean.getReal_level());
        mineKnowledge.setText(bean.getKnowledge());
        mineInfluence.setText(bean.getInfluence());
    }

    private void setLevelImg(int level){
        int ImgId = 0;
        switch (level){
            case 0:
                ImgId = R.drawable.know_level_0;
                break;
            case 1:
                ImgId = R.drawable.know_level_1;
                break;
            case 2:
                ImgId = R.drawable.know_level_2;
                break;
            case 3:
                ImgId = R.drawable.know_level_3;
                break;
            case 4:
                ImgId = R.drawable.know_level_4;
                break;
            case 5:
                ImgId = R.drawable.know_level_5;
                break;
        }
        mineLevel.setImageResource(ImgId);
    }

    @Override
    public void showMineDataError(int error, String message) {
        ToastUtils.ToastText(getContext(), "Error =" + error + ",Message =" + message);
        mineName.setText("--");
        mineLevel.setImageResource(R.drawable.know_level_0);
        mineProgressbarData.setText("00/00");
        mineMoney.setText("--");
        mineProgress.setMax(0);
        mineProgress.setProgress(0);
        mineRealLevel.setText("--");
        mineKnowledge.setText("--");
        mineInfluence.setText("--");
    }

}
