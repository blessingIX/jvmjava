package com.jvmjava.ch03.classfile;

public class ConstantFloatInfo extends ConstantInfo {

    float val;

    @Override
    public void readInfo(ClassReader reader) {
        this.val = reader.readUint32().toFloat();
    }

}
