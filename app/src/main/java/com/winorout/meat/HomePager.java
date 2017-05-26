package com.winorout.meat;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Mr-x on 2017/04/14.
 */
/*
* 用来实现可以随意设置页面缓存个数的ViewPager
* */

public class HomePager extends ViewPager {

    public HomePager(Context context) {
        super(context);
    }

    public HomePager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        return false;
    }
}
