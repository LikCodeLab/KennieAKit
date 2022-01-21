package com.leon.support.base.framework.adapter;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * <b>Project:</b> liba_support<br>
 * <b>Create Date:</b> 15/6/14<br>
 * <b>Author:</b> Leon<br>
 * <b>Description:</b> <br>
 */
public class CommonFragmentPagerAdapter<T> extends CommonPagerAdapter {

    private static final String TAG = "FragmentPagerAdapter";
    private static final boolean DEBUG = true;
    private FragmentManager mFragmentManager;

    private FragmentTransaction mCurTransaction = null;
    private Fragment mCurrentPrimaryItem = null;

    private SparseArray<Fragment> mCacheView = new SparseArray<>();

    @SuppressWarnings("unchecked")
    public CommonFragmentPagerAdapter(FragmentManager fragmentManager, LayoutInflater inflater,
                                      IItemCreator<Fragment, T> creator) {
        super(inflater, creator);
        this.mFragmentManager = fragmentManager;
    }

    protected void addScrapFragment(int pos, Fragment fragment) {
        mCacheView.put(pos, fragment);
    }

    protected Fragment getScrapFragment(int pos) {
        final int size = mCacheView.size();
        if (size > 0) {
            Fragment reference = mCacheView.get(pos);
            if (null != reference) {
                mCacheView.remove(pos);
                return reference;
            }

            int index = size - 1;
            reference = mCacheView.valueAt(index);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                mCacheView.removeAt(index);
            } else {
                final int key = mCacheView.keyAt(index);
                mCacheView.remove(key);
            }
            return reference;
        }
        return null;
    }


    /**
     * Return the Fragment associated with a specified position.
     */
    @SuppressWarnings("unchecked")
    public Fragment getItem(int position) {
        return (Fragment) getCreator().onCreateItem(getLayoutInflater(),
                position,
                getDataItemAt(position));
    }

    @Override
    public void startUpdate(ViewGroup container) {
    }

    @SuppressLint("CommitTransaction")
    @SuppressWarnings("unchecked")
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (mCurTransaction == null) {
            mCurTransaction = mFragmentManager.beginTransaction();
        }

        final long itemId = getItemId(position);

        // Do we already have this fragment?
        String name = makeFragmentName(container.getId(), itemId);
        Fragment fragment = mFragmentManager.findFragmentByTag(name);
        if (fragment != null) {
            if (DEBUG) {
                Log.v(TAG, "Attaching item #" + itemId + ": tag=" + name + ": f=" + fragment);
            }
            mCurTransaction.attach(fragment);
        } else {
            fragment = getScrapFragment(position);
            if (null == fragment) {
                fragment = getItem(position);
            }

            if (DEBUG) {
                Log.v(TAG, "Adding item #" + itemId + ": tag=" + name + ": f=" + fragment);
            }
            mCurTransaction.add(container.getId(), fragment,
                    makeFragmentName(container.getId(), itemId));
        }

        getCreator().onUpdateItem(fragment, position, getDataItemAt(position));
        if (fragment != mCurrentPrimaryItem) {
            fragment.setMenuVisibility(false);
            fragment.setUserVisibleHint(false);
        }
//        mCurTransaction.commitAllowingStateLoss();
//        mFragmentManager.executePendingTransactions();

        return fragment;
    }

    @SuppressLint("CommitTransaction")
    @SuppressWarnings("unchecked")
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (mCurTransaction == null) {
            mCurTransaction = mFragmentManager.beginTransaction();
        }
        if (DEBUG) {
            final String name = makeFragmentName(container.getId(), position);
            Log.v(TAG, "Detaching item #" + getItemId(position) + ": tag=" + name + ": f=" + object
                    + " v=" + ((Fragment) object).getView());
        }
        final Fragment fragment = (Fragment) object;
        addScrapFragment(position, fragment);
        mCurTransaction.detach(fragment);
        mCurTransaction.remove(fragment);
        getCreator().onReleaseItem(fragment, object);

        mCurTransaction.commitAllowingStateLoss();
        mCurTransaction = null;
        mFragmentManager.executePendingTransactions();
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        Fragment fragment = (Fragment) object;
        if (fragment != mCurrentPrimaryItem) {
            if (mCurrentPrimaryItem != null) {
                mCurrentPrimaryItem.setMenuVisibility(false);
                mCurrentPrimaryItem.setUserVisibleHint(false);
            }
            if (fragment != null) {
                fragment.setMenuVisibility(true);
                fragment.setUserVisibleHint(true);
            }
            mCurrentPrimaryItem = fragment;
        }
    }


    @Override
    public void finishUpdate(ViewGroup container) {
        if (mCurTransaction != null) {
            mCurTransaction.commitAllowingStateLoss();
            mCurTransaction = null;
            mFragmentManager.executePendingTransactions();
        }
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return ((Fragment) object).getView() == view;
    }

    /**
     * Return a unique identifier for the item at the given position.
     * <p/>
     * <p>The default implementation returns the given position.
     * Subclasses should override this method if the positions of items can change.</p>
     *
     * @param position Position within this adapter
     *
     * @return Unique identifier for the item at position
     */
    public long getItemId(int position) {
        return position;
    }

    public static String makeFragmentName(int viewId, long id) {
        return "android:switcher:" + viewId + ":" + id;
    }
}
