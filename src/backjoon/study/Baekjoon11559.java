package backjoon.study;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Baekjoon11559 {
    private static char[][] map = new char[12][6];
    private static int pop = 0;
    private static boolean isPop;
    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {-1, 1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 12; i++) {
            String string = br.readLine();
            for (int j = 0; j < 6; j++) {
                map[i][j] = string.charAt(j);
            }
        }
        while (true) {
            isPop = false;
            bfs();
            onFloor();
            if (!isPop) {
                break;
            }
            pop++;
        }
        bw.write(String.valueOf(pop));
        bw.flush();
    }

    private static void bfs() {
        Queue<Puyo> queue = new LinkedList<>();
        boolean[][] visited = new boolean[12][6];

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                if (map[i][j] != '.' && !visited[i][j]) {
                    List<int[]> list = new ArrayList<>();
                    queue.add(new Puyo(i, j, map[i][j]));
                    list.add(new int[]{i, j});
                    visited[i][j] = true;

                    while(!queue.isEmpty()) {
                        Puyo p = queue.poll();

                        int curX = p.x;
                        int curY = p.y;
                        char curColor = p.color;

                        for (int k = 0; k < 4; k++) {
                            int nx = curX + dx[k];
                            int ny = curY + dy[k];

                            if (nx < 0 || ny < 0 || nx >= 12 || ny >= 6) continue;

                            if (!visited[nx][ny] && map[nx][ny] == curColor) {
                                queue.add(new Puyo(nx, ny, map[nx][ny]));
                                list.add(new int[] {nx, ny});
                                visited[nx][ny] = true;
                            }
                        }
                    }

                    if (list.size() >= 4) {
                        for (int m = 0; m < list.size(); m++) {
                            int[] arr = list.get(m);
                            int x = arr[0];
                            int y = arr[1];
                            map[x][y] = '.';
                            isPop = true;
                        }
                    }
                }
            }
        }
    }
    private static void onFloor() {
        for (int j = 0; j < 6; j++) {
            down(j);
        }
    }

    private static void down(int j) {
        Queue<Puyo> puyo = new LinkedList<>();
        int index = 11;
        for (int i = 11; i >= 0; i--) {
            if (map[i][j] != '.') {
                puyo.add(new Puyo(i, j, map[i][j]));
                map[i][j] = '.';
            }
        }
        while (!puyo.isEmpty()) {
            Puyo p = puyo.poll();
            char color = p.color;
            map[index][j] = color;
            index--;
        }
    }

    private static class Puyo {
        int x;
        int y;
        char color;
        public Puyo(int x, int y, char color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }
    }
}
