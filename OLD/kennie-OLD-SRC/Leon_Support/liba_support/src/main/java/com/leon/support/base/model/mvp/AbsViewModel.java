package com.leon.support.base.model.mvp;

import android.content.Context;
import android.databinding.BaseObservable;

/**
 * <b>Project:</b> project_hotclub<br>
 * <b>Create Date:</b> 8/2/16<br>
 * <b>Author:</b> leon<br>
 * <b>Description:</b> <br>
 */
public class AbsViewModel extends BaseObservable {

    private Context mContext;

    public AbsViewModel() {
    }

    protected AbsViewModel(Context context) {
        this.mContext = context;
    }


    protected String getString(Context context, int id) {
        return context.getString(id);
    }

    protected String getString(Context context, int id, Object... args) {
        return context.getString(id, args);
    }


    protected String getString(int id) {
        return getString(mContext, id);
    }

    protected String getString(int id, Object... args) {
        return getString(mContext, id, args);
    }
}
