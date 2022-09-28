package com.jvmjava.ch03.classfile.constantinfo;

import com.jvmjava.ch03.classfile.ClassReader;
import com.jvmjava.ch03.struct.NameAndType;
import com.jvmjava.ch03.struct.Uint16;

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

    public NameAndType nameAndDescriptor() {
        return this.cp.getNameAndType(this.nameAndTypeIndex);
    }

}
