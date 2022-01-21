package com.want.core.imageloader;

/**
 * <b>Project:</b> android_sdk_base<br>
 * <b>Create Date:</b> 15/10/10<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class Config {
    public int width;
    public int height;
    public int loadingRes;
    public int errorRes;

    public Config() {

    }

    public Config setWidth(int width) {
        this.width = width;
        return this;
    }

    public Config setHeight(int height) {
        this.height = height;
        return this;
    }

    public Config setLoadingRes(int res) {
        this.loadingRes = res;
        return this;
    }

    public Config setErrorRes(int res) {
        this.errorRes = res;
        return this;
    }

}
