package com.vargancys.learningassistant.widget.TreeDirectory;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/02
 * version:1.0
 */
public abstract class TreeListViewAdapter<T> extends BaseAdapter {
    protected Context mContext;
    //存储所有可见的Node
    protected List<Node> mNodes;
    protected LayoutInflater mInflater;

    //存储所有的Node;
    protected List<Node> mAllNodes;

    //点击的回调接口
    private OnTreeNodeClickListener onTreeNodeClickListener;

    public interface OnTreeNodeClickListener{
        void onClick(Node node,int position);
    }

    public void setOnTreeNodeClickListener(OnTreeNodeClickListener onTreeNodeClickListener) {
        this.onTreeNodeClickListener = onTreeNodeClickListener;
    }

    public TreeListViewAdapter(ListView mTree,Context context,List<T> datas,int defaultExpandLevel)
            throws IllegalArgumentException,IllegalAccessException{
        mContext = context;
        mAllNodes = TreeHelper.getSortedNodes(datas,defaultExpandLevel);
        mNodes = TreeHelper.filterVisibleNode(mAllNodes);
        mInflater = LayoutInflater.from(context);
        mTree.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                expandOrCollapse(position);
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
        convertView.setPadding(node.getLevel()*30,3,3,3);
        return convertView;
    }

    public abstract View getConvertView(Node node,int position,View convertView,ViewGroup parent);
}











