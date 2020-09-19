package com.vargancys.learningassistant.module.ladder.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.bean.ladder.LadderCommentReplyBean;

import java.util.List;

import butterknife.BindView;

/**
 * @author Vagrancy
 * @date 2020/5/9
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 评论回复适配器
 */
public class CommentReplyAdapter extends BaseRecyclerAdapter {
    private List<LadderCommentReplyBean> mBean;
    public CommentReplyAdapter(Context context, List<LadderCommentReplyBean> mBean){
        super(context);
        this.mBean = mBean;
    }
    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CommentReplyViewHolder(getView(R.layout.comment_reply_item));
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        CommentReplyViewHolder mHolder = (CommentReplyViewHolder) holder;
        LadderCommentReplyBean bean = mBean.get(position);
        Glide.with(getContext()).load(bean.getAvatar()).into(mHolder.commentAvatar);
        mHolder.commentAuthor.setText(bean.getAuthor_title());
        mHolder.commentContent.setText(bean.getComment());
        mHolder.commentLevel.setText(bean.getLevel());
        mHolder.commentTime.setText(bean.getTime());
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return mBean.size();
    }

    public class CommentReplyViewHolder extends CommonViewHolder{
        @BindView(R.id.comment_avatar)
        ImageView commentAvatar;
        @BindView(R.id.comment_author)
        TextView commentAuthor;
        @BindView(R.id.comment_level)
        TextView commentLevel;
        @BindView(R.id.comment_content)
        TextView commentContent;
        @BindView(R.id.comment_time)
        TextView commentTime;
        private CommentReplyViewHolder(View itemView){
            super(itemView);
        }
    }
}
