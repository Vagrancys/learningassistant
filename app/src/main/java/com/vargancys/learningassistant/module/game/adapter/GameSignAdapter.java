package com.vargancys.learningassistant.module.game.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.bean.game.GameSignContent;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Vagrancy
 * @date 2020/4/11
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 关卡签到适配器
 */
public class GameSignAdapter extends BaseRecyclerAdapter {
    private List<GameSignContent> mSigns;
    private Animation mUpScaleAnimation;
    private Animation mDownScaleAnimation;
    private Animation mUpRotateAnimation;
    private Animation mDownRotateAnimation;
    private Handler mHandler;
    public GameSignAdapter(Context context, List<GameSignContent> mSigns, Handler mHandler){
        super(context);
        this.mSigns = mSigns;
        this.mHandler = mHandler;
        mUpScaleAnimation = AnimationUtils.loadAnimation(getContext(),R.anim.common_scale_top_anim);
        mDownScaleAnimation = AnimationUtils.loadAnimation(getContext(),R.anim.common_scale_buttom_anim);
        mUpRotateAnimation = AnimationUtils.loadAnimation(getContext(),R.anim.common_rotate_buttom_anim);
        mDownRotateAnimation = AnimationUtils.loadAnimation(getContext(),R.anim.common_rotate_top_anim);
    }
    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new GameSignViewHolder(getView(R.layout.game_sign_item));
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        final GameSignViewHolder mHolder = (GameSignViewHolder) holder;
        GameSignContent mSign = mSigns.get(position);
        mHolder.signTitle.setText(mSign.getTitle());
        mHolder.signTime.setText(mSign.getTime());
        mHolder.signSummary.setText(mSign.getSummary());
        mHolder.signGameTitle.setText(mSign.getGame_title());
        int res = 0;
        switch (mSign.getLevel()){
            case 0:
                res = R.drawable.know_level_0;
                break;
            case 1:
                res = R.drawable.know_level_1;
                break;
            case 2:
                res = R.drawable.know_level_2;
                break;
            case 3:
                res = R.drawable.know_level_3;
                break;
            case 4:
                res = R.drawable.know_level_4;
                break;
            case 5:
                res = R.drawable.know_level_5;
                break;
        }
        mHolder.signGameLevel.setImageResource(res);
        mUpScaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mHolder.signLayout.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        final Runnable mRunnable = new Runnable() {
            @Override
            public void run() {
                mHolder.signIndicate.setText(getString(R.string.sign_show_indicate));
                mHolder.signIndicateImg.startAnimation(mUpRotateAnimation);
                mHolder.signLayout.startAnimation(mUpScaleAnimation);
            }
        };
        mHolder.indicateLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler.removeCallbacks(mRunnable);
                if(mHolder.signLayout.getVisibility() == View.VISIBLE){
                    mHolder.signIndicate.setText(getString(R.string.sign_show_indicate));
                    mHolder.signIndicateImg.startAnimation(mUpRotateAnimation);
                    mHolder.signLayout.startAnimation(mUpScaleAnimation);
                }else{
                    mHolder.signIndicate.setText(getString(R.string.sign_hide_indicate));
                    mHolder.signIndicateImg.startAnimation(mDownRotateAnimation);
                    mHolder.signLayout.startAnimation(mDownScaleAnimation);
                    mHolder.signLayout.setVisibility(View.VISIBLE);
                    mHandler.postDelayed(mRunnable,2000);
                }
            }
        });
        mHolder.gameSubjectSchedule.setText(mSign.getGame_subject_current()+"题/总"+mSign.getGame_subject_total()+"题");
        mHolder.gameSubjectProgress.setProgress(mSign.getGame_subject_current());
        mHolder.gameSubjectProgress.setMax(mSign.getGame_subject_total());

        mHolder.gameScoreSchedule.setText(mSign.getGame_score_current()+"分/总"+mSign.getGame_score_total()+"分");
        mHolder.gameScoreProgress.setProgress(mSign.getGame_score_current());
        mHolder.gameScoreProgress.setMax(mSign.getGame_score_total());

        mHolder.gameErrorSchedule.setText("复习"+mSign.getGame_error_current()+"道/总"+mSign.getGame_error_total()+"道");
        mHolder.gameErrorProgress.setProgress(mSign.getGame_error_current());
        mHolder.gameErrorProgress.setMax(mSign.getGame_error_total());
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return mSigns.size();
    }

    public class GameSignViewHolder extends CommonViewHolder{
        @BindView(R.id.sign_time)
        TextView signTime;
        @BindView(R.id.sign_title)
        TextView signTitle;
        @BindView(R.id.sign_summary)
        TextView signSummary;
        @BindView(R.id.indicate_layout)
        LinearLayout indicateLayout;
        @BindView(R.id.sign_indicate)
        TextView signIndicate;
        @BindView(R.id.sign_indicate_img)
        ImageView signIndicateImg;
        @BindView(R.id.sign_game_title)
        TextView signGameTitle;
        @BindView(R.id.sign_game_level)
        ImageView signGameLevel;
        @BindView(R.id.sign_layout)
        LinearLayout signLayout;
        @BindView(R.id.game_subject_schedule)
        TextView gameSubjectSchedule;
        @BindView(R.id.game_subject_progress)
        ProgressBar gameSubjectProgress;
        @BindView(R.id.game_score_schedule)
        TextView gameScoreSchedule;
        @BindView(R.id.game_score_progress)
        ProgressBar gameScoreProgress;
        @BindView(R.id.game_error_schedule)
        TextView gameErrorSchedule;
        @BindView(R.id.game_error_progress)
        ProgressBar gameErrorProgress;
        public GameSignViewHolder(View view){
            super(view);
            ButterKnife.bind(view);
        }
    }
}
