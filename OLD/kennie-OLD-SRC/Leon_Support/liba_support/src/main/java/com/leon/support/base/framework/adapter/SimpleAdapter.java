package com.leon.support.base.framework.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * <b>Project:</b> liba_support<br>
 * <b>Create Date:</b> 15/6/14<br>
 * <b>Author:</b> Leon<br>
 * <b>Description:</b> <br>
 */
public class SimpleAdapter<T> extends AbsAdapter<View, T> {

    public SimpleAdapter(LayoutInflater inflater, IItemCreator<View, T> creator) {
        super(inflater, creator);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            convertView = (View) mItemCreator.onCreateItem(mLayoutInflater,
                    position,
                    mDataSet.get(position));
        }
        mItemCreator.onUpdateItem(convertView, position, mDataSet.get(position));
        return convertView;
    }
}
