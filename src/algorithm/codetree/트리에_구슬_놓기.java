package algorithm.codetree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 트리에_구슬_놓기 {

    private static List<Integer>[] tree;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int n = Integer.parseInt(br.readLine());
        tree = new List[n + 1];
        dp = new int[n + 1][2];

        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            final StringTokenizer st = new StringTokenizer(br.readLine());
            final int u = Integer.parseInt(st.nextToken());
            final int v = Integer.parseInt(st.nextToken());
            tree[u].add(v);
            tree[v].add(u);
        }

        dfs(1, -1);
        final int result = Math.min(dp[1][0], dp[1][1]);

        bw.write(Integer.toString(result));
        bw.flush();
    }

    private static void dfs(final int node, final int parent) {
        dp[node][0] = 0;
        dp[node][1] = 1;

        for (final int child : tree[node]) {
            if (child != parent) {
                dfs(child, node);
                dp[node][0] += dp[child][1];
                dp[node][1] += Math.min(dp[child][0], dp[child][1]);
            }
        }
    }
}
