package com.leon.support.base.framework.adapter;

import android.view.LayoutInflater;
import android.view.View;

/**
 * <b>Project:</b> liba_support<br>
 * <b>Create Date:</b> 15/9/15<br>
 * <b>Author:</b> Leon<br>
 * <b>Description:</b>
 * 基于HolderAdapter的通用适配器
 * <br>
 */
public class CommonAdapter<T> extends HolderAdapter<T> {

    public CommonAdapter(LayoutInflater inflater, IItemCreator<View, T> creator) {
        super(inflater, creator);
    }

}
