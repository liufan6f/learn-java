package com.liufan.learn.coreclass;

/**
 * 记录类（record）
 * <p>
 * 和<code>enum</code>类似，我们自己不能直接从Record派生，只能通过<code>record</code>关键字由编译器实现继承。
 * @since Java 14开始提供的<code>record</code>关键字，可以非常方便地定义Data Class。
 * @see LearnEnum
 */
public record Point(int x, int y) {
    /**
     * 编译器默认按照record声明的变量顺序自动创建一个构造方法，并在方法内给字段赋值。
     * 如果我们要检查参数，可以在构造方法加上检查逻辑。
     */
    public Point {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException();
        }
    }
    /*
    public Point { ... } 被称为 Compact Constructor，它的目的是让我们编写检查逻辑，编译器最终生成的构造方法如下：

    public final class Point extends Record {
        public Point(int x, int y) {
            // 这是我们编写的Compact Constructor:
            if (x < 0 || y < 0) {
                throw new IllegalArgumentException();
            }
            // 这是编译器继续生成的赋值代码:
            this.x = x;
            this.y = y;
        }
        ...
    }
     */

    /**
     * 可以添加静态方法。一种常用的静态方法是of()方法，用来创建Point
     */
    public static Point of(int x, int y) {
        return new Point(x, y);
    }
}