package ru.ncedu.java.tasks;

import java.io.*;
import java.util.*;

/**
 * Created by Frolov Maksim on 02.05.2016.
 */
public class WordProcessorImpl implements WordProcessor {
    public WordProcessorImpl() {
    }

    private String text = "";

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setSource(String src) {
        if (src == null) {
            throw new IllegalArgumentException();
        }

        text = src;
    }

    @Override
    public void setSourceFile(String srcFile) throws IOException {
        if (srcFile == null) {
            throw new IllegalArgumentException();
        }

        BufferedReader in = new BufferedReader(new FileReader(srcFile));
        String line;
        while ((line = in.readLine()) != null) {
            text += line + " ";
        }
        text = text.substring(0, text.length() - 1);
        in.close();
    }

    @Override
    public void setSource(FileInputStream fis) throws IOException {
        if (fis == null) {
            throw new IllegalArgumentException();
        }

        BufferedReader in = new BufferedReader(new FileReader(fis.getFD()));
        String line;
        while ((line = in.readLine()) != null) {
            text += line + " ";
        }
        text = text.substring(0, text.length() - 1);
        in.close();
    }

    @Override
    public Set<String> wordsStartWith(String begin) {
        if (text == null) {
            throw new IllegalStateException();
        } else {
            Set<String> parsedText = new HashSet<>();
            String[] splitText = text.toLowerCase().split("\\s+");

            if (begin == null || begin.equals("")) {
                Collections.addAll(parsedText, splitText);
            } else {
                for (String s : splitText) {
                    if (s.matches("^" + begin + ".+$")) {
                        parsedText.add(s);
                    }
                }
            }

            return parsedText;
        }
    }
}
