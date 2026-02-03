package com.liufan.learn.annotation;

/**
 * 子类默认也定义了该注解
 */
@InheritedReport(type = 1)
public class Person {
    @Range(min=1, max=20)
    public String name;

    @Range(max=10)
    public String city;
}
