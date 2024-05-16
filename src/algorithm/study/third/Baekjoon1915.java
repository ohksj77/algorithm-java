package algorithm.study.third;

import java.io.*;
import java.util.StringTokenizer;

public class Baekjoon1915 {
    private static int[][] dp;
    private static char[][] board;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        board = new char[n][m];
        dp = new int[n][m];

        int max = 0;
        for (int i = 0; i < n; i++) {
            board[i] = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                dp[i][j] = board[i][j] - '0';
                if (dp[i][j] == 1 && max == 0) max = 1;
                if (j > 0 && i > 0) {
                    if (dp[i - 1][j] > 0 && dp[i][j - 1] > 0 && dp[i - 1][j - 1] > 0 && dp[i][j] == 1) {
                        dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                        max = Math.max(dp[i][j], max);
                    }
                }
            }
        }
        bw.write(String.valueOf(max * max));
        bw.flush();
    }
}
