package com.vargancys.learningassistant.module.overview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.model.overview.bean.OverViewHallBean;
import com.vargancys.learningassistant.widget.section.StatelessSection;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2020/6/27
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识体系大厅轮播适配器
 */
public class OverViewHallBannerSection extends StatelessSection {
    private Context mContext;
    private List<OverViewHallBean.Banner> mBean;
    public OverViewHallBannerSection(Context context, List<OverViewHallBean.Banner> bean){
        super(R.layout.overview_hall_banner_item);
        mContext = context;
        mBean = bean;
    };

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return null;
    }

    @Override
    public int getContentItemsTotal() {
        return mBean.size();
    }


}
