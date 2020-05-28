package com.vargancys.learningassistant.module.mine.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flyco.tablayout.CommonTabLayout;
import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.db.ladder.LadderDataBean;

import java.util.List;

import butterknife.BindView;

/**
 * @author Vagrancy
 * @date 2020/5/28
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 个人中心天梯各项适配器
 */
public class ChallengeItemAdapter extends BaseRecyclerAdapter {
    private List<LadderDataBean> mBean;
    private Context mContext;
    public ChallengeItemAdapter(Context context, List<LadderDataBean> bean){
        mBean = bean;
        mContext =context;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        ChallengeItemViewHolder mHolder = (ChallengeItemViewHolder) holder;
        LadderDataBean bean = mBean.get(position);
        mHolder.challengeItemNumber.setText(String.valueOf(bean.getId()));
        mHolder.challengeItemTime.setText(String.valueOf(bean.getTime()));
        mHolder.challengeItemTitle.setText(String.valueOf(bean.getName()));
        super.onBindViewHolder(holder, position);
    }

    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ChallengeItemViewHolder(View.inflate(mContext, R.layout.challenge_item_item,viewGroup));
    }

    @Override
    public int getItemCount() {
        return mBean.size();
    }

    public class ChallengeItemViewHolder extends CommonViewHolder{
        @BindView(R.id.challenge_item_title)
        TextView challengeItemTitle;
        @BindView(R.id.challenge_item_number)
        TextView challengeItemNumber;
        @BindView(R.id.challenge_item_time)
        TextView challengeItemTime;
        public ChallengeItemViewHolder(View itemView){
            super(itemView);
        }
    }
}
