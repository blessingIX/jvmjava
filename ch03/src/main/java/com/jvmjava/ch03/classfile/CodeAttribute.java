package com.jvmjava.ch03.classfile;

import com.jvmjava.ch03.uint.Uint16;

public class CodeAttribute extends AttributeInfo {

    ConstantPool cp;

    Uint16 maxStack;

    Uint16 maxLocals;

    byte[] code;

    ExceptionTableEntry[] exceptionTable;

    AttributeInfo[] attributes;

    public CodeAttribute(ConstantPool cp) {
        this.cp = cp;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.maxStack = reader.readUint16();
        this.maxLocals = reader.readUint16();
        int codeLength = reader.readUint32().toInt();
        this.code = reader.readBytes(codeLength);
        this.exceptionTable = readExceptionTable(reader);
        this.attributes = readAttributes(reader, this.cp);
    }

    private ExceptionTableEntry[] readExceptionTable(ClassReader reader) {
        int exceptionTableLength = reader.readUint16().toInt();
        ExceptionTableEntry[] exceptionTable = new ExceptionTableEntry[exceptionTableLength];
        for (int i = 0; i < exceptionTable.length; i++) {
            ExceptionTableEntry entry = new ExceptionTableEntry();
            entry.startPc = reader.readUint16();
            entry.endPc = reader.readUint16();
            entry.handlePc = reader.readUint16();
            entry.catchType = reader.readUint16();
            exceptionTable[i] = new ExceptionTableEntry();
        }
        return exceptionTable;
    }

}
