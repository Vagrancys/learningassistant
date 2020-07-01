package com.vargancys.learningassistant.module.overview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.db.overview.OverViewListItem;

import java.util.List;

import butterknife.BindView;

/**
 * @author Vagrancy
 * @date 2020/7/1
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识体系个人中心创建适配器
 */
public class OverViewCreateAdapter extends BaseRecyclerAdapter {
    private List<OverViewListItem> mItems;
    public OverViewCreateAdapter(Context context, List<OverViewListItem> items){
        super(context);
        mItems = items;
    }
    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new OverViewCreateViewHolder(getView(R.layout.item_overview_create));
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        OverViewCreateViewHolder mHolder = (OverViewCreateViewHolder) holder;
        OverViewListItem mItem = mItems.get(position);
        mHolder.createTitle.setText(mItem.getTitle());
        mHolder.createCount.setText(String.valueOf(mItem.getCount()));
        mHolder.createLevel.setText(String.valueOf(mItem.getLevel()));
        mHolder.createSummary.setText(mItem.getSummary());
        mHolder.createScore.setText(String.valueOf(mItem.getScore()));
        mHolder.createTime.setText(String.valueOf(mItem.getTime()));
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class OverViewCreateViewHolder extends CommonViewHolder{
        @BindView(R.id.create_title)
        TextView createTitle;
        @BindView(R.id.create_summary)
        TextView createSummary;
        @BindView(R.id.create_count)
        TextView createCount;
        @BindView(R.id.create_level)
        TextView createLevel;
        @BindView(R.id.create_score)
        TextView createScore;
        @BindView(R.id.create_time)
        TextView createTime;
        public OverViewCreateViewHolder(View itemView){
            super(itemView);
        }
    }
}
