package com.vargancys.learningassistant.module.mine.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.db.game.GameStartContent;
import com.vargancys.learningassistant.model.mine.bean.KnowLedgeItemBean;
import com.vargancys.learningassistant.module.home.activity.show.KnowShowDefaultActivity;
import com.vargancys.learningassistant.module.home.activity.show.KnowShowFifthActivity;
import com.vargancys.learningassistant.module.home.activity.show.KnowShowFirstActivity;
import com.vargancys.learningassistant.module.home.activity.show.KnowShowFourthActivity;
import com.vargancys.learningassistant.module.home.activity.show.KnowShowSecondActivity;
import com.vargancys.learningassistant.module.home.activity.show.KnowShowThirdActivity;
import com.vargancys.learningassistant.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;

/**
 * @author Vagrancy
 * @date 2020/6/8
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 个人中心问题各项适配器
 */
public class ProblemItemAdapter extends BaseRecyclerAdapter {
    private List<KnowLedgeItemBean.KnowLedgeItem> mProblem;
    private Activity mActivity;
    public ProblemItemAdapter(Context context, Activity activity,List<KnowLedgeItemBean.KnowLedgeItem> mProblem){
        super(context);
        this.mProblem = mProblem;
        this.mActivity = activity;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        ProblemItemViewHolder mHolder = (ProblemItemViewHolder) holder;
        final KnowLedgeItemBean.KnowLedgeItem mItem = mProblem.get(position);
        mHolder.problemType.setText(mItem.getType());
        mHolder.problemLevel.setText(String.valueOf(mItem.getNumber()));
        mHolder.knowledgeLevel.setImageResource(mItem.getLevel());
        mHolder.problemTitle.setText(mItem.getTitle());
        mHolder.problemUse.setText(String.valueOf(mItem.getLook()));
        mHolder.problemPeople.setText(String.valueOf(mItem.getProblem()));
        mHolder.problemSummary.setText(mItem.getSummary());
        mHolder.problemTime.setText(String.valueOf(mItem.getTime()));
        super.onBindViewHolder(holder, position);
    }

    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ProblemItemViewHolder(getView(R.layout.problem_item));
    }

    @Override
    public int getItemCount() {
        return mProblem.size();
    }

    public class ProblemItemViewHolder extends CommonViewHolder{
        @BindView(R.id.problem_level)
        TextView problemLevel;
        @BindView(R.id.problem_title)
        TextView problemTitle;
        @BindView(R.id.problem_type)
        TextView problemType;
        @BindView(R.id.problem_context)
        TextView problemSummary;
        @BindView(R.id.knowledge_level)
        ImageView knowledgeLevel;
        @BindView(R.id.problem_people)
        TextView problemPeople;
        @BindView(R.id.problem_use)
        TextView problemUse;
        @BindView(R.id.problem_time)
        TextView problemTime;
        private ProblemItemViewHolder(View itemView){
            super(itemView);
        }
    }
}
