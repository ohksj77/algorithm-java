package algorithm.codetree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class 미로_탈출하기 {

    private static final char WALL = '#';
    private static final int[][] DR = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int n = Integer.parseInt(br.readLine());
        final String[] start = br.readLine().split(" ");
        int x = Integer.parseInt(start[0]) - 1;
        int y = Integer.parseInt(start[1]) - 1;
        final char[][] maze = new char[n][n];
        final boolean[][][] visited = new boolean[n][n][4];

        for (int i = 0; i < n; i++) {
            maze[i] = br.readLine().toCharArray();
        }

        int directionIdx = 0;
        int time = 0;

        while (true) {
            if (visited[x][y][directionIdx]) {
                time = -1;
                break;
            }
            visited[x][y][directionIdx] = true;

            final int nx = x + DR[directionIdx][0];
            final int ny = y + DR[directionIdx][1];

            if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                time++;
                break;
            }

            if (maze[nx][ny] != WALL) {
                x = nx;
                y = ny;
                time++;

                final int rightDirectionIdx = (directionIdx + 1) % 4;
                final int rightX = x + DR[rightDirectionIdx][0];
                final int rightY = y + DR[rightDirectionIdx][1];

                if (rightX >= 0
                        && rightX < n
                        && rightY >= 0
                        && rightY < n
                        && maze[rightX][rightY] != WALL) {
                    directionIdx = rightDirectionIdx;
                }
            } else {
                directionIdx = (directionIdx + 3) % 4;
            }

            if (time > n * n) {
                time = -1;
                break;
            }
        }

        bw.write(Integer.toString(time));
        bw.flush();
    }
}
