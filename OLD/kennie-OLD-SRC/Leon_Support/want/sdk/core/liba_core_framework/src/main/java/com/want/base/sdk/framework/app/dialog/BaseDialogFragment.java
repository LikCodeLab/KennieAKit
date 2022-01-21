package com.want.base.sdk.framework.app.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatDialog;

/**
 * <b>Project:</b> HollyWant<br>
 * <b>Create Date:</b> 15/9/30<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class BaseDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AppCompatDialog(getActivity(), getTheme());
    }

    @Override
    public AppCompatDialog getDialog() {
        return (AppCompatDialog) super.getDialog();
    }

    public int show(FragmentTransaction transaction) {
        return super.show(transaction, getTagString());
    }

    public void show(FragmentManager manager) {
        super.show(manager, getTagString());
    }

    /**
     * Dismiss the fragment and its dialog.  If the fragment was added to the
     * back stack, all back stack state up to and including this entry will
     * be popped.  Otherwise, a new transaction will be committed to remove
     * the fragment.
     */
    @Override
    public void dismiss() {
        super.dismissAllowingStateLoss();
    }

    protected String getTagString(){
        return getClass().getSimpleName();
    }
}
