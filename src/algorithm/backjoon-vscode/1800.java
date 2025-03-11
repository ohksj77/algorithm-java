import java.io.*;
import java.util.*;

class Main {
    private static int n;
    private static int p;
    private static int k;
    private static int ans = Integer.MAX_VALUE;
    private static List<int[]>[] adj;
    private static boolean[] costCheck;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] npk = br.readLine().split(" ");
        n = Integer.parseInt(npk[0]);
        p = Integer.parseInt(npk[1]);
        k = Integer.parseInt(npk[2]);

        adj = new List[n + 1];

        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < p; i++) {
            String[] abcost = br.readLine().split(" ");
            int a = Integer.parseInt(abcost[0]);
            int b = Integer.parseInt(abcost[1]);
            int cost = Integer.parseInt(abcost[2]);

            adj[a].add(new int[]{b, cost});
            adj[b].add(new int[]{a, cost});
        }

        divide(0, 1000000);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(Integer.toString(ans == Integer.MAX_VALUE ? -1 : ans));
        bw.flush();
    }

    private static void divide(int start, int end) {
        while (start <= end) {
            int mid = (start + end) / 2;

            if (dfs(mid)) {
                ans = mid;
                end = mid - 1;
                continue;
            }
            start = mid + 1;
        }
    }

    private static boolean dfs(int x) {
        Deque<int[]> stack = new ArrayDeque<>();
        boolean[][] visit = new boolean[n + 1][k + 1];

        stack.push(new int[]{1, k});

        while (!stack.isEmpty()) {
            int[] cur = stack.pop();
            if (cur[0] == n) {
                return true;
            }

            if (visit[cur[0]][cur[1]]) {
                continue;
            }
            visit[cur[0]][cur[1]] = true;

            for (int[] a : adj[cur[0]]) {
                if (a[1] <= x) {
                    stack.push(new int[]{a[0], cur[1]});
                } else if (cur[1] > 0) { 
                    stack.push(new int[]{a[0], cur[1] - 1});
                }
            }
        }
        return false;
    }
}
