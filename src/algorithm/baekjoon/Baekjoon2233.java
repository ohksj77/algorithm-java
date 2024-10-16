package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baekjoon2233 {
    private static int s;
    private static int[] depth;
    private static int[][] parent;

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        final int n = Integer.parseInt(br.readLine());
        s = 0;

        for (int i = 1; i <= n; i *= 2) {
            s++;
        }
        depth = new int[n + 1];
        parent = new int[s][n + 1];

        final String input = br.readLine();

        int[] nods = new int[2 * n + 1];
        int[] zeros = new int[n + 1];
        int[] ones = new int[n + 1];
        int length = input.length();

        int now = 1;
        int index = 1;

        for (int i = 1; i <= length; i++) {
            if (input.charAt(i - 1) == '0') {
                parent[0][index] = now;
                depth[index] = depth[now] + 1;
                nods[i] = index;
                zeros[index] = i;
                now = index++;
            } else {
                nods[i] = now;
                ones[now] = i;
                now = parent[0][now];
            }
        }
        for (int i = 1; i < s; i++) {
            for (int j = 1; j <= n; j++) {
                parent[i][j] = parent[i - 1][parent[i - 1][j]];
            }
        }
        st = new StringTokenizer(br.readLine());
        final int a = Integer.parseInt(st.nextToken());
        final int b = Integer.parseInt(st.nextToken());
        final int A = nods[a];
        final int B = nods[b];
        final int lca = lca(A, B);
        bw.write(zeros[lca] + " " + ones[lca]);
        bw.flush();
    }

    private static int lca(int a, int b) {
        if (depth[a] > depth[b]) {
            final int temp = a;
            a = b;
            b = temp;
        }

        for (int i = s - 1; i >= 0; i--) {
            if (depth[a] <= depth[parent[i][b]]) {
                b = parent[i][b];
            }
        }
        if (a == b) {
            return a;
        }

        for (int i = s - 1; i >= 0; i--) {
            if (parent[i][a] != parent[i][b]) {
                a = parent[i][a];
                b = parent[i][b];
            }
        }
        return parent[0][a];
    }
}
