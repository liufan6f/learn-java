package com.liufan.learn.processcontrol;

/**
 * while 循环
 * <p>
 * while 循环是先判断条件是否成立。如果计算结果为 true，就把循环体内的语句执行一遍，如果计算结果为 false，
 * 那就直接跳到 while 循环的末尾，继续往下执行。
 * <p>
 * <b>可见 while 循环可能一次都不执行</b>
 * @see LearnDoWhile
 */
public class LearnWhile {

    /**
     * while 循环练习
     * <p>
     * 计算机的特点是计算速度非常快，我们让计算机循环一亿次也用不到 1 秒，所以很多计算的任务，人去算是算不了的，
     * 但是计算机算，使用循环这种简单粗暴的方法就可以快速得到结果。
     */
    public static void practice() {
        int sum = 0;
        int n = 1;
        while (n <= 100) {
            sum += n;
            n ++;
        }
        System.out.println(sum);
        /*
        对于循环条件判断，以及自增变量的处理，要特别注意边界条件。思考一下下面的代码为何没有获得正确结果：
        int n = 0;
        while (n <= 100) {
            n ++;
            sum = sum + n;
        }
         */

        // 如果循环条件永远满足，那这个循环就变成了死循环。死循环将导致 100% 的 CPU 占用，用户会感觉电脑运行缓慢，所以要避免编写死循环代码。
        while (n > 0) {
            sum += n;
            n ++;
        }
        // 表面上看，上面的 while 循环是一个死循环，但是，Java 的 int 类型有最大值，达到最大值后，再加 1 会变成负数，
        // 结果，意外退出了 while 循环。
        System.out.println(n); // -2147483648
    }
}