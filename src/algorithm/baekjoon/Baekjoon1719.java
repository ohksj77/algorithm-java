package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon1719 {

    private static final int INF = 2_000_000;

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final String[] nm = br.readLine().split(" ");
        final int n = Integer.parseInt(nm[0]);
        final int m = Integer.parseInt(nm[1]);

        final int[][] arr = new int[n + 1][n + 1];
        final int[][] path = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(arr[i], INF);
            arr[i][i] = 0;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                path[i][j] = j;
            }
        }

        for (int i = 0; i < m; i++) {
            final String[] input = br.readLine().split(" ");
            final int a = Integer.parseInt(input[0]);
            final int b = Integer.parseInt(input[1]);
            final int c = Integer.parseInt(input[2]);

            final int min = Math.min(arr[a][b], c);
            arr[a][b] = min;
            arr[b][a] = min;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (arr[i][j] > arr[i][k] + arr[k][j]) {
                        arr[i][j] = arr[i][k] + arr[k][j];
                        path[i][j] = path[i][k];
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    bw.write("- ");
                    continue;
                }
                bw.write(path[i][j] + " ");
            }
            bw.newLine();
        }

        bw.flush();
    }
}
