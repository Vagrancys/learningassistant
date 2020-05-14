package com.vargancys.learningassistant.module.ladder.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.db.ladder.LadderRankDataBean;

import java.lang.invoke.MethodHandle;
import java.util.List;

import butterknife.BindView;

/**
 * @author Vagrancy
 * @date 2020/5/14
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 排行数据详情适配器
 */
public class ZoneRankDataAdapter extends BaseRecyclerAdapter {
    private Context mContext;
    private List<LadderRankDataBean> mBean;
    public ZoneRankDataAdapter(Context context,List<LadderRankDataBean> bean){
        mContext = context;
        mBean = bean;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        ZoneRankViewHolder mHolder = (ZoneRankViewHolder) holder;
        LadderRankDataBean bean = mBean.get(position);
        Glide.with(mContext).load(bean.getAvatar()).into(mHolder.zoneRankAvatar);
        mHolder.zoneRankAuthor.setText(bean.getAuthor());
        mHolder.zoneRankFloor.setText(bean.getFloor());
        mHolder.zoneRankLevel.setText(bean.getLevel());
        mHolder.zoneRankTime.setText(bean.getTime());
        mHolder.zoneRankTotal.setText(bean.getTotal());
        super.onBindViewHolder(holder, position);
    }

    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ZoneRankViewHolder(View.inflate(mContext, R.layout.zone_rank_item,null));
    }

    @Override
    public int getItemCount() {
        return mBean.size();
    }

    public class ZoneRankViewHolder extends CommonViewHolder{
        @BindView(R.id.zone_rank_avatar)
        ImageView zoneRankAvatar;
        @BindView(R.id.zone_rank_author)
        TextView zoneRankAuthor;
        @BindView(R.id.zone_rank_level)
        TextView zoneRankLevel;
        @BindView(R.id.zone_rank_floor)
        TextView zoneRankFloor;
        @BindView(R.id.zone_rank_time)
        TextView zoneRankTime;
        @BindView(R.id.zone_rank_total)
        TextView zoneRankTotal;
        private ZoneRankViewHolder(View itemView){
            super(itemView);
        }
    }
}
