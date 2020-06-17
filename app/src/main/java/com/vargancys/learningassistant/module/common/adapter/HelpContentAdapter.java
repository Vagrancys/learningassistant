package com.vargancys.learningassistant.module.common.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.db.common.HelpContentItem;

import java.util.List;

import butterknife.BindView;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/07
 * version:1.0
 * 帮助内容适配器
 */
public class HelpContentAdapter extends BaseRecyclerAdapter {
    private List<HelpContentItem> mBean;
    public HelpContentAdapter(Context context,List<HelpContentItem> bean){
        super(context);
        mBean = bean;
    }
    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new HelpContentViewHolder(getView(R.layout.help_content_item));
    }

    @Override
    public void onBindViewHolder(@NonNull CommonViewHolder holder, int position) {
        HelpContentViewHolder mHolder = (HelpContentViewHolder) holder;
        mHolder.helpNumber.setText(String.valueOf(mBean.get(position).getId()));
        mHolder.helpTime.setText(mBean.get(position).getTime());
        mHolder.helpTitle.setText(mBean.get(position).getTitle());
        super.onBindViewHolder(holder,position);
    }

    @Override
    public int getItemCount() {
        return mBean.size();
    }

    public class HelpContentViewHolder extends CommonViewHolder{
        @BindView(R.id.help_number)
        TextView helpNumber;
        @BindView(R.id.help_title)
        TextView helpTitle;
        @BindView(R.id.help_time)
        TextView helpTime;
        private HelpContentViewHolder(View view){
            super(view);
        }
    }
}
