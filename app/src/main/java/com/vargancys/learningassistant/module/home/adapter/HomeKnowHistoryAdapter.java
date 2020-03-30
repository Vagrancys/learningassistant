package com.vargancys.learningassistant.module.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.db.common.HelpCommendItem;
import com.vargancys.learningassistant.db.home.HomeKnowCommend;
import com.vargancys.learningassistant.db.home.HomeKnowHistory;

import java.util.List;

import butterknife.BindView;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/08
 * version:1.0
 */
public class HomeKnowHistoryAdapter extends BaseRecyclerAdapter {
    private String TAG = "HomeHistoryAdapter";
    private Context mContext;
    private List<HomeKnowHistory> homeKnowHistories;
    public HomeKnowHistoryAdapter(Context context, List<HomeKnowHistory> items){
        this.mContext = context;
        this.homeKnowHistories = items;
    }
    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new HomeHistoryViewHolder(View.inflate(mContext, R.layout.know_history_item,null));
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        Log.e(TAG,"position = "+position);
        HomeHistoryViewHolder mHolder = (HomeHistoryViewHolder) holder;
        HomeKnowHistory homeKnowHistory = homeKnowHistories.get(position);
        mHolder.historyNumber.setText(String.valueOf(homeKnowHistory.getId()));
        mHolder.historyTime.setText(homeKnowHistory.getTime());
        mHolder.historyTitle.setText(homeKnowHistory.getTitle());
        mHolder.historySummary.setText(homeKnowHistory.getSummary());
        super.onBindViewHolder(holder,position);
    }

    @Override
    public int getItemCount() {
        return homeKnowHistories.size();
    }

    public class HomeHistoryViewHolder extends CommonViewHolder{
        @BindView(R.id.history_number)
        TextView historyNumber;
        @BindView(R.id.history_time)
        TextView historyTime;
        @BindView(R.id.history_title)
        TextView historyTitle;
        @BindView(R.id.history_summary)
        TextView historySummary;
        private HomeHistoryViewHolder(View view){
            super(view);
        }
    }
}