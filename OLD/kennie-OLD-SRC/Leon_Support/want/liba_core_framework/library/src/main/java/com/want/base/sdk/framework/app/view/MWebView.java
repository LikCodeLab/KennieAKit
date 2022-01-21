package com.want.base.sdk.framework.app.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.webkit.WebView;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <b>Project:</b> project_hotclub<br>
 * <b>Create Date:</b> 16/3/22<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class MWebView extends WebView {

    private OnScrollChangedListener mOnScrollChangeListener;
    private GestureDetector mGestureDetector;
    private AtomicBoolean mPreventAction = new AtomicBoolean(false);
    private AtomicLong mPreventActionTime = new AtomicLong(0);

    public MWebView(Context context) {
        super(context);
        init(context);
    }

    public MWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public MWebView(Context context, AttributeSet attrs, int defStyleAttr, boolean privateBrowsing) {
        super(context, attrs, defStyleAttr, privateBrowsing);
        init(context);
    }

    private void init(Context context) {
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                mPreventAction.set(true);
                mPreventActionTime.set(System.currentTimeMillis());
                return true;
            }

            @Override
            public boolean onDoubleTapEvent(MotionEvent e) {
                mPreventAction.set(true);
                mPreventActionTime.set(System.currentTimeMillis());
                return true;
            }
        });
    }

    public void setOnScrollChangeListener(OnScrollChangedListener l) {
        this.mOnScrollChangeListener = l;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int
                index =
                (event.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK) >>
                MotionEvent.ACTION_POINTER_INDEX_SHIFT;
        int pointId = event.getPointerId(index);

        // just use one(first) finger, prevent double tap with two and more fingers
        if (pointId == 0) {
            mGestureDetector.onTouchEvent(event);

            if (mPreventAction.get()) {
                if (System.currentTimeMillis() - mPreventActionTime.get() >
                    ViewConfiguration.getDoubleTapTimeout()) {
                    mPreventAction.set(false);
                } else {
                    return true;
                }
            }

            return super.onTouchEvent(event);
        } else {
            return true;
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (null != mOnScrollChangeListener) {
            mOnScrollChangeListener.onScrollChanged(this, l, t, oldl, oldt);
        }
    }

    public interface OnScrollChangedListener {
        void onScrollChanged(View view, int x, int y, int oldX, int oldY);
    }
}
