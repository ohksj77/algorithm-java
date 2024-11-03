package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.Queue;

public class Baekjoon1414 {

    private static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int totalLength = 0;

        Queue<Edge> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            char[] arr = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                if (arr[j] == '0') {
                    continue;
                }
                int t = toLength(arr[j]);
                totalLength += t;
                if (i != j && t != 0) {
                    pq.add(new Edge(i, j, toLength(arr[j])));
                }
            }
        }

        parent = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        int mstLength = 0;
        int used = 0;

        while (!pq.isEmpty()) {
            Edge now = pq.poll();

            if (find(now.st) != find(now.end)) {
                union(now.st, now.end);
                mstLength += now.w;
                used++;
            }
        }
        if (used == n - 1) {
            bw.write(Integer.toString(totalLength - mstLength));
        } else {
            bw.write("-1");
        }
        bw.flush();
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[b] = a;
        }
    }

    private static int find(int a) {
        if (a == parent[a]) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }

    private static int toLength(char ele) {
        if (ele >= 'a' && ele <= 'z') {
            return ele - 'a' + 1;
        }
        return ele - 'A' + 27;
    }

    private static class Edge implements Comparable<Edge> {
        private final int st;
        private final int end;
        private final int w;

        public Edge(int s, int e, int w) {
            this.st = s;
            this.end = e;
            this.w = w;
        }

        @Override
        public int compareTo(Edge v) {
            return this.w > v.w ? 1 : -1;
        }
    }
}
