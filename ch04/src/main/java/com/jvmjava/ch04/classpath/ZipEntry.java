package com.jvmjava.ch04.classpath;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.zip.ZipInputStream;

public class ZipEntry extends AbstractEntry {

    String absPath;

    public ZipEntry(String path) {
        File file = new File(path);
        this.absPath = file.getAbsolutePath();
    }

    @Override
    public Entry readClass(String className) {
        File file = new File(this.absPath);
        String normalizeClassName = Paths.get(className).normalize().toString();

        try (ZipInputStream in = new ZipInputStream(new FileInputStream(file))) {
            java.util.zip.ZipEntry zipEntry;
            while ((zipEntry = in.getNextEntry()) != null) {
                String normalizeZipEntryName = Paths.get(zipEntry.getName()).normalize().toString();
                if (normalizeClassName.equals(normalizeZipEntryName)) {
                    try (ByteArrayOutputStream out = new ByteArrayOutputStream(1024)) {
                        int len;
                        byte[] buf = new byte[1024];
                        while ((len = in.read(buf)) != -1) {
                            out.write(buf, 0, len);
                        }
                        this.data = out.toByteArray();
                    }
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("read zip/jar error!");
        }
        return this;
    }

    @Override
    public String string() {
        return this.absPath;
    }

    @Override
    public String toString() {
        return "ZipEntry{" +
                "absPath='" + absPath + '\'' +
                '}';
    }
}
