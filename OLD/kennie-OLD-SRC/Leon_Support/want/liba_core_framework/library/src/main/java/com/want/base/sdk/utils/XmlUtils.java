package com.want.base.sdk.utils;

import com.want.base.sdk.utils.xml.IXmlConverter;

/**
 * <b>Project:</b> android_sdk_base<br>
 * <b>Create Date:</b> 15/9/21<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * XML converter utils.
 * <br>
 */
@SuppressWarnings("unused")
public class XmlUtils implements IXmlConverter {


    private static class SingletonHolder {
//        private static IXmlConverter CONVERTER = new SimpleXmlImpl();
    }


    private XmlUtils() {
        // Prevents instantiation from other class
    }


    /**
     * Convert xml string to target class
     *
     * @param xml   xml string
     * @param types target class
     *
     * @return target class
     */
    @Override
    public <T> T toClass(String xml, Class<?>... types) {
//        return SingletonHolder.CONVERTER.toClass(xml, types);
        return null;
    }

}
