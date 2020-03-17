package com.vargancys.learningassistant.module.home.activity.insert;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.db.home.HomeKnowFunction;
import com.vargancys.learningassistant.module.home.adapter.HomeKnowSecondAdapter;
import com.vargancys.learningassistant.module.home.view.KnowInsertSecondView;
import com.vargancys.learningassistant.persenter.home.KnowInsertPresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/09
 * version:1.0
 */
public class KnowInsertSecondActivity extends BaseActivity  implements KnowInsertSecondView {
    private String TAG = "KnowInsertSecondActivity";
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
    @BindView(R.id.show_hint_second)
    TextView showHintSecond;
    private KnowInsertPresenter mPresenter;
    private int know_item_id;
    private List<HomeKnowFunction> homeKnowFunctions = new ArrayList<>();
    private HomeKnowSecondAdapter mAdapter;
    private int mCommon = 1;
    private PopupWindow mPopupWindow;

    @Override
    public int getLayoutId() {
        return R.layout.activity_know_insert_second;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        if(intent != null){
            know_item_id = intent.getIntExtra(ConstantsUtils.KNOW_ITEM_ID,1);
        }
        mPresenter = new KnowInsertPresenter(this);
        initRecyclerView();
        initListener();
        initPopupWindow();
    }

    private void initRecyclerView() {
        mAdapter = new HomeKnowSecondAdapter(getContext(),homeKnowFunctions);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initToolbar() {
        commonTitle.setText(getResources().getString(R.string.common_second));
    }

    private void initListener() {
        commonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        commonImg.setBackgroundResource(R.drawable.commend_complete_selector);

        commonImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.isSecondEmpty();
            }
        });

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

    private void initPopupWindow(){
        mPopupWindow = new PopupWindow(this);
        mPopupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setAnimationStyle(R.style.FunctionAnimAction);
        final View popView = View.inflate(getContext(),R.layout.pop_function_second,null);
        Spinner mSpinner =popView.findViewById(R.id.function_common);
        ArrayAdapter arrayAdapter =ArrayAdapter.createFromResource(getContext(),R.array.common_level,
                R.layout.support_simple_spinner_dropdown_item);
        mSpinner.setAdapter(arrayAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mCommon = position+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        final EditText mTitle = popView.findViewById(R.id.function_title_edit);
        final EditText mSummary = popView.findViewById(R.id.function_summary_edit);
        final EditText mExplain = popView.findViewById(R.id.function_explain_edit);
        TextView mCancel = popView.findViewById(R.id.function_cancel);
        TextView mDeterMine = popView.findViewById(R.id.function_determine);
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });
        mDeterMine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mPresenter.isFunctionEmpty(mCommon,mTitle.getText().toString(),
                        mSummary.getText().toString(),mExplain.getText().toString())){
                    ToastUtils.ToastText(getContext(),"请输入完整!");
                }else{
                    mPresenter.addFunctionData(mCommon,mTitle.getText().toString(),
                            mSummary.getText().toString(),mExplain.getText().toString());
                }
            }
        });
        mPopupWindow.setContentView(popView);
    }

    @Override
    public void isSecondEqualsError(int error, String msg) {
        ToastUtils.ToastText(getContext(),"Error = "+error+",Msg = "+msg+"该知识已经添加了!请重新输入!");
    }

    public static void launch(Activity activity, int know_id){
        Intent intent = new Intent(activity,KnowInsertSecondActivity.class);
        intent.putExtra(ConstantsUtils.KNOW_ITEM_ID,know_id);
        activity.startActivity(intent);
    }

    @Override
    public void saveSecondItemError(int error, String msg) {
        ToastUtils.ToastText(getContext(),"Error = "+error+", Msg = "+msg);
    }

    @Override
    public void saveSecondItemFinish() {
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
            showHintSecond.setVisibility(View.GONE);
            insertShowCount.setVisibility(View.VISIBLE);
            insertShowCount.setText(homeKnowFunctions.size());
        }
        mPopupWindow.dismiss();
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

    @Override
    public void showFunctionWindow() {
        mPopupWindow.showAsDropDown(insertShowAdd);
        Log.e(TAG,"popupWindow");
    }
}
