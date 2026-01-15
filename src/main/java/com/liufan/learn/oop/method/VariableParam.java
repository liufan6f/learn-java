package com.liufan.learn.oop.method;

public class VariableParam {

    private String[] names;

    public String[] getNames() {
        return names;
    }

    /**
     * 设置名称数组
     * @param names 可变参数相当于数组类型参数
     */
    public void setNames(String... names) {
        this.names = names;
    }
}
