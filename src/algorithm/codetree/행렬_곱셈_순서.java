package algorithm.codetree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class 행렬_곱셈_순서 {

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int n = Integer.parseInt(br.readLine());
        final int[][] array = new int[n][];

        for (int i = 0; i < n; i++) {
            final String[] input = br.readLine().split(" ");
            array[i] = new int[] {Integer.parseInt(input[0]), Integer.parseInt(input[1])};
        }

        int[][] dp = new int[n][n];

        for (int len = 1; len < n; len++) {
            for (int i = 0; i < n - len; i++) {
                final int j = i + len;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    final int cost =
                            dp[i][k] + dp[k + 1][j] + array[i][0] * array[k][1] * array[j][1];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }

        bw.write(Integer.toString(dp[0][n - 1]));
        bw.flush();
    }
}
