package com.jvmjava.ch03.classpath;

public abstract class AbstractEntry implements Entry {

    byte[] data;

    public byte[] getData() {
        return this.data;
    }

}
