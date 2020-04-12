package com.vargancys.learningassistant.widget.TreeDirectory;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.vargancys.learningassistant.db.common.KnowListBean;
import com.vargancys.learningassistant.utils.DensityUtils;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/02
 * version:1.0
 */
public abstract class TreeListViewAdapter<T> extends BaseAdapter {
    private static String TAG = "TreeListViewAdapter";
    protected Context mContext;
    //存储所有可见的Node
    protected List<Node> mNodes;
    protected LayoutInflater mInflater;

    protected List<KnowListBean> mDatas;
    //存储所有的Node;
    protected List<Node> mAllNodes;
    private boolean mExpandOrCollapse = true;

    //点击的回调接口
    public OnTreeNodeClickListener onTreeNodeClickListener;

    public interface OnTreeNodeClickListener{
        void onShow(int position);
        void onClick(Node node,int position);
        void onDelete(Node node,int position);
        void onUpdate(Node node,int position);
    }

    public void setOnTreeNodeClickListener(OnTreeNodeClickListener onTreeNodeClickListener) {
        this.onTreeNodeClickListener = onTreeNodeClickListener;
    }

    public TreeListViewAdapter(ListView mTree, Context context, List<KnowListBean> datas, int defaultExpandLevel, boolean expand)
            throws IllegalArgumentException,IllegalAccessException{
        mContext = context;
        mDatas = datas;
        mExpandOrCollapse = expand;
        mAllNodes = TreeHelper.getSortedNodes(mDatas,defaultExpandLevel);
        mNodes = TreeHelper.filterVisibleNode(mAllNodes);
        mInflater = LayoutInflater.from(context);
        mTree.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(mExpandOrCollapse){
                    expandOrCollapse(position);
                }
                if(onTreeNodeClickListener !=null){
                    onTreeNodeClickListener.onClick(mNodes.get(position),position);
                }
            }
        });
    }

    public void expandOrCollapse(int position){
        Node node = mNodes.get(position);
        if(node != null){
            if(!node.isLeaf()){
                node.setExpand(!node.isExpand());
                mNodes = TreeHelper.filterVisibleNode(mAllNodes);
                notifyDataSetChanged();
            }
        }
    }

    public void addNode(KnowListBean data)throws IllegalArgumentException,IllegalAccessException{
        mDatas.add(data);
        mAllNodes = TreeHelper.getSortedNodes(mDatas,10);
        mNodes = TreeHelper.filterVisibleNode(mAllNodes);
    }

    @Override
    public int getCount() {
        return mNodes.size();
    }

    @Override
    public Object getItem(int position) {
        return mNodes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Node node = mNodes.get(position);
        convertView = getConvertView(node,position,convertView,parent);
        convertView.setPadding(node.getLayer()* DensityUtils.dip2px(mContext,20),3,3,3);
        return convertView;
    }

    public abstract View getConvertView(Node node,int position,View convertView,ViewGroup parent);

    public Node getNodes(int position) {
        return mNodes.get(position);
    }

    public List<Node> getAllNodes(){
        return mNodes;
    }

    public void deleteNode(int position){
        mNodes.remove(position);
    }

    public void swipeData(List<KnowListBean> mItem)throws IllegalArgumentException,IllegalAccessException{
        mDatas =mItem;
        Log.e(TAG,"mItem ="+mItem.size()+"mData ="+mDatas.size());
        mAllNodes = TreeHelper.getSortedNodes(mDatas,1);
        mNodes = TreeHelper.filterVisibleNode(mAllNodes);
    }
}












