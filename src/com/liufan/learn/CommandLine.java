package com.liufan.learn;

/**
 * 命令行参数
 * <p>
 * Java 程序的入口是<code>main</code>方法，而 main 法可以接受一个命令行参数，它是一个 <code>String[]</code> 数组。
 * 这个命令行参数由 JVM 接收用户输入并传给 main 方法
 */
public class CommandLine {

    public static void practice(String[] args) {
        /*
        我们可以利用接收到的命令行参数，根据不同的参数执行不同的代码。例如，实现一个 -version 参数，打印程序版本号
        下面这个程序必须在命令行执行，我们先编译它，在 learn-java 目录下执行：
        $ javac -cp . -sourcepath src -d bin src/com/liufan/learn/Main.java

        然后，执行的时候，给它传递一个 -version 参数：
        $ java -cp bin com.liufan.learn.Main -version

        如果需要清理编译生成的 bin 目录，可以使用以下命令：
        $ rm -rf bin
         */
        for (String arg : args) {
            if ("-version".equals(arg)) {
                System.out.println("v 1.0");
            }
        }
    }
}
