import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static final int N = 5;
    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};

    static int k;
    static int[][] dist;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> pieceList = new ArrayList<>();

        for (int r = 0; r < N; r++) {
            String line = br.readLine();
            for (int c = 0; c < N; c++) {
                if (line.charAt(c) == '*')
                    pieceList.add(r * N + c);
            }
        }

        k = pieceList.size();
        precomputeDistances(pieceList);

        dfsComb(0, 0, 0);

        System.out.println(answer);
    }

    private static void precomputeDistances(List<Integer> pieceList) {
        dist = new int[k][N * N];
        for (int p = 0; p < k; p++) {
            int pos = pieceList.get(p);
            int r = pos / N, c = pos % N;
            for (int cell = 0; cell < N * N; cell++) {
                int cr = cell / N, cc = cell % N;
                dist[p][cell] = Math.abs(r - cr) + Math.abs(c - cc);
            }
        }
    }

    private static void dfsComb(int start, int chosen, int mask) {
        if (chosen == k) {
            if (isConnected(mask)) {
                int[] target = new int[k];
                int idx = 0;
                for (int i = 0; i < N * N; i++) {
                    if ((mask & (1 << i)) != 0) target[idx++] = i;
                }
                boolean[] used = new boolean[k];
                permute(0, used, 0, target);
            }
            return;
        }
        for (int i = start; i < N * N; i++) {
            dfsComb(i + 1, chosen + 1, mask | (1 << i));
        }
    }

    private static boolean isConnected(int mask) {
        int start = Integer.numberOfTrailingZeros(mask);
        boolean[] visited = new boolean[N * N];
        ArrayDeque<Integer> q = new ArrayDeque<>();

        visited[start] = true;
        q.add(start);
        int visitedCnt = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();
            visitedCnt++;
            int r = cur / N, c = cur % N;
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d], nc = c + dc[d];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                int nb = nr * N + nc;
                if (!visited[nb] && (mask & (1 << nb)) != 0) {
                    visited[nb] = true;
                    q.add(nb);
                }
            }
        }
        return visitedCnt == k;
    }

    private static void permute(int depth, boolean[] used, int sum, int[] target) {
        if (depth == k) {
            if (sum < answer) answer = sum;
            return;
        }
        if (sum >= answer) return;
        for (int i = 0; i < k; i++) {
            if (!used[i]) {
                used[i] = true;
                int add = dist[depth][target[i]];
                permute(depth + 1, used, sum + add, target);
                used[i] = false;
            }
        }
    }
}
