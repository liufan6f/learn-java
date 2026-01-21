package com.liufan.learn.oop.learnpackage.scope;

/**
 * 作用域（访问权限）修饰符
 * <ul>
 *     <li>public 修饰的 class、interface 可以被其他类访问；public 修饰的 field、method，可以被其他类访问，前提是首先有访问 class 的权限；</li>
 *     <li>protected 作用于继承关系，它修饰的 field、method 可以被子类访问，以及子类的子类；</li>
 *     <li>private 修饰的 field、method 访问权限被限定在 class 的内部（包括嵌套类）；</li>
 *     <li>package 前往 <code>com.liufan.learn.oop.learnpackage.Person</code> 查看。</li>
 * </ul>
 * ⚠️最佳实践：
 * 如果不确定是否需要 public，就不声明为 public，即尽可能少地暴露对外的字段和方法。
 * @see com.liufan.learn.oop.learnpackage.Person
 * @see #hi(String) final 修饰符不是访问权限，它可以修饰 class、field、method 和局部变量
 */
public class Hello {

    /**
     * final 修饰的局部变量（常量）可以阻止被重新赋值
     * @see com.liufan.learn.LearnVar#finalPractice() final 修饰的局部变量（常量）可以阻止被重新赋值
     * @see com.liufan.learn.oop.learnextends.Rect final 修饰 class 可以阻止被继承
     * @see com.liufan.learn.oop.polymorphic.Student#run(String) final 修饰 method 可以阻止被子类覆写
     * @see com.liufan.learn.oop.polymorphic.Student#position final 修饰 field 可以阻止被重新赋值
     */
    public static void hi(final String name) {
        // name = ""; // error!
        hello(name);
    }

    protected static void bye() {
        System.out.println("Bye!");
    }

    private static void hello(String name) {
        System.out.println(name + " 说：Hello!");
    }

    public static class Inner {
        public static void hi() {
            Hello.hello("Hello nested class Inner"); // 内部类可以访问 private 方法
        }
    }
}
