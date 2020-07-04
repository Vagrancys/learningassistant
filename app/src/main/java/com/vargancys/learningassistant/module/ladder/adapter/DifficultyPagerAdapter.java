package com.vargancys.learningassistant.module.ladder.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.vargancys.learningassistant.module.ladder.fragment.DifficultyFragment;

/**
 * @author Vagrancy
 * @date 2020/5/8
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 天梯难度区的适配器
 */
public class DifficultyPagerAdapter extends FragmentPagerAdapter {
    private Fragment[] mFragment;
    private int mSize;
    public DifficultyPagerAdapter(FragmentManager fragmentManager, String[] mTab){
        super(fragmentManager);
        mSize = mTab.length;
        mFragment = new Fragment[mSize];
    }

    @Override
    public Fragment getItem(int position) {
        if(mFragment[position] == null){
            mFragment[position] = DifficultyFragment.newInstance(position);
        }
        return mFragment[position];
    }

    @Override
    public int getCount() {
        return mSize;
    }
}
