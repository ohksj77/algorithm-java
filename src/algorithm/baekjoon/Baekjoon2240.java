package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon2240 {

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final String[] tw = br.readLine().split(" ");
        final int t = Integer.parseInt(tw[0]);
        final int w = Integer.parseInt(tw[1]);

        final int[][] dp = new int[w + 1][t + 1];
        final int[] arr = new int[t + 1];

        for (int i = 1; i <= t; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int result = Integer.MIN_VALUE;

        for (int i = 1; i <= t; i++) {
            if (arr[i] == 1) {
                dp[0][i] = dp[0][i - 1] + 1;
                result = Math.max(result, dp[0][i]);
            }
        }

        for (int i = 1; i <= w; i++) {
            for (int j = 1; j <= t; j++) {
                if ((i % 2 == 0 && arr[j] == 1) || (i % 2 == 1 && arr[j] == 2)) {
                    dp[i][j] = dp[i][j - 1] + 1;
                } else if ((i % 2 == 0 && arr[j] == 2) || (i % 2 == 1 && arr[j] == 1)) {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j - 1] + 1);
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
                result = Math.max(result, dp[i][j]);
            }
        }

        bw.write(Integer.toString(result));
        bw.flush();
    }
}
