package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon1045 {

    private static final Queue<Edge> EDGES = new LinkedList<>();
    private static final StringBuilder ANSWER = new StringBuilder();
    private static int N, M;
    private static char[][] map;
    private static int[] roots;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        roots = new int[N];
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            roots[i] = i;
            for (int j = i + 1; j < N; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'Y') {
                    EDGES.add(new Edge(i, j));
                }
            }
        }
        solve();
        bw.write(ANSWER.toString());
        bw.flush();
    }

    private static void solve() {
        int unionCount = 0;
        int[] count = new int[N];
        if (EDGES.size() < M) {
            ANSWER.append(-1);
            return;
        }

        Queue<Edge> left = new LinkedList<>();
        while (!EDGES.isEmpty()) {
            Edge here = EDGES.poll();
            if (union(here.s, here.e)) {
                count[here.s]++;
                count[here.e]++;
                unionCount++;
            } else {
                left.add(here);
            }
        }
        if (unionCount != N - 1) {
            ANSWER.append(-1);
            return;
        }
        for (int i = N - 1; i < M; i++) {
            if (left.isEmpty()) {
                ANSWER.append(-1);
                return;
            }
            count[left.peek().s]++;
            count[left.peek().e]++;
            left.poll();
        }
        for (int i = 0; i < N; i++) {
            ANSWER.append(count[i]).append(" ");
        }
    }

    private static boolean union(int s, int e) {
        s = find(s);
        e = find(e);

        if (s == e) {
            return false;
        } else if (s < e) {
            roots[e] = s;
        } else {
            roots[s] = e;
        }
        return true;
    }

    private static int find(int s) {
        if (roots[s] == s) return s;
        return roots[s] = find(roots[s]);
    }

    private static class Edge {
        private final int s;
        private final int e;

        public Edge(int s, int e) {
            this.s = s;
            this.e = e;
        }
    }
}
