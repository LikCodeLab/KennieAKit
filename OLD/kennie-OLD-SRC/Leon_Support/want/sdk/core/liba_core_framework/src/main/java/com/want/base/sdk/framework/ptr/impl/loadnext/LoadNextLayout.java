package com.want.base.sdk.framework.ptr.impl.loadnext;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.want.base.sdk.R;


/**
 * <b>Project:</b> HollyWant<br>
 * <b>Create Date:</b> 15/10/21<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * Load next page layout.
 * <br>
 */
public class LoadNextLayout extends FrameLayout {

    public enum Status {
        /** status loading */
        LOADING,
        /** status no next page */
        NONEXT
    }

    public enum Mode {
        /** mode auto load next page */
        AUTO,
        /** mode load next page by user */
        MANUAL
    }

    private View mRootLayout;
    private View mLoadingLayout;
    private View mClickAndLoadLayout;
    private View mNoNextLayout;

    private Status mStatus;
    private Mode mLoadMode;

    public LoadNextLayout(Context context) {
        this(context, null);
    }

    public LoadNextLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadNextLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.sdk_base_ptr_loadnext_layout, this);
        mRootLayout = findViewById(R.id.ptr_loadnext_layout);
        mLoadingLayout = findViewById(R.id.ptr_loadnext_loading_layout);
        mClickAndLoadLayout = findViewById(R.id.ptr_loadnext_clickandload_text);
        mNoNextLayout = findViewById(R.id.ptr_loadnext_nonext_text);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }


    public void setStatus(Status status) {
        this.mStatus = status;
        if (Status.LOADING == status) {
            mLoadingLayout.setVisibility(VISIBLE);
            mNoNextLayout.setVisibility(GONE);
        } else if (Status.NONEXT == status) {
            mLoadingLayout.setVisibility(GONE);
            mNoNextLayout.setVisibility(VISIBLE);
        }
    }

    public void setMode(Mode mode) {
        this.mLoadMode = mode;
    }

    @Override
    public void setVisibility(int visibility) {
        if (GONE == visibility) {
            removeAllViews();
        } else if (null == mRootLayout.getParent()) {
            addView(mRootLayout);
        }
        super.setVisibility(visibility);
    }
}
