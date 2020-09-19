package com.vargancys.learningassistant.module.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.bean.home.HomeKnowHistory;

import java.util.List;

import butterknife.BindView;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/08
 * version:1.0
 * 首页知识历史适配器
 */
public class HomeKnowHistoryAdapter extends BaseRecyclerAdapter {
    private String TAG = "HomeHistoryAdapter";
    private List<HomeKnowHistory> homeKnowHistories;
    public HomeKnowHistoryAdapter(Context context, List<HomeKnowHistory> items){
        super(context);
        this.homeKnowHistories = items;
    }
    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new HomeHistoryViewHolder(getView(R.layout.know_history_item));
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
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
