package com.liufan.learn.oop.polymorphic;

/**
 * 多态
 * <p>
 * 多态是指，针对某个类型的方法调用，其真正执行的方法取决于运行时期实际类型的方法。
 */
public class Student extends Person {

    /**
     * final 修饰 field 可以阻止被重新赋值
     * @see com.liufan.learn.LearnVar#finalPractice() final 修饰的局部变量（常量）可以阻止被重新赋值
     * @see com.liufan.learn.oop.learnpackage.scope.Hello#hi(String) final 修饰的局部变量（常量）可以阻止被重新赋值
     * @see com.liufan.learn.oop.learnextends.Rect final 修饰 class 可以阻止被继承
     * @see #run(String) final 修饰符阻止方法覆写（Override）
     */
    public final String position; // = "班长";

    public Student(String name, String position) {
        super(name);
        this.position = position; // 在构造方法中初始化 final 字段更为常用，可以保证实例一旦创建，其字段就不可修改
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

    /**
     * @see com.liufan.learn.oop.learnextends.Student#hello() super 引用父类字段
     */
    @Override
    public String hello() {
        // super 调用父类 hello() 方法
        return super.hello() + " student";
    }

    /**
     * final 修饰符阻止方法覆写（Override），
     * 这是也一个方法重载 Overload，因为参数不同。
     * <p>
     * 如果一个父类不允许子类对它的某个方法进行覆写，可以把该方法标记为 final。用 final 修饰的方法不能被 Override。
     * @see com.liufan.learn.LearnVar#finalPractice() final 修饰的局部变量（常量）可以阻止被重新赋值
     * @see com.liufan.learn.oop.learnpackage.scope.Hello#hi(String) final 修饰的局部变量（常量）可以阻止被重新赋值
     * @see com.liufan.learn.oop.learnextends.Rect final 修饰 class 可以阻止被继承
     * @see Student#position final 修饰 field 可以阻止被重新赋值
     */
    public final void run(String s) {
        System.out.println(name + "是" + position + "，跑步的时候喜欢说：" + s);
    }
}
