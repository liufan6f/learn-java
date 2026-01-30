package com.liufan.learn.exception;

/*
断言（Assertion）是一种调试程序的方式。在Java中，使用assert关键字来实现断言。

Java断言的特点是：断言失败时会抛出AssertionError，导致程序结束退出。因此，断言不能用于可恢复的程序错误，只应该用于开发和测试阶段。
对于可恢复的程序错误，不应该使用断言。例如：
void sort(int[] arr) {
    assert arr != null;
}

应该抛出异常并在上层捕获：
void sort(int[] arr) {
    if (arr == null) {
        throw new IllegalArgumentException("array cannot be null");
    }
}
 */

/**
 * 断言（Assertion）
 * <p>
 * Java断言的特点是：断言失败时会抛出AssertionError，导致程序结束退出。因此，断言不能用于可恢复的程序错误，只应该用于开发和测试阶段。
 */
public class LearnAssertion {
    public static void practice() {
        double x = -123.45;
        double y = Math.abs(x);
        assert y >= 0; // 语句assert x >= 0; 即为断言，断言条件x >= 0预期为true。如果计算结果为false，则断言失败，抛出AssertionError。
        assert x >= 0 : "x must >= 0"; // 使用assert语句时，还可以添加一个可选的断言消息。这样，断言失败的时候，AssertionError会带上消息x must >= 0，更加便于调试。
        System.out.println(y);

        /*
        断言x必须>=0，实际上x为-123.45，断言肯定失败。执行上述代码，发现程序并未抛出AssertionError，而是正常打印了y的值。

        这是因为JVM默认关闭断言指令，即遇到assert语句就自动忽略了，不执行。
        要执行assert语句，必须给Java虚拟机传递-enableassertions（可简写为-ea）参数启用断言。所以，上述程序必须在命令行下运行才有效果：
         */
    }
}
