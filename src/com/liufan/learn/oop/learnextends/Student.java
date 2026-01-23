package com.liufan.learn.oop.learnextends;

/**
 * 继承
 * <p>
 * 子类可以获得父类的所有字段和方法（构造方法除外），但是不能访问父类的 private 字段和方法。
 * 如果不想让外界访问，又希望子类可以访问，用 protected 修饰。
 * <p>
 * ⚠️注意：
 * 1、严禁定义与父类重名的字段；
 * 2、没有明确写 extends 的类，编译器会自动加上 extends Object。
 */
public class Student extends Person {
    protected int score;

    /**
     * 子类构造方法
     * <p>
     * 在 Java 中，任何 class 的构造方法，第一行语句必须是调用父类的构造方法。如果没有明确地调用父类的构造方法，
     * 编译器会帮我们自动加一句 <code>super();</code>。这也就意味着：
     * <p>
     * ⚠️子类不会继承父类的构造方法，子类默认的构造方法是编译器自动生成的，不是继承的。
     */
    public Student(String name, int age, int score) {
        super(name, age); // 这里就必须使用 super，不能使用 this
        this.score = score;
    }

    /**
     * super 关键字表示父类，子类引用父类的字段时，可以用 super.fieldName
     * <p>
     * 实际上，这里使用 super.name，或者 this.name，或者 name，效果都是一样的。编译器会自动定位到父类的 name 字段。
     * @see #Student(String, int, int) 必须使用 super 的情况
     * @see com.liufan.learn.oop.polymorphic.Student#hello() super 调用父类方法
     */
    public String hello() {
        return "Hello, " + super.name;
    }
}
