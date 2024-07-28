package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon14503 {
    private static int n;
    private static int m;
    private static int r;
    private static int c;
    private static int d;
    private static int[][] board;
    private static int count = 1;
    private static final int[][] DR = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final String[] firstInput = br.readLine().split(" ");

        n = Integer.parseInt(firstInput[0]);
        m = Integer.parseInt(firstInput[1]);

        final String[] secondInput = br.readLine().split(" ");
        r = Integer.parseInt(secondInput[0]);
        c = Integer.parseInt(secondInput[1]);
        d = Integer.parseInt(secondInput[2]);

        board = new int[n][];
        for (int i = 0; i < n; i++) {
            board[i] =
                    Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        clean(r, c, d);
        bw.write(Integer.toString(count));
        bw.flush();
    }

    public static void clean(int x, int y, int dir) {
        board[x][y] = -1;

        for (int i = 0; i < 4; i++) {
            dir = (dir + 3) % 4;
            final int[] dr = DR[dir];

            final int nx = x + dr[0];
            final int ny = y + dr[1];
            if (nx >= 0 && ny >= 0 && nx < n && ny < m && board[nx][ny] == 0) {
                count++;
                clean(nx, ny, dir);
                return;
            }
        }

        final int d = (dir + 2) % 4;
        final int[] dr = DR[d];
        final int bx = x + dr[0];
        final int by = y + dr[1];

        if (bx >= 0 && by >= 0 && bx < n && by < m && board[bx][by] != 1) {
            clean(bx, by, dir);
        }
    }
}
