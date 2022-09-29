package com.jvmjava.ch04.classfile.constantinfo;

import com.jvmjava.ch04.classfile.ClassReader;

public class ConstantLongInfo extends ConstantInfo {

    long val;

    @Override
    public void readInfo(ClassReader reader) {
        this.val = reader.readUint64().toLong();
    }

}
