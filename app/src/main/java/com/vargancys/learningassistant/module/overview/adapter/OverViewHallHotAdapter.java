package com.vargancys.learningassistant.module.overview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.model.overview.bean.OverViewHallRankBean;

import java.util.List;

import butterknife.BindView;

/**
 * @author Vagrancy
 * @date 2020/6/29
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识体系大厅质量适配器
 */
public class OverViewHallHotAdapter extends BaseRecyclerAdapter {
    private List<OverViewHallRankBean> mBean;
    public OverViewHallHotAdapter(Context context, List<OverViewHallRankBean> bean){
        super(context);
        mBean = bean;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        OverViewHallHotViewHolder mHolder =(OverViewHallHotViewHolder) holder;
        OverViewHallRankBean bean = mBean.get(position);
        mHolder.hotCount.setText(bean.getHot());
        mHolder.hotNumber.setText(String.valueOf(bean.getId()));
        mHolder.hotSummary.setText(bean.getSummary());
        mHolder.hotTime.setText(bean.getTime());
        mHolder.hotTitle.setText(bean.getTitle());
        super.onBindViewHolder(holder, position);
    }

    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new OverViewHallHotViewHolder(getView(R.layout.item_overview_hall_hot));
    }

    @Override
    public int getItemCount() {
        return mBean.size();
    }

    public class OverViewHallHotViewHolder extends CommonViewHolder{
        @BindView(R.id.hot_count)
        TextView hotCount;
        @BindView(R.id.hot_number)
        TextView hotNumber;
        @BindView(R.id.hot_summary)
        TextView hotSummary;
        @BindView(R.id.hot_time)
        TextView hotTime;
        @BindView(R.id.hot_title)
        TextView hotTitle;
        public OverViewHallHotViewHolder(View itemView){
            super(itemView);
        }
    }
}
