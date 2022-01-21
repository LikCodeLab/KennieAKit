## ImageLoader 使用说明

> 快速使用

1. 依赖

        // imageloader
        compile 'com.want.model:imageloader:2.0.0-SNAPSHOT@aar'
        //- end
    
2. 添加代码

        ImageLoader.Builder builder = new ImageLoader.Builder();
        builder.with(Activity.this)
               .url(imageurl)
               .callback(callback)
               .size(width, height)
               .loading(loading)
               .error(error)
               .view(imageview) // 或者 .view(view)
               .build()
               .load(); // 不要忘记调用 load() 方法
               
3. 方法说明

    |   方法名称    |   方法描述    |
    |   ------     |   -------    |
    | with(activity、fragment、context) | 调用该方法的对象。 |
    | url(url) |  传入图片链接地址。 |
    | callback(callback) | 图片加载成功后回调此方法。 |
    | size(width, height) |  期望的图片大小。 |
    | loading(loading) |  正在加载时显示的图片资源。 |
    | error(error) |  加载失败时显示的图片资源。 |
    | view(view) |  加载成功后放置图片的View对象。 |
    | build() |  构建 ImageLoader 对象。 |
        
4. 注意事项

    当前默认实现为 Glide。
    size()方法可能不会生效；
    如果 view()方法传入的为非 ImageView 对象，则需要调用 callback() 方法。
