package com.liufan.learn.coreclass;

import com.liufan.learn.classtype.LearnString;

/**
 * Java 核心类 —— String
 * <p>
 * Java 字符串的一个重要特点就是<b>字符串不可变</b>。这种不可变性是通过内部的<code>private final char[]</code>数组字段，
 * 以及没有任何修改这个字段的方法实现的。按下面的写法创建字符串也是可以的：
 * <pre><code>String s = new String(new char[] {'H', 'e', 'l', 'l', 'o', '!'});</code></pre>
 * @see LearnString
 */
public class CoreString extends LearnString {

    /**
     * 字符串比较
     * <ul>
     *     <li>字符串比较必须使用<code>equals()</code></li>
     *     <li>忽略大小写比较，使用<code>equalsIgnoreCase()</code>方法</li>
     * </ul>
     */
    public static void compare(String s) {
        String s2 = "hello";
        System.out.println(s.equals(s2));  // true
        // Java 编译器在编译期，会自动把所有相同的字符串当作一个对象放入常量池，自然 s1 和 s2 的引用就是相同的。
        System.out.println(s == s2);       // 这种 == 比较返回 true 纯属巧合

        String s3 = "HELLO".toLowerCase();
        System.out.println(s2.equals(s3)); // true
        System.out.println(s2 == s3);      // false，换一种写法 == 比较就会失败
    }

    /**
     * 搜索子串、包含子串、提取子串
     */
    public static void search(final String s) {
        System.out.println(s.contains("ll"));         // true
        System.out.println(s.startsWith("he"));       // true
        System.out.println(s.endsWith("lo"));         // true
        System.out.println(s.indexOf("l"));           // 2
        System.out.println(s.lastIndexOf("l"));   // 3
        System.out.println(s.substring(2)); // llo
        System.out.println(s.substring(2, 4));        // ll
    }
}
