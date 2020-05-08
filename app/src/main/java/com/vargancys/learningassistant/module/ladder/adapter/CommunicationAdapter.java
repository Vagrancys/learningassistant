package com.vargancys.learningassistant.module.ladder.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.db.ladder.LadderCommentBean;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2020/5/8
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description:
 */
public class CommunicationAdapter extends BaseRecyclerAdapter {
    private Context mContext;
    private List<LadderCommentBean> mBean;
    public CommunicationAdapter(Context context, List<LadderCommentBean> mBean){
        mContext = context;
        this.mBean = mBean;
    }

    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        return new CommunicationViewHolder(View.inflate(mContext, R.layout.communication_comment_item,null));
    }

    @Override
    public int getItemCount() {
        return mBean.size();
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {

        super.onBindViewHolder(holder, position);
    }

    public class CommunicationViewHolder extends CommonViewHolder{
        public CommunicationViewHolder(View itemView){
            super(itemView);
        }
    }
}
