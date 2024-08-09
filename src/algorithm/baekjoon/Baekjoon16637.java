package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Baekjoon16637 {

    private static final List<Character> OPS = new ArrayList<>();
    private static final List<Integer> NUMS = new ArrayList<>();
    private static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int n = Integer.parseInt(br.readLine());
        final String input = br.readLine();

        for (int i = 0; i < n; i++) {
            final char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                OPS.add(c);
                continue;
            }
            NUMS.add(Character.getNumericValue(c));
        }

        dfs(NUMS.get(0), 0);

        bw.write(Integer.toString(answer));
        bw.flush();
    }

    public static int calc(final char op, final int n1, final int n2) {
        if (op == '+') {
            return n1 + n2;
        }
        if (op == '-') {
            return n1 - n2;
        }
        if (op == '*') {
            return n1 * n2;
        }
        return -1;
    }

    public static void dfs(final int result, final int opIdx) {
        if (opIdx >= OPS.size()) {
            answer = Math.max(answer, result);
            return;
        }

        final int res1 = calc(OPS.get(opIdx), result, NUMS.get(opIdx + 1));
        dfs(res1, opIdx + 1);

        if (opIdx + 1 < OPS.size()) {
            final int res2 = calc(OPS.get(opIdx + 1), NUMS.get(opIdx + 1), NUMS.get(opIdx + 2));
            dfs(calc(OPS.get(opIdx), result, res2), opIdx + 2);
        }
    }
}
