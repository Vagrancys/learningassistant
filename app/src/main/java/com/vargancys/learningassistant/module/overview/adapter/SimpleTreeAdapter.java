package com.vargancys.learningassistant.module.overview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.db.overview.OverViewListBean;
import com.vargancys.learningassistant.widget.TreeDirectory.Node;
import com.vargancys.learningassistant.widget.TreeDirectory.TreeListViewAdapter;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/02
 * version:1.0
 */
public class SimpleTreeAdapter<T> extends TreeListViewAdapter<T> {
    public SimpleTreeAdapter(ListView mTree, Context content, List<OverViewListBean> datas,
                             int defaultExpandLevel)throws IllegalArgumentException,IllegalAccessException {
        super(mTree,content,datas,defaultExpandLevel,true);
    }

    @Override
    public View getConvertView(Node node, int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.overview_list_item,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.overview_icon);
            viewHolder.label = (TextView) convertView.findViewById(R.id.overview_title);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        if(node.getIcon() == -1){
            viewHolder.icon.setVisibility(View.INVISIBLE);
        }else{
            viewHolder.icon.setVisibility(View.VISIBLE);
            viewHolder.icon.setImageResource(node.getIcon());
        }
        viewHolder.label.setText(node.getName());
        return convertView;
    }

    private final class ViewHolder{
        ImageView icon;
        TextView label;
    }
}
