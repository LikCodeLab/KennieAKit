https://raw.githubusercontent.com/zhpanvip/BannerViewPager/master/README_CN.md

# 项目名称

[![License](https://img.shields.io/github/license/zhpanvip/BannerViewPager)](https://github.com/zhpanvip/BannerViewPager/blob/master/LICENSE)
![MinSdk](https://img.shields.io/badge/API-16%2B-brightgreen.svg?style=flat)
[![JitPack](https://jitpack.io/v/zhpanvip/BannerViewPager.svg)](https://jitpack.io/#zhpanvip/BannerViewPager)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-BannerViewPager-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/7961)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-ViewPagerIndicator-brightgreen.svg?style=flat)](https://github.com/zhpanvip/viewpagerindicator)
[![Stars](https://img.shields.io/github/stars/zhpanvip/BannerViewPager)](https://github.com/zhpanvip/BannerViewPager/stargazers)


## [English](https://github.com/zhpanvip/BannerViewPager) | 中文


> 腾讯视频、QQ音乐、酷狗音乐、支付宝、天猫、淘宝、优酷视频、喜马拉雅、网易云音乐、哔哩哔哩、全民K歌等App的Banner样式都可以通过BannerViewPager实现哦！

## 3.0版本新特性

- 基于ViewPager2实现
- 支持多类型Item
- 内存大幅优化，性能大幅提升
- 新增setOrientation，支持竖直滑动
- 新增addPageTransformer与removeTransformer
- setAdapter替换setHolderCreator
- getData替换了getList方法
- registerOnPageChangeCallback替换setOnPageChangeListener
- setUserInputEnabled取代disableTouchScroll
- 移除setPageTransformerStyle
- 移除部分2.x版本已废弃的方法
- 不再支持android support.
- 新增Indicator SCALE与COLOR滑动样式（V3.1.0）
- 支持刷新通过refreshData()方法刷新数据（V3.1.0）
- 支持通过setLifecycleRegistry(Lifecycle)管理BVP生命周期（3.1.4）
- 支持addData(List<T>) （3.2.0）
- 支持insertItem and removeItem（3.2.0）
- 支持addItemDecoration（3.2.0）


## 效果预览

 ### [点击或扫描二维码下载apk](https://www.pgyer.com/bannerviewpager) 
 由于平台限制，每个版本最多只能下载10次。其他版本下载链接：[下载V3.2.0](https://www.pgyer.com/24463b9b840a6317f3c98f6f3082685c),[下载V3.1.4](https://www.pgyer.com/472da9094cdc6175afdb36d99292de94)

![扫描下载Demo](https://gitee.com/zhpanvip/images/raw/master/project/banner/qrcode.png)


## 开放API

| 方法名 | 方法描述 | 说明 |
|--|--|--|
| BannerViewPager<T, VH> setCanLoop(boolean) | 是否开启循环 | 默认值true|
| BannerViewPager<T, VH> setAutoPlay(boolean) | 是否开启自动轮播 | 默认值true|
| BannerViewPager<T, VH> setInterval(int) | 自动轮播时间间隔 |单位毫秒，默认值3000  |
| BannerViewPager<T, VH> setScrollDuration(int) | 设置页面滚动时间 | 设置页面滚动时间 |单位毫秒，默认值500  |
| BannerViewPager<T, VH> setRoundCorner(int) | 设置圆角 |默认无圆角 需要SDK_INT>=LOLLIPOP(API 21)  |
| BannerViewPager<T, VH> setOnPageClickListener(OnPageClickListener) | 设置页面点击事件 |  |
| BannerViewPager<T, VH> setAdapter(BaseBannerAdapter\<T, VH>) |设置Adapter  |必须设置Adapter，否则会抛出NullPointerException|
| BannerViewPager<T, VH> setIndicatorVisibility(int) | indicator visibility |默认值VISIBLE 2.4.2 新增|
| BannerViewPager<T, VH> setIndicatorStyle(int) | 设置指示器样式 | 可选枚举(CIRCLE, DASH、ROUND_RECT) 默认CIRCLE  |
| BannerViewPager<T, VH> setIndicatorGravity(int) | 指示器位置 |可选值(CENTER、START、END)默认值CENTER |
| BannerViewPager<T, VH> setIndicatorColor(int,int) | 指示器圆点颜色 |normalColor：未选中时颜色默认"#8C6C6D72"， checkedColor：选中时颜色 默认"#8C18171C" |
| BannerViewPager<T, VH> setIndicatorSlideMode(int slideMode)  | 设置Indicator滑动模式 | 可选（NORMAL、SMOOTH、WORM、COLOR、SCALE），默认值NORMAL  |
| BannerViewPager<T, VH> setIndicatorSliderRadius(int radius) | 设置指示器圆点半径 | 默认值4dp|
| BannerViewPager<T, VH> setIndicatorSliderRadius(int normalRadius,int checkRadius)  |设置指示器圆点半径  |  normalRadius:未选中时半径  checkedRadius:选中时的半径,默认值4dp |
| BannerViewPager<T, VH> setIndicatorSliderWidth(int) | 设置指示器宽度，如果是圆形指示器，则为直径 |  默认值8dp|
| BannerViewPager<T, VH> setIndicatorSliderWidth(int normalWidth, int checkWidth) | 设置指示器宽度，如果是圆形指示器，则为直径 | 默认值8dp |
| BannerViewPager<T, VH> setIndicatorHeight(int) | 设置指示器高度，仅在Indicator样式为DASH时有效 | 默认值normalIndicatorWidth/2 |
| BannerViewPager<T, VH> setIndicatorSliderGap(int) | 指示器圆点间距| 默认值为指示器宽度（或者是圆的直径）|
| BannerViewPager<T, VH> setIndicatorView(IIndicator) | 设置自定义指示器|自定义View需要继承BaseIndicatorView或实现IIndicator |
| BannerViewPager<T, VH> setPageTransformer(ViewPager2.PageTransformer) | 设置页面Transformer内置样式 |  |
| BannerViewPager<T, VH> addPageTransformer(ViewPager2.PageTransformer) | 3.0.0新增，添加页面Transformer样式 |  |
| BannerViewPager<T, VH> removeTransformer(ViewPager2.PageTransformer) | 3.0.0新增，移除页面Transformer |  |
| BannerViewPager<T, VH> setCurrentItem(int) | Set the currently selected page. | 2.3.5新增 |
| int getCurrentItem() | 获取当前position | 2.3.5新增 |
| BannerViewPager<T, VH> setPageStyle(PageStyle) | 设置页面样式 | 2.4.0新增 可选（MULTI_PAGE_SCALE、MULTI_PAGE_OVERLAP）|
| BannerViewPager<T, VH> setPageMargin(int) | 设置页面间隔 | 2.4.0新增 |
| BannerViewPager<T, VH> setIndicatorMargin(int left, int top, int right, int bottom) | 设置Indicator边距 | 2.4.1新增 |
| BannerViewPager<T, VH> registerOnPageChangeCallback(OnPageChangeListener) | 页面改变的监听事件 | 2.4.3新增 |
| BannerViewPager<T, VH> setRoundRect(int) | 设置页面滑动方向|为BannerViewPager设置圆角 |
| BannerViewPager<T, VH> setOrientation(int) | 设置页面滑动方向| 3.0.0新增 支持水平和竖直滑动 |
| BannerViewPager<T, VH> setUserInputEnabled(int) | 是否开启用户输入 | |
| BannerViewPager<T, VH> setLifecycleRegistry(Lifecycle) | 为BVP设置Lifecycle | |
| void startLoop() |开启自动轮播 | 初始化BannerViewPager时不必调用该方法,设置setAutoPlay后会调用startLoop() |
| void stopLoop() | 停止自动轮播 | |
| List\<T> getData() | 获取Banner中的集合数据 |  |
| void create(List<T> list) |初始化并构造BannerViewPager  |如果创建BannerViewPager时已经有数据可以调用此方法  |
| void create() |创建没有数据的BannerViewPager  | 如果创建BannerViewPager时还没有数据，比如数据是来自服务器，可以调用此方法，等到成功获取数据后调用refreshData()刷新数据  |
### xml支持的attrs
| Attributes | format | description |
|--|--|--|
| bvp_interval | integer | 自动轮播时间间隔 |
| bvp_scroll_duration | integer | 页面切换时滑动时间|
| bvp_can_loop | boolean| 是否循环 |
| bvp_auto_play | boolean | 是否自动播放  |
| bvp_indicator_checked_color | color | indicator选中时颜色 |
| bvp_indicator_normal_color | color | indicator未选中时颜色 |
| bvp_indicator_radius | dimension | indicator圆点半径或者Dash模式的1/2宽度  |
| bvp_round_corner| dimension  | Banner圆角大小 |
| bvp_page_margin | dimension | 页面item间距 |
| bvp_reveal_width | dimension | 一屏多页模式下两边item漏出的宽度 |
| bvp_indicator_style | enum | indicator样式(circle/dash/round_rect)  |
| bvp_indicator_slide_mode | enum | indicator滑动模式(normal;smooth;worm;color;scale) |
| bvp_indicator_gravity | enum | indicator位置(center/start/end) |
| bvp_page_style | enum | page样式(normal/multi_page/multi_page_overlap/multi_page_scale) |
| bvp_indicator_visibility| enum | indicator visibility(visible/gone/invisible) |


## 如何使用

由于ViewPager2不支持Android support，因此BannerViewPager 3.0不再支持Android support，如果你仍在使用Android support，请移步[BannerViewPager 2.x版本](https://github.com/zhpanvip/BannerViewPager/tree/v_2.x)

### 1.gradle中添加依赖
   
在项目的root build.gradle中添加如下配置：
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	
```
添加依赖

```
implementation 'com.github.zhpanvip:BannerViewPager:latestVersion'

```
 latestVersion:[![latestVersion](https://jitpack.io/v/zhpanvip/BannerViewPager.svg)](https://jitpack.io/#zhpanvip/BannerViewPager)

### 2.在xml文件中添加如下代码：

```
    <com.zhpan.bannerview.BannerViewPager
            android:id="@+id/banner_view"
            android:layout_width="match_parent"
            android:layout_margin="10dp"
            android:layout_height="160dp" />
```



## 有问题可以扫码加QQ群交流

 ![QQ交流群60902509](https://gitee.com/zhpanvip/images/raw/master/project/group/qq_group.png)



## <span id="Sponsor"> 赞赏 </span>


**如果您觉得本库还不错，您可以对BVP打赏哦，您的支持将是我持续维护的动力。**

| 支付宝 | 微信支付 |
|--|--|
| ![NORMAL](Resource/pay/支付宝支付.png) |  ![SMOOTH](Resource/pay/微信支付.png) |


## 感谢

[玩Android](https://wanandroid.com/)

[finite-cover-flow](https://github.com/KoderLabs/finite-cover-flow)

[zguop-banner](https://github.com/zguop/banner)

License
-------

    Copyright 2017-2020 lukaslee

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.






