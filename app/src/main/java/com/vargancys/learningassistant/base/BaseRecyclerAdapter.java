package com.vargancys.learningassistant.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import butterknife.ButterKnife;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/02
 * version:1.0
 */
public abstract class BaseRecyclerAdapter extends RecyclerView.Adapter<BaseRecyclerAdapter.CommonViewHolder> {

    @Override
    public void onBindViewHolder(final CommonViewHolder holder, final int position) {
        Log.e("1adapter","1adapter");
        holder.getParentView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("adapter","adapter");
                if(onItemClickListener != null){
                    onItemClickListener.OnItemClick(position);
                }
            }
        });
        holder.getParentView().setOnLongClickListener(new View.OnLongClickListener() {
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
        private View parentView;
        public CommonViewHolder(View itemView) {
            super(itemView);
            this.parentView = itemView;
            ButterKnife.bind(this,itemView);
        }

        View getParentView() {
            return parentView;
        }
    }

    public View getView(Context context,int Ids){
        return View.inflate(context,Ids,null);
    }
}
