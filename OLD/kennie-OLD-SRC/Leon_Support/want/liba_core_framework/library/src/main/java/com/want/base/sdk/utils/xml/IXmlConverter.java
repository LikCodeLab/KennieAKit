package com.want.base.sdk.utils.xml;

/**
 * <b>Project:</b> android_sdk_base<br>
 * <b>Create Date:</b> 15/9/21<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public interface IXmlConverter {

    /**
     * Convert xml string to target class
     *
     * @param xml   xml string
     * @param types target class
     * @param <T>   target class
     *
     * @return target class
     */
    <T> T toClass(final String xml, Class<?>... types);

}
