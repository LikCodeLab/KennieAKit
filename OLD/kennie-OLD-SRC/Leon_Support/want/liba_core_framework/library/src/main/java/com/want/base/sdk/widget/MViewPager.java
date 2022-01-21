package com.want.base.sdk.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * <b>Project:</b> project_hotclub<br>
 * <b>Create Date:</b> 8/1/16<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class MViewPager extends ViewPager {

    public interface OnVisibilityChangedListener {
        void onVisibilityChanged(int visibility);
    }

    private OnVisibilityChangedListener mOnWindowAttachChangeListener;

    public MViewPager(Context context) {
        super(context);
    }

    public MViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setOnVisibilityChangedListener(OnVisibilityChangedListener listener) {
        this.mOnWindowAttachChangeListener = listener;
    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (mOnWindowAttachChangeListener != null) {
            mOnWindowAttachChangeListener.onVisibilityChanged(visibility);
        }
    }
}
