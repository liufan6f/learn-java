package com.liufan.learn.oop.learnextends;

/**
 * 阻止继承（final）、限定继承（sealed permits）
 * <p>
 * 只要 class 没有用 final 修饰符，那么他就是可继承的
 * @since java 15 开始，允许使用 sealed 修饰 class，并通过 permits 明确写出能够从该 class 继承的子类名称。
 * 这种 sealed 类主要用于一些框架，防止继承被滥用。
 */
public sealed class Shape permits Rect, Circle {
}

final class Rect extends Shape {
}

final class Circle extends Shape {
}