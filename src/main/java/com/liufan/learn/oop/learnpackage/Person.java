package com.liufan.learn.oop.learnpackage;

/*
在现实中，如果小明写了一个 Person 类，小红也写了一个 Person 类，现在，小白既想用小明的 Person，也想用小红的 Person，怎么办？

在 Java 中，我们使用 package 来解决名字冲突：
Java 定义了一种名字空间，称之为包：package。一个类总是属于某个包，类名（比如 Person）只是一个简写，真正的完整类名是 包名.类名。
例如本例的 com.liufan.learn.oop.learnpackage.Person。

⚠️注意：包没有父子关系。java.util 和 java.util.zip 是不同的包，两者没有任何继承关系。
没有定义包名的 class，它使用的是默认包，非常容易引起名字冲突，因此，不推荐不写包名的做法。
 */

/**
 * 包（package）作用域
 * <p>
 * 位于同一个包的类，可以访问包作用域的字段和方法。不用 public、protected、private 修饰的字段和方法就是包作用域。
 */
public class Person {
    // 包作用域的字段
    String name;

    // 包作用域的方法
    void hello() {
        System.out.println(name + "说：Hello!");
    }
}
