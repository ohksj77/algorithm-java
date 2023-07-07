package backjoon.study.second;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon14466 {
    private static final int[][] dr = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private static int[][][] map;
    private static int size;
    private static int[][] cows;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        size = Integer.parseInt(st.nextToken());
        int cow = Integer.parseInt(st.nextToken());
        int command = Integer.parseInt(st.nextToken());

        map = new int[size][size][5];
        while (command-- > 0) {
            st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken()) - 1;
            int startY = Integer.parseInt(st.nextToken()) - 1;
            int endX = Integer.parseInt(st.nextToken()) - 1;
            int endY = Integer.parseInt(st.nextToken()) - 1;

            for (int i = 0; i < 4; i++) {
                int[] dir = dr[i];
                if (startX + dir[0] == endX && startY + dir[1] == endY) {
                    map[startX][startY][i] = -1;
                    map[endX][endY][(i + 2) % 4] = -1;
                    break;
                }
            }
        }

        cows = new int[cow][2];
        for (int i = 0; i < cow; i++) {
            st = new StringTokenizer(br.readLine());
            int cowX = Integer.parseInt(st.nextToken()) - 1;
            int cowY = Integer.parseInt(st.nextToken()) - 1;
            cows[i] = new int[]{cowX, cowY};
            map[cowX][cowY][4] = i + 1;
        }

        int sum = 0;
        for (int i = 0; i < cow; i++) {
            sum += (cow - 1 - bfs(i));
        }

        bw.write(Integer.toString(sum / 2));
        bw.flush();
    }

    private static int bfs(int idx) {
        boolean[][] visited = new boolean[size][size];
        visited[cows[idx][0]][cows[idx][1]] = true;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(cows[idx]);
        int visitCnt = 0;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            for (int i = 0; i < 4; i++) {
                int[] dir = dr[i];
                int mvx = now[0] + dir[0];
                int mvy = now[1] + dir[1];

                if (mvx < 0 || mvy < 0 || size <= mvx || size <= mvy || visited[mvx][mvy]) {
                    continue;
                }
                if (map[now[0]][now[1]][i] == -1) {
                    continue;
                }

                if (map[mvx][mvy][4] != 0) {
                    visitCnt++;
                }
                visited[mvx][mvy] = true;
                queue.add(new int[]{mvx, mvy});
            }
        }
        return visitCnt;
    }
}