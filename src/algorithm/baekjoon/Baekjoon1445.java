package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class Baekjoon1445 {
    private static final int[][] DR = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    private static final PriorityQueue<Trash> PQ = new PriorityQueue<>();
    private static int n;
    private static int m;
    private static int sx;
    private static int sy;
    private static int ex;
    private static int ey;
    private static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] nm = br.readLine().split(" ");

        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                if (line.charAt(j) == 'g') map[i][j] = 1;
                else if (line.charAt(j) == 'F') {
                    ex = i;
                    ey = j;
                } else if (line.charAt(j) == 'S') {
                    sx = i;
                    sy = j;
                }
            }
        }
        Trash trash = bfs();
        bw.write(trash.g + " " + trash.ng);
        bw.flush();
    }

    private static Trash bfs() {
        PQ.add(new Trash(sx, sy));
        boolean[][] chk = new boolean[n][m];
        chk[sx][sy] = true;

        while (!PQ.isEmpty()) {
            Trash here = PQ.poll();
            int g = here.g;
            int ng = here.ng;
            if (here.x == ex && here.y == ey) {
                return here;
            }

            for (int i = 0; i < 4; i++) {
                int nx = here.x + DR[i][0];
                int ny = here.y + DR[i][1];
                if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                    continue;
                }
                if (chk[nx][ny]) {
                    continue;
                }
                if (map[nx][ny] == 1) {
                    PQ.add(new Trash(nx, ny, g + 1, ng));
                } else {
                    boolean flag = false;
                    for (int j = 0; j < 4; j++) {
                        int nnx = nx + DR[j][0];
                        int nny = ny + DR[j][1];
                        if (nnx < 0 || nny < 0 || nnx >= n || nny >= m) {
                            continue;
                        }
                        if (nx == ex && ny == ey) {
                            break;
                        }
                        if (map[nnx][nny] == 1) {
                            PQ.add(new Trash(nx, ny, g, ng + 1));
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) {
                        PQ.add(new Trash(nx, ny, g, ng));
                    }
                }
                chk[nx][ny] = true;
            }
        }
        return new Trash(-1, -1);
    }

    private static class Trash implements Comparable<Trash> {

        private final int x;
        private final int y;
        private int g;
        private int ng;

        public Trash(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Trash(int x, int y, int g, int ng) {
            this.x = x;
            this.y = y;
            this.g = g;
            this.ng = ng;
        }

        public int compareTo(Trash t) {
            if (g == t.g) {
                return ng - t.ng;
            }
            return g - t.g;
        }
    }
}
