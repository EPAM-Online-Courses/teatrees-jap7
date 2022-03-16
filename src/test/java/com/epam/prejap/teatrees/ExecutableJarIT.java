package com.epam.prejap.teatrees;

import org.testng.annotations.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.CodeSource;
import java.util.List;
import java.util.jar.JarFile;

import static org.testng.Assert.*;

@Test
public class ExecutableJarIT {

    private static final String MAIN_CLASS_FQN = TeaTrees.class.getName();
    private static String CLASSPATH;
    private static final String JAR_EXTENSION = "jar";

    static {
        CodeSource codeSource = TeaTrees.class.getProtectionDomain().getCodeSource();
        File jarFile;
        try {
            jarFile = new File(codeSource.getLocation().toURI().getPath());
            CLASSPATH = jarFile.getParentFile().getPath();
        } catch (URISyntaxException e) {
            e.getMessage();
        }
    }

    public void testJARisCreated() throws IOException {
        long expected = 0;
        long actual = Files.walk(Path.of(CLASSPATH),1).map(f -> f.getFileName().toString())
                .filter(file -> file.endsWith(JAR_EXTENSION)).count();
        assertNotEquals(actual,expected, "Expected to find more than 0 .jar files");
    }

    public void jarFileContainsManifestFile() throws IOException {
        List<Path> paths = Files.
                walk(Path.of(CLASSPATH), 1)
                .filter(file -> file.getFileName()
                        .toString().endsWith(JAR_EXTENSION))
                .toList();
        for (Path path : paths) {
            JarFile jarFile = new JarFile(String.valueOf(path));
            assertNotNull(jarFile.getManifest(), "MANIFEST.MF does not exist");
        }
    }

    public void manifestFileContainsMainClass() throws IOException {
        String expected = MAIN_CLASS_FQN;
        List<Path> paths = Files.
                walk(Path.of(CLASSPATH), 1)
                .filter(file -> file.getFileName()
                        .toString().endsWith(JAR_EXTENSION))
                .toList();
        for (Path path : paths) {
            JarFile jarFile = new JarFile(String.valueOf(path));
            String actual = jarFile.getManifest().getMainAttributes().getValue("Main-Class");
            assertEquals(actual,expected);
        }
    }
}
