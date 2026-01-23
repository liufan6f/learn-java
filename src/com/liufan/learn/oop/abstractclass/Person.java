package com.liufan.learn.oop.abstractclass;

/*
由于多态的存在，每个子类都可以覆写父类的方法，例如：
class Person {
    public void run() { … }
}

class Student extends Person {
    @Override
    public void run() { … }
}

class Teacher extends Person {
    @Override
    public void run() { … }
}

从 Person 类派生的 Student 和 Teacher 都可以覆写 run() 方法，但是父类 Person 的 run() 方法此时没有实际意义，
却又不能去掉方法的执行语句？那如果直接去掉父类 Person 的 run() 方法，又失去了多态的特性。

如果父类的方法本身不需要实现任何功能，仅仅是为了定义方法签名，目的是让子类去覆写它，那么，可以把父类的方法声明为抽象方法：
 */

/**
 * 抽象类
 * <p>
 * 无法实例化的抽象类有什么用？因为抽象类本身被设计成只能用于被继承，因此，抽象类可以强迫子类实现其定义的抽象方法，
 * 否则编译会报错。因此，抽象方法实际上相当于定义了“规范”。
 * @see com.liufan.learn.oop.learninterface.Person
 */
public abstract class Person {
    /**
     * 把一个方法声明为 abstract，表示它是一个抽象方法，本身没有实现任何方法语句。
     * 因为这个抽象方法本身是无法执行的，所以 Person 类也无法被实例化。必须把 Person 类本身也声明为 abstract
     */
    public abstract void run();
}
