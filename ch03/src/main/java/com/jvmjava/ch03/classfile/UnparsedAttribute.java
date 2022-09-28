package com.jvmjava.ch03.classfile;

import com.jvmjava.ch03.uint.Uint32;

public class UnparsedAttribute extends AttributeInfo {

    String name;

    Uint32 length;

    byte[] info;

    public UnparsedAttribute(String name, Uint32 length) {
        this.name = name;
        this.length = length;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.info = reader.readBytes(this.length.toInt());
    }

}
