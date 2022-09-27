package org.example.poker;

import java.util.HashMap;
import java.util.Map;

public class ValueUtil {
    private static final Map<Character, Integer> values = new HashMap<Character, Integer>();
    static {
        values.put('2', 2);
        values.put('3', 3);
        values.put('4', 4);
        values.put('5', 5);
        values.put('6', 6);
        values.put('7', 7);
        values.put('8', 8);
        values.put('9', 9);
        values.put('T', 10);
        values.put('J', 11);
        values.put('Q', 12);
        values.put('K', 13);
        values.put('A', 14);
    }

    public static Integer getValue(Character key) {
        return values.get(key);
    }
}
