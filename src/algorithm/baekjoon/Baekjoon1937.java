package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon1937 {

    private static final int[][] DR = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static int[][] map;
    private static int[][] dp;
    private static int n;

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        map = new int[n][];
        dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int answer = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                answer = Math.max(answer, dfs(i, j));
            }
        }

        bw.write(Integer.toString(answer));
        bw.flush();
    }

    private static int dfs(final int x, final int y) {
        if (dp[x][y] != 0) {
            return dp[x][y];
        }

        dp[x][y] = 1;

        for (int i = 0; i < 4; i++) {
            final int nx = x + DR[i][0];
            final int ny = y + DR[i][1];

            if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
                continue;
            }

            if (map[nx][ny] > map[x][y]) {
                dp[x][y] = Math.max(dp[x][y], dfs(nx, ny) + 1);
            }
        }

        return dp[x][y];
    }
}
