package com.vargancys.learningassistant.module.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.db.home.HomeKnowItem;
import com.vargancys.learningassistant.widget.MasterProgressView;

import java.util.List;

import butterknife.BindView;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/02
 * version:1.0
 * 知识搜索适配器
 */
public class KnowSearchAdapter extends BaseRecyclerAdapter {
    private Context context;
    private List<HomeKnowItem> contentBean;
    public KnowSearchAdapter(Context context, List<HomeKnowItem> contentBean){
        this.contentBean = contentBean;
        this.context = context;
    }

    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new HomeContentViewHolder(getView(context, R.layout.home_search_item));
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        HomeContentViewHolder viewHolder = (HomeContentViewHolder) holder;
        HomeKnowItem bean = contentBean.get(position);

        viewHolder.contentTitle.setText(bean.getTitle());
        int ImageId;
        switch (bean.getLevel()){
            case 1:
                ImageId = R.drawable.know_level_1;
                break;
            case 2:
                ImageId  = R.drawable.know_level_2;
                break;
            case 3:
                ImageId = R.drawable.know_level_3;
                break;
            case 4:
                ImageId = R.drawable.know_level_4;
                break;
            case 5:
                ImageId = R.drawable.know_level_5;
                break;
            default:
                ImageId = R.drawable.know_level_1;
                break;
        }
        viewHolder.contentLevel.setImageResource(ImageId);
        viewHolder.contentSummary.setText(bean.getSummary());
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return contentBean.size();
    }

    public class HomeContentViewHolder extends CommonViewHolder{
        @BindView(R.id.content_item_title)
        TextView contentTitle;
        @BindView(R.id.content_item_level)
        ImageView contentLevel;
        @BindView(R.id.content_item_summary)
        TextView contentSummary;
        private HomeContentViewHolder(View view){
            super(view);
        }
    }
}
