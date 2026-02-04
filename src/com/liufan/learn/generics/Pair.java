package com.liufan.learn.generics;

/*
首先，按照某种类型，例如：String，来编写类：
public class Pair {
    private String first;
    private String last;
    public Pair(String first, String last) {
        this.first = first;
        this.last = last;
    }
    public String getFirst() {
        return first;
    }
    public String getLast() {
        return last;
    }
}
然后，标记所有的特定类型，这里是String，把特定类型String替换为T，并申明<T>：
 */

/**
 * 编写泛型类
 */
public class Pair<T> {
    private T first;
    private T last;

    public Pair(T first, T last) {
        this.first = first;
        this.last = last;
    }
    public Pair(Class<T> clazz) {
        try {
            first = clazz.newInstance();
            last = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public T getFirst() {
        return first;
    }
    public T getLast() {
        return last;
    }

//    public static Pair<T> create(T first, T last) {
//        return new Pair<T>(first, last);
//    }
//
//    public static <T> Pair<T> create(T first, T last) {
//        return new Pair<T>(first, last);
//    }
    /**
     * 静态方法
     * <p>
     * ⚠️静态方法不能引用泛型类型<code>&lt;T&gt;</code>，必须定义其他类型（例如<code>&lt;K&gt;</code>）来实现静态泛型方法。
     * <pre>
     * public static Pair&lt;T&gt; create(T first, T last) {
     *     return new Pair&lt;T&gt;(first, last);
     * }
     * </pre>
     * 代码编译错误，我们无法在静态方法create()的方法参数和返回类型上使用泛型类型&lt;T&gt;。
     * 有些同学在网上搜索发现，可以在static修饰符后面加一个<code>&lt;T&gt;</code>，编译就能通过：
     * <pre>
     * public static &lt;T&gt; Pair&lt;T&gt; create(T first, T last) {
     *     return new Pair&lt;T&gt;(first, last);
     * }
     * </pre>
     * 但实际上，这个<code>&lt;T&gt;</code>和<code>Pair&lt;T&gt;</code>类型的<code>&lt;T&gt;</code>已经没有任何关系了，
     * 静态泛型方法应该使用其他类型区分：
     */
    public static <K> Pair<K> create(K first, K last) { // 这样才能清楚地将静态方法的泛型类型和实例类型的泛型类型区分开。
        return new Pair<K>(first, last);
    }
}