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

//        BasicData.learn();                                  // 02、基本数据类型
//        LearnInteger.practice();                            // 02.1、整型
//        LearnInteger.Arithmetic.fourFundamentalRules();     // 02.1.1、四则运算
//        LearnInteger.Arithmetic.numericOverflow();          // 02.1.2、数值溢出
//        LearnInteger.Arithmetic.shiftOperation();           // 02.1.3、移位运算
//        LearnInteger.Arithmetic.bitOperation();             // 02.1.4、位运算
//        LearnInteger.Arithmetic.operationalPriority();      // 02.1.5、运算优先级
//        LearnInteger.Arithmetic.typePromotionAndCoercion(); // 02.1.6、类型提升与强制转型
//        LearnFloat.practice();                              // 02.2、浮点型
//        LearnFloat.Arithmetic.fourFundamentalRules();       // 02.2.1、四则运算
//        LearnFloat.Arithmetic.floatEqualsExample();         // 02.2.2、浮点数比较
//        LearnFloat.Arithmetic.typePromotion();              // 02.2.3、类型提升
//        LearnFloat.Arithmetic.numericOverflow();            // 02.2.4、数值溢出
//        LearnFloat.Arithmetic.typePromotionCoercion();      // 02.2.5、强制转型
//        LearnBoolean.practice();                            // 02.3、布尔型
//        LearnBoolean.Arithmetic.practice();                 // 02.3.1、短路运算
//        LearnBoolean.Arithmetic.ternaryOperation();         // 02.3.2、三元运算
//        LearnChar.practice();                               // 02.4、字符类型

//        LearnClass.pracice();            // 03、引用类型
//        LearnString.practice();          // 03.1、字符串
//        LearnString.stringPlus();        // 03.1.1、字符串连接
//        LearnString.multilineString();   // 03.1.2、多行字符串
//        LearnString.immutableProperty(); // 03.1.3、字符串不可变（引用类型变量的指向）

//        LearnArray.practice();             // 03.2、数组
//        LearnArray.immutableProperty();    // 03.2.1、数组大小不可变（数组变量的指向）
//        LearnArray.stringArray();          // 03.2.2、字符串数组（引用类型数组）

        ScannerPrint.print(); // 输入和输出
	}
}