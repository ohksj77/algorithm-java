package algorithm.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class MineSweeper {

    private static final int[][] DR =
            new int[][] {{1, 0}, {0, -1}, {0, 1}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    private static final char MINE = 'M';
    private static final char EMPTY = 'E';
    private static final char BLANK = 'B';
    private static final char X = 'X';
    private int n;
    private int m;

    public char[][] updateBoard(char[][] board, int[] click) {
        n = board.length;
        m = board[0].length;

        int startR = click[0];
        int startC = click[1];

        if (board[startR][startC] == MINE) {
            board[startR][startC] = X;
            return board;
        }

        return bfs(board, startR, startC);
    }

    private char[][] bfs(char[][] board, int r, int c) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {r, c});

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int cr = now[0];
            int cc = now[1];

            int count = 0;
            for (int[] d : DR) {
                int nr = cr + d[0];
                int nc = cc + d[1];

                if (nr < 0 || nr >= n || nc < 0 || nc >= m) {
                    continue;
                }

                if (board[nr][nc] == MINE) {
                    count++;
                }
            }

            /** 주변에 지뢰가 있는 경우 */
            if (count > 0) {
                board[cr][cc] = convertToChar(count);
                continue;
            }
            board[cr][cc] = BLANK;

            /** 주변의 EMPTY 칸들을 BLANK로 바꾸기 */
            for (int[] d : DR) {
                int nr = cr + d[0];
                int nc = cc + d[1];

                if (nr < 0 || nr >= n || nc < 0 || nc >= m) {
                    continue;
                }

                /** 이미 BLANK인 경우를 제외하고 연산 수행 */
                if (board[nr][nc] == EMPTY) {
                    q.add(new int[] {nr, nc});
                    board[nr][nc] = BLANK;
                }
            }
        }

        return board;
    }

    /** '0' + 1 = '1' */
    private char convertToChar(int num) {
        return (char) (num + '0');
    }
}
