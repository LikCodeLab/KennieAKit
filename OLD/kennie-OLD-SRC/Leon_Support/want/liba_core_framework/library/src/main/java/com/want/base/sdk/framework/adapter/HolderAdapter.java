package com.want.base.sdk.framework.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.want.base.sdk.R;

/**
 * <b>Project:</b> almanac<br>
 * <b>Create Date:</b> 15/6/14<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * HolderView模式的Adapter。
 * <br>
 */
public class HolderAdapter<T> extends AbsAdapter<View, T> {

    public HolderAdapter(LayoutInflater inflater, IItemCreator<View, T> creator) {
        super(inflater, creator);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        // Adapter内嵌Adapter时, 一般的holderview处理方式依然会出现问题, 采用下面的方式来处理可以避免.
        if (null == convertView || !(convertView.getTag(R.id.tag_holder_default) instanceof ViewHolder)) {
            holder = new ViewHolder();
            convertView = mItemCreator.onCreateItem(mLayoutInflater, position, getItem(position));
            holder.view = convertView;
            convertView.setTag(R.id.tag_holder_default, holder);
        } else {
            holder = (ViewHolder) convertView.getTag(R.id.tag_holder_default);
            //释放当前的View的数据
            mItemCreator.onReleaseItem(convertView, getItem(position));
        }
        mItemCreator.onUpdateItem(holder.view, position, getItem(position));
        return convertView;
    }

    private static class ViewHolder {
        public View view;
    }
}
