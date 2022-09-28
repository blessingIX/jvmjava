package com.jvmjava.ch03.struct;

import java.nio.ByteBuffer;

public class Uint64 {

    byte[] bytes;

    public Uint64(byte[] bytes) {
        assert bytes != null && bytes.length == 8;
        this.bytes = bytes;
    }

    public long toLong() {
        ByteBuffer byteBuffer = ByteBuffer.wrap(this.bytes, 0, 8);
        return byteBuffer.getLong();
    }

    public double toDouble() {
        ByteBuffer byteBuffer = ByteBuffer.wrap(this.bytes, 0, 8);
        return byteBuffer.getDouble();
    }

}
