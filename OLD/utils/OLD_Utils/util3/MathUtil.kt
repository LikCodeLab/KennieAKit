package cn.lukas.utils.core.old

import android.text.TextUtils
import java.math.BigDecimal
import java.util.regex.Pattern

/**
 *@author：李佳模
 *ON：2020/9/29
 *description: 数学类工具类
 * https://github.com/lijiamu/EasyUtil_Android
 */
object MathUtil {
    /**
     * 判断字符串是否是数字（包含整数、小数）
     * @param stNumber 传入值
     */
    fun isNumeric(value: String): Boolean {
        if (TextUtils.isEmpty(value)) {
            return false
        }
        val pattern = Pattern.compile("^(\\-|\\+)?\\d+(\\.\\d+)?$")
        val isNum = pattern.matcher(value)
        return isNum.matches()
    }

    /**
     * 判断字符串是否是整数
     * @param stNumber 传入值
     */
    fun isNumericInt(value: String): Boolean {
        if (TextUtils.isEmpty(value)) {
            return false
        }
        val pattern = Pattern.compile("^[0-9]*[0-9][0-9]*$")
        val isNum = pattern.matcher(value)
        return isNum.matches()
    }

    /**
     * 保留小数位数 并四舍五入
     * @param value 传入值
     * @param size 保留的小数位数
     * @return
     */
    fun numericRounding(value: String, size: Int): String {
        if (isNumeric(value)) {
            val bg = BigDecimal(value)
            return bg.setScale(size, BigDecimal.ROUND_HALF_UP).toPlainString()
        } else {
            return value
        }

    }
}