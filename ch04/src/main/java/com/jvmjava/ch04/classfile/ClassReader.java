package com.jvmjava.ch04.classfile;

import com.jvmjava.ch04.struct.Uint16;
import com.jvmjava.ch04.struct.Uint32;
import com.jvmjava.ch04.struct.Uint64;
import com.jvmjava.ch04.struct.Uint8;

public class ClassReader {

    byte[] data;

    public ClassReader(byte[] data) {
        this.data = data;
    }

    public Uint8 readUint8() {
        return new Uint8(this.readBytes(1));
    }

    public Uint16 readUint16() {
        return new Uint16(this.readBytes(2));
    }

    public Uint32 readUint32() {
        return new Uint32(this.readBytes(4));
    }

    public Uint64 readUint64() {
        return new Uint64(this.readBytes(8));
    }

    public Uint16[] readUint16s() {
        int n = this.readUint16().toInt();
        Uint16[] s = new Uint16[n];
        for (int i = 0; i < n; i++) {
            s[i] = this.readUint16();
        }
        return s;
    }

    public byte[] readBytes(int len) {
        byte[] val = new byte[len];
        System.arraycopy(this.data, 0, val, 0, len);

        byte[] remaining = new byte[this.data.length - len];
        System.arraycopy(this.data, len, remaining, 0, remaining.length);
        this.data = remaining;

        return val;
    }

}
