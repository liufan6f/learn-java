package com.liufan.learn.oop.learnpackage;

// import com.liufan.learn.oop.learnstatic.*; // 一般不推荐这种写法，因为在导入了多个包后，很难看出 Task 类属于哪个包。
import com.liufan.learn.oop.learnstatic.Task;
import static java.lang.System.*;             // 导入 System 类的所有静态字段和静态方法，很少使用

public final class LearnPackage {

    public static void practice() {
        // LearnPackage 与 Person 处于同一个包内，可以调用包作用域的方法和字段
        Person p = new Person();
        p.name = "Xiao Ming";
        p.hello();
    }

    public static void importPractice() {
        /*
        在一个 class 中，我们总会引用其他的 class，有三种写法：

        第 1 种，直接写出完整类名。很显然，每次写完整类名比较痛苦
        var t = new com.liufan.learn.oop.learnstatic.Task();

        第 2 种，用 import 语句，导入 com.liufan.learn.oop.learnstatic.Task，然后写简单类名
         */
        Task t = new Task();
        t.setPriority(2);
        System.out.println("Task Priority set to " + t.getPriority());
        /*
        在写 import 的时候，可以使用 *，表示把这个包下面的所有 class 都导入进来（但不包括子包的 class）。
        我们一般不推荐这种写法，因为在导入了多个包后，很难看出 Task 类属于哪个包。

        第 3 种，import static 的语法，它可以导入一个类的静态字段和静态方法，这种很少使用
         */
        t.setPriority(2);
        out.println("Task Priority set to " + t.getPriority()); // 相当于调用 System.out.println()
    }
}

/*
Java 编译器最终编译出的 .class 文件只使用完整类名，因此，在代码中，当编译器遇到一个 class 名称时：
1、如果是完整类名，就直接根据完整类名查找这个 class；
2、如果是简单类名，按下面的顺序依次查找：
2.1、查找当前 package 是否存在这个 class；
2.2、查找 import 的包是否包含这个 class；
2.3、查找 java.lang 包是否包含这个 class。

编写 class 的时候，编译器会自动帮我们做两个 import 动作：
1、默认自动 import 当前 package 的其他 class；
2、默认自动 import java.lang.*。

⚠️注意：如果有两个 class 名称相同，例如，mr.jun.Arrays 和 java.util.Arrays，那么只能 import 其中一个，另一个必须写完整类名。
最佳实践：
1、为了避免名字冲突，我们需要确定唯一的包名。推荐的做法是使用倒置的域名来确保唯一性。例如：com.liufan.learn。子包就可以根据功能自行命名；
2、注意不要和 java.lang 包的类重名，即自己的类不要使用这些名字：String、System、Runtime 等；
3、注意也不要和 JDK 常用类重名：java.util.List、java.text.Format、java.math.BigInteger 等。
 */