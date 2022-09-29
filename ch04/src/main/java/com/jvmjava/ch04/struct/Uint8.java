package com.jvmjava.ch04.struct;

import java.nio.ByteBuffer;

public class Uint8 {

    byte[] bytes;

    public Uint8(byte[] bytes) {
        assert bytes != null && bytes.length == 1;
        this.bytes = bytes;
    }

    public int toInt() {
        ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[]{0b00000000, 0b00000000, 0b00000000, bytes[0]}, 0, 4);
        return byteBuffer.getInt();
    }

}
