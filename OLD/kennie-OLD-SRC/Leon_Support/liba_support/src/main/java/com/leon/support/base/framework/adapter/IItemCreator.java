package com.leon.support.base.framework.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;

/**
 * <b>Project:</b> liba_support<br>
 * <b>Create Date:</b> 15/9/15<br>
 * <b>Author:</b> Leon<br>
 * <b>Description:</b>
 * 与{@link BaseAdapter}配合，操作View的接口。
 * <br>
 */
public interface IItemCreator<V, T> {

    /**
     * 创建view的时候调用此方法,一般导入布局文件即可
     *
     * @param inflater {@link LayoutInflater}
     * @param pos
     * @param data
     *
     * @return
     */
    V onCreateItem(LayoutInflater inflater, int pos, T data);

    /**
     * 更新view内容的时候调用此方法
     *
     * @param item {@link View} 需要更新的item
     * @param pos
     * @param data
     */
    void onUpdateItem(V item, int pos, T data);

    /**
     * 释放view的时候调用此方法
     *
     * @param item {@link View} 需要释放的 item
     * @param data
     */
    void onReleaseItem(V item, T data);
}