package com.jvmjava.ch03.classfile.attributeinfo;

import com.jvmjava.ch03.classfile.ClassReader;
import com.jvmjava.ch03.struct.Uint16;

public class ConstantValueAttribute extends AttributeInfo {

    Uint16 constantValueIndex;

    @Override
    public void readInfo(ClassReader reader) {
        this.constantValueIndex = reader.readUint16();
    }

    public Uint16 constantValueIndex() {
        return this.constantValueIndex;
    }

}
