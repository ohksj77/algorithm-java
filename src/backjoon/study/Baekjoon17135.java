package backjoon.study;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Baekjoon17135 {
    private static int n, m, d;
    private static int map[][];
    private static List<int[]> list = new ArrayList<>();
    private static int[] archer = new int[3];
    private static int result;
    public static void main(String[] args) throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    list.add(new int[] {i, j});
                }
            }
        }

        locateArcher(0, 0);
        bw.write(String.valueOf(result));
        bw.flush();
    }

    private static void locateArcher(int idx, int start) {
        if (idx == 3) {
            List<int[]> data = copy(list);
            attackMonster(data);
            return;
        }
        for (int i = start; i < m; i++) {
            archer[idx] = i;
            locateArcher(idx + 1, i + 1);
        }
    }

    private static void attackMonster(List<int[]> list) {
        int count = 0;
        while (true) {
            if (list.isEmpty()) {
                break;
            }
            List<int[]> targets = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                PriorityQueue<Monster> queue = new PriorityQueue<>();

                for (int j = 0; j < list.size(); j++) {
                    int[] cur = list.get(j);
                    int curD = Math.abs(cur[0] - n) + Math.abs(cur[1] - archer[i]);
                    if (curD <= d) {
                        queue.add(new Monster(cur[0], cur[1], curD));
                    }
                }
                if (!queue.isEmpty()) {
                    Monster target = queue.poll();
                    boolean flag = false;
                    for (int k = 0; k < targets.size(); k++) {
                        int[] cur2 = targets.get(k);
                        if (target.x == cur2[0] && target.y == cur2[1]) {
                            flag = true;
                        }
                    }
                    if (!flag) {
                        targets.add(new int[] {target.x, target.y});
                    }
                }
            }
            for (int i = 0; i < targets.size(); i++) {
                for (int j = list.size() - 1; j >= 0; j--) {
                    if (targets.get(i)[0] == list.get(j)[0] && targets.get(i)[1] == list.get(j)[1]) {
                        list.remove(j);
                        count++;
                        break;
                    }
                }
            }
            for (int i = list.size() - 1; i >= 0; i--) {
                list.get(i)[0]++;
                if (list.get(i)[0] == n) {
                    list.remove(i);
                }
            }
        }
        result = Math.max(result, count);
    }

    private static List<int[]> copy(List<int[]> list) {
        List<int[]> temp = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            int[] cur = list.get(i);
            temp.add(new int[] {cur[0], cur[1]});
        }
        return temp;
    }

    private static class Monster implements Comparable<Monster> {
        int x;
        int y;
        int d;
        public Monster(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        @Override
        public int compareTo(Monster o) {
            if (this.d == o.d) return this.y - o.y;
            return this.d - o.d;
        }
    }
}
