package com.liufan.learn.oop.learnextends;

/**
 * 限定继承（sealed permits）
 * @since java 15 开始，允许使用 sealed 修饰 class，并通过 permits 明确写出能够从该 class 继承的子类名称。
 * 这种 sealed 类主要用于一些框架，防止继承被滥用。
 */
public sealed class Shape permits Rect, Circle {
}

/**
 * 阻止继承（final）
 * <p>
 * 如果一个类不希望任何其他类继承自它，那么可以把这个类本身标记为 final。
 * @see com.liufan.learn.LearnVar#finalPractice() final 修饰符常量应用
 * @see com.liufan.learn.oop.polymorphic.Student#run(String) final 修饰符阻止方法覆写（Override）
 * @see com.liufan.learn.oop.polymorphic.Student#position final 修饰符阻止字段修改
 */
final class Rect extends Shape {
}

final class Circle extends Shape {
}