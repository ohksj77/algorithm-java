package backjoon.baekjoon;

import java.io.*;
import java.util.*;

public class Baekjoon1090 {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        final StringBuilder sb = new StringBuilder();

        final int N = Integer.parseInt(br.readLine());

        final int[][] arr = new int[N][2];
        final int[] xArr = new int[N];
        final int[] yArr = new int[N];

        for (int i = 0; i < N; i++) {
            final StringTokenizer st = new StringTokenizer(br.readLine());
            final int x = Integer.parseInt(st.nextToken());
            final int y = Integer.parseInt(st.nextToken());
            arr[i][0] = y;
            arr[i][1] = x;
            yArr[i] = y;
            xArr[i] = x;
        }

        int[] ansArr = new int[N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                final int curY = yArr[i];
                final int curX = xArr[j];

                final int[] dArr = new int[N];

                for (int k = 0; k < N; k++) {
                    int diff = Math.abs(curY - arr[k][0]) + Math.abs(curX - arr[k][1]);
                    dArr[k] = diff;
                }

                Arrays.sort(dArr);

                for (int k = 1; k < N; k++) {
                    dArr[k] = dArr[k] + dArr[k - 1];
                }

                if (i == 0 && j == 0) {
                    ansArr = Arrays.copyOf(dArr, N);
                }

                for (int k = 0; k < N; k++) {
                    if (ansArr[k] > dArr[k]) {
                        ansArr[k] = dArr[k];
                    }
                }
            }
        }

        for (int k = 0; k < N; k++) {
            sb.append(ansArr[k]).append(" ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
