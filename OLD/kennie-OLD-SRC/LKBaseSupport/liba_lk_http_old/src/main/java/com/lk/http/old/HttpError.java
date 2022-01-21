package com.lk.http.old;


import android.net.ParseException;
import androidx.annotation.NonNull;

import com.google.gson.JsonParseException;
import com.lk.http.old.exception.*;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;

import javax.net.ssl.SSLHandshakeException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * 网络状态异常
 * 网络状态码
 *
 * @author LK
 * @since 0.0.1
 */
public final class HttpError {

    /**
     * 响应状态码
     */
    public static final int SUCCESS_RESPONSE = 200;// 响应正常

    /**
     * 异常状态码 -> 系统异常
     */
    public static final int ERROR_ACCESS_DENIED = 302; //网络错误
    public static final int ERROR_UNAUTHORIZED = 401; //未授权的请求
    public static final int ERROR_FORBIDDEN = 403; //禁止访问
    public static final int ERROR_NOT_FOUND = 404; //服务器地址未找到
    public static final int ERROR_REQUEST_TIMEOUT = 408; //请求超时
    public static final int ERROR_HANDEL_ERROR = 417; //接口处理失败
    public static final int ERROR_INTERNAL_SERVER_ERROR = 500; //服务器出错
    public static final int ERROR_BAD_GATEWAY = 502; //无效的请求
    public static final int ERROR_SERVICE_UNAVAILABLE = 503; //服务器不可用
    public static final int ERROR_GATEWAY_TIMEOUT = 504; //网关响应超时

    /**
     * 异常状态码 -> 请求异常
     */
    public static final int ERROR = 5000; // 统一异常，找不到已定义的异常时，统一发送此异常
    public static final int ERROR_Http = 6000; // 统一的HttpException类型异常
    public static final int ERROR_NO_NETWORK = 6001; // 无网络连接
    public static final int ERROR_HOST = 6002; // 网络已连接，但无法访问Internet -> 主机IP地址无法确定
    public static final int ERROR_SocketTimeout = 6003; // socket连接超时异常
    public static final int ERROR_Connect = 6004; // 连接异常
    public static final int ERROR_Parse = 6005; // 解析异常
    public static final int ERROR_SSL = 6006; // 证书验证失败

    /**
     * 异常状态码 -> 自定义异常
     */
    public static final int ERROR_NO_DATA_TYPE = 7001; // 数据无匹配类型异常
    public static final int ERROR_RESPONSE_BODY = 7002; // 数据解析异常


    /**
     * 统一处理 异常情况
     *
     * @param pE
     * @return
     */
    public static HttpException parseException(@NonNull Throwable pE) {
        int code = ERROR;
        String msg = "系统异常，请稍后再试!";

        /**
         * 检查网络连接
         */
        if (!checkNetWork() || pE instanceof HttpNoWorkException) {
            code = ERROR_NO_NETWORK;
            msg = "无网络连接";
        } else if (pE instanceof UnknownHostException) {
            code = ERROR_HOST;
            msg = "网络已连接，但无法访问Internet";
        }
        /**
         * 系统异常
         */
        else if (pE instanceof ServerException) {
            HttpException exception = (HttpException) pE;
            switch (exception.getCode()) {
                case ERROR_ACCESS_DENIED:
                    code = ERROR_ACCESS_DENIED;
                    msg = "网络错误";
                    break;
                case ERROR_UNAUTHORIZED:
                    code = ERROR_UNAUTHORIZED;
                    msg = "未授权的请求";
                    break;
                case ERROR_FORBIDDEN:
                    code = ERROR_FORBIDDEN;
                    msg = "禁止访问";
                    break;
                case ERROR_NOT_FOUND:
                    code = ERROR_NOT_FOUND;
                    msg = "服务器地址未找到";
                    break;
                case ERROR_REQUEST_TIMEOUT:
                    code = ERROR_REQUEST_TIMEOUT;
                    msg = "请求超时";
                    break;
                case ERROR_HANDEL_ERROR:
                    code = ERROR_HANDEL_ERROR;
                    msg = "接口处理失败";
                    break;
                case ERROR_INTERNAL_SERVER_ERROR:
                    code = ERROR_INTERNAL_SERVER_ERROR;
                    msg = "服务器出错";
                    break;
                case ERROR_BAD_GATEWAY:
                    code = ERROR_BAD_GATEWAY;
                    msg = "无效的请求";
                    break;
                case ERROR_SERVICE_UNAVAILABLE:
                    code = ERROR_SERVICE_UNAVAILABLE;
                    msg = "服务器不可用";
                    break;
                case ERROR_GATEWAY_TIMEOUT:
                    code = ERROR_GATEWAY_TIMEOUT;
                    msg = "网关响应超时";
                    break;
                default:
                    code = ERROR_Http;
                    msg = "HttpException异常";
                    break;
            }
        }

        /**
         * 请求异常
         */
        else if (pE instanceof SocketTimeoutException || pE instanceof ConnectTimeoutException) {
            code = ERROR_SocketTimeout;
            msg = "连接超时";
        } else if (pE instanceof ConnectException) {
            code = ERROR_Connect;
            msg = "连接失败";
        } else if (pE instanceof JsonParseException || pE instanceof JSONException || pE instanceof ParseException) {
            code = ERROR_Parse;
            msg = "解析错误";
        } else if (pE instanceof SSLHandshakeException) {
            code = ERROR_SSL;
            msg = "证书验证失败";
        }

        /**
         * 自定义
         */
        else if (pE instanceof HttpNoDataTypeException) {
            code = ERROR_NO_DATA_TYPE;
            msg = "数据无匹配类型异常";
        } else if (pE instanceof HttpNoDataBodyException) {
            code = ERROR_RESPONSE_BODY;
            msg = "数据解析异常";
        } else if (pE instanceof HttpResponseException) {
            code = ((HttpResponseException) pE).getCode();
            msg = "响应异常";
        }
        return new HttpException(pE, code, msg);
    }


    /**
     * 检查网络是否连接
     */
    private static boolean checkNetWork() {
        boolean result = false;
//        ConnectivityManager connMgr = (ConnectivityManager) HttpManager.INSTANCE.context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        if (connMgr == null) {
//            return result;
//        }
//        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP) {
//            //检测API是不是小于23，因为到了API23之后getNetworkInfo(int networkType)方法被弃用
//            //获取WIFI连接的信息
//            NetworkInfo wifiNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
//            if (wifiNetworkInfo != null && wifiNetworkInfo.isConnected()) {
//                result = true;
//            } else {
//                //获取移动数据连接的信息
//                NetworkInfo dataNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
//                if (dataNetworkInfo != null && dataNetworkInfo.isConnected()) {
//                    result = true;
//                }
//            }
//        } else {
//            //API大于23时使用下面的方式进行网络监听
//            //获取所有网络连接的信息
//            Network[] networks = connMgr.getAllNetworks();
//            if (networks != null && networks.length > 0) {
//                for (int i = 0, len = networks.length; i < len; i++) {
//                    NetworkInfo networkInfo = connMgr.getNetworkInfo(networks[i]);
//                    if (networkInfo != null && networkInfo.isConnected()) {
//                        result = true;
//                        break;
//                    }
//                }
//            }
//        }
        return result;
    }

}
