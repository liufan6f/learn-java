package com.liufan.learn.generics;

/**
 * 多个泛型类型
 * <p>
 * 泛型还可以定义多种类型。例如，我们希望Pair不总是存储两个类型一样的对象，就可以使用类型<code>&lt;T, K&gt;</code>。
 * <p>
 * Java标准库的<code>Map&lt;K, V&gt;</code>就是使用两种泛型类型的例子。它对Key使用一种类型，对Value使用另一种类型。
 */
public class NewPair<T, K> {
    private T first;
    private K last;
    public NewPair(T first, K last) {
        this.first = first;
        this.last = last;
    }
    public T getFirst() {
        return first;
    }
    public K getLast() {
        return last;
    }
}
