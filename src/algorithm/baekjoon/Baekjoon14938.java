package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon14938 {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final String[] nmr = br.readLine().split(" ");
        final int n = Integer.parseInt(nmr[0]);
        final int m = Integer.parseInt(nmr[1]);
        final int r = Integer.parseInt(nmr[2]);
        final int[] items =
                Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        final int[][] map = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(map[i], 10001);
            map[i][i] = 0;
        }

        for (int i = 0; i < r; i++) {
            final String[] input = br.readLine().split(" ");
            final int a = Integer.parseInt(input[0]) - 1;
            final int b = Integer.parseInt(input[1]) - 1;
            final int l = Integer.parseInt(input[2]);
            map[a][b] = l;
            map[b][a] = l;
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }

        int max = 0;

        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                if (map[i][j] <= m) {
                    sum += items[j];
                }
            }
            max = Math.max(max, sum);
        }

        bw.write(Integer.toString(max));
        bw.flush();
    }
}
