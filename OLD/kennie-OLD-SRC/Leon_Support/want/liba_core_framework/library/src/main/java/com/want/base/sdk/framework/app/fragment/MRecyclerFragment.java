package com.want.base.sdk.framework.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.want.base.sdk.R;
import com.want.base.sdk.framework.adapter.IItemCreator;
import com.want.base.sdk.framework.adapter.RecyclerAdapter;
import com.want.base.sdk.framework.app.MFragment;
import com.want.base.sdk.framework.app.fragment.recycler.DividerItemDecoration;

import java.util.List;


/**
 * <b>Project:</b> project_hotclub<br>
 * <b>Create Date:</b> 16/5/17<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * Fragment wrapped with {@link android.support.v7.widget.RecyclerView}
 * <br>
 */
public abstract class MRecyclerFragment<T> extends MFragment implements
                                                             IItemCreator<View, T> {

    private RecyclerView mRecyclerView;
    private RecyclerAdapter<T> mRecyclerAdapter;

    protected View onCreateViewWithInRecycler(LayoutInflater inflater,
                                              @Nullable ViewGroup container,
                                              @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sdk_base_recyclerview, null);
    }

    protected void onSetupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                                                              LinearLayoutManager.VERTICAL,
                                                              false));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));
    }

    protected RecyclerAdapter<T> onCreateAdapter() {
        return new RecyclerAdapter<T>(this);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return onCreateViewWithInRecycler(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        if (null == mRecyclerView) {
            throw new IllegalArgumentException(
                    "Content view must contains 'RecyclerView' with id 'R.id.recyclerview' !");
        }

        onSetupRecyclerView(mRecyclerView);

        mRecyclerAdapter = onCreateAdapter();
        mRecyclerView.setAdapter(mRecyclerAdapter);
    }

    protected RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    protected RecyclerAdapter<T> getAdapter() {
        return mRecyclerAdapter;
    }

    public void addData(List<T> datas) {
        mRecyclerAdapter.addData(datas);
        mRecyclerAdapter.notifyDataSetChanged();
    }

    public void updateData(List<T> datas) {
        mRecyclerAdapter.updateData(datas);
        notifyDataSetChanged();
    }

    public List<T> getDataSet() {
        return mRecyclerAdapter.getDataSet();
    }

    public void notifyDataSetChanged() {
        mRecyclerAdapter.notifyDataSetChanged();
    }


    protected abstract View onCreateItemView(LayoutInflater inflater, int viewType);

    protected abstract void onUpdateItemView(View view, int pos, T data, int viewType);

    @Override
    public final View onCreateItem(LayoutInflater inflater, int viewType, T data) {
        return this.onCreateItemView(inflater, viewType);
    }

    @Override
    public final void onUpdateItem(View item, int pos, T data) {
        this.onUpdateItemView(item, pos, data, getAdapter().getItemViewType(pos));
    }

    @Override
    public final void onReleaseItem(View item, T data) {

    }
}
