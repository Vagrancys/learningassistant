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
    private int CircleWidth;
    private int LineLength;
    private int Width;
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
        mPaint.setStrokeWidth(25);
    }

    private void initData(){
        Width = DensityUtils.dip2px(getContext(),50);
        CircleWidth = Width / 2;
        LineLength = Width + DensityUtils.dip2px(getContext(),10);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Width = widthMeasureSpec;
        measure(widthMeasureSpec,heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < 6; i++){
            if(i <= 4){
                mPaint.setColor(Color.YELLOW);
                canvas.drawLine(CircleWidth,CircleWidth,CircleWidth+Width*i,CircleWidth,mPaint);
            }
            mPaint.setColor(Color.GRAY);
            canvas.drawCircle(CircleWidth+Width*i+DensityUtils.dip2px(getContext(),25),CircleWidth,CircleWidth,mPaint);
            mPaint.setColor(Color.BLACK);
            canvas.drawText("入门",CircleWidth,CircleWidth,mPaint);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Width = w;
    }
}
