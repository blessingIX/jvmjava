package com.jvmjava.ch04.classfile.attributeinfo;

import com.jvmjava.ch04.classfile.ClassReader;
import com.jvmjava.ch04.struct.Uint32;

public class UnparsedAttribute extends AttributeInfo {

    String name;

    Uint32 length;

    byte[] info;

    public UnparsedAttribute(String name, Uint32 length, byte[] info) {
        this.name = name;
        this.length = length;
        this.info = info;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.info = reader.readBytes(this.length.toInt());
    }

}
