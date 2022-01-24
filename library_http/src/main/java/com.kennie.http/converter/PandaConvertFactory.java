package com.kennie.http.converter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.kennie.http.annotation.ApiData;
import com.kennie.http.annotation.RealEntity;
import com.kennie.http.converter.ScalarResponseBodyConverters.BooleanResponseBodyConverter;
import com.kennie.http.converter.ScalarResponseBodyConverters.ByteResponseBodyConverter;
import com.kennie.http.converter.ScalarResponseBodyConverters.CharacterResponseBodyConverter;
import com.kennie.http.converter.ScalarResponseBodyConverters.DoubleResponseBodyConverter;
import com.kennie.http.converter.ScalarResponseBodyConverters.FloatResponseBodyConverter;
import com.kennie.http.converter.ScalarResponseBodyConverters.IntegerResponseBodyConverter;
import com.kennie.http.converter.ScalarResponseBodyConverters.LongResponseBodyConverter;
import com.kennie.http.converter.ScalarResponseBodyConverters.ShortResponseBodyConverter;
import com.kennie.http.converter.ScalarResponseBodyConverters.StringResponseBodyConverter;
import com.kennie.http.utils.GsonUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.annotations.Nullable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * <p>
 * Description :自定义解析工厂类
 */
public class PandaConvertFactory extends Converter.Factory {

    private Gson gson;

    public static PandaConvertFactory create() {
        return create(GsonUtil.gson());
    }

    private static PandaConvertFactory create(@NonNull Gson gson) {
        return new PandaConvertFactory(gson);
    }

    private PandaConvertFactory(Gson gson) {
        this.gson = gson;
    }

    /**
     * 这里这个 type 是本地接口方法中传入的泛型类型
     *
     * @param type        Retrofit 接口传入的泛型对象
     * @param annotations Retrofit 接口的注解
     * @param retrofit    Retrofit 对象
     * @return 转换器
     */
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        for (Annotation annotation : annotations) {
            // PandaResponseBodyConverter 转换时不使用 ApiData 剥壳，直接转换为接口中定义的对象
            if (annotation instanceof RealEntity) {
                if (type == String.class) {
                    return StringResponseBodyConverter.INSTANCE;
                } else if (type == Boolean.class || type == Boolean.TYPE) {
                    return BooleanResponseBodyConverter.INSTANCE;
                } else if (type == Byte.class || type == Byte.TYPE) {
                    return ByteResponseBodyConverter.INSTANCE;
                } else if (type == Character.class || type == Character.TYPE) {
                    return CharacterResponseBodyConverter.INSTANCE;
                } else if (type == Double.class || type == Double.TYPE) {
                    return DoubleResponseBodyConverter.INSTANCE;
                } else if (type == Float.class || type == Float.TYPE) {
                    return FloatResponseBodyConverter.INSTANCE;
                } else if (type == Integer.class || type == Integer.TYPE) {
                    return IntegerResponseBodyConverter.INSTANCE;
                } else if (type == Long.class || type == Long.TYPE) {
                    return LongResponseBodyConverter.INSTANCE;
                } else if (type == Short.class || type == Short.TYPE) {
                    return ShortResponseBodyConverter.INSTANCE;
                } else {
                    return new GsonResponseBodyConverter<>(gson, type);
                }
            }
            // 指定某一接口自己的 App壳
            if (annotation instanceof ApiData) {
                return new PandaResponseBodyConverter<>(gson, type, ((ApiData) annotation).clazz());
            }
        }
        return new PandaResponseBodyConverter<>(gson, type);
    }

    @Nullable
    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new PandaRequestBodyConverter<>(gson, adapter);
    }

    @Nullable
    @Override
    public Converter<?, String> stringConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return super.stringConverter(type, annotations, retrofit);
    }
}
