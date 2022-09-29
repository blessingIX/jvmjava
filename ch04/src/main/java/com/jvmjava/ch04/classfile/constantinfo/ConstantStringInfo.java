package com.jvmjava.ch04.classfile.constantinfo;

import com.jvmjava.ch04.classfile.ClassReader;
import com.jvmjava.ch04.struct.Uint16;

public class ConstantStringInfo extends ConstantInfo {

    ConstantPool cp;

    Uint16 stringIndex;

    public ConstantStringInfo(ConstantPool cp) {
        this.cp = cp;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.stringIndex = reader.readUint16();
    }

    public String string() {
        return this.cp.getUtf8(this.stringIndex);
    }

}
