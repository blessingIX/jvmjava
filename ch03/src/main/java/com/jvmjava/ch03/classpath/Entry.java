package com.jvmjava.ch03.classpath;

import java.io.File;

public interface Entry {

    String pathListSeparator = File.pathSeparator;  // unix : | windows ;

    int classBytesLen = 63325;

    Entry readClass(String className);

    String string();

    byte[] getData();

    static Entry newEntry(String path) {
        if (path.contains(pathListSeparator)) {
            return new CompositeEntry(path);
        }
        if (path.endsWith("*")) {
            return new WildcardEntry(path);
        }
        if (path.endsWith(".jar") || path.endsWith(".JAR") || path.endsWith(".zip") || path.endsWith(".ZIP")) {
            return new ZipEntry(path);
        }
        return new DirEntry(path);
    }

}
