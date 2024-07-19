package algorithm.codetree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 최솟값_최댓값_차이_최소화하기 {

    private static int n;
    private static int[][] grid;
    private static int[] cols;
    private static boolean[] visited;
    private static int minDifference = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        grid = new int[n][n];
        cols = new int[n];
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            final StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        findMinDifference(0);

        bw.write(Integer.toString(minDifference));
        bw.flush();
    }

    private static void findMinDifference(final int row) {
        if (row == n) {
            evaluate();
            return;
        }

        for (int col = 0; col < n; col++) {
            if (!visited[col]) {
                visited[col] = true;
                cols[row] = col;
                findMinDifference(row + 1);
                visited[col] = false;
            }
        }
    }

    private static void evaluate() {
        int minVal = Integer.MAX_VALUE;
        int maxVal = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            final int value = grid[i][cols[i]];
            minVal = Math.min(minVal, value);
            maxVal = Math.max(maxVal, value);
        }

        final int difference = maxVal - minVal;
        minDifference = Math.min(minDifference, difference);
    }
}
