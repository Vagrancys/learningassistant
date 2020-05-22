package com.vargancys.learningassistant.module.common.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.db.common.HelpCommendItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/08
 * version:1.0
 */
public class HelpCommendAdapter extends BaseRecyclerAdapter {
    private String TAG = "HelpCommentAdapter";
    private Context mContext;
    private List<HelpCommendItem> helpCommendItems;
    public HelpCommendAdapter(Context context,List<HelpCommendItem> items){
        this.mContext = context;
        this.helpCommendItems = items;
    }
    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new HelpCommendViewHolder(View.inflate(mContext, R.layout.help_commend_item,viewGroup));
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        Log.e(TAG,"position = "+position);
        HelpCommendViewHolder mHolder = (HelpCommendViewHolder) holder;
        HelpCommendItem helpCommendItem = helpCommendItems.get(position);
        mHolder.commendSummary.setText(helpCommendItem.getSummary());
        mHolder.commendTime.setText(helpCommendItem.getTime());
        super.onBindViewHolder(holder,position);
    }

    @Override
    public int getItemCount() {
        return helpCommendItems.size();
    }

    public class HelpCommendViewHolder extends CommonViewHolder{
        @BindView(R.id.commend_summary)
        TextView commendSummary;
        @BindView(R.id.commend_time)
        TextView commendTime;
        private HelpCommendViewHolder(View view){
            super(view);
        }
    }
}
