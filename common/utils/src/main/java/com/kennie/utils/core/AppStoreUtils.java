package com.kennie.utils.core;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.kennie.utils.manager.UtilsManager;

import java.util.List;

/**
 * Author：Kennie
 * Project：KennieUtils
 * Class：AppMarketUtils
 * Date：2021/12/12 23:15
 * Desc：App应用市场工具类
 *
 * <p>
 * --判断应用市场是否存在                                {@link #isMarketAvailable()}
 * --判断当前应用是否来源GooglePlay(从此下载)             {@link #isInstalledFromGooglePlay()}
 * --启动应用商店(自动化酷安、应用宝)                     {@link #startAutoAppStore(String appPackageName)}
 * --跳转到应用商店app详情界面                           {@link #startAppStoreDetail(String appPackage)}
 * --跳转到应用商店app详情界面                           {@link #startAppStoreDetail(String appPackage, String marketPackage)}
 * </p>
 */
public class AppStoreUtils {
    private static final String TAG = AppStoreUtils.class.getSimpleName();
    public static final String MARKET_DATA = "market://details";
    private static final String MARKET_SCHEMA_URL = MARKET_DATA + "?id=";

    private volatile static AppStoreUtils instance;

    public static AppStoreUtils get() {
        if (instance == null) {
            synchronized (AppStoreUtils.class) {
                if (instance == null) {
                    instance = new AppStoreUtils();
                }
            }
        }
        return instance;
    }


    /**
     * 判断应用市场是否存在
     *
     * @return
     */
    public boolean isMarketAvailable() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(MARKET_DATA));
        List<ResolveInfo> list = UtilsManager.get().getAppContext().getPackageManager().queryIntentActivities(intent, 0);
        return list != null && list.size() > 0;
    }


    /**
     * 判断当前应用是否来源GooglePlay(从此下载)
     *
     * @return
     */
    public boolean isInstalledFromGooglePlay() {
        try {
            String installer = UtilsManager.get().getAppContext().getPackageManager().getInstallerPackageName(UtilsManager.get().getAppContext().getPackageName());
            return installer != null && installer.equals(APP_STORE_PACKAGE.GOOGLE_PACKAGE_NAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 启动应用商店
     * (默认手机厂商自带应用商店，如无，自动跳转到 酷安\应用宝 应用商店)
     *
     * @param appPackageName 包名
     * @return
     */
    public boolean startAutoAppStore(String appPackageName) {
        try {
            String deviceBrand = getDeviceBrand(); //获得手机厂商
            Log.i(TAG, "当前手机厂商品牌 ：" + deviceBrand);
            //根据厂商获取对应市场的包名
            String brandName = deviceBrand.toUpperCase(); //大写
            if (TextUtils.isEmpty(brandName)) {
                Log.e(TAG, "没有读取到手机厂商~~");
                return false;
            }
            String marketPackageName = getBrandName(brandName);
            if (TextUtils.isEmpty(marketPackageName)) {
                //手机不再列表里面,去尝试寻找
                //检测酷安和应用宝是否在手机上安装,如果安装，则跳转到这两个市场的其中一个
                boolean isExit1 = isCheckCoolAPKOrYYB(APP_STORE_PACKAGE.COOLAPK_PACKAGE_NAME);
                if (isExit1) {
                    startAppStoreDetail(appPackageName, APP_STORE_PACKAGE.COOLAPK_PACKAGE_NAME);
                    return true;
                }
                boolean isExit2 = isCheckCoolAPKOrYYB(APP_STORE_PACKAGE.TENCENT_PACKAGE_NAME);
                if (isExit2) {
                    startAppStoreDetail(appPackageName, APP_STORE_PACKAGE.TENCENT_PACKAGE_NAME);
                    return true;
                }
            }
            startAppStoreDetail(appPackageName, marketPackageName);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "startAutoAppStore 异常 ：" + e.getMessage());

        }
        return false;
    }

    /**
     * 跳转到应用商店app详情界面
     *
     * @param appPackage app包名
     */
    public boolean startAppStoreDetail(String appPackage) {
        return startAppStoreDetail(appPackage, "");
    }

    /**
     * 启动到应用商店app详情界面
     *
     * @param appPackage    目标App的包名
     * @param marketPackage 应用商店包名 ,如果为"" 则由系统弹出应用商店
     *                      列表供用户选择,否则调转到目标市场的应用详情界面，某些应用商店可能会失败
     */
    public boolean startAppStoreDetail(String appPackage, String marketPackage) {
        try {
            if (TextUtils.isEmpty(appPackage)) return false;
            Uri uri = Uri.parse(MARKET_SCHEMA_URL + appPackage);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (!TextUtils.isEmpty(marketPackage)) {
                intent.setPackage(marketPackage);
                if (APP_STORE_PACKAGE.SAMSUNG_PACKAGE_NAME.equalsIgnoreCase(marketPackage)) {
                    intent.setData(Uri.parse(String.format("samsungapps://ProductDetail/%s", appPackage)));
                }
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            UtilsManager.get().getAppContext().startActivity(intent);
            return true;
        } catch (ActivityNotFoundException anf) {
            Log.e("AppStoreUtils", "startAppStoreDetail 要跳转的应用市场不存在!");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("AppStoreUtils", " startAppStoreDetail 其他错误：" + e.getMessage());
        }
        return false;
    }

    /***
     * 判断是否是酷安或者是应用宝
     * @param packageName 包名
     * @return
     */
    private boolean isCheckCoolAPKOrYYB(String packageName) {
        return AppUtils.isInstalled(packageName);
    }

    /**
     * 获取手机厂商
     */
    private String getDeviceBrand() {
        return android.os.Build.BRAND;
    }

    private String getBrandName(String brandName) {
        if (BRAND.HUAWEI_BRAND.equals(brandName)) {
            //华为
            return APP_STORE_PACKAGE.HUAWEI_PACKAGE_NAME;
        } else if (BRAND.OPPO_BRAND.equals(brandName)) {
            //oppo
            return APP_STORE_PACKAGE.OPPO_PACKAGE_NAME;
        } else if (BRAND.VIVO_BRAND.equals(brandName)) {
            //vivo
            return APP_STORE_PACKAGE.VIVO_PACKAGE_NAME;
        } else if (BRAND.XIAOMI_BRAND.equals(brandName)) {
            //小米
            return APP_STORE_PACKAGE.XIAOMI_PACKAGE_NAME;
        } else if (BRAND.REDMI_BRAND.equals(brandName)) {
            //红米
            return APP_STORE_PACKAGE.XIAOMI_PACKAGE_NAME;
        } else if (BRAND.LENOVO_BRAND.equals(brandName)) {
            //联想
            return APP_STORE_PACKAGE.LIANXIANG_PACKAGE_NAME;
        } else if (BRAND.QH360_BRAND.equals(brandName)) {
            //360
            return APP_STORE_PACKAGE.QH360_PACKAGE_NAME;
        } else if (BRAND.MEIZU_BRAND.equals(brandName)) {
            //魅族
            return APP_STORE_PACKAGE.MEIZU_PACKAGE_NAME;
        } else if (BRAND.HONOR_BRAND.equals(brandName)) {
            //华为荣耀
            return APP_STORE_PACKAGE.HUAWEI_PACKAGE_NAME;
        } else if (BRAND.XIAOLAJIAO_BRAND.equals(brandName)) {
            //小辣椒
            return APP_STORE_PACKAGE.ZHUOYI_PACKAGE_NAME;
        } else if (BRAND.ZTE_BRAND.equals(brandName)) {
            //zte
            return APP_STORE_PACKAGE.ZTE_PACKAGE_NAME;
        } else if (BRAND.NIUBIA_BRAND.equals(brandName)) {
            //努比亚
            return APP_STORE_PACKAGE.NIUBIA_PACKAGE_NAME;
        } else if (BRAND.ONE_PLUS_BRAND.equals(brandName)) {
            //OnePlus
            return APP_STORE_PACKAGE.OPPO_PACKAGE_NAME;
        } else if (BRAND.SAMSUNG_BRAND.equals(brandName)) {
            //三星
            return APP_STORE_PACKAGE.SAMSUNG_PACKAGE_NAME;
        } else if (BRAND.MEITU_BRAND.equals(brandName)) {
            //美图
            return APP_STORE_PACKAGE.MEITU_PACKAGE_NAME;
        } else if (BRAND.SONY_BRAND.equals(brandName)) {
            //索尼
            return APP_STORE_PACKAGE.GOOGLE_PACKAGE_NAME;
        } else if (BRAND.GOOGLE_BRAND.equals(brandName)) {
            //google
            return APP_STORE_PACKAGE.GOOGLE_PACKAGE_NAME;
        }
        return "";
    }

    /**
     * 手机厂商名称
     */
    public static class BRAND {
        public static final String HUAWEI_BRAND = "HUAWEI";         //HUAWEI_PACKAGE_NAME
        public static final String HONOR_BRAND = "HONOR";           //HUAWEI_PACKAGE_NAME
        public static final String OPPO_BRAND = "OPPO";             //OPPO_PACKAGE_NAME
        public static final String MEIZU_BRAND = "MEIZU";           //MEIZU_PACKAGE_NAME
        public static final String VIVO_BRAND = "VIVO";             //VIVO_PACKAGE_NAME
        public static final String XIAOMI_BRAND = "XIAOMI";         //XIAOMI_PACKAGE_NAME
        public static final String REDMI_BRAND = "REDMI";           //REDMI_PACKAGE_NAME
        public static final String LENOVO_BRAND = "LENOVO";         //LIANXIANG_PACKAGE_NAME //Lenovo
        public static final String ZTE_BRAND = "ZTE";               //ZTE_PACKAGE_NAME
        public static final String XIAOLAJIAO_BRAND = "XIAOLAJIAO"; //ZHUOYI_PACKAGE_NAME
        public static final String QH360_BRAND = "360";             //QH360_PACKAGE_NAME
        public static final String NIUBIA_BRAND = "NUBIA";          //NIUBIA_PACKAGE_NAME
        public static final String ONE_PLUS_BRAND = "ONEPLUS";      //OPPO_PACKAGE_NAME
        public static final String MEITU_BRAND = "MEITU";           //MEITU_PACKAGE_NAME
        public static final String SAMSUNG_BRAND = "SAMSUNG";       //SAMSUNG_PACKAGE_NAME
        public static final String SONY_BRAND = "SONY";             //GOOGLE_PACKAGE_NAME
        public static final String GOOGLE_BRAND = "GOOGLE";         //GOOGLE_PACKAGE_NAME

        public static final String HTC_BRAND = "HTC"; //未知应用商店包名
        public static final String ZUK_BRAND = "ZUK"; //未知应用商店包名
    }

    /**
     * 华为，oppo,vivo,小米Redmi，360，联想，魅族，安智，百度，阿里，应用宝，google，豌豆荚，pp助手,酷安
     **/
    public static class APP_STORE_PACKAGE {
        public static final String OPPO_PACKAGE_NAME = "com.oppo.market";                    //oppo
        public static final String VIVO_PACKAGE_NAME = "com.bbk.appstore";                   //vivo
        public static final String HUAWEI_PACKAGE_NAME = "com.huawei.appmarket";             //华为
        public static final String QH360_PACKAGE_NAME = "com.qihoo.appstore";                //360
        public static final String XIAOMI_PACKAGE_NAME = "com.xiaomi.market";                //小米
        public static final String MEIZU_PACKAGE_NAME = "com.meizu.mstore";                  //，魅族
        public static final String LIANXIANG_PACKAGE_NAME = "com.lenovo.leos.appstore";      //联想
        public static final String SAMSUNG_PACKAGE_NAME = "com.sec.android.app.samsungapps"; //三星
        public static final String ZTE_PACKAGE_NAME = "zte.com.market";                      //zte
        public static final String ZHUOYI_PACKAGE_NAME = "com.zhuoyi.market";                //卓易
        public static final String GOOGLE_PACKAGE_NAME = "com.android.vending";              //google
        public static final String NIUBIA_PACKAGE_NAME = "com.nubia.neostore";               //努比亚
        public static final String MEITU_PACKAGE_NAME = "com.android.mobile.appstore";       //美图
        public static final String BAIDU_PACKAGE_NAME = "com.baidu.appsearch";               //baidu
        public static final String TENCENT_PACKAGE_NAME = "com.tencent.android.qqdownloader";//应用宝
        public static final String PPZHUSHOU_PACKAGE_NAME = "com.pp.assistant";              //pp助手
        public static final String ANZHI_PACKAGE_NAME = "com.goapk.market";                  //安智市场
        public static final String WANDOUJIA_PACKAGE_NAME = "com.wandoujia.phonenix2";       //豌豆荚
        public static final String COOLAPK_PACKAGE_NAME = "com.coolapk.market";              //酷安

        //public static final String SUONI_PACKAGE_NAME = "com.android.vending";             //索尼
    }
}
