package com.jvmjava.ch03.classfile;

public class LineNumberTableAttribute extends AttributeInfo {

    LineNumberTableEntry[] lineNumberTable;

    @Override
    public void readInfo(ClassReader reader) {
        int lineNumberTableLength = reader.readUint16().toInt();
        lineNumberTable = new LineNumberTableEntry[lineNumberTableLength];
        for (int i = 0; i < lineNumberTable.length; i++) {
            LineNumberTableEntry entry = new LineNumberTableEntry();
            entry.startPc = reader.readUint16();
            entry.lineNumber = reader.readUint16();
            lineNumberTable[i] = entry;
        }
    }

}
