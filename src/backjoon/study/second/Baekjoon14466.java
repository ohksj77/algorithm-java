package backjoon.study.second;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

public class Baekjoon14466 {
    private static int n, k, r;
    private static boolean[][] visited;
    private static Point[] cows;
    private static List<Point>[][] bridges;
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        cows = new Point[k];
        bridges = new List[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                bridges[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;

            bridges[a][b].add(new Point(c, d));
            bridges[c][d].add(new Point(a, b));
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            cows[i] = new Point(a, b);
        }

        bw.write(String.valueOf(run()));
        bw.flush();
    }

    private static int run() {
        int cnt = 0;

        for (int c = 0; c < k; c++) {
            visited = new boolean[n][n];
            dfs(cows[c].x, cows[c].y);
            for (int nc = c; nc < k; nc++) {
                Point cow = cows[nc];
                if (!visited[cow.x][cow.y]) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static void dfs(int x, int y) {
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = x + dy[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
            if (visited[nx][ny]) continue;
            if (bridges[x][y].contains(new Point(nx, ny))) continue;

            dfs(nx, ny);
        }
    }

    private static class Point {
        private int x;
        private int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
