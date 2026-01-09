package com.liufan.learn.classtype;

import java.util.Arrays;

/**
 * 数组
 * <p>
 * 数组是引用类型
 * <p>
 * Java的数组有几个特点：
 * <ul>
 *     <li>数组所有元素初始化为默认值，整型都是 0 ，浮点型是 0.0 ，布尔型是 false；</li>
 *     <li>数组一旦创建后，大小不可改变。</li>
 * </ul>
 */
public class LearnArray extends LearnClass {

    public static void practice() {
        // 定义一个数组类型的变量，使用数组类型“类型[]”，例如，int[]。
        // 和单个基本类型变量不同，数组变量初始化必须使用 new int[5] 表示创建一个可容纳 5 个 int 元素的数组。
        /*
        int[] ns = new int[5];
        ns[0] = 68;
        ns[1] = 79;
        ns[2] = 91;
        ns[3] = 85;
        ns[4] = 62;
        */

        // 也可以在定义数组时直接指定初始化的元素，这样就不必写出数组大小，而是由编译器自动推算数组大小。
        // int[] ns = new int[] { 68, 79, 91, 85, 62 }; // 还可以进一步简写
        int[] ns = { 68, 79, 91, 85, 62 };

        System.out.println(Arrays.toString(ns));

        // 数组是引用类型，在使用索引访问数组元素时，如果索引超出范围，运行时将报错：
//        System.out.println(ns[5]); // 索引 n 不能超出范围
        System.out.println(ns[2]);
    }

    /**
     * 数组大小不可变（数组变量的指向）
     * @see #stringArray()
     * @see LearnString#immutableProperty()
     * @see com.liufan.learn.LearnVariable#practice()
     */
    public static void immutableProperty() {
        int[] ns = new int[] { 68, 79, 91, 85, 62 };
        System.out.println(ns.length); // 5
        ns = new int[] { 1, 2, 3 };
        System.out.println(ns.length); // 3

        /*
        数组大小变了吗？看上去好像是变了，但其实根本没变。

        执行 int[] ns = new int[] { 68, 79, 91, 85, 62 }; 时，它指向一个 5 个元素的数组：
             ns
              │
              ▼
        ┌───┬───┬───┬───┬───┬───┬───┐
        │   │68 │79 │91 │85 │62 │   │
        └───┴───┴───┴───┴───┴───┴───┘

        执行 ns = new int[] { 1, 2, 3 }; 时，它指向一个新的 3 个元素的数组：
             ns ──────────────────────┐
                                      │
                                      ▼
        ┌───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┐
        │   │68 │79 │91 │85 │62 │   │ 1 │ 2 │ 3 │   │
        └───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┘
        但是，原有的 5 个元素的数组并没有改变，只是无法通过变量 ns 引用到它们而已。
         */
    }

    /**
     * 字符串数组（引用类型数组）
     * @see #immutableProperty()
     * @see LearnString#immutableProperty()
     * @see com.liufan.learn.LearnVariable#practice()
     */
    public static void stringArray() {
        String[] names = { "ABC", "XYZ", "zoo" };
        /*
        如果数组元素不是基本类型，而是一个引用类型，那么，修改数组元素会有哪些不同？

        对于 String[] 类型的数组变量 names，它实际上包含 3 个元素，但每个元素都指向某个字符串对象：
                  ┌─────────────────────────┐
            names │   ┌─────────────────────┼───────────┐
              │   │   │                     │           │
              ▼   │   │                     ▼           ▼
        ┌───┬───┬─┴─┬─┴─┬───┬───────┬───┬───────┬───┬───────┬───┐
        │   │░░░│░░░│░░░│   │ "ABC" │   │ "XYZ" │   │ "zoo" │   │
        └───┴─┬─┴───┴───┴───┴───────┴───┴───────┴───┴───────┴───┘
              │                 ▲
              └─────────────────┘
         */

        String s = names[1];
        names[1] = "cat";
        /*
        对 names[1] 进行赋值，例如 names[1] = "cat"; ，效果如下：
                  ┌─────────────────────────────────────────────────┐
            names │   ┌─────────────────────────────────┐           │
              │   │   │                                 │           │
              ▼   │   │                                 ▼           ▼
        ┌───┬───┬─┴─┬─┴─┬───┬───────┬───┬───────┬───┬───────┬───┬───────┬───┐
        │   │░░░│░░░│░░░│   │ "ABC" │   │ "XYZ" │   │ "zoo" │   │ "cat" │   │
        └───┴─┬─┴───┴───┴───┴───────┴───┴───────┴───┴───────┴───┴───────┴───┘
              │                 ▲
              └─────────────────┘
        这里注意到原来 names[1] 指向的字符串 "XYZ" 并没有改变，仅仅是将 names[1] 的引用从指向 "XYZ" 改成了指向 "cat"，
        其结果是字符串 "XYZ" 再也无法通过 names[1] 访问到了。
         */

        System.out.println(s); // 那么此时 s 是 "XYZ" 还是 "cat"?
    }
}
