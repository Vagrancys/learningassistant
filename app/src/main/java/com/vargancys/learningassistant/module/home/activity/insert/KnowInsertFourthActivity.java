package com.vargancys.learningassistant.module.home.activity.insert;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.db.home.HomeKnowFunction;
import com.vargancys.learningassistant.module.home.adapter.HomeKnowFourthAdapter;
import com.vargancys.learningassistant.module.home.view.KnowInsertFourthView;
import com.vargancys.learningassistant.presenter.home.KnowInsertPresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ToastUtils;
import com.vargancys.learningassistant.widget.FunctionDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/09
 * version:1.0
 */
public class KnowInsertFourthActivity extends BaseActivity implements KnowInsertFourthView {
    @BindView(R.id.common_back)
    ImageView commonBack;
    @BindView(R.id.common_img)
    ImageView commonImg;
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.insert_title_edit)
    EditText insertTitleEdit;
    @BindView(R.id.insert_summary_edit)
    EditText insertSummaryEdit;
    @BindView(R.id.insert_show_add)
    ImageView insertShowAdd;
    @BindView(R.id.insert_show_count)
    TextView insertShowCount;
    @BindView(R.id.insert_heed_edit)
    EditText insertHeedEdit;
    @BindView(R.id.insert_experience_edit)
    EditText insertExperienceEdit;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.show_hint_fourth)
    TextView showHintFourth;
    private KnowInsertPresenter mPresenter;
    private int know_item_id;
    private List<HomeKnowFunction> homeKnowFunctions = new ArrayList<>();
    private HomeKnowFourthAdapter mAdapter;
    private int mCommon = 1;
    private FunctionDialog mDialog;

    @Override
    public int getLayoutId() {
        return R.layout.activity_know_insert_fourth;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        if(intent != null){
            know_item_id = intent.getIntExtra(ConstantsUtils.KNOW_ITEM_ID,0);
        }
        mPresenter = new KnowInsertPresenter(this);
        initRecyclerView();
        initListener();
        initDialog();
    }

    private void initRecyclerView() {
        mAdapter = new HomeKnowFourthAdapter(getContext(),homeKnowFunctions);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initToolbar() {
        commonTitle.setText(getResources().getString(R.string.common_fourth));

        commonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        commonImg.setImageResource(R.drawable.comment_complete_selector);

        commonImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.isFourthEmpty();
            }
        });
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
                mPresenter.showFourthFunctionWindow();
            }
        });
    }

    @Override
    public boolean isFourthEmpty() {
        return insertTitleEdit.getText().toString().isEmpty()
                &&insertSummaryEdit.getText().toString().isEmpty()
                &&insertHeedEdit.getText().toString().isEmpty()
                &&insertExperienceEdit.getText().toString().isEmpty()
                &&homeKnowFunctions.size() > 0;
    }



    @Override
    public void isFourthEmptyError(int error, String msg) {
        ToastUtils.ToastText(getContext(),"Error = "+error+",Msg = "+msg+"请填满每个知识项!");
    }

    @Override
    public void isFourthEqualsItem() {
        mPresenter.isEqualsFourthItem(insertTitleEdit.getText().toString());
    }

    @Override
    public void saveFourthKnowItem() {
        mPresenter.saveKnowFourthItem(know_item_id,insertTitleEdit.getText().toString(),
                insertSummaryEdit.getText().toString(),
                homeKnowFunctions,
                insertHeedEdit.getText().toString(),
                insertExperienceEdit.getText().toString());
    }

    private void initDialog(){
        mDialog = new FunctionDialog(this);
        mDialog = new FunctionDialog(this);
        final View popView = View.inflate(getContext(),R.layout.pop_function_fourth,null);
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
                if(mPresenter.isFunctionFourthEmpty(mCommon,title,
                        summary,explain)){
                    ToastUtils.ToastText(getContext(),"请输入完整!");
                }else{
                    mPresenter.addFunctionFourthData(mCommon,title,
                            summary,explain);
                }
            }
        });
    }



    @Override
    public void isFourthEqualsError(int error, String msg) {
        ToastUtils.ToastText(getContext(),"Error = "+error+",Msg = "+msg+"该知识已经添加了!请重新输入!");
    }

    public static void launch(Activity activity, int know_id){
        Intent intent = new Intent(activity,KnowInsertFourthActivity.class);
        intent.putExtra(ConstantsUtils.KNOW_ITEM_ID,know_id);
        activity.startActivity(intent);
    }

    @Override
    public void saveFourthItemError(int error, String msg) {
        ToastUtils.ToastText(getContext(),"Error = "+error+", Msg = "+msg);
    }

    @Override
    public void saveFourthItemFinish() {
        ToastUtils.ToastText(getContext(),"保存成功了哦!");
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
            showHintFourth.setVisibility(View.GONE);
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
        showHintFourth.setVisibility(View.VISIBLE);
        insertShowCount.setVisibility(View.GONE);
    }

    @Override
    public void showFunctionWindow() {
        mDialog.show();
    }
}

