package com.leon.http;


import java.util.HashMap;
import java.util.Map;

/**
 * <b>Project:</b> http<br>
 * <b>Create Date:</b> 15/8/6<br>
 * <b>Author:</b> Leon<br>
 * <b>Description:</b> <br>
 */
public class HttpRequest {

    private Builder mBuilder;

    private HttpRequest(Builder builder) {
        this.mBuilder = builder;
    }

    public String getContentType() {
        return mBuilder.contentType;
    }

    public Builder.Method getMethod() {
        return mBuilder.method;
    }

    public Map<String, String> getHeaders() {
        return mBuilder.headMaps;
    }

    public Map<String, String> getParams() {
        return mBuilder.params;
    }

    public String getBody() {
        return mBuilder.body;
    }

    public String getParamsEncodeing() {
        return mBuilder.paramsEncodeing;
    }

    public Builder.Priority getPriority() {
        return mBuilder.priority;
    }

    public String getUrl() {
        return mBuilder.url;
    }

    public int getTimeoutInMillions() {
        return mBuilder.timeoutInMillions;
    }

    public int getRetryCount() {
        return mBuilder.retryCount;
    }

    public static final class Builder {

        /**
         * Supported request methods.
         */
        public enum Method {
            DEPRECATED_GET_OR_POST(-1),
            GET(0),
            POST(1),
            PUT(2),
            DELETE(3),
            HEAD(4),
            OPTIONS(5),
            TRACE(6),
            PATCH(7);

            int id;

            Method(int id) {
                this.id = id;
            }

            public int getValue() {
                return id;
            }
        }

        /**
         * Priority values.  Requests will be processed from higher priorities to
         * lower priorities, in FIFO order.
         */
        public enum Priority {
            LOW,
            NORMAL,
            HIGH,
            IMMEDIATE
        }

        String url;
        Map<String, String> headMaps = new HashMap<>();
        Map<String, String> params = new HashMap<>();
        String body;
        String paramsEncodeing = "UTF-8";
        String contentType = "application/x-www-form-urlencoded; charset=";
        Method method = Method.GET;
        Priority priority = Priority.NORMAL;
        int timeoutInMillions = 15000; // default 15s
        int retryCount = 0; // default no retry

        public Builder(String url) {
            this.url = url;
        }

        /**
         * 增加 Http 头信息
         *
         * @param key   key
         * @param value value
         *
         * @return
         */
        public Builder addHeader(String key, String value) {
            this.headMaps.put(key, value);
            return this;
        }

        /**
         * 增加 Http 头信息
         *
         * @param headers
         *
         * @return
         */
        public Builder addheader(Map<String, String> headers) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                this.headMaps.put(entry.getKey(), entry.getValue());
            }
            return this;
        }

        /**
         * 设置 Http 请求方法
         *
         * @param method {@link Method}
         *
         * @return
         */
        public Builder setMethod(Method method) {
            this.method = method;
            return this;
        }

        /**
         * 增加请求参数
         *
         * @param key   key
         * @param value value
         *
         * @return
         */
        public Builder addParam(String key, Object value) {
            this.params.put(key, String.valueOf(value));
            return this;
        }

        /**
         * 增加请求参数
         *
         * @param params map<string, object>
         *
         * @return
         */
        public Builder addParam(Map<String, Object> params) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                this.params.put(entry.getKey(), String.valueOf(entry.getValue()));
            }
            return this;
        }

        /**
         * 设置请求体
         *
         * @param body
         *
         * @return
         */
        public Builder setBody(String body) {
            this.body = body;
            return this;
        }

        /**
         * 设置请求优先级
         *
         * @param priority {@link Priority}
         *
         * @return
         */
        public Builder setPriority(Priority priority) {
            this.priority = priority;
            return this;
        }

        /**
         * 设置文本类型
         *
         * @param contentType http请求文本类型
         *
         * @return
         */
        public Builder setContentType(String contentType) {
            this.contentType = contentType;  //+ paramsEncodeing;
            return this;
        }

        /**
         * 设置连接超时时间
         *
         * @param millions 毫秒数
         *
         * @return
         */
        public Builder setTimeOutInMillions(int millions) {
            this.timeoutInMillions = millions;
            return this;
        }

        /**
         * 请求重试次数
         *
         * @param count 重试次数
         *
         * @return
         */
        public Builder setRetryCount(int count) {
            this.retryCount = count;
            return this;
        }

        /**
         * 构建 HttpRequest
         *
         * @return
         */
        public HttpRequest build() {
            return new HttpRequest(this);
        }

    }
}
