package algorithm.programmers;

public class LockAndKey {

    public static void main(String[] args) {
        LockAndKey solution = new LockAndKey();

        System.out.println(solution.solution(
                new int[][]{{0,0,0}, {1,0,0}, {0,1,1}},
                new int[][]{{1,1,1}, {1,1,0}, {1,0,1}}));
    }


    private int N;
    private int M;

    public boolean solution(int[][] key, int[][] lock) {
        N = lock.length;
        M = key.length;

        int newLength = N + 2 * (M - 1);

        int[][] board = makeBoard(newLength, lock);

        for (int a = 0; a < 3; a++) {
            for (int i = 0; i < newLength - M + 1; i++) {
                for (int j = 0; j < newLength - M + 1; j++) {
                    for (int k = 0; k < M; k++) {
                        for (int l = 0; l < M; l++) {
                            board[i + k][j + l] += key[k][l];
                        }
                    }
                    if (isAnswer(board)) {
                        return true;
                    }
                    board = makeBoard(newLength, lock);
                }
            }
            key = rotate(key);
        }

        return false;
    }

    public int[][] rotate(int[][] key) {
        int length = key.length;
        int[][] result = new int[length][length];

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                result[i][j] = key[length - 1 - j][i];
            }
        }
        return result;
    }

    public int[][] makeBoard(int length, int[][] board) {
        int[][] result = new int[length][length];
        for (int i = M - 1; i < length - M + 1; i++) {
            for (int j = M - 1; j < length - M + 1; j++) {
                result[i][j] = board[i - M + 1][j - M + 1];
            }
        }
        return result;
    }

    public boolean isAnswer(int[][] board) {
        int len = board.length;
        for (int i = M - 1; i < len - M + 1; i++) {
            for (int j = M - 1; j < len - M + 1; j++) {
                if (board[i][j] != 1) {
                    return false;
                }
            }
        }
        return true;
    }
}
