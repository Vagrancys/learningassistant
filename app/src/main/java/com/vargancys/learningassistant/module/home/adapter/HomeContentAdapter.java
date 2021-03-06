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
import com.vargancys.learningassistant.bean.home.KnowLedgeBean;
import com.vargancys.learningassistant.widget.MasterProgressView;

import java.util.List;

import butterknife.BindView;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/02
 * version:1.0
 * 首页中心适配器
 */
public class HomeContentAdapter extends BaseRecyclerAdapter {
    private List<?> contentBean;
    public HomeContentAdapter(Context context, List<?> contentBean){
        super(context);
        this.contentBean = contentBean;
    }

    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new HomeContentViewHolder(getView(R.layout.home_content_item));
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        HomeContentViewHolder viewHolder = (HomeContentViewHolder) holder;
        KnowLedgeBean bean = (KnowLedgeBean) contentBean.get(position);
        if(bean.getHave()){
            viewHolder.contentHave.setImageResource(R.drawable.know_have_selected);
        }else{
            viewHolder.contentHave.setImageResource(R.drawable.know_have_normal);
        }

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
        viewHolder.contentProgress.setProgress(bean.getProgress());
        viewHolder.contentCount.setText(bean.getCount()+"/"+bean.getMax()+"次");
        viewHolder.contentMasterLevel.setMasterLevel(bean.getMasterLevel());
        viewHolder.contentStudyTitle.setText(bean.getStudyTitle());
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return contentBean.size();
    }

    public class HomeContentViewHolder extends CommonViewHolder{
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
        }
    }
}
