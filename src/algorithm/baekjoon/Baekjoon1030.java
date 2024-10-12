package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baekjoon1030 {
    private static int[][] graph;
    private static int s;
    private static int n;
    private static int k;
    private static int r1;
    private static int r2;
    private static int c1;
    private static int c2;

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        final StringTokenizer st = new StringTokenizer(br.readLine());

        s = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        r1 = Integer.parseInt(st.nextToken());
        r2 = Integer.parseInt(st.nextToken());
        c1 = Integer.parseInt(st.nextToken());
        c2 = Integer.parseInt(st.nextToken());
        graph = new int[r2 - r1 + 1][c2 - c1 + 1];

        final int size = (int) Math.pow(n, s);

        recursion(0, 0, size, 0, false);

        for (int[] ints : graph) {
            for (int n : ints) {
                bw.write(Integer.toString(n));
            }
            bw.newLine();
        }

        bw.flush();
    }

    private static void recursion(
            final int r, final int c, final int length, final int second, final boolean isBlack) {

        if ((r < r1 && r + length <= r1) || (c < c1 && c + length <= c1) || r > r2 || c > c2) {
            return;
        }

        if (length == 1 && isBlack) {
            graph[r - r1][c - c1] = 1;
            return;
        }

        final int div = length / n;
        final int blackPoint = ((n - k) / 2) * div;
        final int blackR = blackPoint + r;
        final int blackC = blackPoint + c;

        if (second < s) {
            for (int i = r; i < r + length; i += div) {
                for (int j = c; j < c + length; j += div) {

                    recursion(
                            i,
                            j,
                            div,
                            second + 1,
                            i >= blackR
                                            && j >= blackC
                                            && i < blackR + (k * div)
                                            && j < blackC + (k * div)
                                    || isBlack);
                }
            }
        }
    }
}
