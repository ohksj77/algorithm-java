package algorithm.programmers;

public class FullCrime {

    public int solution(int[][] info, int n, int m) {
        int num = info.length;
        int INF = 100000;

        int[][][] dp = new int[num + 1][n][m];

        for (int i = 0; i <= num; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    dp[i][j][k] = INF;
                }
            }
        }

        dp[0][0][0] = 0;

        for (int i = 0; i < num; i++) {
            int a = info[i][0];
            int b = info[i][1];

            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    if (dp[i][j][k] == INF) {
                        continue;
                    }
                    if (j + a < n) {
                        dp[i + 1][j + a][k] = Math.min(dp[i + 1][j + a][k], dp[i][j][k] + a);
                    }
                    if (k + b < m) {
                        dp[i + 1][j][k + b] = Math.min(dp[i + 1][j][k + b], dp[i][j][k]);
                    }
                }
            }
        }

        int answer = INF;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                answer = Math.min(answer, dp[num][i][j]);
            }
        }
        return answer == INF ? -1 : answer;
    }
}
