package com.vargancys.learningassistant.module.mine.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.model.mine.bean.ProblemItemBean;
import com.vargancys.learningassistant.module.mine.activity.ProblemDetailsActivity;
import com.vargancys.learningassistant.module.mine.activity.ProblemItemActivity;
import com.vargancys.learningassistant.widget.section.SectionedRecyclerViewAdapter;
import com.vargancys.learningassistant.widget.section.StatelessSection;

import butterknife.BindView;

/**
 * @author Vagrancy
 * @date 2020/6/1
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 个人中心各项适配器
 */
public class ProblemItemSection extends StatelessSection {

    private Context mContext;
    private ProblemItemBean mBean;

    private Activity mActivity;
    public ProblemItemSection(Context context, Activity activity, ProblemItemBean bean) {
        super(R.layout.problem_item_header, R.layout.problem_item);
        mContext = context;
        mBean = bean;
        mActivity = activity;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        ProblemHeaderViewHolder mHolder = (ProblemHeaderViewHolder) holder;
        mHolder.problemTitle.setText(mBean.getTitle());
        mHolder.problemMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProblemItemActivity.launch(mActivity,mBean.getType());
            }
        });
        mHolder.problemCount.setText(mBean.getCount());
        mHolder.problemLevel.setText(mBean.getLevel());
        mHolder.problemPeople.setText(String.valueOf(mBean.getTime()));
        mHolder.problemTime.setText(String.valueOf(mBean.getTime()));
        mHolder.problemUse.setText(String.valueOf(mBean.getUse()));

        super.onBindHeaderViewHolder(holder);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        ProblemItemViewHolder mHolder = (ProblemItemViewHolder) holder;
        final ProblemItemBean.ProblemItem mItem = mBean.getItems().get(position);
        mHolder.getParentView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProblemDetailsActivity.launch(mActivity,mItem.getId());
            }
        });
        mHolder.problemContext.setText(String.valueOf(mItem.getContext()));
        mHolder.problemTitle.setText(mItem.getTitle());
        mHolder.problemLevel.setText(String.valueOf(mItem.getLevel()));

        mHolder.problemTime.setText(mItem.getTime());
        mHolder.problemPeople.setText(String.valueOf(mItem.getPeople()));
        mHolder.problemType.setText(mItem.getType());
        mHolder.problemUse.setText(mItem.getUse());
        super.onBindItemViewHolder(holder, position);
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new ProblemHeaderViewHolder(view);
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new ProblemItemViewHolder(view);
    }

    @Override
    public int getContentItemsTotal() {
        return mBean.getItems().size();
    }

    public static class ProblemHeaderViewHolder extends SectionedRecyclerViewAdapter.EmptyViewHolder {
        @BindView(R.id.problem_title)
        TextView problemTitle;
        @BindView(R.id.problem_more)
        TextView problemMore;
        @BindView(R.id.problem_count)
        TextView problemCount;
        @BindView(R.id.problem_level)
        TextView problemLevel;
        @BindView(R.id.problem_people)
        TextView problemPeople;
        @BindView(R.id.problem_use)
        TextView problemUse;
        @BindView(R.id.problem_time)
        TextView problemTime;
        public ProblemHeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    public static class ProblemItemViewHolder extends SectionedRecyclerViewAdapter.EmptyViewHolder {
        @BindView(R.id.problem_level)
        TextView problemLevel;
        @BindView(R.id.problem_title)
        TextView problemTitle;
        @BindView(R.id.problem_context)
        TextView problemContext;
        @BindView(R.id.problem_type)
        TextView problemType;
        @BindView(R.id.problem_use)
        TextView problemUse;
        @BindView(R.id.problem_people)
        TextView problemPeople;
        @BindView(R.id.problem_time)
        TextView problemTime;
        public ProblemItemViewHolder(View itemView) {
            super(itemView);
        }
    }
}
