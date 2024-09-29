package algorithm.codetree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class 부분_수열의_합_맞추기 {

    private static int n;
    private static int k;
    private static int[] numbers;

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final String[] nk = br.readLine().split(" ");
        n = Integer.parseInt(nk[0]);
        k = Integer.parseInt(nk[1]);
        numbers = new int[n];

        final String[] input = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(input[i]);
        }

        bw.write(Integer.toString(find()));
        bw.flush();
    }

    private static int find() {
        final int[][] dp = new int[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }

        for (int i = 1; i <= n; i++) {
            int number = numbers[i - 1];

            for (int j = 1; j <= k; j++) {
                dp[i][j] = dp[i - 1][j];

                if (j >= number && dp[i - 1][j - number] != Integer.MAX_VALUE) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - number] + 1);
                }
            }
        }

        if (dp[n][k] == Integer.MAX_VALUE) {
            return -1;
        }
        return dp[n][k];
    }
}
