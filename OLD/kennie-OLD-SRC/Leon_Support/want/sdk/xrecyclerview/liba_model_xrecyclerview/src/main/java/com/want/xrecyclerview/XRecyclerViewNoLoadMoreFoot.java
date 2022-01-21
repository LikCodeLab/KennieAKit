package com.want.xrecyclerview;

import android.content.Context;
import android.util.AttributeSet;

/**
 * <b>Project:</b> hollywant<br>
 * <b>Create Date:</b> 2016/5/25<br>
 * <b>Author:</b> pengdun<br>
 * <b>Description:</b> <br>
 */
public class XRecyclerViewNoLoadMoreFoot extends XRecyclerView {
    public XRecyclerViewNoLoadMoreFoot(Context context) {
        super(context);
    }

    public XRecyclerViewNoLoadMoreFoot(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public XRecyclerViewNoLoadMoreFoot(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void init(Context context) {
       // super.init(context);
        mContext = context;
        if(pullRefreshEnabled) {
            ArrowRefreshHeader refreshHeader = new ArrowRefreshHeader(mContext);
            mHeaderViews.add(0, refreshHeader);
            mRefreshHeader = refreshHeader;
            mRefreshHeader.setProgressStyle(mRefreshProgressStyle);
        }
    }
}
