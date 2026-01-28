package com.liufan.learn.coreclass;

import java.math.BigInteger;

/**
 * 大整数（BigInteger），用于表示任意大小的整数
 * <p>
 * BigInteger和Integer、Long一样，也是不可变类，并且也继承自Number类。
 * @see WrapperClass
 */
public class LearnBigInteger extends com.liufan.learn.basicdata.BasicData {
    public static void practice() {
        /*
        在Java中，由CPU原生提供的整型最大范围是64位long型整数。使用long型整数可以直接通过CPU指令进行计算，速度非常快。

        如果我们使用的整数范围超过了long型怎么办？这个时候，就只能用软件来模拟一个大整数。
        java.math.BigInteger就是用来表示任意大小的整数。BigInteger内部用一个int[]数组来模拟一个非常大的整数：
         */
        BigInteger bi = new BigInteger("1234567890");
        System.out.println(bi.pow(5));

        // 对BigInteger做运算的时候，只能使用实例方法
        BigInteger bi2 = new BigInteger("123456789000");
        BigInteger sum = bi.add(bi2);
        System.out.println(sum);
        // 和long型整数运算比，BigInteger不会有范围限制，但缺点是速度比较慢。

        // BigInteger转换成基本类型：byte、short、int、long、float、double。
        // 如果BigInteger表示的范围超过了基本类型的范围，转换时将丢失高位信息，即结果不一定是准确的
        System.out.println(bi2.longValue());
        // 如果需要准确地转换成基本类型，可以使用intValueExact()、longValueExact()等方法，在转换时如果超出范围，
        // 将直接抛出ArithmeticException异常。
        // System.out.println(bi2.multiply(bi2).longValueExact()); //  java.lang.ArithmeticException: BigInteger out of long range

        // 如果BigInteger的值甚至超过了float的最大范围（3.4x1038），那么返回的float是什么呢？
        BigInteger n = new BigInteger("999999").pow(99);
        System.out.println(n.floatValue()); // Infinity
    }
}
