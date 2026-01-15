package com.liufan.learn.oop.constructormethod;

/**
 * 在 Java 中，创建对象实例的时候，按照如下顺序进行初始化：
 * <ol>
 *     <li> 先初始化字段，例如<code>boolean male = false;</code>表示字段初始化为 false，
 *          <code>int age;</code>表示字段默认初始化为 0，<code>String name;</code>表示引用类型字段默认初始化为 null；</li>
 *     <li>执行构造方法的代码进行初始化。</li>
 * </ol>
 */
public class Person {

    private String name;            // 默认初始化为 null
    private int age;                // 默认初始化为 0
    private boolean male = false;   // 也可以对字段直接进行初始化

    /**
     * 构造方法
     */
    public Person(String name, int age, boolean male) {
        this.name = name;
        this.age = age;
        this.male = male;
        /*
        由于构造方法是如此特殊，所以构造方法的名称就是类名。构造方法的参数没有限制，在方法内部，也可以编写任意语句。
        但是，和普通方法相比，构造方法没有返回值（也没有 void），调用构造方法，必须用 new 操作符。

        创建一个类，如果没有编写构造方法时，编译器会自动为我们生成一个默认构造方法，它没有参数，也没有执行语句。类似这样：
        class Person {
            public Person() {
            }
        }

        ⚠️但是如果我们自定义了一个构造方法，那么，编译器就不再自动创建默认构造方法。
          如果既要能使用带参数的构造方法，又想保留不带参数的构造方法，那么只能把两个构造方法都定义出来
         */
    }

    public Person(String name) {
        this(name, 18, true); // 一个构造方法可以调用其他构造方法，这样做的目的是便于代码复用
    }

    /**
     * 不带参数的构造方法
     * <p>
     * 没有在构造方法中初始化字段时，引用类型的字段默认是 null；值类型的字段用默认值：int 类型默认值是 0，布尔类型默认值是 false
     */
    public Person() {
        // this("Teenager"); // 一样可以调用其他构造方法，但这里仅为了演示没有初始化字段时的情况
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    /**
     * boolean 字段比较特殊，它的 getter 方法一般命名为 isXyz()
     */
    public boolean isMale() {
        return male;
    }
}
