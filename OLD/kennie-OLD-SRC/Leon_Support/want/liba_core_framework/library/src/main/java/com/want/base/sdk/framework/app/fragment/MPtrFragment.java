package com.want.base.sdk.framework.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.want.base.sdk.framework.app.MFragment;
import com.want.base.sdk.framework.ptr.IPtrable;
import com.want.base.sdk.framework.ptr.IptrView;
import com.want.base.sdk.framework.ptr.impl.SwipeRefresLayoutImpl;

/**
 * <b>Project:</b> hollywant<br>
 * <b>Create Date:</b> 16/2/1<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public abstract class MPtrFragment extends MFragment implements IPtrable {


    private IptrView mPtrView;

    public MPtrFragment() {
        super();
    }

    protected void setPtrView(IptrView iptrView) {
        this.mPtrView = iptrView;
    }

    protected IptrView getPtrView() {
        return this.mPtrView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null == mPtrView) {
            this.mPtrView = new SwipeRefresLayoutImpl(this);
        }
    }

    /**
     * Called to create the fragment its user content view.
     *
     * @param inflater           {@link LayoutInflater}
     * @param container          {@link ViewGroup}
     * @param savedInstanceState {@link Bundle}
     *
     * @return ContentView
     */
    protected abstract View onCreateContentView(LayoutInflater inflater,
                                                ViewGroup container,
                                                Bundle savedInstanceState);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View childView = onCreateContentView(inflater, container, savedInstanceState);
        return mPtrView.onCreateView(inflater, childView, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mPtrView.onViewCreated(view, savedInstanceState);
        super.onViewCreated(view, savedInstanceState);
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

    /**
     * Called when has next page to load
     */
    @Override
    public void onNextPageLoad() {

    }

    /**
     * Has next page
     */
    @Override
    public boolean hasNextPage() {
        return false;
    }

    @Override
    public boolean isRefreshing() {
        return mPtrView.isRefreshing();
    }
}
