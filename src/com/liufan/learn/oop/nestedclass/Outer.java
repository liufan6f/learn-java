package com.liufan.learn.oop.nestedclass;

import java.util.HashMap;

/**
 * 被定义在一个类的内部，所以称为嵌套类（nested class），嵌套类有 3 种：
 * <ul>
 *     <li>内部类（Inner Class）</li>
 *     <li>匿名类（Anonymous Class）</li>
 *     <li>静态内部类（Static Nested Class）</li>
 * </ul>
 */
public class Outer {
    private static String NAME = "OUTER";
    private String name;

    public Outer(String name) {
        this.name = name;
    }

    /**
     * 内部类（Inner Class）
     * <p>
     * 如果一个类定义在另一个类的内部，这个类就是 Inner Class。它与普通类有个最大的不同，
     * 就是 Inner Class 的实例不能单独存在，必须依附于一个 Outer Class 的实例。
     * <p>
     * 这是因为 Inner Class 除了有一个 this 指向它自己，还隐含地持有一个 Outer Class 实例，可以用 Outer.this 访问这个实例。
     * 所以，实例化一个 Inner Class 不能脱离 Outer 实例。
     * <p>
     * 观察 Java 编译后的 .class 文件可以发现，Outer 类被编译为 Outer.class，而 Inner 类被编译为 Outer$Inner.class。
     */
    public class Inner {
        public void hello() {
            // 因为 Inner Class 的作用域在 Outer Class 内部，所以能访问 Outer Class 的 private 字段和方法。
            System.out.println("Hello, " + Outer.this.name);
        }
    }

    /**
     * 匿名类（Anonymous Class）
     * <p>
     * 匿名类和 Inner Class 一样，本质上是相同的，都必须依附于 Outer Class 的实例，即隐含地持有 Outer.this 实例，
     * 可以访问 Outer Class 的 private 字段和方法。之所以我们要定义匿名类，是因为在这里我们通常不关心类名，
     * 比直接定义 Inner Class 可以少写很多代码。
     * <p>
     * 观察 Java 编译后的 .class 文件可以发现，Outer 类被编译为 Outer.class，而匿名类被编译为 Outer$1.class。
     * 如果有多个匿名类，Java 会将每个匿名类依次命名为O uter$1、Outer$2、Outer$3...
     */
    public void asyncHello() {
        /*
        Runnable 本身是接口，接口是不能实例化的，所以这里实际上是定义了一个实现了 Runnable 接口的匿名类，
        并且通过 new 实例化该匿名类，然后转型为 Runnable。在定义匿名类的时候就必须实例化它，定义匿名类的写法如下：
         */
        Runnable r = new Runnable() {
            // 实现必要的抽象方法...
            @Override
            public void run() {
                System.out.println("Hello, " + Outer.this.name);
            }
        };
        new Thread(r).start();

        /*
        除了接口外，匿名类也完全可以继承自普通类。观察以下代码：
         */
        HashMap<String, String> map1 = new HashMap<>();    // map1 是一个普通的 HashMap 实例
        HashMap<String, String> map2 = new HashMap<>() {}; // map2 是一个匿名类实例，只是该匿名类继承自 HashMap
        HashMap<String, String> map3 = new HashMap<>() {   // map3 与 map2 一样，并且添加了 static 代码块来初始化数据
            {
                put("A", "1");
                put("B", "2");
            }
        };
        System.out.println(map1.toString());
        System.out.println(map2.toString());
        System.out.println(map3.toString());
    }

    /**
     * 静态内部类（Static Nested Class）
     * 用 static 修饰的内部类和 Inner Class 有很大的不同，它不再依附于 Outer 的实例，而是一个完全独立的类，
     * 因此无法引用 Outer.this，但它可以访问 Outer 的 private 静态字段和静态方法。
     */
    public static class StaticNested {
        public void hello() {
            System.out.println("Hello, " + Outer.NAME);
        }
    }
}
