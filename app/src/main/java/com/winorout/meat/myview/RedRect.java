package com.winorout.meat.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

/**
 * Created by Mr-x on 2017/03/23.
 */
/*
* 用来绘制主页面中下的红色按钮，用来进入用户注册页面或者用户个人信息界面
* */
public class RedRect extends View {

    private Paint rectpaint;
    private Paint linepaint;
    private int X;
    private int Y;

    public RedRect(Context context, AttributeSet attrs) {
        super(context, attrs);
        rectpaint = new Paint();
        linepaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        linepaint.setColor(Color.WHITE);
        linepaint.setStrokeWidth(6);

        rectpaint.setColor(Color.argb(255, 230, 230, 230));
        canvas.drawCircle(X / 2 ,Y / 2, 70, rectpaint);

        rectpaint.setColor(Color.argb(255, 255, 68, 47));
        canvas.drawCircle(X / 2, Y / 2, 62, rectpaint);

        rectpaint.setColor(Color.argb(255, 255, 68, 47));
        canvas.drawCircle(X / 2, Y / 2, 58, rectpaint);

        canvas.drawLine(X / 2, 32, X / 2, Y - 32, linepaint);
        canvas.drawLine(32, Y / 2, X - 32, Y / 2, linepaint);

        canvas.drawCircle(X / 2, 32, 3, linepaint);
        canvas.drawCircle(X / 2, Y - 32, 3, linepaint);
        canvas.drawCircle(32, Y / 2, 3, linepaint);
        canvas.drawCircle(X - 32, Y / 2, 3, linepaint);
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        X = getMeasuredWidth();
        Y = getMeasuredHeight();
    }
}
