package com.liufan.learn.basicdata;

/**
 * 字符类型
 * <p>
 * 字符类型 <code>char</code> 表示一个字符。Java 的 <code>char</code> 类型除了可表示标准的 ASCII 外，
 * 还可以表示一个 Unicode 字符。
 * <p>
 * 在 Java 中，字符和字符串是两个不同的类型：
 * <ul>
 *     <li>字符类型 <code>char</code> 是基本类型，一个 <code>char</code> 保存一个 Unicode 字符</li>
 *     <li>字符串 <code>String</code> 是引用类型</li>
 * </ul>
 * @see com.liufan.learn.classtype.LearnString
 */
public class LearnChar extends BasicData {

    public static void practice() {
        char a = 'A'; // 注意 char 类型使用单引号，且仅有一个字符，要和双引号 "" 的字符串类型区分开
        char zh = '中';
        System.out.println(a);
        System.out.println(zh);

        // 因为 Java 在内存中总是使用 Unicode 表示字符，所以一个英文字符和一个中文字符都用一个 char 类型表示，
        // 它们都占用两个字节。要显示一个字符的 Unicode 编码，只需将 char 类型直接赋值给 int 类型即可。
        int aUnicode = a;   // 65
        int zhUnicode = zh; // 20013
        System.out.println(aUnicode);
        System.out.println(zhUnicode);

        // 还可以直接用转义字符 \\u+Unicode编码（16进制）来表示一个字符:
        char a1 = '\u0041'; // 'A'，因为十六进制0041 = 十进制65
        char zh1 = '\u4E2D';// '中'
        System.out.println(a1 + " = " + a);
        System.out.println(zh1 + " = " + zh);
    }
}
