package com.liufan.learn.logging;

/*
前面介绍了Commons Logging，可以作为“日志接口”来使用。而真正的“日志实现”可以使用Log4j。它的架构大致如下：
log.info("User signed in.");
 │
 │   ┌──────────┐    ┌──────────┐    ┌──────────┐    ┌──────────┐
 ├──▶│ Appender │───▶│  Filter  │───▶│  Layout  │───▶│ Console  │
 │   └──────────┘    └──────────┘    └──────────┘    └──────────┘
 │
 │   ┌──────────┐    ┌──────────┐    ┌──────────┐    ┌──────────┐
 ├──▶│ Appender │───▶│  Filter  │───▶│  Layout  │───▶│   File   │
 │   └──────────┘    └──────────┘    └──────────┘    └──────────┘
 │
 │   ┌──────────┐    ┌──────────┐    ┌──────────┐    ┌──────────┐
 └──▶│ Appender │───▶│  Filter  │───▶│  Layout  │───▶│  Socket  │
     └──────────┘    └──────────┘    └──────────┘    └──────────┘
当我们使用Log4j输出一条日志时，Log4j自动通过不同的Appender把同一条日志输出到不同的目的地。例如：
1、console：输出到屏幕；
2、file：输出到文件；
3、socket：通过网络输出到远程计算机；
4、jdbc：输出到数据库。
在输出日志的过程中，通过Filter来过滤哪些log需要被输出，哪些log不需要被输出。例如，仅输出ERROR级别的日志。
最后，通过Layout来格式化日志信息，例如，自动添加日期、时间、方法名称等信息。
上述结构虽然复杂，但我们在实际使用的时候，并不需要关心Log4j的API，而是通过配置文件来配置它。

以XML配置为例，使用Log4j的时候，我们把一个log4j2.xml的文件放到classpath下就可以让Log4j读取配置文件并按照我们的配置来输出日志。
路径：src/log4j2.xml（Log4j需要配置文件在classpath根目录下才能自动加载）。
虽然配置Log4j比较繁琐，但一旦配置完成，使用起来就非常方便。对于 src/log4j2.xml 配置文件，凡是INFO级别的日志，会自动输出到屏幕，
而ERROR级别的日志，不但会输出到屏幕，还会同时输出到文件。并且，一旦日志文件达到指定大小（1MB），Log4j就会自动切割新的日志文件，并最多保留10份。

设置好配置文件后，去Log4j官网下载：https://logging.apache.org/log4j/2.x/download.html
下载后解压，把以下3个jar包放到 libs/log4j 目录下:
log4j-api-2.25.3.jar
log4j-core-2.25.3.jar
log4j-jcl-2.25.3.jar

因为Commons Logging会自动发现并使用Log4j，所以，不需要改动任何代码，就可以得到Log4j的日志输出。

在 learn-java 目录下执行编译：
javac -d bin -cp "libs/commons-logging-1.3.5.jar:libs/log4j/*:src" src/com/liufan/learn/Main.java

编译成功后执行：
java -cp "bin:libs/commons-logging-1.3.5.jar:libs/log4j/*:src" com.liufan.learn.Main

如果需要清理编译生成的 bin 目录，可以使用以下命令：
$ rm -rf bin
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Log4j，Commons Logging是日志接口，Log4j是真正日志实现。
 * <p>
 * ✅最佳实践：
 * <p>
 * 在开发阶段，始终使用Commons Logging接口来写入日志，并且开发阶段无需引入Log4j。如果需要把日志写入文件，
 * 只需要把正确的配置文件和Log4j相关的jar包放入classpath，就可以自动把日志切换成使用Log4j写入，无需修改任何代码。
 * @see CommonsLogging
 */
public final class Log4j {
    public static final Log log = LogFactory.getLog(Log4j.class);
    public static void practice() {
        log.info("Log4j info message");
        /*
        日志输出如下：

        01-31 15:55:04.886 [main] INFO
                    com.liufan.learn.logging.Log4j
        Log4j info message
         */
    }
}
