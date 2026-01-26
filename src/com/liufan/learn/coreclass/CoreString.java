package com.liufan.learn.coreclass;

import com.liufan.learn.classtype.LearnString;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * Java 核心类 —— String
 * <p>
 * Java 字符串的一个重要特点就是<b>字符串不可变</b>。这种不可变性是通过内部的<code>private final char[]</code>数组字段，
 * 以及没有任何修改这个字段的方法实现的。按下面的写法创建字符串也是可以的：
 * <pre><code>String s = new String(new char[] {'H', 'e', 'l', 'l', 'o', '!'});</code></pre>
 * 延伸阅读：
 * <p>
 * 早期 JDK 版本的 String 总是以<code>char[]</code>存储：
 * <pre>
 *     public final class String {
 *         private final char[] value;
 *         private final int offset;
 *         private final int count;
 *     }
 * </pre>
 * 而较新的 JDK 版本的 String 则以 byte[] 存储：如果 String 仅包含 ASCII 字符，则每个 byte 存储一个字符，否则，
 * 每两个 byte 存储一个字符，这样做的目的是为了节省内存，因为大量的长度较短的 String 通常仅包含 ASCII 字符：
 * <pre>
 *     public final class String {
 *         private final byte[] value;
 *         private final byte coder; // 0 = LATIN1, 1 = UTF16
 *     }
 * </pre>
 * @see LearnString
 */
public class CoreString extends LearnString {

    /**
     * 字符串比较
     * <ul>
     *     <li>字符串比较必须使用<code>equals()</code></li>
     *     <li>忽略大小写比较，使用<code>equalsIgnoreCase()</code>方法</li>
     * </ul>
     */
    public static void compare() {
        String s1 = "hello";
        String s2 = "hello";
        System.out.println(s1.equals(s2)); // true
        // Java 编译器在编译期，会自动把所有相同的字符串当作一个对象放入常量池，自然 s1 和 s2 的引用就是相同的。
        System.out.println(s1 == s2);      // true，这种 == 比较返回 true 纯属巧合

        String s3 = "HELLO".toLowerCase();
        System.out.println(s2.equals(s3)); // true
        System.out.println(s2 == s3);      // false，换一种写法 == 比较就会失败
    }

    /**
     * 包含子串、搜索子串、提取子串、替换子串
     */
    public static void searchAndSub() {
        String s = "hello";
        System.out.println(s.contains("ll"));         // true

        // 搜索子串
        System.out.println(s.startsWith("he"));       // true
        System.out.println(s.endsWith("lo"));         // true
        System.out.println(s.indexOf("l"));           // 2
        System.out.println(s.lastIndexOf("l"));   // 3

        // 提取子串
        System.out.println(s.substring(2)); // llo
        System.out.println(s.substring(2, 4));        // ll

        // 替换子串
        System.out.println(s.replace('l', 'L'));    // heLLo
        System.out.println(s.replace("ll", "~~")); // he~~o
        // 通过正则表达式替换
        s = "A,,B;C ,D";
        System.out.println(s.replaceAll("[\\,\\;\\s]+", ",")); // "A,B,C,D"
    }

    /**
     * 移除首尾空白字符
     */
    public static void trimAndStrip() {
        String s = "He llo";

        // trim() 移除首尾空白字符，包含空格、\t、\r、\n
        String s1 = "  \t" + s + "\r\n ";
        System.out.print(s1.trim()); // "He llo"
        System.out.println("——print end");
        String s2 = "\u3000" + s + "\u3000";
        // 但去不了中文的空格字符 \u3000
        System.out.print(s2.trim()); // "　He llo　"
        System.out.println("——print end");

        // strip() 除了可以移除首尾空白字符，包含空格、\t、\r、\n，中文的空格字符 \u3000 也会被移除
        String s3 = "  \t" + s2 + "\r\n ";
        System.out.print(s3.strip()); // "He llo"
        System.out.println("——print end");
        String s4 = " Hello ";
        System.out.print(s4.stripLeading()); // "Hello "
        System.out.println("——print end");
        System.out.print(s4.stripTrailing()); // " Hello"
        System.out.println("——print end");

        // isEmpty() 判断字符串是否为空
        System.out.println("".isEmpty());        // true，因为字符串长度为 0
        System.out.println(" ".isEmpty());       // false，因为字符串长度不为 0

        // isBlank() 判断字符串是否为空白字符
        System.out.println(" \n".isBlank());     // true，因为只包含空白字符
        System.out.println(" Hello ".isBlank()); // false，因为包含非空白字符
    }

    /**
     * 分割字符串、拼接字符串
     * @see LearnStringBuilder
     * @see LearnStringJoiner
     */
    public static void splitAndJoin() {
        // 分割字符串
        String s = "A,B,C,D";
        String[] ss = s.split("\\,"); // { "A", "B", "C", "D" }
        System.out.println(Arrays.toString(ss));

        // 拼接字符串
        String s1 = String.join("***", ss); // "A***B***C***D"
        System.out.println(s1);
    }

    /**
     * 格式化字符串
     * <p>
     * 占位符统一查看 {@linkplain com.liufan.learn.ScannerPrint#print()}
     * @see com.liufan.learn.ScannerPrint#print()
     */
    public static void format() {
        String s = "Hi %s, your score is %d!";
        System.out.println(s.formatted("Alice", 80));
        System.out.println(String.format("Hi %s, your score is %.2f!", "Bob", 59.5));
    }

    /**
     * 类型转换
     */
    public static void valueOf() {
        // 任意基本类型或引用类型转换为字符串
        System.out.println(String.valueOf(123));     // "123"
        System.out.println(String.valueOf(123.45)); // "123.45"
        System.out.println(String.valueOf(true));   // "true"
        System.out.println(new Object());              // 类似 java.lang.Object@a09ee92

        // 把字符串转换为其他类型
        System.out.println(Integer.parseInt("123"));          // 123
        System.out.println(Integer.parseInt("ff", 16)); // 按十六进制转换，255
        System.out.println(Boolean.parseBoolean("true"));     // true
        System.out.println(Boolean.parseBoolean("FALSE"));    // false
        // ⚠️getInteger() 不是字符串转换为 Integer，而是把该字符串对应的系统变量转换为 Integer
        // Integer.getInteger("java.version");

        // 转换为 char[]
        char[] cs = "Hello".toCharArray();
        System.out.println(Arrays.toString(cs)); // "[H, e, l, l, o]"
        String s = new String(cs);
        System.out.println(s);  // "Hello"
        // 修改 char 数组元素，String 并不会改变。因为 new String(cs) 并不会直接引用传入的 char 数组，而是会复制一份。
        // 所以从 String 的不变性设计可以看出，如果传入的对象有可能改变，我们需要复制而不是直接引用。
        cs[0] = 'X';
        System.out.println(s);  // "Hello"

        int[] scores = new int[] { 88, 77, 51, 66 };
        CoreString cStr = new CoreString();
        Score score = cStr.new Score(scores);
        score.printScores(); // [88, 77, 51, 66]
        scores[0] = 99;
        score.printScores(); // [99, 77, 51, 66]
        // 观察两次输出，由于 Score 内部直接引用了外部传入的 int[] 数组，这会造成外部代码对 int[] 数组的修改，
        // 影响到 Score 类的字段。如果外部代码不可信，这就会造成安全隐患。
    }

    class Score {
        private int[] scores;
        Score(int[] scores) {
            this.scores = scores;
            // 使用下面这行代码可以修复
            // this.scores = scores.clone();
        }
        void printScores() {
            System.out.println(Arrays.toString(scores));
        }
    }

    /**
     * 字符编码
     * <p>
     * 转换为<code>byte[]</code>时，始终优先考虑 UTF-8 编码。
     * </p>
     * ⚠️始终牢记：Java 的 String 和 char 在内存中总是以 Unicode 编码表示。
     */
    public static void code() {
        /*
        在早期的计算机系统中，为了给字符编码，美国国家标准学会（American National Standard Institute：ANSI）
        制定了一套英文字母、数字和常用符号的编码，它占用一个字节，编码范围从0到127，最高位始终为0，称为ASCII编码。
        例如，字符'A'的编码是0x41，字符'1'的编码是0x31。

        如果要把汉字也纳入计算机编码，很显然一个字节是不够的。GB2312标准使用两个字节表示一个汉字，
        其中第一个字节的最高位始终为1，以便和ASCII编码区分开。例如，汉字'中'的GB2312编码是0xd6d0。类似的，
        日文有Shift_JIS编码，韩文有EUC-KR编码，这些编码因为标准不统一，同时使用，就会产生冲突。

        为了统一全球所有语言的编码，全球统一码联盟发布了Unicode编码，它把世界上主要语言都纳入同一个编码，
        这样，中文、日文、韩文和其他语言就不会冲突。

        Unicode编码需要两个或者更多字节表示，我们可以比较中英文字符在ASCII、GB2312和Unicode的编码：
        英文字符'A'的ASCII编码和Unicode编码：
                 ┌────┐
        ASCII:   │ 41 │
                 └────┘
                 ┌────┬────┐
        Unicode: │ 00 │ 41 │               英文字符的Unicode编码就是简单地在前面添加一个00字节。
                 └────┴────┘
        中文字符'中'的GB2312编码和Unicode编码：
                 ┌────┬────┐
        GB2312:  │ d6 │ d0 │
                 └────┴────┘
                 ┌────┬────┐
        Unicode: │ 4e │ 2d │
                 └────┴────┘


        ⚠️那我们经常使用的UTF-8又是什么编码呢？因为英文字符的Unicode编码高字节总是00，包含大量英文的文本会浪费空间，
        所以，出现了UTF-8编码，它是一种变长编码，用来把固定长度的Unicode编码变成1～4字节的变长编码。通过UTF-8编码，
        英文字符'A'的UTF-8编码变为0x41，正好和ASCII码一致，而中文'中'的UTF-8编码为3字节0xe4b8ad。

        UTF-8编码的另一个好处是容错能力强。如果传输过程中某些字符出错，不会影响后续字符，
        因为UTF-8编码依靠高字节位来确定一个字符究竟是几个字节，它经常用来作为传输编码。
         */
        // 在Java中，char类型实际上就是两个字节的Unicode编码。如果我们要手动把字符串转换成其他编码，可以这样做：
        String s = "Hello";
        byte[] b1 = s.getBytes();                       // 按系统默认编码转换，不推荐
//        byte[] b2 = s.getBytes("GBK");      // 按GBK编码转换
//        byte[] b3 = s.getBytes("UTF-8");    // 按UTF-8编码转换
        byte[] b4 = s.getBytes(StandardCharsets.UTF_8); // 按UTF-8编码转换
        System.out.println(Arrays.toString(b1));
        System.out.println(Arrays.toString(b4));

//        String s4 = new String(b4, "UTF-8");
        String s4 = new String(b4, StandardCharsets.UTF_8); // 按 UTF-8 转换
        System.out.println(s4);
    }
}
