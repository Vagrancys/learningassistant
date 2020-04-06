package com.vargancys.learningassistant.module.overview.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
 * time  : 2020/04/04
 * version:1.0
 */
public class AddTreeAdapter<T> extends TreeListViewAdapter<T> {
    private Animation mShowAnim;
    private Animation mHideAnim;
    private Handler mHandler;
    public AddTreeAdapter(ListView mTree, Context content, Handler handler,List<OverViewListBean> datas,
                          int defaultExpandLevel) throws IllegalArgumentException, IllegalAccessException {
        super(mTree, content, datas, defaultExpandLevel,false);
        mHandler =handler;
        mShowAnim = AnimationUtils.loadAnimation(content,R.anim.common_show_item_anim);
        mHideAnim = AnimationUtils.loadAnimation(content,R.anim.common_hide_item_anim);
    }

    @Override
    public View getConvertView(final Node node,final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.overview_add_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.overViewTitle = convertView.findViewById(R.id.overview_title);
            viewHolder.overViewTool = convertView.findViewById(R.id.overview_tool);
            viewHolder.overViewToolLayout = convertView.findViewById(R.id.overview_tool_layout);
            viewHolder.overViewToolUpdate = convertView.findViewById(R.id.overview_tool_update);
            viewHolder.overViewToolDelete = convertView.findViewById(R.id.overview_tool_delete);
            viewHolder.overViewScore = convertView.findViewById(R.id.overview_score);
            viewHolder.overViewLevel = convertView.findViewById(R.id.overview_level);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.overViewTitle.setText(node.getName());
        viewHolder.overViewTool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.overViewTool.setVisibility(View.GONE);
                viewHolder.overViewToolLayout.setVisibility(View.VISIBLE);
                viewHolder.overViewToolLayout.startAnimation(mShowAnim);
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        viewHolder.overViewToolLayout.startAnimation(mHideAnim);
                        mHideAnim.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                viewHolder.overViewTool.setVisibility(View.VISIBLE);
                                viewHolder.overViewToolLayout.setVisibility(View.GONE);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });

                    }
                },3000);
            }
        });
        viewHolder.overViewToolUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onTreeNodeClickListener != null){
                    onTreeNodeClickListener.onUpdate(node, position);
                }
            }
        });

        viewHolder.overViewToolDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onTreeNodeClickListener != null){
                    onTreeNodeClickListener.onDelete(node, position);
                }
            }
        });
        viewHolder.overViewScore.setText(node.getScore()+"分");
        int ImageId;
        switch (node.getLevel()){
            case 1:
                ImageId = R.drawable.know_level_1;
                break;
            case 2:
                ImageId  = R.drawable.know_level_2;
                break;
            case 3:
                ImageId = R.drawable.know_level_3;
                break;
            case 4:
                ImageId = R.drawable.know_level_4;
                break;
            case 5:
                ImageId = R.drawable.know_level_5;
                break;
            default:
                ImageId = R.drawable.know_level_1;
                break;
        }
        viewHolder.overViewLevel.setImageResource(ImageId);
        return convertView;
    }

    private final class ViewHolder {
        TextView overViewTitle;
        ImageView overViewTool;
        LinearLayout overViewToolLayout;
        ImageView overViewToolUpdate;
        ImageView overViewToolDelete;
        TextView overViewScore;
        ImageView overViewLevel;
    }
}