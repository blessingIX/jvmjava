package com.jvmjava.ch03.classfile.constantinfo;

import com.jvmjava.ch03.classfile.ClassReader;
import com.jvmjava.ch03.struct.Uint16;

public class ConstantNameAndTypeInfo extends ConstantInfo {

    Uint16 nameIndex;

    Uint16 descriptorIndex;

    @Override
    public void readInfo(ClassReader reader) {
        this.nameIndex = reader.readUint16();
        this.descriptorIndex = reader.readUint16();
    }

}
