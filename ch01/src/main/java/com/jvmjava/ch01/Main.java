package com.jvmjava.ch01;

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
        System.out.printf("classpath:%s class:%s args:%s%n", cmd.cpOption, cmd.clazz, Arrays.toString(cmd.args));
    }

}
