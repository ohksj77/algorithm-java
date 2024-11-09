package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon2616 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] train = new int[n + 1];
        int[] sum = new int[n + 1];
        int[][] dp = new int[4][n + 1];

        String[] input = br.readLine().split(" ");
        for (int i = 1; i <= n; i++) {
            train[i] = Integer.parseInt(input[i - 1]);
            sum[i] = sum[i - 1] + train[i];
        }

        int m = Integer.parseInt(br.readLine());

        for (int i = 1; i <= 3; i++) {
            for (int j = i * m; j <= n; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j - m] + sum[j] - sum[j - m]);
            }
        }

        bw.write(Integer.toString(dp[3][n]));
        bw.flush();
    }
}
