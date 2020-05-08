package com.vargancys.learningassistant.module.ladder.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.db.ladder.LadderCommentBean;

import java.util.List;

import butterknife.BindView;

/**
 * @author Vagrancy
 * @date 2020/5/8
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 评论适配器
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
    public void onBindViewHolder(CommonViewHolder holder, final int position) {
        final CommunicationViewHolder mHolder = (CommunicationViewHolder) holder;
        final LadderCommentBean bean = mBean.get(position);
        Glide.with(mContext).load(bean.getAvatar()).into(mHolder.commentAvatar);
        mHolder.commentAuthor.setText(bean.getAuthor_title());
        mHolder.commentLevel.setText(bean.getLevel());
        mHolder.commentPraiseCount.setText(String.valueOf(bean.getPraise()));
        mHolder.commentStepCount.setText(String.valueOf(bean.getStep()));
        mHolder.commentContent.setText(bean.getComment());
        mHolder.commentFloorCount.setText(String.valueOf(bean.getFloor()));
        mHolder.commentReplyCount.setText(String.valueOf(bean.getReply_count()));
        mHolder.commentTime.setText(bean.getTime());
        mHolder.praiseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mHolder.commentPraiseImg.getBackground() == mContext.getResources().getDrawable(R.drawable.comment_praise_selected_normal)){
                    mHolder.commentPraiseImg.setImageResource(R.drawable.comment_praise_unselected_normal);
                    mHolder.commentPraiseCount.setText(bean.getPraise()-1);
                    if(onCommentClickListener != null){
                        onCommentClickListener.onPraiseUnPressure(position);
                    }
                }else{
                    mHolder.commentPraiseImg.setImageResource(R.drawable.comment_praise_selected_normal);
                    mHolder.commentPraiseCount.setText(bean.getPraise()+1);
                    if(onCommentClickListener != null){
                        onCommentClickListener.onPraisePressure(position);
                    }
                }
            }
        });
        mHolder.stepLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mHolder.commentStepImg.getBackground() == mContext.getResources().getDrawable(R.drawable.comment_step_selected_normal)){
                    mHolder.commentStepImg.setImageResource(R.drawable.comment_step_unselected_normal);
                    mHolder.commentStepCount.setText(bean.getStep()-1);
                    if(onCommentClickListener != null){
                        onCommentClickListener.onStepUnPressure(position);
                    }
                }else{
                    mHolder.commentStepImg.setImageResource(R.drawable.comment_step_selected_normal);
                    mHolder.commentStepCount.setText(bean.getStep()+1);
                    if(onCommentClickListener != null){
                        onCommentClickListener.onStepPressure(position);
                    }
                }
            }
        });
        mHolder.replyLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onCommentClickListener != null){
                    onCommentClickListener.onReplyPressure(position);
                }
            }
        });
        super.onBindViewHolder(holder, position);
    }

    public interface OnCommentClickListener{
        void onPraisePressure(int position);
        void onPraiseUnPressure(int position);
        void onStepPressure(int position);
        void onStepUnPressure(int position);
        void onReplyPressure(int position);
    }

    public OnCommentClickListener onCommentClickListener;

    public void setOnCommentClickListener(OnCommentClickListener onCommentClickListener) {
        this.onCommentClickListener = onCommentClickListener;
    }

    public class CommunicationViewHolder extends CommonViewHolder{
        @BindView(R.id.comment_avatar)
        ImageView commentAvatar;
        @BindView(R.id.comment_author)
        TextView commentAuthor;
        @BindView(R.id.comment_level)
        TextView commentLevel;
        @BindView(R.id.praise_layout)
        LinearLayout praiseLayout;
        @BindView(R.id.comment_praise_img)
        ImageView commentPraiseImg;
        @BindView(R.id.comment_praise_count)
        TextView commentPraiseCount;
        @BindView(R.id.step_layout)
        LinearLayout stepLayout;
        @BindView(R.id.comment_step_img)
        ImageView commentStepImg;
        @BindView(R.id.comment_step_count)
        TextView commentStepCount;
        @BindView(R.id.comment_content)
        TextView commentContent;
        @BindView(R.id.comment_floor_count)
        TextView commentFloorCount;
        @BindView(R.id.reply_layout)
        LinearLayout replyLayout;
        @BindView(R.id.comment_reply_count)
        TextView commentReplyCount;
        @BindView(R.id.comment_time)
        TextView commentTime;
        private CommunicationViewHolder(View itemView){
            super(itemView);
        }
    }
}
