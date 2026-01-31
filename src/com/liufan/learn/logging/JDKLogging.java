package com.liufan.learn.logging;

/*
在编写程序的过程中，发现程序运行结果与预期不符，怎么办？当然是用System.out.println()打印出执行过程中的某些变量，
观察每一步的结果与代码逻辑是否符合，然后有针对性地修改代码。

代码改好了怎么办？当然是删除没有用的System.out.println()语句了。如果改代码又改出问题怎么办？再加上System.out.println()。
反复这么搞几次，很快大家就发现使用System.out.println()非常麻烦。怎么办？解决方法是使用日志。

那什么是日志？日志就是Logging，它的目的是为了取代System.out.println()。输出日志，而不是用System.out.println()，有以下几个好处：
1、可以设置输出样式，避免自己每次都写"ERROR: " + var；
2、可以设置输出级别，禁止某些级别输出。例如，只输出错误日志；
3、可以被重定向到文件，这样可以在程序运行结束后查看日志；
4、可以按包名控制日志级别，只输出某些包打的日志；
……
 */

import java.util.logging.Logger;

/**
 * <code>java.util.logging.Logger</code>
 * <p>
 * JDK的Logging定义了7个日志级别，从严重到普通：
 * <ol>
 *     <li>SEVERE</li>
 *     <li>WARNING</li>
 *     <li>INFO</li>
 *     <li>CONFIG</li>
 *     <li>FINE</li>
 *     <li>FINER</li>
 *     <li>FINEST</li>
 * </ol>
 * 默认级别是INFO，因此，INFO级别以下的日志，不会被打印出来。
 */
public final class JDKLogging {
    public static void practice() {
        Logger logger = Logger.getGlobal();
        logger.severe("severe message");
        logger.warning("warning message");
        logger.info("info message");
        // 执行代码发现，只打印了logger.severe()、logger.warning()、logger.info()
        logger.config("config message");
        logger.fine("fine message");
        logger.finer("finer message");
        logger.finest("finest message");
        /*
        使用Java标准库内置的Logging有以下局限：
        Logging系统在JVM启动时读取配置文件并完成初始化，一旦开始运行main()方法，就无法修改配置；
        配置不太方便，需要在JVM启动时传递参数-Djava.util.logging.config.file=<config-file-name>。
        因此，Java标准库内置的Logging使用并不是非常广泛。
         */
    }
}
