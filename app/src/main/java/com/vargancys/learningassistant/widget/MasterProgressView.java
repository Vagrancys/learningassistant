package com.vargancys.learningassistant.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.vargancys.learningassistant.utils.DensityUtils;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/02
 * version:1.0
 */
public class MasterProgressView extends View {
    //圆圈的笔画
    private Paint mCirclePaint;
    //线的笔画
    private Paint mLinePaint;
    //文字的笔画
    private Paint mTextPaint;

    private int mStrokeWidth;
    private int mLineStroke;
    //圆的宽
    private int mCircleWidth;
    //线的宽
    private int mLineWidth;
    //view的宽
    private int Width;
    //圆的半径
    private int mRangCircle;
    private int mNumberCircle = 5;
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
        initData();
        initView();
    }

    private void initView(){
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(Color.GREEN);
        mCirclePaint.setStrokeWidth(mStrokeWidth);
        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);
        mLinePaint.setColor(Color.GRAY);
        mLinePaint.setStrokeWidth(mLineStroke);
        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setStrokeWidth(mStrokeWidth);
        mTextPaint.setTextSize(DensityUtils.dip2px(getContext(),18));
    }

    private void initData(){
        mStrokeWidth = DensityUtils.dip2px(getContext(),25);
        mLineStroke = DensityUtils.dip2px(getContext(),15);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mNumberCircle; i++){
            mLinePaint.setColor(mColor[0]);
            mCirclePaint.setColor(mColor[0]);
            if(i< mNumberCircle-1){
                canvas.drawLine(mCircleWidth*(i+1)+mLineWidth*i-5,mRangCircle,mCircleWidth*(i+1)+mLineWidth*i+mLineWidth+5,mRangCircle,mLinePaint);
            }

            canvas.drawCircle(mRangCircle+(mCircleWidth+mLineWidth)*i,mRangCircle,mRangCircle,mCirclePaint);
            if(i < mMasterLevel){
                mLinePaint.setColor(mColor[i+1]);
                mCirclePaint.setColor(mColor[i+1]);
                if(i< mNumberCircle-1){
                    canvas.drawLine(mCircleWidth*i-5,mRangCircle,mCircleWidth*i+mLineWidth+5,mRangCircle,mLinePaint);
                }
                canvas.drawCircle(mRangCircle+(mCircleWidth+mLineWidth)*i,mRangCircle,mRangCircle,mCirclePaint);
            }
            float textLength = mTextPaint.measureText(mMasterTitle[mMasterLevel])/2;
            float textWidth = mRangCircle*(i+1)-textLength+(mLineWidth+mRangCircle)*i;
            Rect rect = new Rect();
            mTextPaint.getTextBounds(mMasterTitle[mMasterLevel],0,mMasterTitle[mMasterLevel].length(),rect);
            int textHeight = mRangCircle+rect.height()/2;
            Log.e("MasterView","rangCircle= "+mRangCircle+"textLength ="+textLength+"textWidth ="+textWidth+"textHeight ="+textHeight);
            canvas.drawText(mMasterTitle[i],textWidth,textHeight,mTextPaint);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, mCircleWidth, oldw, oldh);
        Width = w;
        mLineWidth = DensityUtils.dip2px(getContext(),20);
        mCircleWidth =Math.min( (Width-mLineWidth*4)/5,h);
        mRangCircle = mCircleWidth/2;
    }

    public void setMasterLevel(int level){
        this.mMasterLevel = level;
        requestLayout();
    }
}
