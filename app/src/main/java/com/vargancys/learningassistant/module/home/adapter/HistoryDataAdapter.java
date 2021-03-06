package com.vargancys.learningassistant.module.home.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseRecyclerAdapter;
import com.vargancys.learningassistant.bean.home.HomeKnowHistory;

import java.util.List;

import butterknife.BindView;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/28
 * version:1.0
 * 历史数据适配器
 */
public class HistoryDataAdapter extends BaseRecyclerAdapter {

    private static final long DELAYED_TIME = 3000;
    private String TAG = "HistoryDataAdapter";
    private List<HomeKnowHistory> homeKnowHistorys;
    private Animation mTopRotateAnimation;
    private Animation mBottomRotateAnimation;
    private Animation mTopScaleAnimation;
    private Animation mBottomScaleAnimation;
    private Handler mHandler;
    public HistoryDataAdapter(Context context, Handler handler,List<HomeKnowHistory> items) {
        super(context);
        this.homeKnowHistorys = items;
        mTopRotateAnimation = AnimationUtils.loadAnimation(getContext(),R.anim.common_rotate_top_anim);
        mBottomRotateAnimation = AnimationUtils.loadAnimation(getContext(),R.anim.common_rotate_buttom_anim);
        mTopScaleAnimation = AnimationUtils.loadAnimation(getContext(),R.anim.common_scale_top_anim);
        mBottomScaleAnimation = AnimationUtils.loadAnimation(getContext(),R.anim.common_scale_buttom_anim);
        mHandler = handler;
    }

    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new HistoryDataViewHolder(getView(R.layout.history_data_item));
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        final HistoryDataViewHolder mHolder = (HistoryDataViewHolder) holder;
        final HomeKnowHistory homeKnowHistory = homeKnowHistorys.get(position);
        mHolder.historyDataNumber.setText(String.valueOf(homeKnowHistory.getId()));
        mHolder.historyDataTime.setText(homeKnowHistory.getTime());
        mHolder.historyDataTitle.setText(homeKnowHistory.getTitle());
        mHolder.historyDataSummary.setText(homeKnowHistory.getSummary());
        mHolder.historyAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Runnable mRunnable = new Runnable() {
                    @Override
                    public void run() {
                        mHolder.historyAnimLayout.startAnimation(mTopScaleAnimation);
                        mHolder.historyAnimImg.startAnimation(mBottomRotateAnimation);
                        mHolder.historyAnimLayout.setVisibility(View.GONE);
                        mHolder.historyAnimTitle.setText(getString(R.string.history_anim_show_title));
                    }
                };
                mHandler.removeCallbacksAndMessages(null);
                if(mHolder.historyAnimLayout.getVisibility() == View.GONE){
                    initData(mHolder,homeKnowHistory);
                    mHolder.historyAnimLayout.setVisibility(View.VISIBLE);
                    mHolder.historyAnimLayout.startAnimation(mBottomScaleAnimation);
                    mHolder.historyAnimImg.startAnimation(mTopRotateAnimation);
                    mHolder.historyAnimTitle.setText(getString(R.string.history_anim_hide_title));
                    mHandler.postDelayed(mRunnable,DELAYED_TIME);
                }else{
                    mHolder.historyAnimLayout.startAnimation(mTopScaleAnimation);
                    mHolder.historyAnimImg.startAnimation(mBottomRotateAnimation);
                    mHolder.historyAnimLayout.setVisibility(View.GONE);
                    mHolder.historyAnimTitle.setText(getString(R.string.history_anim_show_title));
                }
            }
        });
        super.onBindViewHolder(holder, position);
    }

    private void initData(HistoryDataViewHolder mHolder, HomeKnowHistory homeKnowHistory) {
        mHolder.historyDataShow.setText(homeKnowHistory.getShow());
        mHolder.historyDataExplain.setText(homeKnowHistory.getExplain());
        mHolder.historyDataExperience.setText(homeKnowHistory.getExperience());
    }

    @Override
    public int getItemCount() {
        return homeKnowHistorys.size();
    }

    public class HistoryDataViewHolder extends CommonViewHolder {
        @BindView(R.id.history_data_number)
        TextView historyDataNumber;
        @BindView(R.id.history_data_time)
        TextView historyDataTime;
        @BindView(R.id.history_data_title)
        TextView historyDataTitle;
        @BindView(R.id.history_data_summary)
        TextView historyDataSummary;
        @BindView(R.id.history_data_show)
        TextView historyDataShow;
        @BindView(R.id.history_data_explain)
        TextView historyDataExplain;
        @BindView(R.id.history_data_experience)
        TextView historyDataExperience;
        @BindView(R.id.history_anim_layout)
        LinearLayout historyAnimLayout;
        @BindView(R.id.history_anim_title)
        TextView historyAnimTitle;
        @BindView(R.id.history_anim_img)
        ImageView historyAnimImg;
        @BindView(R.id.history_anim)
        LinearLayout historyAnim;
        private HistoryDataViewHolder(View view) {
            super(view);
        }
    }
}
