package com.vargancys.learningassistant.module.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.model.home.bean.ClassTreeListBean;
import com.vargancys.learningassistant.presenter.BaseCallBackListener;

import java.util.ArrayList;
import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/10/11
 * version:1.0
 * 模块名:
 */
public abstract class BaseClassTreeAdapter extends BaseRecyclerAdapter {
    public List<ClassTreeListBean> lists = new ArrayList<>();
    public int headerId;
    public int itemId;
    public BaseClassTreeAdapter(Context context,int headerId, int itemId){
        super(context);
        this.headerId = headerId;
        this.itemId = itemId;
    }
    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        CommonViewHolder holder = null;
        switch (lists.get(i).getType()){
            case 1:
                holder = getHeaderHolder(getView(headerId));
                break;
            case 2:
                holder = getItemHolder(getView(itemId));
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        switch (lists.get(position).getType()){
            case 1:
                onBindHeaderHolder(holder,position);
                break;
            case 2:
                onBindItemHolder(holder,position);
                break;
        }
    }

    public void setTree(List<ClassTreeListBean> lists) {
        this.lists = lists;
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    abstract CommonViewHolder getHeaderHolder(View view);

    abstract CommonViewHolder getItemHolder(View view);

    abstract void onBindHeaderHolder(CommonViewHolder holder,int position);

    abstract void onBindItemHolder(CommonViewHolder holder,int position);
}
