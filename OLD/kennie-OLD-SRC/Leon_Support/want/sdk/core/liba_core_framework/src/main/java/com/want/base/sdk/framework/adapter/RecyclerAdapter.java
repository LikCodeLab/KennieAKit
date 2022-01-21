package com.want.base.sdk.framework.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * <b>Project:</b> apps<br>
 * <b>Create Date:</b> 15/11/12<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
@SuppressWarnings("unused")
public class RecyclerAdapter<T> extends RecyclerView.Adapter {

    /** 数据集 */
    protected List<T> mDataSet = null;
    protected LayoutInflater mLayoutInflater = null;
    private IItemCreator<View, T> mItemCreator;

    public RecyclerAdapter(IItemCreator<View, T> creator) {
        this.mItemCreator = creator;
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        mItemCreator.onReleaseItem(holder.itemView, null);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        final View view = mItemCreator.onCreateItem(LayoutInflater.from(context), viewType, null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        mItemCreator.onUpdateItem(holder.itemView, position, getItem(position));
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
    public int getItemCount() {
        return null == mDataSet ? 0 : mDataSet.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public T getItem(int posistion) {
        return null == mDataSet ? null : mDataSet.get(posistion);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    private static class Holder extends RecyclerView.ViewHolder {

        public Holder(View itemView) {
            super(itemView);
        }
    }
}
