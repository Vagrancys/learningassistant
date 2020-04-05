package com.vargancys.learningassistant.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.vargancys.learningassistant.R;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/04
 * version:1.0
 */
public class AddKnowTitleDialog extends AlertDialog {
    private View parentView;
    private EditText mTitleEdit;
    private EditText mSummaryEdit;
    private TextView mCancel;
    private TextView mDeterMine;
    public AddKnowTitleDialog(Context context) {
        this(context,0);
    }

    public AddKnowTitleDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        parentView = View.inflate(getContext(),R.layout.dialog_add_know_title,null);
        setContentView(parentView);
        initView();
        initListener();
    }

    private void initView() {
        mTitleEdit = parentView.findViewById(R.id.dialog_title_edit);
        mSummaryEdit = parentView.findViewById(R.id.dialog_summary_edit);
        mCancel = parentView.findViewById(R.id.common_cancel);
        mDeterMine = parentView.findViewById(R.id.common_determine);
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
                    onClickDeterMineListener.OnDeterMine(mTitleEdit.getText().toString(),mSummaryEdit.getText().toString());
                }
            }
        });
    }

    public interface OnClickCancelListener{
        void OnCancel();
    }

    public interface OnClickDeterMineListener{
        void OnDeterMine(String title,String summary);
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
