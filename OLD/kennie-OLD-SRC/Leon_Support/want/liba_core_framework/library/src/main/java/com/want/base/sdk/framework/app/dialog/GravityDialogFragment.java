package com.want.base.sdk.framework.app.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.want.base.sdk.R;

/**
 * <b>Project:</b> project_hotclub<br>
 * <b>Create Date:</b> 16/4/29<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public abstract class GravityDialogFragment extends BaseDialogFragment {

    protected abstract View onCreateDialogContentView(Bundle savedInstanceState);

    protected void onConfigDialog(Dialog dialog) {
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.SDKTheme_Dialog_Gravity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // must be called before set content
        dialog.setContentView(onCreateDialogContentView(savedInstanceState));
        onConfigDialog(dialog);
        return dialog;
    }
}
