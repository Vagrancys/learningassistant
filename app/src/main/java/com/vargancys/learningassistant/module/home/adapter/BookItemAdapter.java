package com.vargancys.learningassistant.module.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.vargancys.learningassistant.module.home.fragment.BookContentFragment;

import java.util.ArrayList;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/9/25
 * version:1.0
 * 模块名: 书籍型fragment适配器
 */
public class BookItemAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> mFragment = new ArrayList<>();
    private int count;
    public BookItemAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(int index){
        BookContentFragment fragment = BookContentFragment.newInstance(index);
        mFragment.add(fragment);
        count ++;
    }

    public int getLast(){
        return count-1;
    }

    public ArrayList<Fragment> getFragment() {
        return mFragment;
    }

    @Override
    public Fragment getItem(int i) {
        return mFragment.get(i);
    }

    @Override
    public int getCount() {
        return count;
    }
}
