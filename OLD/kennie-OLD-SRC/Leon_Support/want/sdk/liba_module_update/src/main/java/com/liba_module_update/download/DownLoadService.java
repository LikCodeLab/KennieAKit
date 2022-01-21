package com.liba_module_update.download;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.liba_module_update.R;
import com.liba_module_update.net.CodeInfo;
import com.liba_module_update.update.UpdateController;
import com.liba_module_update.update.UpdateVariables;
import com.liba_module_update.util.SpUtils;
import com.liba_module_update.util.VersionCode;
import com.liba_module_update.view.DownLoadOptionDialog;
import com.liba_module_update.view.IDialogCallBack;
import com.want.base.http.HttpClient;
import com.want.base.http.HttpListener;
import com.want.base.http.HttpRequest;
import com.want.base.http.HttpResponse;
import com.want.base.http.MHttpClient;
import com.want.base.http.error.HttpError;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Random;

import static com.liba_module_update.download.JsonHelper.optput;

/**
 * <b>Create Date:</b> 16/9/21<br>
 * <b>Author:</b> ldc <br>
 * <b>Description:</b>
 * <br>
 */
public class DownLoadService extends IntentService implements IDialogCallBack {

    private UpdateController mUpdateUController;

    private DownLoadOptionDialog mDownLoadOptionDialog;

    private CodeInfo mCode;

    private boolean isAuto;

    private Random mRequestIdRandom = new Random();

    private HashMap<String, String> mHeadersMap = new HashMap<>();

    /**
     * 默认连接超时时间
     */
    public static final int DEFAULT_TIMEOUT_IN_MILLIONS = 30000;
    /**
     * 默认失败重连次数
     */
    public static final int DEDAULT_RETRY_COUNT = 0;

    public static void launch(Context context, String url, boolean is) {
        Intent intent = new Intent(context, DownLoadService.class);
        intent.putExtra(UpdateVariables.DOWNLOAD_URL, url);
        intent.putExtra(UpdateVariables.DOWNLOAD_AUTO, is);
        context.startService(intent);
    }

    public DownLoadService() {
        super("DownLoadService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            String url = intent.getStringExtra(UpdateVariables.DOWNLOAD_URL);
            isAuto = intent.getBooleanExtra(UpdateVariables.DOWNLOAD_AUTO, false);
            getCode(url, "0.001");
        }
    }

    //DownloadManager实现下载
    private void download(String url, boolean is) {
        if (!url.equals(null) && !url.equals("")) {
            String name = VersionCode.getVersionName(this);
            mUpdateUController = new UpdateController(this, is);
            mUpdateUController.download(url, "");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void showDialog(int newCode) {
        int old = VersionCode.getVersionCode(DownLoadService.this);
        if (newCode > old) {
            SpUtils.getInstance(this).putBoolean(UpdateVariables.DOWNLOAD_HAVE, false);
            mDownLoadOptionDialog = new DownLoadOptionDialog(this, this, "发现新版本" + newCode, "请立即下载新版本,以获得最佳的体验!", "稍后再说", "立即更新");
        } else {
            if (!isAuto) {
                Toast.makeText(this, "当前已是最新版本", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void getCode(String url, String version) {

        JSONObject postData = new JSONObject();
        optput(postData, "channel", "");
        optput(postData, "model", "mi");
        optput(postData, "system", "android");
        optput(postData, "version", version);

        getHttpClient(url, postData, new HttpListener<String>() {
            @Override
            public void onResponse(HttpResponse response) {
                Log.d("getCodeInfo:onResponse=", response.toString());
            }

            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                mCode = gson.fromJson(result, CodeInfo.class);
                showDialog(Integer.valueOf(mCode.version));
                Log.d("getCodeInfo:onSuccess=", mCode.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("getCodeInfo:onError=", error.toString());
            }

            @Override
            public void onFinish() {

            }
        });
    }

//    初始化网络请求的格式

    private HttpClient getHttpClient(final String path, JSONObject params, HttpListener<String> listener) {
        MHttpClient httpClient = MHttpClient.getInstance(this);
        final JSONObject postData = createRequestWrapper(params);
        String mBaseUrl = getString(R.string.ec_url);

        HttpRequest.Builder builder = new HttpRequest.Builder(mBaseUrl + path);
        builder.setMethod(HttpRequest.Builder.Method.POST);
        builder.setBody(postData.toString());
        builder.addheader(mHeadersMap);
        builder.setTimeOutInMillions(DEFAULT_TIMEOUT_IN_MILLIONS);
        builder.setRetryCount(DEDAULT_RETRY_COUNT);
        builder.setContentType("application/json; charset=");
        builder.addheader(mHeadersMap);
        httpClient.request(builder.build(), listener);

        return httpClient;
    }

    private JSONObject createRequestWrapper(JSONObject params) {
        JSONObject postData = new JSONObject();

        if (null == params) {
            params = new JSONObject();
        }

        optput(postData, "jsonrpc", "2.0")
                .optput("method", "call")
                .optput("params", params)
                .optput("id", getRequestId());
        return postData;
    }

    private int getRequestId() {
        return mRequestIdRandom.nextInt(9999);
    }

    @Override
    public void positiveFunction() {
        boolean isForce = mCode.upgrade == 801;
        if (isForce) {
            DownloadTask.getInstance(mCode, this).start();
        } else {
            download(mCode.url, true);
        }
    }

    @Override
    public void negativeFunction() {

    }
}
