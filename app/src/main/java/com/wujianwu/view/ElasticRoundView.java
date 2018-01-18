package com.wujianwu.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * QQ 气泡自定义view
 * Created by ex-wujianwu on 2017/12/22.
 */

public class ElasticRoundView extends View {

    public ElasticRoundView(Context context) {
        super(context);
        init();
    }

    public ElasticRoundView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ElasticRoundView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    int centerX;
    int centerY;

    int moveX;
    int moveY;
    int centerRadius;
    int moveRadius;
    Paint paint;
    Path path;

    private void init() {
        //这个得到的不应该叫做密度，应该是密度的一个比例。不是真实的屏幕密度，而是相对于某个值的屏幕密度。
        //也可以说是相对密度
        int density = (int) getResources().getDisplayMetrics().density;
        int widthPixels = getResources().getDisplayMetrics().widthPixels;
        int heightPixels = getResources().getDisplayMetrics().heightPixels;

        //设置起点 中心屏幕中心
        centerX = widthPixels / 2;
        centerY = heightPixels / 2;
        moveX = centerX;
        moveY = centerY;

        //中心圆半径
        centerRadius = density * 25;
        moveRadius = centerRadius;
        int startRadius = centerRadius;

        path = new Path();//路径

        paint = new Paint();//画笔
        paint.setColor(Color.parseColor("#ff5777"));
        paint.setAntiAlias(true);//去锯齿
        paint.setStyle(Paint.Style.FILL);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(centerX, centerY, centerRadius, paint);//中心圆
        canvas.drawCircle(moveX, moveY, centerRadius, paint);//拖动后的圆
        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
