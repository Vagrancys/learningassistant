package com.vargancys.learningassistant.module.mine.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.vargancys.learningassistant.module.mine.fragment.ChallengeFragment;
import com.vargancys.learningassistant.module.mine.fragment.KnowLedgeFragment;
import com.vargancys.learningassistant.module.mine.fragment.LevelFragment;
import com.vargancys.learningassistant.module.mine.fragment.ProblemFragment;
import com.vargancys.learningassistant.module.mine.fragment.SystemFragment;

/**
 * @author Vagrancy
 * @date 2020/5/21
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 个人中心滑动条适配器
 */
public class MineSlidingAdapter extends FragmentPagerAdapter {
    private static String TAG = "MineSlidingAdapter";
    private Fragment[] mFragment;
    private int mSize;
    private String[] mTitle;
    public MineSlidingAdapter(FragmentManager fm, String[] title){
        super(fm);

        mTitle = title;
        mSize = mTitle.length;
        Log.e(TAG,"length ="+mTitle.length+",size ="+mSize);
        mFragment = new Fragment[mSize];
    }

    @Override
    public Fragment getItem(int i) {
        if(mFragment[i] == null){
            Log.e(TAG,"fragment = "+i);
            switch (i){
                case 0:
                    mFragment[i] = KnowLedgeFragment.newInstance();
                    break;
                case 1:
                    mFragment[i] = SystemFragment.newInstance();
                    break;
                case 2:
                    mFragment[i] = ChallengeFragment.newInstance();
                    break;
                case 3:
                    mFragment[i] = ProblemFragment.newInstance();
                    break;
                case 4:
                    mFragment[i] = LevelFragment.newInstance();
                    break;
            }
        }
        return mFragment[i];
    }

    @Override
    public int getCount() {
        return mFragment.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle[position];
    }
}
