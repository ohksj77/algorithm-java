package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Baekjoon11437 {

    private static List<Integer>[] list;
    private static int[] parent;
    private static int[] depth;

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        final int n = Integer.parseInt(br.readLine());

        parent = new int[n + 1];
        depth = new int[n + 1];
        list = new List[n + 1];

        for (int i = 1; i < n + 1; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            final StringTokenizer st = new StringTokenizer(br.readLine());
            final int a = Integer.parseInt(st.nextToken());
            final int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        init(1, 1, 0);

        final StringBuilder sb = new StringBuilder();
        final int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            final StringTokenizer st = new StringTokenizer(br.readLine());
            final int a = Integer.parseInt(st.nextToken());
            final int b = Integer.parseInt(st.nextToken());
            sb.append(lca(a, b)).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    private static void init(final int cur, final int h, final int pa) {
        depth[cur] = h;
        parent[cur] = pa;
        for (final int nxt : list[cur]) {
            if (nxt != pa) {
                init(nxt, h + 1, cur);
            }
        }
    }

    private static int lca(int a, int b) {
        int ah = depth[a];
        int bh = depth[b];

        while (ah > bh) {
            a = parent[a];
            ah--;
        }

        while (bh > ah) {
            b = parent[b];
            bh--;
        }

        while (a != b) {
            a = parent[a];
            b = parent[b];
        }
        return a;
    }
}
