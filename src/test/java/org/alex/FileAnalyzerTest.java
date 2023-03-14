package org.alex;

import org.alex.FileAnalyzer.FileAnalyzer;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class FileAnalyzerTest {

    @Test
    public void analyzeWordAndCount() throws IOException {
        FileAnalyzer fileAnalyzer = new FileAnalyzer();
        fileAnalyzer.analyze("duck", "src/test/Resources/story.txt");


    }
}
