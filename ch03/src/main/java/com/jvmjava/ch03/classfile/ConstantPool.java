package com.jvmjava.ch03.classfile;

import com.jvmjava.ch03.uint.Uint16;

public class ConstantPool {

    ConstantInfo[] items;

    private ConstantPool(ConstantInfo[] items) {
        this.items = items;
    }

    static ConstantPool readConstantPool(ClassReader reader) {
        int cpCount = reader.readUint16().toInt();
        ConstantInfo[] item = new ConstantInfo[cpCount];
        for (int i = 1; i < cpCount; i++) {
//            cp[i] = readConstantInfo(reader, cp);
            if (item[i] instanceof ConstantLongInfo || item[i] instanceof ConstantDoubleInfo) {
                i++;
            }
        }
        return new ConstantPool(item);
    }

    ConstantInfo getConstantInfo(Uint16 index) {
        ConstantInfo cpInfo = null;
        try {
            cpInfo = this.items[index.toInt()];
        } catch (NullPointerException | ArrayIndexOutOfBoundsException ignore) {
        }
        if (cpInfo != null) {
            return cpInfo;
        }
        throw new RuntimeException("Invalid constant pool index!");
    }

    // TODO (string, string)
    String[] getNameAndType(Uint16 index) {
        ConstantNameAndTypeInfo ntInfo = (ConstantNameAndTypeInfo) this.getConstantInfo(index);
        String name = this.getUtf8(ntInfo.nameIndex);
        String type = this.getUtf8(ntInfo.descriptorIndex);
        return new String[]{name, type};
    }

    String getClassName(Uint16 index) {
        ConstantClassInfo classInfo = (ConstantClassInfo) this.getConstantInfo(index);
        return this.getUtf8(classInfo.nameIndex);
    }

    String getUtf8(Uint16 index) {
        ConstantUtf8Info utf8Info = (ConstantUtf8Info) this.getConstantInfo(index);
        return utf8Info.str;
    }

}
