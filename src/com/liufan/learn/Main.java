package com.liufan.learn;

import com.liufan.learn.basicdata.BasicData;
import com.liufan.learn.basicdata.LearnInteger;
import com.liufan.learn.basicdata.LearnFloat;
import com.liufan.learn.basicdata.LearnBoolean;
import com.liufan.learn.basicdata.LearnChar;
import com.liufan.learn.classtype.ArrayOperations;
import com.liufan.learn.classtype.LearnClass;
import com.liufan.learn.classtype.LearnString;
import com.liufan.learn.classtype.LearnArray;
import com.liufan.learn.classtype.ArrayMultiD;
import com.liufan.learn.coreclass.CoreString;
import com.liufan.learn.coreclass.LearnEnum;
import com.liufan.learn.coreclass.LearnJavaBean;
import com.liufan.learn.coreclass.LearnRecord;
import com.liufan.learn.coreclass.LearnStringBuilder;
import com.liufan.learn.coreclass.LearnStringJoiner;
import com.liufan.learn.coreclass.WrapperClass;
import com.liufan.learn.oop.OOProgramming;
import com.liufan.learn.processcontrol.LearnIf;
import com.liufan.learn.processcontrol.LearnSwitch;
import com.liufan.learn.processcontrol.LearnWhile;
import com.liufan.learn.processcontrol.LearnFor;
import com.liufan.learn.processcontrol.Loop;

/**
 * 一个 Java 源文件可以包含多个类的定义，但只能定义一个 public 类，且 public 类名必须与文件名一致
 */
public class Main {

	/**
     * public static void main(String[] args) 是 Java 程序的固定入口方法，总是从 main 方法开始执行。
     */
	public static void main(String[] args) {
        // System.out.println("Hello, world! 你好，世界！");

        boolean iWantToLearn = true;
        if (iWantToLearn) {
            LearnRecord.practice();
            return;
        }

        // 暂未编号分类
        ScannerPrint.print();                        // 输出
        ScannerPrint.scanner();                      // 输入
        com.liufan.learn.CommandLine.practice(args); // 命令行参数，⚠️在命令行编译时，同一个包内的类也需要全名

        LearnVar.practice();      // 01、变量
        LearnVar.varPractice();   // 01.1、变量 —— var 关键字
        LearnVar.finalPractice(); // 01.2、常量 —— final 修饰符

        BasicData.learn();                                  // 02、基本类型
        LearnInteger.practice();                            // 02.1、整型
        LearnInteger.Arithmetic.fourFundamentalRules();     // 02.1.1、四则运算
        LearnInteger.Arithmetic.numericOverflow();          // 02.1.2、数值溢出
        LearnInteger.Arithmetic.shiftOperation();           // 02.1.3、移位运算
        LearnInteger.Arithmetic.bitOperation();             // 02.1.4、位运算
        LearnInteger.Arithmetic.operationalPriority();      // 02.1.5、运算优先级
        LearnInteger.Arithmetic.typePromotionAndCoercion(); // 02.1.6、类型提升与强制转型
        LearnFloat.practice();                              // 02.2、浮点型
        LearnFloat.Arithmetic.fourFundamentalRules();       // 02.2.1、四则运算
        LearnFloat.Arithmetic.floatEqualsExample();         // 02.2.2、浮点数比较
        LearnFloat.Arithmetic.typePromotion();              // 02.2.3、类型提升
        LearnFloat.Arithmetic.numericOverflow();            // 02.2.4、数值溢出
        LearnFloat.Arithmetic.typePromotionCoercion();      // 02.2.5、强制转型
        LearnBoolean.practice();                            // 02.3、布尔型
        LearnBoolean.Arithmetic.practice();                 // 02.3.1、短路运算
        LearnBoolean.Arithmetic.ternaryOperation();         // 02.3.2、三元运算
        LearnChar.practice();                               // 02.4、字符类型

        LearnClass.pracice();                         // 03、引用类型
        LearnClass.equals();                          // 03.0.1、判断引用类型相等
        LearnString.practice();                       // 03.1、字符串
        LearnString.stringPlus();                     // 03.1.1、字符串连接
        LearnString.multilineString();                // 03.1.2、多行字符串
        LearnString.immutableProperty();              // 03.1.3、字符串不可变（引用类型变量的指向）
        LearnArray.practice();                        // 03.2、数组
        LearnArray.immutableProperty();               // 03.2.1、数组大小不可变（数组变量的指向）
        LearnArray.stringArray();                     // 03.2.2、字符串数组（引用类型数组）
        ArrayOperations.print();                      // 03.2.3、数组打印
        ArrayOperations.sorts();                      // 03.2.4、数组排序
        ArrayOperations.bubbleSorts(new int[] { 1 }); // 03.2.4.1、冒泡排序
        ArrayMultiD.array2D();                        // 03.2.5、多维数组 —— 二维数组
        ArrayMultiD.array3D();                        // 03.2.5.1、多维数组 —— 三维数组

        // 04、流程控制
        LearnIf.ifPractice(50);                 // 04.1.1、条件判断 —— if
        LearnIf.elseIfPractice(100);            // 04.1.2、条件判断 —— if 串联
        LearnSwitch.practice(1, "apple"); // 04.2、条件判断 —— switch（多重选择）
        LearnSwitch.practiceJava12("apple");     // 04.2.1、条件判断 —— switch（模式匹配）
        LearnSwitch.practiceReturn("apple");     // 04.2.2、switch 返回值
        LearnWhile.whilePractice();                   // 04.3.1、循环♻️—— while
        LearnWhile.doWhile();                         // 04.3.2、循环♻️—— do while
        LearnFor.forPractice();                       // 04.4.1、循环♻️—— for
        LearnFor.simpleFor();                         // 04.4.2、循环♻️—— for 简易使用
        LearnFor.forEach();                           // 04.4.3、循环♻️—— for each
        Loop.breakPractice();                         // 04.5.1、循环♻️—— break 语句
        Loop.continuePractice();                      // 04.5.2、循环♻️—— continue 语句

        OOProgramming.learn();                  // 05、面向对象编程
        OOProgramming.createInstance();         // 05.1、类（class）、实例（instance）、方法（method）和字段（field）
        OOProgramming.thisLearn();              // 05.2.1、方法 —— this 关键字
        OOProgramming.variableParam();          // 05.2.2、方法 —— 可变参数
        OOProgramming.parmaBinding();           // 05.2.3、方法 —— 参数绑定
        OOProgramming.methodOverload();         // 05.2.4、方法 —— 方法重载（Overload）
        OOProgramming.constructorMethod();      // 05.3、构造方法
        OOProgramming.extendsPractice();        // 05.4、继承（extends）、super 关键字
        OOProgramming.preventExtends();         // 05.4.1、阻止继承（final）、限定继承（sealed permits）
        OOProgramming.upcasting();              // 05.4.2、向上转型
        OOProgramming.downcasting();            // 05.4.3、向下转型、instanceof 操作符
        OOProgramming.composition();            // 05.4.4、区分继承和组合
        OOProgramming.polymorphic();            // 05.5、多态 —— 方法覆写（Override）
        OOProgramming.overrideObjectMethod();   // 05.5.1、多态 —— 覆写 Object 方法
        OOProgramming.finalPractice();          // 05.5.2、多态 —— final 修饰符在类、方法和字段中的应用
        OOProgramming.abstractClass();          // 05.6、抽象类（abstract）
        OOProgramming.interfacePractice();      // 05.7、接口（interface）
        OOProgramming.abstractClassInterface(); // 05.7.1、抽象类（abstract）与接口（interface）复合使用
        OOProgramming.staticPractice();         // 05.8、静态字段和静态方法
        OOProgramming.staticInterface();        // 05.8.1、接口中的静态字段（接口常量）
        OOProgramming.learnPackage();           // 05.9、包（package）
        OOProgramming.scope();                  // 05.9.1、作用域 —— public、protected、private、package
        OOProgramming.scope("name");      // 05.9.2、作用域 —— 局部变量
        OOProgramming.nestedClass();            // 05.10、内部类（nested class）嵌套类

        // 06、Java 核心类
        CoreString.compare();             // 06.1.1、字符串（String）比较
        CoreString.searchAndSub();        // 06.1.2、字符串（String）包含子串、搜索子串、提取子串、替换子串
        CoreString.trimAndStrip();        // 06.1.3、字符串（String）移除首尾空白字符
        CoreString.splitAndJoin();        // 06.1.4、字符串（String）分割字符串、拼接字符串
        CoreString.format();              // 06.1.5、字符串（String）格式化字符串
        CoreString.valueOf();             // 06.1.6、字符串（String）类型转换
        CoreString.code();                // 06.1.7、字符串（String）字符串编码
        LearnStringBuilder.practice();    // 06.1.8、高效拼接字符串（StringBuilder）
        LearnStringJoiner.practice();     // 06.1.9、分隔符拼接数组（StringJoiner）
        WrapperClass.learn();             // 06.2、包装类型（Wrapper Class）自动装箱、自动拆箱
        WrapperClass.immutableProperty(); // 06.2.1、包装类型（Wrapper Class）不可变
        WrapperClass.parse();             // 06.2.2、包装类型（Wrapper Class）进制转换
        WrapperClass.unsignedInt();       // 06.2.3、包装类型（Wrapper Class）无符号整型
        LearnJavaBean.learn();            // 06.3、JavaBean —— 属性（property）
        LearnJavaBean.enumProperty();     // 06.3.1、JavaBean —— 获取属性列表
        LearnEnum.practice();             // 06.4、枚举类（enum）
        LearnEnum.equals();               // 06.4.1、枚举类（enum）比较
        LearnEnum.method();               // 06.4.2、枚举类（enum）常用方法
        LearnRecord.practice();           // 06.5、记录类（record）
	}
}