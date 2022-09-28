package com.jvmjava.ch03.classfile;

import com.jvmjava.ch03.uint.Uint16;
import com.jvmjava.ch03.uint.Uint32;

public abstract class AttributeInfo {

    public static final String Code = "Code";
    public static final String ConstantValue = "ConstantValue";
    public static final String Deprecated = "Deprecated";
    public static final String Exceptions = "Exceptions";
    public static final String LineNumberTable = "LineNumberTable";
    public static final String LocalVariableTable = "LocalVariableTable";
    public static final String SourceFile = "SourceFile";
    public static final String Synthetic = "Synthetic";

    public abstract void readInfo(ClassReader reader);

    AttributeInfo[] readAttributes(ClassReader reader, ConstantPool cp) {
        int attributeCount = reader.readUint16().toInt();
        AttributeInfo[] attributes = new AttributeInfo[attributeCount];
        for (int i = 0; i < attributes.length; i++) {
            attributes[i] = readAttribute(reader, cp);
        }
        return attributes;
    }

    public AttributeInfo readAttribute(ClassReader reader, ConstantPool cp) {
        Uint16 attrNameIndex = reader.readUint16();
        String attrName = cp.getUtf8(attrNameIndex);
        Uint32 attrLen = reader.readUint32();
        return newAttributeInfo(attrName, attrLen, cp);
    }

    public static AttributeInfo newAttributeInfo(String attrName, Uint32 attrLen, ConstantPool cp) {
        switch (attrName) {
        }
        return null;
    }

}
