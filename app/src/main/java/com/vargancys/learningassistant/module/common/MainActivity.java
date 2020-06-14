package com.vargancys.learningassistant.module.common;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.module.common.adapter.CommonFragmentAdapter;
import com.vargancys.learningassistant.utils.ResourceUtils;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    private String TAG = "MainActivity";
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.commonTab)
    CommonTabLayout commonTab;
    private CommonFragmentAdapter commonAdapter;
    private String[] mTitle;
    private ArrayList<CustomTabEntity> customTabEntities = new ArrayList<>();
    private int[] mSelected = {
            R.drawable.main_home_selected,R.drawable.main_overview_selected,
            R.drawable.main_game_selected,R.drawable.main_ladder_selected,
            R.drawable.main_my_selected
    };
    private int[] mUnselected = {
            R.drawable.main_home_normal,R.drawable.main_overview_normal,
            R.drawable.main_game_normal,R.drawable.main_ladder_normal,
            R.drawable.main_my_normal
    };
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        mTitle = ResourceUtils.getStringArray(getContext(),R.array.main_title);
        initEntity();
        commonAdapter = new CommonFragmentAdapter(getSupportFragmentManager(), mTitle.length);
        viewPager.setAdapter(commonAdapter);
        viewPager.addOnPageChangeListener(new CommonPageChangeListener());
        commonTab.setTabData(customTabEntities);
        commonTab.setCurrentTab(0);
        commonTab.setOnTabSelectListener(new CommonTabSelectListener());
    }

    private void initEntity() {
        for (int i = 0,length = mTitle.length;i < length; i++){
            CustomTabEntity customTabEntity = new MainCustomTabEntity(mTitle[i],mSelected[i],mUnselected[i]);
            customTabEntities.add(customTabEntity);
        }
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

    class MainCustomTabEntity implements CustomTabEntity{
        private String Title;
        private int Selected;
        private int Unselected;
        public MainCustomTabEntity(String Title,int Selected,int Unselected){
            this.Title = Title;
            this.Selected = Selected;
            this.Unselected = Unselected;
        }
        @Override
        public String getTabTitle() {
            return Title;
        }

        @Override
        public int getTabSelectedIcon() {
            return Selected;
        }

        @Override
        public int getTabUnselectedIcon() {
            return Unselected;
        }
    }

    private class CommonTabSelectListener implements OnTabSelectListener {

        @Override
        public void onTabSelect(int position) {
            viewPager.setCurrentItem(position);
        }

        @Override
        public void onTabReselect(int position) {

        }
    }

    private class CommonPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int position) {
            commonTab.setCurrentTab(position);
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    }

    public Fragment getFragment(int position){
        return commonAdapter.getItem(position);
    }
}
