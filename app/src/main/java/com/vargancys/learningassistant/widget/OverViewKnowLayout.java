package com.vargancys.learningassistant.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.vargancys.learningassistant.R;
import com.vargancys.learningassistant.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/01
 * version:1.0
 */
public class OverViewKnowLayout extends View {
    private int mLineWidth;
    private int mLineColor;
    private int mCircleRadius;
    private int mCircleColor;
    private int mCenterPaintX;
    private int mCenterPaintY;
    private int mLanguage = 1;
    private Paint mKnowPaint;
    private List<KnowItem> knowItems = new ArrayList<>();
    private Paint mLinePaint;
    private Random mRandom;
    public OverViewKnowLayout(Context context) {
        this(context,null);
    }

    public OverViewKnowLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public OverViewKnowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.OverViewKnowLayout);
        mLineWidth = typedArray.getDimensionPixelOffset(R.styleable.OverViewKnowLayout_line_width, DensityUtils.dip2px(getContext(),10));
        mLineColor = typedArray.getColor(R.styleable.OverViewKnowLayout_line_color,getResources().getColor(R.color.gray));
        mCircleColor = typedArray.getColor(R.styleable.OverViewKnowLayout_circle_color,getResources().getColor(R.color.pink));
        mCircleRadius = typedArray.getDimensionPixelOffset(R.styleable.OverViewKnowLayout_circle_radius,DensityUtils.dip2px(getContext(),25));
        typedArray.recycle();
        mRandom = new Random();
        initResources();
    }

    private void initData() {
        KnowItem knowItem = new KnowItem();
        knowItem.setCenterPaintX(mCenterPaintX);
        knowItem.setCenterPaintY(mCenterPaintY);
        knowItems.add(knowItem);
        for (int length = 0;length<5;length++){
            KnowItem knowItem1 = new KnowItem();
            knowItem1.setCenterPaintX(mCenterPaintX-mCircleRadius*2+DensityUtils.dip2px(getContext(),120));
            knowItem1.setCenterPaintY(mCenterPaintY);
            knowItem1.setParentCenterPaintX(mCenterPaintX);
            knowItem1.setParentCenterPaintY(mCenterPaintY);
            knowItems.add(knowItem1);
        }
    }

    private void initResources() {
        mKnowPaint = new Paint();
        mKnowPaint.setColor(mCircleColor);
        mKnowPaint.setAntiAlias(true);
        mKnowPaint.setStrokeWidth(DensityUtils.dip2px(getContext(),15));
        mKnowPaint.setStyle(Paint.Style.FILL);

        mLinePaint = new Paint();
        mLinePaint.setColor(mCircleColor);
        mLinePaint.setAntiAlias(true);
        mLinePaint.setStrokeWidth(DensityUtils.dip2px(getContext(),15));
        mLinePaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        for (int item = 0,size = knowItems.size();item< size;item ++){
            canvas.drawCircle(knowItems.get(item).getCenterPaintX(),knowItems.get(item).getCenterPaintY(),mCircleRadius,mKnowPaint);
            if(item != 0){
                canvas.drawLine(knowItems.get(item).getParentCenterPaintX(),knowItems.get(item).getParentCenterPaintY(),
                        knowItems.get(item).getCenterPaintX(),knowItems.get(item).getCenterPaintY(),mLinePaint);
            }
            canvas.rotate(45,mCenterPaintX,mCenterPaintY);
        }
        canvas.restore();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterPaintX = w/2;
        mCenterPaintY = h/2;
        initData();
    }
}
