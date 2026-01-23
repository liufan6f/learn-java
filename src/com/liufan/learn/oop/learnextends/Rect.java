package com.liufan.learn.oop.learnextends;

/**
 * final 修饰 class 可以阻止被继承
 * <p>
 * 如果一个类不希望任何其他类继承自它，那么可以把这个类本身标记为 final。
 * @see com.liufan.learn.LearnVar#finalPractice() final 修饰的局部变量（常量）可以阻止被重新赋值
 * @see com.liufan.learn.oop.learnpackage.scope.Hello#hi(String) final 修饰的局部变量（常量）可以阻止被重新赋值
 * @see com.liufan.learn.oop.polymorphic.Student#run(String) final 修饰 method 可以阻止被子类覆写
 * @see com.liufan.learn.oop.polymorphic.Student#position final 修饰 field 可以阻止被重新赋值
 */
public final class Rect extends Shape {
}