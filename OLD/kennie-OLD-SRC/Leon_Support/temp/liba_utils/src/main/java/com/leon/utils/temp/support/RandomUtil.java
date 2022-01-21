package com.leon.utils.temp.support;

import java.util.Random;

/**
 * <b>Project:</b> liba_utils<br>
 * <b>Create Date:</b> 16/9/19<br>
 * <b>Author:</b> LearKoo<br>
 * <b>Description:</b>
 * 随机数工具类
 * <br>
 */
public class RandomUtil {

    /**
     * <p>
     * 为了确保同一毫秒不能返回相同的值,不同声明在方法里面.<br>
     * 把Random对象作为一个全局实例(static)来使用. Java中Random是线程安全的(内部进行了加锁处理);
     * </p>
     */
    private static final Random JVM_RANDOM = new Random();

    /**
     * Don't let anyone instantiate this class.
     */
    private RandomUtil() {
        //AssertionError不是必须的. 但它可以避免不小心在类的内部调用构造器. 保证该类在任何情况下都不会被实例化.
        //see 《Effective Java》 2nd
        throw new AssertionError("No " + getClass().getName() + " instances for you!");
    }

    /**
     * 生成一个指定长度<code>length</code>的 <b>随机正整数</b>.
     * <h3>示例:</h3>
     * <blockquote>
     * <p>
     * <pre class="code">
     * RandomUtil.createRandomWithLength(2)
     * 生成的结果是可能是 89
     * </pre>
     * <p>
     * </blockquote>
     *
     * @param length 设定所取出随机数的长度.
     * @return
     */
    public static long createRandomWithLength(int length) {
        long num = 1;
        for (int i = 0; i < length; ++i) {
            num = num * 10;
        }

        // 该值大于等于 0.0 且小于 1.0 正号的 double 值
        double random = JVM_RANDOM.nextDouble();
        random = random < 0.1 ? random + 0.1 : random;// 可能出现 0.09346924349151808
        return (long) (random * num);
    }

}
