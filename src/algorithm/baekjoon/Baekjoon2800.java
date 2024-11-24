package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Baekjoon2800 {
    private static final List<Pair> PAIRS = new ArrayList<>();
    private static final Set<String> TREE_SET = new TreeSet<>();
    private static int pairCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = br.readLine();

        Deque<Integer> stack = new ArrayDeque<>();

        int inputSize = input.length();
        for (int i = 0; i < inputSize; i++) {
            char cur = input.charAt(i);
            if (cur == '(') {
                stack.push(i);
                continue;
            }
            if (cur == ')') {
                PAIRS.add(new Pair(stack.pop(), i));
            }
        }

        pairCount = PAIRS.size();
        makeString(0, input);

        TREE_SET.remove(input);

        for (String s : TREE_SET) {
            bw.write(s);
            bw.newLine();
        }
        bw.flush();
    }

    private static void makeString(int index, String string) {
        if (index == pairCount) {
            TREE_SET.add(string.replaceAll(" ", ""));
            return;
        }

        Pair pair = PAIRS.get(index);
        StringBuilder sb = new StringBuilder(string);
        sb.setCharAt(pair.left, ' ');
        sb.setCharAt(pair.right, ' ');
        makeString(index + 1, sb.toString());
        makeString(index + 1, string);
    }

    private static class Pair {
        private final int left;
        private final int right;

        public Pair(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }
}
