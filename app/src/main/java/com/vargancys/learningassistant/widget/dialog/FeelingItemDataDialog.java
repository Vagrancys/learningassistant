package com.vargancys.learningassistant.widget.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.vargancys.learningassistant.R;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/23
 * version:1.0
 * 感悟Item数据弹窗
 */
public class FeelingItemDataDialog extends AlertDialog {
    private EditText mTitle;
    private EditText mSummary;
    private TextView mCancel;
    private TextView mDeterMine;
    private Spinner mSpinner;
    private int mCommon = 1;
    private int tree_id;
    private boolean isEdit = false;
    public FeelingItemDataDialog(Context context) {
        this(context,0);
    }

    public FeelingItemDataDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.pop_knowledge_class_header);
        initView();
        initListener();
    }

    public int getTree_id() {
        return tree_id;
    }

    public void setTree_id(int tree_id) {
        this.tree_id = tree_id;
    }

    private void initListener(){
        mCancel.setOnClickListener(v -> {
            if(onClickCancelListener !=null){
                onClickCancelListener.OnCancel();
            }
        });

        mDeterMine.setOnClickListener(v -> {
            if(onClickDeterMineListener != null){
                onClickDeterMineListener.OnDeterMine(tree_id,mCommon,mTitle.getText().toString(),
                        mSummary.getText().toString());
            }
        });

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mCommon = position+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void setTitle(String title){
        if(mTitle != null){
            mTitle.setText(title);
        }
    }

    public void setSummary(String summary){
        if(mSummary != null){
            mSummary.setText(summary);
        }
    }

    public void setLevel(int common){
        if(mSpinner != null){
            mCommon = common;
            mSpinner.notify();
        }
    }

    public boolean isEdit(){
        return isEdit;
    }

    private void initView() {
        mTitle = findViewById(R.id.knowledge_title_edit);
        mSummary = findViewById(R.id.knowledge_summary_edit);
        mCancel = findViewById(R.id.knowledge_cancel);
        mDeterMine = findViewById(R.id.knowledge_determine);
        mSpinner =findViewById(R.id.knowledge_level);
        ArrayAdapter arrayAdapter =ArrayAdapter.createFromResource(getContext(),R.array.common_level,
                R.layout.support_simple_spinner_dropdown_item);
        mSpinner.setAdapter(arrayAdapter);
    }

    public void clearData() {
        mTitle.setText("");
        mSummary.setText("");
        mCommon = 1;
    }

    public interface OnClickCancelListener{
        void OnCancel();
    }

    public interface OnClickDeterMineListener{
        void OnDeterMine(int position, int common, String title, String summary);
    }

    private OnClickCancelListener onClickCancelListener;
    private OnClickDeterMineListener onClickDeterMineListener;

    public void setOnClickCancelListener(OnClickCancelListener onClickCancelListener) {
        this.onClickCancelListener = onClickCancelListener;
    }

    public void setOnClickDeterMineListener(OnClickDeterMineListener onClickDeterMineListener) {
        this.onClickDeterMineListener = onClickDeterMineListener;
    }
}
