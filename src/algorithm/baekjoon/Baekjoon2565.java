package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;

public class Baekjoon2565 {
    private static Integer[] dp;
    private static int[][] wire;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        dp = new Integer[n];
        wire = new int[n][2];

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            wire[i][0] = Integer.parseInt(input[0]);
            wire[i][1] = Integer.parseInt(input[1]);
        }

        Arrays.sort(wire, Comparator.comparingInt(o -> o[0]));

        int max = 0;

        for (int i = 0; i < n; i++) {
            max = Math.max(find(i), max);
        }

        bw.write(Integer.toString(n - max));
        bw.flush();
    }

    private static int find(int N) {
        if (dp[N] == null) {
            dp[N] = 1;
            for (int i = N + 1; i < dp.length; i++) {
                if (wire[N][1] < wire[i][1]) {
                    dp[N] = Math.max(dp[N], find(i) + 1);
                }
            }
        }
        return dp[N];
    }
}
