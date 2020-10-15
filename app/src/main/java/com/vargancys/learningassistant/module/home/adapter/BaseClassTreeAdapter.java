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
    private List<ClassTreeListBean> lists = new ArrayList<>();
    private int headerId;
    private int itemId;
    private int addId;
    public BaseClassTreeAdapter(Context context,int addId,int headerId, int itemId){
        super(context);
        this.headerId = headerId;
        this.itemId = itemId;
        this.addId = addId;
    }
    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        CommonViewHolder holder;
        switch (lists.get(i).getType()){
            case 1:
                holder = getHeaderHolder(getView(headerId));
                break;
            case 2:
                holder = getItemHolder(getView(itemId));
                break;
            default:
                holder = getAddHolder(getView(addId));
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        ClassTreeListBean mBean = lists.get(position);
        switch (mBean.getType()){
            case 1:
                onBindHeaderHolder(holder,position,mBean.getHeader());
                break;
            case 2:
                onBindItemHolder(holder,position,mBean.getItem());
                break;
            case 3:
                onBindAddHolder(holder,position);
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

    abstract CommonViewHolder getAddHolder(View view);

    abstract void onBindHeaderHolder(CommonViewHolder holder, int position, ClassTreeListBean.ClassTreeHeader header);

    abstract void onBindItemHolder(CommonViewHolder holder, int position, ClassTreeListBean.ClassTreeItem item);

    abstract void onBindAddHolder(CommonViewHolder holder,int position);
}
