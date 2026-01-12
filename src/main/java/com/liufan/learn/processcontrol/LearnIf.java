package com.liufan.learn.processcontrol;

import com.liufan.learn.basicdata.LearnFloat;

/**
 * if 条件判断
 * @see LearnSwitchCase
 */
public class LearnIf {

    /**
     * if 条件判断
     * <p>
     * 使用<code>if</code>时，还要特别注意边界条件
     */
    public static void ifPractice(int score) {
        /*
        if (score < 60) {
            System.out.println("不及格"); // 只有一行语句时，可以省略花括号{}
        }

        但是，省略花括号并不总是一个好主意。假设某个时候，突然想给 if 语句块增加一条语句时，
        由于使用缩进格式，很容易把两行语句都看成 if 语句的执行块。
        在使用 git 这些版本控制系统自动合并时更容易出问题，所以不推荐忽略花括号的写法。

        if (n < 60)
            System.out.println("不及格");
            System.out.println("要加油"); // 注意这条语句不是if语句块的一部分
        */

        if (score >= 60) {
            System.out.println("及格了");
        } else {
            System.out.println("挂科了");
        }
        System.out.println("END");

        // 使用 if 时，还要特别注意边界条件
        double x = 1 - 9.0 / 10;
        /*
        if (x == 0.1) {
            System.out.println("x is 0.1");
        } else {
            System.out.println("x is not 0.1"); // 很大概率执行这里
        }

        前面讲过了浮点数在计算机中常常无法精确表示，并且计算可能出现误差，因此，判断浮点数相等用 == 判断不靠谱
        */
        if (LearnFloat.Arithmetic.floatEquals(x, 0.1)) {
            System.out.println("x is 0.1");
        } else {
            System.out.println("x is not 0.1");
        }
    }

    /**
     * if 串联
     * <p>
     * 在串联使用多个<code>if</code>时，要特别注意判断顺序：
     * <ul>
     *     <li>使用<code>> 或 >=</code>时，从大到小依次判断</li>
     *     <li>使用<code>< 或 <=</code>时，从小到大依次判断</li>
     * </ul>
     */
    public static void elseIfPractice(int score) {
        if (score >= 90) {
            System.out.println("优秀");
        } else if (score >= 60) {
            System.out.println("及格了");
        } else {
            System.out.println("挂科了");
        }
        System.out.println("END");
        /*
        串联的效果其实相当于：
        if (score >= 90) {
            System.out.println("优秀");
        } else {
            if (score >= 60) {
                System.out.println("及格了");
            } else {
                System.out.println("挂科了");
            }
        }

        在串联使用多个 if 时，要特别注意判断顺序。观察下面的代码：当 score = 100 时，满足 score >= 90，应该属于优秀，但实际执行的是及格了。
        原因是 if 语句从上到下执行时，先判断 n >= 60 成功后，后续 else 不再执行，因此，if (n >= 90) 没有机会执行了。
        if (score >= 60) {
            System.out.println("及格了");
        } else {
            if (score >= 90) {
                System.out.println("优秀");
            } else {
                System.out.println("挂科了");
            }
        }
         */
    }
}
