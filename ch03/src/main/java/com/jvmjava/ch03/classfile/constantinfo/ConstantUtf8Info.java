package com.jvmjava.ch03.classfile.constantinfo;

import com.jvmjava.ch03.classfile.ClassReader;

public class ConstantUtf8Info extends ConstantInfo {

    String str;

    @Override
    public void readInfo(ClassReader reader) {
        int length = reader.readUint16().toInt();
        byte[] bytes = reader.readBytes(length);
        this.str = decodeMUTF8(bytes);
    }

    // TODO class文件中字符串为MUTF-8编码，与UTF-8大致相同，但不兼容
    // TODO 这里先简单实现，后续根据java.io.DataInputStream#readUTF()方法改写
    private String decodeMUTF8(byte[] bytes) {
        return new String(bytes);
    }

}
