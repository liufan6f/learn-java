package com.liufan.learn.generics.learnextends;

/**
 * 泛型继承
 */
public class IntPair extends MyPair<Integer> { // 一个类可以继承自一个泛型类，父类的类型是MyPair<Integer>
    public IntPair(Integer first, Integer last) {
        super(first, last);
    }
}
