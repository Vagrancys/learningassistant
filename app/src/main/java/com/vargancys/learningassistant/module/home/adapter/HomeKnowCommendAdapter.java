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

import java.util.List;

import butterknife.BindView;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/08
 * version:1.0
 */
public class HomeKnowCommendAdapter extends BaseRecyclerAdapter {
    private String TAG = "HomeKnowCommendAdapter";
    private Context mContext;
    private List<HomeKnowCommend> homeKnowCommends;
    public HomeKnowCommendAdapter(Context context, List<HomeKnowCommend> items){
        this.mContext = context;
        this.homeKnowCommends = items;
    }
    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new HomeCommendViewHolder(View.inflate(mContext, R.layout.home_commend_item,null));
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        Log.e(TAG,"position = "+position);
        HomeCommendViewHolder mHolder = (HomeCommendViewHolder) holder;
        HomeKnowCommend homeKnowCommend = homeKnowCommends.get(position);
        super.onBindViewHolder(holder,position);
    }

    @Override
    public int getItemCount() {
        return homeKnowCommends.size();
    }

    public class HomeCommendViewHolder extends CommonViewHolder{
        private HomeCommendViewHolder(View view){
            super(view);
        }
    }
}
