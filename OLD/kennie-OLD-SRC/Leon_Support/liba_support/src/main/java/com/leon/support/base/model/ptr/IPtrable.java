package com.leon.support.base.model.ptr;

import android.view.View;


/**
 * <b>Project:</b> liba_support<br>
 * <b>Create Date:</b> 16/10/15<br>
 * <b>Author:</b> LearKoo<br>
 * <b>Description:</b>
 * Interface or pull down to refresh and pull up to load more.
 * <br>
 */
public interface IPtrable {

    /**
     * Return the ptr view.
     *
     * @return the ptr view
     */
    View getView();

    /**
     * Show refresh status or not.
     *
     * @param refreshing true, show refresh status. false otherwise.
     */
    void setRefreshing(boolean refreshing);

    /**
     * Called when need re
     *
     * @param manual true, called from user. false otherwise.
     */
    void onRefresh(boolean manual);

    /**
     * Called when has next page to load
     */
    void onNextPageLoad();

    /**
     * Has next page
     */
    boolean hasNextPage();

    boolean isRefreshing();

}
