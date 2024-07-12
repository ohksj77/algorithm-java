package algorithm.codetree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class 최소_개수의_막대기 {

    private static int n;
    private static int m;
    private static char[][] board;
    private static int minSticks = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final String[] nm = br.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);

        board = new char[n][m];

        for (int i = 0; i < n; i++) {
            board[i] = br.readLine().toCharArray();
        }

        dfs(0);

        bw.write(Integer.toString(minSticks));
        bw.flush();
    }

    private static void dfs(final int stickCount) {
        final int[] xy = getStart();

        if (xy.length == 0) {
            minSticks = Math.min(minSticks, stickCount);
            return;
        }

        int x = xy[0];
        int y = xy[1];

        final List<int[]> coordinates = new ArrayList<>();
        coordinates.add(xy);
        board[x][y] = '.';

        while (++y < m && board[x][y] != '.') {
            coordinates.add(new int[] {x, y});
            board[x][y] = '.';
        }

        dfs(stickCount + 1);

        for (int[] coordinate : coordinates) {
            board[coordinate[0]][coordinate[1]] = '*';
        }

        coordinates.clear();
        coordinates.add(xy);
        x = xy[0];
        y = xy[1];
        board[x][y] = '.';

        while (++x < n && board[x][y] != '.') {
            coordinates.add(new int[] {x, y});
            board[x][y] = '.';
        }

        dfs(stickCount + 1);

        for (int[] coordinate : coordinates) {
            board[coordinate[0]][coordinate[1]] = '*';
        }
    }

    private static int[] getStart() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == '*') {
                    return new int[] {i, j};
                }
            }
        }
        return new int[0];
    }
}
