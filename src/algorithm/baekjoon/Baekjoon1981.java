package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Baekjoon1981 {

    private static final int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static List<Integer> numbers = new ArrayList<>();
    private static int[][] map;
    private static int n;

    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (!numbers.contains(map[i][j])) {
                    numbers.add(map[i][j]);
                }
            }
        }

        Collections.sort(numbers);

        bw.write(Integer.toString(solve()));
        bw.flush();
    }

    private static int solve() {
        int min = 0;
        int max = 0;
        int answer = Integer.MAX_VALUE;

        while (min < numbers.size() && max < numbers.size()) {
            if (bfs(numbers.get(min), numbers.get(max))) {
                int gap = numbers.get(max) - numbers.get(min);
                answer = Math.min(answer, gap);
                min++;
                continue;
            }
            max++;
        }

        return answer;
    }

    private static boolean bfs(int min, int max) {

        if (map[0][0] < min || map[0][0] > max) {
            return false;
        }

        boolean[][] visited = new boolean[n][n];
        Queue<Node> q = new LinkedList<>();

        q.offer(new Node(0, 0));
        visited[0][0] = true;

        while (!q.isEmpty()) {
            Node now = q.poll();

            if (now.x == n - 1 && now.y == n - 1) {
                return true;
            }

            for (int d = 0; d < 4; ++d) {
                int nr = now.x + dir[d][0];
                int nc = now.y + dir[d][1];

                if (nr >= n || nr < 0 || nc >= n || nc < 0 || visited[nr][nc]) {
                    continue;
                }

                if (map[nr][nc] >= min && map[nr][nc] <= max) {
                    visited[nr][nc] = true;
                    q.offer(new Node(nr, nc));
                }
            }
        }
        return false;
    }
}
