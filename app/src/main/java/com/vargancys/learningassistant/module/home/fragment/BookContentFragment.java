package com.vargancys.learningassistant.module.home.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseFragment;
import com.vargancys.learningassistant.bean.home.BookBean;
import com.vargancys.learningassistant.utils.ConstantsUtils;

import butterknife.BindView;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/9/25
 * version:1.0
 * 模块名: 书籍型内容碎片
 */
public class BookContentFragment extends BaseFragment {
    private static BookBean.BookItemBean mBookItem;
    @BindView(R.id.book_content)
    EditText bookContent;
    private int book_index;
    public static BookContentFragment newInstance(int index) {
        BookContentFragment fragment = new BookContentFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantsUtils.KNOWLEDGE_BOOK_INDEX,index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_knowledge_book_content;
    }

    @Override
    protected void initView() {
        if(getArguments() != null){
            book_index = getArguments().getInt(ConstantsUtils.KNOWLEDGE_BOOK_INDEX);
        }
        mBookItem = new BookBean.BookItemBean();
        mBookItem.setIndex(book_index);
        bookContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mBookItem.setContent(bookContent.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public static BookBean.BookItemBean getBookItem() {
        return mBookItem;
    }
}
