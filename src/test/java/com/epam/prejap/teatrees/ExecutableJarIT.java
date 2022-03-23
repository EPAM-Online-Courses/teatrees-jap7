package com.epam.prejap.teatrees;

import org.testng.annotations.BeforeClass;
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

    @BeforeClass
    public void init() {
        CodeSource codeSource = TeaTrees.class.getProtectionDomain().getCodeSource();
        File jarFile;
        try {
            jarFile = new File(codeSource.getLocation().toURI().getPath());
            CLASSPATH = jarFile.getParentFile().getPath();
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }
    }

    public void testJARisCreated() throws IOException {
        long expected = 0;
        long actual = findPathsWithExtensions(CLASSPATH,JAR_EXTENSION).size();
        assertNotEquals(actual, expected, "Expected to find more than 0 .jar files");
    }

    @Test(dependsOnMethods = "testJARisCreated")
    public void jarFileContainsManifestFile() throws IOException {
        List<Path> paths = findPathsWithExtensions(CLASSPATH,JAR_EXTENSION);

        for (Path path : paths) {
            JarFile jarFile = new JarFile(String.valueOf(path));
            assertNotNull(jarFile.getManifest(), "MANIFEST.MF does not exist");
        }
    }

    @Test(dependsOnMethods = "testJARisCreated")
    public void manifestFileContainsMainClass() throws IOException {
        String expected = MAIN_CLASS_FQN;
        List<Path> paths = findPathsWithExtensions(CLASSPATH,JAR_EXTENSION);

        for (Path path : paths) {
            JarFile jarFile = new JarFile(String.valueOf(path));
            String actual = jarFile.getManifest().getMainAttributes().getValue("Main-Class");
            assertEquals(actual, expected);
        }
    }

    private List<Path> findPathsWithExtensions(String path, String extension) throws IOException {
        List<Path> paths = Files.
                walk(Path.of(path), 1)
                .filter(file -> file.getFileName()
                        .toString().endsWith(extension))
                .toList();
        return paths;
    }
}
