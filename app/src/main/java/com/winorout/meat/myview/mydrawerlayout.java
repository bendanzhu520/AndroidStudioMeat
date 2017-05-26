package com.winorout.meat.myview;

/**
 * Created by Mr-x on 2017/04/17.
 */
import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class mydrawerlayout extends DrawerLayout{
    public mydrawerlayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
}
