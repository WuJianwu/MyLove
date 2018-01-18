package com.wuijanwu.love.mylove;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ex-wujianwu on 2017/12/11.
 */

public class MyView extends View {
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int horizontalCenter = getWidth()/2 ;
        int verticalCenter = getHeight()/2 ;
        int red = 10 ;
        Paint paint = new Paint();
        paint.setAntiAlias(false);
        paint.setColor(Color.parseColor("#303F9F"));
        canvas.drawCircle(horizontalCenter,verticalCenter,red,paint);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
    }
}
