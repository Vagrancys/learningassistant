package com.vargancys.learningassistant.module.mine.adapter;

import android.content.Context;
import android.support.annotation.BinderThread;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.db.overview.OverViewListContent;

import java.util.List;

import butterknife.BindView;

/**
 * @author Vagrancy
 * @date 2020/5/30
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 个人中心知识体系各项适配器
 */
public class SystemDataAdapter extends BaseRecyclerAdapter {
    private Context mContext;
    private List<OverViewListContent> mContent;
    public SystemDataAdapter(Context context,List<OverViewListContent> content){
        this.mContext = context;
        this.mContent = content;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        SystemViewHolder mHolder = (SystemViewHolder) holder;
        OverViewListContent mData = mContent.get(position);
        mHolder.systemTitle.setText(mData.getTitle());
        mHolder.systemAuthor.setText(mData.getAuthor());
        Glide.with(mContext).load(mData.getAuthor()).into(mHolder.systemAvatar);
        mHolder.systemGrade.setText(mData.getGrade());
        mHolder.systemLayer.setText(mData.getLayer());
        mHolder.systemLevel.setText(mData.getLevel());
        mHolder.systemNumber.setText(mData.getNumber());
        mHolder.systemPeople.setText(mData.getPeople());
        mHolder.systemSummary.setText(mData.getSummary());
        mHolder.systemTime.setText(mData.getTime());
        super.onBindViewHolder(holder, position);
    }

    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SystemViewHolder(getView(mContext, R.layout.system_data_item));
    }

    @Override
    public int getItemCount() {
        return mContent.size();
    }

    public class SystemViewHolder extends CommonViewHolder{
        @BindView(R.id.system_title)
        TextView systemTitle;
        @BindView(R.id.system_author)
        TextView systemAuthor;
        @BindView(R.id.system_avatar)
        ImageView systemAvatar;
        @BindView(R.id.system_grade)
        TextView systemGrade;
        @BindView(R.id.system_layer)
        TextView systemLayer;
        @BindView(R.id.system_level)
        TextView systemLevel;
        @BindView(R.id.system_number)
        TextView systemNumber;
        @BindView(R.id.system_people)
        TextView systemPeople;
        @BindView(R.id.system_summary)
        TextView systemSummary;
        @BindView(R.id.system_time)
        TextView systemTime;
        public SystemViewHolder(View itemView){
            super(itemView);
        }
    }
}
