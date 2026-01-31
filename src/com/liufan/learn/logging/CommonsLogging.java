package com.liufan.learn.logging;

/*
Commons Logging是一个第三方日志库，它是由Apache创建的日志模块。
Commons Logging的特色是，它可以挂接不同的日志系统，并通过配置文件指定挂接的日志系统。默认情况下，
Commons Loggin自动搜索并使用Log4j（Log4j是另一个流行的日志系统），如果没有找到Log4j，再使用JDK Logging。

因为Commons Logging是一个第三方提供的库，所以先去官网下载：
https://commons.apache.org/proper/commons-logging/download_logging.cgi

下载后解压，找到commons-logging-1.3.5.jar这个文件，再把文件放到 libs 目录下。然后在 learn-java 目录下执行编译：
$ javac -d bin -cp libs/commons-logging-1.3.5.jar:src src/com/liufan/learn/Main.java

编译成功后执行：
$ java -cp bin:libs/commons-logging-1.3.5.jar com.liufan.learn.Main

如果需要清理编译生成的 bin 目录，可以使用以下命令：
$ rm -rf bin
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Commons Logging，是使用最广泛的一个第三方日志库，它是由Apache创建的日志模块。
 * <p>
 * Commons Logging定义了6个日志级别：
 * <ol>
 *     <li>FATAL</li>
 *     <li>ERROR</li>
 *     <li>WARNING</li>
 *     <li>INFO</li>
 *     <li>DEBUG</li>
 *     <li>TRACE</li>
 * </ol>
 * 默认级别是INFO，因此，INFO级别以下的日志，不会被打印出来。
 */
public final class CommonsLogging {

    public static final Log log = LogFactory.getLog(CommonsLogging.class);

    /**
     * 使用Commons Logging时，如果在静态方法中引用Log，通常直接定义一个静态类型变量
     */
    public static void practice() {
        /*
        使用Commons Logging只需要和两个类打交道，并且只有两步：
        1、通过LogFactory获取Log类的实例；
        2、使用Log实例的方法打日志。
         */
        log.fatal("fatal message");
        log.error("error message");
        log.warn("warn message");
        log.info("info message");
        log.debug("debug message");
        log.trace("trace message");

        Person p = new Person();
        p.foo();
        Student s = new Student();
        s.bar();

        // 此外，Commons Logging的日志方法，例如info()，除了标准的info(String)外，
        // 还提供了一个非常有用的重载方法：info(String, Throwable)，这使得记录异常更加简单：
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            log.info("got exception", e);
        }

        /*
        日志输出如下：

        1月 31, 2026 2:47:22 下午 com.liufan.learn.logging.CommonsLogging practice
        严重: fatal message
        1月 31, 2026 2:47:22 下午 com.liufan.learn.logging.CommonsLogging practice
        严重: error message
        1月 31, 2026 2:47:22 下午 com.liufan.learn.logging.CommonsLogging practice
        警告: warn message
        1月 31, 2026 2:47:22 下午 com.liufan.learn.logging.CommonsLogging practice
        信息: info message
        1月 31, 2026 2:47:22 下午 com.liufan.learn.logging.CommonsLogging$Person foo
        信息: foo
        1月 31, 2026 2:47:22 下午 com.liufan.learn.logging.CommonsLogging$Student bar
        信息: bar
        1月 31, 2026 2:47:22 下午 com.liufan.learn.logging.CommonsLogging practice
        信息: got exception
        java.lang.ArithmeticException: / by zero
            at com.liufan.learn.logging.CommonsLogging.practice(CommonsLogging.java:66)
            at com.liufan.learn.Main.main(Main.java:48)
         */
    }

    static class Person {
        /**
         * 实例变量log的获取方式是LogFactory.getLog(getClass())，虽然也可以用LogFactory.getLog(Person.class)，
         * 但是前一种方式有个非常大的好处，就是子类可以直接使用该log实例。
         */
        protected final Log log = LogFactory.getLog(getClass());
        /**
         * 在实例方法中引用Log，通常定义一个实例变量：
         */
        void foo() {
            log.info("foo");
        }
    }

    static class Student extends Person {
        /**
         * 由于Java类的动态特性，子类获取的log字段实际上相当于LogFactory.getLog(Student.class)，但却是从父类继承而来，并且无需改动代码。
         */
        void bar() {
            log.info("bar");
        }
    }
}
