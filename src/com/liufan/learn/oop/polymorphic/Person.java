package com.liufan.learn.oop.polymorphic;

/**
 * 覆写 Object 方法
 * <p>
 * 所有 class 最终都继承自 Object，而 Object 定义了几个重要的方法：
 * <ul>
 *     <li><code>toString()</code>：把 instance 输出为 String；</li>
 *     <li><code>equals(Object)</code>：判断两个 instance 是否逻辑相等；</li>
 *     <li><code>hashCode()</code>：计算一个 instance 的哈希值。</li>
 * </ul>
 */
public class Person {
    protected String name;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person:name=" + name;
    }

    @Override
    public boolean equals(Object obj) {
        // 当且仅当 obj 为 Person 类型
        if (obj instanceof Person p) {
            // 并且 name 字段相同时，返回 true
            return this.name.equals(p.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    public void run() {
        System.out.println("Person.run");
    }

    public String hello() {
        return "Hello, " + name;
    }
}
