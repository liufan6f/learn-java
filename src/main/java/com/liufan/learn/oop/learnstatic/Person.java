package com.liufan.learn.oop.learnstatic;

public class Person {
    public String name;

    /**
     * 用 static 修饰的字段，称为静态字段
     * <p>
     * 实例字段在每个实例中都有自己的一个独立“空间”，但是静态字段只有一个共享“空间”，所有实例都会共享该字段。
     */
    public static int number;
    /*
    对于静态字段，无论修改哪个实例的静态字段，效果都是一样的：所有实例的静态字段都被修改了，原因是静态字段并不属于实例：
            ┌──────────────────┐
    ming ──▶│Person instance   │
            ├──────────────────┤
            │name = "Xiao Ming"│
            │age = 12          │
            │number ───────────┼──┐    ┌─────────────┐
            └──────────────────┘  │    │Person class │
                                  │    ├─────────────┤
                                  ├───▶│number = 99  │
            ┌──────────────────┐  │    └─────────────┘
    hong ──▶│Person instance   │  │
            ├──────────────────┤  │
            │name = "Xiao Hong"│  │
            │age = 15          │  │
            │number ───────────┼──┘
            └──────────────────┘
    虽然实例可以访问静态字段，但是它们指向的其实都是 Person class 的静态字段。所以，所有实例共享一个静态字段。
    因此，不推荐用 实例变量.静态字段 去访问静态字段，因为在 Java 程序中，实例对象并没有静态字段。在代码中，
    实例对象能访问静态字段只是因为编译器可以根据实例类型自动转换为 类名.静态字段 来访问静态对象。
     */

    /**
     * 静态方法
     * <p>
     * 静态方法同样属于 class 而不属于实例，因此，静态方法内部，无法访问 this 变量，也无法访问实例字段，它只能访问静态字段。
     * <p>
     * 静态方法经常用于工具类，如：<code>Arrays.sort()</code>、<code>Math.random()</code>；
     * 静态方法也经常用于辅助方法，如 Java 程序的入口<code>main()</code>
     */
    public static void setNumber(int value) {
        number = value;
    }
}
