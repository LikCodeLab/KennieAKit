package com.want.imageloader;

import com.want.imageloader.ImageLoader.Builder;


/**
 * <b>Project:</b> android_sdk_base<br>
 * <b>Create Date:</b> 15/10/10<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * Interface of image loader.
 * <br>
 */
public interface IImageLoader {

    /**
     * Init with {@link Builder}
     *
     * @param builder
     */
    void init(ImageLoader.InnerBuilder builder);

    /**
     * Load the image.
     */
    void load();
}
