package com.jvmjava.ch04.classfile.constantinfo;

import com.jvmjava.ch04.classfile.ClassReader;

public class ConstantFloatInfo extends ConstantInfo {

    float val;

    @Override
    public void readInfo(ClassReader reader) {
        this.val = reader.readUint32().toFloat();
    }

}
