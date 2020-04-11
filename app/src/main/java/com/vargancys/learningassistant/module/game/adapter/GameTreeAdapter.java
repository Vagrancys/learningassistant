package com.vargancys.learningassistant.module.game.adapter;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.db.common.KnowListBean;
import com.vargancys.learningassistant.db.overview.OverViewListItem;
import com.vargancys.learningassistant.widget.TreeDirectory.Node;
import com.vargancys.learningassistant.widget.TreeDirectory.TreeListViewAdapter;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/10
 * version:1.0
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
        initData(viewHolder,mItem);
        return convertView;
    }

    private void initData(final ViewHolder viewHolder, OverViewListItem mItem) {
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
                        Log.e(TAG,"动画监控!");
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
    }
}
