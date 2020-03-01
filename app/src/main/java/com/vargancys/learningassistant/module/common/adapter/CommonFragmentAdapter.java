package com.vargancys.learningassistant.module.common.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.vargancys.learningassistant.module.home.fragment.HomeFragment;

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
            }
        }
        return mFragment[position];
    }

    @Override
    public int getCount() {
        return mFragment.length;
    }
}
