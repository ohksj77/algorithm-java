package backjoon.study.second;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon1113 {
    private static Queue<int[]> q = new LinkedList<>();
    private static int n, m, result, min = 10, max, dy[] = {0, 0, 1, -1}, dx[] = {1, -1, 0, 0}, board[][];

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        for (int i = 0; i < n; i++) {
            String string = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = string.charAt(j) - '0';
                min = Math.min(min, board[i][j]);
                max = Math.max(max, board[i][j]);
            }
        }
        for (int i = min; i < max; i++) {
            for (int j = 1; j < n - 1; j++) {
                for (int k = 1; k < m - 1; k++) {
                    if (board[j][k] == i) {
                        result += bfs(i, j, k);
                    }
                }
            }
        }
        bw.write(String.valueOf(result));
        bw.flush();
    }

    private static int bfs(int depth, int y, int x) {
        q.clear();
        int count = 1;
        boolean isPossible = true;
        board[y][x] = depth + 1;
        q.add(new int[]{y, x});

        while (!q.isEmpty()) {
            int curY = q.peek()[0];
            int curX = q.poll()[1];
            for (int i = 0; i < 4; i++) {
                int ny = curY + dy[i];
                int nx = curX + dx[i];
                if (ny < 0 || ny >= n || nx < 0 || nx >= m || board[ny][nx] < depth) {
                    isPossible = false;
                    continue;
                }
                if (board[ny][nx] != depth) {
                    continue;
                }
                board[ny][nx] = depth + 1;
                count++;
                q.add(new int[]{ny, nx});
            }
        }
        if (isPossible) return count;
        return 0;
    }
}
