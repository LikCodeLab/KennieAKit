package com.want.base.sdk.framework.adapter;

import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * <b>Project:</b> almanac<br>
 * <b>Create Date:</b> 15/6/14<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * 基于{@link PagerAdapter}的通用适配器。
 * <br>
 */
public class CommonPagerAdapter<T> extends PagerAdapter {

    private List<T> mDataSet = null;
    private LayoutInflater mLayoutInflater = null;
    private IItemCreator<View, T> mCreator = null;
    private boolean mIsForceUpdateView = false;

    private SparseArray<View> mCacheView = new SparseArray<>();

    public CommonPagerAdapter(LayoutInflater inflater,
                              IItemCreator<View, T> creator) {
        this.mLayoutInflater = inflater;
        this.mCreator = creator;
    }

    protected IItemCreator<View, T> getCreator() {
        return mCreator;
    }

    protected LayoutInflater getLayoutInflater() {
        return mLayoutInflater;
    }

    private void addScrapView(int pos, View view) {
        mCacheView.put(pos, view);
    }

    private View getScrapView(int pos) {
        final int size = mCacheView.size();
        if (size > 0) {
            View view = mCacheView.get(pos);
            if (null != view) {
                mCacheView.remove(pos);
                return view;
            }

            int index = size - 1;
            view = mCacheView.valueAt(index);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                mCacheView.removeAt(index);
            } else {
                final int key = mCacheView.keyAt(index);
                mCacheView.remove(key);
            }
            return view;
        }
        return null;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        addScrapView(position, view);
        container.removeView(view);
        int index = Math.max(0, Math.min(position, getCount() - 1));
        mCreator.onReleaseItem(view, getItem(index));
    }

    public T getItem(int postion) {
        return null == mDataSet ? null : mDataSet.get(postion);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = getScrapView(position);
        if (null == view) {
            view = mCreator.onCreateItem(mLayoutInflater, position, getItem(position));
        }
        mCreator.onUpdateItem(view, position, getItem(position));
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return null == mDataSet ? 0 : mDataSet.size();
    }

    public T getDataItemAt(int position) {
        return null == mDataSet ? null : mDataSet.get(position);
    }

    /**
     * 设置是否强制更新
     *
     * @param isForce
     */
    public void setForceUpdate(boolean isForce) {
        this.mIsForceUpdateView = isForce;
    }

    @Override
    public int getItemPosition(Object object) {
        if (mIsForceUpdateView) {
            return POSITION_NONE;
        }
        return super.getItemPosition(object);
    }

    /**
     * 向数据集更新数据
     *
     * @param data
     */
    public void updateData(List<T> data) {
        mDataSet = data;
    }

    /**
     * 向数据集追加数据
     *
     * @param data
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
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
