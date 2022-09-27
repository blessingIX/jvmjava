package com.jvmjava.ch03.classfile;

public class ConstantIntegerInfo extends ConstantInfo {

    int val;

    @Override
    public void readInfo(ClassReader reader) {
        this.val = reader.readUint32().toInt();
    }

}
