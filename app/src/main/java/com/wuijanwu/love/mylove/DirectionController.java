package com.wuijanwu.love.mylove;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class DirectionController extends View {
	private Paint mPaint;
	private Path mPath;
	private int width, height;
	private PointF movePoint = new PointF();
	private PointF controlPoint = new PointF();//贝赛尔曲线控制点
	private float angle;
	private float radius = 60;
	private int lineLength = 200;
	private float ANGLE_MAX_VALUE = (float) (Math.PI / 2);
	private float ANGLE_MIN_VALUE = (float) -(Math.PI / 2);
	private int touchSlop = 2;
	private int touchMaxValue;
	private int lineGap = 40;
	private int STROKE_WIDTH = 6;
	private float lastX, lastY, currentX, currentY;

	public DirectionController(Context context) {
		this(context, null);
	}

	public DirectionController(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public DirectionController(Context context, AttributeSet attrs,int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setColor(Color.BLUE);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeWidth(STROKE_WIDTH);
		mPaint.setStrokeCap(Cap.ROUND);
		mPath = new Path();
		controlPoint.set(0, -lineLength + radius);
		touchMaxValue = touchSlop * 40;
		PointF movePoint = getMovePoint();
		mPath.quadTo(controlPoint.x, controlPoint.y, movePoint.x, movePoint.y);
	}

	private PointF getMovePoint() {
		movePoint.set((float) (radius * Math.sin(angle)),
				(float) (controlPoint.y - radius * Math.cos(angle)));
		return movePoint;
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		width = getWidth();
		height = getHeight();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		currentX = event.getX();
		currentY = event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			lastX = currentX;
			lastY = currentY;
			return true;
		case MotionEvent.ACTION_MOVE:
			angle = Math.min(ANGLE_MAX_VALUE, angle);
			angle = Math.max(ANGLE_MIN_VALUE, angle);
			if (Math.abs(currentX - lastX) > touchSlop) {
				float angleDelta = (float) (Math.abs((currentX - lastX))
						/ touchMaxValue * ANGLE_MAX_VALUE);
				if (currentX > lastX) {
					angle += angleDelta;
				} else {
					angle -= angleDelta;
				}

				Log.e("DirectionController",Math.toDegrees(angleDelta) + " , "+ Math.toDegrees(angle));

				PointF movePoint = getMovePoint();
				mPath.reset();
				mPath.moveTo(0, 0);
				mPath.quadTo(controlPoint.x, controlPoint.y, movePoint.x,
						movePoint.y);
				invalidate();
			}
			lastX = currentX;
			lastY = currentY;
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
			break;
		}
		return super.onTouchEvent(event);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.translate(width / 2 - lineGap / 2, height / 2);
		canvas.drawPath(mPath, mPaint);
		canvas.translate(lineGap, 0);
		canvas.drawPath(mPath, mPaint);
	}
}
