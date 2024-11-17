package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Baekjoon2151 {

    private static final int[][] DR = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static final int[] START = new int[2];
    private static char[][] map;
    private static int n;
    private static int res;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        map = new char[n][n];
        boolean flag = true;

        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < n; j++) {
                char idx = input.charAt(j);
                map[i][j] = idx;
                if (idx == '#') {
                    if (flag) {
                        START[0] = i;
                        START[1] = j;
                        map[i][j] = '@';
                        flag = false;
                    } else {
                        map[i][j] = '$';
                    }
                }
            }
        }

        res = Integer.MAX_VALUE;
        dijkstra();

        bw.write(Integer.toString(res));
        bw.flush();
    }

    private static void dijkstra() {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));

        boolean[][][] v = new boolean[4][n][n];
        for (int i = 0; i < 4; i++) {
            v[i][START[0]][START[1]] = true;
        }

        for (int i = 0; i < 4; i++) {
            int nx = START[0] + DR[i][0];
            int ny = START[1] + DR[i][1];
            if (nx < 0 || ny < 0 || nx >= n || ny >= n || map[nx][ny] == '*') {
                continue;
            }
            pq.offer(new int[] {nx, ny, 0, i});
        }

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            int x = cur[0];
            int y = cur[1];

            v[cur[3]][x][y] = true;

            if (map[x][y] == '$') {
                res = cur[2];
                return;
            }

            if (map[x][y] == '!') {
                if (cur[3] == 2 || cur[3] == 3) {
                    for (int i = 0; i < 2; i++) {
                        int nx = x + DR[i][0];
                        int ny = y + DR[i][1];
                        if (nx < 0
                                || ny < 0
                                || nx >= n
                                || ny >= n
                                || v[i][nx][ny]
                                || map[nx][ny] == '*') {
                            continue;
                        }
                        pq.offer(new int[] {nx, ny, cur[2] + 1, i});
                    }
                } else {
                    for (int i = 2; i < 4; i++) {
                        int nx = x + DR[i][0];
                        int ny = y + DR[i][1];
                        if (nx < 0
                                || ny < 0
                                || nx >= n
                                || ny >= n
                                || v[i][nx][ny]
                                || map[nx][ny] == '*') {
                            continue;
                        }
                        pq.offer(new int[] {nx, ny, cur[2] + 1, i});
                    }
                }
            }

            int nx = x + DR[cur[3]][0];
            int ny = y + DR[cur[3]][1];
            if (nx < 0 || ny < 0 || nx >= n || ny >= n || v[cur[3]][nx][ny] || map[nx][ny] == '*') {
                continue;
            }
            pq.offer(new int[] {nx, ny, cur[2], cur[3]});
        }
    }
}
