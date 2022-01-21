package com.want.imageloader.impl;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.want.imageloader.IImageLoader;
import com.want.imageloader.ImageLoader;
import com.want.imageloader.OnLoadCallback;

/**
 * <b>Project:</b> android_sdk_base<br>
 * <b>Create Date:</b> 15/10/10<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class DefaultImageLoaderImpl implements IImageLoader {

    private ImageLoader.InnerBuilder mBuilder;
    private RequestManager mRequestManager;

    public DefaultImageLoaderImpl() {
    }

    @Override
    public void init(final ImageLoader.InnerBuilder builder) {
        this.mBuilder = builder;
        if (null != builder.getActivity()) {
            mRequestManager = Glide.with(builder.getActivity());
        } else if (null != builder.getFragment()) {
            mRequestManager = Glide.with(builder.getFragment());
        } else if (null != builder.getContext()) {
            mRequestManager = Glide.with(builder.getContext());
        } else {
            throw new IllegalArgumentException("You must call builder.with() first!!");
        }

        // use DefaultOptions handle the builder options.
        RequestManager.DefaultOptions options = new RequestManager.DefaultOptions() {
            @Override
            public <T> void apply(GenericRequestBuilder<T, ?, ?, ?> genericRequestBuilder) {
                if (0 != builder.getLoading()) {
                    genericRequestBuilder.placeholder(builder.getLoading());
                }

                if (0 != builder.getError()) {
                    genericRequestBuilder.error(builder.getError());
                }

                final int[] size = builder.getSize();
                if (null != size) {
                    genericRequestBuilder.override(size[0], size[1]);
                }
            }
        };

        mRequestManager.setDefaultOptions(options);
    }

    /**
     * Load the image.
     */
    @Override
    public void load() {
        final View view = mBuilder.getView();
        if (null == view) {
            throw new IllegalArgumentException("You must pass a view with builder.view() method");
        }

        final DrawableTypeRequest request = mRequestManager.load(mBuilder.getUrl());
        request.diskCacheStrategy(DiskCacheStrategy.ALL); // cache all size


        final OnLoadCallback listener = mBuilder.getCallback();
        if (null != listener) {
            request.into(new SimpleTarget<GlideDrawable>() {
                @Override
                public void onResourceReady(GlideDrawable glideDrawable,
                                            GlideAnimation<? super GlideDrawable> glideAnimation) {
                    mBuilder.getCallback().onLoadCallback(glideDrawable);
                }
            });
        } else if (view instanceof ImageView) {
            request.into((ImageView) view);
        } else {
            throw new IllegalArgumentException(
                    "You must call builder.callback() method if you pass a view");
        }
    }
}
