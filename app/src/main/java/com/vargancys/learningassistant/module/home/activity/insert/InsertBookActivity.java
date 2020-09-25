package com.vargancys.learningassistant.module.home.activity.insert;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.bean.home.BookBean;
import com.vargancys.learningassistant.bean.home.HomeKnowFunction;
import com.vargancys.learningassistant.module.home.adapter.BookItemAdapter;
import com.vargancys.learningassistant.module.home.fragment.BookContentFragment;
import com.vargancys.learningassistant.module.home.view.InsertArticleView;
import com.vargancys.learningassistant.module.home.view.KnowInsertSecondView;
import com.vargancys.learningassistant.presenter.home.BookPresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ToastUtils;
import com.vargancys.learningassistant.widget.FunctionDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/09
 * version:1.0
 * 知识添加二级页面
 */
public class InsertBookActivity extends BaseActivity  implements InsertArticleView {
    private String TAG = "KnowInsertSecondActivity";
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
    private int mCommon = 1;
    private int mBookIndex = 0;
    private FunctionDialog mDialog;

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
        initListener();
        initDialog();
    }

    private void init() {
        mAdapter = new BookItemAdapter(getSupportFragmentManager());
        bookViewPager.setAdapter(mAdapter);
    }

    @Override
    public void initToolbar() {
        commonTitle.setText(getText(R.string.common_second));

        commonImg.setImageResource(R.drawable.comment_complete_selector);
    }

    private void initListener() {
        mAdapter.setOnItemLongClickListener(new BaseRecyclerAdapter.OnItemLongClickListener() {
            @Override
            public void OnItemLongClick(final int position) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setTitle(homeKnowFunctions.get(position).getTitle());
                alert.setMessage(homeKnowFunctions.get(position).getSummary());
                alert.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alert.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        homeKnowFunctions.remove(position);
                        mAdapter.notifyItemRemoved(position);
                        dialog.dismiss();
                    }
                });
            }
        });

        insertShowAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.showSecondFunctionWindow();
            }
        });
    }

    @Override
    public boolean isSecondEmpty() {
        return insertTitleEdit.getText().toString().isEmpty()
                &&insertSummaryEdit.getText().toString().isEmpty()
                &&insertHeedEdit.getText().toString().isEmpty()
                &&insertExperienceEdit.getText().toString().isEmpty()
                &&homeKnowFunctions.size() > 0;
    }

    @Override
    public void isSecondEmptyError(int error, String msg) {
        ToastUtils.ToastText(getContext(),"Error = "+error+",Msg = "+msg+"请填满每个知识项!");
    }

    @Override
    public void isSecondEqualsItem() {
        mPresenter.isEqualsSecondItem(insertTitleEdit.getText().toString());
    }

    @Override
    public void saveSecondKnowItem() {
        mPresenter.saveKnowSecondItem(know_item_id,insertTitleEdit.getText().toString(),
                insertSummaryEdit.getText().toString(),
                homeKnowFunctions,
                insertHeedEdit.getText().toString(),
                insertExperienceEdit.getText().toString());
    }

    private void initDialog(){
        mDialog = new FunctionDialog(this);
        final View popView = View.inflate(getContext(),R.layout.pop_function_second,null);
        mDialog.setParentView(popView);
        mDialog.setOnClickCancelListener(new FunctionDialog.OnClickCancelListener() {
            @Override
            public void OnCancel() {
                mDialog.cancel();
            }
        });
        mDialog.setOnClickDeterMineListener(new FunctionDialog.OnClickDeterMineListener() {
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

    @Override
    public void isSecondEqualsError(int error, String msg) {
        ToastUtils.ToastText(getContext(),"Error = "+error+",Msg = "+msg+"该知识已经添加了!请重新输入!");
    }

    public static void launch(Activity activity, int knowledge_id){
        Intent intent = new Intent(activity, InsertBookActivity.class);
        intent.putExtra(ConstantsUtils.KNOWLEDGE_ID,knowledge_id);
        activity.startActivity(intent);
    }

    @Override
    public void saveSecondItemError(int error, String msg) {
        ToastUtils.ToastText(getContext(),"Error = "+error+", Msg = "+msg);
    }

    @Override
    public void saveSecondItemFinish() {
        ToastUtils.ToastText(getContext(),R.string.know_insert_success_text);
        initEmpty();
        finish();
    }

    @Override
    public boolean isFunctionEmpty(int common, String title, String summary, String explain) {
        return common != 0 &&title.isEmpty()&&summary.isEmpty()&&explain.isEmpty();
    }

    @Override
    public void addFunctionFinish() {
        ToastUtils.ToastText(getContext(),"添加函数项成功了!");
        if(homeKnowFunctions.size() > 0){
            recyclerView.setVisibility(View.VISIBLE);
            showHintSecond.setVisibility(View.GONE);
            insertShowCount.setVisibility(View.VISIBLE);
            insertShowCount.setText(String.valueOf(homeKnowFunctions.size()));
        }
        mDialog.clearData();
        mDialog.cancel();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void addFunctionError(int error, String msg) {
        ToastUtils.ToastText(getContext(),"Error = "+error+", Msg = "+msg);
    }

    @Override
    public boolean addFunctionData(int common, String title, String summary, String explain) {
        HomeKnowFunction homeKnowFunction = new HomeKnowFunction();
        homeKnowFunction.setCommon(common);
        homeKnowFunction.setTitle(title);
        homeKnowFunction.setSummary(summary);
        homeKnowFunction.setExplain(explain);
        return homeKnowFunctions.add(homeKnowFunction);
    }

    private void initEmpty() {
        insertTitleEdit.setText("");
        insertSummaryEdit.setText("");
        insertHeedEdit.setText("");
        insertExperienceEdit.setText("");
        homeKnowFunctions.clear();
        mAdapter.notifyDataSetChanged();
        recyclerView.setVisibility(View.GONE);
        showHintSecond.setVisibility(View.VISIBLE);
        insertShowCount.setVisibility(View.GONE);
    }

    @OnClick({R.id.common_back,R.id.book_data,R.id.book_add,R.id.book_save})
    public void onViewClicked(View itemView){
        switch (itemView.getId()){
            case R.id.common_back:
                finish();
                break;
            case R.id.book_data:
                mDialog.show();
                break;
            case R.id.book_add:
                mAdapter.addFragment(mBookIndex);
                mBookIndex ++;
                mAdapter.notifyDataSetChanged();
                bookViewPager.setCurrentItem(mBookIndex-1);
                break;
            case R.id.book_save:
                mPresenter.isAddEmpty();
                for (Fragment fragment : mAdapter.getFragment()) {
                    BookBean.BookItemBean bookItem = ((BookContentFragment) fragment).getBookItem();
                    if(bookItem != null && !bookItem.getContent().isEmpty()){
                        mBook.addItem(bookItem);
                    }
                }
                mPresenter.add(mBook);
                break;
        }
    }

    @Override
    public void onSuccess() {
        ToastUtils.ToastText(getContext(),R.string.book_success);
    }

    @Override
    public void onSuccess(Object object) {

    }

    @Override
    public void onNoData() {

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
    public void onFinish() {

    }
}
