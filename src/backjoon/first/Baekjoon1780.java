package backjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon1780 {

    private static int zero = 0, one = 0, minus = 0;
    private static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        board = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        partition(0, 0, n);

        System.out.println(minus + "\n" + zero + "\n" + one);

        br.close();

    }

    public static void partition(int row, int col, int size) {

        if (check(row, col, size)) {
            if (board[row][col] == -1) {
                minus++;
            } else if (board[row][col] == 0) {
                zero++;
            } else {
                one++;
            }

            return;
        }

        int newSize = size / 3;

        partition(row, col, newSize);
        partition(row, col + newSize, newSize);
        partition(row, col + 2 * newSize, newSize);

        partition(row + newSize, col, newSize);
        partition(row + newSize, col + newSize, newSize);
        partition(row + newSize, col + 2 * newSize, newSize);

        partition(row + 2 * newSize, col, newSize);
        partition(row + 2 * newSize, col + newSize, newSize);
        partition(row + 2 * newSize, col + 2 * newSize, newSize);

    }

    public static boolean check(int row, int col, int size) {
        int color = board[row][col];

        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (color != board[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

}
