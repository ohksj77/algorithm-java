package algorithm.study.first;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon1135 {
    private static List<Integer>[] tree;
    private static int[] dp;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        dp = new int[n];
        tree = new List[n];

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        st.nextToken();
        for (int i = 1; i < n; i++) {
            tree[Integer.parseInt(st.nextToken())].add(i);
        }
        bw.write(String.valueOf(dfs(0)));
        bw.flush();
    }

    private static int dfs(int cur) {
        int cnt = 0, max = 0;
        PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
        for (int next : tree[cur]) {
            dp[next] = dfs(next);
            q.add(new int[]{next, dp[next]});
        }
        while (!q.isEmpty()) {
            int[] node = q.poll();
            max = Math.max(max, node[1] + ++cnt);
        }
        return max;
    }
}
