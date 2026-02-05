package com.liufan.learn.generics.learnextends;

/**
 * 使用extends限定T类型
 */
public class ExtendsTPair<T extends Number> {
    private T first;
    private T last;

    public ExtendsTPair(T first, T last) {
        this.first = first;
        this.last = last;
    }

    public T getFirst() {
        return first;
    }
    public T getLast() {
        return last;
    }
}
