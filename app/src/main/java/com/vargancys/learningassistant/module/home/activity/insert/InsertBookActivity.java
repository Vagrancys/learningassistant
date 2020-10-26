package com.vargancys.learningassistant.module.home.activity.insert;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.model.home.bean.BookBean;
import com.vargancys.learningassistant.module.home.adapter.BookItemAdapter;
import com.vargancys.learningassistant.module.home.fragment.BookContentFragment;
import com.vargancys.learningassistant.module.home.view.InsertBookView;
import com.vargancys.learningassistant.presenter.home.BookPresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ToastUtils;
import com.vargancys.learningassistant.widget.dialog.KnowLedgeDataDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/09
 * version:1.0
 * 知识添加二级页面
 */
public class InsertBookActivity extends BaseActivity  implements InsertBookView {
    private String TAG = "InsertBookActivity";
    @BindView(R.id.common_img)
    ImageView commonImg;
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.book_viewpager)
    ViewPager bookViewPager;
    private BookPresenter mPresenter;
    private int knowledge_id;
    private BookBean mBook = new BookBean();
    private BookItemAdapter mAdapter;
    private int mBookIndex = 0;
    private KnowLedgeDataDialog mDialog;

    @Override
    public int getLayoutId() {
        return R.layout.activity_knowledge_insert_book;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        if(intent != null){
            knowledge_id = intent.getIntExtra(ConstantsUtils.KNOWLEDGE_ID,0);
        }
        mPresenter = new BookPresenter(this);
        init();

    }

    private void init() {
        mAdapter = new BookItemAdapter(getSupportFragmentManager());
        bookViewPager.setAdapter(mAdapter);
        initDialog();
    }

    @Override
    public void initToolbar() {
        commonTitle.setText(getText(R.string.book_insert_toolbar));
    }

    private void initDialog(){
        mDialog = new KnowLedgeDataDialog(this);
        mDialog.setOnClickCancelListener(() -> mDialog.cancel());
        mDialog.setOnClickDeterMineListener((common, title, summary, explain) -> {
            mBook.setLevel(common);
            mBook.setTitle(title);
            mBook.setSummary(summary);
            mBook.setExplain(explain);
            mDialog.dismiss();
        });
    }

    public static void launch(Activity activity, int knowledge_id){
        Intent intent = new Intent(activity, InsertBookActivity.class);
        intent.putExtra(ConstantsUtils.KNOWLEDGE_ID,knowledge_id);
        activity.startActivity(intent);
    }

    @OnClick({R.id.common_back,R.id.book_data,R.id.common_add,R.id.common_save})
    public void onViewClicked(View itemView){
        switch (itemView.getId()){
            case R.id.common_back:
                finish();
                break;
            case R.id.book_data:
                if(!mDialog.isEdit()){
                    mDialog.setTitle(mBook.getTitle());
                    mDialog.setLevel(mBook.getLevel());
                    mDialog.setExplain(mBook.getExplain());
                    mDialog.setSummary(mBook.getSummary());
                }
                mDialog.show();
                break;
            case R.id.common_add:
                mAdapter.addFragment(mBookIndex);
                mBookIndex ++;
                mAdapter.notifyDataSetChanged();
                bookViewPager.setCurrentItem(mBookIndex-1);
                break;
            case R.id.common_save:
                if(!mPresenter.isAddEmpty()){
                    for (Fragment fragment : mAdapter.getFragment()) {
                        BookBean.BookItemBean bookItem = ((BookContentFragment) fragment).getBookItem();
                        if(bookItem != null && !bookItem.getContent().isEmpty()){
                            mBook.addItem(bookItem);
                        }
                    }
                    mBook.setFather_id(knowledge_id);
                    mPresenter.add(mBook);
                }
                break;
        }
    }

    @Override
    public void onSuccess() {
        ToastUtils.ToastText(getContext(),R.string.book_success);
    }

    @Override
    public void onFail() {
        ToastUtils.ToastText(getContext(),R.string.common_fail);
    }

    @Override
    public void onError(String message) {
        ToastUtils.ToastText(getContext(),R.string.common_error);
    }

    @Override
    public boolean isBookEmpty() {
        int count = 0;
        for (Fragment fragment : mAdapter.getFragment()) {
            BookBean.BookItemBean bookItem = ((BookContentFragment) fragment).getBookItem();
            if(bookItem == null && !bookItem.getContent().isEmpty()){
                count ++;
            }
        }
        return count != 0;
    }
}
