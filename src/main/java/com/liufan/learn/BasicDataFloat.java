package com.liufan.learn;

/**
 * 浮点型（浮点数类型）
 * <p>
 * 浮点数就是小数，因为小数用科学计数法表示的时候，小数点是可以“浮动”的，如 1234.5 可以表示成 12.345x10²，
 * 也可以表示成1.2345x10³，所以称为浮点数。浮点数可表示的范围非常大，float 类型可最大表示 3.4x10³⁸，
 * 而 double 类型可最大表示1.79x10³⁰⁸。
 * <p>
 * 浮点数虽然表示的范围大，但是，浮点数有个非常重要的特点，就是浮点数常常无法精确表示。
 * @see BasicDataFloat.Arithmetic#fourFundamentalRules()
 */
class BasicDataFloat extends BasicData {

    /**
     * 浮点数在内存的表示方法和整数比更加复杂。Java 的浮点数完全遵循
     * <a href="https://standards.ieee.org/ieee/754/6210/">IEEE-754标准</a>，
     * 这也是绝大多数计算机平台都支持的浮点数标准表示方法。
     */
    static void practice() {
        float f1 = 3.14f;    // 对于 float 类型，需要加上 f 后缀
        float f2 = 3.14e38f; // 科学计数法表示的 3.14x10³⁸
        // float f3 = 1.0;   // 报错：不带 f 结尾的是 double 类型，不能赋值给 float
        System.out.println(f1);
        System.out.println(f2);

        double d1 = 1.79e308;
        double d2 = -1.79e308;
        double d3 = 4.9e-324; // 科学计数法表示的4.9x10的-324次方
        System.out.println(d1);
        System.out.println(d2);
        System.out.println(d3);
    }

    /**
     * 浮点数运算
     * <p>
     * 浮点数运算和整数运算相比，只能进行加减乘除这些数值计算，不能做位运算和移位运算。
     */
    static class Arithmetic {

        /**
         * 四则运算
         * <p>
         * 浮点数虽然表示的范围大，但是，浮点数有个非常重要的特点，就是浮点数常常无法精确表示。
         * <p>
         * 那么该如何判断两个浮点数是否相等呢？
         * @see BasicDataFloat.Arithmetic#floatEquals(Double, Double)
         */
        static void fourFundamentalRules() {
            /*
            例如：浮点数 0.1 在计算机中就无法精确表示，因为十进制的 0.1 换算成二进制是一个无限循环小数，很显然，
            无论使用 float 还是 double ，都只能存储一个 0.1 的近似值。但是 0.5 这个浮点数又可以精确地表示。
             */
            double x = 1.0 / 10;
            double y = 1 - 9.0 / 10;
            // 观察x和y是否相等:
            System.out.println(x);
            System.out.println(y);
        }

        /**
         * 浮点数比较
         * <p>
         * 由于浮点数存在运算误差，所以比较两个浮点数是否相等常常会出现错误的结果。
         * 正确的比较方法是判断两个浮点数之差的绝对值是否小于一个很小的数。
         */
        static boolean floatEquals(Double x, Double y) {
            return Math.abs(x - y) < 0.00001;
        }

        /**
         * 浮点数比较
         * <p>
         * 由于浮点数存在运算误差，所以比较两个浮点数是否相等常常会出现错误的结果。
         * 正确的比较方法是判断两个浮点数之差的绝对值是否小于一个很小的数。
         * @see BasicDataFloat.Arithmetic#floatEquals(Double, Double)
         */
        static void floatEqualsExample() {
            double x = 1.0 / 10;
            double y = 1 - 9.0 / 10;
            System.out.println(floatEquals(x, y));
        }

        /**
         * 类型提升
         * <p>
         * 如果参与运算的两个数其中一个是整型，那么整型可以自动提升到浮点型。
         */
        static void typePromotion() {
            int n = 5;
            double a = 1.2 + 24.0 / n; // 6.0
            System.out.println(a);

            // ⚠️但是需要特别注意：在一个复杂的四则运算中，两个整数的运算不会出现自动提升的情况。例如：
//            double b = 1.2 + 24 / 5; // 结果不是 6.0 而是 5.2。先整数相除等于 4，再自动提升为浮点型
            double b = 1.2 + 24 / 5.0; // 改造 5 或 24 为浮点型即可，6.0
            System.out.println(b);
        }

        /**
         * 数值溢出
         * <p>
         * 整数运算在除数为 0 时运行时会报错，而浮点数运算在除数为 0 时，不会报错，但会返回几个特殊值：
         * <ul>
         *     <li>NaN：Not a Number，非数字</li>
         *     <li>Infinity：正无穷大</li>
         *     <li>-Infinity：负无穷大</li>
         * </ul>
         * @see BasicDataInteger.Arithmetic#fourFundamentalRules()
         */
        static void numericOverflow() {
//            double d1 = 0.0 / 0; // NaN
//            double d2 = 1.0 / 0; // Infinity
//            double d3 = -1.0 / 0; // -Infinity
//            System.out.println(d1);
//            System.out.println(d2);
//            System.out.println(d3);
            System.out.println("这三种特殊值在实际运算中很少碰到，我们只需要了解即可。");
        }

        /**
         * 强制转型
         * <p>
         * 可以将浮点数强制转型为整数。在转型时，浮点数的小数部分会被丢掉。
         * 如果转型后超过了整型能表示的最大范围，将返回整型的最大值。
         */
        static void typePromotionCoercion() {
//            int n1 = (int) 12.3;         // 12
//            int n2 = (int) 12.7;         // 12
//            int n3 = (int) -12.7;        // -12
//            int n4 = (int) (12.7 + 0.5); // 13
            int n5 = (int) 1.2e20;       // 2147483647 int 最大值
            System.out.println(n5 + " = " + Integer.MAX_VALUE);

            // 如果要对浮点数进行四舍五入，可以对浮点数加上 0.5 再强制转型
            double[] arr = { 2.0, 2.1, 2.2, 2.3, 2.4, 2.5, 2.6, 2.7, 2.8, 2.9 };
            for (double d : arr) {
                int n = (int) (d + 0.5);
                System.out.println(d + " 四舍五入后为：" + n);
            }
        }
    }
}
