package com.jvmjava.ch03.classfile;

public class ConstantLongInfo extends ConstantInfo {

    long val;

    @Override
    public void readInfo(ClassReader reader) {
        this.val = reader.readUint64().toLong();
    }

}
