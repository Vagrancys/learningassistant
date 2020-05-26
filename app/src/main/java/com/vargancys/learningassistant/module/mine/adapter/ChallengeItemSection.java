package com.vargancys.learningassistant.module.mine.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.model.mine.bean.ChallengeItemBean;
import com.vargancys.learningassistant.module.mine.activity.ChallengeDetailsActivity;
import com.vargancys.learningassistant.module.mine.activity.ChallengeItemActivity;
import com.vargancys.learningassistant.widget.section.SectionedRecyclerViewAdapter;
import com.vargancys.learningassistant.widget.section.StatelessSection;

import butterknife.BindView;

/**
 * @author Vagrancy
 * @date 2020/5/26
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 个人中心天梯各项挑战section
 */
public class ChallengeItemSection extends StatelessSection {

    private Context mContext;
    private ChallengeItemBean mBean;

    private Activity mActivity;
    public ChallengeItemSection(Context context, Activity activity, ChallengeItemBean bean) {
        super(R.layout.challenge_item_header, R.layout.challenge_item);
        mContext = context;
        mBean = bean;
        mActivity = activity;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        ChallengeHeaderViewHolder mHolder = (ChallengeHeaderViewHolder) holder;
        mHolder.challengeTitle.setText(mBean.getTitle());
        mHolder.challengeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChallengeItemActivity.launch(mActivity,mBean.getType());
            }
        });
        mHolder.challengeCount.setText(mBean.getCount());
        mHolder.challengeSuccess.setText(mBean.getSuccess());
        mHolder.challengeTime.setText(String.valueOf(mBean.getTime()));
        mHolder.challengeDifficulty.setText(String.valueOf(mBean.getDifficulty()));
        mHolder.challengeFail.setText(String.valueOf(mBean.getFail()));
        mHolder.challengeHighest.setText(String.valueOf(mBean.getHighest()));
        mHolder.challengeNumber.setText(String.valueOf(mBean.getNumber()));

        super.onBindHeaderViewHolder(holder);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        ChallengeItemViewHolder mHolder = (ChallengeItemViewHolder) holder;
        final ChallengeItemBean.ChallengeItem mItem = mBean.getItems().get(position);
        mHolder.getParentView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChallengeDetailsActivity.launch(mActivity,mItem.getId());
            }
        });
        mHolder.challengeSerial.setText(String.valueOf(mItem.getSerial()));
        mHolder.challengeTitle.setText(mItem.getTitle());
        mHolder.challengeHighest.setText(String.valueOf(mItem.getHighest()));
        if(mItem.isSituation()){
            mHolder.challengeSituation.setText("成");
        }else{
            mHolder.challengeSituation.setText("失");
        }

        mHolder.challengeSummary.setText(mItem.getSummary());
        mHolder.challengeTime.setText(String.valueOf(mItem.getTime()));
        super.onBindItemViewHolder(holder, position);
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new ChallengeHeaderViewHolder(view);
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new ChallengeItemViewHolder(view);
    }

    @Override
    public int getContentItemsTotal() {
        return mBean.getItems().size();
    }

    public static class ChallengeHeaderViewHolder extends SectionedRecyclerViewAdapter.EmptyViewHolder {
        @BindView(R.id.challenge_title)
        TextView challengeTitle;
        @BindView(R.id.challenge_more)
        TextView challengeMore;
        @BindView(R.id.challenge_count)
        TextView challengeCount;
        @BindView(R.id.challenge_success)
        TextView challengeSuccess;
        @BindView(R.id.challenge_time)
        TextView challengeTime;
        @BindView(R.id.challenge_fail)
        TextView challengeFail;
        @BindView(R.id.challenge_highest)
        TextView challengeHighest;
        @BindView(R.id.challenge_number)
        TextView challengeNumber;
        @BindView(R.id.challenge_difficulty)
        TextView challengeDifficulty;
        public ChallengeHeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    public static class ChallengeItemViewHolder extends SectionedRecyclerViewAdapter.EmptyViewHolder {
        @BindView(R.id.challenge_serial)
        TextView challengeSerial;
        @BindView(R.id.challenge_title)
        TextView challengeTitle;
        @BindView(R.id.challenge_summary)
        TextView challengeSummary;
        @BindView(R.id.challenge_situation)
        TextView challengeSituation;
        @BindView(R.id.challenge_highest)
        TextView challengeHighest;
        @BindView(R.id.challenge_time)
        TextView challengeTime;
        public ChallengeItemViewHolder(View itemView) {
            super(itemView);
        }
    }
}
