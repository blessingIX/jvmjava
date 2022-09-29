package com.jvmjava.ch04.classfile.constantinfo;

import com.jvmjava.ch04.classfile.ClassReader;

public class ConstantIntegerInfo extends ConstantInfo {

    int val;

    @Override
    public void readInfo(ClassReader reader) {
        this.val = reader.readUint32().toInt();
    }

}
