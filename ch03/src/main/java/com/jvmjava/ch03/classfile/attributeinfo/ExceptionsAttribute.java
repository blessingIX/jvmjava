package com.jvmjava.ch03.classfile.attributeinfo;

import com.jvmjava.ch03.classfile.ClassReader;
import com.jvmjava.ch03.struct.Uint16;

public class ExceptionsAttribute extends AttributeInfo {

    Uint16[] exceptionIndexTable;

    @Override
    public void readInfo(ClassReader reader) {
        this.exceptionIndexTable = reader.readUint16s();
    }

    public Uint16[] exceptionIndexTable() {
        return this.exceptionIndexTable;
    }

}
