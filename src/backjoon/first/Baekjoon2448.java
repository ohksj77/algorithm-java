package backjoon.first;

import java.io.*;
import java.util.Arrays;

public class Baekjoon2448 {
    private static char[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int rows = Integer.parseInt(br.readLine());
        board = new char[rows][rows * 2];

        Arrays.stream(board).forEach(i -> Arrays.fill(i, ' '));

        for (int i = 0; i < board.length; i++) board[i][board[i].length - 1] = '\n';

        makeBoard(0, rows - 1, rows);

        Arrays.stream(board).forEach(i -> {try{bw.write(i);} catch (IOException e) {}});
        bw.flush();
    }

    private static void makeBoard(int x, int y, int n) {
        if (n == 3) {
            board[x][y] = '*';
            board[x + 1][y - 1] = board[x + 1][y + 1] = '*';
            board[x + 2][y - 2] = board[x + 2][y - 1] = board[x + 2][y] = board[x + 2][y + 1] = board[x + 2][y + 2] = '*';
            return;
        }
        makeBoard(x, y, n / 2);
        makeBoard(x + n / 2, y - n / 2, n / 2);
        makeBoard(x + n / 2, y + n / 2, n / 2);
    }
}
