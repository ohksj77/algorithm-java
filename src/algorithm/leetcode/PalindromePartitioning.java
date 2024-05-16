package algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {

    public static void main(String[] args) {
        PalindromePartitioning palindromePartitioning = new PalindromePartitioning();

        System.out.println(palindromePartitioning.partition("aab"));
    }


    public List<List<String>> partition(String s) {
        List<List<String>> results = new ArrayList<>();
        backtrack(s, 0, new ArrayList<>(), results);
        return results;
    }

    private void backtrack(String s, int start, List<String> path, List<List<String>> results) {
        if (start == s.length()) {
            results.add(new ArrayList<>(path));
            return;
        }
        for (int i = start + 1; i <= s.length(); i++) {
            String str = s.substring(start, i);
            if (isPalindrome(str)) {
                path.add(str);
                backtrack(s, i, path, results);
                path.remove(path.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s) {
        return s.contentEquals((new StringBuilder(s)).reverse());
    }
}
