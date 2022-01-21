package com.want.core.imageloader;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

/**
 * <b>Project:</b> android_sdk_base<br>
 * <b>Create Date:</b> 15/10/10<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
@SuppressWarnings("unused")
public interface IImageLoader {

    IImageLoader setConfig(Config config);

    /**
     * Get instance with {@link Fragment}
     *
     * @param fragment {@link Fragment}
     *
     * @return {@link IImageLoader}
     */
    IImageLoader with(Fragment fragment);

    /**
     * Get instance with {@link Activity}
     *
     * @param activity {@link Activity}
     *
     * @return {@link IImageLoader}
     */
    IImageLoader with(Activity activity);

    /**
     * Get instance with {@link FragmentActivity}
     *
     * @param activity {@link FragmentActivity}
     *
     * @return {@link IImageLoader}
     */
    IImageLoader with(FragmentActivity activity);

    /**
     * Get instance with {@link Context}
     *
     * @param context {@link Context}
     *
     * @return {@link IImageLoader}
     */
    IImageLoader with(Context context);


    /**
     * Load the image with url.
     *
     * @param imageView {@link ImageView}
     * @param url       url
     */
    void load(ImageView imageView, String url);

    /**
     * Load the image with url and callback
     *
     * @param loadListener {@link OnImageLoadListener}
     * @param url          url
     */
    void load(OnImageLoadListener loadListener, String url);

    /**
     * Load the image with url and default loading image res
     *
     * @param imageView  {@link ImageView}
     * @param loadingRes loading image resource
     * @param url        url
     */
    void load(ImageView imageView, int loadingRes, String url);

    /**
     * Load the image with url and default loading image resource, error image resource.
     *
     * @param imageView  {@link ImageView}
     * @param loadingRes loading image resource
     * @param errorRes   error image resource
     * @param url        url
     */
    void load(ImageView imageView, int loadingRes, int errorRes, String url);


    /**
     * Load the image with url and size
     *
     * @param imageView {@link ImageView}
     * @param size      width=size[0], height=size[1]
     * @param url       url
     */
    void load(ImageView imageView, int[] size, String url);
}
