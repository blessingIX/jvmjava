package com.jvmjava.ch03.classfile.constantinfo;

import com.jvmjava.ch03.classfile.ClassReader;
import com.jvmjava.ch03.struct.NameAndType;
import com.jvmjava.ch03.struct.Uint16;

public class ConstantPool {

    public ConstantInfo[] infos;

    private ConstantPool(ConstantInfo[] infos) {
        this.infos = infos;
    }

    private void set(int index, ConstantInfo constantInfo) {
        this.infos[index] = constantInfo;
    }

    public int len() {
        return infos == null ? 0 : infos.length;
    }

    private ConstantInfo get(int index) {
        return this.infos[index];
    }

    public static ConstantPool readConstantPool(ClassReader reader) {
        int cpCount = reader.readUint16().toInt();
        ConstantPool cp = new ConstantPool(new ConstantInfo[cpCount]);
        for (int i = 1; i < cpCount; i++) {
            cp.set(i, ConstantInfo.readConstantInfo(reader, cp));
            if (cp.get(i) instanceof ConstantLongInfo || cp.get(i) instanceof ConstantDoubleInfo) {
                i++;
            }
        }
        return cp;
    }

    ConstantInfo getConstantInfo(Uint16 index) {
        ConstantInfo cpInfo = this.infos[index.toInt()];
        if (cpInfo != null) {
            return cpInfo;
        }
        throw new RuntimeException("Invalid constant pool index!");
    }

    public NameAndType getNameAndType(Uint16 index) {
        ConstantNameAndTypeInfo ntInfo = (ConstantNameAndTypeInfo) this.getConstantInfo(index);
        String name = this.getUtf8(ntInfo.nameIndex);
        String type = this.getUtf8(ntInfo.descriptorIndex);
        return new NameAndType(name, type);
    }

    public String getClassName(Uint16 index) {
        ConstantClassInfo classInfo = (ConstantClassInfo) this.getConstantInfo(index);
        return this.getUtf8(classInfo.nameIndex);
    }

    public String getUtf8(Uint16 index) {
        ConstantInfo constantInfo = this.getConstantInfo(index);
        ConstantUtf8Info utf8Info = null;
        if (constantInfo instanceof ConstantUtf8Info) {
            utf8Info = (ConstantUtf8Info) constantInfo;
        }
        if (utf8Info == null) {
            System.out.println(constantInfo.getClass());
            throw new RuntimeException("cast to ConstantUtf8Info error!");
        }
        return utf8Info.str;
    }

}
