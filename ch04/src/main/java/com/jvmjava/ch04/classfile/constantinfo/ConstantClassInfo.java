package com.jvmjava.ch04.classfile.constantinfo;

import com.jvmjava.ch04.classfile.ClassReader;
import com.jvmjava.ch04.struct.Uint16;

public class ConstantClassInfo extends ConstantInfo {

    ConstantPool cp;

    Uint16 nameIndex;

    public ConstantClassInfo(ConstantPool cp) {
        this.cp = cp;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.nameIndex = reader.readUint16();
    }

    public String name() {
        return this.cp.getUtf8(this.nameIndex);
    }

}
