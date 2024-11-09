package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon23634 {
    private static final int[] dr = {-1, 0, 1, 0};
    private static final int[] dc = {0, -1, 0, 1};
    private static Queue<int[]> trees;
    private static int N;
    private static int M;
    private static int[][] graph;
    private static int[][] groupIndex;
    private static int groupMax;
    private static int[] parent;
    private static int[] grouped;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        trees = new ArrayDeque<>();

        graph = new int[N][M];
        groupIndex = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                graph[i][j] = line.charAt(j) - '0';
            }
        }

        findGroup();

        if (groupMax <= 1) {
            bw.write("0 " + countFire());
        } else {
            int time = 0;
            unionFire();
            while (!confluence()) {
                goFire();
                time++;
            }
            bw.write(time + " " + countFire());
        }
        bw.flush();
    }

    private static void findGroup() {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        int index = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (graph[i][j] == 0) {
                    if (!visited[i][j]) {
                        q.add(new int[] {i, j});

                        visited[i][j] = true;
                        groupIndex[i][j] = index;

                        while (!q.isEmpty()) {
                            int[] current = q.poll();
                            int curR = current[0];
                            int curC = current[1];

                            for (int d = 0; d < 4; d++) {
                                int newR = curR + dr[d];
                                int newC = curC + dc[d];

                                if (0 <= newR && newR < N && 0 <= newC && newC < M) {
                                    if (!visited[newR][newC]) {
                                        visited[newR][newC] = true;

                                        if (graph[newR][newC] == 0) {
                                            groupIndex[newR][newC] = index;
                                            q.add(new int[] {newR, newC});
                                        } else if (graph[newR][newC] == 1) {
                                            trees.add(new int[] {newR, newC});
                                        }
                                    }
                                }
                            }
                        }
                        index++;
                    }
                }
            }
        }

        groupMax = index - 1;
    }

    private static void unionFire() {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        boolean[] searched = new boolean[groupMax + 1];

        parent = new int[groupMax + 1];
        grouped = new int[groupMax + 1];

        for (int i = 1; i <= groupMax; i++) {
            parent[i] = i;
            grouped[i] = i;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && graph[i][j] == 0 && !searched[groupIndex[i][j]]) {
                    int groupFlag = groupIndex[i][j];

                    q.add(new int[] {i, j});
                    visited[i][j] = true;
                    searched[groupFlag] = true;

                    while (!q.isEmpty()) {
                        int[] current = q.poll();
                        int curR = current[0];
                        int curC = current[1];

                        for (int d = 0; d < 4; d++) {
                            int newR = curR + dr[d];
                            int newC = curC + dc[d];

                            if (0 <= newR && newR < N && 0 <= newC && newC < M) {
                                if (!visited[newR][newC] && graph[newR][newC] != 2) {
                                    visited[newR][newC] = true;

                                    if (graph[newR][newC] == 0
                                            && groupIndex[newR][newC] != groupFlag
                                            && !searched[groupIndex[newR][newC]]) {
                                        searched[groupIndex[newR][newC]] = true;
                                        union(groupFlag, groupIndex[newR][newC], grouped);
                                    }

                                    q.add(new int[] {newR, newC});
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static void goFire() {
        Queue<int[]> newTrees = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];

        while (!trees.isEmpty()) {
            int[] current = trees.poll();
            int curR = current[0];
            int curC = current[1];

            graph[curR][curC] = 0;

            for (int i = 0; i < 4; i++) {
                int newR = curR + dr[i];
                int newC = curC + dc[i];

                if (0 <= newR && newR < N && 0 <= newC && newC < M) {
                    if (graph[newR][newC] == 1 && !visited[newR][newC]) {
                        visited[newR][newC] = true;
                        newTrees.add(new int[] {newR, newC});
                    } else if (groupIndex[newR][newC] > 0) {
                        if (groupIndex[curR][curC] == 0)
                            groupIndex[curR][curC] = groupIndex[newR][newC];
                        else {
                            union(groupIndex[newR][newC], groupIndex[curR][curC], parent);
                        }
                    }
                }
            }
        }
        trees = newTrees;
    }

    private static int countFire() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (graph[i][j] == 0) cnt++;
            }
        }
        return cnt;
    }

    private static void union(int a, int b, int[] parent) {
        int aParent = find(a, parent);
        int bParent = find(b, parent);

        if (aParent < bParent) {
            parent[bParent] = aParent;
        } else if (bParent < aParent) {
            parent[aParent] = bParent;
        }
    }

    private static int find(int target, int[] parent) {
        if (target == parent[target]) {
            return target;
        }
        return parent[target] = find(parent[target], parent);
    }

    private static boolean confluence() {
        for (int i = 1; i <= groupMax; i++) {
            if (find(parent[i], grouped) != find(parent[i], parent)) {
                return false;
            }
        }
        return true;
    }
}
