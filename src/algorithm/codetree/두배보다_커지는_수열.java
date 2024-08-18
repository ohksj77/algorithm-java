package algorithm.codetree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class 두배보다_커지는_수열 {

    private static final long MOD = 1_000_000_007;
    private static long[][] dp;
    private static int n;
    private static int m;

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        dp = new long[n + 1][m + 1]; // 수열의 길이가 i이고 마지막 수가 j인 수열의 개수

        for (int i = 1; i < m + 1; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                long sum = 0;
                for (int k = 1; k < j / 2 + 1; k++) {
                    sum += dp[i - 1][k] % MOD;
                }
                dp[i][j] = sum % MOD;
            }
        }

        long answer = 0;
        for (int i = 1; i < m + 1; i++) {
            answer += dp[n][i] % MOD;
        }

        bw.write(Long.toString(answer % MOD));
        bw.flush();
    }
}
