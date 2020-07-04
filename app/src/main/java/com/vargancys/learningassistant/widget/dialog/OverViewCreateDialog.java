package com.vargancys.learningassistant.widget.dialog;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.vargancys.learningassistant.R;

/**
 * @author Vagrancy
 * @date 2020/7/2
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识创建选择类型弹窗
 */
public class OverViewCreateDialog extends PopupWindow implements View.OnClickListener {
    private Context mContext;
    private ImageView dialogClose;
    private TextView dialogTitle;
    private TextView dialogInsert;
    private TextView dialogUpdate;
    private TextView dialogDelete;
    private String mTitle;
    private int mPosition;
    public OverViewCreateDialog(Context context){
        mContext = context;
        setAnimationStyle(R.style.dialog_animation_style);
        setContentView(getView());
        initView();
        initListener();
    }
    private void initView(){
        dialogClose = getView().findViewById(R.id.dialog_close);
        dialogDelete = getView().findViewById(R.id.dialog_delete);
        dialogInsert = getView().findViewById(R.id.dialog_insert);
        dialogTitle = getView().findViewById(R.id.dialog_title);
        dialogUpdate = getView().findViewById(R.id.dialog_update);
        dialogTitle.setText(mTitle);
    }

    public void setTitle(String title){
        if(title != null && !title.isEmpty()){
            mTitle = title;
            dialogTitle.setText(title);
        }
    }

    public void setPosition(int position){
        mPosition = position;
    }

    public Context getContext() {
        return mContext;
    }

    private void initListener(){
        dialogClose.setOnClickListener(this);
        dialogUpdate.setOnClickListener(this);
        dialogInsert.setOnClickListener(this);
        dialogDelete.setOnClickListener(this);
    }

    private View getView(){
        return View.inflate(getContext(),R.layout.dialog_overview_create,null);
    }

    private OnDialogClickListener onDialogClickListener;

    public void setOnDialogClickListener(OnDialogClickListener onDialogClickListener) {
        this.onDialogClickListener = onDialogClickListener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dialog_close:
                if(onDialogClickListener != null){
                    onDialogClickListener.onClose();
                }
                break;
            case R.id.dialog_delete:
                if(onDialogClickListener != null){
                    onDialogClickListener.onDelete(mPosition);
                }
                break;
            case R.id.dialog_update:
                if(onDialogClickListener != null){
                    onDialogClickListener.onUpdate(mPosition);
                }
                break;
            case R.id.dialog_insert:
                if(onDialogClickListener != null){
                    onDialogClickListener.onInsert(mPosition);
                }
                break;
        }
    }

    public interface OnDialogClickListener{
        void onClose();
        void onDelete(int position);
        void onUpdate(int position);
        void onInsert(int position);
    }
}
