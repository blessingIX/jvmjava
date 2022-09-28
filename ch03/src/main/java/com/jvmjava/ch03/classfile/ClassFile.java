package com.jvmjava.ch03.classfile;

import com.jvmjava.ch03.classfile.attributeinfo.AttributeInfo;
import com.jvmjava.ch03.classfile.attributeinfo.MemberInfo;
import com.jvmjava.ch03.classfile.constantinfo.ConstantPool;
import com.jvmjava.ch03.struct.Uint16;
import com.jvmjava.ch03.struct.Uint32;

/**
 * ClassFile结构反映了Java虚拟机规范定义的class文件格式
 */
public class ClassFile {

    Uint32 magic;

    Uint16 minorVersion;

    Uint16 majorVersion;

    ConstantPool constantPool;

    Uint16 accessFlags;

    Uint16 thisClass;

    Uint16 superClass;

    Uint16[] interfaces;

    MemberInfo[] fields;

    MemberInfo[] methods;

    AttributeInfo[] attributes;

    public static ClassFile parse(byte[] classData) {
        ClassReader cr = new ClassReader(classData);
        ClassFile cf = new ClassFile();
        cf.read(cr);
        return cf;
    }

    public void read(ClassReader reader) {
        this.readAndCheckMagic(reader);
        this.readAndCheckVersion(reader);
        this.constantPool = ConstantPool.readConstantPool(reader);
        this.accessFlags = reader.readUint16();
        this.thisClass = reader.readUint16();
        this.superClass = reader.readUint16();
        this.interfaces = reader.readUint16s();
        this.fields = MemberInfo.readMembers(reader, this.constantPool);
        this.methods = MemberInfo.readMembers(reader, this.constantPool);
        this.attributes = AttributeInfo.readAttributes(reader, this.constantPool);
    }

    public void readAndCheckMagic(ClassReader reader) {
        Uint32 magic = reader.readUint32();
        if (magic.toInt() != 0xCAFEBABE) {
            throw new RuntimeException("java.lang.ClassFormatError: mageic!");
        }
        this.magic = magic;
    }

    public void readAndCheckVersion(ClassReader reader) {
        this.minorVersion = reader.readUint16();
        this.majorVersion = reader.readUint16();
        switch (this.majorVersion.toInt()) {
            case 45:
                return;
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
                if (this.minorVersion.toInt() == 0) {
                    return;
                }
        }
        throw new RuntimeException("java.lang.UnsupportedClassVersionError!");
    }

    public Uint16 minorVersion() {          // getter
        return this.minorVersion;
    }

    public Uint16 majorVersion() {          // getter
        return this.majorVersion;
    }

    public ConstantPool constantPool() {    // getter
        return this.constantPool;
    }

    public Uint16 accessFlags() {           // getter
        return this.accessFlags;
    }

    public MemberInfo[] fields() {          // getter
        return this.fields;
    }

    public MemberInfo[] methods() {         // getter
        return this.methods;
    }

    public String className() {
        return this.constantPool.getClassName(this.thisClass);
    }

    public String superClassName() {
        if (this.superClass.toInt() > 0) {
            return this.constantPool.getClassName(this.superClass);
        }
        return "";  // java.lang.Object没有父类
    }

    public String[] interfaceNames() {
        String[] interfaceNames = new String[this.interfaces.length];
        for (int i = 0; i < this.interfaces.length; i++) {
            interfaceNames[i] = this.constantPool.getClassName(this.interfaces[i]);
        }
        return interfaceNames;
    }

}
