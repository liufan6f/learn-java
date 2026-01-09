package com.liufan.learn.basicdata;

/**
 * 字符类型
 * <p>
 * 字符类型 char 表示一个字符。Java 的 char 类型除了可表示标准的 ASCII 外，还可以表示一个 Unicode 字符。
 * <p>
 * 在Java中，字符和字符串是两个不同的类型。
 * @see com.liufan.learn.classtype.LearnString
 */
public class LearnChar extends BasicData {

    public static void practice() {
        char a = 'A'; // 注意 char 类型使用单引号 '，且仅有一个字符，要和双引号 " 的字符串类型区分开
        char zh = '中';
        System.out.println(a);
        System.out.println(zh);
    }
}
