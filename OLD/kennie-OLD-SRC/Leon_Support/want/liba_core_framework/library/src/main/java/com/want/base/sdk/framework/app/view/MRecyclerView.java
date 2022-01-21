package com.want.base.sdk.framework.app.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.want.base.sdk.framework.adapter.IItemCreator;
import com.want.base.sdk.framework.adapter.RecyclerAdapter;
import com.want.base.sdk.framework.app.fragment.recycler.DividerItemDecoration;

import java.util.List;


/**
 * <b>Project:</b> project_hotclub<br>
 * <b>Create Date:</b> 7/18/16<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public abstract class MRecyclerView<T> extends RecyclerView implements IItemCreator<View, T> {

    private RecyclerAdapter<T> mAdapter;


    public MRecyclerView(Context context) {
        super(context);
        init(context);
    }

    public MRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        mAdapter = onCreateAdapter();
        onConfiguration(context);
        setAdapter(mAdapter);
    }

    protected RecyclerAdapter<T> onCreateAdapter() {
        return new RecyclerAdapter<T>(this);
    }

    protected void onConfiguration(Context context) {
        setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        addItemDecoration(new DividerItemDecoration(context));
    }

    protected abstract View onCreateItemView(LayoutInflater inflater, int viewType);

    protected abstract void onUpdateItemView(View view, int pos, T data, int viewType);

    @Override
    @SuppressWarnings("unchecked")
    public RecyclerAdapter<T> getAdapter() {
        return (RecyclerAdapter<T>) super.getAdapter();
    }

    public void addData(List<T> datas) {
        mAdapter.addData(datas);
        mAdapter.notifyDataSetChanged();
    }

    public void updateData(List<T> datas) {
        mAdapter.updateData(datas);
        mAdapter.notifyDataSetChanged();
    }

    public List<T> getDataset() {
        return mAdapter.getDataSet();
    }

    @Override
    public final View onCreateItem(LayoutInflater inflater, int pos, T data) {
        return this.onCreateItemView(inflater, pos);
    }

    @Override
    public final void onUpdateItem(View item, int pos, T data) {
        final int id = item.getId();
        // 1) if NO_ID, we update the item view always
        if (NO_ID == id) {
            this.onUpdateItemView(item, pos, data, getAdapter().getItemViewType(pos));
            // 2) if has ID and item view attached to parent, we update the item view
        } else if (null != findViewById(id)) {
            this.onUpdateItemView(item, pos, data, getAdapter().getItemViewType(pos));
        }
        // 3) otherwise, we ignore
        {
            // ... ignore
        }
    }

    @Override
    public void onReleaseItem(View item, T data) {

    }
}
