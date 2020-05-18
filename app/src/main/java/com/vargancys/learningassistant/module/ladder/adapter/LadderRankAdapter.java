package com.vargancys.learningassistant.module.ladder.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

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
    private static String TAG = "LadderRankAdapter";
    public LadderRankAdapter(FragmentManager fm,int size){
        super(fm);
        mSize = size;
        mFragment = new Fragment[size];
    }
    @Override
    public Fragment getItem(int position) {
        if(mFragment[position] == null){
            switch (position){
                case 0:
                case 1:
                case 2:
                    mFragment[position] = ZoneRankFragment.newInstance(position);
                    break;
                default:
                    break;
            }
        }
        Log.e(TAG,"position ="+position);
        return mFragment[position];
    }

    @Override
    public int getCount() {
        return mSize;
    }
}
