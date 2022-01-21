package cn.lukas.core.utils;

import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.text.DecimalFormat;

import static cn.lukas.core.utils.ConvertUtils.bytes2HexString;

/**
 * <pre>
 *     author : likun
 *     blog   : https://likun.github.io/
 *     time   : 2017/1/23
 *     desc   : 文件相关工具类
 *     			权限 {@link android.Manifest.permission#WRITE_EXTERNAL_STORAGE}
 * </pre>
 */

public class FileUtils {


    /**
     * > - **文件相关→[FileUtils.java][FileUtils]**
     * ```
     * createNewFile       : 创建文件
     * deleteFile          : 删除文件
     * mkDir               : 创建目录
     * deleteDir           : 删除目录
     * deletePostfixFiles  : 删除目录下的某后缀文件
     * formatFileSize      : 格式化文件大小
     * getFilesSize        : 遍历目录、获取文件大小
     * getFileSize         : 获取文件大小
     * getDirSize          : 遍历目录大小
     * getDirFreeSpace     : 获取目录剩余空间
     * getDirTotalSpace    : 获取目录总空间
     * getDirUsableSpace   : 获取目录可用空间
     * chmod               : 修改目录、文件权限
     * isFileExists        : 判断文件或目录是否存在
     * getFileLastModified : 读取文件最后的修改时间
     * getFileMd5          : 读取文件MD5值
     * ```
     */













    /**
     * @param dir File类型
     * @return
     * @see File#getFreeSpace() 获取系统root用户可用空间
     * @see File#getUsableSpace() 取非root用户可用空间
     * <p>
     * 获取目录剩余空间，同{@link StatFs#getFreeBytes()}
     * 剩余空间 = 总空间 - 已使用空间
     * 剩余空间 ！= 可用空间
     */
    public static long getDirFreeSpace(File dir) {
        long freeSpace = 0;
        if (isFileExists(dir)) {
            if (dir.isDirectory()) {
                freeSpace = dir.getFreeSpace();
            } else if (dir.isFile()) {
                freeSpace = dir.getParentFile().getFreeSpace();
            }
        }
        return freeSpace;
    }

    /**
     * @param filePath 文件路径字符串
     * @return
     * @see File#getFreeSpace() 获取系统root用户可用空间
     * @see File#getUsableSpace() 取非root用户可用空间
     * <p>
     * 获取目录剩余空间，同{@link StatFs#getFreeBytes()}
     * 剩余空间 = 总空间 - 已使用空间
     * 剩余空间 ！= 可用空间
     */
    public static long getDirFreeSpace(String filePath) {
        if (isFileExists(filePath)) {
            return getDirFreeSpace(new File(filePath));
        }
        return 0;
    }

    /**
     * 获取目录总空间，同{@link StatFs#getTotalBytes()}
     *
     * @param dir File类型
     * @return
     */
    public static long getDirTotalSpace(File dir) {
        long totalSpace = 0;
        if (isFileExists(dir)) {
            if (dir.isDirectory()) {
                totalSpace = dir.getTotalSpace();
            } else if (dir.isFile()) {
                totalSpace = dir.getParentFile().getTotalSpace();
            }
        }
        return totalSpace;
    }

    /**
     * 获取目录总空间，同{@link StatFs#getTotalBytes()}
     *
     * @param filePath 文件路径字符串
     * @return
     */
    public static long getDirTotalSpace(String filePath) {
        if (isFileExists(filePath)) {
            return getDirTotalSpace(new File(filePath));
        }
        return 0;
    }

    /**
     * @param dir File类型
     * @return
     * @see File#getFreeSpace() 获取系统root用户可用空间
     * @see File#getUsableSpace() 取非root用户可用空间
     * <p>
     * 获取目录可用空间，同{@link StatFs#getAvailableBytes()}
     */
    public static long getDirUsableSpace(File dir) {
        long usableSpace = 0;
        if (isFileExists(dir)) {
            if (dir.isDirectory()) {
                usableSpace = dir.getUsableSpace();
            } else if (dir.isFile()) {
                usableSpace = dir.getParentFile().getUsableSpace();
            }
        }
        return usableSpace;
    }

    /**
     * @param filePath 文件路径字符串
     * @return
     * @see File#getFreeSpace() 获取系统root用户可用空间
     * @see File#getUsableSpace() 取非root用户可用空间
     * <p>
     * 获取目录可用空间，同{@link StatFs#getAvailableBytes()}
     */
    public static long getDirUsableSpace(String filePath) {
        if (isFileExists(filePath)) {
            return getDirUsableSpace(new File(filePath));
        }
        return 0;
    }

    /**
     * 修改目录、文件权限
     *
     * @param path       目录、文件路径字符串
     * @param permission 权限字符串，如：777
     */
    public static void chmod(String path, String permission) {
        ShellUtils.execProcessBuilderCommand("chmod", permission, path);
    }

    /**
     * 修改目录、文件权限
     *
     * @param file       File类型
     * @param permission 权限字符串，如：777
     */
    public static void chmod(File file, String permission) {
        chmod(file.getPath(), permission);
    }

    /**
     * 修改目录、文件777权限
     *
     * @param path 目录、文件路径字符串
     */
    public static void chmod777(String path) {
        chmod(path, "777");
    }

    /**
     * 修改目录、文件777权限
     *
     * @param file File类型
     */
    public static void chmod777(File file) {
        chmod(file.getPath(), "777");
    }

    /**
     * 判断文件或目录是否存在
     *
     * @param file File类型
     * @return
     */
    public static boolean isFileExists(File file) {
        return file != null && file.exists();
    }

    /**
     * 判断文件或目录是否存在
     *
     * @param filePath 路径字符串
     * @return
     */
    public static boolean isFileExists(String filePath) {
        return !TextUtils.isEmpty(filePath) && isFileExists(new File(filePath));
    }



    /**
     * 读取文件MD5值
     *
     * @param filePath
     * @return
     */
    public static String getFileMd5(String filePath) {
        if (isFileExists(filePath)) {
            return getFileMd5(new File(filePath));
        }
        return null;
    }

    /**
     * 读取文件MD5值
     *
     * @param file
     * @return
     */
    public static String getFileMd5(File file) {
        try {
            if (!isFileExists(file)) {
                return null;
            }

            FileInputStream fis = new FileInputStream(file);
            MessageDigest md = MessageDigest.getInstance("MD5");
            DigestInputStream dis = new DigestInputStream(fis, md);
            byte[] buffer = new byte[1024 * 256];
            while (true) {
                if (dis.read(buffer) <= 0) {
                    break;
                }
            }
            md = dis.getMessageDigest();
            fis.close();
            dis.close();
            return bytes2HexString(md.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
