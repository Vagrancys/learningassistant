package com.vargancys.learningassistant.module.overview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.db.overview.OverViewListContent;

import java.util.List;

import butterknife.BindView;

/**
 * @author Vagrancy
 * @date 2020/4/3
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识体系搜索适配器
 */
public class OverViewSearchAdapter extends BaseRecyclerAdapter {
    private Context mContext;
    private List<OverViewListContent> mObjects;
    public OverViewSearchAdapter(Context context, List<OverViewListContent> objects){
        this.mContext = context;
        this.mObjects = objects;
    }
    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new OverViewSearchViewHolder(View.inflate(mContext, R.layout.overview_search_item,null));
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        OverViewSearchViewHolder mHolder = (OverViewSearchViewHolder) holder;
        OverViewListContent mContent = mObjects.get(position);
        if(mContent.getRecommend()){
            mHolder.overviewRecommend.setVisibility(View.VISIBLE);
        }else{
            mHolder.overviewRecommend.setVisibility(View.GONE);
        }
        mHolder.overviewTitle.setText(mContent.getTitle());
        mHolder.overviewAvatar.setImageResource(R.drawable.overview_avatar_normal);
        mHolder.overviewAuthor.setText(mContent.getAuthor());
        int imageId = 0;
        switch (mContent.getLevel()){
            case 0:
                imageId = R.drawable.know_level_0;
                break;
            case 1:
                imageId = R.drawable.know_level_1;
                break;
            case 2:
                imageId = R.drawable.know_level_2;
                break;
            case 3:
                imageId = R.drawable.know_level_3;
                break;
            case 4:
                imageId = R.drawable.know_level_4;
                break;
            case 5:
                imageId = R.drawable.know_level_5;
                break;
        }
        mHolder.overviewLevel.setImageResource(imageId);
        mHolder.overviewSummary.setText(mContent.getSummary());
        mHolder.overviewGrade.setText(mContent.getGrade()+"分");
        mHolder.overviewNumber.setText(mContent.getNumber()+"个");
        mHolder.overviewLayer.setText(mContent.getLayer()+"层");
        if (mContent.getPeople() != 0){
            mHolder.overviewPeople.setText(mContent.getPeople()+"人使用");
        }
        mHolder.overviewTime.setText(mContent.getTime());
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return mObjects.size();
    }

    public class OverViewSearchViewHolder extends CommonViewHolder{
        @BindView(R.id.overview_recommend)
        ImageView overviewRecommend;
        @BindView(R.id.overview_title)
        TextView overviewTitle;
        @BindView(R.id.overview_avatar)
        ImageView overviewAvatar;
        @BindView(R.id.overview_author)
        TextView overviewAuthor;
        @BindView(R.id.overview_level)
        ImageView overviewLevel;
        @BindView(R.id.overview_summary)
        TextView overviewSummary;
        @BindView(R.id.overview_grade)
        TextView overviewGrade;
        @BindView(R.id.overview_number)
        TextView overviewNumber;
        @BindView(R.id.overview_layer)
        TextView overviewLayer;
        @BindView(R.id.overview_people)
        TextView overviewPeople;
        @BindView(R.id.overview_time)
        TextView overviewTime;
        public OverViewSearchViewHolder(View view){
            super(view);
        }
    }
}
