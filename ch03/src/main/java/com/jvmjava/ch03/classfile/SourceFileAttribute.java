package com.jvmjava.ch03.classfile;

import com.jvmjava.ch03.uint.Uint16;

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
