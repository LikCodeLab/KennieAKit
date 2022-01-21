package com.want.base.sdk.framework.app.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.want.base.sdk.framework.ptr.IPtrable;
import com.want.base.sdk.framework.ptr.IptrView;
import com.want.base.sdk.framework.ptr.impl.SwipeRefresLayoutImpl;
import com.want.core.log.lg;

/**
 * <b>Project:</b> project_hotclub<br>
 * <b>Create Date:</b> 16/5/18<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public abstract class MPtrRecyclerFragment<T> extends MRecyclerFragment<T> implements
                                                                           IPtrable {
    private static final boolean DEBUG = Log.isLoggable("ptr", Log.VERBOSE);
    private IptrView mPtrView;

    public MPtrRecyclerFragment() {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View childView = super.onCreateView(inflater, container, savedInstanceState);
        return mPtrView.onCreateView(inflater, childView, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mPtrView.onViewCreated(view, savedInstanceState);
        super.onViewCreated(view, savedInstanceState);

        final RecyclerView recyclerView = getRecyclerView();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            int diffY = 0;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (DEBUG) {
                    lg.v("ptr", "dx: " + dx + ", dy: " + dy);
                }
                diffY += dy;
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (DEBUG) {
                    lg.w("ptr", "state: " + newState);
                }

                // 1) caculate weather can pull up to load next page
                final boolean inEffect = diffY >= 0;

                // 2) has next page && IDLE status && can pull to up
                if (RecyclerView.SCROLL_STATE_IDLE == newState && inEffect && hasNextPage()) {
                    final RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
                    final int count = recyclerView.getAdapter().getItemCount();
                    final int totalVisibleCount = recyclerView.getChildCount();
                    int firstVisibleItem = 0;
                    if (manager instanceof LinearLayoutManager) {
                        firstVisibleItem = ((LinearLayoutManager) manager).findFirstVisibleItemPosition();
                    } /*else if (manager instanceof StaggeredGridLayoutManager) {
                        lastVisibleItem = ((StaggeredGridLayoutManager) manager).findLastVisibleItemPosition();
                    }*/
                    if (DEBUG) {
                        lg.v("ptr",
                             "total: " + count + ", visible: " + totalVisibleCount +
                             ", firstVisibleItem: " + firstVisibleItem);
                    }

                    // 3) last item has been visibled
                    if (count - totalVisibleCount <= firstVisibleItem) {
                        final View view = recyclerView.getChildAt(totalVisibleCount - 1);
                        final int bottom = view.getBottom();
                        final int pbottom = recyclerView.getBottom();

                        // 4) last item has been showed completely
                        if (bottom <= pbottom) {
                            if (DEBUG) {
                                lg.v("ptr", "enable load next, bottom: " + bottom + ", pbottom: " + pbottom);
                            }
                            setRefreshing(true);
                            onNextPageLoad();
                        }
                    }

                }
                // 5) reset diffY
                diffY = 0;
            }
        });
    }

    @Override
    public void onNextPageLoad() {

    }

    @Override
    public boolean hasNextPage() {
        return false;
    }

    @Override
    public boolean isRefreshing() {
        return mPtrView.isRefreshing();
    }

    @Override
    public void setRefreshing(boolean refreshing) {
        mPtrView.setRefreshing(refreshing);
    }
}
