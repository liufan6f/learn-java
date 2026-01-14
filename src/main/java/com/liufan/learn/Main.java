package com.liufan.learn;

import com.liufan.learn.basicdata.BasicData; // import 是导入某个类的语句，必须放到 Java 源代码的开头
import com.liufan.learn.basicdata.LearnInteger;
import com.liufan.learn.basicdata.LearnFloat;
import com.liufan.learn.basicdata.LearnBoolean;
import com.liufan.learn.basicdata.LearnChar;
import com.liufan.learn.classtype.ArrayOperations;
import com.liufan.learn.classtype.LearnClass;
import com.liufan.learn.classtype.LearnString;
import com.liufan.learn.classtype.LearnArray;
import com.liufan.learn.classtype.MultidimensionalArray;
import com.liufan.learn.processcontrol.LearnIf;
import com.liufan.learn.processcontrol.LearnSwitchCase;
import com.liufan.learn.processcontrol.LearnWhile;
import com.liufan.learn.processcontrol.LearnFor;
import com.liufan.learn.processcontrol.Loop;

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

//        LearnVariable.practice();      // 01、变量
//        LearnVariable.finalPractice(); // 01.1、常量
//        LearnVariable.varPractice();   // 01.2、var 关键字

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

//        LearnClass.pracice();                          // 03、引用类型
//        LearnClass.equals();                           // 03.0.1、判断引用类型相等
//        LearnString.practice();                        // 03.1、字符串
//        LearnString.stringPlus();                      // 03.1.1、字符串连接
//        LearnString.multilineString();                 // 03.1.2、多行字符串
//        LearnString.immutableProperty();               // 03.1.3、字符串不可变（引用类型变量的指向）
//        LearnArray.practice();                         // 03.2、数组
//        LearnArray.immutableProperty();                // 03.2.1、数组大小不可变（数组变量的指向）
//        LearnArray.stringArray();                      // 03.2.2、字符串数组（引用类型数组）
//        ArrayOperations.print();                       // 03.2.3、数组打印
//        ArrayOperations.sorts();                       // 03.2.4、数组排序
//        ArrayOperations.bubbleSorts(new int[] { 1 });  // 03.2.4.1、冒泡排序
//        MultidimensionalArray.twoDimensionalArray();   // 03.2.5、多维数组——二维数组
//        MultidimensionalArray.threeDimensionalArray(); // 03.2.5.1、多维数组——三维数组

//        LearnIf.ifPractice(50);                  // 04、流程控制
//        LearnIf.elseIfPractice(100);             // 04.1、if 条件判断
//        LearnSwitchCase.practice(1, "apple");    // 04.2、switch case 条件判断（多重选择）
//        LearnSwitchCase.practiceJava12("apple"); // 04.2.1、switch case 条件判断（模式匹配）
//        LearnSwitchCase.practiceReturn("apple"); // 04.2.2、switch case 返回值
//        Loop.breakPractice();                    // 05.0.1、循环♻️—— break 语句
//        Loop.continuePractice();                 // 05.0.2、循环♻️—— continue 语句
//        LearnWhile.whilePractice();              // 05.1、while 循环
//        LearnWhile.doWhile();                    // 05.2、do while 循环
//        LearnFor.forPractice();                  // 05.3、for 循环
//        LearnFor.simpleFor();                    // 05.3.1、for 循环简易使用
//        LearnFor.forEach();                      // 05.4、for each 循环

        // 暂未编号分类
//        ScannerPrint.print();       // 输出
//        ScannerPrint.scanner();     // 输入
        CommandLine.practice(args); // 命令行参数
	}
}