package backjoon.first;

import java.io.*;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon9328 {

    private static class Pos {
        int r;
        int c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};
    private static int[][] map;
    private static boolean[][] visited;
    private static boolean[] keys;
    private static Queue<Pos> locked = new LinkedList<>();
    private static int h, w, result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter((System.out)));
        int t = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            result = 0;
            map = new int[h + 2][w + 2];
            visited = new boolean[h + 2][w + 2];
            keys = new boolean[26];
            locked.clear();
            for (int i = 1; i <= h; i++) {
                String str = br.readLine();
                for (int j = 1; j <= w; j++) map[i][j] = str.charAt(j - 1);
            }
            String str = br.readLine();
            if (!str.equals("0")) {
                for (int i = 0; i < str.length(); i++) {
                    char c = str.charAt(i);
                    keys[c - 97] = true;
                }
            }
            bfs(0, 0);

            while (true) {
                boolean isUpdate = false;
                int size = locked.size();
                for (int i = 0; i < size; i++) {
                    Pos cur = locked.poll();
                    if (!keys[map[cur.r][cur.c] - 65]) {
                        locked.offer(cur);
                        continue;
                    }
                    isUpdate = true;
                    bfs(cur.r, cur.c);
                }
                if (!isUpdate || locked.isEmpty()) {
                    break;
                }
            }

            bw.write(Integer.toString(result));
            bw.newLine();
        }
        bw.flush();
    }

    private static void bfs(int r, int c) {
        Queue<Pos> q = new ArrayDeque<>();
        q.add(new Pos(r, c));
        visited[r][c] = true;
        while (!q.isEmpty()) {
            Pos cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int tempR = cur.r + dr[i];
                int tempC = cur.c + dc[i];
                if (inSpace(tempR, tempC) && !visited[tempR][tempC] && map[tempR][tempC] != 42) {
                    visited[tempR][tempC] = true;
                    if (map[tempR][tempC] >= 97) {
                        keys[map[tempR][tempC] - 97] = true;
                        q.add(new Pos(tempR, tempC));
                    } else if (map[tempR][tempC] >= 65) {
                        if (keys[map[tempR][tempC] - 65]) {
                            q.add(new Pos(tempR, tempC));
                        } else {
                            locked.add(new Pos(tempR, tempC));
                        }
                    } else {
                        if (map[tempR][tempC] == 36) {
                            result++;
                        }
                        q.offer(new Pos(tempR, tempC));
                    }
                }
            }
        }
    }

    private static boolean inSpace(int r, int c) {
        if (r >= 0 && c >= 0 && r <= h + 1 && c <= w + 1) {
            return true;
        }
        return false;
    }
}
