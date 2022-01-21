##HTTP 模块使用说明

>http模块封装了第三方网络组件okhttp及网络请求框架volley。当前阶段，http模块仅支持轻量级的网络请求，不支持大文件上传及下载。


###快速使用

1. 添加依赖

        // use http moduel
        compile 'com.want.model:http:1.5-SNAPSHOT@aar'
        // use volley default
        compile 'com.mcxiaoke.volley:library:1.0.15'
        // use okhttp default
        compile 'com.squareup.okhttp:okhttp:2.4.0'
        compile 'com.squareup.okhttp:okhttp-urlconnection:2.4.0'

2. 添加代码

    为了更加灵活的构建网络请求，该模块使用builder模式构建网络请求，并使用流式编程风格。
    
    以下为具体的代码使用示例：
    
    1. 获取http模块实例。为单进程单例。
       
            final HttpClient<String> mHttpClient = MHttpClient.getInstance(context);
            // 也可以在调用getInstance方法时传入httpclient对象，用于指定httpclient的具体实现
            // 默认情况下，获取到的是基于Volley的httpclient实现
            // mHttpClient = MHttpClient.getInstance(context, httpclient);
            
    2. 构造网络请求HttpRequest.Builder。关于Builder的详细说明见下文。
    
            final HttpRequest.Builder builder = new HttpRequest.Builder(url);
            builder.setMethod(Method.POST);
            builder.setBody(postData);
            builder.addheader(headerName, headerValue);
            
    3. 执行网络请求
    
            mHttpClient.request(builder.build(), httplistener);
    
    4. 你可能需要添加INTERNET权限。

以上便是使用http模块的主要流程。

###高阶使用

####Builder
Builder承担着http模块自定义使用的主要功能，包含以下：

|   方法名称    |   方法描述    |
|   :------    |   :------    |
| Builder(url) | 唯一的构造方法，使用时需要传入完整的网络请求路径。 |
| addHeader(key, value) | 增加一个HTTP Header |
| addHeader(headerMap) | 增加一组HTTP Header |
| setMethod(method) | 设置HTTP请求方法，如POST，GET，PUT等。默认为GET |
| addParam(key, value) | 增加一个请求参数，包含：URLParams，POST params |
| addParam(paramMap) | 增加一组请求参数，同上 |
| setBody(httpBody) | 设置Http请求体 |
| setPriority(priority) | 设置请求优先级，有LOW, NORMAL, HIGH, IMMEDIATE四种模式 |
| setContentType(contentType) | 设置Content-Type |
| build() | 生成HttpRequest对象。在配置完请求信息后，该方法必需调用 |

####处理回调
http模块支持异步回调网络请求结果。基于Volley的默认HttpClient实现中，除onResponse方法外，所有回调都在主线程中。以下是HttpListener的方法说明：

|   方法名称    |   方法描述    |
|   :------    |   :------    |
| onResponse(httpResponse) | 响应服务端请求，一般情况下，不用覆写该方法。该方法返回一个HttpResponse对象。注意，该方法默认在异步线程中执行。 |
| onSuccess(T t) | 网络请求结果符合预期。即，如果该方法被回调，则服务器返回是“正确”的。 |
| onError(httpError) | 网络请求出错时回调该方法。http模块对常见的错误进行了封装处理，如：HttpTimeOutError, HttpServerError等。 |
| onFinish() | 网络请求结束时回调方法。在整个回调方法中，该方法最后一个被回调。|

####处理错误

http模块将错误抛给HttpListener中处理，你需要根据onError传入的httpError类型，有针对的进行错误处理。

####调试模式

http模块默认情况下将会打印非常少的日志信息，如果你需要丰富的日志信息，请在命令行中执行以下代码：

        adb shell setprop log.tag.http VERBOSE

####自定义HttpClient

自定义的HttpClient客户端需要实现HttpClient接口，并覆写rquest(httpRequest, callback)方法。在调用MHttpClient.getInstance方法时传入该HttpClient即可。



###后续优化

1. 支持mulitipart
2. 支持文件上传及下载接口。