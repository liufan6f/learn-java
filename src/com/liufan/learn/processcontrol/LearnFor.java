package com.liufan.learn.processcontrol;

/**
 * for 循环，Java 使用最广泛的循环
 * <p>
 * for 循环会先初始化计数器，然后，在每次循环前检测循环条件，在每次循环后更新计数器。计数器变量通常命名为 i。
 */
public class LearnFor extends Loop {

    public static void forPractice() {
        /*
        在 for 循环执行前，会先执行初始化语句 int i = 1;
        它定义了计数器变量 i 并赋初始值为 1，然后，循环前先检查循环条件 i <= 100;
        循环后自动执行 i++，因此，和 while 循环相比，for 循环把更新计数器的代码统一放到了一起。
        在 for 循环的循环体内部，不需要去更新变量 i。
         */
        int sum = 0;

        // ⚠️注意 for 循环的初始化计数器总是会被执行，并且 for 循环也可能循环0次。
        for (int i = 1 /* 初始条件 */; i <= 100 /* 循环检测条件 */; i++ /* 循环后更新计数器 */) {
            sum = sum + i;
        }
        System.out.println(sum);

        // 遍历数组
        int[] ns = { 1, 4, 9, 16, 25 };
        int nsSum = 0;
        for (int i = 0; i < ns.length; i++) {
            System.out.println("i = " + i + ", ns[i] = " + ns[i]);
            nsSum = nsSum + ns[i];
        }
        System.out.println("nsSum = " + nsSum);

        // ⚠️使用 for 循环时，千万不要在循环体内修改计数器！在循环体中修改计数器常常导致莫名其妙的逻辑错误。如：
        for (int i = 0; i < ns.length; i++) {
            System.out.println("i = " + i + ", ns[i] = " + ns[i]); // 1 9 25
            i = i + 1; //            原因是这里 + 1 之后，for 循环还会自动执行 i ++，因此每次循环实际上加了 2。
        }
        // 因此，在 for 循环中，不要修改计数器的值。计数器的初始化、判断条件、每次循环后的更新条件统一放到 for() 语句中可以一目了然。
        // 如果希望只访问索引号为偶数的数组元素，应该把for循环改写为：
        for (int i = 0; i < ns.length; i = i + 2) {
            System.out.println("i = " + i + ", ns[i] = " + ns[i]); // 1 9 25
        }

        // ⚠️使用 for 循环时，计数器变量 i 要尽量定义在 for 循环中
//        int n = i; // compile error!     在此之前，我们都无法访问 i，因为 i 是 for 循环的一部分。

        int i;
        for (i = 0; i < ns.length; i++) {
            System.out.println("i = " + i + ", ns[i] = " + ns[i]);
        }
        System.out.println("i = " + i); // 退出 for 循环后，变量 i 仍然可以被访问，这就破坏了变量应该把访问范围缩到最小的原则。
    }

    /**
     * 简易使用 for 循环
     * <p>
     * for 循环还可以缺少初始化语句、循环条件和每次循环更新语句。通常不推荐这样写，但是，某些情况下，是可以省略 for 循环的某些语句的。
     */
    public static void simpleFor() {
        /*
        // 不设置循环检测条件，也就是结束条件
        for (int i = 0; ; i++) {
            // 死循环
        }

        // 不设置循环检测条件，也就是结束条件，也不设置更新语句
        for (int i = 0;;) {
            // 死循环
        }

        // 什么都不设置
        for (;;) {
            // 死循环
        }
        */
        System.out.println("通常不推荐这样写，但是，某些情况下，是可以省略 for 循环的某些语句的。");
    }

    /**
     * for each 循环
     */
    public static void forEach() {
        // for 循环经常用来遍历数组，因为通过计数器可以根据索引来访问数组的每个元素
        int[] ns = { 1, 4, 9, 16, 25 };
        for (int i = 0; i < ns.length; i++) {
            System.out.println("i = " + i + ", ns[i] = " + ns[i]);
        }

        // 但是，很多时候，我们实际上真正想要访问的是数组每个元素的值
        for (int n : ns) {
            System.out.println("n = " + n);
        }
    }
}
