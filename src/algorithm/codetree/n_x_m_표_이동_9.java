package algorithm.codetree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class n_x_m_표_이동_9 {

    private static final int[][] DR = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static int n;
    private static int m;
    private static int[][] board;

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final String[] input = br.readLine().trim().split(" ");
        n = Integer.parseInt(input[0].trim());
        m = Integer.parseInt(input[1].trim());

        board = new int[n][];

        for (int i = 0; i < n; i++) {
            board[i] = makeLine(br.readLine().trim());
        }

        bw.write(Integer.toString(bfs()));
        bw.flush();
    }

    private static int[] makeLine(final String input) {
        final String[] inputArray = input.split(" ");
        final int[] line = new int[m];
        for (int i = 0; i < m; i++) {
            line[i] = Integer.parseInt(inputArray[i]);
        }
        return line;
    }

    private static int bfs() {
        final Queue<int[]> q = new LinkedList<>();
        final boolean[][] visited = new boolean[n][m];
        visited[0][0] = true;

        if (board[0][0] == 1) {
            q.add(new int[] {0, 0, 1, 1});
        } else {
            q.add(new int[] {0, 0, 1, 0});
        }

        while (!q.isEmpty()) {
            final int[] now = q.poll();
            final int x = now[0];
            final int y = now[1];
            final int count = now[2];
            final int passed = now[3];

            for (int[] dr : DR) {
                final int nx = x + dr[0];
                final int ny = y + dr[1];

                if (nx < 0
                        || nx >= n
                        || ny < 0
                        || ny >= m
                        || visited[nx][ny]
                        || (passed == 1 && board[nx][ny] == 1)) {
                    continue;
                }

                if (nx == n - 1 && ny == m - 1) {
                    return count + 1;
                }

                visited[nx][ny] = true;
                if (board[nx][ny] == 1) {
                    q.add(new int[] {nx, ny, count + 1, 1});
                    continue;
                }
                q.add(new int[] {nx, ny, count + 1, passed});
            }
        }
        return -1;
    }
}
