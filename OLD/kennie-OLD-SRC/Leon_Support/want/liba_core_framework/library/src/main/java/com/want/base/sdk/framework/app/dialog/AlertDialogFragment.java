package com.want.base.sdk.framework.app.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

/**
 * <b>Project:</b> HollyWant<br>
 * <b>Create Date:</b> 15/9/30<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * 通用的 AlertDialog.
 * <br>
 */
public class AlertDialogFragment extends BaseDialogFragment {

    private int mTitleId;
    private String mTitle;

    private int messageId;
    private CharSequence message;

    private int mPositiveButtonTextId;
    private CharSequence mPositiveButtonText;
    private DialogInterface.OnClickListener mPositiveButtonListener;
    private int mNegativeButtonTextId;
    private CharSequence mNegativeButtonText;
    private DialogInterface.OnClickListener mNegativeButtonListener;
    private int mNeutralButtonTextId;
    private CharSequence mNeutralButtonText;
    private DialogInterface.OnClickListener mNeutralButtonListener;

    private int mViewId;
    private View mView;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // setup custom view
        if (0 != mViewId) {
            mView = LayoutInflater.from(getActivity()).inflate(mViewId, null);
        }

        if (null != mView) {
            builder.setView(mView);
        }

        // setup title
        if (0 != mTitleId) {
            mTitle = getString(mTitleId);
        }
        if (!TextUtils.isEmpty(mTitle)) {
            builder.setTitle(mTitle);
        }

        // setup message
        if (0 != messageId) {
            message = getString(messageId);
        }
        if (!TextUtils.isEmpty(message)) {
            builder.setMessage(message);
        }

        // setup negative button
        if (0 != mNegativeButtonTextId) {
            mNegativeButtonText = getString(mNegativeButtonTextId);
        }
        if (!TextUtils.isEmpty(mNegativeButtonText)) {
            builder.setNegativeButton(mNegativeButtonText, mNegativeButtonListener);
        }

        // setup neutral button
        if (0 != mNeutralButtonTextId) {
            mNeutralButtonText = getString(mNeutralButtonTextId);
        }
        if (!TextUtils.isEmpty(mNeutralButtonText)) {
            builder.setNeutralButton(mNeutralButtonText, mNeutralButtonListener);
        }

        // setup positive button
        if (0 != mPositiveButtonTextId) {
            mPositiveButtonText = getString(mPositiveButtonTextId);
        }
        if (!TextUtils.isEmpty(mPositiveButtonText)) {
            builder.setPositiveButton(mPositiveButtonText, mPositiveButtonListener);
        }

        return builder.create();
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setTitle(int mTitleId) {
        this.mTitleId = mTitleId;
    }

    public void setView(View mView) {
        this.mView = mView;
    }

    public void setView(int viewId) {
        this.mViewId = viewId;
    }

    public void setMessage(int messageId) {
        this.messageId = messageId;
    }

    public void setMessage(CharSequence message) {
        this.message = message;
    }

    public void setNegativeButton(CharSequence mNegativeButtonText,
                                  DialogInterface.OnClickListener listener) {
        this.mNegativeButtonText = mNegativeButtonText;
        this.mNegativeButtonListener = listener;
    }

    public void setNegativeButton(int mNegativeButtonTextId, DialogInterface.OnClickListener listener) {
        this.mNegativeButtonTextId = mNegativeButtonTextId;
        this.mNegativeButtonListener = listener;
    }

    public void setNeutralButton(CharSequence mNeutralButtonText, DialogInterface.OnClickListener listener) {
        this.mNeutralButtonText = mNeutralButtonText;
        this.mNeutralButtonListener = listener;
    }

    public void setNeutralButton(int mNeutralButtonTextId, DialogInterface.OnClickListener listener) {
        this.mNeutralButtonTextId = mNeutralButtonTextId;
        this.mNeutralButtonListener = listener;
    }

    public void setPositiveButton(CharSequence mPositiveButtonText,
                                  DialogInterface.OnClickListener listener) {
        this.mPositiveButtonText = mPositiveButtonText;
        this.mPositiveButtonListener = listener;
    }

    public void setPositiveButton(int mPositiveButtonTextId, DialogInterface.OnClickListener listener) {
        this.mPositiveButtonTextId = mPositiveButtonTextId;
        this.mPositiveButtonListener = listener;
    }
}
