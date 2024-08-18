package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon16974 {
    private static int n;
    private static int level;
    private static long x;
    private static long answer;
    private static long[] total;
    private static long[] patty;

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        x = Long.parseLong(input[1]);

        total = new long[n + 1];
        patty = new long[n + 1];

        total[0] = 1;
        patty[0] = 1;

        for (int i = 1; i < n + 1; i++) {
            total[i] = 1 + total[i - 1] + 1 + total[i - 1] + 1;
            patty[i] = patty[i - 1] + 1 + patty[i - 1];
        }

        process();
        bw.write(Long.toString(answer));
        bw.flush();
    }

    private static void process() {
        answer = 0;
        level = n;
        eat(1);
        if (x == 0) {
            return;
        }
        level = n;
        eat(0);
    }

    private static void eat(final int n) {
        while (level >= 0 && x != 0) {
            if (total[level] > x) {
                x--;
                level--;
                continue;
            }
            x -= total[level];
            answer += patty[level];
            if (x == 0) {
                return;
            }
            x -= n;
            answer += n;
        }
    }
}
