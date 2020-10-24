package com.vargancys.learningassistant.module.mine.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.model.mine.bean.KnowLedgeItemBean;
import com.vargancys.learningassistant.module.home.activity.show.ShowAidedActivity;
import com.vargancys.learningassistant.module.home.activity.show.ShowArticleActivity;
import com.vargancys.learningassistant.module.home.activity.show.ShowBookActivity;
import com.vargancys.learningassistant.module.home.activity.show.ShowFeelingActivity;
import com.vargancys.learningassistant.module.home.activity.show.ShowClassActivity;
import com.vargancys.learningassistant.module.mine.activity.KnowLedgeItemActivity;
import com.vargancys.learningassistant.utils.ToastUtils;
import com.vargancys.learningassistant.widget.section.SectionedRecyclerViewAdapter;
import com.vargancys.learningassistant.widget.section.StatelessSection;

import butterknife.BindView;

/**
 * @author Vagrancy
 * @date 2020/5/24
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 个人中心知识适配器
 */
public class KnowLedgeItemSection extends StatelessSection {

    private KnowLedgeItemBean mBean;

    private Activity mActivity;
    public KnowLedgeItemSection(Context context, Activity activity, KnowLedgeItemBean bean) {
        super(context,R.layout.knowledge_item_header, R.layout.knowledge_item);
        mBean = bean;
        mActivity = activity;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        KnowLedgeHeaderViewHolder mHolder = (KnowLedgeHeaderViewHolder) holder;
        mHolder.knowledgeTitle.setText(mBean.getTitle());
        mHolder.knowledgeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KnowLedgeItemActivity.launch(mActivity,mBean.getType());
            }
        });
        mHolder.knowledgeCount.setText(mBean.getCount());
        mHolder.knowledgeQuality.setText(mBean.getQuality());
        mHolder.knowledgeTime.setText(String.valueOf(mBean.getTime()));
        mHolder.knowledgePeople.setText(String.valueOf(mBean.getPeople()));
        mHolder.knowledgeLevel.setText(String.valueOf(mBean.getLevel()));
        mHolder.knowledgePrize.setText(String.valueOf(mBean.getPrize()));

        super.onBindHeaderViewHolder(holder);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        KnowLedgeItemViewHolder mHolder = (KnowLedgeItemViewHolder) holder;
        final KnowLedgeItemBean.KnowLedgeItem mItem = mBean.getItems().get(position);
        mHolder.getParentView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(mItem.isCreateClass()){
                        if(!mItem.isHave()){
                            launchShowActivity(mItem.getId().intValue(),mItem.getLevel());
                        }
                    }else{
                        if(mItem.isHave()){
                            ToastUtils.ToastText(getContext(),"这需要官方来创建!个人不能创建!");
                        }
                }
            }
        });
        mHolder.knowledgeNumber.setText(String.valueOf(mItem.getNumber()));
        mHolder.knowledgeLevel.setImageResource(mItem.getLevel());
        mHolder.knowledgeTitle.setText(mItem.getTitle());
        mHolder.knowledgeLook.setText(String.valueOf(mItem.getLook()));
        mHolder.knowledgePeople.setText(String.valueOf(mItem.getProblem()));
        mHolder.knowledgeSummary.setText(mItem.getSummary());
        mHolder.knowledgeTime.setText(String.valueOf(mItem.getTime()));
        super.onBindItemViewHolder(holder, position);
    }

    private void launchShowActivity(int item_id,int level) {
        switch (level){
            case 1:
                ShowArticleActivity.launch(mActivity,item_id);
                break;
            case 2:
                ShowBookActivity.launch(mActivity,item_id);
                break;
            case 3:
                ShowAidedActivity.launch(mActivity,item_id);
                break;
            case 4:
                ShowClassActivity.launch(mActivity,item_id);
                break;
            case 5:
                ShowFeelingActivity.launch(mActivity,item_id);
                break;
            default:
                ShowCommonDefaultActivity.launch(mActivity,item_id);
                break;
        }
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new KnowLedgeHeaderViewHolder(view);
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new KnowLedgeItemViewHolder(view);
    }

    @Override
    public int getContentItemsTotal() {
        return mBean.getItems().size();
    }

    public static class KnowLedgeHeaderViewHolder extends SectionedRecyclerViewAdapter.EmptyViewHolder {
        @BindView(R.id.knowledge_title)
        TextView knowledgeTitle;
        @BindView(R.id.knowledge_more)
        TextView knowledgeMore;
        @BindView(R.id.knowledge_count)
        TextView knowledgeCount;
        @BindView(R.id.knowledge_quality)
        TextView knowledgeQuality;
        @BindView(R.id.knowledge_time)
        TextView knowledgeTime;
        @BindView(R.id.knowledge_people)
        TextView knowledgePeople;
        @BindView(R.id.knowledge_level)
        TextView knowledgeLevel;
        @BindView(R.id.knowledge_prize)
        TextView knowledgePrize;
        public KnowLedgeHeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    public static class KnowLedgeItemViewHolder extends SectionedRecyclerViewAdapter.EmptyViewHolder {
        @BindView(R.id.knowledge_number)
        TextView knowledgeNumber;
        @BindView(R.id.knowledge_title)
        TextView knowledgeTitle;
        @BindView(R.id.knowledge_summary)
        TextView knowledgeSummary;
        @BindView(R.id.knowledge_level)
        ImageView knowledgeLevel;
        @BindView(R.id.knowledge_people)
        TextView knowledgePeople;
        @BindView(R.id.knowledge_look)
        TextView knowledgeLook;
        @BindView(R.id.knowledge_time)
        TextView knowledgeTime;
        public KnowLedgeItemViewHolder(View itemView) {
            super(itemView);
        }
    }
}
