package backjoon.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Baekjoon13459 {

    private static int n;
    private static int m;
    private static char[][] board;
    private static boolean[][][][] visited;
    private static final int[][] DR = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] nm = br.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);

        visited = new boolean[n][m][n][m];
        board = new char[n][m];

        Point red = null;
        Point blue = null;

        for (int i = 0; i < n; i++) {
            board[i] = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'R') {
                    red = new Point(i, j);
                    continue;
                }
                if (board[i][j] == 'B') {
                    blue = new Point(i, j);
                }
            }
        }

        int answer = bfs(red, blue);

        bw.write(Integer.toString(answer));
        bw.flush();
    }

    private static int bfs(Point red, Point blue) {
        Queue<Point[]> q = new LinkedList<>();
        q.add(new Point[] {red, blue});

        int count = 0;

        while (!q.isEmpty() && count < 10) {

            int length = q.size();

            for (int i = 0; i < length; i++) {
                Point[] now = q.poll();
                for (int d = 0; d < 4; d++) {
                    Point r = now[0].newInstance();
                    Point b = now[1].newInstance();

                    if (moveAll(r, b, d)) {
                        if (board[r.r][r.c] == 'O') {
                            return 1;
                        }
                        if (visited[r.r][r.c][b.r][b.c]) {
                            continue;
                        }
                        visited[r.r][r.c][b.r][b.c] = true;
                        q.add(new Point[] {r, b});
                    }
                }
            }
            count++;
        }
        return 0;
    }

    private static boolean moveAll(Point red, Point blue, int d) {
        boolean redFirst =
                (d == 0 && red.r < blue.r)
                        || (d == 1 && red.r > blue.r)
                        || (d == 2 && red.c < blue.c)
                        || (d == 3 && red.c > blue.c);

        move(red, d, red.r, red.c);
        move(blue, d, blue.r, blue.c);

        if (board[blue.r][blue.c] == 'O') {
            return false;
        }

        if (red.r == blue.r && red.c == blue.c) {
            moveToMakeDistinct(red, blue, d, redFirst);
        }
        return true;
    }

    private static void moveToMakeDistinct(
            final Point red, final Point blue, final int d, final boolean redFirst) {
        if (d == 0) {
            moveProperWay(() -> blue.r++, () -> red.r++, redFirst);
            return;
        }
        if (d == 1) {
            moveProperWay(() -> blue.r--, () -> red.r--, redFirst);
            return;
        }
        if (d == 2) {
            moveProperWay(() -> blue.c++, () -> red.c++, redFirst);
            return;
        }
        moveProperWay(() -> blue.c--, () -> red.c--, redFirst);
    }

    private static void moveProperWay(Move blue, Move red, boolean redFirst) {
        if (redFirst) {
            blue.move();
            return;
        }
        red.move();
    }

    private static void move(Point now, int d, int nx, int ny) {
        while (true) {
            nx += DR[d][0];
            ny += DR[d][1];

            if (board[nx][ny] == '#') {
                return;
            }
            now.r = nx;
            now.c = ny;
            if (board[nx][ny] == 'O') {
                return;
            }
        }
    }

    @FunctionalInterface
    private interface Move {
        void move();
    }

    private static class Point {
        private int r;
        private int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public Point newInstance() {
            return new Point(this.r, this.c);
        }
    }
}
