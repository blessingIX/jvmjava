package com.jvmjava.ch03.classpath;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.StringJoiner;

public class Classpath {

    Entry bootClasspath;

    Entry extClasspath;

    Entry userClasspath;

    public static Classpath parse(String jreOption, String cpOption) {
        Classpath cp = new Classpath();
        cp.parseBootAndExtClasspath(jreOption);
        cp.parseUserClasspath(cpOption);
        return cp;
    }

    public Entry readClass(String className) {
        className = className + ".class";
        Entry entry = this.bootClasspath.readClass(className);
        if (entry != null) {
            return entry;
        }
        entry = this.extClasspath.readClass(className);
        if (entry != null) {
            return entry;
        }
        return this.userClasspath.readClass(className);
    }

    public String string() {
        return this.userClasspath.string();
    }

    private void parseBootAndExtClasspath(String jreOption) {
        String jreDir = getJreDir(jreOption);
        // jre/lib/*
        String jreLibPath = Classpath.joinPath(jreDir, "lib", "*");
        this.bootClasspath = new WildcardEntry(jreLibPath);
        // jre/lib/ext/*
        String jreExtPath = Classpath.joinPath(jreDir, "lib", "ext", "*");
        this.extClasspath = new WildcardEntry(jreExtPath);
    }

    private void parseUserClasspath(String cpOption) {
        if (cpOption == null || "".equals(cpOption)) {
            cpOption = ".";
        }
        this.userClasspath = Entry.newEntry(cpOption);
    }

    private String getJreDir(String jreOption) {
        if (jreOption != null && !"".equals(jreOption) && exists(jreOption)) {
            return jreOption;
        }
        if (exists("./jre")) {
            return "./jre";
        }
        String javaHome = System.getenv("JAVA_HOME");
        if (javaHome != null && !"".equals(javaHome)) {
            return Classpath.joinPath(javaHome, "jre");
        }
        throw new RuntimeException("Can not find jre folder!");
    }

    private boolean exists(String path) {
        return Files.exists(Paths.get(path));
    }

    public static String joinPath(String... paths) {
        StringJoiner joiner = new StringJoiner(File.separator);
        Arrays.stream(paths).forEach(joiner::add);
        return joiner.toString();
    }

    @Override
    public String toString() {
        return "Classpath{" +
                "bootClasspath=" + bootClasspath +
                ", extClasspath=" + extClasspath +
                ", userClasspath=" + userClasspath +
                '}';
    }
}
