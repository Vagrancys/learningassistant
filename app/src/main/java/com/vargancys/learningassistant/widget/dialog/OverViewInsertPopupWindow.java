package com.vargancys.learningassistant.widget.dialog;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.utils.ToastUtils;

/**
 * @author Vagrancy
 * @date 2020/7/4
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识体系添加的弹窗
 */
public class OverViewInsertPopupWindow extends PopupWindow implements View.OnClickListener {
    private View parentView;
    private EditText insertTitleEdit;
    private EditText insertSummaryEdit;
    private EditText insertScoreEdit;
    private TextView insertCancel;
    private TextView insertDetermine;
    private ImageView insertExit;
    private Context mContext;
    private String scoreEdit;
    private String titleEdit;
    private Long insertId;
    private Spinner mSpinner;
    private int spinnerId = 0;
    public OverViewInsertPopupWindow(Context context){
        mContext =context;
        setContentView(getView());
        setAnimationStyle(R.style.dialog_animation_style);
        initView();
        initListener();
    }

    public View getParentView() {
        return parentView;
    }

    public void setInsertId(Long insertId) {
        this.insertId = insertId;
    }

    public Long getInsertId() {
        return insertId;
    }

    private View getView(){
        parentView = View.inflate(getContext(),R.layout.dialog_overview_insert,null);
        return parentView;
    }

    public Context getContext() {
        return mContext;
    }

    private void initListener() {
        insertCancel.setOnClickListener(this);
        insertDetermine.setOnClickListener(this);
        insertExit.setOnClickListener(this);
        ArrayAdapter mAdapter = ArrayAdapter.createFromResource(getContext(),R.array.common_level,R.layout.support_simple_spinner_dropdown_item);
        mSpinner.setAdapter(mAdapter);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerId = position+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initView() {
        insertTitleEdit = parentView.findViewById(R.id.insert_title_edit);
        insertExit = parentView.findViewById(R.id.insert_exit);
        insertSummaryEdit = parentView.findViewById(R.id.insert_summary_edit);
        insertScoreEdit = parentView.findViewById(R.id.insert_score_edit);
        insertCancel = parentView.findViewById(R.id.insert_cancel);
        insertDetermine = parentView.findViewById(R.id.insert_determine);
    }

    public OnInsertClickListener onInsertClickListener;

    public void setOnInsertClickListener(OnInsertClickListener onInsertClickListener) {
        this.onInsertClickListener = onInsertClickListener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.insert_exit:
                if(onInsertClickListener != null){
                    onInsertClickListener.exit();
                }
                break;
            case R.id.insert_cancel:
                if(onInsertClickListener != null){
                    onInsertClickListener.cancel();
                }
                break;
            case R.id.insert_determine:
                String title = insertTitleEdit.getText().toString();
                if(title.isEmpty()){
                    ToastUtils.ToastText(getContext(),R.string.overview_insert_empty_text);
                    return ;
                }
                String score = insertScoreEdit.getText().toString();
                if(score.isEmpty()){
                    ToastUtils.ToastText(getContext(),R.string.overview_insert_empty_text);
                    return;
                }
                int level = spinnerId;
                if(level == 0){
                    ToastUtils.ToastText(getContext(),R.string.overview_insert_level_text);
                    return;
                }
                String summary = insertSummaryEdit.getText().toString();
                if(summary.isEmpty()){
                    ToastUtils.ToastText(getContext(),R.string.overview_insert_empty_text);
                }
                if(onInsertClickListener != null){
                    onInsertClickListener.determine(getInsertId(),title,summary,score,level);
                }
                initData();
                break;
        }
    }

    private void initData() {
        insertTitleEdit.setText(null);
        insertScoreEdit.setText(null);
        insertSummaryEdit.setText(null);
        spinnerId = 0;
    }

    public interface OnInsertClickListener{
        void exit();
        void determine(long id, String title,String summary,String score,int level);
        void cancel();
    }
}
