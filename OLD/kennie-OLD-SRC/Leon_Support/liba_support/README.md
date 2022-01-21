## API Guide

> SDK 使用手册。

### theme
Theme 是 Android 设计中的灵魂，通过 Theme 我们可以非常灵活的改变 view 或者组件的状态，统一的风格状态。SDK 中也继承了 Android 的这个特性。

1. SDKTheme
SDKTheme 继承自 "Theme.AppCompat.Light.DarkActionBar" ，同时增加 "base_background" 属性，通过该属性可以指定统一的背景。使用该框架时，你应该总是让你的 theme 继承自该 theme。

2. SDKTheme.NoActionBar
没有 ActionBar 的 theme。

3. SDKTheme.Dialog
Dialog theme。

4. SDKTheme.Dialog.Alert
AlertDialog theme。

### style

1. SDK
SDK 的基础 style，一般情况下所有的 style 都应该默认继承该 style。

    <!-- style of sdk-->
        <style name="SDK" parent="">
            <item name="android:layout_width">wrap_content</item>
            <item name="android:layout_height">wrap_content</item>
        </style>

2. SDK.Textview
TextView 默认的 style。字号默认为16dp。

        <style name="SDK.Textview">
                <item name="android:textSize">@dimen/text_size_16</item>
        </style>

3. SDK.Textview.small
小字号的 TextView。

        <style name="SDK.Textview.small">
                <item name="android:textSize">@dimen/text_size_14</item>
        </style>

### TextSize
SDK使用DP 作为字体大小单位。

    <dimen name="text_size_5">5dp</dimen>
    <dimen name="text_size_6">6dp</dimen>
    <dimen name="text_size_7">7dp</dimen>
    <dimen name="text_size_8">8dp</dimen>
    <dimen name="text_size_9">9dp</dimen>
    <dimen name="text_size_10">10dp</dimen>
    <dimen name="text_size_11">11dp</dimen>
    <dimen name="text_size_12">12dp</dimen>
    <dimen name="text_size_13">13dp</dimen>
    <dimen name="text_size_14">14dp</dimen>
    <dimen name="text_size_15">15dp</dimen>
    <dimen name="text_size_16">16dp</dimen>
    <dimen name="text_size_17">17dp</dimen>
    <dimen name="text_size_18">18dp</dimen>
    <dimen name="text_size_19">19dp</dimen>
    <dimen name="text_size_20">20dp</dimen>
    <dimen name="text_size_21">21dp</dimen>
    <dimen name="text_size_22">22dp</dimen>
    <dimen name="text_size_23">23dp</dimen>
    <dimen name="text_size_24">24dp</dimen>
    <dimen name="text_size_25">25dp</dimen>
    <dimen name="text_size_26">26dp</dimen>
    <dimen name="text_size_27">27dp</dimen>
    <dimen name="text_size_28">28dp</dimen>
    <dimen name="text_size_29">29dp</dimen>
    <dimen name="text_size_30">30dp</dimen>
    <dimen name="text_size_31">31dp</dimen>
    <dimen name="text_size_32">32dp</dimen>
    <dimen name="text_size_33">33dp</dimen>
    <dimen name="text_size_34">34dp</dimen>
    <dimen name="text_size_35">35dp</dimen>
    <dimen name="text_size_36">36dp</dimen>
    <dimen name="text_size_37">37dp</dimen>
    <dimen name="text_size_38">38dp</dimen>
    <dimen name="text_size_39">39dp</dimen>
    <dimen name="text_size_40">40dp</dimen>
    <dimen name="text_size_41">41dp</dimen>
    <dimen name="text_size_42">42dp</dimen>
    <dimen name="text_size_43">43dp</dimen>
    <dimen name="text_size_44">44dp</dimen>
    <dimen name="text_size_45">45dp</dimen>
    <dimen name="text_size_46">46dp</dimen>
    <dimen name="text_size_47">47dp</dimen>
    <dimen name="text_size_48">48dp</dimen>
    <dimen name="text_size_49">49dp</dimen>
    <dimen name="text_size_50">50dp</dimen>


### dimen

SDK 也统一了 dimen。常用的 dimen 如下。

    <dimen name="dp0">0dp</dimen>
    <dimen name="dp1">1dp</dimen>
    <dimen name="dp2">2dp</dimen>
    <dimen name="dp3">3dp</dimen>
    <dimen name="dp4">4dp</dimen>
    <dimen name="dp5">5dp</dimen>
    <dimen name="dp6">6dp</dimen>
    <dimen name="dp8">8dp</dimen>
    <dimen name="dp10">10dp</dimen>
    <dimen name="dp12">12dp</dimen>
    <dimen name="dp16">16dp</dimen>
    <dimen name="dp18">18dp</dimen>
    <dimen name="dp20">20dp</dimen>

    <dimen name="small">4dp</dimen>
    <dimen name="xsmall">6dp</dimen>
    <dimen name="normal">8dp</dimen>
    <dimen name="xnormal">10dp</dimen>
    <dimen name="media">12dp</dimen>
    <dimen name="xmedia">14dp</dimen>
    <dimen name="large">16dp</dimen>
    <dimen name="xlarge">18dp</dimen>

### ids

SDK 针对常用的 id 进行了预先定义。

    <!-- holder adapter id-->
    <item name="tag_holder_default" type="id"/>

    <!-- content view id-->
    <item name="content" type="id"/>
    <!-- home view id-->
    <item name="home" type="id"/>
    <!-- toolbar id-->
    <item name="toolbar" type="id"/>
    <!-- listview id-->
    <item name="listview" type="id"/>
    <!-- gridview id-->
    <item name="gridview" type="id"/>

    <!-- error id -->
    <item name="error" type="id"/>
    <!-- swipe layout id-->
    <item name="base_swipe_layout" type="id"/>
    <!-- viewpager id-->
    <item name="viewpager" type="id"/>
    <!-- title id-->
    <item name="title" type="id"/>

### strings

暂时略。


### 基础组件

为了便于从框架层灵活控制及优化程序，SDK 针对Android 的基础组件以及常用组件进行了二次封装。所有以 M** 开始命名的组件都在 SDK 约束范围内。在编码时，如果没有特殊的情况，你的组件都应该继承自以下组件。如下表：

|   类名  |   extends  |   implements  |
| ------ | ---------- | ------------- |
| MActivity | Activity | Constants, ILocalBroadcastHandler, ISDKUIHelper, IAnalytic |
| MApplication | Application | Constants, ILocalBroadcastHandler |
| MBroadcastReceiver | BroadcastReceiver | Constants, ILocalBroadcastHandler |
| MContentProvider | ContentProvider | Constants |
| MFragment | Fragment | Constants, ILocalBroadcastHandler, ISDKUIHelper, IAnalytic |
| MListFragment<T> | MFragment | IItemCreator<View, T> |
| MFragmentActivity | FragmentActivity | Constants, ILocalBroadcastHandler, ISDKUIHelper, IAnalytic, IToolbarHandler |
| MIntentService | IntentService | Constants |
| MService | Service | Constants |
| MIntentService | IntentService | Constants, ILocalBroadcastHandler, ISDKUIHelper, IAnalytic |

### 接口

SDK 在设计时大量使用了面向接口编程的思想，同时将具体实现分离。这样处理是为了增强 SDK 的可扩展性，可定制行，同时使用接口也有利于形成规范。

1. Constants

    定义全局的静态值。
    当前阶段，为了简化使用 Intent 传值时 KEY 值的定义，该接口内部定了 Extra 类，并包含11个常用的终态 KEY 值。建议所有的 Intent 传值都使用 Extra 提供的 KEY值。

2. ILocalBroadcastHandler

    本地广播接口。众所周知，如果在程序中频繁的使用系统广播严重时将会引起严重的性能问题。而 support 包中针对这个问题提供了本地广播。LocalBroadcastHandlerImpl类实现了这个接口。主要方法见下表：

    |   方法名称    |   方法描述    |
    |   ------     |   -------    |
    | getRegisteredReceivers() | 获取所有已经注册的本地广播。如果广播已经反注册，则不返回该广播 |
    | unRegisterAllLocalReceivers() | 反注册所有已经注册的本地广播。 |
    | registerLocalReceiver(receiver, intentFilter) | 注册本地广播。|
    | registerLocalReceiver(receiver, actions...) | 注册本地广播，并根据传入的 action 值生成一个指定的 IntentFilter。 |
    | unRegisterLocalReceiver(receiver) | 反注册指定的广播。 |
    | sendLocalBroadcast(intent) | 发送一个广播。 |
    | sendLocalBroadcastSync(intent) | 发送一个同步广播。 |

3. ISDKUIHelper

    UI 的辅助工具类。BaseUIHelperImpl 实现了这个接口。
    
    |   方法名称    |   方法描述    |
    |   ------     |   -------    |
    | getHandler() | 返回一个 Handler 实例。 |
    | post(uirunnable) | 在主线程上运行一个 UIRunnable 对象。 |
    | post(uirunnable, delay) | 在主线程上运行一个 UIRunnable 对象，并延迟 delay 毫秒。 |
    | getIdentifier(context, type, name) | 根据资源的类型和名称获取该资源在资源管理器中对应的 ID。 |
    | getIdentifier(context, name) | 根据资源名称获取该资源的 ID。 |
    | getDrawableId(context, name) | 根据资源名称获取该资源对应的 drawble ID。 |
    | getString(context, id) | 根据资源 id 获取该资源的 string 值。 |
    | getString(context, id, args...) | 根据资源 id 以及额外数据，获取该资源的 string 值。 |
    | getStringArray(context, id) | 根据资源 id 获取该资源的 string[] 值。 |
    | toast(context, id, length) |  展示一个 Toast。 |
    | toast(context, id) |  展示一个 Toast。 |

4. IAnalytic

    数据搜集接口。用于搜集页面跳转信息，页面停留时长，自定的事件等。Activity、Fragment 已经实现了该接口，并默认开启页面统计。AnalyticHelper 实现了该接口并默认接入友盟统计。

    |   方法名称    |   方法描述    |
    |   -------    |   -------    |
    | openActivityDurationTrack(track) | 是否开启统计 Activity 页面停留时长。在 FragmentActivity 中 Fragment 实现了页面的传统功能，如果 Fragment 中对页面停留时长进行统计，则会对结果造成影响。FragmentActivity 中默认关闭。|
    | onEVResume(context) | 页面已经展示。 |
    | onEVPause(context) | 页面已经暂停。 |
    | onEVPageStart(label) | 页面开始展示。 |
    | onEVPageEnd(label) | 页面停止展示。 |
    | onEVEvent(context, label) | 自定义统计信息，对 label 指定的事件进行计数。 |
    | onEVEvent(context, label, maps) | 同上。maps 中允许传递额外的信息，如出生日期、性别等。 |
    | onEVEvent(context, label, maps, duration) | 同上，并统计单个事件停留的时长。|
    
    关于自定义事件统计的详细示例。

        // 示例1，统计简单事件。
        onEVEvent(this, "更新天气");
        
        // 示例2，统计用户注册时填入的男女性别数量
        final Map<String, String> maps = new HashMap<String, String>();
        maps.put("性别", "男");
        onEVEvent(this, "性别统计", maps);// 调用之后，友盟后台中可以看到 "性别男" 的数量增加了1.
        
5. IToolbarHandler

    Toolbar 配置接口。用于配置 Toolbar。ToolbarHandlerImpl 实现了该接口。
    
    |   方法名称    |   方法描述    |
    |   -------    |   -------    |
    | setupToolbar(appCompatActivity, toolbar) | 配置 Toolbar。 |
    | onOptionsItemSelected(item) | 响应 ItemSelected 事件。 |

### 基础模块

> 提供基本的模块。

1. crash

    异常崩溃搜集模块。
    当程序异常崩溃后，该模块会搜集崩溃信息，并上报到指定的后台。已经默认集成了友盟崩溃统计，在程序下次启动后会上报异常崩溃信息。
    Framework 已经默认集成了该模块，默认情况下无需额外配置。


### 友盟统计

1. 为了简化数据统计工作以及对统计逻辑的集中控制，framework 默认集成了友盟统计组件。关于页面统计、自定义事件统计请参见接口中的IAnalytic。
2. 正常使用友盟统计，仅需要配置 UMENG_APPKEY 以及 UMENG_CHANNEL，在 AndroidManifest.xml 文件中加入以下代码：

        <!-- 友盟统计-->
        <meta-data
            android:name="UMENG_APPKEY"
            android:value="你的 APPKEY"
            tools:replace="android:value"/>

        <meta-data
            android:name="UMENG_CHANNEL"
            android:value="你的 CHANNEL"
            tools:replace="android:value"/>
    

### 适配器

SDK 基于 HolderAdapter 模型实现了一套便于扩展和快速使用的 Adapter 框架。基于此框架进行开发时，无需关注除视图以外的任何细节处理。





