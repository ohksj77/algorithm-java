package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon1175 {

    private static int n;
    private static int m;
    private static char[][] classRoom;
    private static final int[][] DR = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        final StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        classRoom = new char[n][];
        Node start = null;
        final Node[] end = new Node[2];

        for (int i = 0, index = 0; i < n; i++) {
            classRoom[i] = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                char now = classRoom[i][j];
                if (now == 'S') {
                    start = new Node(-1, i, j, 0, 0);
                    continue;
                }
                if (now == 'C') {
                    end[index++] = new Node(-1, i, j, 0, 0);
                }
            }
        }

        bw.write(Integer.toString(bfs(start, end)));
        bw.flush();
    }

    private static int bfs(final Node start, final Node[] end) {
        final boolean[][][][] visited = new boolean[4][n][m][3];

        for (int i = 0; i < 4; i++) {
            visited[i][start.x][start.y][0] = true;
        }
        final Queue<Node> q = new LinkedList<>();
        q.offer(start);

        while (!q.isEmpty()) {
            final Node now = q.poll();

            if (now.x == end[0].x && now.y == end[0].y && now.check != 1) {
                now.check += 1;
            } else if (now.x == end[1].x && now.y == end[1].y && now.check != 2) {
                now.check += 2;
            }
            if (now.check == 3) {
                return now.time;
            }

            for (int i = 0; i < 4; i++) {
                if (now.dir == i) {
                    continue;
                }
                int[] dr = DR[i];
                int nextX = now.x + dr[0];
                int nextY = now.y + dr[1];

                if (!isInRange(nextX, nextY)
                        || classRoom[nextX][nextY] == '#'
                        || visited[i][nextX][nextY][now.check]) {
                    continue;
                }
                visited[i][nextX][nextY][now.check] = true;
                q.offer(new Node(i, nextX, nextY, now.time + 1, now.check));
            }
        }
        return -1;
    }

    private static boolean isInRange(final int x, final int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    private static class Node {
        private final int dir;
        private final int x;
        private final int y;
        private final int time;
        private int check;

        public Node(final int dir, final int x, final int y, final int time, final int check) {
            this.dir = dir;
            this.x = x;
            this.y = y;
            this.time = time;
            this.check = check;
        }
    }
}
