package com.liufan.learn.logging;

/*
SLF4J类似于Commons Logging，也是一个日志接口，而Logback类似于Log4j，是一个日志的实现。

因为Java有着非常悠久的开源历史，不但OpenJDK本身是开源的，而且我们用到的第三方库，几乎全部都是开源的。开源生态丰富的一个特定就是，
同一个功能，可以找到若干种互相竞争的开源库。因为对Commons Logging的接口不满意，有人就搞了SLF4J。因为对Log4j的性能不满意，有人就搞了Logback。
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liufan.learn.oop.method.Person;

/**
 * SLF4J
 * <p>
 * SLF4J类似于Commons Logging，也是一个日志接口，而Logback类似于Log4j，是一个日志的实现。他们的使用方式也几乎一模一样。
 * @see CommonsLogging
 */
public final class SLF4J {
    public static final Log log = LogFactory.getLog(SLF4J.class);
    public static final Logger logger = LoggerFactory.getLogger(SLF4J.class);
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
