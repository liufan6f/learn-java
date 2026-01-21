package com.liufan.learn.oop.learninterface;

public class Student implements Person {
    /*
    default 方法，可以不用实现

    @Override
    public void run() {
        System.out.println("Student.run");
    }
    */

    @Override
    public void hello() {
        System.out.println("Student.hello");
    }

    @Override
    public String getName() {
        return "Xiao Ming";
    }
}
