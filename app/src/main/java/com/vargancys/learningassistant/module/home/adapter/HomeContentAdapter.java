package com.vargancys.learningassistant.module.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;

import butterknife.ButterKnife;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/02
 * version:1.0
 */
public class HomeContentAdapter extends BaseRecyclerAdapter {
    private Context context;
    private String contentBean;
    public HomeContentAdapter(Context context,String contentBean){
        this.contentBean = contentBean;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new HomeContentViewHolder(View.inflate(context, R.layout.home_content_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        HomeContentViewHolder viewHolder = (HomeContentViewHolder) holder;
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    private class HomeContentViewHolder extends CommonViewHolder{
        private HomeContentViewHolder(View view){
            super(view);
            ButterKnife.bind(view);
        }
    }
}
