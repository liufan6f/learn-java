package com.liufan.learn.coreclass;

/**
 * StringBuilder
 * <p>
 * <code>StringBuilder</code>是可变对象，用来高效拼接字符串。
 * </p>
 * ⚠️注意：对于普通的字符串<code>+</code>操作，并不需要我们将其改写为<code>StringBuilder</code>，
 * 因为 Java 编译器在编译时就自动把多个连续的<code>+</code>操作编码为<code>StringConcatFactory</code>的操作。
 * 在运行期，<code>StringConcatFactory</code>会自动把字符串连接操作优化为数组复制或者<code>StringBuilder</code>操作。
 */
public class LearnStringBuilder extends CoreString {
    public static void practice() {
        /*
        Java 编译器对 String 做了特殊处理，使得我们可以直接用 + 拼接字符串：

        String s = "";
        for (int i=0; i<1000; i++) {
            s = s + "," + i;
        }

        虽然可以直接拼接字符串，但是，在循环中，每次循环都会创建新的字符串对象，然后扔掉旧的字符串。这样，
        绝大部分字符串都是临时对象，不但浪费内存，还会影响 GC 效率。

        为了能高效拼接字符串，Java 标准库提供了 StringBuilder，它是一个可变对象，可以预分配缓冲区，这样，
        往 StringBuilder 中新增字符时，不会创建新的临时对象：
         */
        StringBuilder sb = new StringBuilder(1024);
        for (int i = 0; i < 1000; i++) {
//            sb.append(',');
//            sb.append(i);
            sb.append(',').append(i); // StringBuilder 还可以进行链式操作
        }
        String s = sb.toString();
        System.out.println(s);

        // 仿照链式操作
        LearnStringBuilder lsb = new LearnStringBuilder();
        Adder adder = lsb.new Adder();
        adder.add(3).add(5).inc().add(10);
        System.out.println(adder.value());

        /*
        你可能还听说过 StringBuffer，这是 Java 早期的一个 StringBuilder 的线程安全版本，
        它通过同步来保证多个线程操作 StringBuffer 也是安全的，但是同步会带来执行速度的下降。

        StringBuilder 和 StringBuffer 接口完全相同，现在完全没有必要使用 StringBuffer。
         */
    }

    class Adder {
        private int sum = 0;

        Adder add(int n) {
            sum += n;
            return this;
        }
        Adder inc() {
            sum ++;
            return this;
        }
        int value() {
            return sum;
        }
    }
}