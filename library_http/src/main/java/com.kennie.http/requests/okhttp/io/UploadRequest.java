package com.kennie.http.requests.okhttp.io;

import com.kennie.http.RequestManager;
import com.kennie.http.callbacks.TransmitCallback;
import com.kennie.http.callbacks.UploadCallBack;
import com.kennie.http.constants.MediaTypes;
import com.kennie.http.interceptor.UploadInterceptor;
import com.kennie.http.transformer.RxScheduler;

import io.reactivex.rxjava3.annotations.NonNull;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.internal.Util;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class UploadRequest extends IORequest<UploadRequest> {

    private List<MultipartBody.Part> multipartBodyParts = new ArrayList<>();
    private StringBuilder stringBuilder = new StringBuilder();

    public UploadRequest(String suffixUrl) {
        super(suffixUrl);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected <T extends TransmitCallback> void execute(T t) {
        UploadCallBack callback;
        if (t instanceof UploadCallBack) {
            callback = (UploadCallBack) t;
        } else {
            return;
        }
        if (super.tag != null) {
            RequestManager.get().addTag(super.tag, callback);
        }


        if (stringBuilder.length() > 0) {
            url = url + stringBuilder.toString();
        }
        if (localParams != null && localParams.size() > 0) {
            Iterator<Map.Entry<String, String>> entryIterator = localParams.entrySet().iterator();
            Map.Entry<String, String> entry;
            while (entryIterator.hasNext()) {
                entry = entryIterator.next();
                if (entry != null) {
                    multipartBodyParts.add(MultipartBody.Part.createFormData(entry.getKey(), entry.getValue()));
                }
            }
        }
        mApi.uploadFiles(url, multipartBodyParts)
                .compose(RxScheduler.io())
                .subscribe(callback);
    }

    public UploadRequest addUrlParam(String paramKey, String paramValue) {
        if (paramKey != null && paramValue != null) {
            if (stringBuilder.length() == 0) {
                stringBuilder.append("?");
            } else {
                stringBuilder.append("&");
            }
            stringBuilder.append(paramKey).append("=").append(paramValue);
        }
        return this;
    }

    public UploadRequest addFiles(Map<String, File> fileMap) {
        if (fileMap == null) {
            return this;
        }
        for (Map.Entry<String, File> entry : fileMap.entrySet()) {
            addFile(entry.getKey(), entry.getValue());
        }
        return this;
    }


    public UploadRequest addFile(String key, File file) {
        if (key == null || file == null) {
            return this;
        }
        RequestBody requestBody = RequestBody.create(MediaTypes.APPLICATION_OCTET_STREAM_TYPE, file);
        MultipartBody.Part part = MultipartBody.Part.createFormData(key, file.getName(), requestBody);
        this.multipartBodyParts.add(part);
        return this;
    }

    public UploadRequest addImageFile(String key, File file) {
        if (key == null || file == null) {
            return this;
        }
        RequestBody requestBody = RequestBody.create(MediaTypes.IMAGE_TYPE, file);
        MultipartBody.Part part = MultipartBody.Part.createFormData(key, file.getName(), requestBody);
        this.multipartBodyParts.add(part);
        return this;
    }

    public UploadRequest addBytes(String key, byte[] bytes, String name) {
        if (key == null || bytes == null || name == null) {
            return this;
        }
        RequestBody requestBody = RequestBody.create(MediaTypes.APPLICATION_OCTET_STREAM_TYPE, bytes);
        MultipartBody.Part part = MultipartBody.Part.createFormData(key, name, requestBody);
        this.multipartBodyParts.add(part);
        return this;
    }

    public UploadRequest addStream(String key, InputStream inputStream, String name) {
        if (key == null || inputStream == null || name == null) {
            return this;
        }

        RequestBody requestBody = create(MediaTypes.APPLICATION_OCTET_STREAM_TYPE, inputStream);
        MultipartBody.Part part = MultipartBody.Part.createFormData(key, name, requestBody);
        this.multipartBodyParts.add(part);
        return this;
    }

    protected RequestBody create(final MediaType mediaType, final InputStream inputStream) {
        return new RequestBody() {
            @Override
            public MediaType contentType() {
                return mediaType;
            }

            @Override
            public long contentLength() {
                try {
                    return inputStream.available();
                } catch (IOException e) {
                    return 0;
                }
            }

            @Override
            public void writeTo(@NonNull BufferedSink sink) throws IOException {
                Source source = null;
                try {
                    source = Okio.source(inputStream);
                    sink.writeAll(source);
                } finally {
                    Util.closeQuietly(source);
                }
            }
        };
    }

    public void request(UploadCallBack callback) {
        netInterceptor(new UploadInterceptor(callback));
        injectLocalParams();
        execute(callback);
    }

}
