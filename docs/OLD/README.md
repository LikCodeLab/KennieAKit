## 目录结构

      | -- 根目录
        | -- app_test  测试项目，包括开发过程中功能的调试
        | -- configs-depx 通用的版本管理，包括项目配置文件、工具
        | -- DOCS 文档资料目录，包括项目、模块的文档参考资料
            | -- studio_plugin Studio插件工具， commit提交规范模板插件
            | -- code_standard 代码规范 包括阿里巴巴规范的Android、Java规范手册
        | -- LibS 模块目录，包括各功能模块信息、源代码
        | -- ProjectsS 项目目录，包括演示项目信息、源代码
        | -- Resources 资源目录，包括项目、库相关图片等
        | -- TemplateS 模板目录，包括项目、库模板信息、源代码


## 项目目录结构


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
└── settings.gradle                      项目依赖配置
```
