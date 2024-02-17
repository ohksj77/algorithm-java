package backjoon.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubstringWithConcatenationOfAllWords {

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        Map<String, Integer> wordMap = new HashMap<>();
        for (String word : words) {
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }

        int wordLength = words[0].length();
        int wordCount = words.length;
        int totalLength = wordLength * wordCount;

        for (int i = 0; i <= s.length() - totalLength; i++) {
            Map<String, Integer> tempMap = new HashMap<>(wordMap);
            for (int j = 0; j < wordCount; j++) {
                String word = s.substring(i + j * wordLength, i + (j + 1) * wordLength);
                if (tempMap.containsKey(word)) {
                    int count = tempMap.get(word);
                    if (count == 1) {
                        tempMap.remove(word);
                    } else {
                        tempMap.put(word, count - 1);
                    }
                } else {
                    break;
                }
            }
            if (tempMap.isEmpty()) {
                result.add(i);
            }
        }
        return result;
    }
}
