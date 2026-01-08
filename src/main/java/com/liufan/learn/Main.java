package com.liufan.learn;

/**
 * Java 规定
 * <p>
 * <ol>
 *     <li>一个 Java 源文件可以包含多个类的定义，但只能定义一个 public 类，且 public 类名必须与文件名一致；</li>
 *     <li>没有明确写 extends 的类，编译器会自动加上 extends Object。</li>
 * </ol>
 */
public class Main {

	/**
     * Java 规定
     * <p>
     * 某个类定义的 public static void main(String[] args) 是 Java 程序的固定入口方法，总是从 main 方法开始执行。
     */
	public static void main(String[] args) {
        System.out.println("Hello, world! 你好，世界！");

//        Variable.practice();      // 01、变量
//        Variable.finalPractice(); // 01.1、常量
//        Variable.varPractice();   // 01.2、var 关键字

//        BasicData.learn();                                               // 02、基本数据类型
//        BasicDataInteger.practice();                                     // 02.1、整型
//        BasicDataInteger.Arithmetic.fourFundamentalRules();              // 02.1.1、四则运算
//        BasicDataInteger.Arithmetic.numericOverflow();                   // 02.1.2、数值溢出
//        BasicDataInteger.Arithmetic.shiftOperation();                    // 02.1.3、移位运算
//        BasicDataInteger.Arithmetic.bitOperation();                      // 02.1.4、位运算
//        BasicDataInteger.Arithmetic.operationalPriority();               // 02.1.5、运算优先级
//        BasicDataInteger.Arithmetic.typePromotionAndCoercion();          // 02.1.6、类型提升与强制转型

//        BasicDataFloat.practice();                                       // 02.2、浮点型
//        BasicDataFloat.Arithmetic.fourFundamentalRules();                // 02.2.1、四则运算
//        BasicDataFloat.Arithmetic.floatEqualsExample();                  // 02.2.2、浮点数比较
//        BasicDataFloat.Arithmetic.typePromotion();                       // 02.2.3、类型提升
//        BasicDataFloat.Arithmetic.numericOverflow();                     // 02.2.4、数值溢出
//        BasicDataFloat.Arithmetic.typePromotionCoercion();               // 02.2.5、强制转型

//        BasicDataBoolean.practice();                                     // 02.3、布尔型
//        BasicDataBoolean.Arithmetic.practice();                          // 02.3.1、短路运算
//        BasicDataBoolean.Arithmetic.ternaryOperation();                  // 02.3.2、三元运算

        BasicDataChar.practice();                                        // 02.4、字符类型
	}
}