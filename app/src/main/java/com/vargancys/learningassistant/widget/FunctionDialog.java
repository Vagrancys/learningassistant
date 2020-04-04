package com.vargancys.learningassistant.widget;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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
 */
public class FunctionDialog extends AlertDialog {
    private View parentView;
    private EditText mTitle;
    private EditText mSummary;
    private EditText mExplain;
    private TextView mCancel;
    private TextView mDeterMine;
    private Spinner mSpinner;
    private int mCommon = 1;
    public FunctionDialog(Context context) {
        this(context,0);
    }

    public FunctionDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(parentView);
        initView();
        initListener();
    }

    private void initListener(){
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClickCancelListener !=null){
                    onClickCancelListener.OnCancel();
                }
            }
        });

        mDeterMine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClickDeterMineListener != null){
                    onClickDeterMineListener.OnDeterMine(mCommon,mTitle.getText().toString(),
                            mSummary.getText().toString(),mExplain.getText().toString());
                }
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

    private void initView() {
        mTitle = parentView.findViewById(R.id.function_title_edit);
        mSummary = parentView.findViewById(R.id.function_summary_edit);
        mExplain = parentView.findViewById(R.id.function_explain_edit);
        mCancel = parentView.findViewById(R.id.function_cancel);
        mDeterMine = parentView.findViewById(R.id.function_determine);
        mSpinner =parentView.findViewById(R.id.function_common);
        ArrayAdapter arrayAdapter =ArrayAdapter.createFromResource(getContext(),R.array.common_level,
                R.layout.support_simple_spinner_dropdown_item);
        mSpinner.setAdapter(arrayAdapter);
    }

    public View getContentView() {
        return parentView;
    }

    public void setParentView(View parentView) {
        this.parentView = parentView;
    }

    public void clearData() {
        mTitle.setText("");
        mSummary.setText("");
        mCommon = 1;
        mExplain.setText("");
    }

    public interface OnClickCancelListener{
        void OnCancel();
    }

    public interface OnClickDeterMineListener{
        void OnDeterMine(int common,String title,String summary,String explain);
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
