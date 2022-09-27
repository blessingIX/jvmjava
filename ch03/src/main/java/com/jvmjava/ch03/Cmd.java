package com.jvmjava.ch03;

public class Cmd {

    boolean helpFlag;

    boolean versionFlag;

    String cpOption;

    String XjreOption;

    String clazz;

    String[] args;

    static Cmd parseCmd(String[] args) {
        if (args == null || args.length == 0) {
            System.err.println("miss args!");
            return null;
        }
        Cmd cmd = new Cmd();

        int classArgIdx = 0;
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            if ("-help".equals(arg)) {
                cmd.helpFlag = true;
            } else if ("-version".equals(arg)) {
                cmd.versionFlag = true;
            } else if ("-classpath".equals(arg) || "-cp".equals(arg)) {
                i++;
                cmd.cpOption = args[i];
            } else if ("-Xjre".equals(arg)) {
                i++;
                cmd.XjreOption = args[i];
            } else {
                classArgIdx = i;
                break;
            }
        }

        cmd.clazz = args[classArgIdx];
        cmd.args = new String[args.length - classArgIdx - 1];
        if (cmd.args.length > 0) {
            System.arraycopy(args, classArgIdx + 1, cmd.args, 0, cmd.args.length);
        }

        return cmd;
    }

    static void printUsage(Cmd cmd) {
        System.out.printf("Usage: java [-options] class [args...]\n%n");
    }

}
