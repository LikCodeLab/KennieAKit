package com.want.base.sdk.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * <b>Create Date:</b> 9/5/16<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class StreamUtils {

    private StreamUtils() {
        //no instance
    }

    public static void close(Closeable closeable){
        try {
            closeable.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
