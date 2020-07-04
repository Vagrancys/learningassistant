package com.vargancys.learningassistant.widget.dialog;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.utils.ToastUtils;

/**
 * @author Vagrancy
 * @date 2020/7/4
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识体系更新的弹窗
 */
public class OverViewUpdatePopupWindow extends PopupWindow implements View.OnClickListener {
    private View parentView;
    private TextView updateTitle;
    private ImageView updateClose;
    private TextView updateMessage;
    private EditText updateEdit;
    private TextView updateCancel;
    private TextView updateDetermine;
    private ImageView updateExit;
    private Context mContext;
    private String title;
    private String message;
    private String edit;
    private Long updateId;
    public OverViewUpdatePopupWindow(Context context){
        mContext =context;
        setContentView(getView());
        setAnimationStyle(R.style.dialog_animation_style);
        initView();
        initListener();
        initData();
    }

    public View getParentView() {
        return parentView;
    }

    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }

    public Long getUpdateId() {
        return updateId;
    }

    private void initData(){
        updateTitle.setText(title);
        updateMessage.setText(message);
        updateEdit.setText(edit);
    }

    private View getView(){
        parentView = View.inflate(getContext(),R.layout.dialog_overview_update,null);
        return parentView;
    }

    public Context getContext() {
        return mContext;
    }

    public void setEdit(String edit) {
        if(!edit.isEmpty()){
            this.edit = edit;
        }
    }

    public void setTitle(String title) {
        if(!title.isEmpty()){
            this.title = title;
        }
    }

    public void setMessage(String message) {
        if(!message.isEmpty()){
            this.message = message;
        }
    }



    private void initListener() {
        updateClose.setOnClickListener(this);
        updateCancel.setOnClickListener(this);
        updateDetermine.setOnClickListener(this);
        updateExit.setOnClickListener(this);
        updateEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(updateEdit.getText().length() > 0){
                    updateClose.setVisibility(View.VISIBLE);
                }else{
                    updateClose.setVisibility(View.GONE);
                    if(onUpdateClickListener != null){
                        onUpdateClickListener.TextEmpty();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initView() {
        updateTitle = parentView.findViewById(R.id.update_title);
        updateClose = parentView.findViewById(R.id.update_close);
        updateMessage = parentView.findViewById(R.id.update_message);
        updateEdit = parentView.findViewById(R.id.update_edit);
        updateCancel = parentView.findViewById(R.id.update_cancel);
        updateDetermine = parentView.findViewById(R.id.update_determine);
    }

    public OnUpdateClickListener onUpdateClickListener;

    public void setOnUpdateClickListener(OnUpdateClickListener onUpdateClickListener) {
        this.onUpdateClickListener = onUpdateClickListener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.update_exit:
                if(onUpdateClickListener != null){
                    onUpdateClickListener.exit();
                }
                break;
            case R.id.update_cancel:
                if(onUpdateClickListener != null){
                    onUpdateClickListener.cancel();
                }
                break;
            case R.id.update_determine:
                String update = updateEdit.getText().toString();
                if(update.isEmpty()){
                    ToastUtils.ToastText(getContext(),R.string.overview_update_empty_text);
                    return ;
                }
                if(edit.equals(update)){
                    ToastUtils.ToastText(getContext(),R.string.overview_update_equals_text);
                    return;
                }
                if(onUpdateClickListener != null){
                    onUpdateClickListener.determine(getUpdateId(),edit);
                }
                break;
            case R.id.update_close:
                updateClose.setVisibility(View.GONE);
                updateEdit.setText("");
                if(onUpdateClickListener != null){
                    onUpdateClickListener.TextEmpty();
                }
                break;
        }
    }

    public interface OnUpdateClickListener{
        void exit();
        void TextEmpty();
        void determine(long id,String message);
        void cancel();
    }
}
