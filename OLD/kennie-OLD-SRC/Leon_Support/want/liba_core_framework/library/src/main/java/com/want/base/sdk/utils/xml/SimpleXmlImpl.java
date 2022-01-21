package com.want.base.sdk.utils.xml;

//import org.simpleframework.xml.Root;
//import org.simpleframework.xml.Serializer;
//import org.simpleframework.xml.convert.AnnotationStrategy;
//import org.simpleframework.xml.core.Persister;

/**
 * <b>Project:</b> android_sdk_base<br>
 * <b>Create Date:</b> 15/9/21<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class SimpleXmlImpl implements IXmlConverter {

//    private Serializer mSerializer;

    public SimpleXmlImpl() {
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
    @SuppressWarnings("unchecked")
    public <T> T toClass(String xml, Class<?>... types) {
//        if (!TextUtils.isEmpty(xml)) {
//            for (Class<?> type : types) {
//                if (type.isAnnotationPresent(Root.class)) {
//                    try {
//                        return (T) mSerializer.read(type, xml);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        return null;
//                    }
//                }
//            }
//        }
//        return (T) xml;
        return null;
    }
}
