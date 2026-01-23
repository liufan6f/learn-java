package com.liufan.learn.processcontrol;

/**
 * 循环
 * <p>
 * 无论是 while 循环还是 for 循环，有两个特别的语句可以使用，就是 break 语句和 continue 语句。
 * @see LearnWhile#whilePractice()
 * @see LearnWhile#doWhile()
 * @see LearnFor#forPractice()
 * @see LearnFor#forEach()
 */
public class Loop {

    /**
     * break 语句
     * <p>
     * 在循环过程中，可以使用 break 语句跳出当前循环
     */
    public static void breakPractice() {
        int sum = 0;
        for (int i = 1; ; i++) {
            sum = sum + i;
            if (i == 100) {
                break; // 跳出死循环
            }
        }
        System.out.println(sum);

        for (int i = 1; i <= 10; i++) {
            System.out.println("i = " + i);
            for (int j = 1; j <= 10; j++) {
                System.out.println("j = " + j);
                if (j >= i) {
                    break; // break 语句总是跳出自己所在的那一层循环
                }
            }
            // break跳到这里
            System.out.println("breaked");
        }
    }

    /**
     * continue 语句
     * <p>
     * 在循环过程中，continue 可以提前结束本次循环，直接继续执行下次循环。
     */
    public static void continuePractice() {
        int sum = 0;
        for (int i = 1; i <= 10; i++) {
            System.out.println("begin i = " + i);
            if (i % 2 == 0) {
                          // 在多层嵌套的循环中，continue 语句同样是结束本次自己所在的循环。
                continue; // continue 语句会结束本次循环，直接进入下次循环，下面 2 句代码不会执行
            }
            sum = sum + i;
            System.out.println("end i = " + i);
        }
        System.out.println(sum); // 25
    }
}
