package com.liufan.learn.oop.polymorphic;

/**
 * 多态
 * <p>
 * 多态是指，针对某个类型的方法调用，其真正执行的方法取决于运行时期实际类型的方法。
 *
 *
 */
public class Student extends Person {

    public Student(String name) {
        super(name);
    }

    /**
     * 方法覆写（Override）
     * <p>
     * 在继承关系中，子类如果定义了一个与父类方法签名完全相同的方法，被称为覆写。
     * <p>
     * Override 和 Overload 不同的是，
     * Overload 方法是一个新方法；而如果方法签名相同，返回值也相同，就是 Override。
     * <p>
     * ⚠️加上 <code>@Override</code> 可以让编译器帮助检查是否进行了正确的覆写，但是 <code>@Override</code> 不是必需的。
     * @see com.liufan.learn.oop.OOProgramming#methodOverload()
     */
    @Override
    public void run() {
        System.out.println("Student.run");
    }

    @Override
    public String hello() {
        // 调用父类 hello() 方法
        return super.hello() + " student";
    }

    /**
     * 这是一个方法重载 Overload，因为参数不同
     */
    void run(String s) {
    }
}
