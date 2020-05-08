package com.vargancys.learningassistant.module.ladder.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import com.vargancys.learningassistant.module.ladder.fragment.CommunicationFragment;

/**
 * @author Vagrancy
 * @date 2020/5/8
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 天梯交流区的适配器
 */
public class CommunicationPagerAdapter extends FragmentPagerAdapter {
    private Fragment[] mFragment;
    private int mSize;
    public CommunicationPagerAdapter(FragmentManager fragmentManager,String[] mTab){
        super(fragmentManager);
        mSize = mTab.length;
        mFragment = new Fragment[mSize];
    }

    @Override
    public Fragment getItem(int position) {
        if(mFragment[position] == null){
            mFragment[position] = CommunicationFragment.newInstance(position);
        }
        return mFragment[position];
    }

    @Override
    public int getCount() {
        return mSize;
    }
}
