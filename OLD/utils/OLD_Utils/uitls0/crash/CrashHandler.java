package cn.lukaslee.utils.support.crash;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.File;
import java.lang.reflect.Field;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @author LukasLee
 * @time 2020/3/30 15:04
 * @classname CrashHandler
 * description:全局异常崩溃统一处理类
 */
@SuppressWarnings("unused")
public class CrashHandler implements Thread.UncaughtExceptionHandler{

    public static final String TAG = "CrashHandler";

    //系统默认的UncaughtException处理类
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    //CrashHandler实例
    private static CrashHandler INSTANCE = new CrashHandler();
    //程序的Context对象
    private static Context mContext;
    //用来存储设备信息和异常信息
    private Map<String, String> infos = new HashMap<>();

    //用于格式化日期,作为日志文件名的一部分
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 保证只有一个CrashHandler实例
     */
    public CrashHandler() {
    }

    /**
     * 获取CrashHandler实例 ,单例模式
     */
    public static CrashHandler getInstance() {
        return INSTANCE;
    }

    /**
     * 初始化
     *
     * @param context
     */
    public void init(Context context) {
        mContext = context;
        //获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        //设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     *
     * @param thread
     * @param throwable
     */
    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        if (!handleException(throwable) && mDefaultHandler != null) {
            //如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, throwable);
        } else {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Log.e(TAG, "error : ", e);
            }
            //退出程序
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false.
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        //使用Toast来显示异常信息
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Looper.loop();
            }
        }.start();
        //收集设备参数信息
        collectDeviceInfo(mContext);
        //保存日志文件
        saveCrashInfo2File(ex);
        return true;
    }

    /**
     * 收集设备参数信息
     *
     * @param ctx
     */
    public void collectDeviceInfo(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null" : pi.versionName;
                String versionCode = pi.versionCode + "";
                infos.put("versionName", versionName);
                infos.put("versionCode", versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "an error occured when collect package info", e);
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
                Log.d(TAG, field.getName() + " : " + field.get(null));
            } catch (Exception e) {
                Log.e(TAG, "an error occured when collect crash info", e);
            }
        }
    }

    /**
     * 保存错误信息到文件中
     *
     * @param ex
     * @return 返回文件名称, 便于将文件传送到服务器
     */
    @Nullable
    private String saveCrashInfo2File(Throwable ex) {

        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\n");
        }

        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append(result);
        try {
            long timestamp = System.currentTimeMillis();
            String time = formatter.format(new Date());
            String fileName = "Crash.log";
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                String path = Environment.getExternalStorageDirectory().getPath() + "/CreateLog/";
                File dir = new File(path);
                if (!dir.exists()) {
                    dir.mkdirs();
                } else {
                    deleteExpireFiles(path);
                }
                File logFile = new File(path + fileName);
                if (!logFile.exists()) {
                    logFile.createNewFile();
                }
                FileOutputStream fos = new FileOutputStream(logFile);
                fos.write(sb.toString().getBytes());
                fos.close();
            }
            return fileName;
        } catch (Exception e) {
            Log.e(TAG, "an error occured while writing file...", e);
        }
        return null;
    }


    public static void deleteExpireFiles(String dirName) {
        File dir = new File(dirName);
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (i < 5 && files.length > 5) {
                    Log.i(TAG, "file is delete " + files[i].getName() + " result : " + files[i].delete());
                }
            }
        }
    }


    public static void reportError(Context context, String error) {

    }

    //或
    public static void reportError(Context context, Throwable e) {

    }

    /**
     * 生成日志 打印日志 log TxT 专用
     */
    public static void printLogMsg(String content) {
        String filePath = Environment.getExternalStorageDirectory().getPath() + "/CreateLog/";
        long timestamp = System.currentTimeMillis();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = formatter.format(new Date());
        String fileName = "Record.log";
//        String fileName = "CreateLog.txt";
        Log.e(TAG, "printLogMsg: " + filePath);
        String sdPath = getSDPath(mContext);
        writeTxtToFile(content, sdPath, fileName);
//        getLog();
    }


    public static String getSDPath(Context context) {
        File sdDir = null;
        String path;
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
        // 判断sd卡是否存在
        if (sdCardExist) {
            Log.e("============", "getSDPath: " + sdCardExist);
            if (Build.VERSION.SDK_INT >= 29) {
//Android10之后
                sdDir = context.getExternalFilesDir(null);
                path=sdDir.toString();
                Log.e("===========", "getSDPath: " + sdDir.getPath());
            } else {
                // 获取SD卡根目录
                sdDir = Environment.getExternalStorageDirectory();
                path=sdDir.getPath()+"/CreateLog/";
                Log.e("===========1", "getSDPath: " + sdDir.getPath());
            }
        } else {
            // 获取跟目录
            sdDir = Environment.getRootDirectory();
            path=sdDir.toString();
        }
        return path;
    }
//    public static void printLogMsg(String location, String content) {
//        String filePath = Environment.getExternalStorageDirectory().getPath() + "/CreateLog/";
//        long timestamp = System.currentTimeMillis();
//        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String time = formatter.format(new Date());
//        String fileName = "Record.log";
////        String fileName = "CreateLog.txt";
//        String currentDateString = DateUtils.getInstance().getCurrentDateString("yyyy-MM-dd HH:mm:ss");
//        writeTxtToFile(currentDateString + "\n" + location + "\n" + content, filePath, fileName);
////        getLog();
//    }

    /**
     * 将字符串写入到文本文件中
     */
    private static void writeTxtToFile(String strcontent, String filePath, String fileName) {
        //生成文件夹之后，再生成文件，不然会出错
        makeFilePath(filePath, fileName);

        String strFilePath = filePath + fileName;
        // 每次写入时，都换行写
        String strContent = strcontent + "\r\n";
        try {
            File file = new File(strFilePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                Log.e(TAG, "Create the file:" + strFilePath);
            }/* else {
//                file.delete();
                file.getParentFile().mkdirs();
                file.createNewFile();
            }*/
            RandomAccessFile raf = new RandomAccessFile(file, "rwd");
            raf.seek(file.length());
            raf.write(strContent.getBytes());
            raf.close();
        } catch (Exception e) {
            Log.e("TestFile", "Error on write File:" + e);
        }
    }

    /**
     * 生成文件
     */
    private static void makeFilePath(String filePath, String fileName) {
        File file;
        makeRootDirectory(filePath);
        try {
            Log.e(TAG, "makeFilePath: " + filePath + fileName);
            file = new File(filePath + fileName);
            Log.e(TAG, "makeFilePath: " + file.exists());
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            Log.e(TAG, "makeFilePath: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 生成文件夹
     */
    private static void makeRootDirectory(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            Log.e(TAG, "makeRootDirectory: " + filePath + file.exists());
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {
            Log.e(TAG, "makeRootDirectory: " + e.getMessage());
        }
    }

    public static void getLog() {
        // 方法启动
        printLogMsg("-----------------------Start record Log---------------------");
        try {
            //设置命令   logcat -d 读取日志
            ArrayList<String> cmdLine = new ArrayList<>();
            cmdLine.add("logcat");
            cmdLine.add("-d");

            //设置命令  logcat -c 清除日志
            ArrayList<String> clearLog = new ArrayList<>();
            clearLog.add("logcat");
            clearLog.add("-c");

            //捕获日志
            Process process = Runtime.getRuntime().exec(cmdLine.toArray(new String[cmdLine.size()]));
            //将捕获内容转换为BufferedReader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String str = null;
            //开始读取日志，每次读取一行
            while ((str = bufferedReader.readLine()) != null) {
                //清理日志....这里至关重要，不清理的话，任何操作都将产生新的日志，代码进入死循环，直到bufferreader满
                Runtime.getRuntime().exec(clearLog.toArray(new String[clearLog.size()]));
                printLogMsg(str);
                System.out.println(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        printLogMsg("-----------------------End record Log-----------------------------");
    }
}
