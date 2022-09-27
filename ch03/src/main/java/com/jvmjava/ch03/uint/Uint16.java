package com.jvmjava.ch03.uint;

import java.nio.ByteBuffer;

public class Uint16 {

    byte[] bytes;

    public Uint16(byte[] bytes) {
        assert bytes != null && bytes.length == 2;
        this.bytes = bytes;
    }

    public int toInt() {
        ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[]{0b00000000, 0b00000000, bytes[0], bytes[1]}, 0, 4);
        return byteBuffer.getInt();
    }

}
