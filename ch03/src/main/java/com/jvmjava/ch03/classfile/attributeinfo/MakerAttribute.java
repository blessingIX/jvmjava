package com.jvmjava.ch03.classfile.attributeinfo;

import com.jvmjava.ch03.classfile.ClassReader;

public abstract class MakerAttribute extends AttributeInfo {

    @Override
    public void readInfo(ClassReader reader) {
        // read nothing
    }

}
