package cn.lukas.utils;

import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.text.DecimalFormat;

/**
 * <pre>
 *     author : likun
 *     time   : 2019/1/23
 *     desc   : 文件相关工具类
 *     			权限 {@link android.Manifest.permission#WRITE_EXTERNAL_STORAGE}
 * </pre>
 */

public class FileUtils {

    public static final long KB = 1024;
    public static final long MB = 1024 * 1024;
    public static final long GB = 1024 * 1024 * 1024;
    public static final long TB = 1024 * 1024 * 1024 * 1024L;
    public static final String DEFAULT_FORMAT_PATTERN = "#.##";

    /**
     * 新建文件
     *
     * @param filePath 文件路径字符串
     * @return 文件是否创建成功
     */
    public static boolean createNewFile(String filePath) {
        return !TextUtils.isEmpty(filePath) && createNewFile(new File(filePath));
    }

    /**
     * 新建文件
     *
     * @param file File类型
     * @return 文件是否创建成功
     */
    public static boolean createNewFile(File file) {
        try {
            return !isFileExists(file) && file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 删除文件
     *
     * @param filePath 文件路径字符串
     * @return 是否成功删除文件
     */
    public static boolean deleteFile(String filePath) {
        return !TextUtils.isEmpty(filePath) && deleteFile(new File(filePath));
    }


    /**
     * 删除文件
     *
     * @param file File类型
     * @return 是否成功删除文件
     */
    public static boolean deleteFile(File file) {
        return isFileExists(file) && file.delete();
    }


    /**
     * 创建目录
     *
     * @param dirPath 目录路径字符串
     * @return 目录是否创建成功
     */
    public static boolean createDir(String dirPath) {
        return !TextUtils.isEmpty(dirPath) && createDir(new File(dirPath));
    }

    /**
     * 创建目录
     *
     * @param dir File类型
     * @return 目录是否创建成功
     */
    public static boolean createDir(File dir) {
        return !isFileExists(dir) && dir.mkdirs();
    }

    /**
     * 删除目录
     *
     * @param dirPath 目录路径字符串
     * @return 目录是否成功删除
     */
    public static boolean deleteDir(String dirPath) {
        return !TextUtils.isEmpty(dirPath) && deleteDir(new File(dirPath));
    }

    /**
     * 删除目录
     *
     * @param dir File类型
     * @return 目录是否删除成功 true 删除成功 false 删除失败
     */
    public static boolean deleteDir(File dir) {
        if (!isFileExists(dir)) {
            return false;
        }

        if (dir.isDirectory()) {
            File[] fileList = dir.listFiles();
            if (fileList == null) {
                return false;
            }
            for (File file : fileList) {
                boolean success = deleteDir(file);
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }


    /**
     * 删除目录下的指定后缀文件
     *
     * @param dirPath 目录路径字符串
     * @param postfix 后缀
     */
    public static boolean deletePostfixFiles(String dirPath, String postfix) {
        return !TextUtils.isEmpty(dirPath) && deletePostfixFiles(new File(dirPath), postfix);
    }


    /**
     * 删除目录下的指定后缀文件
     *
     * @param dir     File类型
     * @param postfix 指定后缀
     */
    public static boolean deletePostfixFiles(File dir, String postfix) {
        if (!isFileExists(dir) || TextUtils.isEmpty(postfix)) {
            return false;
        }

        if (dir.isFile() && dir.getName().endsWith(postfix)) {
            return dir.delete();
        } else if (dir.isDirectory()) {
            File[] fileList = dir.listFiles();
            if (fileList == null) {
                return false;
            }
            for (File file : fileList) {
                boolean success = deletePostfixFiles(file, postfix);
                if (!success) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * 格式化文件大小
     * <p>默认格式，保留两位小数</p>
     *
     * @param fileSize 文件大小
     * @return 转换后文件大小
     */
    public static String formatFileSize(long fileSize) {
        return formatFileSize(fileSize, DEFAULT_FORMAT_PATTERN);
    }

    /**
     * 格式化文件大小
     * <p>自定格式，保留位数</p>
     *
     * @param fileSize 文件大小
     * @param pattern  保留格式
     * @return 转换后文件大小
     */
    public static String formatFileSize(long fileSize, String pattern) {
        DecimalFormat sizeFormat = new DecimalFormat(pattern);
        String unitStr = "Bytes";
        long unit = 1;
        if (fileSize >= TB) {
            unitStr = "TB";
            unit = TB;
        } else if (fileSize >= GB) {
            unitStr = "GB";
            unit = GB;
        } else if (fileSize >= MB) {
            unitStr = "MB";
            unit = MB;
        } else if (fileSize >= KB) {
            unitStr = "KB";
            unit = KB;
        }
        return sizeFormat.format((double) fileSize / unit) + unitStr;
    }


    /**
     * 获取文件或者遍历目录大小
     *
     * @param filePath 文件路径字符串
     * @return
     */
    public static long getFilesSize(String filePath) {
        if (isFileExists(filePath)) {
            return getFilesSize(new File(filePath));
        }
        return 0;
    }

    /**
     * 获取文件或者遍历目录大小
     *
     * @param file File类型
     * @return 文件或者目录大小
     */
    public static long getFilesSize(File file) {
        long fileSize = 0;
        if (isFileExists(file)) {
            if (file.isDirectory()) {
                fileSize = getDirSize(file);
            } else {
                fileSize = getFileSize(file);
            }
        }
        return fileSize;
    }


    /**
     * 遍历目录大小
     *
     * @param filePath 文件路径字符串
     * @return 目录大小
     */
    public static long getDirSize(String filePath) {
        if (isFileExists(filePath)) {
            return getDirSize(new File(filePath));
        }
        return 0;
    }


    /**
     * 遍历目录大小
     *
     * @param dir File类型
     * @return 目录大小
     */
    public static long getDirSize(File dir) {
        long size = 0;
        if (isFileExists(dir)) {
            File[] fileList = dir.listFiles();
            if (fileList == null) {
                return size;
            }
            for (File file : fileList) {
                if (file.isDirectory()) {
                    size += getDirSize(file);
                } else {
                    size += getFileSize(file);
                }
            }
        }
        return size;
    }


    /**
     * 获取文件大小
     *
     * @param file File类型
     * @return 文件大小
     */
    public static long getFileSize(File file) {
        try {
            if (isFileExists(file)) {
                if (file.isFile()) {
                    FileInputStream inStream = new FileInputStream(file);
                    return inStream.available();
                } else if (file.isDirectory()) {
                    return getDirSize(file);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * 获取文件大小
     *
     * @param filePath 文件路径字符串
     * @return 文件大小
     */
    public static long getFileSize(String filePath) {
        if (isFileExists(filePath)) {
            return getFileSize(new File(filePath));
        }
        return 0;
    }


    /**
     * 判断文件或目录是否存在
     *
     * @param filePath 路径字符串
     * @return 文件是否存在 true 存在 false 不存在
     */
    public static boolean isFileExists(String filePath) {
        return !TextUtils.isEmpty(filePath) && isFileExists(new File(filePath));
    }

    /**
     * 判断文件或目录是否存在
     *
     * @param file File类型
     * @return 文件是否存在 true 存在 false 不存在
     */
    public static boolean isFileExists(File file) {
        return file != null && file.exists();
    }


    /**
     * 读取文件最后的修改时间：milliseconds
     *
     * @param filePath 文件路径字符串
     * @return 文件的最后修改时间
     */
    public static long getFileLastModified(String filePath) {
        if (isFileExists(filePath)) {
            return getFileLastModified(new File(filePath));
        }
        return -1;
    }

    /**
     * 读取文件最后的修改时间：milliseconds
     *
     * @param file 文件类型
     * @return 文件的最后修改时间
     */
    public static long getFileLastModified(File file) {
        if (isFileExists(file)) {
            return -1;
        }
        return file.lastModified();
    }

}
