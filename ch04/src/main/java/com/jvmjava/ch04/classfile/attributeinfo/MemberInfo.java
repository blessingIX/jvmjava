package com.jvmjava.ch04.classfile.attributeinfo;

import com.jvmjava.ch04.classfile.ClassReader;
import com.jvmjava.ch04.classfile.constantinfo.ConstantPool;
import com.jvmjava.ch04.struct.Uint16;

public class MemberInfo {

    ConstantPool cp;

    Uint16 accessFlags;

    Uint16 nameIndex;

    Uint16 descriptorIndex;

    AttributeInfo[] attributes;

    public static MemberInfo[] readMembers(ClassReader reader, ConstantPool cp) {
        int memberCount = reader.readUint16().toInt();
        MemberInfo[] members = new MemberInfo[memberCount];
        for (int i = 0; i < members.length; i++) {
            members[i] = readMember(reader, cp);
        }
        return members;
    }

    static MemberInfo readMember(ClassReader reader, ConstantPool cp) {
        MemberInfo member = new MemberInfo();
        member.cp = cp;
        member.accessFlags = reader.readUint16();
        member.nameIndex = reader.readUint16();
        member.descriptorIndex = reader.readUint16();
        member.attributes = AttributeInfo.readAttributes(reader, cp);
        return member;
    }

    public Uint16 accessFlags() {   // getter
        return this.accessFlags;
    }

    public String name() {
        return this.cp.getUtf8(this.nameIndex);
    }

    public String descriptor() {
        return this.cp.getUtf8(this.descriptorIndex);
    }

}
