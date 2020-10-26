package com.vargancys.learningassistant.module.home.activity.show;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.model.home.bean.BookBean;
import com.vargancys.learningassistant.module.home.activity.data.DataBookActivity;
import com.vargancys.learningassistant.module.home.adapter.BookShowItemAdapter;
import com.vargancys.learningassistant.presenter.home.BookPresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/06
 * version:1.0
 * 知识展示二级页面
 */
public class ShowBookActivity extends BaseActivity{
    private static final String TAG = "ShowArticleActivity";

    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.book_title)
    TextView bookTitle;
    @BindView(R.id.book_data)
    TextView bookData;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private BookShowItemAdapter mAdapter;
    private BookPresenter mPresenter;
    private BookBean bookBean;
    private ArrayList<BookBean.BookItemBean> mItems = new ArrayList<>();
    private int article_id;
    private static int REQUEST_CODE = 2001;
    private int count;

    @Override
    public int getLayoutId() {
        return R.layout.activity_knowledge_show_book;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        if(intent !=null){
            article_id = intent.getIntExtra(ConstantsUtils.KNOWLEDGE_ARTICLE_ID,0);
        }
        init();
        mPresenter = new BookPresenter(this);
        mPresenter.query(article_id);

    }

    private void init() {

        mAdapter = new BookShowItemAdapter(getSupportFragmentManager(),mItems);
        viewPager.setAdapter(mAdapter);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                bookData.setText(String.format(Locale.CHINA,"%d/%d", i, count));
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    public static void launch(Activity activity, long item_id) {
        Intent intent = new Intent(activity, ShowBookActivity.class);
        intent.putExtra(ConstantsUtils.KNOWLEDGE_ARTICLE_ID, item_id);
        activity.startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE&&resultCode == DataBookActivity.RESULT_CODE&&data !=null){
            int state = data.getIntExtra(ConstantsUtils.ITEM_DELETE_STATUS,0);
            if(state == 1){
                finish();
            }else if(state == 2){
                mPresenter.query(article_id);
            }
        }
    }

    @OnClick({R.id.common_back,R.id.common_img})
    public void onViewClicked(View itemView){
        switch (itemView.getId()){
            case R.id.common_back:
                finish();
                break;
            case R.id.common_img:
                DataBookActivity.launch(ShowBookActivity.this,REQUEST_CODE,article_id);
                break;
        }
    }

    @Override
    public void onSuccess(Object object) {
        bookBean = (BookBean) object;
        bookTitle.setText(bookBean.getTitle());
        bookData.setText(String.format(Locale.CHINA,"1/%d", bookBean.getItems().size()));
        count = bookBean.getItems().size();
        mItems.addAll(bookBean.getItems());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFail() {
        ToastUtils.ToastText(getContext(),R.string.book_query_empty);
    }

    @Override
    public void onError(String message) {
        ToastUtils.ToastText(getContext(),R.string.common_error);
    }
}
