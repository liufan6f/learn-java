package com.liufan.learn.coreclass;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HexFormat;
import java.util.Random;

/**
 * Java的核心库提供了大量的现成的类供我们使用
 */
public class Utils {
    /**
     * Math，数学计算
     */
    public static void math() {
        // 求绝对值
        System.out.println(Math.abs(-100));   // 100

        // 取最大值或最小值
        System.out.println(Math.max(98, 99)); // 99
        System.out.println(Math.min(98, 99)); // 98

        // Xⁿ次方
        System.out.println(Math.pow(2, 10));  // 2的10次方=1024

        // 计算√￣x
        System.out.println(Math.sqrt(2));     // 2的平方根=1.4142135623730951

        // 计算自然常数eⁿ次方
        System.out.println(Math.exp(2));
        // 计算以e为底的对数
        System.out.println(Math.log(100));
        // 计算以10为底的对数
        System.out.println(Math.log10(100)); // 2

        // 三角函数
        System.out.println(Math.sin(3.14));
        System.out.println(Math.cos(3.14));
        System.out.println(Math.tan(3.14));
        System.out.println(Math.asin(1));
        System.out.println(Math.acos(1));

        // 数学常量
        System.out.println(Math.PI);
        System.out.println(Math.E);

        // 生成一个随机数x，x的范围是0 <= x < 1
        System.out.println(Math.random()); // 每次都不一样
        mathRamdom(); // 生成一个区间在[MIN, MAX)的随机数
    }

    /**
     * 生成一个区间在[MIN, MAX)的随机数
     */
    private static void mathRamdom() {
        System.out.println(random(10, 50));
        System.out.println((int) random(20, 40));
        /*
        有些同学可能注意到Java标准库还提供了一个StrictMath，它提供了和Math几乎一模一样的方法。这两个类的区别在于，
        由于浮点数计算存在误差，不同的平台（例如x86和ARM）计算的结果可能不一致（指误差不同），因此，
        StrictMath保证所有平台计算结果都是完全相同的，而Math会尽量针对平台优化计算速度，所以，绝大多数情况下，
        使用Math就足够了。
         */
    }

    private static double random(double min, double max) {
        double x = Math.random();     // x的范围是[0,1)
        return x * (max - min) + min; // 假设min是10，max是50，则y的范围是[10,50)
    }

    /**
     * HexFormat，byte[] 数组与十六进制字符串转换
     */
    public static void hexFormat() {
        // byte[] 数组转十六进制字符串
        byte[] data = "Hello".getBytes();
        HexFormat hf = HexFormat.of();
        System.out.println(hf.formatHex(data));  // 48656c6c6f
        // byte[] 数组转十六进制字符串，带格式
        HexFormat hf1 = HexFormat.ofDelimiter(" ").withPrefix("0x").withUpperCase();
        System.out.println(hf1.formatHex(data)); // 0x48 0x65 0x6C 0x6C 0x6F

        // 十六进制字符串 转 byte[] 数组
        byte[] bs = HexFormat.of().parseHex("48656c6c6f");
        System.out.println(Arrays.toString(bs));
    }

    /**
     * Random，伪随机数
     * <p>
     * 所谓伪随机数，是指只要给定一个初始的种子，产生的随机数序列是完全一样的。
     * @see #mathRamdom() Math.random()内部调用了Random类
     */
    public static void random() {
        // 当创建Random实例时，如果不给定种子，就使用系统当前时间戳作为种子，因此每次运行时，种子不同，得到的伪随机数序列就不同。
        Random r = new Random();
        System.out.println(r.nextInt());                    // 生成一个int
        System.out.println(r.nextInt(10));           // 生成一个[0,10)之间的int
        System.out.println(r.nextInt(10, 20)); // 生成一个[10,20)之间的int
        System.out.println(r.nextDouble());                 // 生成一个[0,1)之间的double

        // 如果我们在创建Random实例时指定一个种子，就会得到完全确定的随机数序列
        Random r1 = new Random(12345);
        for (int i = 0; i < 10; i++) {
            System.out.println(r1.nextInt(100)); // 51, 80, 41, 28, 55...
        }
    }

    /**
     * SecureRandom，安全随机数
     * <p>
     * 实际上真正的真随机数只能通过量子力学原理来获取，而我们想要的是一个不可预测的安全的随机数。
     */
    public static void secureRandom() {
        SecureRandom srm = new SecureRandom();
        System.out.println(srm.nextInt(100));

        /*
        SecureRandom无法指定种子，它使用RNG（random number generator）算法。
        JDK的SecureRandom实际上有多种不同的底层实现，有的使用安全随机种子加上伪随机数算法来产生安全的随机数，
        有的使用真正的随机数生成器。实际使用的时候，可以优先获取高强度的安全随机数生成器，如果没有提供，
        再使用普通等级的安全随机数生成器。

        SecureRandom的安全性是通过操作系统提供的安全的随机种子来生成随机数。
        这个种子是通过CPU的热噪声、读写磁盘的字节、网络流量等各种随机事件产生的“熵”。

        ⚠️在密码学中，安全的随机数非常重要。如果使用不安全的伪随机数，所有加密体系都将被攻破。因此，
        时刻牢记必须使用SecureRandom来产生安全的随机数。
         */
        SecureRandom sr = null;
        try {
            sr = SecureRandom.getInstanceStrong(); // 获取高强度安全随机数生成器
        } catch (NoSuchAlgorithmException e) {
            sr = new SecureRandom(); // 获取普通的安全随机数生成器
        }
        byte[] buffer = new byte[16];
        sr.nextBytes(buffer); // 用安全随机数填充buffer
        System.out.println(Arrays.toString(buffer));
    }
}