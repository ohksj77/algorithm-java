package algorithm.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathInBinaryMatrix {

    private final int[][] dr =
            new int[][] {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}, {0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public int shortestPathBinaryMatrix(int[][] grid) {

        if (grid[0][0] == 1 || grid[grid.length - 1][grid[0].length - 1] == -1) {
            return -1;
        }

        Queue<int[]> q = new LinkedList<>();

        q.add(new int[] {0, 0, 0});

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int nc = now[0];
            int nr = now[1];

            if (nc == grid.length - 1 && nr == grid[0].length - 1) {
                return now[2] + 1;
            }

            for (int[] d : dr) {
                int nextC = nc + d[0];
                int nextR = nr + d[1];

                if (0 <= nextC && nextC < grid.length && 0 <= nextR && nextR < grid[0].length) {
                    if (grid[nextC][nextR] == 0) {
                        grid[nextC][nextR] = 1;
                        q.add(new int[] {nextC, nextR, now[2] + 1});
                    }
                }
            }
        }
        return -1;
    }
}
