package backjoon.study.second;

import java.io.*;
import java.util.*;

public class Baekjoon16988 {
    private static int[][] board;
    private static int n, m, result = 0;
    private static boolean[][] visited;
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};
    private static List<BadukDol> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 0) {
                    list.add(new BadukDol(i, j));
                }
            }
        }

        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                BadukDol a = list.get(i);
                BadukDol b = list.get(j);
                board[a.x][a.y] = 1;
                board[b.x][b.y] = 1;
                bfs();
                board[a.x][a.y] = 0;
                board[b.x][b.y] = 0;
                visited = new boolean[n][m];
            }
        }

        bw.write(String.valueOf(result));
        bw.flush();
    }

    private static void bfs() {
        Queue<BadukDol> q;
        int total = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 2 && !visited[i][j]) {
                    q = new LinkedList<>();
                    visited[i][j] = true;
                    q.offer(new BadukDol(i, j));
                    int count = 1;
                    int empty = 0;
                    while (!q.isEmpty()) {
                        BadukDol b = q.poll();
                        for (int k = 0; k < 4; k++) {
                            int nx = b.x + dx[k];
                            int ny = b.y + dy[k];
                            if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny] || board[nx][ny] == 1) {
                                continue;
                            }
                            if (board[nx][ny] == 0) {
                                empty++;
                            }
                            if (board[nx][ny] == 2) {
                                visited[nx][ny] = true;
                                q.offer(new BadukDol(nx, ny));
                                count++;
                            }
                        }
                    }
                    if (empty > 0) {
                        continue;
                    }
                    total += count;
                }
            }
        }
        if (total > result) {
            result = total;
        }
    }

    private static class BadukDol {
        int x;
        int y;

        public BadukDol(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
