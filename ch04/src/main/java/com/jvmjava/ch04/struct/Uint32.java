package com.jvmjava.ch04.struct;

import java.nio.ByteBuffer;

public class Uint32 {

    byte[] bytes;

    public Uint32(byte[] bytes) {
        assert bytes != null && bytes.length == 4;
        this.bytes = bytes;
    }

    public int toInt() {
        ByteBuffer byteBuffer = ByteBuffer.wrap(this.bytes, 0, 4);
        return byteBuffer.getInt();
    }

    public float toFloat() {
        ByteBuffer byteBuffer = ByteBuffer.wrap(this.bytes, 0, 4);
        return byteBuffer.getFloat();
    }

}
