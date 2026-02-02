package com.liufan.learn.logging;

/*
SLF4J类似于Commons Logging，也是一个日志接口，而Logback类似于Log4j，是一个日志的实现。

因为Java有着非常悠久的开源历史，不但OpenJDK本身是开源的，而且我们用到的第三方库，几乎全部都是开源的。开源生态丰富的一个特定就是，
同一个功能，可以找到若干种互相竞争的开源库。因为对Commons Logging的接口不满意，有人就搞了SLF4J。因为对Log4j的性能不满意，有人就搞了Logback。

因为Log4j和Logback是第三方提供的库，所以先去官网下载：
https://www.slf4j.org/download.html
https://logback.qos.ch/download.html

下载后解压，然后把以下jar包放到放到 libs 目录下：
libs/slf4j-api-2.0.9.jar
libs/logback/logback-classic-1.5.9.jar
libs/logback/logback-core-1.5.9.jar

和Log4j类似，我们仍然需要一个Logback的配置文件，把logback.xml放到classpath下，配置如下：
路径：src/logback.xml

在 learn-java 目录下执行编译：
$ javac -d bin -cp "libs/commons-logging-1.3.5.jar:libs/slf4j-api-2.0.9.jar:libs/log4j/*:libs/logback/*:src" src/com/liufan/learn/Main.java

编译成功后执行：
$ java -cp "bin:libs/commons-logging-1.3.5.jar:libs/slf4j-api-2.0.9.jar:libs/log4j/*:libs/logback/*:src" com.liufan.learn.Main

如果需要清理编译生成的 bin 目录，可以使用以下命令：
$ rm -rf bin
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liufan.learn.oop.method.Person;

/**
 * SLF4J是日志接口，Logback是真正日志实现
 * <p>
 * SLF4J类似于Commons Logging，也是一个日志接口，而Logback类似于Log4j，是一个日志的实现。他们的使用方式也几乎一模一样。
 * <p>
 * 从目前的趋势来看，越来越多的开源项目从Commons Logging加Log4j转向了SLF4J加Logback。
 * @see CommonsLogging
 * @see Log4j
 */
public final class SLF4J {
    private static final Log log = LogFactory.getLog(SLF4J.class);
    private static final Logger logger = LoggerFactory.getLogger(SLF4J.class);
    public static void practice() {
        Person p = new Person();
        int age = 18;
        p.setAge(age);
        p.setName("Ming");

        // SLF4J对Commons Logging的接口有何改进？
        // 在Commons Logging中，我们要打印日志，有时候得这么写：
        log.info("Set age " + age + " for Person " + p.getName() + " ok.");
        // 拼字符串是一个非常麻烦的事情，所以SLF4J的日志接口改进成这样了：
        logger.info("Set age {} for Person {} ok.", age, p.getName());
    }
}
