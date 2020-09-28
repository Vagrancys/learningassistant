package com.vargancys.learningassistant.module.home.activity.update;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.bean.home.BookBean;
import com.vargancys.learningassistant.bean.home.HomeKnowContent;
import com.vargancys.learningassistant.bean.home.HomeKnowFunction;
import com.vargancys.learningassistant.bean.home.HomeKnowHistory;
import com.vargancys.learningassistant.bean.home.HomeKnowHistoryFunction;
import com.vargancys.learningassistant.module.home.adapter.BookItemAdapter;
import com.vargancys.learningassistant.module.home.adapter.BookUpdateAdapter;
import com.vargancys.learningassistant.module.home.adapter.HomeKnowSecondAdapter;
import com.vargancys.learningassistant.module.home.view.BaseKnowLedgeUpdateView;
import com.vargancys.learningassistant.presenter.home.BookPresenter;
import com.vargancys.learningassistant.presenter.home.KnowUpdatePresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ToastUtils;
import com.vargancys.learningassistant.widget.KnowLedgeDataDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/09
 * version:1.0
 * 知识更新二级页面
 */
public class UpdateBookActivity extends BaseActivity  implements BaseKnowLedgeUpdateView {
    private String TAG = "KnowInsertSecondActivity";
    @BindView(R.id.common_img)
    ImageView commonImg;
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.book_title)
    TextView bookTitle;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.book_data)
    TextView bookData;
    private BookPresenter mPresenter;
    private int articleId;
    private int fatherId;
    private ArrayList<BookBean.BookItemBean> mItems = new ArrayList<>();
    private BookUpdateAdapter mAdapter;
    private int mCommon = 1;
    private KnowLedgeDataDialog mDialog;
    private int RESULT_CODE = 2002;

    @Override
    public int getLayoutId() {
        return R.layout.activity_knowledge_update_book;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        if(intent != null){
            articleId = intent.getIntExtra(ConstantsUtils.KNOWLEDGE_ARTICLE_ID,0);
            fatherId = intent.getIntExtra(ConstantsUtils.KNOWLEDGE_FATHER_ID,0);
        }
        mPresenter = new BookPresenter(this);
        initRecyclerView();
        initListener();
        initDialog();
        mPresenter.query(articleId);
    }

    private void initRecyclerView() {
        mAdapter = new BookUpdateAdapter(getSupportFragmentManager(),mItems);
        viewPager.setAdapter(mAdapter);
        viewPager.setCurrentItem(0);
    }

    @Override
    public void initToolbar() {
        commonTitle.setText(getText(R.string.common_update_second));

        commonImg.setImageResource(R.drawable.common_update_normal);
    }

    private void initListener() {
    }

    private void initDialog(){
        mDialog = new KnowLedgeDataDialog(this);
        final View popView = View.inflate(getContext(),R.layout.pop_knowledge_data,null);
        mDialog.setParentView(popView);
        mDialog.setOnClickCancelListener(new KnowLedgeDataDialog.OnClickCancelListener() {
            @Override
            public void OnCancel() {
                mDialog.cancel();
            }
        });
        mDialog.setOnClickDeterMineListener(new KnowLedgeDataDialog.OnClickDeterMineListener() {
            @Override
            public void OnDeterMine(int common,String title,String summary,String explain) {
                mCommon = common;
                if(mPresenter.isFunctionSecondEmpty(mCommon,title,
                        summary,explain)){
                    ToastUtils.ToastText(getContext(),"请输入完整!");
                }else{
                    mPresenter.addFunctionSecondData(mCommon,title,
                            summary,explain);
                }
            }
        });
    }

    public static void launch(Activity activity,int REQUEST_CODE,int father_id,int article_id){
        Intent intent = new Intent(activity, UpdateBookActivity.class);
        intent.putExtra(ConstantsUtils.KNOWLEDGE_ARTICLE_ID,article_id);
        intent.putExtra(ConstantsUtils.KNOWLEDGE_FATHER_ID,father_id);
        activity.startActivityForResult(intent,REQUEST_CODE);
    }

    @OnClick({R.id.common_back,R.id.common_img})
    public void onViewClicked(View itemView){
        switch (itemView.getId()){
            case R.id.common_back:
                setResult(RESULT_CODE);
                finish();
                break;
            case R.id.common_img:
                mPresenter.isKnowUpdateSecondEmpty();
                break;
        }
    }

    @Override
    public boolean isPass() {
        return false;
    }

    @Override
    public void isPassSuccess() {

    }

    @Override
    public void isPassFail() {

    }

    @Override
    public void onUpdateSuccess() {

    }

    @Override
    public void onUpdateFail() {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onSuccess(Object object) {

    }

    @Override
    public void onNoData() {

    }

    @Override
    public void onFail() {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void onFinish() {

    }
}
