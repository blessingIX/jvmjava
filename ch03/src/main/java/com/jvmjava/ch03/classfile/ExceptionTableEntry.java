package com.jvmjava.ch03.classfile;

import com.jvmjava.ch03.uint.Uint16;

public class ExceptionTableEntry {

    Uint16 startPc;

    Uint16 endPc;

    Uint16 handlePc;

    Uint16 catchType;

}
