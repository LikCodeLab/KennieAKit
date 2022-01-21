package com.want.imageloader;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;

import com.want.imageloader.impl.DefaultImageLoaderImpl;

/**
 * <b>Project:</b> android_sdk_base<br>
 * <b>Create Date:</b> 15/10/10<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * ImageLoader.
 * <br>
 */
public class ImageLoader {

    private static Builder sDefaultBuilder;

    private InnerBuilder mBuilder;
    private IImageLoader mImageLoader;


    private ImageLoader(InnerBuilder builder) {
        this.mBuilder = builder;
        if (null != builder.getImageLoader()) {
            this.mImageLoader = builder.getImageLoader();
        } else {
            this.mImageLoader = new DefaultImageLoaderImpl();
        }
    }

    /**
     * Begin load the image.
     */
    public void load() {
        mImageLoader.init(mBuilder);
        mImageLoader.load();
    }


    /**
     * Config the default {@link Builder}
     *
     * @param builder {@link Builder}
     */
    public static void defaultBuilder(Builder builder) {
        ImageLoader.sDefaultBuilder = builder;
    }


    /**
     * Wrap your attrs to this builder, and the {@link IImageLoader} load the image with this builder.
     */
    public static class Builder {
        private IImageLoader imageLoader;
        private Activity withActivity;
        private Fragment withfragment;
        private Context withContext;
        private View view;
        private String url;
        private int loading;
        private int error;
        private int[] size;
        private OnLoadCallback callback;

        public Builder() {
            this(sDefaultBuilder);
        }

        private Builder(final Builder builder) {
            if (null != builder) {
                this.imageLoader = builder.imageLoader;
                this.withActivity = builder.withActivity;
                this.withfragment = builder.withfragment;
                this.view = builder.view;
                this.url = builder.url;
                this.loading = builder.loading;
                this.error = builder.error;
                this.size = builder.size;
                this.callback = builder.callback;
            }
        }

        public ImageLoader build() {
            return new ImageLoader(new InnerBuilder(this));
        }


        /**
         * Set the {@link IImageLoader}
         *
         * @param loader {@link IImageLoader}
         *
         * @return {@link Builder}
         */
        public Builder imageLoader(IImageLoader loader) {
            this.imageLoader = loader;
            return this;
        }

        /**
         * Context is {@link Activity}
         *
         * @param activity activity
         *
         * @return {@link Builder}
         */
        public Builder with(Activity activity) {
            this.withActivity = activity;
            return this;
        }

        /**
         * Context is {@link Fragment}
         *
         * @param fragment fragment
         *
         * @return {@link Builder}
         */
        public Builder with(Fragment fragment) {
            this.withfragment = fragment;
            return this;
        }

        /**
         * With {@link Context}
         *
         * @param context context
         *
         * @return {@link Builder}
         */
        public Builder with(Context context) {
            this.withContext = context;
            return this;
        }

        /**
         * Whitch {@link View} to show with. If you pass a {@link View}, you should call {@link #callback}.
         *
         * @param view view
         *
         * @return {@link Builder}
         */
        public Builder view(View view) {
            this.view = view;
            return this;
        }

        /**
         * Image url.
         *
         * @param url url
         *
         * @return {@link Builder}
         */
        public Builder url(String url) {
            this.url = url;
            return this;
        }

        /**
         * Loading status drawable res.
         *
         * @param loading drawable res
         *
         * @return {@link Builder}
         */
        public Builder loading(int loading) {
            this.loading = loading;
            return this;
        }

        /**
         * Error status drawable res.
         *
         * @param error drawable res.
         *
         * @return {@link Builder}
         */
        public Builder error(int error) {
            this.error = error;
            return this;
        }

        /**
         * Desire image size.
         *
         * @param width  image width.
         * @param height image height.
         *
         * @return {@link Builder}
         */
        public Builder size(int width, int height) {
            this.size = new int[2];
            size[0] = width;
            size[1] = height;
            return this;
        }

        /**
         * {@link OnLoadCallback}
         *
         * @param callback callback
         *
         * @return {@link Builder}
         */
        public Builder callback(OnLoadCallback callback) {
            this.callback = callback;
            return this;
        }

    }

    public static class InnerBuilder {
        private Builder builder;

        public InnerBuilder(Builder builder) {
            this.builder = builder;
        }

        public IImageLoader getImageLoader() {
            return builder.imageLoader;
        }

        public Activity getActivity() {
            return builder.withActivity;
        }

        public Fragment getFragment() {
            return builder.withfragment;
        }

        public Context getContext() {
            return builder.withContext;
        }

        public View getView() {
            return builder.view;
        }

        public String getUrl() {
            return builder.url;
        }

        public int getLoading() {
            return builder.loading;
        }

        public int getError() {
            return builder.error;
        }

        public int[] getSize() {
            return builder.size;
        }

        public OnLoadCallback getCallback() {
            return builder.callback;
        }

    }

}
