package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon1053 {
    private static final StringBuilder SB = new StringBuilder();
    private static final int[][] DP = new int[51][51];

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int result;

        SB.append(br.readLine().trim());

        dps(0, 0);
        result = DP[0][SB.length() - 1];

        for (int ch = 0; ch < SB.length(); ch++) {
            for (int ta = ch + 1; ta < SB.length(); ta++) {
                dps(ch, ta);
                result = Math.min(result, DP[0][SB.length() - 1] + 1);
                swap(ch, ta);
            }
        }
        bw.write(Integer.toString(result));
        bw.flush();
    }

    private static void swap(int src, int dst) {
        char temp = SB.charAt(src);
        SB.setCharAt(src, SB.charAt(dst));
        SB.setCharAt(dst, temp);
    }

    private static void dps(int ch, int ta) {
        swap(ch, ta);

        for (int i = 0; i < SB.length(); i++) {
            DP[i][i] = 0;
            if (i < SB.length() - 1) {
                if (SB.charAt(i) == SB.charAt(i + 1)) {
                    DP[i][i + 1] = 0;
                } else {
                    DP[i][i + 1] = 1;
                }
            }
        }

        for (int i = 2; i < SB.length(); i++) {
            for (int j = 0; j + i < SB.length(); j++) {
                DP[j][j + i] = Math.min(DP[j + 1][j + i] + 1, DP[j][j + i - 1] + 1);
                if (SB.charAt(j + i) == SB.charAt(j)) {
                    DP[j][j + i] = Math.min(DP[j + 1][j + i - 1], DP[j][j + i]);
                } else {
                    DP[j][j + i] = Math.min(DP[j + 1][j + i - 1] + 1, DP[j][j + i]);
                }
            }
        }
    }
}
