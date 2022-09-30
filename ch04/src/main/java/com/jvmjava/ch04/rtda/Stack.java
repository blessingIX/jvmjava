package com.jvmjava.ch04.rtda;

public class Stack {

    int maxSize;

    int size;

    Frame top;

    public Stack(int maxSize) {
        this.maxSize = maxSize;
    }

    public void push(Frame frame) {
        if (this.size >= this.maxSize) {
            throw new RuntimeException("java.lang.StackOverflowError");
        }
        if (this.top != null) {
            frame.lower = this.top;
        }

        this.top = frame;
        this.size++;
    }

    public Frame pop() {
        if (this.top == null) {
            throw new RuntimeException("jvm stack is empty!");
        }
        Frame top = this.top;
        this.top = top.lower;
        top.lower = null;
        this.size--;
        return top;
    }

    public Frame top() {
        if (this.top == null) {
            throw new RuntimeException("jvm stack is empty!");
        }
        return this.top;
    }

}
