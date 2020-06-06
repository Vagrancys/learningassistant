package com.vargancys.learningassistant.module.common;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.base.BaseActivity;
import com.vargancys.learningassistant.utils.CacheUtils;
import com.vargancys.learningassistant.utils.ConstantsUtils;
import com.vargancys.learningassistant.utils.DensityUtils;
import com.vargancys.learningassistant.utils.ResourceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/01
 * version:1.0
 * 轮播图页面
 */
public class GroupActivity extends BaseActivity {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.group_point)
    ImageView groupPoint;
    @BindView(R.id.group_linear)
    LinearLayout groupLinear;
    @BindView(R.id.group_next)
    TextView groupNext;

    private String[] mTitle;
    private int widthDpi;
    private int heightDpi;
    //点与点之间的间距
    private int leftMax;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity,GroupActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_group;
    }

    @Override
    public void initView() {
        mTitle = ResourceUtils.getStringArray(getContext(),R.array.group_title);
        addPoint();
        viewPager.setAdapter(new GroupPagerAdapter());

        groupNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CacheUtils.putBoolean(getContext(), ConstantsUtils.GROUP_STATE,true);
                MainActivity.launch(GroupActivity.this);
                finish();
            }
        });
    }

    private void addPoint() {
        widthDpi = DensityUtils.dip2px(getContext(),10);
        heightDpi = DensityUtils.dip2px(getContext(),10);
        for (int i = 0 ; i <mTitle.length; i ++){
            ImageView imgPoint = new ImageView(getContext());
            imgPoint.setBackgroundResource(R.drawable.group_point_grey_shape);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(widthDpi,heightDpi);
            if(i != 0){
                params.leftMargin = widthDpi;
            }
            imgPoint.setLayoutParams(params);
            groupLinear.addView(imgPoint);
        }

        groupPoint.getViewTreeObserver().addOnGlobalLayoutListener(new GroupPointOnGlobalLayoutListener());

        viewPager.addOnPageChangeListener(new MyOnPageChangeListener());
    }



    class GroupPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return mTitle.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            TextView textView = new TextView(getContext());
            textView.setGravity(Gravity.CENTER);
            textView.setText(mTitle[position]);
            textView.setTextSize(ResourceUtils.getDimension(getContext(),R.dimen.text_size_24sp));
            textView.setTextColor(ResourceUtils.getColor(getContext(),R.color.black));
            container.addView(textView);
            return textView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }

    private class GroupPointOnGlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {

        @Override
        public void onGlobalLayout() {
            groupPoint.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            leftMax = groupLinear.getChildAt(1).getLeft() - groupLinear.getChildAt(0).getLeft();
        }
    }

    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int i, float v, int i1) {
            int leftMargin = (int) (i * leftMax +(v * leftMax));
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) groupPoint.getLayoutParams();
            params.leftMargin = leftMargin;
            groupPoint.setLayoutParams(params);
        }

        @Override
        public void onPageSelected(int i) {
            if(i == mTitle.length - 1){
                groupNext.setVisibility(View.VISIBLE);
            }else{
                groupNext.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    }
}
