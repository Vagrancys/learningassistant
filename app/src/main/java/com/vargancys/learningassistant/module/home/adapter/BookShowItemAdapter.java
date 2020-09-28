package com.vargancys.learningassistant.module.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.vargancys.learningassistant.bean.home.BookBean;
import com.vargancys.learningassistant.module.home.fragment.BookContentFragment;
import com.vargancys.learningassistant.module.home.fragment.BookShowFragment;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/9/25
 * version:1.0
 * 模块名: 书籍型fragment展示适配器
 */
public class BookShowItemAdapter extends FragmentPagerAdapter {
    private Fragment[] mFragment;
    private int count;
    private ArrayList<BookBean.BookItemBean> mItems;
    public BookShowItemAdapter(FragmentManager fm, ArrayList<BookBean.BookItemBean> items) {
        super(fm);
        this.mItems = items;
        mFragment = new Fragment[mItems.size()];
    }

    @Override
    public Fragment getItem(int i) {
        if(mFragment[i] == null){
            mFragment[i] = BookShowFragment.newInstance(mItems.get(i).getContent());
        }
        return mFragment[i];
    }

    @Override
    public int getCount() {
        return mItems.size();
    }
}
