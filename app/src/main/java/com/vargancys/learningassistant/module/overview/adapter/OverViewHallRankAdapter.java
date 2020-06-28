package com.vargancys.learningassistant.module.overview.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.vargancys.learningassistant.module.overview.fragment.OverViewHallHotFragment;
import com.vargancys.learningassistant.module.overview.fragment.OverViewHallQualityFragment;
import com.vargancys.learningassistant.module.overview.fragment.OverViewHallSystemFragment;

/**
 * @author Vagrancy
 * @date 2020/6/28
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识体系大厅排行适配器
 */
public class OverViewHallRankAdapter extends FragmentPagerAdapter {
    private Fragment[] mFragment;
    private int mSize;
    private String[] mRank;
    public OverViewHallRankAdapter(FragmentManager fm,String[] rank){
        super(fm);
        mRank = rank;
        mSize = rank.length;
        mFragment = new Fragment[mSize];
    };
    @Override
    public Fragment getItem(int position) {
        if(mFragment[position] == null){
            switch (position){
                case 0:
                    mFragment[position] = OverViewHallHotFragment.newInstance();
                    break;
                case 1:
                    mFragment[position] = OverViewHallSystemFragment.newInstance();
                    break;
                case 2:
                    mFragment[position] = OverViewHallQualityFragment.newInstance();
                    break;
            }
        }
        return mFragment[position];
    }

    @Override
    public int getCount() {
        return mSize;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mRank[position];
    }
}
