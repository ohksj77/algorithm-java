package backjoon.baekjoon;

import java.io.*;

public class Baekjoon1799 {
    private static int n, macCnt;
    private static int[] cache;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        cache = new int[2 * n - 1];
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }

        putBishop(0, 0);
        bw.write(Integer.toString(macCnt));
        bw.flush();
    }

    private static void putBishop(int idx, int cnt) {
        if (idx == (2 * n) - 1) {
            if (macCnt < cnt) macCnt = cnt;
            return;
        }

        boolean isExist = false;
        for (int i = 0; i <= idx; i++) {
            if (i >= n || idx - i >= n || map[i][idx - i] == 0 || !isAvailable(i, idx - i, idx)) {
                continue;
            }
            cache[idx] = i;
            putBishop(idx + 1, cnt + 1);
            isExist = true;
        }
        if (!isExist) {
            cache[idx] = -1;
            putBishop(idx + 1, cnt);
        }
    }

    private static boolean isAvailable(int x, int y, int idx) {
        while (idx >= 2 && x >= 1 && y >= 1) {
            idx -= 2;
            x -= 1;
            y -= 1;
            if (cache[idx] == x) {
                return false;
            }
        }

        return true;
    }
}
