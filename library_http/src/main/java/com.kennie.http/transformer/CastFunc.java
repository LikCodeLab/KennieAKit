package com.kennie.http.transformer;

import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.kennie.http.entity.EmptyData;
import com.kennie.http.exception.ApiException;
import com.kennie.http.exception.ExceptionType;
import com.kennie.http.utils.GsonUtil;

import java.lang.reflect.Type;

import io.reactivex.rxjava3.functions.Function;
import okhttp3.ResponseBody;

/**
 * Description :cast ResponseBody to T
 */
@SuppressWarnings("unchecked")
public class CastFunc<T> implements Function<ResponseBody, T> {

    private Type type;
    private TypeAdapter<T> typeAdapter;

    public CastFunc(Type type) {
        this.type = type;
        this.typeAdapter = (TypeAdapter<T>) GsonUtil.gson().getAdapter(TypeToken.get(type));
    }

    @Override
    public T apply(ResponseBody responseBody) throws Exception {
        String json = responseBody.string();
        if (json.isEmpty()) {
            if (EmptyData.class.equals(type)) { // 如果接收的是空对象则回传 EmptyData
                return typeAdapter.fromJson(GsonUtil.gson().toJson(new EmptyData()));
            } else if (String.class.equals(type)) {
                return (T) json;
            } else {
                ApiException exception = new ApiException("-1", "data parse error", json);
                exception.setExceptionType(ExceptionType.JSON_PARSE);
                throw exception;
            }
        } else {
            return GsonUtil.gson().fromJson(json, type);
        }
    }
}
