package com.liufan.learn.coreclass;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

/**
 * JavaBean
 * <p>
 * JavaBean 是一种符合命名规范的 class，它通过 getter 和 setter 来定义属性。
 */
public class LearnJavaBean {
    public static void learn() {
        /*
        在 Java 中，有很多 class 的定义都符合这样的规范：
        - 若干 private 实例字段；
        - 通过 public 方法来读写实例字段。

        那么这种 class 被称为 JavaBean，如 Person 类：
        字段名是 xyz，那么读写方法名分别以 get 和 set 开头，并且后接大写字母开头的字段名 Xyz，
        因此两个读写方法名分别是 getXyz() 和 setXyz()。

        boolean 字段比较特殊，它的读方法一般命名为 isXyz()：
        public boolean isChild()            // 读方法
        public void setChild(boolean value) // 写方法

        我们通常把一组对应的读方法（getter）和写方法（setter）称为属性（property），属性是一种通用的叫法，并非 Java 语法规定；
        只有读方法（getter）的属性称为只读属性（read-only）；只有写方法（setter）的属性称为只写属性（write-only）。
        只读属性很常见，只写属性不常见。属性只需要定义 getter 和 setter 方法，不一定需要对应的字段，如 Person 的 child 只读属性。

        可以看出，getter 和 setter 也是一种数据封装的方法。那么 JavaBean 的作用是什么？
        JavaBean 主要用来传递数据，即把一组数据组合成一个 JavaBean 便于传输。此外，JavaBean 可以方便地被 IDE 工具分析，
        生成读写属性的代码，主要用在图形界面的可视化设计中。
         */
        System.out.println("常把一组对应的读方法（getter）和写方法（setter）称为属性（property）");
    }

    class Person {
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public boolean isChild() {
            return age <= 6;
        }
    }

    /**
     * 获取 JavaBean 属性列表
     */
    public static void enumProperty() {
        try {
            // 要枚举一个 JavaBean 的所有属性，可以直接使用 Java 核心库提供的 Introspector
            BeanInfo info = Introspector.getBeanInfo(Person.class);
            for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
                System.out.println(pd.getName());
                System.out.println("  " + pd.getReadMethod());
                System.out.println("  " + pd.getWriteMethod());
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        // 运行上述代码，可以列出所有的属性，以及对应的读写方法。注意 class 属性是从 Object 继承的 getClass() 方法带来的。
    }
}

