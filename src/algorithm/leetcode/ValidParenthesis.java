package algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class ValidParenthesis {

    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (c == '{') {
                stack.push('}');
                continue;
            }
            if (c == '[') {
                stack.push(']');
                continue;
            }
            if (c == '(') {
                stack.push(')');
                continue;
            }
            if (!stack.isEmpty() && stack.peek() == c) {
                stack.pop();
                continue;
            }
            return false;
        }

        return stack.isEmpty();
    }
}
