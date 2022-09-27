package com.jvmjava.ch02.classpath;

import java.util.Arrays;

public abstract class AbstractEntry implements Entry {

    byte[] data;

    public byte[] getData() {
        return this.data;
    }

    @Override
    public String toString() {
        return "AbstractEntry{" +
                "data=" + Arrays.toString(data) +
                '}';
    }
}
