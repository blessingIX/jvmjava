package com.jvmjava.ch03.classfile;

import com.jvmjava.ch03.uint.Uint16;

public class ConstantMemberrefInfo extends ConstantInfo {

    ConstantPool cp;

    Uint16 classIndex;

    Uint16 nameAndTypeIndex;

    public ConstantMemberrefInfo(ConstantPool cp) {
        this.cp = cp;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.classIndex = reader.readUint16();
        this.nameAndTypeIndex = reader.readUint16();
    }

    public String className() {
        return this.cp.getUtf8(classIndex);
    }

    public String[] nameAndDescriptor() {
        return this.cp.getNameAndType(this.nameAndTypeIndex);
    }

}
