package com.vargancys.learningassistant.module.mine.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.model.mine.bean.LevelItemBean;
import com.vargancys.learningassistant.module.mine.activity.LevelDetailsActivity;
import com.vargancys.learningassistant.widget.section.SectionedRecyclerViewAdapter;
import com.vargancys.learningassistant.widget.section.StatelessSection;

import butterknife.BindView;

/**
 * @author Vagrancy
 * @date 2020/5/30
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 个人中心等级各项适配器
 */
public class LevelItemSection extends StatelessSection {

    private Context mContext;
    private LevelItemBean mBean;

    private Activity mActivity;
    public LevelItemSection(Context context, Activity activity, LevelItemBean bean) {
        super(R.layout.level_item_header, R.layout.level_item);
        mContext = context;
        mBean = bean;
        mActivity = activity;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        LevelHeaderViewHolder mHolder = (LevelHeaderViewHolder) holder;
        mHolder.levelTitle.setText(mBean.getTitle());
        super.onBindHeaderViewHolder(holder);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        LevelItemViewHolder mHolder = (LevelItemViewHolder) holder;
        mHolder.getParentView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LevelDetailsActivity.launch(mActivity,mBean.getId());
            }
        });
        mHolder.levelNew.setText(mBean.getNews());
        mHolder.levelNext.setText(mBean.getNext());
        mHolder.levelExperience.setText(mBean.getExperience());
        mHolder.levelNextExperience.setText(mBean.getNext_experience());
        mHolder.levelRank.setText(mBean.getRank());
        super.onBindItemViewHolder(holder, position);
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new LevelHeaderViewHolder(view);
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new LevelItemViewHolder(view);
    }

    @Override
    public int getContentItemsTotal() {
        return 1;
    }

    public static class LevelHeaderViewHolder extends SectionedRecyclerViewAdapter.EmptyViewHolder {
        @BindView(R.id.level_title)
        TextView levelTitle;

        public LevelHeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    public static class LevelItemViewHolder extends SectionedRecyclerViewAdapter.EmptyViewHolder {
        @BindView(R.id.level_new)
        TextView levelNew;
        @BindView(R.id.level_next)
        TextView levelNext;
        @BindView(R.id.level_experience)
        TextView levelExperience;
        @BindView(R.id.level_next_experience)
        TextView levelNextExperience;
        @BindView(R.id.level_rank)
        TextView levelRank;
        public LevelItemViewHolder(View itemView) {
            super(itemView);
        }
    }
}