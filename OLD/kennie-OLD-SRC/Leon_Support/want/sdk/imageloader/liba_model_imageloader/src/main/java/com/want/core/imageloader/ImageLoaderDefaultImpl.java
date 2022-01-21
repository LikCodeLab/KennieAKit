package com.want.core.imageloader;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

/**
 * <b>Project:</b> android_sdk_base<br>
 * <b>Create Date:</b> 15/10/10<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class ImageLoaderDefaultImpl implements IImageLoader {


    private RequestManager mRequestManager;
    private RequestManager.DefaultOptions mDefaultOptions;

    ImageLoaderDefaultImpl() {
    }

    @Override
    public IImageLoader setConfig(final Config config) {
        if (null != config) {
            mDefaultOptions = new RequestManager.DefaultOptions() {
                @Override
                public <T> void apply(GenericRequestBuilder<T, ?, ?, ?> builder) {
                    if (0 != config.loadingRes) {
                        builder.placeholder(config.loadingRes);
                    }

                    if (0 != config.errorRes) {
                        builder.error(config.errorRes);
                    }

                    if (config.width != 0 && config.height != 0) {
                        builder.override(config.width, config.height);
                    }
                }
            };
        }
        return this;
    }

    public IImageLoader with(Fragment fragment) {
        mRequestManager = Glide.with(fragment);
        if (null != mDefaultOptions) {
            mRequestManager.setDefaultOptions(mDefaultOptions);
        }
        return this;
    }

    public IImageLoader with(Activity activity) {
        mRequestManager = Glide.with(activity);
        if (null != mDefaultOptions) {
            mRequestManager.setDefaultOptions(mDefaultOptions);
        }
        return this;
    }

    public IImageLoader with(FragmentActivity activity) {
        mRequestManager = Glide.with(activity);
        if (null != mDefaultOptions) {
            mRequestManager.setDefaultOptions(mDefaultOptions);
        }
        return this;
    }

    public IImageLoader with(Context context) {
        mRequestManager = Glide.with(context);
        if (null != mDefaultOptions) {
            mRequestManager.setDefaultOptions(mDefaultOptions);
        }
        return this;
    }

    /**
     * Load the image with url.
     *
     * @param imageView {@link ImageView}
     * @param url       url
     */
    public void load(ImageView imageView, String url) {
        mRequestManager.load(url).into(imageView);
    }

    /**
     * Load the image with url and callback
     *
     * @param loadListener {@link OnImageLoadListener}
     * @param url          url
     */
    @Override
    public void load(final OnImageLoadListener loadListener, String url) {
        mRequestManager.load(url).into(new SimpleTarget<GlideDrawable>() {
            @Override
            public void onResourceReady(GlideDrawable glideDrawable,
                                        GlideAnimation<? super GlideDrawable> glideAnimation) {
                if (null != loadListener) {
                    loadListener.onImageLoad(glideDrawable);
                }
            }
        });
    }

    /**
     * Load the image with url and default loading image res
     *
     * @param imageView  {@link ImageView}
     * @param loadingRes loading image resource
     * @param url        url
     */
    public void load(ImageView imageView, int loadingRes, String url) {
        mRequestManager.load(url).placeholder(loadingRes).into(imageView);
    }

    /**
     * Load the image with url and default loading image resource, error image resource.
     *
     * @param imageView  {@link ImageView}
     * @param loadingRes loading image resource
     * @param errorRes   error image resource
     * @param url        url
     */
    public void load(ImageView imageView, int loadingRes, int errorRes, String url) {
        mRequestManager.load(url).placeholder(loadingRes).error(errorRes).into(imageView);
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
        mRequestManager.load(url).override(size[0], size[1]).into(imageView);
    }
}
