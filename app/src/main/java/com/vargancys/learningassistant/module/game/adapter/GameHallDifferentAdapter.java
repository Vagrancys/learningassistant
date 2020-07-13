package com.vargancys.learningassistant.module.game.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.model.game.bean.GameHallRankBean;
import com.vargancys.learningassistant.model.overview.bean.OverViewHallRankBean;
import com.vargancys.learningassistant.module.overview.adapter.OverViewHallHotAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * @author Vagrancy
 * @date 2020/7/13
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 关卡大厅难度适配器
 */
public class GameHallDifferentAdapter extends BaseRecyclerAdapter {
    private List<GameHallRankBean> mBean;
    public GameHallDifferentAdapter(Context context, List<GameHallRankBean> bean){
        super(context);
        mBean = bean;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        GameHallDifferentViewHolder mHolder =(GameHallDifferentViewHolder) holder;
        GameHallRankBean bean = mBean.get(position);
        mHolder.differentCount.setText(bean.getHot());
        mHolder.differentNumber.setText(String.valueOf(bean.getId()));
        mHolder.differentSummary.setText(bean.getSummary());
        mHolder.differentTime.setText(bean.getTime());
        mHolder.differentTitle.setText(bean.getTitle());
        super.onBindViewHolder(holder, position);
    }

    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new GameHallDifferentViewHolder(getView(R.layout.item_game_hall_different));
    }

    @Override
    public int getItemCount() {
        return mBean.size();
    }

    public class GameHallDifferentViewHolder extends CommonViewHolder{
        @BindView(R.id.different_count)
        TextView differentCount;
        @BindView(R.id.different_number)
        TextView differentNumber;
        @BindView(R.id.different_summary)
        TextView differentSummary;
        @BindView(R.id.different_time)
        TextView differentTime;
        @BindView(R.id.different_title)
        TextView differentTitle;
        public GameHallDifferentViewHolder(View itemView){
            super(itemView);
        }
    }
}
