package com.liufan.learn.coreclass;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * BigDecimal，用于表示一个任意大小且精度完全准确的浮点数，常用于财务计算。
 * <p>
 * BigDecimal也是从Number继承的，也是不可变对象。
 * 实际上一个BigDecimal是通过一个BigInteger和一个scale来表示的，即BigInteger表示一个完整的整数，而scale表示小数位数。
 * <pre>
 * public class BigDecimal extends Number implements Comparable<BigDecimal> {
 *     private final BigInteger intVal;
 *     private final int scale;
 * }
 * </pre>
 * @see WrapperClass
 * @see LearnBigInteger
 */
public class LearnBigDecimal extends com.liufan.learn.basicdata.BasicData {
    public static void practice() {
        BigDecimal bd = new BigDecimal("123.4567");
        System.out.println(bd.multiply(bd));

        // scale()表示小数位数
        System.out.println(bd.scale());

        // stripTrailingZeros() 可以将一个BigDecimal格式化为一个相等的，但去掉了末尾0的BigDecimal
        BigDecimal d1 = new BigDecimal("123.4500");
        BigDecimal d2 = d1.stripTrailingZeros();
        System.out.println(d1.scale()); // 4
        System.out.println(d2.scale()); // 2,因为去掉了00
        BigDecimal d3 = new BigDecimal("1234500");
        BigDecimal d4 = d3.stripTrailingZeros();
        System.out.println(d3.scale()); // 0
        // 如果一个BigDecimal的scale()返回负数，例如，-2，表示这个数是个整数，并且末尾有2个0。
        System.out.println(d4.scale()); // -2

        // 对一个BigDecimal设置它的scale，如果精度比原始值低，那么按照指定的方法进行四舍五入或者直接截断
        BigDecimal d5 = new BigDecimal("123.456789");
        BigDecimal d6 = d5.setScale(4, RoundingMode.HALF_UP); // 四舍五入，123.4568
        BigDecimal d7 = d5.setScale(4, RoundingMode.DOWN);    // 直接截断，123.4567
        System.out.println(d6);
        System.out.println(d7);

        // 对BigDecimal做加、减、乘时，精度不会丢失，但是做除法时，存在无法除尽的情况，这时，就必须指定精度以及如何进行截断
        BigDecimal d8 = new BigDecimal("123.456");
        BigDecimal d9 = new BigDecimal("23.456789");
        BigDecimal d10 = d8.divide(d9, 10, RoundingMode.HALF_UP); // 保留10位小数并四舍五入
        System.out.println(d10);
        // BigDecimal d11 = d8.divide(d9); // 报错：ArithmeticException，因为除不尽

        // 对BigDecimal做除法的同时求余数
        BigDecimal n = new BigDecimal("12.345");
        BigDecimal m = new BigDecimal("0.12"); // 除数
        BigDecimal[] dr = n.divideAndRemainder(m);
        // 调用divideAndRemainder()方法时，返回的数组包含两个BigDecimal，分别是商和余数，其中商总是整数，余数不会大于除数。
        System.out.println(dr[0]); // 102   商，商总是整数
        System.out.println(dr[1]); // 0.105 余数，余数不会大于除数

        // 可以利用这个方法判断两个BigDecimal是否是整数倍数
        BigDecimal n1 = new BigDecimal("12.75");
        BigDecimal m1 = new BigDecimal("0.15");
        BigDecimal[] dr1 = n1.divideAndRemainder(m1);
        if (dr1[1].signum() == 0) {
            System.out.println("n1是m1的整数倍");
        }
    }

    /**
     * 必须使用<code>compareTo()</code>方法来比较，它根据两个值的大小分别返回负数、正数和0，分别表示小于、大于和等于。
     * ⚠️注意不要使用<code>equals()</code>
     */
    public static void equals() {
        // 在比较两个BigDecimal的值是否相等时，要特别注意，使用equals()方法不但要求两个BigDecimal的值相等，还要求它们的scale()相等
        BigDecimal d1 = new BigDecimal("123.456");
        BigDecimal d2 = new BigDecimal("123.45600");
        System.out.println(d1.equals(d2)); // false,因为scale不同
        System.out.println(d1.equals(d2.stripTrailingZeros())); // true,因为d2去除尾部0后scale变为3
        System.out.println(d1.compareTo(d2)); // 0 = 相等, -1 = d1 < d2, 1 = d1 > d2
    }
}
