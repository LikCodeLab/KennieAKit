package com.leon.utils.temp.support;


import android.text.TextUtils;

/**
 * 版本管理工具类
 */
public class VersionUtil {

    /**
     * 比较版本号大小(数据传空默认为0)
     * 描述:通常版本号如：1.3.20.8，6.82.20160101，8.5a/8.5c等；
     * 通用规则就是，先将版本字符串按照点号分割，然后主版本与主版本比较、
     * 此版本与此版本比较，如此按序一级一级往后比较，直到有分出大小；
     * 值得注意的是，很多比较版本号的方法都先将字符串转换成int或者double类型，
     * 这样做未必通用，因为可能含有字母，如8.5c这样的版本号；
     * 通用的方式依然是将分割后的字符串当做字符串来比较，不过，比较字符串之前，先比较位数；
     * TODO 注意点
     * 注意：1.其中 split 方法入参为正则匹配表达式，
     * 不能用"."("."在正则表达式里匹配任何值)，需要用"\\."，才算是按点号分割；
     * 2.这样，先分割成子串数组，再挨个比较子版本号，比较子版本号时，先比较位数，
     * 位数大的就大，位数一样时再按字符串比较方式比较；
     * 3.如果全部比较完（其中一个版本号比较完）之后，再看一下哪个版本号有更更多的子版本号，
     * 也就是分割后的数组长度，有子版本号的为大；
     * 4.这样就比较完善地考虑了各种情况，并比较出版本号大小；包括有字母后缀的也可以使用；
     * 5.如 "9.9", "10.8.8.6" ，如果直接按字符串比较，则会前者大，后者小，而明显是错误的；
     * 分割后比较第一个主版本9与10，从位数上，就已经得出结果后者大；
     * 6.再如 "9.9b", "9.8a" 等也适用，如果用转换成int或者double的方法就不适用；
     *
     * @param getVersion     获取到的版本
     * @param currentVersion 当前app的版本
     * @return 0 版本相同 1 有更新  -1 无更新
     * @throws Exception
     */
    public static int compareVer(String getVersion, String currentVersion) {
        //版本号为空 默认为0
        if (TextUtils.isEmpty(getVersion) || TextUtils.isEmpty(currentVersion)) {
            return 0;
        }
        //版本号相同 默认为0
        if (getVersion.equals(currentVersion)) {
            return 0;
        }
        String[] versionArray1 = getVersion.split("\\.");//注意此处为正则匹配，不能用"."；
        String[] versionArray2 = currentVersion.split("\\.");
        int idx = 0;
        int minLength = Math.min(versionArray1.length, versionArray2.length);//取最小长度值
        int diff = 0;
        while (idx < minLength
                && (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0//先比较长度
                && (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {//再比较字符
            ++idx;
        }
        //如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；
        diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;
        return diff;
    }

    /**
     * http://blog.csdn.net/wzy_1988/article/details/44060525
     * @param version1
     * @param version2
     * @return
     */
    public static int compareVersion(String version1, String version2) {
        if (version1.equals(version2)) {
            return 0;
        }

        String[] version1Array = version1.split("\\.");
        String[] version2Array = version2.split("\\.");

        int index = 0;
        int minLen = Math.min(version1Array.length, version2Array.length);
        int diff = 0;

        while (index < minLen && (diff = Integer.parseInt(version1Array[index]) - Integer.parseInt(version2Array[index])) == 0) {
            index++;
        }

        if (diff == 0) {
            for (int i = index; i < version1Array.length; i++) {
                if (Integer.parseInt(version1Array[i]) > 0) {
                    return 1;
                }
            }

            for (int i = index; i < version2Array.length; i++) {
                if (Integer.parseInt(version2Array[i]) > 0) {
                    return -1;
                }
            }

            return 0;
        } else {
            return diff > 0 ? 1 : -1;
        }
    }

    /**
     * 判断是否为最新版本方法 将版本号根据.切分为int数组 比较
     * http://www.android100.org/html/201512/07/201635.html
     * @param localVersion
     *            本地版本号
     * @param onlineVersion
     *            线上版本号
     * @return
     */
    public static boolean isAppNewVersion(String localVersion, String onlineVersion)
    {
        if (localVersion.equals(onlineVersion))
        {
            return false;
        }
        String[] localArray = localVersion.split("\\.");
        String[] onlineArray = onlineVersion.split("\\.");

        int length = localArray.length < onlineArray.length ? localArray.length : onlineArray.length;

        for (int i = 0; i < length; i++)
        {
            if (Integer.parseInt(onlineArray[i]) > Integer.parseInt(localArray[i]))
            {
                return true;
            }
            else if (Integer.parseInt(onlineArray[i]) < Integer.parseInt(localArray[i]))
            {
                return false;
            }
            // 相等 比较下一组值
        }

        return true;
    }
}
