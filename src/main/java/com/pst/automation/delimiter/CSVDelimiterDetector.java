package com.pst.automation.delimiter;

import java.util.HashMap;
import java.util.Map;

public class CSVDelimiterDetector {

    public char detectDelimiter(String input) {
        char[] characters = input.toCharArray();
        Map<Character, Integer> symbols = new HashMap<>();
        for (int i = 0; i < input.length(); i++) {

            char ch = characters[i];

            if (isSymbol(ch)) { //counts all symbols. Skips letters and digits
                increment(symbols, ch);
            }
        }
        return symbols.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
    }

    private boolean isSymbol(char ch) {
        return !Character.isLetterOrDigit(ch) && (ch == '\t' || ch >= ' ');
    }

    private void increment(Map<Character, Integer> map, char symbol) {
        this.increment(map, symbol, 1);
    }

    private void increment(Map<Character, Integer> map, char symbol, int incrementSize) {
        Integer count = map.get(symbol);
        if (count == null) {
            count = 0;
        }
        map.put(symbol, count + incrementSize);
    }
}
