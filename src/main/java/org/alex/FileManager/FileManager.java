package org.alex.FileManager;

import java.io.*;
import java.nio.file.Files;

public class FileManager {

    public int countFiles(String path) {
        int count = 0;
        File directory = new File(path);
        if (directory != null) {
            for (File file : directory.listFiles()) {
                if (file.isFile()) {
                    count++;
                }
                if (file.isDirectory()) {
                    count += countFiles(file.getAbsolutePath());
                }
            }
        }
        return count;

    }

    public int countDirs(String path) {
        int count = 0;
        File directory = new File(path);
        if (directory != null) {
            for (File file : directory.listFiles()) {
                if (file.isDirectory()) {
                    count++;
                    count += countDirs(file.getAbsolutePath());
                }
            }
        }
        return count;

    }

    public void copy(String from, String to) throws IOException {
        File originalFile = new File(from);
        File newFile = new File(to);
        InputStream origin = new FileInputStream(originalFile);
        OutputStream dest = new FileOutputStream(newFile);
        int fileLength = (int) originalFile.length();
        byte[] buffer = new byte[fileLength];
        int count = origin.read(buffer);
        dest.write(buffer, 0, count);
        origin.close();
        dest.close();
    }

    public static void move(String from, String to) {
        File myFile = new File(from);
        myFile.renameTo(new File(to));

    }

}