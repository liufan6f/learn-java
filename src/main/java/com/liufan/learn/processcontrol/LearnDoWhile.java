package com.liufan.learn.processcontrol;

/**
 * do while 循环
 * <p>
 * do while 循环是先执行循环体内的语句，然后判断条件是否成立，条件满足时继续循环，条件不满足时退出。
 * <p>
 * <b>可见 do while 循环至少循环一次</b>
 * @see LearnWhile
 */
public class LearnDoWhile {

    public static void practice() {
        int sum = 0;
        int n = 1;
        do {
            sum = sum + n;
            n ++;
        } while (n <= 100); // 一样要注意死循环
        System.out.println(sum);
    }
}
