package com.kennie.utils.core;


import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;


/**
 * project : KennieAKit
 * class_name :  FileUtils
 * author : Kennie
 * date : 2022/1/18 21:20
 * desc : 文件处理工具类
 *
 * <p>
 * --获取文件扩展名                                {@link #getFileExt(String fileName)}
 * </p>
 */
public class FileUtils {

    private static final String TAG = FileUtils.class.getSimpleName();

    @SuppressLint("StaticFieldLeak")
    private static Context ctx;

    /**
     * 初始化
     *
     * @param context 上下文对象
     */
    public static void init(Context context) {
        ctx = context;
    }


    /**
     * 获取文件扩展名
     *
     * @param fileName 文件名
     * @return {@code 文件扩展名} <br>
     */
    public static String getFileExt(String fileName) {
        if (!TextUtils.isEmpty(fileName)) {
            if (fileName.contains(".")) {
                return fileName.substring(fileName.lastIndexOf(".") + 1);
            }
            return "";
        }
        return null;
    }
}
