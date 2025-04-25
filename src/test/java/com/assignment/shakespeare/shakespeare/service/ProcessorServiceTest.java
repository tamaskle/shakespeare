package com.assignment.shakespeare.shakespeare.service;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ProcessorServiceTest {

    @Test
    void getWordsFromText() {
        String input = "A.AA; AAA  AAAA a";

        String[] expected = { "a", "aa", "aaa", "aaaa", "a" };
        var actual = ProcessorService.getWordsFromText(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    void countWords() {
        String[] input1 = { "a", "aa", "aaa", "a" };
        String[] input2 = { "a", "aa", "aaaa"};

        var expected = Map.of("a", 3, "aa", 2, "aaa", 1, "aaaa", 1);
        Map<String, Integer> actual = new HashMap<>();
        ProcessorService.countWords(actual, input1);
        ProcessorService.countWords(actual, input2);

        assertEquals(actual, expected);
    }

    @Test
    void getSortedWordCounts() {
        var input = Map.of("a", 3, "aa", 2, "aaa", 1, "aaaa", 1);

        var expected = Map.of("a", 3, "aa", 2);
        var actual = ProcessorService.getSortedWordCounts(input, 2);

        assertEquals(expected, actual);
    }
}