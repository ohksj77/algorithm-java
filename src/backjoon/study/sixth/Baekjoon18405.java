package backjoon.study.sixth;

import java.io.*;
import java.util.*;

public class Baekjoon18405 {
    static int[][] board;
    static Queue<int[]> q = new LinkedList<>();
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int n, k, s, x, y;
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[n][n];

        List<int[]> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int input = Integer.parseInt(st.nextToken());
                board[i][j] = input;
                if (input != 0) {
                    list.add(new int[]{i, j, input, 0});
                }
            }
        }
        list.sort((o1, o2) -> o1[2] - o2[2]);
        q.addAll(list);

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());

        bfs();

        bw.write(Integer.toString(board[x - 1][y - 1]));
        bw.flush();
    }

    static void bfs() {
        while(!q.isEmpty()) {
            int[] now = q.poll();
            if (now[3] == s) {
                continue;
            }
            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                if (0 <= nx && nx < n && 0 <= ny && ny < n && board[nx][ny] == 0) {
                        board[nx][ny] = now[2];
                        q.add(new int[]{nx, ny, now[2], now[3] + 1});

                }
            }
        }
    }
}
