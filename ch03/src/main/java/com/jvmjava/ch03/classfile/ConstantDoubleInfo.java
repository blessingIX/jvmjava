package com.jvmjava.ch03.classfile;

public class ConstantDoubleInfo extends ConstantInfo {

    double val;

    @Override
    public void readInfo(ClassReader reader) {
        this.val = reader.readUint64().toDouble();
    }

}
