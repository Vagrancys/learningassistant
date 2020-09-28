package com.vargancys.learningassistant.module.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.vargancys.learningassistant.bean.home.BookBean;
import com.vargancys.learningassistant.module.home.fragment.BookContentFragment;
import com.vargancys.learningassistant.module.home.fragment.BookUpdateFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/9/28
 * version:1.0
 * 模块名: 书籍更新适配器
 */
public class BookUpdateAdapter extends FragmentPagerAdapter {
    private List<BookBean.BookItemBean> mItems;
    private Fragment[] mFragment;
    public BookUpdateAdapter(FragmentManager fm, ArrayList<BookBean.BookItemBean> mItems) {
        super(fm);
        this.mItems = mItems;
        mFragment = new Fragment[mItems.size()];
    }

    @Override
    public Fragment getItem(int i) {
        if(mFragment[i] == null){
            mFragment[i] = BookUpdateFragment.newInstance(mItems.get(i).getContent());
        }
        return null;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }
}
