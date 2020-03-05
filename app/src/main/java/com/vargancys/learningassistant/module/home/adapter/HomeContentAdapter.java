package com.vargancys.learningassistant.module.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.model.home.bean.HomeContentBean;
import com.vargancys.learningassistant.widget.MasterProgressView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/02
 * version:1.0
 */
public class HomeContentAdapter extends BaseRecyclerAdapter {
    private Context context;
    private List<?> contentBean;
    public HomeContentAdapter(Context context, List<?> contentBean){
        this.contentBean = contentBean;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new HomeContentViewHolder(View.inflate(context, R.layout.home_content_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HomeContentViewHolder viewHolder = (HomeContentViewHolder) holder;
        HomeContentBean.ContentBean bean = (HomeContentBean.ContentBean) contentBean.get(position);
        if(bean.isHave()){
            viewHolder.contentHave.setImageResource(R.drawable.know_have_selected);
        }else{
            viewHolder.contentHave.setImageResource(R.drawable.know_have_normal);
        }

        viewHolder.contentTitle.setText(bean.getTitle());
        int ImageId;
        switch (bean.getLevel()){
            case 0:
                ImageId = R.drawable.know_level_1;
                break;
            case 1:
                ImageId  = R.drawable.know_level_2;
                break;
            case 2:
                ImageId = R.drawable.know_level_3;
                break;
            case 3:
                ImageId = R.drawable.know_level_4;
                break;
            default:
                ImageId = R.drawable.know_level_1;
                break;
        }
        viewHolder.contentLevel.setImageResource(ImageId);
        viewHolder.contentSummary.setText(bean.getSummary());
        viewHolder.contentProgress.setProgress(bean.getProgress());
        viewHolder.contentCount.setText(bean.getCount()+"/"+bean.getMax()+"次");
        viewHolder.contentMasterLevel.setMasterLevel(bean.getMasterLevel());
    }

    @Override
    public int getItemCount() {
        return contentBean.size();
    }

    private class HomeContentViewHolder extends CommonViewHolder{
        @BindView(R.id.content_item_have)
        ImageView contentHave;
        @BindView(R.id.content_item_title)
        TextView contentTitle;
        @BindView(R.id.content_item_level)
        ImageView contentLevel;
        @BindView(R.id.content_item_summary)
        TextView contentSummary;
        @BindView(R.id.content_item_study_title)
        TextView contentStudyTitle;
        @BindView(R.id.content_item_progress)
        ProgressBar contentProgress;
        @BindView(R.id.content_item_count)
        TextView contentCount;
        @BindView(R.id.content_item_master_level)
        MasterProgressView contentMasterLevel;
        private HomeContentViewHolder(View view){
            super(view);
            ButterKnife.bind(view);
        }
    }
}
