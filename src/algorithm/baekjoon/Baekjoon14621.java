package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Baekjoon14621 {

    private static int[] parent;

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final String[] nm = br.readLine().split(" ");
        final int n = Integer.parseInt(nm[0]);
        final int m = Integer.parseInt(nm[1]);

        final String[] schoolType = br.readLine().split(" ");

        final List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            final String[] input = br.readLine().split(" ");
            final int u = Integer.parseInt(input[0]) - 1;
            final int v = Integer.parseInt(input[1]) - 1;
            final int weight = Integer.parseInt(input[2]);

            if (!schoolType[u].equals(schoolType[v])) {
                edges.add(new Edge(u, v, weight));
            }
        }

        Collections.sort(edges);

        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        int answer = 0;
        int count = 0;

        for (final Edge edge : edges) {
            if (find(edge.u) != find(edge.v)) {
                union(edge.u, edge.v);
                answer += edge.weight;
                count++;
            }
        }

        if (count != n - 1) {
            bw.write("-1");
        } else {
            bw.write(Integer.toString(answer));
        }
        bw.flush();
    }

    private static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    private static void union(int x, int y) {
        final int rootX = find(x);
        final int rootY = find(y);
        if (rootX != rootY) {
            parent[rootY] = rootX;
        }
    }

    private static class Edge implements Comparable<Edge> {
        private final int u;
        private final int v;
        private final int weight;

        public Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }
}
