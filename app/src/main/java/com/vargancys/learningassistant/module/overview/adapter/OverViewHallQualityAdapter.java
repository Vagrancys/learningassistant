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
public class OverViewHallQualityAdapter extends BaseRecyclerAdapter {
    private List<OverViewHallRankBean> mBean;
    public OverViewHallQualityAdapter(Context context,List<OverViewHallRankBean> bean){
        super(context);
        mBean = bean;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        OverViewHallQualityViewHolder mHolder =(OverViewHallQualityViewHolder) holder;
        OverViewHallRankBean bean = mBean.get(position);
        mHolder.qualityCount.setText(bean.getQuality());
        mHolder.qualityNumber.setText(String.valueOf(bean.getId()));
        mHolder.qualitySummary.setText(bean.getSummary());
        mHolder.qualityTime.setText(bean.getTime());
        mHolder.qualityTitle.setText(bean.getTitle());
        super.onBindViewHolder(holder, position);
    }

    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new OverViewHallQualityViewHolder(getView(R.layout.item_overview_hall_quality));
    }

    @Override
    public int getItemCount() {
        return mBean.size();
    }

    public class OverViewHallQualityViewHolder extends CommonViewHolder{
        @BindView(R.id.quality_count)
        TextView qualityCount;
        @BindView(R.id.quality_number)
        TextView qualityNumber;
        @BindView(R.id.quality_summary)
        TextView qualitySummary;
        @BindView(R.id.quality_time)
        TextView qualityTime;
        @BindView(R.id.quality_title)
        TextView qualityTitle;
        public OverViewHallQualityViewHolder(View itemView){
            super(itemView);
        }
    }
}
