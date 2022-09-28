package com.jvmjava.ch03;

import com.jvmjava.ch03.classfile.ClassFile;
import com.jvmjava.ch03.classfile.attributeinfo.MemberInfo;
import com.jvmjava.ch03.classpath.Classpath;
import com.jvmjava.ch03.classpath.Entry;

import java.io.File;
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
        Classpath cp = Classpath.parse(cmd.XjreOption, cmd.cpOption);
        System.out.printf("cp:%s\nclass:%s\nargs:%s%n", cp, cmd.clazz, Arrays.toString(cmd.args));
        String className = cmd.clazz.replace(".", File.separator);
        ClassFile cf = loadClass(className, cp);
        System.out.println(cmd.clazz);
        printClassInfo(cf);
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

}
