# LukasLeeAndroidEngine
## 项目说明

> LukasLeeAndroidEngine 一个綜合高复用性的项目框架 该项目是Android基础技术框架，包含各种基础模块，通用组件等。

## 目录结构

      | -- 根目录
        | -- app java版本
        | -- app_kotlin kotlin版本
        | -- base_project 项目通用模块
        | -- dependencies 统一依赖库版本管理（子模块）
        | -- doc 项目及第三方技术相关文档资料
            | -- gradle 一些gradle配置文件     
            | -- 第三方SDK库 存放第三方官方的SDK相关参考资料        
        | -- libs  项目所需的支持库        
            | -- core 核心模块
                | -- liba_support 基础支持库模块
                | -- liba_log 核心日志模块
            | -- imageloader 图片加载模块
                | -- liba_imageloader_glide Glide图片加载模块
            | -- location 位置服务模块
                | -- liba_location_core 位置服务核心
                | -- liba_location_gd 基于高德定位实现的位置服务模块
            | -- liba_utils  工具库    
        | -- OLD 旧开发文件存储    
        | -- SRCDEMO 演示SRC的DEMO -> AndroidSRC           
        | -- tools 项目所需的工具包    
            | -- keystore 项目签名
            | -- proguard 项目混淆文件    


## 目录说明

### app
app最上层的应用模块，主工程项目统一入口。使用ARouter的路由方式调用业务模块，不对业务模块产生强依赖。

### base
基础封装组件，主要包括：通用的资源文件（color/style/shape等），项目架构封装，一些第三方SDK的二次封装，自定义View，Utils类等。只能被common/module/app依赖。不能依赖其他组件。

### common
业务通用组件，公共的业务模块，可供其他业务模块调用。可添加对core/base模块的依赖，只能被module/app依赖。common之间应尽量避免相互依赖。

### core
系统核心组件，主要包括：网络请求、数据库、推送、地图、多媒体（相机、音视频）等等，不包含任何业务，不能依赖其他任何组件。可被除core以外的模块依赖。

### modules
业务模块，项目的主要业务模块，可建立对core、base、common的依赖，module互相之间不能有任何依赖关系，应做到绝对解耦，可满足独立编译/运行/打包的条件。


## 编译环境

  > AndroidStudio 4.1.1

  > JDK 1.8

  > kotlin 1.4.10

  > gradle 4.1.0 | gradle-6.5-all

  > compileSdkVersion 30 | buildToolsVersion "30.0.2"

  > 支持androidx(support库不在更新)


## 运行环境
  > 手机端 Android5.0 ~ Android 11.0

## 项目说明

### 依赖(dependencies)

> 为了便于统一管理依赖, 该项目及该项目所依赖的子项目利用gradle构建的特性使用dependencies.gradle文件统一管理依赖. 并不是所有的子项目斗使用该依赖方式.


## 技术说明

### 数据交互(网关连接)

> Socket + XML

### 子模块

克隆项目后，默认子模块目录下无任何内容。需要在项目根目录执行如下命令完成子模块的下载：

> git submodule update --init --recursive

#### 子模块的更新

子模块的维护者提交了更新后，使用子模块的项目必须手动更新才能包含最新的提交。

在项目中，进入到子模块目录下，执行 git pull更新，查看git log查看相应提交。

完成后返回到项目目录，可以看到子模块有待提交的更新，使用git add，提交即可。

#### 删除子模块

有时子模块的项目维护地址发生了变化，或者需要替换子模块，就需要删除原有的子模块。

删除子模块较复杂，步骤如下：

1. rm -rf 子模块目录 删除子模块目录及源码
2. vi .gitmodules 删除项目目录下.gitmodules文件中子模块相关条目
3. vi .git/config 删除配置项中子模块相关条目
4. rm .git/module/* 删除模块下的子模块目录，每个子模块对应一个目录，注意只删除对应的子模块目录即可


## 更新日志
> 因为自己用的关系，更新的频率可能有点快

|  VERSION(版本)  |  Description(描述)  |
| :-------: | ------------- |
|   2.6.2   | 更新 RxUI、RxKit、RxPay 模块 |
|   2.6.1   | 更新 RxUI、RxKit 模块，新增 测试数据生成工具 |
|   **2.6.0**   | 整体转换为kotlin |
|   2.5.9   | 更新 RxUI 模块，新增 TBlurVIew，更新了 RxActivityTool、RxDialogChooseImage |
|   **2.4.0**   | **`全面升级到 Android X`**:<br>修复 RxToast 在 Android 9 上,连点只弹出一次的问题<br>修复 二维码扫描框的焦点偏离 问题<br>添加 生成二维码LOGO 功能<br>添加 RxQRCode 的空白边界设置方法<br>更新若干工具类  |
|   2.3.9   | 完善 RxFeature 模块  |
|   2.3.8   | 优化 RxFeature 模块  |
|   2.3.7   | 更新 RxFeature 模块  |
|   2.3.6   | 更新 RxFeature 模块，优化 RxUI 模块  |


## 项目参考链接

[git中submodule子模块的添加、使用和删除](https://blog.csdn.net/guotianqing/article/details/82391665)










