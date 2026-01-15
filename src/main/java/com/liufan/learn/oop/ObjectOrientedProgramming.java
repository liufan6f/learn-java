package com.liufan.learn.oop;

import com.liufan.learn.oop.method.VariableParam;

import java.util.Arrays;

/**
 * 面向对象编程
 * <p>
 * Java 是一种面向对象的编程语言。面向对象编程，英文是 Object-Oriented Programming，简称 OOP。
 */
public class ObjectOrientedProgramming {

    public static void learn() {
        /*
        面向对象的基本概念，包括：
        1、类（class），类是一种对象模版，它定义了如何创建实例；
        2、实例（instance），跟据类创建的实例对象；
        3、方法（method），方法用来描述一个类的行为，是一组执行语句，可以执行任意逻辑；
        4、字段（field），字段用来描述一个类的特征（本质上是一个类内部的变量）。

        面向对象的实现方式，包括：
        1、继承
        2、多态

        面向对象编程，是一种通过对象的方式，把现实世界映射到计算机模型的一种编程方法。现实世界中，
        我们定义了“人”这种抽象概念，而具体的人则是“小明”、“小红”、“小军”等一个个具体的人，
        所以，“人”可以定义为一个类（class），而具体的人则是实例（instance）。
        同样的，“书”也是一种抽象的概念，所以它是类，而《Java核心技术》、《Java编程思想》、《Java学习笔记》则是实例。

        所以，class 和 instance 是“模版”和“实例”的关系。
        class 是一种对象模版，它定义了如何创建实例，因此，class 本身就是一种数据类型；而 instance 是对象实例，
        instance 是根据 class 创建的实例，可以创建多个 instance，每个 instance 类型相同，但各自属性可能不相同。
         */
        System.out.println("Java 是一种面向对象的编程语言。面向对象编程，英文是 Object-Oriented Programming，简称 OOP。");
    }

    /**
     * 类（class）、实例（instance）、方法（method）和字段（field）
     */
    public static void createInstance() {
        /*
        创建了一个 Person 类型的实例，并通过变量 ming 指向它

        var ming 是定义 com.liufan.learn.oop.classinstance.Person 类型的变量 ming
        new com.liufan.learn.oop.classinstance.Person() 是创建 Person 实例
         */
        var ming = new com.liufan.learn.oop.classinstance.Person();
        ming.name = "Xiao Ming";
        ming.age = 18;
        // 有了指向这个实例的变量，我们就可以通过这个变量来操作实例。
        System.out.println(ming.name + "，今年 " + ming.age);

        var hong = new com.liufan.learn.oop.classinstance.Person();
        hong.name = "Xiao Hong";
        hong.age = 15;
        System.out.println(hong.name + "，今年 " + hong.age);
        /*
        内存中的表现
                    ┌──────────────────┐
        ming ──────▶│Person instance   │
                    ├──────────────────┤
                    │name = "Xiao Ming"│
                    │age = 12          │
                    └──────────────────┘
                    ┌──────────────────┐
        hong ──────▶│Person instance   │
                    ├──────────────────┤
                    │name = "Xiao Hong"│
                    │age = 15          │
                    └──────────────────┘
         */

        // 但是这样直接把 field 用 public 暴露给外部可能会破坏封装性，比如：
        hong.age = -99; // 年龄设置为负数，显然是不合理的，直接操作 field，容易造成逻辑混乱。

        /*
        那么我们就把字段用 private 修饰，此时就无法直接操作字段。
        但是也无法访问这些字段，那我们定义这些 field 有什么用？怎么才能给它赋值？怎么才能读取它的值？
        所以我们需要使用方法（method）来让外部代码可以间接修改 field。

        var bai = new com.liufan.learn.oop.method.Person();
        bai.name = "Xiao Bai";
        bai.age = 12;
        */
        var bai = new com.liufan.learn.oop.method.Person();
        bai.setName("Xiao Bai");
        bai.setAge(12);
        // bai.setAge(-99); // 若此时设置 -99，则会运行时抛出错误 IllegalArgumentException
        System.out.println(bai.getName() + "，虚岁 " + bai.getAge() + "，出生年份是 " + bai.getBirthYear());
    }

    /**
     * this 变量
     * <p>
     * 在实例方法内部，可以使用一个隐含的变量 this，它始终指向当前实例。因此，通过 this.field 就可以访问当前实例的字段。
     * 如果没有命名冲突，可以省略 this。但是，如果有局部变量和字段重名，那么局部变量优先级更高，就必须加上 this。
     * @see com.liufan.learn.oop.method.Person
     */
    public static void thisLearn() {
        System.out.println("this 变量在 com.liufan.learn.oop.method.Person 可以看到具体应用");
    }

    /**
     * 可变参数
     * <p>
     * 可变参数相当于数组类型参数，但比数组类型要方便的多
     */
    public static void variableParam() {
        VariableParam p = new VariableParam();
        p.setNames(new String[] { "apple", "banana", "orange" });
        System.out.println(Arrays.toString(p.getNames()));
        p.setNames("apple", "banana");
        System.out.println(Arrays.toString(p.getNames()));
        p.setNames("apple");
        System.out.println(Arrays.toString(p.getNames()));
        p.setNames(); // 传入 0 个 String
        System.out.println(Arrays.toString(p.getNames()));
    }

    /**
     * 参数绑定
     * <ul>
     *     <li>基本数据类型参数的传递，是值的复制，双方各自的后续修改，互不影响；</li>
     *     <li>引用类型参数的传递，调用方的变量，和接收方的参数变量，指向的是同一个对象。双方任意一方对这个对象的修改，都会影响对方（因为指向同一个对象）。</li>
     * </ul>
     */
    public static void parmaBinding() {
        // 基本数据类型（值类型）参数绑定
        var bai = new com.liufan.learn.oop.method.Person();
        String xiaoBai = "Xiao Bai";
        bai.setName(xiaoBai);
        int n = 15;
        bai.setAge(n);
        System.out.println(bai.getName() + "，虚岁 " + bai.getAge() + "，出生年份是 " + bai.getBirthYear());
        n = 20;
        // 局部变量 n 的值改为 20，并不影响 Person 的 age 字段，
        // 原因是 setAge() 方法获得的参数，复制了 n 的值，因此，p.age 和局部变量 n 互不影响。
        System.out.println(bai.getName() + "，虚岁 " + bai.getAge() + "，出生年份是 " + bai.getBirthYear());

        // 引用类型参数绑定
        VariableParam p = new VariableParam();
        String[] names = { "apple", "banana", "orange" };
        p.setNames(names);
        System.out.println(Arrays.toString(p.getNames()));
        names[0] = "pear";
        System.out.println(Arrays.toString(p.getNames()));

        // 再来看看字符串为什么也是引用类型，但为什么两次输出都是"Xiao Bai"
        System.out.println(bai.getName());
        // 因为数组时可变的，而字符串不可变
        xiaoBai = "Bai Xiao"; // 这里重新开辟了一个 "Bai Xiao"，然后 xiaoBai 引用了它，但是 bai.name 仍然指向 "Xiao Bai"
        System.out.println(bai.getName());
    }

    /**
     * 构造方法
     * @see com.liufan.learn.oop.constructormethod.Person
     */
    public static void constructorMethod() {
        /*
        创建实例的时候，我们需要初始化这个实例的字段，
        这里需要 3 行代码，而且，如果忘了调用 setName() 或者 setAge()，这个实例内部的状态就是不正确的。

        var bai = new com.liufan.learn.oop.method.Person();
        bai.setName("Xiao Bai");
        bai.setAge(15);

        我么希望创建对象实例时就把内部字段全部初始化，这就需要通过构造方法。
         */
        var bai = new com.liufan.learn.oop.constructormethod.Person("Xiao Bai", 15, true);
        System.out.println(bai.getName() + "，虚岁 " + bai.getAge() + "，男生 " + bai.isMale());

        // 不带参数的构造方法
        var p = new com.liufan.learn.oop.constructormethod.Person();
        System.out.println(p.getName() + "，虚岁 " + p.getAge() + "，男生 " + p.isMale());

        // 存在多个构造方法时，会自动匹配到对应的构造方法
        var p1 = new com.liufan.learn.oop.constructormethod.Person("Teenager");
        System.out.println(p1.getName() + "，少年永远 " + p1.getAge());
    }
}
