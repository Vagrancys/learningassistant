package com.vargancys.learningassistant.module.common.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.db.common.HelpContentItem;
import com.vargancys.learningassistant.model.common.bean.HelpContentBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/07
 * version:1.0
 */
public class HelpContentAdapter extends BaseRecyclerAdapter {
    private Context mContext;
    private List<?> mBean;
    public HelpContentAdapter(Context context,List<?> bean){
        mBean = bean;
        mContext = context;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new HelpContentViewHolder(View.inflate(mContext, R.layout.help_content_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HelpContentViewHolder mHolder = (HelpContentViewHolder) holder;
        HelpContentItem helpContentItem = (HelpContentItem) mBean.get(position);
        mHolder.helpNumber.setText(helpContentItem.getNumber());
        mHolder.helpTime.setText(helpContentItem.getTime());
        mHolder.helpTitle.setText(helpContentItem.getTitle());
    }

    private class HelpContentViewHolder extends CommonViewHolder{
        @BindView(R.id.help_number)
        TextView helpNumber;
        @BindView(R.id.help_title)
        TextView helpTitle;
        @BindView(R.id.help_time)
        TextView helpTime;
        private HelpContentViewHolder(View view){
            super(view);
            ButterKnife.bind(view);
        }
    }
}
