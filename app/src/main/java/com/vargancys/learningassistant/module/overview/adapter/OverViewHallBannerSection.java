package com.vargancys.learningassistant.module.overview.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.model.overview.bean.OverViewHallBean;
import com.vargancys.learningassistant.widget.section.SectionedRecyclerViewAdapter;
import com.vargancys.learningassistant.widget.section.StatelessSection;

import java.util.List;

import butterknife.BindView;

/**
 * @author Vagrancy
 * @date 2020/6/27
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识体系大厅轮播适配器
 */
public class OverViewHallBannerSection extends StatelessSection {
    private List<OverViewHallBean.Banner> mBean;
    private BannerViewPager mPager;
    private Handler mHandler;
    private Runnable mRunnable;
    public OverViewHallBannerSection(Context context,Handler handler, List<OverViewHallBean.Banner> bean){
        super(context,R.layout.overview_hall_banner_item);
        mBean = bean;
        mHandler =handler;
    };

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        final OverViewHallBannerViewHolder mHolder = (OverViewHallBannerViewHolder) holder;
        mPager = new BannerViewPager(getContext(),mBean);
        mHolder.viewPager.setAdapter(mPager);
        mHolder.viewPager.setOffscreenPageLimit(mBean.size());
        mHolder.viewPager.setCurrentItem(0);
        mRunnable = new Runnable() {
            @Override
            public void run() {
                mHandler.removeCallbacksAndMessages(mRunnable);
                mHolder.viewPager.setCurrentItem(mHolder.viewPager.getCurrentItem()+1);
                mHandler.postDelayed(mRunnable,1000);
            }
        };
        mHandler.post(mRunnable);
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new OverViewHallBannerViewHolder(view);
    }

    @Override
    public int getContentItemsTotal() {
        return 1;
    }

    public static class OverViewHallBannerViewHolder extends SectionedRecyclerViewAdapter.EmptyViewHolder {
        @BindView(R.id.viewPager)
        ViewPager viewPager;
        public OverViewHallBannerViewHolder(View itemView){
            super(itemView);
        }
    }

    private static class BannerViewPager extends PagerAdapter{
        private List<OverViewHallBean.Banner> mBean;
        private View[] mView;
        private Context mContext;
        private int mSize;
        public BannerViewPager(Context context,List<OverViewHallBean.Banner> mBean){
            this.mBean = mBean;
            mContext = context;
            mSize = mBean.size();
            mView = new View[mBean.size()];
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            super.destroyItem(container, position, object);
            container.removeView((View) object);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            int current = position/mSize;
            if(mView[current] == null){
                View view = View.inflate(mContext,R.layout.item_overview_banner_viewpager,null);
                mView[current] = initViewData(view,mBean.get(current));
            }
            container.addView(mView[current]);
            return mView[current];
        }

        private View initViewData(View view, OverViewHallBean.Banner banner) {
            ImageView img = view.findViewById(R.id.banner_img);
            Glide.with(mContext).load(banner.getImgUri()).into(img);
            TextView title = view.findViewById(R.id.banner_title);
            title.setText(banner.getTitle());
            TextView summary = view.findViewById(R.id.banner_summary);
            summary.setText(banner.getSummary());
            return view;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }
    }
}
