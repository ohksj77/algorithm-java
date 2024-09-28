package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Baekjoon2234 {

    private static final int[][] DR = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    private static int[][] map;
    private static boolean[][] visited;
    private static int n;
    private static int m;
    private static int maxSize = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final String[] nm = br.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);
        map = new int[m][n];
        visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            final String[] input = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        int roomCnt = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    bfs(i, j);
                    roomCnt += 1;
                }
            }
        }
        bw.write(Integer.toString(roomCnt));
        bw.newLine();
        bw.write(Integer.toString(maxSize));
        bw.newLine();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                breakWall(i, j);
            }
        }
        bw.write(Integer.toString(maxSize));
        bw.flush();
    }

    private static void breakWall(final int startRow, final int startCol) {
        for (int i = 0; i < 4; i++) {
            if ((map[startRow][startCol] & (1 << i)) != 0) {
                for (final boolean[] booleans : visited) {
                    Arrays.fill(booleans, false);
                }
                map[startRow][startCol] -= (1 << i);
                bfs(startRow, startCol);
                map[startRow][startCol] += (1 << i);
            }
        }
    }

    private static void bfs(final int startRow, final int startCol) {
        final Queue<Node> q = new LinkedList<>();
        q.offer(new Node(startRow, startCol));
        visited[startRow][startCol] = true;
        int roomSize = 0;

        while (!q.isEmpty()) {

            Node node = q.poll();
            final int row = node.row;
            final int col = node.col;
            final int wall = map[row][col];
            roomSize += 1;

            for (int i = 0; i < 4; i++) {

                if ((wall & (1 << i)) > 0) {
                    continue;
                }

                int nr = row + DR[i][0];
                int nc = col + DR[i][1];

                if (isBoundary(nr, nc) && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.offer(new Node(nr, nc));
                }
            }
        }
        maxSize = Math.max(maxSize, roomSize);
    }

    private static boolean isBoundary(final int row, final int col) {
        return (row >= 0 && row < m) && (col >= 0 && col < n);
    }

    private static class Node {

        private final int row;
        private final int col;

        public Node(final int row, final int col) {
            this.row = row;
            this.col = col;
        }
    }
}
