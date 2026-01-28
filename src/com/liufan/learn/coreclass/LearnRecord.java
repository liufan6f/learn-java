package com.liufan.learn.coreclass;

/**
 * 记录类
 * @since Java 14
 * @see Point
 */
public class LearnRecord extends com.liufan.learn.classtype.LearnClass {
    public static void practice() {
        /*
        使用String、Integer等类型的时候，这些类型都是不变类，一个不变类具有以下特点：
        1、定义class时使用final，无法派生子类；
        2、每个字段使用final，保证创建实例后无法修改任何字段。

        假设我们希望定义一个Point类，有x、y两个变量，同时它是一个不变类，可以像下面这么写。
        从Java 14开始，引入了新的Record类。我们定义Record类时，使用关键字record。把上述Point类改写为Record类。

        除了用final修饰class以及每个字段外，编译器还自动为我们创建了构造方法，和字段名同名的方法，以及覆写toString()、equals()和hashCode()方法。
        换句话说，使用record关键字，可以一行写出一个不变类。
         */
        Point p = Point.of(123, 456); // new Point(123, 456);
        System.out.println(p.x());
        System.out.println(p.y());
        System.out.println(p.toString());
    }
}

/*
final class Point {
    private final int x;
    private final int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int x() {
        return this.x;
    }

    int y() {
        return this.y;
    }

    @Override
    public String toString() {
        return String.format("Point(%d, %d)", this.x, this.y);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // 检查内存地址是否相同
        if (obj instanceof Point p) {
            return this.x == p.x && this.y == p.y;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }
}
*/