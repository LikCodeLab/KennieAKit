# kennie-android说明


```
├── app                                  app
|   └── src
|       └── main
|       |   └── java                     源码目录
|       |       ├── activity             Activity目录
|       |       |   └── MainActivity     项目唯一Activity
|       |       ├── adapter              Adapter目录
|       |       ├── fragment             Fragment目录
|       |       ├── model                ViewModel目录
|       |       └── App                  Application
|       |
|       └── build.gradle                 模块构建配置
|       └── channel                      渠道配置文件
|       └── dictionary                   自定义混淆字典
|       └── proguard-rules.pro           代码混淆配置文件
|
├── library_base                         基础library（存放通用的封装源码）
|   └── src
|       └── main
|       |   ├── assets                   原生文件目录
|       |   └── java                     源码目录
|       |       ├── bus                  消息总线目录
|       |       ├── dialog               Dialog目录
|       |       ├── http                 网络请求目录
|       |       ├── provider             ContentProvider目录
|       |       ├── utils                工具类目录
|       |       └── view                 自定义view目录
|       |
|       └── build.gradle                 模块构建配置
|
├── library_common                       公共library（存放各个 module 公共的源码及资源）
|   └── src
|       └── main
|       |   └── java                     源码目录
|       |       ├── bean                 实体类目录
|       |       └── constant             常量配置目录
|       |
|       └── build.gradle                 模块构建配置
|
├── module_faq                           问答模块（目录同app，不再展开）
├── module_home                          首页模块（目录同app，不再展开）
├── module_navigation                    导航模块（目录同app，不再展开）
├── module_project                       项目模块（目录同app，不再展开）
├── module_system                        体系模块（目录同app，不再展开）
├── module_user                          用户模块（目录同app，不再展开）
|
├── plugin_statistic                     统计插件模块
|   └── src
|       └── main
|           └── groovy                   源码目录
|           |   ├── bp                   埋点统计目录
|           |   └── mt                   耗时统计目录
|           |
|           └── resources                配置目录
|               └── statistic.properties 插件配置
|
├── repos                                统计插件生成目录
|
├── build.gradle                         项目构建配置
├── config.gradle                        gradle编译文件 gradle依赖配置
├── config.properties                    项目配置
├── gradle.properties                    gradle配置
└── settings.gradle                      项目依赖配置
```



