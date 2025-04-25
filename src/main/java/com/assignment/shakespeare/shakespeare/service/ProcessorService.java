package com.assignment.shakespeare.shakespeare.service;

import com.assignment.shakespeare.shakespeare.configuration.ApplicationConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProcessorService {

    private final ApplicationConfiguration applicationConfiguration;

    public void process() {
        var wordCounts = processFile(applicationConfiguration.getFilePath());
        var processedWords = getSortedWordCounts(wordCounts, applicationConfiguration.getLimit());

        processedWords.forEach((word, count) -> {
            System.out.printf("%s - %d%n", word, count);
        });
    }

    static Map<String, Integer> processFile(String filePath) {
        Map<String, Integer> wordCounts = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                countWords(wordCounts, getWordsFromText(line));
            }

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return wordCounts;
    }

    static String[] getWordsFromText(String text) {
        return text
                .toLowerCase()
                .replaceAll("[^a-z0-9\\s]", " ")
                .split("\\s+");
    }

    static void countWords(Map<String, Integer> wordCounts, String[] words) {
        for (String word : words) {
            if (!word.isBlank()) {
                wordCounts.merge(word, 1, Integer::sum);
            }
        }
    }

    static Map<String, Integer> getSortedWordCounts(Map<String, Integer> wordCounts, int limit) {
        return wordCounts.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(limit)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }
}
