package com.leon.support.base.framework.adapter;

import android.view.LayoutInflater;

/**
 * <b>Project:</b> liba_support<br>
 * <b>Create Date:</b> 15/6/14<br>
 * <b>Author:</b> Leon<br>
 * <b>Description:</b>
 * {@link IItemCreator}的空实现。
 * <br>
 */
public class SimpleViewCreator<V, T> implements IItemCreator<V, T> {

    /* (non-Javadoc)
     * @see oms.mmc.app.adapter.IItemCreator#onCreateItem(android.view.LayoutInflater, int, java.lang.Object)
     */
    @Override
    public V onCreateItem(LayoutInflater inflater, int position, T data) {
        return null;
    }

    /* (non-Javadoc)
     * @see oms.mmc.app.adapter.IItemCreator#onUpdateItem(android.view.View, int, java.lang.Object)
     */
    @Override
    public void onUpdateItem(V item, int position, T data) {
    }

    /* (non-Javadoc)
     * @see oms.mmc.app.adapter.IItemCreator#onReleaseItem(android.view.View, java.lang.Object)
     */
    @Override
    public void onReleaseItem(V item, T data) {
    }

}
