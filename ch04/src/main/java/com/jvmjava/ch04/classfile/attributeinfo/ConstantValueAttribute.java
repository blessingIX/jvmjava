package com.jvmjava.ch04.classfile.attributeinfo;

import com.jvmjava.ch04.classfile.ClassReader;
import com.jvmjava.ch04.struct.Uint16;

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
