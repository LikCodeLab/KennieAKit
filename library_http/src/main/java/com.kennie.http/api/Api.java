package com.kennie.http.api;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

/**
 * Description : http request apis, make requests fit rxJava Observable
 */
public interface Api {


    //==========================//
    //         GET请求           //
    // =========================//

    @GET()
    Observable<ResponseBody> get(@Url String url, @QueryMap Map<String, String> maps);

    @HEAD()
    Observable<ResponseBody> head(@Url String url, @QueryMap Map<String, String> maps);

    //==========================//
    //         POST请求          //
    // =========================//

    @FormUrlEncoded
    @POST()
    Observable<ResponseBody> post(@Url() String url, @FieldMap Map<String, String> maps);

    @FormUrlEncoded
    @POST()
    Observable<ResponseBody> postForm(@Url() String url, @FieldMap Map<String, Object> maps);

    @POST()
    Observable<ResponseBody> postBody(@Url() String url, @Body RequestBody requestBody);

    @FormUrlEncoded
    @PATCH()
    Observable<ResponseBody> patch(@Url() String url, @FieldMap Map<String, String> maps);


    //==========================//
    //         PUT请求           //
    // =========================//

    @FormUrlEncoded
    @PUT()
    Observable<ResponseBody> put(@Url() String url, @FieldMap Map<String, String> maps);

    //==========================//
    //         DELETE请求        //
    // =========================//

    @FormUrlEncoded
    @DELETE()
    Observable<ResponseBody> delete(@Url() String url, @FieldMap Map<String, String> maps);


    //==========================//
    //       文件上传下载         //
    // =========================//

    @Multipart
    @POST()
    Observable<ResponseBody> uploadFiles(@Url() String url, @Part() List<MultipartBody.Part> parts);

    @Streaming
    @GET()
    Observable<ResponseBody> downFile(@Url() String url, @QueryMap Map<String, String> maps);


}
