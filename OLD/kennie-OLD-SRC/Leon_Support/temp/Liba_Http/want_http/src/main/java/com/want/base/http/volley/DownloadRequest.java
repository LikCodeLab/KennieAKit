package com.want.base.http.volley;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.leon.log.lg;
import com.want.base.http.HttpListener;
import com.want.base.http.HttpRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * <b>Project:</b> mmc_android_sdk_base<br>
 * <b>Create Date:</b> 15/8/12<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * 基于 Volley 的文件下载请求
 * <br>
 */
public class DownloadRequest extends MStringRequest {

    private File mDownloadPath;


    public DownloadRequest(HttpRequest request, File filePath, HttpListener<String> listener) {
        super(request, listener);
        this.mDownloadPath = filePath;
    }


    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        try {
            FileOutputStream fos = new FileOutputStream(mDownloadPath);
            fos.write(response.data);
            fos.close();
        } catch (IOException e) {
            lg.e("http", "download file error!! url: " + getUrl() + ", file path: " + mDownloadPath.getAbsolutePath(), e);
            return Response.error(new ParseError());
        }

        final byte[] pathByte = mDownloadPath.getAbsolutePath().getBytes(Charset.forName("UTF-8"));
        response = new NetworkResponse(response.statusCode, pathByte, response.headers, response.notModified, response.networkTimeMs);
        return super.parseNetworkResponse(response);
    }
}
