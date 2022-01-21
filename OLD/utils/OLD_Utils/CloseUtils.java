package cn.lukas.core.utils;

import androidx.annotation.NonNull;

import java.io.Closeable;
import java.io.IOException;

/**
 * <pre>
 *     author : likun
 *     blog   : https://likun.github.io/
 *     time   : 2017/3/10
 *     desc   : 关闭相关工具类
 * </pre>
 */
public class CloseUtils {


    /**
     * > - **关闭相关→[CloseUtils.java][CloseUtils]**
     * ```
     * closeIO        : 关闭IO
     * closeIOQuietly : 安静关闭IO
     * ```
     */

    /**
     * 关闭IO
     *
     * @param closeables closeable
     */
    public static void closeIO(@NonNull Closeable... closeables) {
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 安静关闭IO
     *
     * @param closeables closeable
     */
    public static void closeIOQuietly(@NonNull Closeable... closeables) {
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException ignored) {

                }
            }
        }
    }
}
