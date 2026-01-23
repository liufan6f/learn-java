package com.liufan.learn.classtype;

import com.liufan.learn.LearnVar;

/**
 * 字符串
 * <p>
 * 和 <code>char</code> 类型不同，字符串 <code>String</code> 是引用类型。
 * Java 的字符串除了是一个引用类型外，还有个重要特点，就是字符串不可变。
 * @see com.liufan.learn.basicdata.LearnChar
 */
public class LearnString extends LearnClass {

    /**
     * 常见的转义字符：
     * <ul>
     *     <li>\"      表示字符 "</li>
     *     <li>\'      表示字符 '</li>
     *     <li>\\      表示字符 \</li>
     *     <li>\n      表示换行符</li>
     *     <li>\r      表示回车符</li>
     *     <li>\t      表示Tab</li>
     *     <li>\\u#### 表示一个 Unicode 编码的字符</li>
     * </ul>
     */
    public static void practice() {
        // 注意要区分空值 null 和空字符串 ""，空字符串是一个有效的字符串对象，它不等于 null。
        String sn = null;                // 空值 null，它表示不存在，即该变量不指向任何对象。
        String s = "";                   // 空字符串，包含 0 个字符

        String s1 = "A";                 // 包含 1 个字符
        String s2 = "中文 ABC";           // 包含 6 个字符，其中有 1 个空格
        String s3 = "ABC\n\u0041\u4e2d"; // 包含 6 个字符：ABC 换行符 A 中
        System.out.println(s3);
    }

    /**
     * 字符串连接
     * <p>
     * Java 的编译器对字符串做了特殊照顾，可以使用 + 连接任意字符串和其他数据类型。
     */
    public static void stringPlus() {
        int age = 25;
        String s = "age is " + age; // 如果用 + 连接字符串和其他数据类型，会将其他数据类型先自动转型为字符串
        System.out.println(s);
    }

    /**
     * 多行字符串
     * <p>
     * 从 Java 13 开始，字符串可以用 """...""" 表示多行字符串（Text Blocks）了
     */
    public static void multilineString() {
        // 我们要表示多行字符串，使用 + 号连接会非常不方便
        /*
        String s = "first line \n"
                + "second line \n"
                + "end";
        */

        // 从 Java 13 开始，字符串可以用 """...""" 表示多行字符串（Text Blocks）了
        String s = """
                   SELECT * FROM
                     users
                   WHERE id > 100
                   ORDER BY name DESC
                   """;
        System.out.println(s);

        // 上述多行字符串实际上是 5 行，在最后一个 DESC 后面还有一个 \n。
        // 如果我们不想在字符串末尾加一个 \n，就需要这么写：
        String s1 = """
                   SELECT * FROM
                     users1
                   WHERE id > 100
                   ORDER BY name DESC""";
        System.out.println(s1);

        // 如果多行字符串的排版不规则，总是以最短的行首空格为基准
        String s2 = """
                   SELECT * FROM
                     users2
                WHERE id > 100
                   ORDER BY name DESC""";
        System.out.println(s2);
    }

    /**
     * 字符串不可变（引用类型变量的指向）
     * <p>
     * Java 的字符串除了是一个引用类型外，还有个重要特点，就是字符串不可变。
     * @see LearnVar#practice()
     * @see LearnArray#immutableProperty()
     * @see LearnArray#stringArray()
     */
    public static void immutableProperty() {
        String s = "hello";
        System.out.println(s); // hello
        s = "world";
        System.out.println(s); // world
        /*
        观察执行结果，难道字符串 s 变了吗？其实变的不是字符串，而是变量 s 的“指向”。

        执行 String s = "hello"; 时，JVM 虚拟机先创建字符串 "hello"，然后，把字符串变量 s 指向它：
              s
              │
              ▼
        ┌───┬───────────┬───┐
        │   │  "hello"  │   │
        └───┴───────────┴───┘

        紧接着，执行 s = "world"; 时，JVM 虚拟机先创建字符串 "world"，然后，把字符串变量 s 指向它：
              s ──────────────┐
                              │
                              ▼
        ┌───┬───────────┬───┬───────────┬───┐
        │   │  "hello"  │   │  "world"  │   │
        └───┴───────────┴───┴───────────┴───┘
        原来的字符串 "hello" 还在，只是我们无法通过变量 s 访问它而已。
        因此，字符串的不可变是指字符串内容不可变。至于变量，可以一会指向字符串 "hello"，一会指向字符串 "world"。
         */

        String s1 = "hello";
        String t = s1;
        s1 = "world";
        System.out.println(t); // t 是 "hello" 还是 "world"?
    }
}
