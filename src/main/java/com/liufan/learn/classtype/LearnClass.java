package com.liufan.learn.classtype;

/**
 * 引用类型
 * <p>
 * 除了基本数据类型的变量，剩下的都是引用类型。
 * <p>
 * 引用类型的变量可以指向一个空值 <code>null</code>，它表示不存在，即该变量不指向任何对象。
 * @see LearnString
 * @see LearnArray
 */
public class LearnClass {
    public static void pracice() {
        String sn = null;         // 空值 null，它表示不存在，即该变量不指向任何对象。
//        if (sn.equals("hello")) { // 当变量为 null 时，执行方法会报错 NullPointerException
//            System.out.println("hello");
//        }

        // 要避免 NullPointerException 错误，可以利用短路运算符 &&
//        if (sn != null && sn.equals("hello")) {
//            System.out.println("hello");
//        } else {
//            System.out.println("sn is null");
//        }
        System.out.println("在 Java 中除了基本数据类型的变量，剩下的都是引用类型。");
    }

    /**
     * 判断引用类型相等
     * <p>
     * 在 Java 中，判断值类型的变量是否相等，可以使用<code>==</code>运算符。但是，判断引用类型的变量是否相等，
     * <code>==</code>表示“引用是否相等”，或者说，是否指向同一个对象。
     * 要判断引用类型的变量内容是否相等，必须使用<code>equals()</code>方法
     */
    public static void equals() {
        // 例如，下面的两个 String 类型，它们的内容是相同的，但是，分别指向不同的对象，用 == 判断，结果为 false：
        String s1 = "hello";
        String s2 = "HELLO".toLowerCase();
        System.out.println(s1);
        System.out.println(s2);
        if (s1.equals(s2)) { // 判断 if (s1 == s2) 结果为 false
            System.out.println("s1 == s2");
        } else {
            System.out.println("s1 != s2");
        }
    }
}
