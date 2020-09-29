package com.vargancys.learningassistant.module.home.fragment;

import android.os.Bundle;

import com.vargancys.learningassistant.base.BaseFragment;
import com.vargancys.learningassistant.bean.home.BookBean;
import com.vargancys.learningassistant.utils.ConstantsUtils;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/9/28
 * version:1.0
 * 模块名: 书籍型更新
 */
public class BookUpdateFragment extends BaseFragment {
    private BookBean.BookItemBean mItemBean;
    private String bookContent;
    public static BookUpdateFragment newInstance(String content){
        BookUpdateFragment mFragment = new BookUpdateFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ConstantsUtils.KNOWLEDGE_BOOK_CONTENT,content);
        mFragment.setArguments(bundle);
        return mFragment;
    }
    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    protected void initView() {
        if(getArguments() != null){
            bookContent = getArguments().getString(ConstantsUtils.KNOWLEDGE_BOOK_CONTENT);
        }
    }

    public BookBean.BookItemBean getItemBean() {
        return mItemBean;
    }
}
