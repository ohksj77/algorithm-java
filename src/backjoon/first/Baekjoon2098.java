package backjoon.first;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon2098 {
    private static int n;
    private static int[][] w, dp;
    private static int INF = 16_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        w = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                w[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[n][(1 << n) - 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        bw.write(Integer.toString(dfs(0, 1)));
        bw.flush();
    }

    private static int dfs(int now, int visit) {

        if (visit == (1 << n) - 1) {
            if (w[now][0] == 0) {
                return INF;
            }
            return w[now][0];
        }

        if (dp[now][visit] != -1) {
            return dp[now][visit];
        }
        dp[now][visit] = INF;

        for (int i = 0; i < n; i++) {
            if ((visit & (1 << i)) == 0 && w[now][i] != 0) {
                dp[now][visit] = Math.min(dfs(i, visit | (1 << i)) + w[now][i], dp[now][visit]);
            }
        }
        return dp[now][visit];
    }
}
