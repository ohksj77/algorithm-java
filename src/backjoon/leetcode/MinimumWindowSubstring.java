package backjoon.leetcode;

import java.util.*;

public class MinimumWindowSubstring {
    private static final String EMPTY = "";

    public String minWindow(String s, String t) {
        int sLength = s.length();
        int tLength = t.length();

        if (sLength == 0 && tLength == 0) {
            return EMPTY;
        }

        int minLength = Integer.MAX_VALUE;
        int minLeft = 0;

        Map<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int left = 0;
        int right = 0;
        int count = tLength;

        while (right < sLength) {
            char c = s.charAt(right);
            if (map.containsKey(c) && map.get(c) > 0) {
                count--;
            }

            if (map.containsKey(c)) {
                map.compute(c, (k, v) -> v - 1);
            }
            right++;

            while (count == 0) {
                if (right - left < minLength) {
                    minLength = right - left;
                    minLeft = left;
                }

                char leftChar = s.charAt(left);
                if (map.containsKey(leftChar)) {
                    map.compute(leftChar, (k, v) -> v + 1);
                    if (map.get(leftChar) > 0) {
                        count++;
                    }
                }
                left++;
            }
        }

        if (minLength == Integer.MAX_VALUE) {
            return EMPTY;
        }

        return s.substring(minLeft, minLeft + minLength);
    }
}
