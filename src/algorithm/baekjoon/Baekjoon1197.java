package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon1197 {

    private static final PriorityQueue<Edge> pq = new PriorityQueue<>();
    private static int[] arr;
    private static int V;
    private static int E;
    private static int sum = 0;
    private static int edgeCount = 0;

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        arr = new int[V + 1];

        for (int i = 1; i <= V; i++) {
            arr[i] = i;
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            final int s = Integer.parseInt(st.nextToken());
            final int e = Integer.parseInt(st.nextToken());
            final int w = Integer.parseInt(st.nextToken());

            pq.offer(new Edge(s, e, w));
        }

        kruskal();
        bw.write(Integer.toString(sum));
        bw.flush();
    }

    private static void kruskal() {
        while (!pq.isEmpty()) {
            final Edge edge = pq.poll();
            if (find(edge.s) == find(edge.e)) {
                continue;
            }
            union(edge.s, edge.e);
            sum += edge.w;
            edgeCount++;
            if (edgeCount > V - 1) {
                break;
            }
        }
    }

    private static void union(final int a, final int b) {
        int A = find(a);
        int B = find(b);
        if (A > B) {
            arr[A] = B;
            return;
        }
        arr[B] = A;
    }

    private static int find(final int a) {
        if (arr[a] == a) {
            return a;
        }
        final int rep = find(arr[a]);
        arr[a] = rep;
        return rep;
    }

    private static class Edge implements Comparable<Edge> {
        public final int s;
        public final int e;
        public final int w;

        public Edge(final int s, final int e, final int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Edge edge) {
            return Integer.compare(this.w, edge.w);
        }
    }
}
