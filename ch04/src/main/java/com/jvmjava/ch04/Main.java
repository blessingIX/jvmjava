package com.jvmjava.ch04;

import com.jvmjava.ch04.classfile.ClassFile;
import com.jvmjava.ch04.classfile.attributeinfo.MemberInfo;
import com.jvmjava.ch04.classpath.Classpath;
import com.jvmjava.ch04.classpath.Entry;
import com.jvmjava.ch04.rtda.Frame;
import com.jvmjava.ch04.rtda.LocalVars;
import com.jvmjava.ch04.rtda.OperandStack;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Cmd cmd = Cmd.parseCmd(args);
        if (cmd == null) {
            return;
        }

        if (cmd.versionFlag) {
            System.out.println("openjdk version \"1.8.0_302\"\nOpenJDK Runtime Environment (Temurin)(build 1.8.0_302-b08)\nOpenJDK 64-Bit Server VM (Temurin)(build 25.302-b08, mixed mode)");
        } else if (cmd.helpFlag || "".equals(cmd.clazz)) {
            Cmd.printUsage(cmd);
        } else {
            startJVM(cmd);
        }
    }

    static void startJVM(Cmd cmd) {
        Frame frame = new Frame(100, 100);
        testLocalVars(frame.getLocalVars());
        testOperandStack(frame.getOperandStack());
    }

    static ClassFile loadClass(String className, Classpath cp) {
        Entry entry = cp.readClass(className);
        return ClassFile.parse(entry.getData());
    }

    static void printClassInfo(ClassFile cf) {
        System.out.printf("version: %d.%d%n", cf.majorVersion().toInt(), cf.majorVersion().toInt());
        System.out.printf("constant count: %d%n", cf.constantPool().len());
        System.out.printf("access flags: %s%n", Integer.toHexString(cf.accessFlags().toInt()));
        System.out.printf("this class: %s%n", cf.className());
        System.out.printf("super class: %s%n", cf.superClassName());
        System.out.printf("interfaces: %s%n", Arrays.toString(cf.interfaceNames()));
        System.out.printf("fields count: %d%n", cf.fields() == null ? 0 : cf.fields().length);
        for (MemberInfo field : cf.fields()) {
            System.out.printf("%s%n", field.name());
        }
        System.out.printf("methods count: %d%n", cf.methods() == null ? 0 : cf.methods().length);
        for (MemberInfo method : cf.methods()) {
            System.out.printf("%s%n", method.name());
        }
    }

    static void testLocalVars(LocalVars vars) {
        vars.setInd(0, 100);
        vars.setInd(1, -100);
        vars.setLong(2, 2997924580L);
        vars.setLong(4, -2997924580L);
        vars.setFloat(6, 3.1415926F);
        vars.setDouble(7, 2.71828182845);
        vars.setRef(9, null);
        System.out.println(vars.getInt(0));
        System.out.println(vars.getInt(1));
        System.out.println(vars.getLong(2));
        System.out.println(vars.getLong(4));
        System.out.println(vars.getFloat(6));
        System.out.println(vars.getDouble(7));
        System.out.println(vars.getRef(9));
    }

    static void testOperandStack(OperandStack ops) {
        ops.pushInt(100);
        ops.pushInt(-100);
        ops.pushLong(2997924580L);
        ops.pushLong(-2997924580L);
        ops.pushFloat(3.1415926F);
        ops.pushDouble(2.71828182845);
        ops.pushRef(null);
        System.out.println(ops.popRef());
        System.out.println(ops.popDouble());
        System.out.println(ops.popFloat());
        System.out.println(ops.popLong());
        System.out.println(ops.popLong());
        System.out.println(ops.popInt());
        System.out.println(ops.popInt());
    }

}
