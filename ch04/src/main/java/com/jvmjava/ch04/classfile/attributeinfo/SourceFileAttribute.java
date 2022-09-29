package com.jvmjava.ch04.classfile.attributeinfo;

import com.jvmjava.ch04.classfile.ClassReader;
import com.jvmjava.ch04.classfile.constantinfo.ConstantPool;
import com.jvmjava.ch04.struct.Uint16;

public class SourceFileAttribute extends AttributeInfo {

    ConstantPool cp;

    Uint16 sourceFileIndex;

    public SourceFileAttribute(ConstantPool cp) {
        this.cp = cp;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.sourceFileIndex = reader.readUint16();
    }

    public String fileName() {
        return this.cp.getUtf8(this.sourceFileIndex);
    }

}
