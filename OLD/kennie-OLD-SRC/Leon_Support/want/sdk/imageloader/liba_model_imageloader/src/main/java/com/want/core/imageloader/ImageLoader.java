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
public class ImageLoader implements IImageLoader {

    private static ImageLoader sInctance;

    private IImageLoader imageLoaderImpl;

    private ImageLoader(IImageLoader imageLoader) {
        if (null != imageLoader) {
            imageLoaderImpl = imageLoader;
        } else {
            imageLoaderImpl = new ImageLoaderDefaultImpl();
        }
    }

    public static ImageLoader get() {
        return get(null);
    }

    public static ImageLoader get(IImageLoader imageLoader) {
        if (null == sInctance) {
            synchronized (ImageLoader.class) {
                if (null == sInctance) {
                    sInctance = new ImageLoader(imageLoader);
                }
            }
        }
        return sInctance;
    }

    @Override
    public IImageLoader setConfig(Config config) {
        imageLoaderImpl.setConfig(config);
        return sInctance;
    }

    /**
     * Get instance with {@link Fragment}
     *
     * @param fragment {@link Fragment}
     *
     * @return {@link IImageLoader}
     */
    @Override
    public IImageLoader with(Fragment fragment) {
        return sInctance.imageLoaderImpl.with(fragment);
    }

    /**
     * Get instance with {@link Activity}
     *
     * @param activity {@link Activity}
     *
     * @return {@link IImageLoader}
     */
    @Override
    public IImageLoader with(Activity activity) {
        return sInctance.imageLoaderImpl.with(activity);
    }

    /**
     * Get instance with {@link FragmentActivity}
     *
     * @param activity {@link FragmentActivity}
     *
     * @return {@link IImageLoader}
     */
    @Override
    public IImageLoader with(FragmentActivity activity) {
        return sInctance.imageLoaderImpl.with(activity);
    }

    /**
     * Get instance with {@link Context}
     *
     * @param context {@link Context}
     *
     * @return {@link IImageLoader}
     */
    @Override
    public IImageLoader with(Context context) {
        return sInctance.imageLoaderImpl.with(context);
    }

    /**
     * Load the image with url.
     *
     * @param imageView {@link ImageView}
     * @param url       url
     */
    public void load(ImageView imageView, String url) {
        sInctance.imageLoaderImpl.load(imageView, url);
    }

    /**
     * Load the image with url and callback
     *
     * @param loadListener {@link OnImageLoadListener}
     * @param url          url
     */
    @Override
    public void load(OnImageLoadListener loadListener, String url) {
        sInctance.imageLoaderImpl.load(loadListener, url);
    }

    /**
     * Load the image with url and default loading image res
     *
     * @param imageView  {@link ImageView}
     * @param loadingRes loading image resource
     * @param url        url
     */
    public void load(ImageView imageView, int loadingRes, String url) {
        sInctance.imageLoaderImpl.load(imageView, loadingRes, url);
    }

    /**
     * Load the image with url and default loading image resource, error image resource.
     *
     * @param imageView  {@link ImageView}
     * @param loadingRes loading image resource
     * @param errRes     error image resource
     * @param url        url
     */
    public void load(ImageView imageView, int loadingRes, int errRes, String url) {
        sInctance.imageLoaderImpl.load(imageView, loadingRes, errRes, url);
    }


    /**
     * Load the image with url and size
     *
     * @param imageView {@link ImageView}
     * @param size      width=size[0], height=size[1]
     * @param url       url
     */
    @Override
    public void load(ImageView imageView, int[] size, String url) {
        sInctance.imageLoaderImpl.load(imageView, size, url);
    }
}
