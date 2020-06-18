package com.vargancys.learningassistant.widget.section;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author Vagrancy
 * @date 2020/5/23
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 基础类型适配器
 */
public abstract class Section {

    public enum State{
        LOADING,LOADED,FAILED
    }

    public State state = State.LOADED;

    boolean visible = true;
    boolean hasHeader = false;
    boolean hasFooter = false;
    Integer headerResourceId;
    Integer footerResourceId;
    int itemResourceId;
    private Integer loadingResourceId;
    private Integer failedResourceId;

    Section(){

    };

    public Section(int itemResourceId,int loadingResourceId,int failedResourceId){
        this.itemResourceId = itemResourceId;
        this.loadingResourceId = loadingResourceId;
        this.failedResourceId = failedResourceId;
    }

    public Section(int headerResourceId,int itemResourceId,int loadingResourceId,int failedResourceId){
        this(itemResourceId,loadingResourceId,failedResourceId);
        this.headerResourceId = headerResourceId;
        hasHeader = true;
    };

    public Section(int headerResourceId,int footerResourceId,int itemResourceId,int loadingResourceId,int failedResourceId){
        this(headerResourceId, itemResourceId, loadingResourceId, failedResourceId);
        this.footerResourceId = footerResourceId;
        this.hasFooter = true;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setHasHeader(boolean hasHeader) {
        this.hasHeader = hasHeader;
    }

    public boolean isHasHeader() {
        return hasHeader;
    }

    public void setHasFooter(boolean hasFooter) {
        this.hasFooter = hasFooter;
    }

    public boolean isHasFooter() {
        return hasFooter;
    }

    public int getItemResourceId() {
        return itemResourceId;
    }

    public Integer getFailedResourceId() {
        return failedResourceId;
    }

    public Integer getFooterResourceId() {
        return footerResourceId;
    }

    public Integer getHeaderResourceId() {
        return headerResourceId;
    }

    public Integer getLoadingResourceId() {
        return loadingResourceId;
    }

    public final void onBindContentViewHolder(RecyclerView.ViewHolder viewHolder,int position){
        switch (state){
            case FAILED:
                onBindFailedViewHolder(viewHolder);
                break;
            case LOADED:
                onBindItemViewHolder(viewHolder,position);
            case LOADING:
                onBindLoadingViewHolder(viewHolder);
                break;
            default:
                throw new IllegalStateException("Invalid state");
        }
    }

    public final int getSectionItemTotal(){
        int contentItemTotal;
        switch (state){
            case LOADING:
            case FAILED:
                contentItemTotal=1;
                break;
            case LOADED:
                contentItemTotal = getSectionItemTotal();
                break;
            default:
                throw new IllegalStateException("Invalid state");
        }
        return contentItemTotal+(hasHeader?1:0)+(hasFooter?1:0);
    }

    public abstract int getContentItemsTotal();

    public RecyclerView.ViewHolder getHeaderViewHolder(View view){
        return new SectionedRecyclerViewAdapter.EmptyViewHolder(view);
    }

    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder){

    }

    public RecyclerView.ViewHolder getFooterViewHolder(View view){
        return new SectionedRecyclerViewAdapter.EmptyViewHolder(view);
    }

    public void onBindFooterViewHolder(RecyclerView.ViewHolder holder){

    }

    public abstract RecyclerView.ViewHolder getItemViewHolder(View view);

    public void onBindItemViewHolder(RecyclerView.ViewHolder holder,int position){};

    public RecyclerView.ViewHolder getLoadingViewHolder(View view){
        return new SectionedRecyclerViewAdapter.EmptyViewHolder(view);
    }

    public void onBindLoadingViewHolder(RecyclerView.ViewHolder holder){

    }

    public RecyclerView.ViewHolder getFailedViewHolder(View view){
        return new SectionedRecyclerViewAdapter.EmptyViewHolder(view);
    }

    public void onBindFailedViewHolder(RecyclerView.ViewHolder holder){

    }
}
