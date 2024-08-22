package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon1197 {
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new java.io.OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        final int v = Integer.parseInt(st.nextToken());
        final int e = Integer.parseInt(st.nextToken());

        final Queue<Node> nodes = new PriorityQueue<>();

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());

            final int a = Integer.parseInt(st.nextToken());
            final int b = Integer.parseInt(st.nextToken());
            final int val = Integer.parseInt(st.nextToken());

            nodes.add(new Node(a, b, val));
        }

        int sum = 0;
        parent = new int[v + 1];

        for (int i = 1; i <= v; i++) {
            parent[i] = i;
        }

        while (!nodes.isEmpty()) {
            final Node node = nodes.poll();
            final int a = node.a;
            final int b = node.b;
            final int val = node.val;

            if (find(a) == find(b)) {
                continue;
            }

            sum += val;
            union(a, b);
        }

        bw.write(Integer.toString(sum));
        bw.flush();
    }

    private static int find(final int a) {
        if (parent[a] == a) {
            return a;
        }
        return find(parent[a]);
    }

    private static void union(int a, int b) {
        final int findA = find(a);
        final int findB = find(b);

        if (findA != findB) {
            parent[findB] = findA;
        }
    }

    private static class Node implements Comparable<Node> {
        private final int a;
        private final int b;
        private final int val;

        public Node(final int a, final int b, final int val) {
            this.a = a;
            this.b = b;
            this.val = val;
        }

        @Override
        public int compareTo(final Node a) {
            final int val = a.val;

            if (this.val > val) {
                return 1;
            }
            return -1;
        }
    }
}
