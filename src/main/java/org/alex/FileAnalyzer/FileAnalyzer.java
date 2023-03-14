package org.alex.FileAnalyzer;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;



public class FileAnalyzer {

    private static final Pattern SENTENCE_PATTERN = Pattern.compile("((?<=[.?!]))");

    public FileStatistics analyze(String word, String path) throws IOException {
        String content = readContent(path);
        List<String> sentences = breakIntoSentences(content);
        List<String> filteredSentences = containsInSentence(sentences, word);
        int count = countWord(filteredSentences, word);

        System.out.println("The searched word \"" + word + "\" in the file counted " + count + " time(s).");
        System.out.println();
        for (String filteredSentence : filteredSentences) {
            System.out.println(filteredSentence);
        }
        return new FileStatistics(filteredSentences, count);
    }

    public String readContent(String path) throws IOException {
        File file = new File(path);
        InputStream inputStream = new FileInputStream(file);
        int fileLength = (int) file.length();
        byte[] contentArray = new byte[fileLength];
        inputStream.read(contentArray);
        inputStream.close();

        return new String(contentArray);


    }

    public List<String> breakIntoSentences(String content) {
        String[] sentences = SENTENCE_PATTERN.split(content);
        List<String> trimmedSentences = new ArrayList<>();

        for (String sentence : sentences) {
            trimmedSentences.add(sentence.trim());

        }
        return trimmedSentences;
    }

    public List<String> containsInSentence(List<String> sentences, String word) {
        List<String> filteredSentences = new ArrayList<>();
        for (String sentence : sentences) {
            if (sentence.contains(word)) {
                filteredSentences.add(sentence);
            }

        }

        return filteredSentences;
    }

    public int countWord(List<String> filteredSentences, String word) {
        int count = 0;
        for (String filteredSentence : filteredSentences) {
            filteredSentence = filteredSentence.toLowerCase();
            filteredSentence = filteredSentence.replaceAll("[.,!?]", "");
            String words[] = filteredSentence.split(" ");
            for (int i = 0; i < words.length; i++) {
                if (words[i].equals(word)) {
                    count++;
                }
            }

        }
        return count;
    }

}

//Используем классы FileInputStream, FileOutputStream, File
//Практика:
//1: Написать программу FileAnalyzer, которая в консоли принимает 2 параметра:
//1) путь к файлу
//2) слово
//Usage:
//java FileAnalyzer C:/test/story.txt duck
//
//Выводит:
//1) Кол-во вхождений искомого слова в файле
//2) Все предложения содержащие искомое слово(предложение заканчивается символами ".", "?", "!").
//Каждое предложение выводится с новой строки.