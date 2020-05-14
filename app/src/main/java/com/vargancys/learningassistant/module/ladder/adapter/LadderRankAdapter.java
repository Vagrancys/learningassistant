package com.vargancys.learningassistant.module.ladder.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.vargancys.learningassistant.module.ladder.fragment.ZoneRankFragment;

/**
 * @author Vagrancy
 * @date 2020/5/14
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 天梯排行适配器
 */
public class LadderRankAdapter extends FragmentPagerAdapter {
    private Fragment[] mFragment;
    private int mSize;
    public LadderRankAdapter(FragmentManager fm,int size){
        super(fm);
        mSize = size;
        mFragment = new Fragment[mSize];
    }
    @Override
    public Fragment getItem(int position) {
        if(mFragment[position] == null){
            switch (position){
                case 0:
                    ZoneRankFragment.newInstance(0);
                    break;
                case 1:
                    ZoneRankFragment.newInstance(1);
                    break;
                case 2:
                    ZoneRankFragment.newInstance(2);
                    break;
            }
        }
        return mFragment[position];
    }

    @Override
    public int getCount() {
        return mSize;
    }
}
