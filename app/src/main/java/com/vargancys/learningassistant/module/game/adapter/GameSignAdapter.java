package com.vargancys.learningassistant.module.game.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.db.game.GameSignContent;

import java.util.List;

import butterknife.ButterKnife;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/11
 * version:1.0
 */
public class GameSignAdapter extends BaseRecyclerAdapter {
    private Context mContext;
    private List<GameSignContent> mSigns;
    public GameSignAdapter(Context context,List<GameSignContent> mSigns){
        mContext = context;
        this.mSigns = mSigns;
    }
    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new GameSignViewHolder(View.inflate(mContext, R.layout.game_sign_item,null));
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        GameSignViewHolder mHandler = (GameSignViewHolder) holder;
        GameSignContent mSign = mSigns.get(position);
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return mSigns.size();
    }

    public class GameSignViewHolder extends CommonViewHolder{
        public GameSignViewHolder(View view){
            super(view);
            ButterKnife.bind(view);
        }
    }
}
