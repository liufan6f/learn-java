package com.liufan.learn.oop.learninterface;

/*
在抽象类中，抽象方法本质上是定义接口规范：即规定高层类的接口，从而保证所有子类都有相同的接口实现，这样，多态就能发挥出威力。
如果一个抽象类没有字段，所有方法全部都是抽象方法：
abstract class Person {
    public abstract void run();
    public abstract String getName();
}

就可以把该抽象类改写为接口：interface。
所谓 interface，就是比抽象类还要抽象的纯抽象接口，因为它连字段都不能有。因为接口定义的所有方法默认都是 public abstract 的，
所以这两个修饰符不需要写出来（写不写效果都一样）。

抽象类和接口的对比如下：
                              abstract class	       interface
                    继承	      只能 extends 一个 class   可以 implements 多 个interface
                    字段	      可以定义实例字段	           不能定义实例字段
                    抽象方法	  可以定义抽象方法	           可以定义抽象方法
                    非抽象方法  可以定义非抽象方法	       可以定义 default 方法

合理设计 interface 和 abstract class 的继承关系，可以充分复用代码。一般来说，公共逻辑适合放在 abstract class 中，
具体逻辑放到各个子类，而接口层次代表抽象程度。可以参考 Java 的集合类定义的一组接口、抽象类以及具体子类的继承关系：
                    ┌───────────────┐
                    │   Iterable    │
                    └───────────────┘
                            ▲                ┌───────────────────┐
                            │                │      Object       │
                    ┌───────────────┐        └───────────────────┘
                    │  Collection   │                  ▲
                    └───────────────┘                  │
                            ▲     ▲          ┌───────────────────┐
                            │     └──────────│AbstractCollection │
                    ┌───────────────┐        └───────────────────┘
                    │     List      │                  ▲
                    └───────────────┘                  │
                                  ▲          ┌───────────────────┐
                                  └──────────│   AbstractList    │
                                             └───────────────────┘
                                                    ▲     ▲
                                                    │     │
                                                    │     │
                                         ┌────────────┐ ┌────────────┐
                                         │ ArrayList  │ │ LinkedList │
                                         └────────────┘ └────────────┘
在使用的时候，实例化的对象永远只能是某个具体的子类，但总是通过接口去引用它，因为接口比抽象类更抽象：
List list = new ArrayList(); // 用List接口引用具体子类的实例
Collection coll = list;      // 向上转型为Collection接口
Iterable it = coll;          // 向上转型为Iterable接口

何时使用抽象类 vs 接口：
                      使用抽象类当：   使用接口当：
             需要在相关类之间共享代码    需要定义不相关的类的契约
                需要有状态的默认实现    需要多重继承的行为
需要控制子类的访问权限（protected方法）   API 需要在未来扩展而不破坏现有实现
 */

/**
 * 接口（interface）
 * <ul>
 *     <li>interface 是比抽象类还要抽象的纯抽象接口，所以不能有字段；</li>
 *     <li>所有方法默认都是 public abstract 的；</li>
 *     <li>interface 可以继承自 interface，相当于扩展了接口的方法；</li>
 *     <li>在 Java 中，一个类只能继承自另一个类。但是，一个类可以实现多个 interface；</li>
 *     <li>在接口中，可以定义 default 方法。</li>
 * </ul>
 * @see com.liufan.learn.oop.abstractclass.Person
 * @see com.liufan.learn.oop.abstractclassinterface.EmployeePayment
 */
public interface Person extends Hello {
    String getName();

    /**
     * default 方法，实现类可以不必覆写 default 方法。
     * <p>
     * default 方法的目的是，当我们需要给接口新增一个方法时，会涉及到修改全部子类。如果新增的是 default 方法，
     * 那么子类就不必全部修改，只需要在需要覆写的地方去覆写新增方法。
     * <p>
     * ⚠️注意 default 方法和抽象类的普通方法是有所不同的。因为 interface 没有字段，default 方法无法访问字段，
     * 而抽象类的普通方法可以访问实例字段。
     */
    default void run() {
        System.out.println(getName() + " run");
    }
}
