package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon1806 {

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final String[] input = br.readLine().split(" ");
        final int n = Integer.parseInt(input[0]);
        final int m = Integer.parseInt(input[1]);
        final int length = n + 1;
        final int[] num = new int[length];

        final String[] s = br.readLine().split(" ");

        for (int i = 0; i < length; i++) {
            if (i == n) {
                break;
            }
            num[i] = Integer.parseInt(s[i]);
        }

        int min = Integer.MAX_VALUE;
        int start = 0;
        int end = 0;
        int sum = 0;

        while (start <= n && end <= n) {
            if (sum >= m && min > end - start) {
                min = end - start;
            }
            if (sum < m) {
                sum += num[end++];
                continue;
            }
            sum -= num[start++];
        }

        bw.write(min == Integer.MAX_VALUE ? "0" : Integer.toString(min));
        bw.flush();
    }
}
