package com.vargancys.learningassistant.module.overview.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.bean.common.KnowListBean;
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
public class UpdateTreeAdapter<T> extends TreeListViewAdapter<T> {
    private List<KnowListBean> mData;
    public UpdateTreeAdapter(ListView mTree, Context content, List<KnowListBean> datas,
                             int defaultExpandLevel)throws IllegalArgumentException,IllegalAccessException {
        super(mTree,content,datas,defaultExpandLevel,true);
        mData = datas;
    }

    private OnUpdateClickListener onUpdateClickListener;

    public void setOnUpdateClickListener(OnUpdateClickListener onUpdateClickListener) {
        this.onUpdateClickListener = onUpdateClickListener;
    }

    public interface OnUpdateClickListener{
        void update(int position,long id);
    }

    @Override
    public View getConvertView(Node node, int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.item_overview_list_update,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.icon =convertView.findViewById(R.id.overview_icon);
            viewHolder.level = convertView.findViewById(R.id.overview_level);
            viewHolder.title = convertView.findViewById(R.id.overview_title);
            viewHolder.score = convertView.findViewById(R.id.overview_score);
            viewHolder.update = convertView.findViewById(R.id.overview_update);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        initData(viewHolder,mData.get(position),position);


        return convertView;
    }

    private void initData(ViewHolder viewHolder, final KnowListBean knowListBean, final int position) {
        viewHolder.icon.setVisibility(View.INVISIBLE);
        viewHolder.title.setText(knowListBean.getTitle());
        int res = 0;
        switch (knowListBean.getLevel()){
            case 1:
                res = R.drawable.know_level_0;
                break;
            case 2:
                res = R.drawable.know_level_2;
                break;
            case 3:
                res = R.drawable.know_level_3;
                break;
            case 4:
                res = R.drawable.know_level_4;
                break;
            case 5:
                res = R.drawable.know_level_5;
                break;
        }
        viewHolder.level.setImageResource(res);
        viewHolder.score.setText(knowListBean.getScore());
        viewHolder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onUpdateClickListener != null){
                    onUpdateClickListener.update(position,knowListBean.getKnowId());
                }
            }
        });
    }

    private static final class ViewHolder{
        ImageView icon;
        ImageView level;
        TextView title;
        TextView score;
        ImageView update;
    }
}
