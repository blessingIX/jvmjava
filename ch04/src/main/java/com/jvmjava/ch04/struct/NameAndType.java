package com.jvmjava.ch04.struct;

public class NameAndType {

    String name;

    String type;

    public NameAndType(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String name() {
        return this.name;
    }

    public String type() {
        return this.type;
    }

}
