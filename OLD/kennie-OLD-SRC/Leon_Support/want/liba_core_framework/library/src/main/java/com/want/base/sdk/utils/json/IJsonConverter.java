package com.want.base.sdk.utils.json;

import java.lang.reflect.Type;

/**
 * <b>Project:</b> android_sdk_base<br>
 * <b>Create Date:</b> 15/9/15<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * Json converter interface.
 * <br>
 */
public interface IJsonConverter {

    /**
     * Convert json string to target class.
     *
     * @param json  json string
     * @param clazz target class
     * @param <T>   target class type
     *
     * @return target class.
     */
    <T> T fromJson(String json, Class<T> clazz);

    /**
     * Convert json string to target class.
     *
     * @param json json string
     * @param type {@link Type}
     * @param <T>  target class type
     *
     * @return target class
     */
    <T> T fromJson(String json, Type type);

    /**
     * Convert json object to json string
     *
     * @param jsonObject json object
     *
     * @return json string.
     */
    String toJson(Object jsonObject);
}
