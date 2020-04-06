package com.vargancys.learningassistant.module.common.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.vargancys.learningassistant.module.game.fragment.GameFragment;
import com.vargancys.learningassistant.module.home.fragment.HomeFragment;
import com.vargancys.learningassistant.module.overview.fragment.OverViewFragment;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/01
 * version:1.0
 */
public class CommonFragmentAdapter extends FragmentPagerAdapter {
    private Fragment[] mFragment;
    public CommonFragmentAdapter(FragmentManager fragmentManager,int size){
        super(fragmentManager);
        mFragment = new Fragment[size];
    }


    @Override
    public Fragment getItem(int position) {
        if(mFragment[position] == null){
            switch (position){
                case 0:
                    mFragment[position] = HomeFragment.newInstance();
                    break;
                case 1:
                    mFragment[position] = OverViewFragment.newInstance();
                    break;
                case 2:
                    mFragment[position] = GameFragment.newInstance();
                case 3:
                    mFragment[position] = HomeFragment.newInstance();
                case 4:
                    mFragment[position] = HomeFragment.newInstance();
            }
        }
        return mFragment[position];
    }

    @Override
    public int getCount() {
        return mFragment.length;
    }
}
