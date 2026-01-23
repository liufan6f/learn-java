package com.liufan.learn.oop.learnstatic;

public class Task implements Priority {
    private int priority;

    public void setPriority(int priority) {
        switch (priority) {
            case HIGH, MEDIUM, LOW -> this.priority = priority;
            default -> throw new IllegalArgumentException("Invalid priority: " + priority);
        }
    }

    public int getPriority() {
        return priority;
    }
}
