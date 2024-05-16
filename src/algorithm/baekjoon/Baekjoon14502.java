package algorithm.baekjoon;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class Baekjoon14502 {

    private static final int[][] DR = new int[][] {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    private static int answer = 0;
    private static int n;
    private static int m;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        List<int[]> board = new ArrayList<>(n * m);
        List<int[]> virus = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            final String[] line = br.readLine().split(" ");
            final int[] element = new int[m];
            board.add(element);
            for (int j = 0; j < m; j++) {
                final int num = Integer.parseInt(line[j]);
                element[j] = num;
                if (num == 2) {
                    virus.add(new int[] {i, j});
                }
            }
        }

        dfs(0, virus, board);

        bw.write(Integer.toString(answer));
        bw.flush();
    }

    private static void bfs(List<int[]> virus, List<int[]> board) {
        Queue<int[]> q = new LinkedList<>();
        List<int[]> b = board.stream().map(int[]::clone).collect(Collectors.toList());

        q.addAll(virus);

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int r = now[0];
            int c = now[1];

            for (int[] d : DR) {
                int nr = r + d[0];
                int nc = c + d[1];

                if (0 <= nr && nr < n && 0 <= nc && nc < m && b.get(nr)[nc] == 0) {
                    b.get(nr)[nc] = 2;
                    q.add(new int[] {nr, nc});
                }
            }
        }
        safe(b);
    }

    private static void safe(List<int[]> board) {
        int count = 0;
        for (int[] row : board) {
            for (int num : row) {
                if (num == 0) {
                    count++;
                }
            }
        }
        answer = Math.max(answer, count);
    }

    private static void dfs(int count, List<int[]> virus, List<int[]> board) {
        if (count == 3) {
            bfs(virus, board);
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board.get(i)[j] == 0) {
                    board.get(i)[j] = 1;
                    dfs(count + 1, virus, board);
                    board.get(i)[j] = 0;
                }
            }
        }
    }
}
