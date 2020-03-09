package com.vargancys.learningassistant.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/02
 * version:1.0
 */
public abstract class BaseRecyclerAdapter extends RecyclerView.Adapter<BaseRecyclerAdapter.CommonViewHolder> {
    private View parentView;

    @Override
    public void onBindViewHolder(@NonNull CommonViewHolder holder, final int position) {
        parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener != null){
                    onItemClickListener.OnItemClick(position);
                }
            }
        });
        parentView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(onItemLongClickListener !=null){
                    onItemLongClickListener.OnItemLongClick(position);
                }
                return false;
            }
        });
    }

    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public interface OnItemClickListener{
        void OnItemClick(int position);
    }

    public interface OnItemLongClickListener{
        void OnItemLongClick(int position);
    }

    public class CommonViewHolder extends RecyclerView.ViewHolder {
        public CommonViewHolder(View itemView) {
            super(itemView);
            parentView = itemView;
        }
    }
}
