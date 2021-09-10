package com.zhoukaifan.jvm.memory;

import lombok.Getter;

@Getter
public class ThreadData {
    private Counter counter;
    private Stack stack;

    public ThreadData() {
        this.counter = new Counter();
        this.stack = new Stack();
    }
}
