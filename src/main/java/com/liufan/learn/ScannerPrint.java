package com.liufan.learn;

import java.util.Scanner;

/**
 * 输入和输出
 */
public class ScannerPrint {

    /**
     * 输出
     * <p>
     * Java 的格式化功能提供了多种占位符，可以把各种数据类型“格式化”成指定的字符串：
     * <ul>
     *     <li><code>%d</code>：十进制整数</li>
     *     <li><code>%x</code>：十六进制整数</li>
     *     <li><code>%f</code>：十进制浮点数</li>
     *     <li><code>%e</code>：科学计数法</li>
     *     <li><code>%s</code>：字符串</li>
     *     <li><code>%c</code>：字符</li>
     *     <li><code>%b</code>：布尔值</li>
     * </ul>
     * 详细的格式化参数请参考 JDK 文档
     * <a href="https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Formatter.html#syntax">
     *     java.util.Formatter
     * </a>
     */
    public static void print() {
        // 输出不换行
        System.out.print("A,");
        System.out.print("B.");

        // 输出换行
        System.out.println("END"); // print line 的缩写，表示输出并换行

        // 格式化输出
        double d = 12900000;
        System.out.println(d);          // 1.29E7
        System.out.printf("%.2f\n", d); // 12900000.00，格式化输出

        int n = 12345000;
        System.out.printf("n=%d, hex=%08x", n, n); // 注意，两个 % 占位符必须传入两个数
    }

    /**
     * 输入
     */
    public static void scanner() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input your name: ");
        String name = scanner.nextLine(); // 读取一行输入并获取字符串
        System.out.print("Input your age: ");
        int age = scanner.nextInt();      // Scanner 会自动转换数据类型，因此不必手动转换。
        System.out.printf("Hi, %s, you are %d\n", name, age);
    }
}
