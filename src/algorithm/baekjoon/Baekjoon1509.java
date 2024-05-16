package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon1509 {

    static int N;
    static int[] dp;
    static boolean[][] palindrome;

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String str = br.readLine();
        N = str.length();
        str = " " + str;
        palindrome = new boolean[N + 1][N + 1];
        dp = new int[N + 1];

        checkPalindrome(str);

        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                if (palindrome[j][i]) {
                    dp[i] = Math.min(dp[i], dp[j - 1] + 1);
                }
            }
        }

        bw.write(Integer.toString(dp[N]));
        bw.flush();
    }

    private static void checkPalindrome(String str) {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                boolean flag = true;
                int from = j;
                int to = i;
                while (from <= to) {
                    if (str.charAt(from++) != str.charAt(to--)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    palindrome[j][i] = true;
                }
            }
        }
    }
}
