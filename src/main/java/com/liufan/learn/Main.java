package com.liufan.learn;

/**
 * <ol>
 *     <li>一个 Java 源文件可以包含多个类的定义，但只能定义一个 public 类，且 public 类名必须与文件名一致；</li>
 *     <li>没有明确写 extends 的类，编译器会自动加上 extends Object。</li>
 * </ol>
 */
public class Main {

	/**
     * Java 规定，某个类定义的 public static void main(String[] args) 是 Java 程序的固定入口方法，Java 程序总是从 main 方法开始执行。
     */
	public static void main(String[] args) {
        System.out.println("Hello, world! 你好，世界！");
        // LearnVar.practice(); // 1、变量
        LearnBasicDataType.practice(); // 2、基本数据类型
	}
}