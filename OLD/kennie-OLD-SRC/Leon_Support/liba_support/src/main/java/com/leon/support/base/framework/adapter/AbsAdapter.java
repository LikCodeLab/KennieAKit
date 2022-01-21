package com.leon.support.base.framework.adapter;

import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * <b>Project:</b> liba_support<br>
 * <b>Create Date:</b> 16/9/19<br>
 * <b>Author:</b> Leon<br>
 * <b>Description:</b>
 * 继承自BaseAdapter的抽象类，封装了一些功能，使用者不用关心视图和数据以外的东西，详见方法说明。
 * <br>
 */
@SuppressWarnings("unused")
public abstract class AbsAdapter<V, T> extends BaseAdapter {

    /** 数据集 */
    protected List<T> mDataSet = null;
    protected LayoutInflater mLayoutInflater = null;
    protected IItemCreator<V, T> mItemCreator = null;

    /**
     * 创建Adapter，需要给定View创建接口。
     *
     * @param inflater
     * @param creator
     */
    public AbsAdapter(LayoutInflater inflater, IItemCreator<V, T> creator) {
        this.mLayoutInflater = inflater;
        this.mItemCreator = creator;
    }

    /**
     * 清空以前的数据并重新赋值
     *
     * @param data {@link List}
     */
    public void updateData(List<T> data) {
        this.mDataSet = data;
    }

    /**
     * 向数据集中添加新的数据
     *
     * @param data {@link List}
     */
    public void addData(List<T> data) {
        if (null == data || data.size() == 0) {
            return;
        }

        if (null == mDataSet) {
            updateData(data);
            return;
        }

        mDataSet.addAll(data);
    }

    /**
     * 从数据集中移除数据
     *
     * @param position
     */
    public void removeData(int position) {
        if (null != mDataSet) {
            mDataSet.remove(position);
        }
    }

    /**
     * 清空数据集
     */
    public void clearData() {
        if (null != mDataSet) {
            mDataSet.clear();
        }
    }

    /**
     * 获取数据集
     *
     * @return
     */
    public List<T> getDataSet() {
        return this.mDataSet;
    }

    @Override
    public int getCount() {
        return null == mDataSet ? 0 : mDataSet.size();
    }

    @Override
    public T getItem(int position) {
        return null == mDataSet ? null : mDataSet.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
