package com.vargancys.learningassistant.module.game.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.model.game.bean.GameHallRankBean;

import java.util.List;

import butterknife.BindView;

/**
 * @author Vagrancy
 * @date 2020/7/13
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 关卡大厅质量适配器
 */
public class GameHallHeightAdapter extends BaseRecyclerAdapter {
    private List<GameHallRankBean> mBean;
    public GameHallHeightAdapter(Context context, List<GameHallRankBean> bean){
        super(context);
        mBean = bean;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        GameHallViewHolder mHolder =(GameHallViewHolder) holder;
        GameHallRankBean bean = mBean.get(position);
        mHolder.hallCount.setText(bean.getHot());
        mHolder.hallNumber.setText(String.valueOf(bean.getId()));
        mHolder.hallSummary.setText(bean.getSummary());
        mHolder.hallTime.setText(bean.getTime());
        mHolder.hallTitle.setText(bean.getTitle());
        super.onBindViewHolder(holder, position);
    }

    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new GameHallViewHolder(getView(R.layout.item_game_hall_height));
    }

    @Override
    public int getItemCount() {
        return mBean.size();
    }

    public class GameHallViewHolder extends CommonViewHolder{
        @BindView(R.id.hall_count)
        TextView hallCount;
        @BindView(R.id.hall_number)
        TextView hallNumber;
        @BindView(R.id.hall_summary)
        TextView hallSummary;
        @BindView(R.id.hall_time)
        TextView hallTime;
        @BindView(R.id.hall_title)
        TextView hallTitle;
        public GameHallViewHolder(View itemView){
            super(itemView);
        }
    }
}
