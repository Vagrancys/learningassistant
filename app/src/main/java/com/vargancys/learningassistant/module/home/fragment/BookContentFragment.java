package com.vargancys.learningassistant.module.home.fragment;

import android.os.Bundle;

import com.vargancys.learningassistant.base.BaseFragment;
import com.vargancys.learningassistant.bean.home.BookBean;
import com.vargancys.learningassistant.utils.ConstantsUtils;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/9/25
 * version:1.0
 * 模块名: 书籍型内容碎片
 */
public class BookContentFragment extends BaseFragment {
    private static BookBean.BookItemBean mBookItem;
    public static BookContentFragment newInstance(int index) {
        BookContentFragment fragment = new BookContentFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantsUtils.KNOWLEDGE_BOOK_INDEX,index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    protected void initView() {

    }

    public static BookBean.BookItemBean getBookItem() {
        return mBookItem;
    }
}
