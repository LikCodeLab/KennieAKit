package com.want.base.sdk.framework.app.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.want.base.sdk.R;
import com.want.base.sdk.framework.adapter.AbsAdapter;
import com.want.base.sdk.framework.adapter.HolderAdapter;
import com.want.base.sdk.framework.adapter.IItemCreator;
import com.want.base.sdk.framework.app.MFragment;

import java.util.List;

/**
 * <b>Project:</b> android_sdk_base<br>
 * <b>Create Date:</b> 15/9/25<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * Package listivew and adapter.
 * <br>
 */
@SuppressWarnings("unused")
public abstract class MListFragment<T> extends MFragment implements IItemCreator<View, T> {

    private ListView mListView;
    private View mHeadView;
    private View mFooterView;

    private HolderAdapter<T> mListAdapter;


    /**
     * See {@link IItemCreator#onCreateItem(LayoutInflater, int, Object)}
     */
    protected abstract View onCreateItemView(LayoutInflater inflater, int pos, T data);

    /**
     * See {@link IItemCreator#onUpdateItem(Object, int, Object)}
     */
    protected abstract void onUpdateItemView(View view, int pos, T data);

    /**
     * Create view that contains listview
     *
     * @param inflater           {@link LayoutInflater}
     * @param container          {@link ViewGroup}
     * @param savedInstanceState {@link Bundle}
     *
     * @return View contains listview
     */
    protected View onCreateListView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sdk_base_listview, null);
    }

    /**
     * Create the head view of listview
     *
     * @param listView {@link ListView}
     *
     * @return {@link View}
     */
    protected View onCreateHeadView(ListView listView) {
        return null;
    }

    /**
     * Create the footer view of listview
     *
     * @param listView {@link ListView}
     *
     * @return {@link View}
     */
    protected View onCreateFooterView(ListView listView) {
        return null;
    }

    /**
     * Config the listview before.
     *
     * @param listView {@link ListView}
     */
    protected void onConfigListView(ListView listView) {

    }

    /**
     * Create your {@link HolderAdapter}
     *
     * @param inflater    {@link LayoutInflater}
     * @param itemCreator {@link IItemCreator}
     *
     * @return {@link HolderAdapter}
     */
    protected HolderAdapter<T> onCreateAdapter(LayoutInflater inflater,
                                               IItemCreator<View, T> itemCreator) {
        return new HolderAdapter<T>(inflater, itemCreator);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return onCreateListView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListView = (ListView) view.findViewById(R.id.listview);
        if (null == mListView) {
            throw new IllegalArgumentException(
                    "You must define your listview with id '@id/listview' !!");
        }
        onConfigListView(mListView);

        mHeadView = onCreateHeadView(mListView);
        if (null != mHeadView) {
            mListView.addHeaderView(mHeadView);
        }

        mFooterView = onCreateFooterView(mListView);
        if (null != mFooterView) {
            mListView.addFooterView(mFooterView);
        }

        mListAdapter = onCreateAdapter(getLayoutInflater(savedInstanceState), this);
        mListView.setAdapter(mListAdapter);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * Get this {@link ListView}
     *
     * @return {@link ListView}
     */
    public ListView getListView() {
        return this.mListView;
    }


    /**
     * Get the listview's Adapter
     *
     * @return {@link AbsAdapter}
     */
    public AbsAdapter<View, T> getAdapter() {
        return mListAdapter;
    }

    /**
     * Get the adapter's dataset
     *
     * @return {@link List}
     */
    public List<T> getDataSet() {
        return mListAdapter.getDataSet();
    }

    protected void setEmptyView(int view) {
        setEmptyView(view, -1);
    }

    protected void setEmptyView(int view, ViewGroup.LayoutParams lp) {
        setEmptyView(view, -1, lp);
    }

    protected void setEmptyView(int view, int index) {
        setEmptyView(view, index, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                             ViewGroup.LayoutParams.MATCH_PARENT));
    }

    protected void setEmptyView(int view, int index, ViewGroup.LayoutParams lp) {
        setEmptyView(LayoutInflater.from(getActivity()).inflate(view, null), index, lp);
    }

    protected void setEmptyView(View emptyView) {
        setEmptyView(emptyView, -1);
    }

    protected void setEmptyView(View emptyView, int index) {
        setEmptyView(emptyView, index, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                                  ViewGroup.LayoutParams.MATCH_PARENT));
    }

    protected void setEmptyView(View emptyView, ViewGroup.LayoutParams lp) {
        setEmptyView(emptyView, -1, lp);
    }

    protected void setEmptyView(View emptyView, int index, ViewGroup.LayoutParams lp) {
        if (null != emptyView.getParent()) {
            ((ViewGroup) emptyView.getParent()).removeView(emptyView);
        }

        final ListView listView = getListView();
        ((ViewGroup) listView.getParent()).addView(emptyView, index, lp);
        listView.setEmptyView(emptyView);
    }

    protected void showEmptyView(boolean show) {
        final View view = getListView().getEmptyView();
        if (null == view) {
            return;
        }

        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    /**
     * Append datas to adapter.
     *
     * @param datas {@link List}
     */
    public void addData(List<T> datas) {
        addData(datas, true);
    }

    /**
     * Append datas to adapter and update the listview
     *
     * @param datas  {@link List}
     * @param update true, update the listview; false, otherwise.
     */
    public void addData(List<T> datas, boolean update) {
        if (null != datas && datas.size() != 0) {
            mListAdapter.addData(datas);
            if (update) {
                mListAdapter.notifyDataSetChanged();
            }
        }
    }

    /**
     * Clear and reset datas to adapter.
     *
     * @param datas {@link List}
     */
    public void updateData(List<T> datas) {
        updateData(datas, true);
    }

    /**
     * Clear and reset datas to adapter and update the listview.
     *
     * @param datas  {@link List}
     * @param update true, update the listview; false, otherwise.
     */
    public void updateData(List<T> datas, boolean update) {
        mListAdapter.updateData(datas);
        if (update) {
            notifyDataSetChanged();
        }
    }

    /**
     * See {@link BaseAdapter#notifyDataSetChanged()}
     */
    public void notifyDataSetChanged() {
        mListAdapter.notifyDataSetChanged();
    }

    /**
     * See {@link BaseAdapter#notifyDataSetInvalidated()}
     */
    public void notifyDataSetInvalidated() {
        mListAdapter.notifyDataSetInvalidated();
    }

    /**
     * 创建view的时候调用此方法,一般导入布局文件即可
     *
     * @param inflater {@link LayoutInflater}
     * @param pos
     * @param data
     *
     * @return
     */
    @Override
    public final View onCreateItem(LayoutInflater inflater, int pos, T data) {
        return this.onCreateItemView(inflater, pos, data);
    }

    /**
     * 更新view内容的时候调用此方法
     *
     * @param item {@link View} 需要更新的item
     * @param pos
     * @param data
     */
    @Override
    public final void onUpdateItem(View item, int pos, T data) {
        this.onUpdateItemView(item, pos, data);
    }

    /**
     * 释放view的时候调用此方法
     *
     * @param item {@link View} 需要释放的 item
     * @param data
     */
    @Override
    public void onReleaseItem(View item, T data) {

    }
}
