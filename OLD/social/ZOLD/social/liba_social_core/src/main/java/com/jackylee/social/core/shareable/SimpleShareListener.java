package com.jackylee.social.core.shareable;

import android.content.Context;
import android.widget.Toast;

import com.jackylee.social.core.R;
import com.jackylee.social.core.platform.IPlatform;

/**
 * <b>Project:</b> liba_social_core<br>
 * <b>Create Date:</b> 16/9/19<br>
 * <b>Author:</b> LearKoo<br>
 * <b>Description:</b>
 *
 * <br>
 */
public class SimpleShareListener implements ShareActionListener {


    private Context mContext;

    public SimpleShareListener(Context context) {
        this.mContext = context;
    }

    protected void onNotifySuccess(IPlatform platform, String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    protected void onNotifyCancel(IPlatform platform, String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    protected void onNotifyError(IPlatform platform, String msg, Throwable e) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(IPlatform platform) {
        onNotifySuccess(platform, mContext.getString(R.string.share_result_success));
    }

    @Override
    public void onError(IPlatform platform, Throwable e) {
        onNotifyError(platform, mContext.getString(R.string.share_result_error), e);
    }

    @Override
    public void onCancel(IPlatform platform) {
        onNotifyCancel(platform, mContext.getString(R.string.share_result_cancel));
    }
}
