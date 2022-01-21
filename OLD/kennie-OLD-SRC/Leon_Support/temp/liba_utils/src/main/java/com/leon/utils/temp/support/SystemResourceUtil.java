
package com.leon.utils.temp.support;

import android.content.Context;
import android.graphics.BitmapFactory;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 系统资源数据，包含图片加载参数，程序上下文环境，初始化线程池。并提供初始化方法，
 * 初始化本包需要使用的数据，如应用程序数据存放目录、线程池大小、日志初始化等， 调用
 * {@link SystemResourceUtil#init(String, int)}方法进行初始化�?
 */
public class SystemResourceUtil {

    /**
     * 图片加裁参数.
     */
    private BitmapFactory.Options opts = null;

    /**
     * 应用程序环境.
     */
    private Context applicationContext = null;

    /**
     * 应用程序线程池.
     */
    private ExecutorService executorService = null;

    /**
     * 应用程序数据目录.
     */
    private static String DATA_ROOT_DIRECTORY = null;

    /**
     * 应用程序图像目录 *.
     */
    private static String IMAGE_RELATIVE_DIRECTORY = "/image";

    /**
     * The app name.
     */
    private static String APP_NAME = null;

    /**
     * The Constant DEFAULT_THREADPOOL_SIZE.
     */
    private static final int DEFAULT_THREADPOOL_SIZE = 2;

    /**
     * The Constant DEFAULT_APP_DATA.
     */
    private static final String DEFAULT_APP_DATA = "AOSHeater";

    /**
     * The instance.
     */
    private static SystemResourceUtil instance = null;

    /**
     * Gets the single instance of SystemResourceUtil.
     *
     * @return single instance of SystemResourceUtil
     */
    private static SystemResourceUtil getInstance() {
        if (instance == null) {
            synchronized (SystemResourceUtil.class) {
                if (instance == null)
                    instance = new SystemResourceUtil();
            }
        }
        return instance;
    }

    /**
     * 初始化系统的数据文件目录和线程池大小.
     *
     * @param appName    应用程序名称，在SD卡目录下会创建一个以appName的文件夹，用以保存应用程序运行时数据
     * @param threadSize the thread size
     */
    public static void init(String appName, int threadSize) {
        // Log.i("Log", appName);
        if (StringUtil.isEmpty(appName))
            SystemResourceUtil.APP_NAME = DEFAULT_APP_DATA;
        else
            SystemResourceUtil.APP_NAME = appName;
        SystemResourceUtil.DATA_ROOT_DIRECTORY =
                android.os.Environment.getExternalStorageDirectory().getAbsolutePath()
                        + "/" + SystemResourceUtil.APP_NAME;
        initDir(appName);
        // Log.i("Log", SystemResourceUtil.DIRECTORY);
        getInstance().initThreadPool(threadSize);
    }

    /**
     * 初始化目录
     *
     * @param appName the app name
     * @return true, if successful
     */
    public static boolean initDir(String appName) {
        if (StringUtil.isEmpty(appName))
            SystemResourceUtil.APP_NAME = DEFAULT_APP_DATA;
        else
            SystemResourceUtil.APP_NAME = appName;
        SystemResourceUtil.DATA_ROOT_DIRECTORY = android.os.Environment
                .getExternalStorageDirectory().getAbsolutePath()
                + "/"
                + SystemResourceUtil.APP_NAME;

        File file = new File(SystemResourceUtil.DATA_ROOT_DIRECTORY
                + SystemResourceUtil.IMAGE_RELATIVE_DIRECTORY);
        if (!file.exists()) {
            file.mkdirs();
        }
        return true;
    }

    /**
     * Gets the image dir.
     *
     * @return 程序图像目录
     */
    public static String getImageDir() {
        initDir(SystemResourceUtil.APP_NAME);
        return SystemResourceUtil.DATA_ROOT_DIRECTORY
                + SystemResourceUtil.IMAGE_RELATIVE_DIRECTORY;
    }

    /**
     * 设置应用程序上下文环�?.
     *
     * @param applicationContext 应用程序上下文环�?
     */
    public static void setApplicationContext(Context applicationContext) {
        getInstance().applicationContext = applicationContext;
    }

    /**
     * 初始化线程池，如果输�?，则创建2个线程的线程�?.
     *
     * @param num 线程池大�?
     */
    private void initThreadPool(int num) {
        if (num == 0)
            num = 2;
        getInstance().executorService = Executors.newFixedThreadPool(num);
    }

    /**
     * 设置图片加载参数.
     *
     * @param opts 图片加载参数
     */
    public static void setBitmapFactoryOptions(BitmapFactory.Options opts) {
        getInstance().opts = opts;
    }

    /**
     * 获得应用程序上下文环�?.
     *
     * @return the application context
     */
    public static Context getApplicationContext() {
        return getInstance().applicationContext;
    }

    /**
     * 从线程池中返回一个执行线�?.
     *
     * @return the executor service
     */
    public static ExecutorService getExecutorService() {
        ExecutorService executorService = getInstance().executorService;
        if (executorService == null) {
            synchronized (SystemResourceUtil.class) {
                if (instance.executorService == null) {
                    getInstance().initThreadPool(DEFAULT_THREADPOOL_SIZE);
                }
            }
        }
        return instance.executorService;
    }

    /**
     * 回收该类的数据资�?.
     */
    public static void recycle() {
        if (instance != null) {
            instance.executorService = null;
            instance.applicationContext = null;
            instance.opts = null;
        }
        instance = null;
    }
}
