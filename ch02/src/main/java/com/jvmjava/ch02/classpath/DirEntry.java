package com.jvmjava.ch02.classpath;

import java.io.*;
import java.util.Arrays;

public class DirEntry extends AbstractEntry {

    String absDir;

    public DirEntry(String className) {
        File file = new File(className);
        this.absDir = file.getAbsolutePath();
    }

    @Override
    public Entry readClass(String className) {
        File file = new File(absDir, className);
        byte[] buf = new byte[1024];
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
             ByteArrayOutputStream out = new ByteArrayOutputStream(1024)) {
            int len = 0;
            while ((len = in.read(buf)) != -1) {
                out.write(buf, 0, len);
            }
            this.data = out.toByteArray();
        } catch (IOException e) {
//            e.printStackTrace();
            return this;
        }
        return this;
    }

    @Override
    public String string() {
        return this.absDir;
    }

    @Override
    public String toString() {
        return "DirEntry{" +
                "data=" + Arrays.toString(data) +
                ", absDir='" + absDir + '\'' +
                '}';
    }

}
