package com.liufan.learn;

/**
 * 变量
 * <p>
 * 在 Java 中分为两种：
 * <ol>
 *     <li>基本数据类型的变量，变量是“持有”某个数值；</li>
 *     <li>引用类型的变量，变量是“指向”某个对象，它也可以指向一个空值 <code>null</code>，表示不存在，不指向任何对象。</li>
 * </ol>
 * @see com.liufan.learn.basicdata.BasicData
 * @see com.liufan.learn.classtype.LearnClass
 * @see com.liufan.learn.classtype.LearnString#immutableProperty()
 */
class Variable {

    static void practice() {
        // int n;    // 不写初始值，就相当于给它指定了默认值。默认值总是 0。
        int n = 100; // 整型 int 类型的变量，名称为 n，初始值为 100。
        System.out.println("n = " + n);
        n = 200;     // 变量可以重新赋值。此时变量 n 已经存在了，不能再重复定义，因此不能指定变量类型 int
        System.out.println("n = " + n);

        int x = n;   // 变量还可以赋值给其他变量。
        System.out.println("x = " + x);

        x = x + 100;
        System.out.println("x = " + x);
        System.out.println("n = " + n);

        /*
        我们一行一行地分析代码执行流程：

        执行 int n = 100;
              n
              │
              ▼ JVM 在内存中为变量 n 分配一个“存储单元”，填入值 100。
        ┌───┬───┬───┬───┬───┬───┬───┐
        │   │100│   │   │   │   │   │
        └───┴───┴───┴───┴───┴───┴───┘

        执行 n = 200;
              n
              │
              ▼ JVM 把 200 写入变量 n 的存储单元，因此，原有的值被覆盖，现在 n 的值为 200。
        ┌───┬───┬───┬───┬───┬───┬───┐
        │   │200│   │   │   │   │   │
        └───┴───┴───┴───┴───┴───┴───┘

        执行 int x = n;
              n           x
              │           │ 定义了一个新的变量 x，同时对 x 赋值，因此，JVM 需要新分配一个存储单元给变量 x，
              ▼           ▼ 并写入和变量 n 一样的值，结果是变量 x 的值也变为 200。
        ┌───┬───┬───┬───┬───┬───┬───┐
        │   │200│   │   │200│   │   │
        └───┴───┴───┴───┴───┴───┴───┘

        执行 x = x + 100;
              n           x
              │           │ JVM首先计算等式右边的值 x + 100，结果为 300，然后，将结果 300 写入 x 的存储单元，
              ▼           ▼ 因此，变量 x 最终的值变为 300。
        ┌───┬───┬───┬───┬───┬───┬───┐
        │   │200│   │   │300│   │   │
        └───┴───┴───┴───┴───┴───┴───┘
        ⚠️注意，等号 = 是赋值语句，不是数学意义上的相等，否则无法解释 x = x + 100。
        */
    }

    /**
     * 常量
     * <p>
     * 定义变量的时候，如果加上 final 修饰符，这个变量就变成了常量
     */
    static void finalPractice() {
        // 常量的作用是用有意义的变量名来避免魔术数字（Magic number），例如不要在代码中到处写 3.14，而是定义一个常量。
        // 如果将来需要提高计算精度，我们只需要在常量的定义处修改，例如改成 3.1416，而不必在所有地方替换 3.14。
        final double PI = 3.14; // PI 是一个常量
        double r = 5.0;
        double area = PI * r * r;
        System.out.println(area);
        // PI = 3.1416; // 报错：常量不能重新赋值
    }

    /**
     * var 关键字
     * <p>
     * 有时，类的名字太长，写起来比较麻烦，这个时候，如果想省略变量类型，可以使用 var 关键字。
     */
    static void varPractice() {
//        StringBuilder sb = new StringBuilder();
//        var sb = new StringBuilder(); // 编译器会根据赋值语句自动推断出变量 sb 的类型是 StringBuilder
        System.out.println("var 关键字用来替换一些名字长的类");
    }
}