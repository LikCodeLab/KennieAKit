package com.leon.http;


import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Map;

/**
 * <b>Project:</b> http<br>
 * <b>Create Date:</b> 15/7/27<br>
 * <b>Author:</b> Leon<br>
 * <b>Description:</b>
 * Data and headers returned from server.
 * <br>
 */
@SuppressWarnings("unused")
public class HttpResponse {
    /**
     * Creates a new network response.
     *
     * @param statusCode    the HTTP status code
     * @param data          Response body
     * @param headers       Headers returned with this response, or null for none
     * @param notModified   True if the server returned a 304 and the data was already in cache
     * @param networkTimeMs Round-trip network time to receive network response
     */
    public HttpResponse(int statusCode, byte[] data, Map<String, String> headers,
                        boolean notModified, long networkTimeMs) {
        this.statusCode = statusCode;
        this.data = data;
        this.headers = headers;
        this.notModified = notModified;
        this.networkTimeMs = networkTimeMs;
    }

    public HttpResponse(int statusCode, byte[] data, Map<String, String> headers,
                        boolean notModified) {
        this(statusCode, data, headers, notModified, 0);
    }

    public HttpResponse(byte[] data) {
        this(200, data, Collections.<String, String>emptyMap(), false, 0);
    }

    public HttpResponse(byte[] data, Map<String, String> headers) {
        this(200, data, headers, false, 0);
    }

    /** The HTTP status code. */
    public final int statusCode;

    /** Raw data from this response. */
    public final byte[] data;

    /** Response headers. */
    public final Map<String, String> headers;

    /** True if the server returned a 304 (Not Modified). */
    public final boolean notModified;

    /** Network roundtrip time in milliseconds. */
    public final long networkTimeMs;

    @Override
    public String toString() {
        return "HttpResponse{" +
                "statusCode=" + statusCode +
                ", notModified=" + notModified +
                ", networkTimeMs=" + networkTimeMs +
                ", headers=" + mapToString(headers) +
                ", data=" + dataToString(data) +
                '}';
    }

    public String dataToString(byte[] data) {
        try {
            return new String(data, parseCharset(headers));
        } catch (UnsupportedEncodingException e) {
            return new String(data);
        }
    }

    private String mapToString(Map<String, String> maps) {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        for (Map.Entry<String, String> entry : maps.entrySet()) {
            builder.append(entry.getKey());
            builder.append(" -> ");
            builder.append(entry.getValue());
            builder.append("; ");
        }
        builder.append("}");
        return builder.toString();
    }

    /**
     * Retrieve a charset from headers
     *
     * @param headers        An {@link Map} of headers
     * @param defaultCharset Charset to return if none can be found
     *
     * @return Returns the charset specified in the Content-Type of this header,
     * or the defaultCharset if none can be found.
     */
    public static String parseCharset(Map<String, String> headers, String defaultCharset) {
        String contentType = headers.get("Content-Type");
        if (contentType != null) {
            String[] params = contentType.split(";");
            for (int i = 1; i < params.length; i++) {
                String[] pair = params[i].trim().split("=");
                if (pair.length == 2) {
                    if (pair[0].equals("charset")) {
                        return pair[1];
                    }
                }
            }
        }

        return defaultCharset;
    }

    /**
     * Returns the charset specified in the Content-Type of this header,
     * or the HTTP default (ISO-8859-1) if none can be found.
     */
    public static String parseCharset(Map<String, String> headers) {
        return parseCharset(headers, "ISO-8859-1");
    }
}
