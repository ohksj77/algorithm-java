package algorithm.leetcode;

import java.util.*;

class NQueen {

    private final Set<Integer> vertical = new HashSet <>();
    private final Set<Integer> diagonalleft = new HashSet <>();
    private final Set<Integer> diagonalright = new HashSet <>();

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        for(char[] row : board) {
            Arrays.fill(row, '.');
        }

        backtrack(0, board, result);
        return result;
    }

    private void backtrack(int r, char[][] board, List<List<String>> result) {
        if (r == board.length) {
            List<String> ans = new ArrayList<>();
            for(char[] row : board) {
                ans.add(String.copyValueOf(row));
            }
            result.add(ans);
            return;
        }

        for (int i = 0; i < board[r].length; i++) {
            if (!vertical.contains(i) && !diagonalleft.contains(r - i)
                    && !diagonalright.contains(r + i)) {

                board[r][i] = 'Q';
                vertical.add(i);
                diagonalleft.add(r - i);
                diagonalright.add(r + i);

                backtrack(r + 1, board, result);

                board[r][i] = '.';
                vertical.remove(i);
                diagonalleft.remove(r - i);
                diagonalright.remove(r + i);
            }
        }
    }
}
