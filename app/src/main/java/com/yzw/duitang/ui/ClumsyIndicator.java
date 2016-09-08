package com.yzw.duitang.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import com.yzw.duitang.R;

/**
 * Created by yzw on 2016/9/6..
 */
public class ClumsyIndicator extends View {

    // 设置个数
    private int mCount;

    // 当前选择
    private int selectedItem = 0;

    // 圆形半径
    private float mRadius;

    // 选中圆形半径
    private float mSelectedRadius;

    // 圆形之间的间隔
    private float mSpace;

    // 画笔
    private Paint mPaint;

    public ClumsyIndicator(Context context) {
        this(context, null);
    }

    public ClumsyIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClumsyIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
        setSize();
    }

    // 根据density设置大小
    private void setSize() {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        mRadius = 2 * metrics.density;
        mSelectedRadius = 3 * metrics.density;
        mSpace = 12 * metrics.density;


    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(getResources().getColor(R.color.light_grey));
        mPaint.setStyle(Paint.Style.FILL);
    }

    public void setViewPager(ViewPager pager) {
        mCount = pager.getAdapter().getCount();
        invalidate();
    }

    public void setSelectedItem(int position) {
        selectedItem = position;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }


    private int measureWidth(int widthMeasureSpec) {
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);
        int result;
        if (specMode != MeasureSpec.EXACTLY) {
            result = getPaddingLeft() + getPaddingRight() + (int)(mSpace * mCount);
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(specSize, result);
            }
        } else {
            result = specSize;
        }
        return result;
    }

    private int measureHeight(int heightMeasureSpec) {
        int specMode = MeasureSpec.getMode(heightMeasureSpec);
        int specSize = MeasureSpec.getSize(heightMeasureSpec);
        int result;
        if (specMode != MeasureSpec.EXACTLY) {
            result = getPaddingTop() + getPaddingBottom() + (int)(mSelectedRadius * 2);
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(specSize, result);
            }
        } else {
            result = specSize;
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float y = getHeight() / 2f;
        float x = mSpace / 2f;
        for (int i = 0; i < mCount; i++) {
            if (i != selectedItem) {
                canvas.drawCircle(x, y, mRadius, mPaint );
            } else {
                canvas.drawCircle(x, y, mSelectedRadius, mPaint );
            }
            x += mSpace;
        }
    }

}
