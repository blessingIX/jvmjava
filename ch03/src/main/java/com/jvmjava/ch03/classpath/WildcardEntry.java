package com.jvmjava.ch03.classpath;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class WildcardEntry extends CompositeEntry {

    public WildcardEntry(String path) {
        super();
        String baseDir = path.substring(0, path.length() - 1);  // remove *
        try {
            Files.walk(Paths.get(baseDir), 1).forEach(p -> {
                if (Files.isDirectory(p) && !p.getFileName().toString().equals(baseDir)) {
                    return;
                }
                if (p.getFileName().toString().endsWith(".jar") || p.getFileName().toString().endsWith(".JAR")) {
                    ZipEntry zipEntry = new ZipEntry(p.toFile().getAbsolutePath());
                    super.append(zipEntry);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException("walk directory error!");
        }
    }

    @Override
    public String toString() {
        return "WildcardEntry{" +
                "entries=" + Arrays.toString(entries) +
                '}';
    }

}
