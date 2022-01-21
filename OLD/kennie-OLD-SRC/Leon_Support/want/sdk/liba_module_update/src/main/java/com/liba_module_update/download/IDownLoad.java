package com.liba_module_update.download;

/**
 * <b>Create Date:</b> 16/9/20<br>
 * <b>Author:</b> ldc <br>
 * <b>Description:</b>
 * <br>
 */
public interface IDownLoad {
    /**
     * 具体下载逻辑
     */
//    void downloadStart();

    /**
     * 新的版本号
     *
     * @return
     */
//    int getNewCode();

    /**
     * 检验版本号的路径
     *
     * @return
     */
    String getCodeURL();

}
