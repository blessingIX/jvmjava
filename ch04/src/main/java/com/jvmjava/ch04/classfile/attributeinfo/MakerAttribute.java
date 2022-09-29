package com.jvmjava.ch04.classfile.attributeinfo;

import com.jvmjava.ch04.classfile.ClassReader;

public abstract class MakerAttribute extends AttributeInfo {

    @Override
    public void readInfo(ClassReader reader) {
        // read nothing
    }

}
