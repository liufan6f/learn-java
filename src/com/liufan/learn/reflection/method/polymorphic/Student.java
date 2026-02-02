package com.liufan.learn.reflection.method.polymorphic;

public class Student extends Person {
    @Override
    public void hello() {
        System.out.println("Student:hello");
    }
}
