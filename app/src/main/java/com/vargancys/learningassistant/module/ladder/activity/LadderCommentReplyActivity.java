package com.vargancys.learningassistant.module.ladder.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.db.ladder.LadderCommentBean;
import com.vargancys.learningassistant.db.ladder.LadderCommentReplyBean;
import com.vargancys.learningassistant.module.ladder.adapter.CommentReplyAdapter;
import com.vargancys.learningassistant.module.ladder.view.LadderCommentReplyView;
import com.vargancys.learningassistant.presenter.ladder.BaseLadderPresenter;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.ResourceUtils;
import com.vargancys.learningassistant.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Vagrancy
 * @date 2020/5/8
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 评论回复中心
 */
public class LadderCommentReplyActivity extends BaseActivity implements LadderCommentReplyView {
    @BindView(R.id.comment_avatar)
    ImageView commentAvatar;
    @BindView(R.id.comment_author)
    TextView commentAuthor;
    @BindView(R.id.comment_level)
    TextView commentLevel;
    @BindView(R.id.comment_content)
    TextView commentContent;
    @BindView(R.id.comment_reply_count)
    TextView commentReplyCount;
    @BindView(R.id.comment_time)
    TextView commentTime;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.comment_edit)
    EditText commentEdit;
    @BindView(R.id.comment_send)
    ImageView commentSend;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.common_back)
    ImageView commonBack;
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.common_img)
    ImageView commonImg;
    private BaseLadderPresenter mPresenter;

    private long commentId;
    private CommentReplyAdapter mAdapter;
    private List<LadderCommentReplyBean> mReplyBean = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_comment_reply;
    }

    @Override
    public void initView() {
        commentId = getIntent().getLongExtra(ConstantsUtils.COMMENT_ID, 0);
        mPresenter = new BaseLadderPresenter(this);
        initData();
    }

    private void initData() {
        mPresenter.getLadderCommentData(commentId);
        mAdapter = new CommentReplyAdapter(getContext(), mReplyBean);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
        swipeRefresh.setColorSchemeColors(ResourceUtils.getColor(getContext(),R.color.pink));
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                autoRefresh();
            }
        });

        commentSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (commentEdit.getText().length() > 0) {
                    mPresenter.saveCommentReplyData(commentId, commentEdit.getText().toString());
                } else {
                    ToastUtils.ToastText(getBaseContext(), ResourceUtils.getString(getContext(),R.string.comment_data_empty_text));
                }
            }
        });
    }

    @Override
    public void initToolbar() {
        commonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        commonTitle.setText(ResourceUtils.getString(getContext(),R.string.comment_reply_toolbar));

        commonImg.setVisibility(View.GONE);
    }

    private void autoRefresh() {
        swipeRefresh.setRefreshing(true);
        mPresenter.getCommentReplyAllData(commentId);
    }

    public static void launch(Activity activity, long commentId) {
        Intent intent = new Intent(activity, LadderCommentReplyActivity.class);
        intent.putExtra(ConstantsUtils.COMMENT_ID, commentId);
        activity.startActivity(intent);
    }

    @Override
    public void showCommentDataFinish(LadderCommentBean mBean) {
        Glide.with(getContext()).load(mBean).into(commentAvatar);
        commentAuthor.setText(mBean.getAuthor_title());
        commentContent.setText(mBean.getComment());
        commentLevel.setText(mBean.getLevel());
        commentTime.setText(mBean.getTime());
        commentReplyCount.setText(mBean.getReply_count());
    }

    @Override
    public void showSaveCommentDataFinish() {
        ToastUtils.ToastText(getContext(), ResourceUtils.getString(getContext(),R.string.comment_save_data_finish_text));
        autoRefresh();
    }

    @Override
    public void showSaveCommentDataError(int error, String message) {
        ToastUtils.ToastText(getContext(), ResourceUtils.getString(getContext(),R.string.comment_save_data_error_text));
        commentEdit.setText("");
    }

    @Override
    public void showCommentAllDataFinish(List<LadderCommentReplyBean> mBeans) {
        this.mReplyBean = mBeans;
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showCommentAllDataError(int error, String message) {
        ToastUtils.ToastText(getContext(), "Error =" + error + ", Message =" + message);
    }

    @Override
    public void showCommentDataError(int error, String message) {
        ToastUtils.ToastText(getContext(), ResourceUtils.getString(getContext(),R.string.comment_empty_text));
        commentAuthor.setText("--");
        commentContent.setText("--");
        commentLevel.setText("--");
        commentTime.setText("--");
        commentReplyCount.setText("0");
    }
}
