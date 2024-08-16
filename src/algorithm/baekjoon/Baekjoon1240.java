package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Baekjoon1240 {

    private static int n, m;
    private static List<Node>[] list;
    private static int[] dist;
    private static boolean[] visited;

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final String[] input = br.readLine().split(" ");

        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        list = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            final String[] now = br.readLine().split(" ");
            final int s = Integer.parseInt(now[0]);
            final int d = Integer.parseInt(now[1]);
            final int v = Integer.parseInt(now[2]);
            list[s].add(new Node(d, v));
            list[d].add(new Node(s, v));
        }

        for (int i = 0; i < m; i++) {
            final String[] now = br.readLine().split(" ");
            final int start = Integer.parseInt(now[0]);
            final int end = Integer.parseInt(now[1]);
            dist = new int[n + 1];
            Arrays.fill(dist, Integer.MAX_VALUE);
            visited = new boolean[n + 1];
            dijkstra(start);
            bw.write(Integer.toString(dist[end]));
            bw.newLine();
        }
        bw.flush();
    }

    private static void dijkstra(final int s) {
        final Queue<Node> q = new PriorityQueue<>();
        dist[s] = 0;
        q.offer(new Node(s, 0));

        while (!q.isEmpty()) {
            final Node current = q.poll();

            if (!visited[current.n]) {
                visited[current.n] = true;
            } else {
                continue;
            }

            for (int i = 0; i < list[current.n].size(); i++) {
                final Node next = list[current.n].get(i);
                if (dist[next.n] > dist[current.n] + next.v) {
                    dist[next.n] = dist[current.n] + next.v;
                    q.offer(new Node(next.n, dist[next.n]));
                }
            }
        }
    }

    private static class Node implements Comparable<Node> {
        private final int n;
        private final int v;

        public Node(final int n, final int v) {
            this.n = n;
            this.v = v;
        }

        @Override
        public int compareTo(Node node) {
            return this.v - node.v;
        }
    }
}
