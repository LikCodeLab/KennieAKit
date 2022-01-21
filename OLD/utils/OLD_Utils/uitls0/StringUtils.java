package cn.lukaslee.utils.support;


import java.security.MessageDigest;
import java.text.DecimalFormat;


/**
 * @author LukasLee
 * @time 2020/3/30 15:04
 * @classname DateUtils
 * description:字符串处理类
 *
 *  CrashHandler crashHandler = CrashHandler.getInstance();
 *         crashHandler.init(getApplicationContext());
 *         CrashHandler.getLog();
 */
@SuppressWarnings("unused")
public class StringUtils {


    /**
     * 判断字符串为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return null == str || str.trim().length() == 0;
    }

    /**
     * MD5
     *
     * @param string
     * @return
     * @throws Exception
     */
    public static String encodeToMD5(String string) {
        byte[] hash = new byte[0];
        try {
            hash = MessageDigest.getInstance("MD5").digest(
                    string.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) {
                hex.append("0");
            }
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

    /**
     * 保留两位小数
     *
     * @param doubleString
     * @return
     */
    public static double retainString(double doubleString) {
        double result = 0;
        try {
            DecimalFormat df = new DecimalFormat("######0.00");
            result = Double.parseDouble(df.format(doubleString));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 获取百分比字符串
     *
     * @param num
     * @param total
     * @return
     */
    public static String getPercentString(int num, int total) {
        // 总数为0不用计算
        String result = "0%";
        if (total > 0) {
            try {
                double numDouble = num * 1.0;
                double resultTemp = numDouble / total;
                // format percent
                DecimalFormat df = new DecimalFormat("0.00%");
                result = df.format(resultTemp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 获取百分比字符串
     *
     * @param num
     * @param total
     * @return
     */
    public static String getPercentString(long num, long total) {
        // 总数为0不用计算
        String result = "0%";
        if (total > 0) {
            try {
                double numDouble = num * 1.0;
                double resultTemp = numDouble / total;
                // format percent
                DecimalFormat df = new DecimalFormat("0.00%");
                result = df.format(resultTemp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 获取百分比字符串
     *
     * @param num
     * @param total
     * @return
     */
    public static String getPercentString(double num, double total) {
        // 总数为0不用计算
        String result = "0%";
        if (total > 0) {
            try {
                double resultTemp = num / total;
                // format percent
                DecimalFormat df = new DecimalFormat("0.00%");
                result = df.format(resultTemp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }



    /**
     * 判断是否是字符图片
     *
     * @param source
     * @return
     */
    public static boolean containsEmoji(String source) {
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char hs = source.charAt(i);
            if (0xd800 <= hs && hs <= 0xdbff) {
                if (source.length() > 1) {
                    char ls = source.charAt(i + 1);
                    int uc = ((hs - 0xd800) * 0x400) + (ls - 0xdc00) + 0x10000;
                    if (0x1d000 <= uc && uc <= 0x1f77f) {
                        return true;
                    }
                }
            } else {
                // non surrogate
                if (0x2100 <= hs && hs <= 0x27ff && hs != 0x263b) {
                    return true;
                } else if (0x2B05 <= hs && hs <= 0x2b07) {
                    return true;
                } else if (0x2934 <= hs && hs <= 0x2935) {
                    return true;
                } else if (0x3297 <= hs && hs <= 0x3299) {
                    return true;
                } else if (hs == 0xa9 || hs == 0xae || hs == 0x303d
                        || hs == 0x3030 || hs == 0x2b55 || hs == 0x2b1c
                        || hs == 0x2b1b || hs == 0x2b50 || hs == 0x231a) {
                    return true;
                }
                if (source.length() > 1 && i < source.length() - 1) {
                    char ls = source.charAt(i + 1);
                    if (ls == 0x20e3) {
                        return true;
                    }
                }
            }
        }
        return false;
    }



    /**
     * 验证用户密码格式
     *
     * @param pwd
     * @return
     */
    public static boolean isPassWord(String pwd) {
        return pwd.matches("^[0-9a-zA-Z]{6,12}");
    }

    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA)
                || (codePoint == 0xD)
                || ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
                || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
                || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }

    /**
     * 包含大小写字母及数字且在6-12位
     *
     * @param str
     * @return
     */
    public static boolean isLetterDigit(String str) {
        //定义一个boolean值，用来表示是否包含数字
        boolean isDigit = false;
        //定义一个boolean值，用来表示是否包含字母
        boolean isLetter = false;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                //用char包装类中的判断数字的方法判断每一个字符
                isDigit = true;
            } else if (Character.isLetter(str.charAt(i))) {
                //用char包装类中的判断字母的方法判断每一个字符
                isLetter = true;
            }
        }
        String regex = "^[a-zA-Z0-9]{6,12}$";
        boolean isRight = isDigit || isLetter || str.matches(regex);
        return isRight;
    }
}
