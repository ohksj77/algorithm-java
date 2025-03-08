import java.io.*;
import java.util.*;

class Main {

    private static int[][] map;
    private static boolean[][][] visited;
    private static int m, n, t;
    private static final int[][] DR = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        map = new int[m][n];
        visited = new boolean[2][m][n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = bfs();
        System.out.println(result <= t ? result : "Fail");
    }

    private static int bfs() {
        Queue<Location> q = new LinkedList<>();
        q.offer(new Location(0, 0, 0, false));
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            Location cur = q.poll();

            if (cur.x == m - 1 && cur.y == n - 1) {
                return cur.time;
            }

            for (int[] dr : DR) {
                int nx = cur.x + dr[0];
                int ny = cur.y + dr[1];

                if (nx < 0 || ny < 0 || nx >= m || ny >= n) {
                    continue;
                }

                if (!cur.hasGram) {
                    if (!visited[0][nx][ny] && map[nx][ny] != 1) {
                        visited[map[nx][ny] == 2 ? 1 : 0][nx][ny] = true;
                        q.offer(new Location(nx, ny, cur.time + 1, map[nx][ny] == 2));
                    }
                } else {
                    if (!visited[1][nx][ny]) {
                        visited[1][nx][ny] = true;
                        q.offer(new Location(nx, ny, cur.time + 1, true));
                    }
                }
            }
        }
        return 10001;
    }

    private static class Location {
        int x, y, time;
        boolean hasGram;

        public Location(int x, int y, int time, boolean hasGram) {
            this.x = x;
            this.y = y;
            this.time = time;
            this.hasGram = hasGram;
        }
    }
}
