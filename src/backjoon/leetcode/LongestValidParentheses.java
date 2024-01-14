package backjoon.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class LongestValidParentheses {

    public int longestValidParentheses(String s) {

        Deque<Node> stack = new ArrayDeque<>();

        int answer = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                stack.push(new Node(i, c));
            } else {
                if (!stack.isEmpty() && stack.peek().c == '(') {
                    stack.pop();

                    answer = Math.max(answer, stack.isEmpty() ? i : i - stack.peek().index);
                } else {
                    stack.push(new Node(i, c));
                }
            }
        }

        return answer;
    }

    private static class Node {
        private int index;
        private char c;

        Node(int index, char c) {
            this.index = index;
            this.c = c;
        }
    }
}
