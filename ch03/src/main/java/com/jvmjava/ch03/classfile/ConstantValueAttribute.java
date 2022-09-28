package com.jvmjava.ch03.classfile;

import com.jvmjava.ch03.uint.Uint16;

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
