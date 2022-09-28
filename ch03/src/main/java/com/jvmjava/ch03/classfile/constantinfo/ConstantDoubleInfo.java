package com.jvmjava.ch03.classfile.constantinfo;

import com.jvmjava.ch03.classfile.ClassReader;

public class ConstantDoubleInfo extends ConstantInfo {

    double val;

    @Override
    public void readInfo(ClassReader reader) {
        this.val = reader.readUint64().toDouble();
    }

}
