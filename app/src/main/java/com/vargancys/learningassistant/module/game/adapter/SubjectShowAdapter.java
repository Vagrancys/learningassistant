package com.vargancys.learningassistant.module.game.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.db.game.GameSubjectItem;

import java.util.List;

import butterknife.ButterKnife;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/12
 * version:1.0
 */
public class SubjectShowAdapter extends BaseRecyclerAdapter {
    private List<GameSubjectItem> mItems;
    private Context mContext;
    private Handler mHandler;
    public SubjectShowAdapter(Context context, List<GameSubjectItem> mItems, Handler mHandler){
        this.mHandler = mHandler;
        this.mContext = context;
        this.mItems = mItems;
    }
    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class SubjectShowViewHolder extends CommonViewHolder{
        public SubjectShowViewHolder(View view){
            super(view);
            ButterKnife.bind(view);
        }
    }
}
