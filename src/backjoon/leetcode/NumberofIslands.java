package backjoon.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class NumberofIslands {

    private int islands = 0;
    private final int[][] dr = new int[][] {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    private boolean[][] visited;

    public int numIslands(char[][] grid) {
        visited = new boolean[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    islands++;
                    bfs(grid, i, j);
                }
            }
        }

        return islands;
    }

    private void bfs(char[][] grid, int r, int c) {

        Queue<int[]> q = new LinkedList<>();

        q.add(new int[] {r, c});
        visited[r][c] = true;

        while (!q.isEmpty()) {
            int[] now = q.poll();

            int cr = now[0];
            int cc = now[1];

            for (int[] d : dr) {
                int nr = cr + d[0];
                int nc = cc + d[1];

                if (0 <= nr && nr < grid.length && 0 <= nc && nc < grid[0].length) {
                    if (!visited[nr][nc] && grid[nr][nc] == '1') {
                        visited[nr][nc] = true;
                        q.add(new int[] {nr, nc});
                    }
                }
            }
        }
    }
}
