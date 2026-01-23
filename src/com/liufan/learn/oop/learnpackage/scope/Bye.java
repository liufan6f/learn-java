package com.liufan.learn.oop.learnpackage.scope;

public class Bye extends Hello {

    public static void byeBye() {
        bye(); // 可以访问 protected 方法
    }
}
