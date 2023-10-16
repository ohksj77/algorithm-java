package backjoon.study.sixth;

import java.io.*;
import java.util.*;

public class Baekjoon18428 {
    static List<int[]> student = new ArrayList<>();
    static int n;
    static char[][] board;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new char[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                char c = st.nextToken().charAt(0);
                board[i][j] = c;
                if (c == 'S') {
                    student.add(new int[]{i, j});
                }
            }
        }
        dfs(0);

        bw.write("NO");
        bw.flush();
    }

    static void dfs(int wall) throws IOException {
        if (wall == 3) {
            bfs();
            return;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'X') {
                    board[i][j] = '0';
                    dfs(wall + 1);
                    board[i][j] = 'X';
                }
            }
        }
    }

    static void bfs() throws IOException {
        Queue<int[]> q = new LinkedList<>();
        char[][] copyMap = new char[n][n];
        boolean[][] check = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copyMap[i][j] = board[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (copyMap[i][j] == 'T') {
                    q.add(new int[]{i, j});
                    check[i][j] = true;
                }
            }
        }
        while (!q.isEmpty()) {
            int[] now = q.poll();
            int x = now[0];
            int y = now[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                while (0 <= nx && nx < n && 0 <= ny && ny < n) {
                    if (copyMap[nx][ny] != '0') {
                        check[nx][ny] = true;
                        nx += dx[i];
                        ny += dy[i];
                    } else {
                        break;
                    }
                }
            }
        }
        if (catchStudent(check)) {
            bw.write("YES");
            bw.flush();
            System.exit(0);
        }
    }

    static boolean catchStudent(boolean[][] check) {
        for (int[] s : student) {
            if (check[s[0]][s[1]] == true) {
                return false;
            }
        }
        return true;
    }
}
