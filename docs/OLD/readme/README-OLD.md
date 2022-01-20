# RareSpecies

[TOC]

## 项目说明

> 珍稀植物物种

## 目录结构

      | -- 根目录
        | -- app 手机版
        | -- app_expert 专家版
        | -- app_clerk 一线业务员/实验室业务员版    
        | -- base_project 项目通用模块
        | -- dependencies 统一依赖库版本管理（子模块）
        | -- doc 项目相关文档资料
            | -- old 旧版代码(2020-05-15)     
            | -- amap 高德地图官方参考资料        
        | -- libs  项目所需的支持库        
            | -- socket 数据交互连接
                | -- liba_support 基础支持库模块
                | -- liba_log 核心日志模块
            | -- core 核心模块
                | -- liba_support 基础支持库模块
                | -- liba_log 核心日志模块
            | -- imageloader 图片加载模块
                | -- liba_imageloader_glide Glide图片加载模块
            | -- location 位置服务模块
                | -- liba_location_core 位置服务核心
                | -- liba_location_gd 基于高德定位实现的位置服务模块
            | -- push PUSH模块
                | -- liba_push_agent PUSH客户端
                | -- liba_push_core PUSH核心模块
                | -- liba_push_jpush JPUSH实现
            | -- liba_utils  工具库    
        | -- tools 项目所需的工具包    
            | -- keystore 项目签名
            | -- proguard 项目混淆文件    



## 编译环境

  > AndroidStudio 4.1
  
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

### 即时通信(Xmpp+Openfire)

> 服务端采用openfire

### 音视频聊天(WebRTC)

> WebRTC

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



## 项目参考链接

[git中submodule子模块的添加、使用和删除](https://blog.csdn.net/guotianqing/article/details/82391665)




