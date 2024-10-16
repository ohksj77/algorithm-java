package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon4803 {

    private static List<List<Integer>> graph;
    private static boolean[] visited;

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int testCase = 1;

        while (true) {
            st = new StringTokenizer(br.readLine(), " ");

            final int n = Integer.parseInt(st.nextToken());
            final int m = Integer.parseInt(st.nextToken());

            if (n == 0 && m == 0) {
                break;
            }

            graph = new ArrayList<>();
            for (int i = 0; i < n + 1; i++) {
                graph.add(new ArrayList<>());
            }

            visited = new boolean[n + 1];

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                final int a = Integer.parseInt(st.nextToken());
                final int b = Integer.parseInt(st.nextToken());

                graph.get(a).add(b);
                graph.get(b).add(a);
            }

            int tree = 0;
            for (int i = 1; i < n + 1; i++) {
                if (!visited[i]) {
                    tree += checkTree(i);
                }
            }
            bw.write("Case " + testCase + ": ");

            if (tree > 1) {
                bw.write("A forest of " + tree + " trees.");
            } else if (tree == 1) {
                bw.write("There is one tree.");
            } else {
                bw.write("No trees.");
            }
            bw.newLine();
            testCase++;
        }

        bw.flush();
    }

    private static int checkTree(final int root) {
        final Queue<Integer> q = new LinkedList<>();
        int node = 0;
        int edge = 0;

        q.add(root);

        while (!q.isEmpty()) {
            final int now = q.poll();

            if (visited[now]) {
                continue;
            }
            visited[now] = true;
            node++;

            for (int i = 0; i < graph.get(now).size(); i++) {
                final int next = graph.get(now).get(i);
                edge++;
                if (!visited[next]) {
                    q.add(next);
                }
            }
        }

        if ((edge / 2) + 1 == node) {
            return 1;
        }
        return 0;
    }
}
