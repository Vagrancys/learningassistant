package com.vargancys.learningassistant.module.overview.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.db.common.KnowListBean;
import com.vargancys.learningassistant.widget.TreeDirectory.Node;
import com.vargancys.learningassistant.widget.TreeDirectory.TreeListViewAdapter;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2020/4/2
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识体系简单树适配器
 */
public class SimpleTreeAdapter<T> extends TreeListViewAdapter<T> {
    public SimpleTreeAdapter(ListView mTree, Context content, List<KnowListBean> datas,
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
