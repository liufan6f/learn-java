package com.liufan.learn.coreclass;

import java.util.StringJoiner;

/**
 * StringJoiner，分隔符拼接数组
 * <p>
 * <code>String.join()</code>这个方法在内部使用了<code>StringJoiner</code>来拼接字符串，
 * 在不需要指定“开头”和“结尾”的时候，用<code>String.join()</code>更方便。
 */
public class LearnStringJoiner extends CoreString {
    public static void practice() {
        String[] names = {"Bob", "Alice", "Grace"};
        /*
        var sb = new StringBuilder();
        sb.append("Hello ");
        for (String name : names) {
            sb.append(name).append(", ");
        }
        // 注意去掉最后的", ":
        sb.delete(sb.length() - 2, sb.length());
        sb.append("!");
        System.out.println(sb.toString());
        */

        // 这种用分隔符拼接数组的需求很常见，所以 Java 标准库还提供了一个 StringJoiner 来干这个事：
        var sj = new StringJoiner(", ", "Hello ", "!");
        for (String name : names) {
            sj.add(name);
        }
        System.out.println(sj.toString());
    }
}
