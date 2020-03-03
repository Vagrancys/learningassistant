package com.vargancys.learningassistant.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.vargancys.learningassistant.utils.DensityUtils;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/02
 * version:1.0
 */
public class MasterProgressView extends View {
    private Paint mPaint;
    private Paint mLinePaint;
    private Paint mTextPaint;
    private int mStrokeWidth;
    private int CircleWidth;
    private int LineWidth;
    private int Width;
    private int mMasterLevel = 2;
    private String[] mMasterTitle = {
            "入门","了解","熟悉","精通","之父"
    };
    private int[] mColor = {
            Color.GRAY,Color.CYAN,Color.RED,Color.GREEN,Color.MAGENTA,Color.DKGRAY
    };
    public MasterProgressView(Context context) {
        super(context);
        initView();
    }

    public MasterProgressView(Context context,AttributeSet attrs) {
        super(context, attrs);
        initView();
        initData();
    }

    private void initView(){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(mStrokeWidth);
        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);
        mLinePaint.setColor(Color.GRAY);
        mLinePaint.setStrokeWidth(mStrokeWidth);
        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setStrokeWidth(mStrokeWidth);
        mTextPaint.setTextSize(DensityUtils.dip2px(getContext(),18));
    }

    private void initData(){
        Width = DensityUtils.dip2px(getContext(),50);
        CircleWidth = DensityUtils.dip2px(getContext(),25);
        LineWidth =DensityUtils.dip2px(getContext(),20);
        mStrokeWidth = DensityUtils.dip2px(getContext(),25);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < 6; i++){
            mLinePaint.setColor(mColor[0]);
            mPaint.setColor(mColor[0]);
            canvas.drawLine(Width*(i+1),CircleWidth,Width*(i+1)+LineWidth,CircleWidth,mLinePaint);
            canvas.drawCircle((Width+LineWidth)*i+CircleWidth,CircleWidth,CircleWidth,mPaint);
            if(i < mMasterLevel){
                mLinePaint.setColor(mColor[i+1]);
                mPaint.setColor(mColor[i+1]);
                canvas.drawLine(Width*(i+1),CircleWidth,Width*(i+1)+LineWidth,CircleWidth,mLinePaint);
                canvas.drawCircle((Width+LineWidth)*i+CircleWidth,CircleWidth,CircleWidth,mPaint);
            }
            float textLength = mTextPaint.measureText(mMasterTitle[mMasterLevel])/2;
            canvas.drawText(mMasterTitle[i],CircleWidth*(i*2-1)+LineWidth*(i+2),CircleWidth+textLength/2,mTextPaint);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }
}
