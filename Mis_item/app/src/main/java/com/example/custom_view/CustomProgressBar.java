package com.example.custom_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.mis_item.R;

public class CustomProgressBar extends View {
    private static final int DEFAULT_BORDER_COLOR = Color.BLACK;

    private int mFirstColor;
    private int mCircleWidth;
    private int mCircleAngle;
    private Paint mPaint;

    private boolean isFirst = true;

    public CustomProgressBar(Context context) {
        this(context, null);
    }

    public CustomProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.CustomProgressBar, defStyle, 0);

        mFirstColor = a.getColor(R.styleable.CustomProgressBar_circle_color,
                DEFAULT_BORDER_COLOR);
        mCircleWidth = a.getDimensionPixelSize(
                R.styleable.CustomProgressBar_circle_width, 10);
        mCircleAngle = a.getInt(
                R.styleable.CustomProgressBar_circle_angle, 360);
        a.recycle();


//        new Thread(this).start();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        init();
        int center = getWidth() / 2; // 获取圆心x坐标
        int radius = center - mCircleWidth / 2; // 半径
        mPaint.setStrokeWidth(mCircleWidth);
        RectF oval = new RectF(center - radius, center - radius, center
                + radius, center + radius);
        mPaint.setColor(mFirstColor);
//            canvas.drawCircle(center, center, radius, mPaint);
        canvas.drawArc(oval, 0, mCircleAngle, false, mPaint);
    }
}

