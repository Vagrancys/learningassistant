package com.vargancys.learningassistant.widget.section;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author Vagrancy
 * @date 2020/5/23
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 封装基础类型适配器
 */
public abstract class StatelessSection extends Section{
    public StatelessSection(int itemResourceId){
        super();
        this.itemResourceId = itemResourceId;
    }

    public StatelessSection(int headerResourceId,int itemResourceId){
        this(itemResourceId);
        this.hasHeader = true;
        this.headerResourceId = headerResourceId;
    }

    public StatelessSection(int headerResourceId,int footerResourceId,int itemResourceId){
        this(headerResourceId, itemResourceId);
        this.hasFooter =true;
        this.footerResourceId = footerResourceId;
    }

    public StatelessSection() {

    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return null;
    }

    @Override
    public void onBindLoadingViewHolder(RecyclerView.ViewHolder holder) {
        super.onBindLoadingViewHolder(holder);
    }

    @Override
    public void onBindFailedViewHolder(RecyclerView.ViewHolder holder) {
        super.onBindFailedViewHolder(holder);
    }

    @Override
    public RecyclerView.ViewHolder getLoadingViewHolder(View view) {
        return super.getLoadingViewHolder(view);
    }

    @Override
    public RecyclerView.ViewHolder getFailedViewHolder(View view) {
        return super.getFailedViewHolder(view);
    }
}
