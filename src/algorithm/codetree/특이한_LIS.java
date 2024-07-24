package algorithm.codetree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class 특이한_LIS {

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw =
                new BufferedWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        final String[] nk = br.readLine().split(" ");
        final int n = Integer.parseInt(nk[0]);
        final int k = Integer.parseInt(nk[1]);

        final int[] s =
                Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        final int[][] dp = new int[n][3];
        dp[0][0] = 1;
        dp[0][1] = 1;
        dp[0][2] = s[0];

        for (int i = 1; i < n; i++) {
            dp[i][0] = 1;
            dp[i][1] = dp[i - 1][1];
            dp[i][2] = dp[i - 1][2];
            for (int j = 0; j < i; j++) {
                if (s[i] > s[j]) {
                    dp[i][0] = Math.max(dp[i][0], dp[j][0] + 1);
                    dp[i][2] = s[i];
                } else if (s[i] == s[j] && s[i] == dp[i][2] && dp[i][1] < k) {
                    dp[i][1]++;
                    dp[i][0] = Math.max(dp[i][0], dp[j][0] + 1);
                }
            }
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, dp[i][0]);
        }

        bw.write(Integer.toString(max));
        bw.flush();
    }
}
