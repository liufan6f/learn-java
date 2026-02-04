package com.liufan.learn.generics;

public class Person implements Comparable<Person> {
    private String name;
    private int score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Person(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String toString() {
        return this.name + "," + this.score;
    }

    @Override
    public int compareTo(Person o) {
        // return this.name.compareTo(o.name); // 按名称排序
        return -(this.score - o.score); // 按成绩从高到低排序
    }
}
