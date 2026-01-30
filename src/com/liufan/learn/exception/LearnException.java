package com.liufan.learn.exception;

/*
在计算机程序运行的过程中，总是会出现各种各样的错误。

有一些错误是用户造成的，比如，希望用户输入一个int类型的年龄，但是用户的输入是abc：
String s = "abc";
int n = Integer.parseInt(s); // NumberFormatException!

程序想要读写某个文件的内容，但是用户已经把它删除了：
String t = readFile("C:\\abc.txt"); // FileNotFoundException!

还有一些错误是随机出现，并且永远不可能避免的。比如：
1、网络突然断了，连接不到远程服务器；
2、内存耗尽，程序崩溃了；
3、用户点“打印”，但根本没有打印机；
...

所以，一个健壮的程序必须处理各种各样的错误。
所谓错误，就是程序调用某个函数的时候，如果失败了，就表示出错。

那么调用方如何获知调用失败的信息？有两种方法：
1、约定返回错误码

    例如，处理一个文件，如果返回0，表示成功，返回其他整数，表示约定的错误码：
    int code = processFile("C:\\test.txt");
    if (code == 0) {
        // ok:
    } else {
        // error:
        switch (code) {
        case 1:
            // file not found:
        case 2:
            // no read permission:
        default:
            // unknown error:
        }
    }
    因为使用int类型的错误码，想要处理就非常麻烦。这种方式常见于底层C函数。

2、在语言层面上提供一个异常处理机制

    Java内置了一套异常处理机制，总是使用异常来表示错误。
    异常是一种class，因此它本身带有类型信息。
                         ┌───────────┐
                         │  Object   │
                         └───────────┘
                               ▲
                               │
                         ┌───────────┐
                         │ Throwable │
                         └───────────┘
                               ▲
                     ┌─────────┴─────────┐
                     │                   │
               ┌───────────┐       ┌───────────┐
               │   Error   │       │ Exception │
               └───────────┘       └───────────┘
                     ▲                   ▲
             ┌───────┘              ┌────┴──────────┐
             │                      │               │
    ┌─────────────────┐    ┌─────────────────┐┌───────────┐
    │OutOfMemoryError │... │RuntimeException ││IOException│...
    └─────────────────┘    └─────────────────┘└───────────┘
                                    ▲
                        ┌───────────┴─────────────┐
                        │                         │
             ┌─────────────────────┐ ┌─────────────────────────┐
             │NullPointerException │ │IllegalArgumentException │...
             └─────────────────────┘ └─────────────────────────┘
从继承关系可知：Throwable是异常体系的根，它继承自Object。Throwable有两个体系：Error和Exception，Error表示严重的错误，程序对此一般无能为力，例如：
1、OutOfMemoryError 内存耗尽；
2、NoClassDefFoundError 无法加载某个Class；
3、StackOverflowError 栈溢出
...

而Exception则是运行时的错误，它可以被捕获并处理。比如某些异常是应用程序逻辑处理的一部分，应该捕获并处理：
1、NumberFormatException 数值类型的格式错误
2、FileNotFoundException 未找到文件
3、SocketException 读取网络失败
...
还有一些异常是程序逻辑编写不对造成的，应该修复程序本身：
1、NullPointerException 对某个null的对象调用方法或字段
2、IndexOutOfBoundsException 数组索引越界
...

Exception主要分为两大类：
1、RuntimeException以及它的子类；
2、非RuntimeException（包括IOException、ReflectiveOperationException等等）。
 */

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * 异常（Exception）
 * <p>
 * Java 规定：
 * <ul>
 *     <li>必须捕获的异常，包括Exception及其子类（但不包括RuntimeException及其子类），这种类型的异常称为Checked Exception；</li>
 *     <li>不需要捕获的异常，包括Error及其子类，RuntimeException及其子类。</li>
 * </ul>
 * ⚠️注意编译器对RuntimeException及其子类不做强制捕获要求，不是指应用程序本身不应该捕获并处理RuntimeException。是否需要捕获，具体问题具体分析。
 */
public class LearnException {
    public static void practice() {
        String s = "中文";
        // 方法内捕获异常
        byte[] bs = toGBK(s);
        System.out.println(Arrays.toString(bs));

        // 方法外捕获异常
        s = "汉语";
        try {
            bs = toGBKThrows(s);
        } catch (UnsupportedEncodingException e) {
            /*
            有一些童鞋喜欢在 catch 内部“消化”异常，如：
            try {
                return s.getBytes("GBK");
            } catch (UnsupportedEncodingException e) {
                // 什么也不干
            }

            这种捕获后不处理的方式是非常不好的，即使真的什么也做不了，也要先把异常记录下来：
             */
            e.printStackTrace(); // 先记下来再说，所有异常都可以调用printStackTrace()方法打印异常栈，这是一个简单有用的快速打印异常的方法，对于调试非常有用。
            bs = s.getBytes();
        }
        System.out.println(Arrays.toString(bs));

        /*
        可见，只要是方法声明的Checked Exception，不在调用层捕获，也必须在更高的调用层捕获。
        所有未捕获的异常，最终也必须在main()方法中捕获，不会出现漏写try的情况。这是由编译器保证的。main()方法也是最后捕获Exception的机会。

        如果是测试代码，上面的写法就略显麻烦。如果不想写任何try代码，可以直接把main()方法定义为throws Exception：
        public class Main {
            public static void main(String[] args) throws Exception {
                byte[] bs = toGBK("中文");
                System.out.println(Arrays.toString(bs));
            }

            static byte[] toGBK(String s) throws UnsupportedEncodingException {
                // 用指定编码转换String为byte[]:
                return s.getBytes("GBK");
            }
        }
        因为main()方法声明了可能抛出Exception，也就声明了可能抛出所有的Exception，因此在内部就无需捕获了。代价就是一旦发生异常，程序会立刻退出。
         */
    }

    private static byte[] toGBK(String s) {
        // 捕获异常使用try...catch语句，把可能发生异常的代码放到try {...}中，然后使用catch捕获对应的Exception及其子类
        try {
            return s.getBytes("GBK"); // 用指定编码转换String为byte[]
        } catch (UnsupportedEncodingException e) {
            // 如果系统不支持GBK编码，会捕获到UnsupportedEncodingException:
            e.printStackTrace(); // 打印异常信息
            return s.getBytes(); // 尝试使用默认编码
        }
    }

    private static byte[] toGBKThrows(String s) throws UnsupportedEncodingException {
        // 也可以不捕获，而是在方法定义处用throws表示方法可能会抛出UnsupportedEncodingException
        return s.getBytes("GBK");
    }

    /**
     * 捕获多种异常
     * <p>
     * 可以使用多个catch语句，每个catch分别捕获对应的Exception及其子类。JVM在捕获到异常后，会从上到下匹配catch语句，
     * 匹配到某个catch后，执行catch代码块，然后不再继续匹配。
     */
    public static void multiCatch() {
        /*
        存在多个catch的时候，catch的顺序非常重要：子类必须写在前面。例如：

        try {
            process1();
            process2();
            process3();
        } catch (IOException e) {
            System.out.println("IO error");
        } catch (UnsupportedEncodingException e) { // 永远捕获不到
            System.out.println("Bad encoding");
        }

        对于上面的代码，UnsupportedEncodingException异常是永远捕获不到的，因为它是IOException的子类。
        当抛出UnsupportedEncodingException异常时，会被catch (IOException e) { ... }捕获并执行。正确的写法是把子类放到前面。

        try {
            process1();
            process2();
            process3();
        } catch (UnsupportedEncodingException e) { // 永远捕获不到
            System.out.println("Bad encoding");
        } catch (IOException e) {
            System.out.println("IO error");
        }
         */
        System.out.println("存在多个catch的时候，catch的顺序非常重要：子类必须写在前面。");

        /*
        如果某些异常的处理逻辑相同，但是异常本身不存在继承关系，那么就得编写多条catch子句：
        try {
            process1();
            process2();
            process3();
        } catch (IOException e) {
            System.out.println("Bad input");
        } catch (NumberFormatException e) {
            System.out.println("Bad input");
        } catch (Exception e) {
            System.out.println("Unknown error");
        }

        因为处理IOException和NumberFormatException的代码是相同的，所以我们可以把它两用|合并到一起：
        try {
            process1();
            process2();
            process3();
        } catch (IOException | NumberFormatException e) {
            System.out.println("Bad input");
        } catch (Exception e) {
            System.out.println("Unknown error");
        }
         */
    }

    /**
     * finally语句
     * <p>
     * 无论是否有异常发生，如果我们都希望执行一些语句时，使用finally语句。
     */
    public static void finallyLearn() {
        /*
        try {
            process1();
            process2();
            process3();
            System.out.println("END");
        } catch (UnsupportedEncodingException e) {
            System.out.println("Bad encoding");
            System.out.println("END");
        } catch (IOException e) {
            System.out.println("IO error");
            System.out.println("END");
        }
        上述代码无论是否发生异常，都会执行System.out.println("END");这条语句。要消除这些重复的代码？就可以通过finally语句。

        try {
            process1();
            process2();
            process3();
        } catch (UnsupportedEncodingException e) {
            System.out.println("Bad encoding");
        } catch (IOException e) {
            System.out.println("IO error");
        } finally {
            System.out.println("END");
        }
        如果没有发生异常，就正常执行try { ... }语句块，然后执行finally。如果发生了异常，就中断执行try { ... }语句块，
        然后跳转执行匹配的catch语句块，最后执行finally。可见，finally是用来保证一些代码必须执行的。

        某些情况下，可以没有catch，只使用try ... finally结构。例如：
        void process(String file) throws IOException {
            try {
                ...
            } finally {
                System.out.println("END");
            }
        }
        因为方法声明了可能抛出的异常，所以可以不写catch。
         */
        String s = """
                注意finally有几个特点：
                1、finally语句不是必须的，可写可不写；
                2、finally总是最后执行。""";
        System.out.println(s);
    }

    /**
     * 异常的传播
     */
    public static void transmit() {
        // 当某个方法抛出了异常时，如果当前方法没有捕获异常，异常就会被抛到上层调用方法，直到遇到某个try...catch被捕获为止
        try {
            func1();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void func1() {
        func2();
    }
    private static void func2() {
        Integer.parseInt(null); // 会抛出NumberFormatException
    }

    /**
     * 抛出异常
     */
    public static void throwPractice() {
        /*
        如何抛出异常？
        当发生错误时，例如，用户输入了非法的字符，我们就可以抛出异常：
        void process2(String s) {
            if (s == null) {
                throw new NullPointerException();
            }
        }

        如果一个方法捕获了某个异常后，又在catch子句中抛出新的异常，就相当于把抛出的异常类型“转换”了：
        void process1(String s) {
            try {
                process2();
            } catch (NullPointerException e) {
                throw new IllegalArgumentException();
            }
        }
         */

        /*
        那么，执行这段代码，会抛出IllegalArgumentException异常。
        这说明新的异常丢失了原始异常信息，我们已经看不到原始异常NullPointerException的信息了。
         */
        try {
            thorwIllegalArgumentException();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void throwNullPointerException() {
        throw new NullPointerException();
    }

    /**
     * 如果我们在try或者catch语句块中抛出异常，finally语句是否会执行？
     */
    private static void thorwIllegalArgumentException() {
        try {
            throwNullPointerException();
        } catch (NullPointerException e) {
            System.out.println("catched");
//            throw new IllegalArgumentException();

            /*
            为了能追踪到完整的异常栈，将本行代码进行改造：
            在构造异常的时候，把原始的Exception实例传进去，新的Exception就可以持有原始Exception信息。
             */
            if (e.getCause() == null) {
                // 在代码中获取原始异常可以使用Throwable.getCause()方法。如果返回null，说明已经是“根异常”了。
                System.out.println(e.toString() + " 已经是根异常了");
            }
            throw new IllegalArgumentException(e);
            // ⚠️所以，捕获到异常并再次抛出时，一定要留住原始异常，否则很难定位第一案发现场！

        } finally {

            /*
            如果我们在try或者catch语句块中抛出异常，finally语句是否会执行？

            在catch中抛出异常，不会影响finally的执行。JVM会先执行finally，然后抛出异常。
             */
            System.out.println("finally END");
        }
    }

    /**
     * 异常屏蔽（Suppressed Exception）
     * <p>
     * 绝大多数情况下，在finally中不要抛出异常。因此，我们通常不需要关心Suppressed Exception
     */
    public static void suppressed() {
        // 如果在执行finally语句时抛出异常，那么，catch语句的异常还能否继续抛出？
//        try {
//            Integer.parseInt("abc");
//        } catch (Exception e) {
//            System.out.println("catched");
//            throw new RuntimeException(e);
//        } finally {
//            System.out.println("finally");
//            throw new IllegalArgumentException();
//        }
        /*
        执行上述代码发现，异常信息如下，只抛出了 IllegalArgumentException：
        catched
        finally
        Exception in thread "main" java.lang.IllegalArgumentException...

        这说明finally抛出异常后，原来在catch中准备抛出的异常就“消失”了，因为只能抛出一个异常。
        没有被抛出的异常称为“被屏蔽”的异常（Suppressed Exception）。
         */

        // 在极少数的情况下，我们需要获知所有的异常。如何保存所有的异常信息？方法是先用origin变量保存原始异常，
        // 然后调用Throwable.addSuppressed()，把原始异常添加进来，最后在finally抛出：
        try {
            addSuppressed(new IllegalArgumentException());
        } catch (Exception e) {
            // e.getSuppressed(); // 获取所有的Suppressed Exception
            e.printStackTrace();
        }
        // 当catch和finally都抛出了异常时，虽然catch的异常被屏蔽了，但是，finally抛出的异常仍然包含了它
        // 绝大多数情况下，在finally中不要抛出异常。因此，我们通常不需要关心Suppressed Exception。
    }
    private static void addSuppressed(Exception exception) throws Exception {
        Exception origin = null;
        try {
            Integer.parseInt("abc");
        } catch (Exception e) {
            System.out.println("catched");
            origin = e;
            throw e;
        } finally {
            System.out.println("finally");
            if (origin != null) {
                exception.addSuppressed(origin);
            }
            throw exception;
        }
    }

    /**
     * 自定义异常
     */
    public static void custom() {
        try {
            addSuppressed(new MyException());
        } catch (Exception e) {
            // e.getSuppressed(); // 获取所有的Suppressed Exception
            e.printStackTrace();
        }
    }
}