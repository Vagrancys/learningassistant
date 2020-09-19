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
import com.vargancys.learningassistant.bean.ladder.LadderDifficultyCommentBean;

import java.util.List;

import butterknife.BindView;

/**
 * @author Vagrancy
 * @date 2020/5/11
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 难度区评论的适配器
 */
public class DifficultyCommentAdapter extends BaseRecyclerAdapter {
    private List<LadderDifficultyCommentBean> mBean;
    public DifficultyCommentAdapter(Context context, List<LadderDifficultyCommentBean> bean){
        super(context);
        mBean = bean;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        DifficultyCommentViewHolder mHolder = (DifficultyCommentViewHolder) holder;
        LadderDifficultyCommentBean bean = mBean.get(position);
        mHolder.commentAuthor.setText(bean.getAuthor_title());
        mHolder.commentContent.setText(bean.getComment());
        mHolder.commentFloorCount.setText(String.valueOf(bean.getFloor()));
        mHolder.commentTime.setText(bean.getTime());
        mHolder.commentLevel.setText(String.valueOf(bean.getLevel()));
        Glide.with(getContext()).load(bean.getAuthor()).into(mHolder.commentAvatar);
        super.onBindViewHolder(holder, position);
    }

    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new DifficultyCommentViewHolder(getView(R.layout.difficulty_comment_item));
    }

    @Override
    public int getItemCount() {
        return mBean.size();
    }

    public class DifficultyCommentViewHolder extends CommonViewHolder{
        @BindView(R.id.comment_avatar)
        ImageView commentAvatar;
        @BindView(R.id.comment_author)
        TextView commentAuthor;
        @BindView(R.id.comment_level)
        TextView commentLevel;
        @BindView(R.id.comment_content)
        TextView commentContent;
        @BindView(R.id.comment_floor_count)
        TextView commentFloorCount;
        @BindView(R.id.comment_time)
        TextView commentTime;
        private DifficultyCommentViewHolder(View itemView){
            super(itemView);
        }
    }
}
