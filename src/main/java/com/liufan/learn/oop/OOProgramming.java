package com.liufan.learn.oop;

import com.liufan.learn.oop.method.VarParam;
import com.liufan.learn.oop.polymorphic.Income;
import com.liufan.learn.oop.polymorphic.Salary;
import com.liufan.learn.oop.polymorphic.StateCouncilSpecialAllowance;

import java.util.Arrays;

/**
 * 面向对象编程
 * <p>
 * Java 是一种面向对象的编程语言。面向对象编程，英文是 Object-Oriented Programming，简称 OOP。
 */
public class OOProgramming {

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
        VarParam p = new VarParam();
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
        // 基本数据类型参数绑定
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
        VarParam p = new VarParam();
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

    /**
     * 方法重载（Overload）
     * <p>
     * 方法重载是指多个方法的方法名相同，但各自的参数不同。目的是，功能类似的方法使用同一名字，更便于记忆。
     * <p>
     * ⚠️注意：方法重载的返回值类型通常都是相同的。方法名相同，方法参数相同，但方法返回值不同，也是不同的方法。在 Java 程序中，出现这种情况，编译器会报错。
     */
    public static void methodOverload() {
        /*
        举个例子，String 类提供了多个重载方法 indexOf()，可以查找子串：
        1、int indexOf(int ch)：根据字符的 Unicode 码查找；
        2、int indexOf(String str)：根据字符串查找；
        3、int indexOf(int ch, int fromIndex)：根据字符查找，但指定起始位置；、
        4、int indexOf(String str, int fromIndex)：根据字符串查找，但指定起始位置。
         */
        System.out.println("方法重载是指多个方法的方法名相同，但各自的参数不同。目的是，功能类似的方法使用同一名字，更便于记忆。");
    }

    /**
     * 继承（extends）、super 关键字
     * <p>
     * 继承是面向对象编程的一种强大的代码复用方式，Java 只允许单继承，所有类最终的根类是 Object
     * @see com.liufan.learn.oop.learnextends.Student
     */
    public static void extendsPractice() {
        var s = new com.liufan.learn.oop.learnextends.Student("Xiao Ming", 18, 100);
        System.out.println(s.hello());
    }

    /**
     * 阻止继承（final）、限定继承（sealed permits）
     * @see com.liufan.learn.oop.learnextends.Shape
     */
    public static void preventExtends() {
        System.out.println("只要 class 没有用 final 修饰符，那么他就是可继承的");
    }

    /**
     * 向上转型
     * <p>
     * 向上转型（upcasting）实际上是把一个子类型安全地变为更加抽象的父类型
     */
    public static void upcasting() {
        /*
        这是因为 Student 继承自 Person，它拥有 Person 的全部功能，继承树： Student > Person > Object

        var s = new com.liufan.learn.oop.learnextends.Student("Xiao Ming", 18, 100);
        com.liufan.learn.oop.learnextends.Person p = s;
        Object o1 = p;
        Object o2 = s;
         */
        System.out.println("向上转型实际上是把一个子类型安全地变为更加抽象的父类型");
    }

    /**
     * 向下转型：把一个父类类型强制转型为子类类型，就是向下转型（downcasting）。
     * <p>
     * instanceof 操作符：instanceof 实际上判断一个变量所指向的实例是否是指定类型，或者这个类型的子类。如果一个引用变量为 null，那么对任何 instanceof 的判断都为 false。
     * @since 从 Java 14 开始，判断 instanceof 后，可以直接转型为指定变量，避免再次强制转型。
     */
    public static void downcasting() {
        com.liufan.learn.oop.learnextends.Person p1 = new com.liufan.learn.oop.learnextends.Student("Xiao Ming", 18, 100);
        // p1 实际指向 Student 实例，向下转型为 Student 会成功
        var s1 = (com.liufan.learn.oop.learnextends.Student) p1;
        System.out.println(s1.hello());

        // p2 实际指向 Person 实例，向下转型为 Student 会失败
        var p2 = new com.liufan.learn.oop.learnextends.Person("Xiao Bai", 15);
        // 不能把父类变为子类，因为子类功能比父类多，多的功能无法凭空变出来。
//        var s2 = (com.liufan.learn.oop.learnextends.Student) p2; // runtime error ClassCastException

        // 为了避免向下转型出错，Java 提供了 instanceof 操作符，可以先判断一个实例究竟是不是某种类型。
        System.out.println(p1 instanceof com.liufan.learn.oop.learnextends.Person);  // true
        System.out.println(p1 instanceof com.liufan.learn.oop.learnextends.Student); // true
        System.out.println(p2 instanceof com.liufan.learn.oop.learnextends.Student); // false
        String s = null;
        System.out.println(s instanceof String); // false
        System.out.println(s instanceof Object); // false
        // 利用 instanceof，在向下转型前可以先判断
        if (p1 instanceof com.liufan.learn.oop.learnextends.Student) {
            var s3 = (com.liufan.learn.oop.learnextends.Student) p1;
            System.out.println(s3.hello());
        }

        // 从 Java 14 开始，判断 instanceof 后，可以直接转型为指定变量，避免再次强制转型。
        if (p1 instanceof com.liufan.learn.oop.learnextends.Student s3) {
            System.out.println(s3.hello());
        }
    }

    /**
     * 区分继承和组合
     * <p>
     * 子类和父类的继承是 is 关系，组合是 has 关系。
     */
    public static void composition() {
        /*
        在使用继承时，我们要注意逻辑一致性。假设有一个 Book 类：
        class Book {
            protected String name;
            public String getName() {...}
            public void setName(String name) {...}
        }

        这个 Book 类也有 name 字段，那么，我们能不能让 Student 继承自 Book 呢？
        class Student extends Book {
            protected int score;
        }

        显然，从逻辑上讲，这是不合理的，Student 不应该从 Book 继承，而应该从 Person 继承。
        究其原因，是因为 Student 是 Person 的一种，它们是 is 关系，而 Student 并不是 Book。实际上 Student 和 Book 的关系是 has 关系。

        具有 has 关系不应该使用继承，而是使用组合，即 Student 可以持有一个 Book 实例：
        class Student extends Person {
            protected Book book;
            protected int score;
        }
         */
        System.out.println("继承是 is 关系，组合是 has 关系");
    }

    /**
     * 多态、方法覆写（Override）
     * @see com.liufan.learn.oop.polymorphic.Student
     * @see #downcasting()
     */
    public static void polymorphic() {
        /*
        我们已经知道，引用变量的声明类型可能与其实际类型不符，详情查看 downcasting() 方法。

        现在，我们考虑一种情况，如果子类覆写了父类的方法，那么，一个实际类型为 Student，引用类型为 Person 的变量，
        调用其 run() 方法，调用的是 Person 还是 Student 的 run() 方法？
         */
        com.liufan.learn.oop.polymorphic.Person p = new com.liufan.learn.oop.polymorphic.Student("Xiao Ming", "班长");
        p.run();                       // 应该打印 Person.run 还是 Student.run?
        System.out.println(p.hello()); // super 调用父类方法

        /*
        运行一下上面的代码就可以知道，实际上调用的方法是 Student 的 run() 方法。因此可得出结论：
        Java 的实例方法调用是基于运行时的实际类型的动态调用，而非变量的声明类型。
        这个非常重要的特性在面向对象编程中称之为多态。它的英文拼写非常复杂：Polymorphic。
        所以，多态的特性就是，运行期才能动态决定调用的子类方法。对某个类型调用某个方法，执行的实际方法可能是某个子类的覆写方法。
        这种不确定性的方法调用，究竟有什么作用？

        多态应用：
        现在，我们要编写一个报税的财务软件，对于一个人的所有收入进行报税：
        */
        Income[] incomes = new Income[] {
                new Income(3000),
                new Salary(7500),
                new StateCouncilSpecialAllowance(15000)
        };
        System.out.println(totalTax(incomes));
    }

    /**
     * 利用多态，本方法只需要和 Income 打交道，它完全不需要知道 Salary 和 StateCouncilSpecialAllowance 的存在，
     * 就可以正确计算出总的税。
     * <p>
     * 如果我们要新增一种稿费收入，只需要从 Income 派生，然后正确覆写 getTax() 方法就可以。把新的类型传入本方法，不需要修改任何代码。
     * <p>
     * 可见，多态具有一个非常强大的功能，就是允许添加更多类型的子类实现功能扩展，却不需要修改基于父类的代码。
     */
    private static double totalTax(Income... incomes) {
        double total = 0;
        for (var income : incomes) {
            total = total + income.getTax();
        }
        return total;
    }

    /**
     * 覆写 Object 方法
     * @see com.liufan.learn.oop.polymorphic.Person
     */
    public static void overrideObjectMethod() {
        System.out.println("所有 class 最终都继承自 Object，而 Object 定义了几个重要的方法。");
    }

    /**
     * final 修饰符在类、方法和字段中的应用
     */
    public static void finalPractice() {
        var s = new com.liufan.learn.oop.polymorphic.Student("Xiao Ming", "班长");
        s.run("奥力给！");
    }
}
