package algorithm.codetree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;

public class 올바른_쌍의_괄호 {

    private static final char OPEN = '(';
    private static final char CLOSE = ')';
    private static final String YES = "Yes";
    private static final String NO = "No";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = br.readLine();

        Deque<Character> stack = new ArrayDeque<>();
        boolean isYes = true;

        for (int i = 0; i < input.length(); i++) {
            char value = input.charAt(i);

            if (value == OPEN) {
                stack.push(value);
                continue;
            }
            if (value == CLOSE) {
                if (stack.isEmpty()) {
                    bw.write(NO);
                    isYes = false;
                    break;
                }
                char poped = stack.pop();
                if (poped == OPEN) {
                    continue;
                }
                bw.write(NO);
                isYes = false;
                break;
            }
        }

        if (!stack.isEmpty()) {
            bw.write(NO);
        } else if (isYes) {
            bw.write(YES);
        }
        bw.flush();
    }
}
