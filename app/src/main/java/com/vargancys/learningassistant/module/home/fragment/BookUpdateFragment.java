package com.vargancys.learningassistant.module.home.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseFragment;
import com.vargancys.learningassistant.model.home.bean.BookBean;
import com.vargancys.learningassistant.utils.ConstantsUtils;

import butterknife.BindView;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/9/28
 * version:1.0
 * 模块名: 书籍型更新
 */
public class BookUpdateFragment extends BaseFragment {
    private BookBean.BookItemBean mItemBean;
    private String content;
    private int index;
    @BindView(R.id.book_content)
    TextView bookContent;
    public static BookUpdateFragment newInstance(String content,int index){
        BookUpdateFragment mFragment = new BookUpdateFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantsUtils.KNOWLEDGE_BOOK_INDEX,index);
        bundle.putString(ConstantsUtils.KNOWLEDGE_BOOK_CONTENT,content);
        mFragment.setArguments(bundle);
        return mFragment;
    }
    @Override
    public int getLayoutId() {
        return R.layout.fragment_book_update;
    }

    @Override
    protected void initView() {
        if(getArguments() != null){
            content = getArguments().getString(ConstantsUtils.KNOWLEDGE_BOOK_CONTENT);
            index = getArguments().getInt(ConstantsUtils.KNOWLEDGE_BOOK_INDEX);
        }
        bookContent.setText(content);
        mItemBean = new BookBean.BookItemBean();
        mItemBean.setContent(content);
    }

    public BookBean.BookItemBean getItemBean() {
        return mItemBean;
    }
}
