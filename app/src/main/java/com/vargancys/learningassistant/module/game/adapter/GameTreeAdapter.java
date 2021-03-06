package com.vargancys.learningassistant.module.game.adapter;

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
import com.vargancys.learningassistant.bean.common.KnowListBean;
import com.vargancys.learningassistant.bean.overview.OverViewListItem;
import com.vargancys.learningassistant.widget.TreeDirectory.Node;
import com.vargancys.learningassistant.widget.TreeDirectory.TreeListViewAdapter;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2020/4/10
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 关卡树适配器
 */

public class GameTreeAdapter<T> extends TreeListViewAdapter<T> {
    private static String TAG = "GameTreeAdapter";
    private List<OverViewListItem> mItems;
    private Handler mHandler;
    private Animation mShowAnimation;
    private Animation mHideAnimation;
    public GameTreeAdapter(ListView mTree, Context content, List<KnowListBean> datas, List<OverViewListItem> mItems,
                           Handler mHandler,int defaultExpandLevel)throws IllegalArgumentException,IllegalAccessException {
        super(mTree,content,datas,defaultExpandLevel,true);
        this.mItems = mItems;
        this.mHandler = mHandler;
        mShowAnimation = AnimationUtils.loadAnimation(content,R.anim.common_show_item_anim);
        mHideAnimation = AnimationUtils.loadAnimation(content,R.anim.common_hide_item_anim);
    }

    @Override
    public View getConvertView(Node node, int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.game_list_item,parent,false);
            viewHolder = new ViewHolder();
            initViewHolder(viewHolder,convertView);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        OverViewListItem mItem = mItems.get(position);
        initData(viewHolder,mItem,position);
        return convertView;
    }

    private void initData(final ViewHolder viewHolder, OverViewListItem mItem, final int position) {
        viewHolder.gameTitle.setText(mItem.getTitle());
        viewHolder.gameLevel.setText(mItem.getLevel()+"级");
        viewHolder.gameScore.setText(mItem.getScore()+"分");
        viewHolder.gameNumber.setText(String.valueOf(0));
        if(mItem.getStudy()){
            viewHolder.gameState.setText("已学");
        }else{
            viewHolder.gameState.setText("未学");
        }
        viewHolder.gameTime.setText(String.valueOf(mItem.getTime()));
        final Runnable mRunnable = new Runnable() {
            @Override
            public void run() {
                viewHolder.gameLayout.startAnimation(mHideAnimation);
            }
        };
        viewHolder.gameCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.gameLayout.setVisibility(View.VISIBLE);
                viewHolder.gameLayout.startAnimation(mShowAnimation);
                mHandler.removeCallbacksAndMessages(mRunnable);
                mHandler.postDelayed(mRunnable,2000);
                mHideAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        viewHolder.gameLayout.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });

        viewHolder.gameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler.removeCallbacks(mRunnable);
                viewHolder.gameLayout.startAnimation(mHideAnimation);
            }
        });

        viewHolder.gameShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onTreeNodeClickListener != null){
                    onTreeNodeClickListener.onShow(position);
                }
            }
        });
    }

    private void initViewHolder(ViewHolder viewHolder, View convertView) {
        viewHolder.gameTitle = convertView.findViewById(R.id.game_title);
        viewHolder.gameCollection = convertView.findViewById(R.id.game_collection);
        viewHolder.gameLayout = convertView.findViewById(R.id.game_layout);
        viewHolder.gameLevel = convertView.findViewById(R.id.game_level);
        viewHolder.gameScore = convertView.findViewById(R.id.game_score);
        viewHolder.gameNumber = convertView.findViewById(R.id.game_number);
        viewHolder.gameState = convertView.findViewById(R.id.game_state);
        viewHolder.gameTime = convertView.findViewById(R.id.game_time);
        viewHolder.gameShow = convertView.findViewById(R.id.game_show);
        convertView.setTag(viewHolder);
    }

    private final class ViewHolder{
        TextView gameTitle;
        ImageView gameCollection;
        TextView gameLevel;
        TextView gameScore;
        TextView gameState;
        TextView gameTime;
        TextView gameNumber;
        LinearLayout gameLayout;
        ImageView gameShow;
    }
}
