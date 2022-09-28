package com.jvmjava.ch03.classfile.attributeinfo;

import com.jvmjava.ch03.struct.Uint16;

public class ExceptionTableEntry {

    Uint16 startPc;

    Uint16 endPc;

    Uint16 handlePc;

    Uint16 catchType;

}
