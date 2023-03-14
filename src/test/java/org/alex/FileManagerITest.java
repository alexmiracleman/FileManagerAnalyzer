package org.alex;

import org.alex.FileManager.FileManager;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class FileManagerITest {
    final static String RESOURCES = "src/test/resources";
    final static String BACKUP = "src/test/BackUp";

    @Test
    public void countFilesInDirAndItsSubDirs() {
        FileManager fileManager = new FileManager();
        assertEquals(3, fileManager.countFiles(RESOURCES));
    }

    @Test
    public void countDirsAndItsSubDirs() {
        FileManager fileManager = new FileManager();
        assertEquals(4, fileManager.countDirs(RESOURCES));
    }

    @Test
    public void copyFile() throws IOException {
        FileManager fileManager = new FileManager();
        fileManager.copy(RESOURCES +"\\story.txt", RESOURCES + "\\story(copy).txt");

    }

    @Test
    public void moveFile() {
        FileManager fileManager = new FileManager();
        fileManager.move(RESOURCES +"\\story.txt", RESOURCES +"\\story(moved).txt");
    }
    @Test
    public void restoreToDefaults() throws IOException {
        FileManager fileManager = new FileManager();
        fileManager.copy(BACKUP +"\\story.txt", RESOURCES +"\\story.txt");
        fileManager.delete(RESOURCES + "\\story(moved).txt");
        fileManager.delete(RESOURCES + "\\story(copy).txt");
    }
}
