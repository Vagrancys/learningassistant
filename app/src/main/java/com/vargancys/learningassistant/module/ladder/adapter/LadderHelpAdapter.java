package com.vargancys.learningassistant.module.ladder.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.bean.ladder.LadderHelpBean;

import java.util.List;

import butterknife.BindView;

/**
 * @author Vagrancy
 * @date 2020/5/11
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 帮助中心的适配器
 */
public class LadderHelpAdapter extends BaseRecyclerAdapter {
    private List<LadderHelpBean> mBean;
    public LadderHelpAdapter(Context context,List<LadderHelpBean> bean){
        super(context);
        mBean = bean;
    }

    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new LadderHelpViewHolder(getView(R.layout.ladder_help_item));
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        LadderHelpBean bean = mBean.get(position);
        LadderHelpViewHolder mHolder = (LadderHelpViewHolder) holder;
        mHolder.ladderHelpTitle.setText(bean.getTitle());
        mHolder.ladderHelpNumber.setText(bean.getNumber());
        mHolder.ladderHelpSummary.setText(bean.getSummary());
        mHolder.ladderHelpTime.setText(bean.getTitle());
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return mBean.size();
    }

    public class LadderHelpViewHolder extends CommonViewHolder{
        @BindView(R.id.ladder_help_title)
        TextView ladderHelpTitle;
        @BindView(R.id.ladder_help_number)
        TextView ladderHelpNumber;
        @BindView(R.id.ladder_help_summary)
        TextView ladderHelpSummary;
        @BindView(R.id.ladder_help_time)
        TextView ladderHelpTime;
        private LadderHelpViewHolder(View itemView){
            super(itemView);
        }
    }
}
