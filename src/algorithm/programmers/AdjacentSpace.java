package algorithm.programmers;

public class AdjacentSpace {

    private static final int[][] DR = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int solution(String[][] board, int h, int w) {
        int answer = 0;

        for (int[] dr : DR) {
            int nx = h + dr[0];
            int ny = w + dr[1];

            if (0 <= nx && nx < board.length && 0 <= ny && ny < board[0].length) {
                if (board[h][w].equals(board[nx][ny])) {
                    answer++;
                }
            }
        }
        return answer;
    }
}
