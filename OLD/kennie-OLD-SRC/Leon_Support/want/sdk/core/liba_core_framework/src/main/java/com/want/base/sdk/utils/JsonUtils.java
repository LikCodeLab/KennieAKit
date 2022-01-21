package com.want.base.sdk.utils;

import com.want.base.sdk.utils.json.GsonImpl;
import com.want.base.sdk.utils.json.IJsonConverter;

import java.lang.reflect.Type;

/**
 * <b>Project:</b> android_sdk_base<br>
 * <b>Create Date:</b> 15/9/15<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * Json converter utils.
 * <br>
 */
@SuppressWarnings("unused")
public class JsonUtils {

    private static final class SingletonHolder {
        private static IJsonConverter INSTANCE = new GsonImpl();
    }

    private JsonUtils(){
        // Prevents instantiation from other classes
    }

    /**
     * Convert json string to target class.
     *
     * @param json  json string
     * @param clazz target class
     * @param <T>   target class type
     *
     * @return target class.
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        return SingletonHolder.INSTANCE.fromJson(json, clazz);
    }

    /**
     * Convert json string to target class.
     *
     * @param json json string
     * @param type {@link Type}
     * @param <T>  target class type
     *
     * @return target class
     */
    public static <T> T fromJson(String json, Type type) {
        return SingletonHolder.INSTANCE.fromJson(json, type);
    }

    /**
     * Convert json object to json string
     *
     * @param jsonObject json object
     *
     * @return json string.
     */
    public static String toJson(Object jsonObject) {
        return SingletonHolder.INSTANCE.toJson(jsonObject);
    }
}
