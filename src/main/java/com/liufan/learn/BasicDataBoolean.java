package com.liufan.learn;

/**
 * 布尔类型
 * <p>
 * 布尔类型只有两个值：
 * <ul>
 *     <li>true</li>
 *     <li>false，默认值</li>
 * </ul>
 * @see BasicDataBoolean.Arithmetic
 */
class BasicDataBoolean extends BasicData {

    static void practice() {
        boolean b1 = true;
        boolean b2 = false;
        System.out.println(b1);
        System.out.println(b2);

        // int age = 12;
        // boolean isAdult = age >= 18; // 计算结果为 false
    }

    /**
     * 布尔运算
     * <p>
     * 布尔运算是一种逻辑关系运算，包括以下几类：
     * <ul>
     *     <li>比较运算符：>、>=、<、<=、==、!=</li>
     *     <li>与运算（&&）</li>
     *     <li>或运算（||）</li>
     *     <li>非运算（!）</li>
     * </ul>
     * 关系运算符的优先级从高到低依次是：
     * <ol>
     *     <li>!</li>
     *     <li>>、>=、<、<=</li>
     *     <li>==、!=</li>
     *     <li>&&</li>
     *     <li>||</li>
     * </ol>
     */
    static class Arithmetic {

        /**
         * 布尔运算
         * <p>
         * 布尔运算的一个重要特点是短路运算。如果一个布尔运算的表达式能提前确定结果，则后续的计算不再执行，直接返回结果。
         */
        static void practice() {
            // 因为 false && x 的结果总是 false，无论 x 是 true 还是 false，因此与运算在确定第一个值为 false 后，
            // 不再继续计算，而是直接返回 false。
//            boolean b = 5 < 3;
//            boolean result = b && (5 / 0 > 0); // 这时候 5 / 0 运行时不会报错
//            System.out.println(result);
            System.out.println("如果一个布尔运算的表达式能提前确定结果，则后续的计算不再执行，直接返回结果。");
        }

        /**
         * 三元运算 <code>b ? x : y</code>
         * <p>
         * 三元运算也是“短路运算”，根据 b 的值只计算 x 或 y。
         */
        static void ternaryOperation() {
            // 注意到三元运算 b ? x : y 会首先计算 b，如果 b 为 true，则只计算 x，否则只计算 y。
            // 此外 x 和 y 的类型必须相同，因为返回值不是 boolean ，而是 x 和 y 之一。
//            int n = -100;
//            int x = n >= 0 ? n : -n;
//            System.out.println(x); // -n 100
            System.out.println("三元运算的返回值不是 boolean ，而是 x 和 y 之一。");
        }
    }
}
