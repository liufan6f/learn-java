package com.liufan.learn.oop.method;

/**
 * Person 类
 */
public class Person {

    private String name;

    private int age;

    public String getName() {
        return name; // 省略 this
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("invalid name value");
        }
        // 有局部变量和字段重名，那么局部变量优先级更高，就必须加上 this
        this.name = name.strip(); // 去掉首尾空格
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0 || age > 200) {
            throw new IllegalArgumentException("invalid age value");
        }
        this.age = age;
    }

    public int getBirthYear() {
        return calculateBirthYear(2026);
    }

    /**
     * 计算出生年份
     */
    private int calculateBirthYear(int currentYear) {
        return currentYear - age + 1;
    }
}
