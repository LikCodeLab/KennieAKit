package com.want.base.sdk.framework.app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.want.base.sdk.framework.ptr.IPtrable;
import com.want.base.sdk.framework.ptr.IptrView;
import com.want.base.sdk.framework.ptr.impl.SwipeRefresLayoutImpl;
import com.want.base.sdk.framework.ptr.impl.loadnext.LoadNextLayout;

import java.util.List;

/**
 * <b>Project:</b> hollywant<br>
 * <b>Create Date:</b> 16/1/28<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * List Fragment that supports pull to refresh.
 * <br>
 */
public abstract class MPtrListFragment<T> extends MListFragment<T> implements IPtrable {


    private IptrView mPtrView;

    private LoadNextLayout mLoadNextLayout;
    private boolean isLoadingNext = false;
    private boolean isEnableLoadNext = false;

    public MPtrListFragment() {
        super();
    }

    protected void setPtrView(IptrView iptrView) {
        this.mPtrView = iptrView;
    }

    protected IptrView getPtrView() {
        return this.mPtrView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null == mPtrView) {
            mPtrView = new SwipeRefresLayoutImpl(this);
        }
    }

    @Override
    protected View onCreateFooterView(ListView listView) {
        mLoadNextLayout = new LoadNextLayout(getActivity());
        return mLoadNextLayout;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View childView = super.onCreateView(inflater, container, savedInstanceState);
        return mPtrView.onCreateView(inflater, childView, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mPtrView.onViewCreated(view, savedInstanceState);
        super.onViewCreated(view, savedInstanceState);
        getListView().setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (SCROLL_STATE_IDLE == scrollState &&
                    isEnableLoadNext &&
                    !isLoadingNext) {
                    try {
                        final int lastVisiblePostion = view.getLastVisiblePosition();
                        if (lastVisiblePostion == view.getPositionForView(mLoadNextLayout)) {
                            // 当前分页数小于总数
                            if (hasNextPage()) {
                                final int index = ((ListView) view).getHeaderViewsCount();
                                final int firstVisiblePositon = view.getFirstVisiblePosition();
                                final int count = lastVisiblePostion - index;
                                final List<T> dataSet = getDataSet();
                                final boolean isDataEmpty = null == dataSet || dataSet.isEmpty();

                                if (!isDataEmpty
                                    && !(firstVisiblePositon == index && count == dataSet.size())) {
                                    mLoadNextLayout.setStatus(LoadNextLayout.Status.LOADING);
                                    isLoadingNext = true;
                                    onNextPageLoad();
                                } else {
                                    mLoadNextLayout.setStatus(LoadNextLayout.Status.NONEXT);
                                }
                            } else {
                                mLoadNextLayout.setStatus(LoadNextLayout.Status.NONEXT);
                            }

                            mLoadNextLayout.setVisibility(View.VISIBLE);
                        }
                    } catch (Exception e) {
                        // ignore
                    }
                } else if (SCROLL_STATE_TOUCH_SCROLL == scrollState && isEnableLoadNext) {
                    mLoadNextLayout.post(new Runnable() {
                        @Override
                        public void run() {
                            mLoadNextLayout.setVisibility(View.VISIBLE);
                        }
                    });
                }
            }

            @Override
            public void onScroll(AbsListView view,
                                 int firstVisibleItem,
                                 int visibleItemCount,
                                 int totalItemCount) {

            }
        });

        mLoadNextLayout.post(new Runnable() {
            @Override
            public void run() {
                mLoadNextLayout.setVisibility(View.GONE);
            }
        });
    }

    /**
     * 该方法需要使用者显示调用，有两个时机是必须调用的：<br/>
     * 1. 数据更新后（即重置数据时）调用；<br/>
     * 2. 更多数据加载完成后调用。<br/>
     * <br/>
     */
    protected void setNextPageLoaded() {
        isLoadingNext = false;

        if (!hasNextPage()) {
            mLoadNextLayout.setStatus(LoadNextLayout.Status.NONEXT);
        }
    }

    /**
     * Set whether enable load next page
     *
     * @param enable true, enable; false otherwise.
     */
    protected void setEnableNextPageLoad(boolean enable) {
        this.isEnableLoadNext = enable;
    }

    /**
     * Show refresh status or not.
     *
     * @param refreshing true, show refresh status. false otherwise.
     */
    @Override
    public void setRefreshing(boolean refreshing) {
        mPtrView.setRefreshing(refreshing);
    }

    @Override
    public boolean isRefreshing() {
        return mPtrView.isRefreshing();
    }
}
