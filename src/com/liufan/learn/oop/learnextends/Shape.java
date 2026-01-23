package com.liufan.learn.oop.learnextends;

/**
 * 限定继承（sealed permits）
 * @since java 15 开始，允许使用 sealed 修饰 class，并通过 permits 明确写出能够从该 class 继承的子类名称。
 * 这种 sealed 类主要用于一些框架，防止继承被滥用。
 */
public sealed class Shape permits Rect, Circle {
}