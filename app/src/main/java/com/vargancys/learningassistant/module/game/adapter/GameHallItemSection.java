package com.vargancys.learningassistant.module.game.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.model.game.bean.GameHallBean;
import com.vargancys.learningassistant.utils.ResourceUtils;
import com.vargancys.learningassistant.widget.section.SectionedRecyclerViewAdapter;
import com.vargancys.learningassistant.widget.section.StatelessSection;

import java.util.List;

import butterknife.BindView;

/**
 * @author Vagrancy
 * @date 2020/7/12
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 关卡大厅排行部分
 */
public class GameHallItemSection extends StatelessSection {
    private String[] mRank;
    private List<GameHallBean.Hall> mBean;
    public GameHallItemSection(Context context, List<GameHallBean.Hall> bean){
        super(context, R.layout.game_hall_item);
        mBean = bean;
        mRank = ResourceUtils.getStringArray(getContext(),R.array.game_hall_rank);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindItemViewHolder(holder, position);
        GameHallItemViewHolder mHolder = (GameHallItemViewHolder) holder;
        GameHallBean.Hall hall = mBean.get(position);
        mHolder.itemNumber.setText(String.valueOf(hall.getId()));
        mHolder.itemAuthor.setText(String.valueOf(hall.getAuthor()));
        mHolder.itemTime.setText(String.valueOf(hall.getTime()));
        mHolder.itemCount.setText(String.valueOf(hall.getTotal()));
        mHolder.itemPeople.setText(String.valueOf(hall.getPeople()));
        mHolder.itemSummary.setText(String.valueOf(hall.getSummary()));
        mHolder.itemTitle.setText(String.valueOf(hall.getTitle()));
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new GameHallItemViewHolder(view);
    }

    @Override
    public int getContentItemsTotal() {
        return mBean.size();
    }

    private static class GameHallItemViewHolder extends SectionedRecyclerViewAdapter.EmptyViewHolder{
        @BindView(R.id.item_author)
        TextView itemAuthor;
        @BindView(R.id.item_number)
        TextView itemNumber;
        @BindView(R.id.item_count)
        TextView itemCount;
        @BindView(R.id.item_summary)
        TextView itemSummary;
        @BindView(R.id.item_title)
        TextView itemTitle;
        @BindView(R.id.item_time)
        TextView itemTime;
        @BindView(R.id.item_people)
        TextView itemPeople;
        public GameHallItemViewHolder(View view){
            super(view);
        }
    }
}
