package com.want.base.sdk.framework.ptr.impl.view;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.want.core.log.lg;

/**
 * <b>Project:</b> hollywant<br>
 * <b>Create Date:</b> 16/1/28<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class SwipeRefreshLayoutEx extends SwipeRefreshLayout {

    private View mTarget;

    private int mTouchSlop;

    /**
     * Simple constructor to use when creating a SwipeRefreshLayout from code.
     *
     * @param context
     */
    public SwipeRefreshLayoutEx(Context context) {
        this(context, null);
    }

    /**
     * Constructor that is called when inflating SwipeRefreshLayout from XML.
     *
     * @param context
     * @param attrs
     */
    public SwipeRefreshLayoutEx(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        ensureTarget();
        return super.onInterceptTouchEvent(ev);
    }


    //    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        return super.onInterceptTouchEvent(ev) | innerOnInterceptTouchEvent(ev);
//    }

    private float mInitX;
    private float mInitY;

    private boolean innerOnInterceptTouchEvent(MotionEvent ev) {
        ensureTarget();

        if (!isEnabled() || canChildScrollDown()) {
            return false;
        }

        final int action = MotionEventCompat.getActionMasked(ev);
        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                mInitX = ev.getX();
                mInitY = ev.getY();
                break;
            }
            case MotionEvent.ACTION_UP: {
                final float x = ev.getX();
                final float y = ev.getY();

                final float xDiffabs = Math.abs(x - mInitX);
                final float yDiffabs = Math.abs(y - mInitY);
                if (yDiffabs > mTouchSlop && yDiffabs >= xDiffabs && mInitY > y) {
                    loadPage();
                }
                break;
            }
        }

        return false;
    }

    private void loadPage() {
        lg.w("refresh", "loadPage");
    }


    /**
     * @return Whether it is possible for the child view of this layout to
     * scroll up. Override this if the child view is a custom view.
     */
    @Override
    public boolean canChildScrollUp() {
        if (super.canChildScrollUp()) {
            return true;
        }

        return innerCanChildScrollVertically(mTarget, -1);
    }

    private boolean innerCanChildScrollVertically(View view, int direction) {
        if (view instanceof ViewGroup) {
            final ViewGroup vGroup = (ViewGroup) view;
            View child;
            boolean result;
            for (int i = 0; i < vGroup.getChildCount(); i++) {
                child = vGroup.getChildAt(i);
                if (child instanceof View) {
                    result = ViewCompat.canScrollVertically(child, direction);
                } else {
                    result = innerCanChildScrollVertically(child, direction);
                }

                if (result) {
                    return true;
                }
            }
        }

        return ViewCompat.canScrollVertically(view, direction);
    }

    private void ensureTarget() {
        // Don't bother getting the parent height if the parent hasn't been laid
        // out yet.
        if (mTarget == null) {
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                if (!(child instanceof ImageView)) {
                    mTarget = child;
                    break;
                }
            }
        }
    }

    /**
     * @return Whether it is possible for the child view of this layout to
     * scroll down. Override this if the child view is a custom view.
     */
    public boolean canChildScrollDown() {
        return ViewCompat.canScrollVertically(mTarget, -1);
    }
}
