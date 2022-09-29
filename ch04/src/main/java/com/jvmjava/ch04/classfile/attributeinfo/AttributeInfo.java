package com.jvmjava.ch04.classfile.attributeinfo;

import com.jvmjava.ch04.classfile.ClassReader;
import com.jvmjava.ch04.classfile.constantinfo.ConstantPool;
import com.jvmjava.ch04.struct.Uint16;
import com.jvmjava.ch04.struct.Uint32;

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

    public static AttributeInfo[] readAttributes(ClassReader reader, ConstantPool cp) {
        int attributeCount = reader.readUint16().toInt();
        AttributeInfo[] attributes = new AttributeInfo[attributeCount];
        for (int i = 0; i < attributes.length; i++) {
            attributes[i] = readAttribute(reader, cp);
        }
        return attributes;
    }

    public static AttributeInfo readAttribute(ClassReader reader, ConstantPool cp) {
        Uint16 attrNameIndex = reader.readUint16();
        String attrName = cp.getUtf8(attrNameIndex);
        Uint32 attrLen = reader.readUint32();
        AttributeInfo attributeInfo = newAttributeInfo(attrName, attrLen, cp);
        attributeInfo.readInfo(reader);
        return attributeInfo;
    }

    public static AttributeInfo newAttributeInfo(String attrName, Uint32 attrLen, ConstantPool cp) {
        switch (attrName) {
            case Code:
                return new CodeAttribute(cp);
            case ConstantValue:
                return new ConstantValueAttribute();
            case Deprecated:
                return new DeprecatedAttribute();
            case Exceptions:
                return new ExceptionsAttribute();
            case LineNumberTable:
                return new LineNumberTableAttribute();
            case LocalVariableTable:
                return new LocalVariableTableAttribute();
            case SourceFile:
                return new SourceFileAttribute(cp);
            case Synthetic:
                return new SyntheticAttribute();
            default:
                return new UnparsedAttribute(attrName, attrLen, null);
        }
    }

}
