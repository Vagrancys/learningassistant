package com.vargancys.learningassistant.module.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.bean.home.HomeKnowCommend;

import java.util.List;

import butterknife.BindView;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/08
 * version:1.0
 *首页知识评论适配器
 */
public class HomeKnowCommendAdapter extends BaseRecyclerAdapter {
    private String TAG = "HomeKnowCommendAdapter";
    private List<HomeKnowCommend> homeKnowCommends;
    public HomeKnowCommendAdapter(Context context, List<HomeKnowCommend> items){
        super(context);
        this.homeKnowCommends = items;
    }
    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new HomeCommendViewHolder(getView(R.layout.home_commend_item));
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        HomeCommendViewHolder mHolder = (HomeCommendViewHolder) holder;
        HomeKnowCommend homeKnowCommend = homeKnowCommends.get(position);
        mHolder.commendNumber.setText(String.valueOf(homeKnowCommend.getId()));
        mHolder.commendTime.setText(homeKnowCommend.getTime());
        mHolder.commendTitle.setText(homeKnowCommend.getTitle());
        super.onBindViewHolder(holder,position);
    }

    @Override
    public int getItemCount() {
        return homeKnowCommends.size();
    }

    public class HomeCommendViewHolder extends CommonViewHolder{
        @BindView(R.id.commend_number)
        TextView commendNumber;
        @BindView(R.id.commend_time)
        TextView commendTime;
        @BindView(R.id.commend_title)
        TextView commendTitle;
        private HomeCommendViewHolder(View view){
            super(view);
        }
    }
}
