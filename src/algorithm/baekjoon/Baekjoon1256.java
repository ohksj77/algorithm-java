package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon1256 {

    private static final int[][] DP = new int[101][101];
    private static final StringBuilder SB = new StringBuilder();
    private static final String A = "a";
    private static final String Z = "z";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] st = br.readLine().split(" ");

        int n = Integer.parseInt(st[0]);
        int m = Integer.parseInt(st[1]);
        int k = Integer.parseInt(st[2]);

        if (check(n, m) < k) {
            bw.write("-1");
        } else {
            find(n, m, k);
            bw.write(SB.toString());
        }
        bw.flush();
    }

    public static int check(int n, int m) {
        if (n == 0 || m == 0) {
            return 1;
        }
        if (DP[n][m] != 0) {
            return DP[n][m];
        }
        return DP[n][m] = Integer.min(check(n - 1, m) + check(n, m - 1), 1000000001);
    }

    public static void find(int n, int m, int k) {
        if (n == 0) {
            SB.append(Z.repeat(Math.max(0, m)));
            return;
        }
        if (m == 0) {
            SB.append(A.repeat(Math.max(0, n)));
            return;
        }
        int check = check(n - 1, m);
        if (k > check) {
            SB.append(Z);
            find(n, m - 1, k - check);
        } else {
            SB.append(A);
            find(n - 1, m, k);
        }
    }
}
