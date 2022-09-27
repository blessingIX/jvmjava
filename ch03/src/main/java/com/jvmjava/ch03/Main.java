package com.jvmjava.ch03;

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
        Classpath classpath = Classpath.parse(cmd.XjreOption, cmd.cpOption);
        System.out.printf("classpath:%s\nclass:%s\nargs:%s%n", classpath, cmd.clazz, Arrays.toString(cmd.args));
        String className = cmd.clazz.replace(".", File.separator);
        Entry entry = classpath.readClass(className);
        System.out.println(entry);
    }

}
