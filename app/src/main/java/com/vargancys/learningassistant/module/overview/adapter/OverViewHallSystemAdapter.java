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
public class OverViewHallSystemAdapter extends BaseRecyclerAdapter {
    private List<OverViewHallRankBean> mBean;
    public OverViewHallSystemAdapter(Context context, List<OverViewHallRankBean> bean){
        super(context);
        mBean = bean;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        OverViewHallSystemViewHolder mHolder =(OverViewHallSystemViewHolder) holder;
        OverViewHallRankBean bean = mBean.get(position);
        mHolder.systemCount.setText(bean.getSystem());
        mHolder.systemNumber.setText(String.valueOf(bean.getId()));
        mHolder.systemSummary.setText(bean.getSummary());
        mHolder.systemTime.setText(bean.getTime());
        mHolder.systemTitle.setText(bean.getTitle());
        super.onBindViewHolder(holder, position);
    }

    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new OverViewHallSystemViewHolder(getView(R.layout.item_overview_hall_system));
    }

    @Override
    public int getItemCount() {
        return mBean.size();
    }

    public class OverViewHallSystemViewHolder extends CommonViewHolder{
        @BindView(R.id.system_count)
        TextView systemCount;
        @BindView(R.id.system_number)
        TextView systemNumber;
        @BindView(R.id.system_summary)
        TextView systemSummary;
        @BindView(R.id.system_time)
        TextView systemTime;
        @BindView(R.id.system_title)
        TextView systemTitle;
        public OverViewHallSystemViewHolder(View itemView){
            super(itemView);
        }
    }
}
