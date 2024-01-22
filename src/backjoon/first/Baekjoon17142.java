package backjoon.first;

import java.io.*;
import java.util.*;

public class Baekjoon17142 {

    private static final int[] DR = {-1, 0, 1, 0};
    private static final int[] DC = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] line = br.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int M = Integer.parseInt(line[1]);

        char[][] map = new char[N][N];
        List<Point> poison = new ArrayList<>();

        int target = 0;
        for (int r = 0; r < N; r++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = st.nextToken().charAt(0);
                if (map[r][c] == '2') {
                    poison.add(new Point(r, c));
                } else if (map[r][c] == '0') {
                    target++;
                }
            }
        }

        List<Set<Integer>> combination = combination(poison.size(), M);

        int result = Integer.MAX_VALUE;
        for (Set<Integer> active : combination) {
            char[][] copy = copyMap(map);
            int infacted = 0;
            int time = 0;
            Queue<Point> q = new ArrayDeque<>();
            for (Integer i : active) {
                Point p = poison.get(i);
                q.add(p);
                copy[p.r][p.c] = '1';
            }

            while (!q.isEmpty()) {
                Point cur = q.remove();

                for (int i = 0; i < 4; i++) {
                    Point next = new Point(cur.r + DR[i], cur.c + DC[i]);
                    if (next.inRange(N, N) && copy[next.r][next.c] != '1') {
                        if (copy[next.r][next.c] == '0') {
                            infacted++;
                            time = cur.t + 1;
                        }
                        copy[next.r][next.c] = '1';
                        next.t = cur.t + 1;
                        q.add(next);
                    }
                }

                if (infacted == target) {
                    break;
                }
            }

            if (infacted == target) {
                result = Math.min(result, time);
            }
        }

        bw.write(Integer.toString((result == Integer.MAX_VALUE) ? -1 : result));
        bw.flush();
    }

    static char[][] copyMap(char[][] map) {
        char[][] result = new char[map.length][map[0].length];
        for (int r = 0; r < map.length; r++) {
            result[r] = map[r].clone();
        }
        return result;
    }

    static List<Set<Integer>> combination(int n, int k) {
        List<Set<Integer>> result = new ArrayList<>();
        Set<Integer> temp = new HashSet<>();
        for (int i = 0; i < n; i++) {
            temp.add(i);
            recursion(n, k, result, temp, i);
            temp.remove(i);
        }
        return result;
    }

    static void recursion(int n, int k, List<Set<Integer>> result, Set<Integer> temp, int index) {
        if (temp.size() == k) {
            result.add(new HashSet<>(temp));
            return;
        }

        for (int i = index + 1; i < n; i++) {
            temp.add(i);
            recursion(n, k, result, temp, i);
            temp.remove(i);
        }
    }

    static class Point {
        int r;
        int c;
        int t;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public boolean inRange(int n, int m) {
            return r >= 0 && r < n && c >= 0 && c < m;
        }
    }
}
