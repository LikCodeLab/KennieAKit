package com.leon.support.base.framework.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leon.support.R;
import com.leon.support.base.MFragment;
import com.leon.support.base.framework.adapter.IItemCreator;
import com.leon.support.base.framework.adapter.RecyclerAdapter;
import com.leon.support.base.framework.recycler.DividerItemDecoration;

import java.util.List;

/**
 * <b>Project:</b> liba_support<br>
 * <b>Create Date:</b> 16/10/15<br>
 * <b>Author:</b> LearKoo<br>
 * <b>Description:</b>
 * Fragment wrapped with {@link RecyclerView}
 * <br>
 */
public abstract class MRecyclerFragment<T> extends MFragment implements
        IItemCreator<View, T> {

    private RecyclerView mRecyclerView;
    private RecyclerAdapter<T> mRecyclerAdapter;

    protected View onCreateViewWithInRecycler(LayoutInflater inflater,
                                              @Nullable ViewGroup container,
                                              @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.base_recyclerview, null);
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
        mRecyclerView = (RecyclerView) view.findViewById(R.id.base_recyclerview);
        if (null == mRecyclerView) {
            throw new IllegalArgumentException(
                    "Content view must contains 'RecyclerView' with id 'R.id.support_recyclerview' !");
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
