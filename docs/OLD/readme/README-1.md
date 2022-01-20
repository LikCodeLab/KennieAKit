<p align="center">
  <a href="https://github.com/pingfangushi/screw">
   <img alt="screw-logo" src="https://images.gitee.com/uploads/images/2020/0728/155335_59a712d2_1407605.png">
  </a>
</p>

<p align="center">💕 企业级开发过程中，一颗永不生锈的螺丝钉。</p>

<p align="center">
    <a href="https://github.com/pingfangushi/screw/blob/master/LICENSE">
        <img src="https://img.shields.io/badge/license-LGPL3-blue.svg" alt="LGPL3">
    </a>
    <a href="https://github.com/pingfangushi/screw">
        <img src="https://img.shields.io/badge/link-wiki-green.svg?style=flat-square" alt="wiki">
    </a>
    <a href="https://search.maven.org/search?q=cn.smallbun.screw">
       <img alt="Maven Central" src="https://img.shields.io/maven-central/v/cn.smallbun.screw/screw-core">
    </a>
    <a href="#">
        <img src="https://img.shields.io/badge/JDK-1.8+-green.svg" alt="JDK Version">
    </a>
    <a href="#">
        <img src="https://img.shields.io/badge/MAVEN-3.0+-green.svg" alt="JDK Version">
    </a>
</p>

>  🚀 screw (螺丝钉) 英:[`skruː`]  ~ 简洁好用的数据库表结构文档生成工具


#### 简介
> KennieAndroid 个人构建的Android平台库

#### 目录结构

      | -- 根目录
        | -- app_example 演示版本
        | -- app_project 项目版本
        | -- DOC 项目所需文档资料
        | -- TOOLS 项目所需的工具包
            | -- dependencies 通用依赖库版本管理（子模块）
            | -- gradle 通用项目配置文件包
                | -- apk_output.gradle APK 打包配置，包括apk重命名、归档备份
                | -- app.gradle 通用的 APP build配置，包括解决依赖冲突、分环境分渠道打包、分包、自动签名等
                | -- common.gradle 通用的项目配置，包括 GIT 提交信息、打包排除文件、静态检查白名单等
                | -- config.gradle 通用的项目build构建版本号配置，
                | -- git.gradle 基于 GIT 进行版本号得到 APP 版本号
                | -- library.gradle 通用的library库项目配置
            | -- keystore 通用项目签名文件包
            | -- proguard 通用项目混淆文件包

#### 开发环境

``` gradle
    Android Studio ArcticFox 2020.3.1
    JDK 1.8 || JDK 11
    kotlin 1.5.21
    gradle-7.0.2-bin & gradle 7.0.1
    compileSdk 31
    minSdk 24
    targetSdk 31
    AndroidX
```

#### 运行环境

  > 手机端 Android7.0 ~ Android 12.0

#### 更新日志
> 因为自己用的关系，更新的频率可能有点快

|  VERSION(版本)  |  Description(描述)  |
| :-------: | ------------- |
|   0.0.0   |  |
|   **0.0.0**   |  |


#### 更多支持

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td align="center"><img src="https://images.gitee.com/uploads/images/2020/0622/161414_eaa2819d_1407605.jpeg" width="300" height="300"  alt="WeChat"/> </td>
    <td align="center"><img src="https://images.gitee.com/uploads/images/2021/0806/091540_66d72182_1407605.png" width="300" height="300" alt="WeChat"/></td>
    <td align="center"><img src="https://images.gitee.com/uploads/images/2020/0707/191620_9a63fb23_1407605.png" width="300" height="300"  alt="QQ"/></td>
  </tr>
  <tr>
    <td align="center">微信公众号</td>
    <td align="center">微信交流群</td>
    <td align="center">QQ交流群</td>
  </tr>
</table>

+ 扫码关注官方微信公众号，第一时间尊享最新动向，回复 **screw** 获取作者微信号。

#### 注意事项

> **使用前请查看注意事项**

#### 附录

##### commit提交模板

    * 【FEAT新增功能】-【新的功能点、新的需求】
    * 【FIXBug修复】-【修复的Bug:现网发散Bug、测试阶段的Bug、验收阶段的Bug】
    * 【DOCS文档修改】-【只是修改了文档:注释、README.md等】.
    * 【STYLE样式修改】-【不影响代码功能的修改:CSS样式、代码格式化等】
    * 【REFACTOR代码重构】-【代码更改既不修复错误也不添加功能】
    * 【PERF性能优化】-【代码更改可以提高性能】
    * 【TEST测试代码】-【添加缺失测试或更正现有测试】
    * 【BUILD编译代码】-【影响构建系统或外部依赖项的更改:build.gradle、package.json、Podfile等】
    * 【CI持续集成】-【我们的CI配置文件和脚本的更改:Jenkinsfile等】
    * 【REVERT回退更改】-【代码回退提交更改】
    * 【CHORE其他提交】-【除以上所有类型之外的提交更改】

##### 编译错误信息详细查看

> gradlew compileDebug --stacktrace <br>
> gradlew compileDebug --stacktrace -info <br>
> gradlew compileDebug --stacktrace -debug <br>
> (推荐) gradlew compileDebugSources --stacktrace -info

##### 参考链接

[git中submodule子模块的添加、使用和删除](https://blog.csdn.net/guotianqing/article/details/82391665)

#### 推荐开源项目

| 项目名称       | 项目描述          |  
| ------------- |:-------------:|
|[api-boot](https://gitee.com/minbox-projects/api-boot)|为组件化构建Api服务而生| 

#### 参与贡献

恳请的希望有兴趣的同学能够参与到**screw**建设中来，让我们共同完善它，让我们共同成长，帮助更多开发者，解决更多的问题。

#### License

<img src='https://www.gnu.org/graphics/lgplv3-with-text-154x68.png' alt="license">

#### 捐赠支持

&emsp;&emsp;我们一直致力于为您提供更好的数据库文档生成器。为了**screw**更好的发展和社区更加的繁荣，我们需要您的支持。<br/>
&emsp;&emsp;捐赠的目的是获得资金来维持我们所提供的程序和服务，捐款是基于大家自愿的原则，建议大家使用 支付宝 的"转账付款"功能，即时到帐。您的支持是鼓励我们前行的动力，无论金额多少都足够表达您这份心意。<br/>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td align="center"><img src="https://images.gitee.com/uploads/images/2020/0622/161414_c87e1846_1407605.png" width="200" height="200" /> </td>
    <td align="center"><img src="https://images.gitee.com/uploads/images/2020/0622/161414_e953f85f_1407605.png" width="200" height="200" /></td>
  </tr>
  <tr>
    <td align="center">支付宝</td>
    <td align="center">微信</td>
  </tr>
</table>
