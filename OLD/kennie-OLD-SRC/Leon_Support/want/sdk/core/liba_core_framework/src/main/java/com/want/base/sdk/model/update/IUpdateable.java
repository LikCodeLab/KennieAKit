package com.want.base.sdk.model.update;

import android.content.Context;

import java.io.File;

/**
 * <b>Project:</b> apps<br>
 * <b>Create Date:</b> 15/11/16<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * Interface of update agent.
 * <br>
 */
public interface IUpdateable {

    /**
     * Normal update
     *
     * @param context context
     */
    void update(Context context);

    /**
     * Silent update. APKs will be downloaded on the background.
     *
     * @param context context
     */
    void silentUpdate(Context context);

    /**
     * Force update
     *
     * @param context context
     */
    void forceUpdate(Context context);

    /**
     * Install the apk file
     *
     * @param context context
     * @param file    {@link File}
     */
    void install(Context context, File file);

    /**
     * Setup agent config
     *
     * @param builder {@link com.want.base.sdk.model.update.UpdateAgent.Builder}
     */
    void setupConfig(Context context, UpdateAgent.Builder builder);
}
