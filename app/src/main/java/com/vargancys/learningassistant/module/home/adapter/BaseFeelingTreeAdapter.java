package com.vargancys.learningassistant.module.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.model.home.bean.FeelingBean;

import java.util.ArrayList;
import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/10/11
 * version:1.0
 * 模块名: 基础感悟树适配器
 */
public abstract class BaseFeelingTreeAdapter extends BaseRecyclerAdapter {
    private List<FeelingBean.FeelingItemBean> lists = new ArrayList<>();
    private int itemId;
    private int addId;
    public BaseFeelingTreeAdapter(Context context, int addId, int itemId){
        super(context);
        this.itemId = itemId;
        this.addId = addId;
    }
    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        CommonViewHolder holder;
        if (lists.get(i).getType() == 1) {
            holder = getItemHolder(getView(itemId));
        } else {
            holder = getAddHolder(getView(addId));
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        FeelingBean.FeelingItemBean mBean = lists.get(position);
        if (mBean.getType() == 1) {
            onBindItemHolder(holder, position, mBean);
        } else {
            onBindAddHolder(holder, position);
        }
    }

    public void setTree(List<FeelingBean.FeelingItemBean> lists) {
        this.lists = lists;
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    abstract CommonViewHolder getItemHolder(View view);

    abstract CommonViewHolder getAddHolder(View view);

    abstract void onBindItemHolder(CommonViewHolder holder, int position, FeelingBean.FeelingItemBean item);

    abstract void onBindAddHolder(CommonViewHolder holder,int position);
}
