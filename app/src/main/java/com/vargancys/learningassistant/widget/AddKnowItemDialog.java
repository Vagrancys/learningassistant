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
public class AddKnowItemDialog extends AlertDialog {
    private View parentView;
    private EditText mTitleEdit;
    private EditText mLevelEdit;
    private EditText mScoreEdit;
    private TextView mCancel;
    private TextView mDeterMine;
    public AddKnowItemDialog(Context context) {
        this(context,0);
    }

    public AddKnowItemDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        parentView = View.inflate(getContext(),R.layout.dialog_add_know_item,null);
        setContentView(parentView);
        initView();
        initListener();
    }

    private void initView() {
        mTitleEdit = parentView.findViewById(R.id.item_title_edit);
        mLevelEdit = parentView.findViewById(R.id.item_level_edit);
        mScoreEdit = parentView.findViewById(R.id.item_score_edit);
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
                    onClickDeterMineListener.OnDeterMine(mTitleEdit.getText().toString(),
                            mLevelEdit.getText().toString(),mScoreEdit.getText().toString());
                }
            }
        });
    }

    public void clearData() {
        mTitleEdit.setText("");
        mScoreEdit.setText("");
        mLevelEdit.setText("");
    }

    public interface OnClickCancelListener{
        void OnCancel();
    }

    public interface OnClickDeterMineListener{
        void OnDeterMine(String title,String level,String score);
    }

    private OnClickCancelListener onClickCancelListener;
    private OnClickDeterMineListener onClickDeterMineListener;

    public void setOnClickCancelListener(OnClickCancelListener onClickCancelListener) {
        this.onClickCancelListener = onClickCancelListener;
    }

    public void setOnClickDeterMineListener(OnClickDeterMineListener onClickDeterMineListener) {
        this.onClickDeterMineListener = onClickDeterMineListener;
    }

    public void setTitle(String title){
        mTitleEdit.setText(title);
    }

    public void setLevel(int level){
        mLevelEdit.setText(String.valueOf(level));
    }

    public void setScore(int score){
        mScoreEdit.setText(String.valueOf(score));
    }
}
