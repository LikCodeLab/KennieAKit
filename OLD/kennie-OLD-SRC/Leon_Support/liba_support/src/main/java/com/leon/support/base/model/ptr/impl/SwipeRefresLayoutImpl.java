package com.leon.support.base.model.ptr.impl;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;

import com.leon.support.R;
import com.leon.support.base.model.ptr.IPtrable;
import com.leon.support.base.model.ptr.IptrView;


/**
 * <b>Project:</b> liba_support<br>
 * <b>Create Date:</b> 16/10/15<br>
 * <b>Author:</b> LearKoo<br>
 * <b>Description:</b>
 * Load next page layout.
 * <br>
 */
public class SwipeRefresLayoutImpl implements IptrView {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    //    private int mProgressViewOffset;
    private IPtrable mCallback;
    private boolean isManual = false;

    public SwipeRefresLayoutImpl(IPtrable callback) {
        this.mCallback = callback;
    }

    /**
     * Return the ptr view.
     *
     * @return the ptr view
     */
    @Override
    public View getView() {
        return mSwipeRefreshLayout;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, View container, Bundle savedInstanceState) {
        mSwipeRefreshLayout =
                (SwipeRefreshLayout) inflater.inflate(R.layout.base_ptr_swiperefresh_layout, null);
        final SwipeRefreshLayout.LayoutParams lp =
                new SwipeRefreshLayout.LayoutParams(SwipeRefreshLayout.LayoutParams.MATCH_PARENT
                        , SwipeRefreshLayout.LayoutParams.MATCH_PARENT);
        mSwipeRefreshLayout.addView(container, lp);
        return mSwipeRefreshLayout;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
//        mProgressViewOffset = (int) (72 * view.getResources().getDisplayMetrics().density);
        final int[] attrs = new int[]{R.attr.colorPrimary};
        TypedArray a = view.getContext().getTheme().obtainStyledAttributes(attrs);
        final int color = a.getColor(0, 0);
        a.recycle();

        mSwipeRefreshLayout.setColorSchemeColors(color);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (null != mCallback) {
                    mCallback.onRefresh(isManual);
                    isManual = false;
                }
            }
        });
    }

    /**
     * Show refresh status or not.
     *
     * @param refreshing true, show refresh status. false otherwise.
     */
    @Override
    public void setRefreshing(boolean refreshing) {
        if (refreshing) {
            isManual = true;
//            mSwipeRefreshLayout.setProgressViewOffset(false, 0, mProgressViewOffset);
        }
        if (mSwipeRefreshLayout != null) {
        mSwipeRefreshLayout.setRefreshing(refreshing);
        }
    }

    /**
     * Called when need re
     *
     * @param manual true, called from user. false otherwise.
     */
    @Override
    public void onRefresh(boolean manual) {
        if (null != mCallback) {
            mCallback.onRefresh(manual);
        }
    }

    /**
     * Called when has next page to load
     */
    @Override
    public void onNextPageLoad() {
        if (null != mCallback) {
            mCallback.onNextPageLoad();
        }
    }

    /**
     * Has next page
     */
    @Override
    public boolean hasNextPage() {
        return null != mCallback && mCallback.hasNextPage();
    }

    @Override
    public boolean isRefreshing() {
        return mSwipeRefreshLayout.isRefreshing();
    }
}
