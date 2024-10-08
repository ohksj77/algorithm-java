package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon16438 {

    private static final String A = "A";
    private static final String B = "B";
    private static char[][] monkeys;

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int n = Integer.parseInt(br.readLine());
        monkeys = new char[7][n];
        final StringBuilder sb = new StringBuilder();

        sb.append(A.repeat(n));

        make(0, n - 1, 0);

        for (int i = 0; i < 7; i++) {
            String str = new String(monkeys[i]);
            if (str.contentEquals(sb)) {
                str = str.substring(1) + B;
            }
            bw.write(str);
            bw.newLine();
        }
        bw.flush();
    }

    public static void make(final int start, final int end, final int depth) {
        if (depth == 7) {
            return;
        }
        final int middle = (start + end) / 2;

        for (int i = start; i <= middle; i++) {
            monkeys[depth][i] = 'A';
        }
        for (int i = middle + 1; i <= end; i++) {
            monkeys[depth][i] = 'B';
        }
        make(start, middle, depth + 1);
        make(middle + 1, end, depth + 1);
    }
}
